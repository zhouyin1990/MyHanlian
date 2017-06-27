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
import com.example.hanlian.DateModel.KeShouHouBean;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import shouhoushenqingModel.Result;
import shouhoushenqingModel.TBORDERDETAIL;
import utils.JsonHelper;
import utils.LogUtil;
import utils.TCHConstants;

public class TuihuanhuoActivity extends Activity implements OnClickListener {

    private ListView mlistview_jindu;
    private ListView mlistview_shouhou;
    private int type=0;
    private int pageNum = 1;
    private int pageSize = 0;
    private DisplayImageOptions options;
    private Button sh, jd;

    private ArrayList<KeShouHouBean.ResultBean> shouhoulist = new ArrayList<KeShouHouBean.ResultBean>();
    private ArrayList<KeShouHouBean.ResultBean.TBORDERDETAILSBean> tb_list = new ArrayList<KeShouHouBean.ResultBean.TBORDERDETAILSBean>();
    private ImageView imgtuhuanhuo_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuihuanhuo);
//        PushAgent.getInstance(this).onAppStart();

        initView();
        intiUIL();
        intidata();

    }

    private void intidata() {

        HashMap<String, String> paramsshouhou = new HashMap<String, String>();
        paramsshouhou.put("pageNum", pageNum + "");
        paramsshouhou.put("pageSize", pageSize + "");
        paramsshouhou.put("token", TCHConstants.url.token);

        HTTPUtils.get(TuihuanhuoActivity.this, TCHConstants.url.MYshouhou, paramsshouhou, new ResponseListener() {


            @Override
            public void onResponse(String arg0) {
                // TODO Auto-generated method stub

                LogUtil.e("可售后商品json ==",""+arg0);

                KeShouHouBean keShouHouBean = JsonHelper.fromJson(arg0, KeShouHouBean.class);
                int errorCode = keShouHouBean.getErrorCode();
                String token = keShouHouBean.getToken();
                TCHConstants.url.token = token;
                Log.e("shouhoutoken==", ""+token);

                Log.e("errorCode", ""+errorCode);

                if (errorCode == 0) {
                    List<KeShouHouBean.ResultBean> result = keShouHouBean.getResult();
                    if (result != null && result.size() != 0) {
                        shouhoulist.clear();
                        shouhoulist.addAll(result);
                        ShouhouAdapter shouhouAdapter = new ShouhouAdapter();
                        mlistview_shouhou.setAdapter(shouhouAdapter);
                        shouhouAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(TuihuanhuoActivity.this,
                            "MYshouhouErrorCode = " + errorCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }


    private void intiUIL() {
        // TODO Auto-generated method stub
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

    private void initView() {
        sh = (Button) findViewById(R.id.button1);
        jd = (Button) findViewById(R.id.button2);
        jd.setOnClickListener(this);
        sh.setOnClickListener(this);
        TextView mTextView = (TextView) findViewById(R.id.tv_title);

        if(type==0 || type==1)
        {
           mTextView.setText("售后申请");

        }else if (type==2)
        {
            mTextView.setText("进度查询");
        }

        mlistview_jindu = (ListView) findViewById(R.id.listview_jindu);
        mlistview_shouhou = (ListView) findViewById(R.id.listview_shouhou);
        imgtuhuanhuo_back = (ImageView) findViewById(R.id.imgtuhuanhuo_back);
        imgtuhuanhuo_back.setOnClickListener(this);
    }


    class ShouhouAdapter extends BaseAdapter {

        View layout = null;
        ViewHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            if (convertView == null) {
                holder = new ViewHolder();
                layout = getLayoutInflater().inflate(R.layout.shouhoushengqing_item, null);
                holder.img_recouce = (ImageView) layout.findViewById(R.id.img_recouce);
                holder.tv_shouhounumber = (TextView) layout.findViewById(R.id.tv_shouhounumber);
                holder.tv_shouhoustate = (TextView) layout.findViewById(R.id.tv_shouhoustate);
                holder.tv_title = (TextView) layout.findViewById(R.id.tv_title);
                holder.tv_shouhoutime = (TextView) layout.findViewById(R.id.tv_shouhoutime);
                holder.tv_number_shouhou = (TextView) layout.findViewById(R.id.tv_number_shouhou);
                holder.btn_shouhoushengqing = (Button) layout.findViewById(R.id.btn_shouhoushengqing);

                layout.setTag(holder);
            } else {
                layout = convertView;
                holder = (ViewHolder) layout.getTag();
            }
//          String cmcreatetime = shouhoulist.get(position).getCMCREATETIME();

            String cm_createtime = shouhoulist.get(position).getCM_CREATETIME();


            holder.tv_shouhoutime.setText(cm_createtime);

//          String cmorderid = shouhoulist.get(position).getCMORDERID();
//          holder.tv_dingdanbianhao.setText("订单编号：" + cmorderid);


            List<KeShouHouBean.ResultBean.TBORDERDETAILSBean> tb_orderdetails = shouhoulist.get(position).getTB_ORDERDETAILS();

            tb_list.clear();
            tb_list.addAll(tb_orderdetails);

            Log.e("tb_orderdetails==", ""+tb_orderdetails.size());
            Log.e("tb_list", ""+tb_list.size());

            Integer cmnumber = tb_list.get(0).getCM_NUMBER();
            holder.tv_number_shouhou.setText("销量x" + cmnumber);
            String cmtitle = tb_list.get(0).getCM_TITLE();
            holder.tv_title.setText(cmtitle);
            // 服务状态
//           Integer cmservicestate = tb_list.get(0).getCMSERVICESTATE();
            Integer cmservicestate = tb_list.get(0).getCM_SERVICE_STATE();


            //订单状态
            Integer cmorderstate = shouhoulist.get(position).getCM_ORDER_STATE();

            if (cmorderstate == 0) {
                holder.tv_shouhoustate.setText("正常");
            } else if (cmorderstate == 1) {
                holder.tv_shouhoustate.setText("换货");
            } else if (cmorderstate == 2) {
                holder.tv_shouhoustate.setText("退货");
            } else if (cmorderstate == 3) {
                holder.tv_shouhoustate.setText("返修");
            } else if (cmorderstate == 4) {
                holder.tv_shouhoustate.setText("完成");
            }
            String cmmainfigurepath = tb_list.get(0).getCM_MAINFIGUREPATH();
            if (cmmainfigurepath != null) {
                ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + cmmainfigurepath, holder.img_recouce, options);
            }

            holder.btn_shouhoushengqing.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //申请退货
                    customer(position);
                }
            });

            return layout;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return shouhoulist.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub


            return 0;
        }

    }

    class ViewHolder {
        ImageView img_recouce;
        TextView tv_shouhounumber; //编号
        TextView tv_shouhoustate;
        TextView tv_title;
        TextView tv_shouhoutime;
        TextView tv_number_shouhou;// 数量
        Button btn_shouhoushengqing;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.imgtuhuanhuo_back:
                finish();
                break;
            case R.id.button1:

                type = 1 ;

                sh.setTextColor(getResources().getColor(R.color.white));
                sh.setBackgroundColor(getResources().getColor(R.color.red));
                jd.setTextColor(getResources().getColor(R.color.red));
                jd.setBackgroundColor(getResources().getColor(R.color.white));
                mlistview_shouhou.setVisibility(View.VISIBLE);
                mlistview_jindu.setVisibility(View.INVISIBLE);
                break;
            case R.id.button2:
                type =2 ;
                sh.setTextColor(getResources().getColor(R.color.red));
                sh.setBackgroundColor(getResources().getColor(R.color.white));
                jd.setTextColor(getResources().getColor(R.color.white));
                jd.setBackgroundColor(getResources().getColor(R.color.red));
                mlistview_shouhou.setVisibility(View.INVISIBLE);
                mlistview_jindu.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }


    }

    //暂时没界面 申请 退货

    private void customer(int postion) {
        for (int i = 0; i < tb_list.size(); i++) {
            String cm_orderdetailsid = tb_list.get(postion).getCM_ORDERDETAILSID();
            TCHConstants.url.Orderdetailsids = cm_orderdetailsid;
            Log.e("cm_orderdetailsid==", TCHConstants.url.Orderdetailsids);

        }

        HashMap<String, String> parms = new HashMap<>();
        parms.put("orderdetailsid",TCHConstants.url.Orderdetailsids);
        parms.put("type", ""+1);
        parms.put("token", TCHConstants.url.token);
        parms.put("reason", "退货");



        OkHttpUtils.post().params(parms).url(TCHConstants.url.ApplyAfterService).build().execute(new StringCallback() {
              @Override
              public void onError(Call call, Exception e, int id) {

              }

              @Override
              public void onResponse(String response, int id) {
                 Log.e("response==", response);
                  try {
                      JSONObject jsonObject = new JSONObject(response);
                      String token = jsonObject.getString("Token");
                      //返回toekn
                      int errorCode = jsonObject.getInt("ErrorCode");
                      if (errorCode==0) {
                          TCHConstants.url.token=token ;
                          Toast.makeText(TuihuanhuoActivity.this ,"申请售后成功",Toast.LENGTH_SHORT).show();
//                          intidata();
                      } else if (errorCode==26){

                          Toast.makeText(TuihuanhuoActivity.this ,"已申请售后",Toast.LENGTH_SHORT).show();
                      }

                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
          });


    }

    //todo 返回token 赋值
    private void cancelorder() {
        HashMap<String, String> parms = new HashMap<>();
        for (int i = 0; i < shouhoulist.size(); i++) {

            String orderid = shouhoulist.get(i).getCM_ORDERID();
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
                        Toast.makeText(TuihuanhuoActivity.this, "已删除订单", Toast.LENGTH_SHORT).show();
                        intidata();//删除后重新查询
                    } else {
                        Log.e("取消订单errorCode==", "" + errorCode);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
