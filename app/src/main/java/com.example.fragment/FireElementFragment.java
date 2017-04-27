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
import com.example.hanlian.HotData.Hotdata;
import com.example.hanlian.HotData.Result;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
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

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FireElementFragment extends Fragment {
	private View layout;
	private LayoutInflater inflater;	
	private PullToRefreshGridView hotgv;
	private hotgridviewAdapter hotgridviewadapter;
	private DisplayImageOptions  options;
	private JSONArray goodsList = new JSONArray();
	private ArrayList<Result> hotlist = new ArrayList<Result>();

	public FireElementFragment() 
	{
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		if(layout==null)
		{
			layout = inflater.inflate(R.layout.fragment_fire_element, container, false);	
			intiUI();
			intidata();
		}else
		{
			ViewGroup parent = (ViewGroup) layout.getParent();
	        if (parent!=null){	        	
	          parent.removeAllViewsInLayout();
	        }
		}
		return layout; 
	}

	int pageNum=1 ;
	int pageSize= 0 ;	
	private void intidata() 
	{	
		pageSize +=10;
		Map<String, String> params =new HashMap<String,String>();
		params.put("pageNum", pageNum+"");
		params.put("pageSize", pageSize+"");
		
		HTTPUtils.get(getContext(),TCHConstants.url.hoturl ,params ,new ResponseListener() {
			
			@Override
			public void onResponse(String arg0) {
				Hotdata parseJSON = GsonUtils.parseJSON(arg0, Hotdata.class);

				List<Result> result = parseJSON.getResult();

				hotlist.clear();
				hotlist.addAll(result);
				try {
					goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");														
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				hotgv.setAdapter(hotgridviewadapter);
				hotgridviewadapter.notifyDataSetChanged();								
			}			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(getContext(), "请检查你的网络", Toast.LENGTH_LONG).show();
			}
		});
	}
	private void intiUI() 
	{	
		
		hotgv = (PullToRefreshGridView) layout.findViewById(R.id.hot_gridview);
		hotgridviewadapter = new hotgridviewAdapter();
		hotgv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent=new Intent(getActivity(), GoodsDetailActivity.class);
				String goodsid = hotlist.get(position).getCMGOODSID();
			    intent.putExtra("goodsid", goodsid) ;
			    startActivity(intent);
			}
		});
		
		//下拉刷新
		hotgv.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub 上
				intidata();
				hotgv.onRefreshComplete();
			}
			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub  下
				intidata();
				hotgv.onRefreshComplete();
			}			
		});
		
	}	
	class hotgridviewAdapter extends BaseAdapter
	{		
		private View layout;
		@Override
		public int getCount() {
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
			    layout = inflater.inflate(R.layout.hot_girdviw_item, null);
			    holder.img_hot_gv_item = (ImageView) layout.findViewById(R.id.img_hot_gv_item);
			    holder.tv_hot_title = (TextView) layout.findViewById(R.id.tv_hot_title);
	            holder.tv_hot_shoujia = (TextView) layout.findViewById(R.id.tv_hot_shoujia);		
			    layout.setTag(holder);
			}else
			{
				layout = convertView;
				holder = (viewHolder)layout.getTag();
			}						
			try {   				
				holder.tv_hot_title.setText(goodsList.getJSONObject(position).getString("CM_TITLE")); // 标题
				holder.tv_hot_shoujia.setText(goodsList.getJSONObject(position).getString("CM_ORIGINALPRICE"));//售价
				ImageLoader.getInstance().displayImage(
				           TCHConstants.url.imgurl + goodsList.getJSONObject(position).getString("CM_MAINFIGUREPATH"),
						   holder.img_hot_gv_item, MyApplication.options);//图片地址
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
