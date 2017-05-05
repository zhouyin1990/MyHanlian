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
import com.example.hanlian.Adapter.CustomAdapter;
import com.example.hanlian.Adapter.GoodsInfo;
import com.example.hanlian.Adapter.MyAdapter;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.message.PushAgent;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.TCHConstants;

/**
 * 产品分类
 */
public class GoodsSortActivity extends Activity implements OnClickListener {

	private ListView sort_listView1;
	private ListView sort_listView3;
	private RecyclerView sort_recyclerview;
	private List<GoodsInfo> sortData1 = new ArrayList<GoodsInfo>();;// 大的类别数据
	private List<GoodsInfo> sortData2 = new ArrayList<GoodsInfo>();;// 小的类别数据
	private List<GoodsInfo> sortData3 = new ArrayList<GoodsInfo>();;// 海报的类别数据
//	private String[] data = new String[] { "上衣", "裤子" , "裙子", "亲子装", "鞋子","套装", "饰品", "家居服" };
	private String sort;
	private JSONArray goodsList = new JSONArray();// 大的类别数据
	private MyAdapter2 myAdapter2;
	private MyAdapter myAdapter;
	private int firstId;// 第一次进来的商品id
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_sort);
		PushAgent.getInstance(this).onAppStart();
		initView();
		initData();
		
	}

	private void initView() {
		Intent intent = getIntent();
		sort = intent.getStringExtra("sort");
		Log.e("sort", sort);
		findViewById(R.id.sort_back).setOnClickListener(this);
		findViewById(R.id.sort_et_search).setOnClickListener(this);
		sort_listView1 = (ListView) findViewById(R.id.sort_listView);
		myAdapter2 = new MyAdapter2();
		sort_listView1.setAdapter(myAdapter2);
		sort_listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				initData2(sortData1.get(position).id);//点击listview切换分类
				myAdapter2.setSelectItem(position);
				myAdapter2.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				
			}
		});
		
		sort_listView3 = (ListView) findViewById(R.id.sort_listView2);
//		sort_listView3.setAdapter(new CustomAdapter<GoodsInfo>(GoodsSortActivity.this, sortData3,
//				R.layout.item_sort2) {// 自定义adapter
//
//			public void convert(RecyclerView.ViewHolder holder, GoodsInfo t) {
//				ImageView item_sort_image = holder.getView(R.id.item_sort_image);
//				ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + t.image,item_sort_image, MyApplication.options);
//				item_sort_image.setImageDrawable(getResources().getDrawable(R.drawable.new_banner_02));
//				holder.getView(R.id.item_sort_name).setVisibility(View.GONE);
//			}
//		});




		sort_listView3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				sort_listView3.setItemChecked(position, true);
				Intent intent=new Intent(getApplicationContext(), GoodsDetailActivity.class);
				intent.putExtra("goodsid", sortData3.get(position).id) ;
				startActivity(intent);
			}
		});
		sort_recyclerview = (RecyclerView) findViewById(R.id.sort_recyclerview);
		StaggeredGridLayoutManager layoutManager = 
				new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//水平流

		sort_recyclerview.setLayoutManager(layoutManager);
		myAdapter = new MyAdapter(sortData2, this);
		sort_recyclerview.setAdapter(myAdapter);
	}
	
	public class MyAdapter2 extends BaseAdapter {  
		
        public int getCount() {
            return sortData1.size();  
        }  
        public Object getItem(int arg0) {  
            return 0;  
        }  
        public long getItemId(int arg0) {  
            return arg0;  
        }  
        public View getView(int position, View convertView, ViewGroup parent) {  
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
			}  else {  
				convertView.setBackgroundColor(getResources().getColor(R.color.white));  
			}
            return convertView;  
        }  
        public  void setSelectItem(int selectItem) {  
             this.selectItem = selectItem;  
        }  
        private int  selectItem=-1;  
    }
	//获取主分类数据
	private void initData() {
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", "1");
		params.put("classifyid", "0");
		HTTPUtils.get(getApplicationContext(), TCHConstants.url.goodsSort,params,new ResponseListener() {         

			private int firstId;

			@Override
			public void onResponse(String arg0) {
		//		Log.e("TestLog", arg0.toString());
				try {
					goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
					if (goodsList.length() > 0) {
						
						for (int i = 0; i < goodsList.length(); i++) {
							
							JSONObject goodsObject = goodsList.getJSONObject(i);
							String name = goodsObject.getString("CM_CLASSIFYNAME");
							int goodsId = goodsObject.getInt("CM_CLASSIFYID");
							sortData1.add(new GoodsInfo("", name, goodsId));
							if(name.equals(sort)){// 设置初始进入时选中listview
								firstId = goodsId;
								myAdapter2.setSelectItem(i);
							}
						}
						
						myAdapter2.notifyDataSetChanged();						
						initData2(firstId);						
					}else{ // 搜索为空
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
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", 2 + "");
		params.put("classifyid", id + "");
		//获取分类详情数据
		HTTPUtils.get(getApplicationContext(), TCHConstants.url.goodsSort,params,new ResponseListener() {         

			@Override
			public void onResponse(String arg0) {
				try {
					goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
					if (goodsList.length() > 0) {
						
						for (int i = 0; i < goodsList.length(); i++) {
							JSONObject goodsObject = goodsList.getJSONObject(i);
							String name = goodsObject.getString("CM_CLASSIFYNAME");
							String imageUrl = goodsObject.getString("CM_IMGPATH");
							int goodsId = goodsObject.getInt("CM_CLASSIFYID");
							sortData2.add(new GoodsInfo(imageUrl, name, goodsId));
							sortData3.add(new GoodsInfo(imageUrl, name, goodsId));
						}					
						myAdapter.notifyDataSetChanged();
					}else{ // 搜索为空
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
			gosearch();
			break;
		default:
			break;
		}
	}
	private class Holder{
		TextView name;
		ImageView image;
	}



     class  MyAdapter3 extends BaseAdapter
	 {


		 @Override
		 public int getCount() {
			 return 0;
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





			 return null;
		 }
	 }




}
