package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.R;
import com.example.hanlian.TestToken.TestToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Allorder.AllOrders;
import Allorder.Result;
import Allorder.TBORDERDETAIL;
import okhttp3.Call;
import utils.TCHConstants;

public class AllOrderActivity extends Activity implements OnClickListener {

    private ListView listView_allorder;
    private AllOrderAdapter adapter;
    private String arg0;
    private int pageNum = 1;
    private int pageSize = 0;
    private DisplayImageOptions options;
    ArrayList<Result> allorderlist = new ArrayList<Result>();
    ArrayList<TBORDERDETAIL> detailslist = new ArrayList<TBORDERDETAIL>();
    private ImageView img_allorder_back;
    private TextView tv_nomore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
//		PushAgent.getInstance(this).onAppStart();
        initUI();
        intiUIL();
        initData();

    }

    private void intiUIL() {
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.err)
                // 设置图片在下载期间显示的默认图片
                .showImageForEmptyUri(R.drawable.err)
                // 设置URI错误或者为空时显示的图片
                .showImageOnFail(R.drawable.err)
                // 设置图片加载或者解析出现错误时设置的图片
                .cacheInMemory(true)
                // 是否内存缓存
                .cacheOnDisk(true)
                // 是否外存缓存
                .considerExifParams(true)
                // .displayer(new RoundedBitmapDisplayer(100))// 是否设置图片圆角
                .displayer(new FadeInBitmapDisplayer(400))// 图片加载好之后渐入的时间
                .build();

    }

    private void initData() {
        //查询订单
        pageSize += 10;
        Map<String, String> parms1 = new HashMap<String, String>();
        parms1.put("pageNum", pageNum + "");
        parms1.put("pageSize", pageSize + "");
        parms1.put("token", TCHConstants.url.token);
        // TODO
        HTTPUtils.get(AllOrderActivity.this, TCHConstants.url.QueryMyAllOrders_Url, parms1, new ResponseListener() {

            @Override
            public void onResponse(String arg0) {

                Log.e("查询全部订单arg0==", arg0);

                AllOrders allorderjson = GsonUtils.parseJSON(arg0, AllOrders.class);

                Integer errorCode2 = allorderjson.getErrorCode();
//						   Toast.makeText(AllOrderActivity.this, "errorCode=="+errorCode2, Toast.LENGTH_SHORT).show();
                if (errorCode2 == 0) {
                    List<Result> result = allorderjson.getResult();
                    allorderlist.clear();
                    allorderlist.addAll(result);
//                    if (allorderlist.size() < 3) {
//                        tv_nomore.setVisibility(View.INVISIBLE);
//                    } else {
//                        tv_nomore.setVisibility(View.VISIBLE);
//                    }
                    listView_allorder.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(AllOrderActivity.this, "arg0" + arg0, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initUI() {
        img_allorder_back = (ImageView) findViewById(R.id.img_allorder_back);
        img_allorder_back.setOnClickListener(this);
        tv_nomore = (TextView) findViewById(R.id.tv_nomore);
        listView_allorder = (ListView) findViewById(R.id.listView_allorder);
        adapter = new AllOrderAdapter();

    }

    class AllOrderAdapter extends BaseAdapter {
        View layout = null;
        ViewHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new ViewHolder();
                layout = getLayoutInflater().inflate(R.layout.layout_item_allorder, null);
                holder.img_shangpin_name = (ImageView) layout.findViewById(R.id.img_shangpin_name);
                holder.tv_shangpin_name = (TextView) layout.findViewById(R.id.tv_shangpin_name);
                holder.tv_shoujia = (TextView) layout.findViewById(R.id.tv_shoujia);
                holder.tv_xiaoliang = (TextView) layout.findViewById(R.id.tv_xiaoliang);
                holder.tv_price = (TextView) layout.findViewById(R.id.tv_price);
                holder.tv_state = (TextView) layout.findViewById(R.id.tv_state);
                holder.tv_total_sales = (TextView) layout.findViewById(R.id.tv_total_sales);
                holder.btn_confirmshouhuo = (Button) layout.findViewById(R.id.btn_confirmshouhuo); //付款
                holder.btn_querywuliu = (Button) layout.findViewById(R.id.btn_querywuliu);
                holder.tv_order_time = (TextView) layout.findViewById(R.id.tv_order_time);
                layout.setTag(holder);
            } else {
                layout = convertView;
                holder = (ViewHolder) layout.getTag();
            }
            //  TODO:   没有返回详情id 无法查询物流 （隐藏查询按钮）
            holder.btn_querywuliu.setVisibility(View.INVISIBLE);
            holder.btn_confirmshouhuo.setText("付款");

            final Integer cmstate = allorderlist.get(position).getCMSTATE();
            if (cmstate == 0) {
                holder.tv_state.setText("待支付");
//				holder.btn_confirmshouhuo.setText("付款");


            } else if (cmstate == 1) {
                holder.tv_state.setText("已取消");
                holder.btn_confirmshouhuo.setText("删除订单");

            } else if (cmstate == 2) {
                holder.tv_state.setText("待发货");

                holder.btn_confirmshouhuo.setText("确认收货");

//				holder.btn_confirmshouhuo.setVisibility(View.VISIBLE);

            } else if (cmstate == 3) {
                holder.tv_state.setText("已发货");
//				holder.btn_confirmshouhuo.setVisibility(View.VISIBLE);
                holder.btn_confirmshouhuo.setText("确认收货");


            } else if (cmstate == 4) {
                holder.tv_state.setText("已完成");
                holder.btn_confirmshouhuo.setText("确认收货");

            }

            List<TBORDERDETAIL> tborderdetails = allorderlist.get(position).getTBORDERDETAILS();
            detailslist.clear();
            detailslist.addAll(tborderdetails);
            TBORDERDETAIL tborderdetail = detailslist.get(0);
            String cmtitle = tborderdetail.getCMTITLE();


            String order_time = allorderlist.get(position).getCMCREATETIME();

            holder.tv_order_time.setText(order_time);
            Integer cmpresentprice = tborderdetail.getCMPRESENTPRICE();
            Integer cmmoney = tborderdetail.getCMMONEY();
            Integer cmnumber = tborderdetail.getCMNUMBER();
            String cmmainfigurepath = tborderdetail.getCMMAINFIGUREPATH();
            holder.tv_shangpin_name.setText(cmtitle);
            holder.tv_shoujia.setText("¥" + cmpresentprice);
            holder.tv_xiaoliang.setText("X" + cmnumber);
            holder.tv_price.setText("合计 :¥" + cmmoney);
            holder.tv_total_sales.setText("共" + cmnumber + "件商品");
            if (cmmainfigurepath != null) {
                ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + cmmainfigurepath, holder.img_shangpin_name, options);
            }

//			// 查看物流
//			holder.btn_querywuliu.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//
//
//					Intent intent=new Intent(AllOrderActivity.this ,LogisticsActivity.class);
//
//					startActivity(intent );
//
//
//				}
//			});


            // 确认收货
            holder.btn_confirmshouhuo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (cmstate == 0) {

                        for (int i = 0; i < allorderlist.size(); i++) {
                            double cmmoneysun = allorderlist.get(position).getCMMONEYSUN();
                            String cmorderid = allorderlist.get(position).getCMORDERID();
                            TCHConstants.url.ordermoney = cmmoneysun + "";
                            TCHConstants.url.orderid = cmorderid;
                            Log.e("cmorderid==", cmorderid);Log.e("ordermoney==", cmmoneysun + " ");


                        }

                        Intent intent = new Intent(AllOrderActivity.this, PayActivity.class);
                        startActivity(intent);


                    } else if (cmstate == 1)

                    {

                        // 删除订单 并在此查询刷新页面
                        cancelorder();

                    } else if (cmstate == 2) {//待发货

                        //确认收货
                        confimorder();
                    } else if (cmstate == 3 || cmstate == 4) {
                        // 确认收货
                        confimorder();
                    }


                }
            });

            return layout;
        }


        @Override
        public int getCount() {
            return allorderlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    class ViewHolder {
        ImageView img_shangpin_name;
        TextView tv_shangpin_name;
        TextView tv_shoujia;
        TextView tv_xiaoliang;
        TextView tv_total_sales;
        TextView tv_price;
        TextView tv_state;
        Button btn_confirmshouhuo;
        Button btn_querywuliu;
        TextView tv_order_time;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_allorder_back://返回
                finish();
                break;
            default:
                break;
        }
    }

//todo 取消（删除）

    private void cancelorder() {
        HashMap<String, String> parms = new HashMap<>();
        for (int i = 0; i < allorderlist.size(); i++) {

            String orderid = allorderlist.get(i).getCMORDERID();
            parms.put("orderid", orderid);
            parms.put("token", TCHConstants.url.token);
        }

//
        OkHttpUtils.get().params(parms).url(TCHConstants.url.DelMyOrder).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("Exception e ==", "" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("取消订单json == ", "" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String token = jsonObject.getString("Token");
                    TCHConstants.url.token = token;
                    int errorCode = jsonObject.getInt("ErrorCode");
                    if (errorCode == 0) {
                        Toast.makeText(AllOrderActivity.this, "已删除订单", Toast.LENGTH_SHORT).show();
                        initData();//删除后重新查询
                    } else {
                        Log.e("取消订单errorCode==", "" + errorCode);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void confimorder() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("orderid", TCHConstants.url.orderid);
        parms.put("token", TCHConstants.url.token);

        Log.e("orderid==",TCHConstants.url.orderid);
        Log.e("token==",TCHConstants.url.token);



        OkHttpUtils.get().params(parms).url(TCHConstants.url.ConfirmOrder).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                Log.e("确认收货ErrorCode ==", "" + e);

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("确认收货response ==", "" + response);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int errorCode = jsonObject.getInt("ErrorCode");
                    if (errorCode == 0) {
                        String token = jsonObject.getString("Token");
                        TCHConstants.url.token = token;
                        //todo 测试
                        Log.e("confimordeToken==", token);

                        //确认完成后  刷新
                        initData();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
