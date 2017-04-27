package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.VolleyError;
import com.example.hanlian.Activity.GoodsDetailActivity;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import NewdataModel.New;
import NewdataModel.Result;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import utils.TCHConstants;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class NewFragment extends Fragment {

	private View layout;
	private LayoutInflater inflater;
	private PullToRefreshGridView newgridview;
	private DisplayImageOptions  options;
	private newgridviewAdapter newgvadapter;
    private JSONArray goodsList = new JSONArray();
    private ArrayList<Result> newlist = new ArrayList<Result>();
	

	public NewFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		if(layout==null)
		{
			layout = inflater.inflate(R.layout.fragment_new, container, false);
			intiUI();
			intiUIL();
			intiData();
		}else
		{
		 ViewGroup parent = (ViewGroup) layout.getParent();
	        if (parent!=null){
	        	parent.removeAllViewsInLayout();
	        }
		}
		
		return layout;
	}
	
	
	
	private void intiUI() 
	{		
		newgridview = (PullToRefreshGridView) layout.findViewById(R.id.new_gridview);
		newgvadapter = new newgridviewAdapter();	
		newgridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent=new Intent(getActivity(), GoodsDetailActivity.class);
//				String goodsid = homegoodlist.get(position).getCMGOODSID();								
//			    intent.putExtra("goodsid", goodsid) ;
				
				String goodsid = newlist.get(position).getCMGOODSID();							
			    intent.putExtra("goodsid", goodsid) ;
			    startActivity(intent);
			}
		});
		//下拉刷新
		newgridview.setOnRefreshListener(new OnRefreshListener<GridView>() {

				@Override
				public void onRefresh(PullToRefreshBase<GridView> refreshView) {
					//重新加载数据
					intiData();
					//完成刷新
					newgridview.onRefreshComplete();					
					}
				});
		
	}
	int pageNum=1 ;
	int pageSize= 0 ;
	
	private void intiData() {				
		pageSize +=10 ;
		Map<String, String> params =new HashMap<String, String>();
		params.put("pageNum", pageNum+"");
		params.put("pageSize", pageSize+"");
		
		HTTPUtils.get(getContext(),TCHConstants.url.newurl ,params , new ResponseListener() {			
			@Override
			public void onResponse(String arg0) {
				
				New parseJSON = GsonUtils.parseJSON(arg0, New.class);
				List<Result> result = parseJSON.getResult();
				newlist.clear();
				newlist.addAll(result);
		    
				try {
					goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				newgridview.setAdapter(newgvadapter);
				newgvadapter.notifyDataSetChanged();												
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(getContext(), "请检查你的网络", Toast.LENGTH_LONG).show();
			}
		});
	}
       private void intiUIL() {		
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.err)
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
       
     class newgridviewAdapter extends BaseAdapter
   	    {   		
   		private View layout;
   		@Override
   		public int getCount() {
   			//TODO
   			return goodsList.length();
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
   			viewHolder holder = null;
   			View layout = null;			
   			if(convertView==null)
   			{
   				holder = new viewHolder();
   			    layout = inflater.inflate(R.layout.hot_girdviw_item, null); // 复用 hot gv item
   			    holder.img_hot_gv_item = (ImageView) layout.findViewById(R.id.img_hot_gv_item);
   			    holder.tv_hot_title = (TextView) layout.findViewById(R.id.tv_hot_title);
   	            holder.tv_hot_shoujia = (TextView) layout.findViewById(R.id.tv_hot_shoujia);		
   			    layout.setTag(holder);
   			}else
   			{
   				layout = convertView;
   				holder = (viewHolder) layout.getTag();
   			}	
   			try {   				
				holder.tv_hot_title.setText(goodsList.getJSONObject(position).getString("CM_TITLE")); // 标题
				holder.tv_hot_shoujia.setText(goodsList.getJSONObject(position).getString("CM_ORIGINALPRICE"));//售价
				ImageLoader.getInstance().displayImage(
						TCHConstants.url.imgurl + goodsList.getJSONObject(position).getString("CM_MAINFIGUREPATH"),
						holder.img_hot_gv_item, MyApplication.options);//图片地址
				
	//			Glide.with(getContext()).load(TCHConstants.url.imgurl + goodsList.getJSONObject(position).getString("CM_MAINFIGUREPATH")).into(holder.img_hot_gv_item);
			} catch (JSONException e) {
				e.printStackTrace();
			}
   		
   			return layout;
   		}				
   		class viewHolder
   		{
   			ImageView img_hot_gv_item;
   			TextView tv_hot_title;
   			TextView tv_hot_shoujia;
   		}		
   	}     
       
       
}
