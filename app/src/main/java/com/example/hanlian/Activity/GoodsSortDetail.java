package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.message.PushAgent;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import utils.TCHConstants;

/**
 * 产品分类详情
 */
public class GoodsSortDetail extends Activity implements OnClickListener {

	private PullToRefreshGridView sort_detail_gridview;
	private JSONArray goodsList = new JSONArray();
	private ImageAdapter imageAdapter;
	private LinearLayout sort_detail_linear1;
	private LinearLayout sort_detail_linear2;
	private LinearLayout sort_detail_linear3;
	private TextView sort_detail_buy;
	private TextView sort_detail_all;
	private TextView sort_detail_new;
	private int size = 4;//出现显示两条数据
	private int lastDataNum ;//上次数据长度

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_sort_detail);
		PushAgent.getInstance(this).onAppStart();
		initView();
		initData(77, size);
		
	}	
	private void initView() {
		findViewById(R.id.sort_detail_back).setOnClickListener(this);
		findViewById(R.id.sort_detail_search).setOnClickListener(this);
		
		sort_detail_linear1 = (LinearLayout) findViewById(R.id.sort_detail_linear1);
		sort_detail_linear1.setOnClickListener(this);
		sort_detail_linear2 = (LinearLayout) findViewById(R.id.sort_detail_linear2);
		sort_detail_linear2.setOnClickListener(this);
		sort_detail_linear3 = (LinearLayout) findViewById(R.id.sort_detail_linear3);
		sort_detail_linear3.setOnClickListener(this);
		sort_detail_all = (TextView) findViewById(R.id.sort_detail_all);
		sort_detail_buy = (TextView) findViewById(R.id.sort_detail_buy);
		sort_detail_new = (TextView) findViewById(R.id.sort_detail_new);
		sort_detail_gridview = (PullToRefreshGridView) findViewById(R.id.sort_detail_gridview);
		imageAdapter = new ImageAdapter();
		sort_detail_gridview.setAdapter(imageAdapter);
		//跳转至商品详情页
		sort_detail_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//GoodsDetailActivity是共用的，接受参数来显示对应的item内容   CM_GOODSID
			    try {
			    	Intent intent=new Intent(getApplicationContext(), GoodsDetailActivity.class);
			    	String goodsid = goodsList.getJSONObject(position).getString("CM_GOODSID");
			    	Toast.makeText(getApplicationContext(),goodsid+ "goodsid", Toast.LENGTH_SHORT).show();
					intent.putExtra("goodsid", goodsid);
					Log.e("goodsid***", goodsid);
					startActivity(intent);
				} catch (JSONException e) {
					e.printStackTrace();
				}      
			}
		});
		sort_detail_gridview.setOnRefreshListener(new OnRefreshListener2<GridView>() {//上拉，下拉刷新

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				// 下拉加载更多
				size++;
				initData(77, size);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				// 上拉加载更多
				size++;
				initData(77, size);
				
			}
		});
		
	}


	private void initData(int id, int pageSize) {
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", 1 + "");
		params.put("classifyid", id + "");
		params.put("pageNum", 4 + "");
		params.put("pageSize", pageSize + "");
      	//获取分类详情数据
		HTTPUtils.get(getApplicationContext(), TCHConstants.url.goodsSortDetail,params,new ResponseListener() {         
			@Override
			public void onResponse(String arg0) {
				sort_detail_gridview.onRefreshComplete();								
				try {
					goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
					if(lastDataNum >= goodsList.length()){
				//		Toast.makeText(getApplicationContext(), "没有更多数据！", 1000).show();
					}
					lastDataNum = goodsList.length();
					
					
					if (goodsList.length() > 0) {
						imageAdapter.notifyDataSetChanged();
						((LinearLayout) findViewById(R.id.tab))
								.setVisibility(View.VISIBLE);
						((LinearLayout) findViewById(R.id.nocontent))
								.setVisibility(View.GONE);
					}else{ // 搜索为空
						((LinearLayout) findViewById(R.id.tab))
								.setVisibility(View.GONE);
						((LinearLayout) findViewById(R.id.nocontent))
								.setVisibility(View.VISIBLE);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
    
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {
				((LinearLayout) findViewById(R.id.tab)).setVisibility(View.GONE);
				((LinearLayout) findViewById(R.id.nocontent)).setVisibility(View.VISIBLE);
			}
		});
	}


	public class ImageAdapter extends BaseAdapter {
		public JSONObject getInfo(int position) {
			JSONObject result = null;
			try {
				result = goodsList.getJSONObject(position);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		public int getCount() {
			return goodsList.length();
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg1) {
			return arg1;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_sort_detail, null);
				holder = new ViewHolder();
				holder.title = (TextView) convertView
						.findViewById(R.id.sort_detail_title);
				holder.price = (TextView) convertView
						.findViewById(R.id.sort_detail_price);
				holder.image = (ImageView) convertView
						.findViewById(R.id.sort_detail_image);
				holder.star = (ImageView) convertView
						.findViewById(R.id.sort_detail_star);
				convertView.setTag(holder);
			} else {            
				holder = (ViewHolder) convertView.getTag();
			}
			try {
				holder.title.setText(goodsList.getJSONObject(position)
						.getString("CM_TITLE"));
				holder.price.setText(goodsList.getJSONObject(position).getString("CM_PRESENTPRICE"));
				ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + goodsList.getJSONObject(position)
				.getString("CM_MAINFIGUREPATH"), holder.image, MyApplication.options);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return convertView;
		}
	}

	static class ViewHolder {
		ImageView image, star;
		TextView title, price;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sort_detail_back:
			finish();
			break;
		case R.id.sort_detail_linear1://综合分类
			initData(77, 4);
			sort_detail_all.setTextColor(getResources().getColor(R.color.red));
			sort_detail_buy.setTextColor(getResources().getColor(R.color.black));
			sort_detail_new.setTextColor(getResources().getColor(R.color.black));
			break;
		case R.id.sort_detail_linear2://销量分类
			initData(29, 4);
			sort_detail_all.setTextColor(getResources().getColor(R.color.black));
			sort_detail_buy.setTextColor(getResources().getColor(R.color.red));
			sort_detail_new.setTextColor(getResources().getColor(R.color.black));
			break;
		case R.id.sort_detail_linear3://新品分类
			initData(30, 4);
			sort_detail_all.setTextColor(getResources().getColor(R.color.black));
			sort_detail_buy.setTextColor(getResources().getColor(R.color.black));
			sort_detail_new.setTextColor(getResources().getColor(R.color.red));
			break;
		case R.id.sort_detail_search://搜索
			gosearch();
			break;
		default:
			break;
		}
		
	}
	
	private void gosearch() {
		Intent intent = new Intent(GoodsSortDetail.this, SearchActivity.class);
		startActivity(intent);
		// 开启无动画
		overridePendingTransition(0, 0);
	}

}
