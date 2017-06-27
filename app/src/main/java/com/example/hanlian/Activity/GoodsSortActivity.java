package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.hanlian.Adapter.CustomAdapter;
import com.example.hanlian.Adapter.GoodsInfo;
import com.example.hanlian.Adapter.MyAdapter;
import com.example.hanlian.Adapter.ViewHolder;
import com.example.hanlian.DateModel.Classify;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import utils.JsonHelper;
import utils.LogUtil;
import utils.TCHConstants;

/**
 * 产品分类
 */
public class GoodsSortActivity extends Activity implements OnClickListener {

    private ListView sort_listView1;
    private ListView sort_listView3;
    private RecyclerView sort_recyclerview;
    private List<GoodsInfo> sortData1 = new ArrayList<GoodsInfo>();// 大的类别数据

    private List<GoodsInfo> sortData2 = new ArrayList<GoodsInfo>();// 小的类别数据

    private List<GoodsInfo> sortData3 = new ArrayList<GoodsInfo>();// 海报的类别数据

//  private String[] mGridViewImgRes = new String[]{};

    //	private String[] data = new String[] { "上衣", "裤子" , "裙子", "亲子装", "鞋子","套装", "饰品", "家居服" };
    private String sort;
    private JSONArray goodsList = new JSONArray();// 大的类别数据
    private MyAdapter2 myAdapter2;
    private MyAdapter recyclerviewAdapter;
    private int firstId;// 第一次进来的商品id

    private ArrayList<Classify.ResultBean.TBSUBCLASSBean> playbillData = new ArrayList();//
    private ArrayList<Classify.ResultBean.TBSUBCLASSBean.TBGOODSBean> Goodlist = new ArrayList();//海报
    private ArrayList<Classify.ResultBean> Result = new ArrayList();

    private PalybillAdapter palybillAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_sort);
//		PushAgent.getInstance(this).onAppStart();

        Intent intent = getIntent();
        sort = intent.getStringExtra("sort");
        int position = intent.getIntExtra("position", 0);

        initView();
        initData();

        initClassifyData(position);

    }

    private void initClassifyData(int position) {


        OkHttpUtils.get().url(TCHConstants.url.Classify).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e(" ==", "" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Classify classifyjson = JsonHelper.fromJson(response, Classify.class);


                List<Classify.ResultBean> result = classifyjson.getResult();
                // TODO 点击切换时如何动态的获取下标改变海报
                LogUtil.e("海报size==", result.size() + "");

                List<Classify.ResultBean.TBSUBCLASSBean> tb_subclass = classifyjson.getResult().get(position).getTB_SUBCLASS();
                playbillData.clear();
                playbillData.addAll(tb_subclass);
                LogUtil.e("海报==", tb_subclass.size() + "");

                Goodlist.clear();
                for (int i = 0; i < playbillData.size(); i++) {
                    List<Classify.ResultBean.TBSUBCLASSBean.TBGOODSBean> tb_goods = playbillData.get(i).getTB_GOODS();
                    Goodlist.addAll(tb_goods);
                }

                palybillAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initView() {

        findViewById(R.id.sort_back).setOnClickListener(this);
        findViewById(R.id.sort_et_search).setOnClickListener(this);

        sort_listView1 = (ListView) findViewById(R.id.sort_listView);
        myAdapter2 = new MyAdapter2();
        sort_listView1.setAdapter(myAdapter2);
        sort_listView1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                initData2(sortData1.get(position).id);//点击listview切换分类

                myAdapter2.setSelectItem(position); // 设置选中项目

                myAdapter2.notifyDataSetChanged();//listview

                recyclerviewAdapter.notifyDataSetChanged(); //RecyclerView
                // TODO: 2017/6/14  点击切换  海报数据
//                palybillAdapter.notifyDataSetChanged();
                initClassifyData(position);
            }
        });
        //展示页listview数据
        sort_listView3 = (ListView) findViewById(R.id.sort_listView2);

        palybillAdapter = new PalybillAdapter();
        sort_listView3.setAdapter(palybillAdapter);

//        sort_listView3.setAdapter(new CustomAdapter<GoodsInfo>(GoodsSortActivity.this, sortData3,
//                R.layout.item_sort2) {
//            @Override
//            public void convert(ViewHolder holder, GoodsInfo t) {
//
//                ImageView item_sort_image = holder.getView(R.id.item_sort_image);
//                ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + t.image, item_sort_image, MyApplication.options);
////				item_sort_image.setImageDrawable(getResources().getDrawable(R.drawable.new_banner_02));
//                holder.getView(R.id.item_sort_name).setVisibility(View.GONE);
//            }
//        });

        sort_recyclerview = (RecyclerView) findViewById(R.id.sort_recyclerview);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//水平流

        sort_recyclerview.setLayoutManager(layoutManager);
        recyclerviewAdapter = new MyAdapter(sortData2, this);
        sort_recyclerview.setAdapter(recyclerviewAdapter);
    }

    // 左侧 listview
    public class MyAdapter2 extends BaseAdapter {

        public int getCount() {
            return sortData1.size();
//           a ? b :c   a==ture 取值 b    a==false 取值 c
//          return   sortData1.size()==0 ? sortData1.size() : 1 ;

        }

        public Object getItem(int arg0) {
            return 0;
        }

        public long getItemId(int arg0) {
            return arg0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // position listview 对应的条数   converView  返回视图    parent  加载对应的xml
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = getLayoutInflater().inflate(R.layout.item_sort0, null);
                holder.name = (TextView) convertView.findViewById(R.id.item_sort_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.name.setText(sortData1.get(position).name);
            if (position == selectItem) {  // 设置点击选中item背景
                convertView.setBackgroundColor(getResources().getColor(R.color.gray));
            } else {
                convertView.setBackgroundColor(getResources().getColor(R.color.white));
            }
            return convertView;
        }

        public void setSelectItem(int selectItem) {
            this.selectItem = selectItem;
        }

        private int selectItem = -1;
    }

    //获取主分类数据
    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "1");
        params.put("classifyid", "0");
        HTTPUtils.get(getApplicationContext(), TCHConstants.url.goodsSort, params, new ResponseListener() {

            private int firstId;

            @Override
            public void onResponse(String arg0) {
                try {
                    goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
                    if (goodsList.length() > 0) {
                        for (int i = 0; i < goodsList.length(); i++) {

                            JSONObject goodsObject = goodsList.getJSONObject(i);
                            String name = goodsObject.getString("CM_CLASSIFYNAME");
                            int goodsId = goodsObject.getInt("CM_CLASSIFYID");
                            sortData1.add(new GoodsInfo("", name, goodsId));

                            if (name.equals(sort)) {// 设置初始进入时选中listview
                                firstId = goodsId;
                                myAdapter2.setSelectItem(i);//设置选中项

                            }
                        }

                        myAdapter2.notifyDataSetChanged();

                        initData2(firstId);

                    } else { // 搜索为空
                        Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {

            }
        });
    }

    //二级分类数据
    private void initData2(int id) {
        sortData2.clear();
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", 2 + "");
        params.put("classifyid", id + "");
        //获取分类详情数据
        HTTPUtils.get(getApplicationContext(), TCHConstants.url.goodsSort, params, new ResponseListener() {

            @Override
            public void onResponse(String arg0) {
                try {
                    goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
                    if (goodsList.length() > 0) {

//                        LogUtil.e("二级分类数据==", goodsList.toString());
                        for (int i = 0; i < goodsList.length(); i++) {
                            JSONObject goodsObject = goodsList.getJSONObject(i);

                            String name = goodsObject.getString("CM_CLASSIFYNAME");
                            String imageUrl = goodsObject.getString("CM_IMGPATH");
                            int goodsId = goodsObject.getInt("CM_CLASSIFYID");
                            sortData2.add(new GoodsInfo(imageUrl, name, goodsId));
                            sortData3.add(new GoodsInfo(imageUrl, name, goodsId));

                            /**
                             * 海报图片路径
                             */

//                            JSONArray tb_subclass = goodsObject.getJSONArray("TB_SUBCLASS");
//                            if (tb_subclass.length() >0)
//                            {
//                                JSONObject jsonObject = tb_subclass.getJSONObject(i);
//                                JSONArray tb_goods = jsonObject.getJSONArray("TB_GOODS");
//                                String cm_mainfigurepath = tb_goods.getJSONObject(i).getString("CM_MAINFIGUREPATH");
//                                String cm_goodsid = tb_goods.getJSONObject(i).getString("CM_GOODSID");
//                                int goodsid = Integer.parseInt(cm_goodsid);
//
//                                sortData3.add(new GoodsInfo(cm_mainfigurepath, "", goodsid));
//                            }


                        }
                        recyclerviewAdapter.notifyDataSetChanged();


                    } else { // 搜索为空
                        Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {


            }
        });
    }

    private void gosearch() {
        Intent intent = new Intent(GoodsSortActivity.this, SearchActivity.class);
        startActivity(intent);
        // 开启无动画
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort_back:
                finish();
                break;
            case R.id.sort_et_search:

                break;
            default:
                break;
        }
    }

    private class Holder {
        TextView name;
        ImageView img_paybill_item;
    }


    class PalybillAdapter extends BaseAdapter {


        private String cm_mainfigurepath;

        @Override
        public int getCount() {

            return Goodlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = getLayoutInflater().inflate(R.layout.palybillitem, null);
                holder.img_paybill_item = (ImageView) convertView.findViewById(R.id.img_paybill_item);
                convertView.setTag(holder);

            } else {
                holder = (Holder) convertView.getTag();

            }
            if (Goodlist.size() != 0) {
                cm_mainfigurepath = Goodlist.get(position).getCM_MAINFIGUREPATH();

            }
            if (cm_mainfigurepath.length() != 0 || cm_mainfigurepath != null) {

                Glide.with(GoodsSortActivity.this).load(TCHConstants.url.imgurl + cm_mainfigurepath).into(holder.img_paybill_item);

            } else {

            }

            holder.img_paybill_item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cm_goodsid = Goodlist.get(position).getCM_GOODSID();
                    OkHttpUtils.get().addParams("goodsid", cm_goodsid).addParams("token", TCHConstants.url.token).url(TCHConstants.url.detailsurl).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {

                            Intent intent = new Intent(GoodsSortActivity.this, GoodsDetailActivity.class);
                            intent.putExtra("goodsid", cm_goodsid);
                            startActivity(intent);
                        }
                    });
                }
            });

            return convertView;
        }
    }
}
