package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hanlian.Adapter.Result;
import com.example.hanlian.Adapter.Tuijian;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.TCHConstants;

public class JiFenActivity extends Activity implements OnClickListener{

	private Button btn_jifenduihuan;
	private PullToRefreshScrollView tuijiangv;
	int pageNum=1 ;
	int pageSize= 0 ;
	ArrayList<Result> tuijianlist =new ArrayList<Result>();
	private String[] split;
	private ArrayList<String[]> splitslist = new ArrayList<String[]>();
	private jifenadapter jifenadapter;
	private GridView jifengv;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ji_fen);
//		PushAgent.getInstance(this).onAppStart();
		intiUI();
		intijifen();//积分
//		intidata();
	}

	private void intidata() {
		pageSize +=10 ;
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", 0+"");
		params.put("classifyID",0+"");
		params.put("pageNum",  pageNum+"");
		params.put("pageSize",pageSize+"" );
		
		HTTPUtils.get(JiFenActivity.this, TCHConstants.url.QueryPromotionData, params, new ResponseListener() {
			
			@Override
			public void onResponse(String arg0) {
				Tuijian tuijianjson = GsonUtils.parseJSON(arg0, Tuijian.class);
				List<Result> result = tuijianjson.getResult();
				tuijianlist.clear();
				tuijianlist.addAll(result);
				if(tuijianlist !=null)
				{					
					String cmfigurespath = tuijianlist.get(0).getCMFIGURESPATH();
				    split = cmfigurespath.split("\\|"); //分割图片
				    jifengv.setAdapter(jifenadapter);
				    
				    
				    jifenadapter.notifyDataSetChanged();
				}
				
				
				
				
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	private void intiUI() 
	{
		
		
		findViewById(R.id.tv_jifen_back).setOnClickListener(this);;
		btn_jifenduihuan = (Button) findViewById(R.id.btn_jifenduihuan);
		btn_jifenduihuan.setOnClickListener(this);
//		jifengv = (GridView) findViewById(R.id.jifen_gridView_detail);
//
//		jifengv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent intent=new Intent(JiFenActivity.this, GoodsDetailActivity.class);
//				 String goodsid = tuijianlist.get(position).getCMGOODSID();
//				 intent.putExtra("goodsid", goodsid) ;
//				startActivity(intent);
//			}
//		});
//
//
//
//		jifenadapter = new jifenadapter();
//
//
//
//		tuijiangv = (PullToRefreshScrollView) findViewById(R.id.tuijian_gridview_gridview);
//		//上拉下拉刷新
//		tuijiangv.setOnRefreshListener(new OnRefreshListener2<ScrollView>() {
//
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//				// TODO Auto-generated method stub
//				intidata();
//				tuijiangv.onRefreshComplete();
//			}
//
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//				// TODO Auto-generated method stub
//				intidata();
//				tuijiangv.onRefreshComplete();
//			}
//
//		});
	}
	

	private void intijifen() 
	{
		
	}

	@Override
	public void onClick(View v) {
		// TODO 点击事件
		switch (v.getId()) {
		case R.id.tv_jifen: //积分
			
			break;
		case R.id.tv_jifen_back: //			
			finish();
			break; 
		case R.id.btn_jifenduihuan: //兑换积分
		   jifenduihuan();
		   break;
		  default:
			break;
		}
		
	}

	private void jifenduihuan() 
	{
		
	}
	
	class jifenadapter extends  BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tuijianlist.size();
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			viewHolder holder = null;
   			View layout = null;			
   			if(convertView==null)
   			{
   				holder = new viewHolder();		
   			    layout = getLayoutInflater().inflate(R.layout.tuijian_gridview_item, null); // 复用 hot gv item
   			    holder.img_tuijian_gv_item = (ImageView) layout.findViewById(R.id.img_tuijian_gv_item);
   			    holder.tv_tuijian_title = (TextView) layout.findViewById(R.id.tv_tuijian_title);
   	            holder.tv_tuijian_shoujia = (TextView) layout.findViewById(R.id.tv_tuijian_shoujia);		
   			    layout.setTag(holder);
   			}else
   			{
   				layout = convertView;
   				holder = (viewHolder) layout.getTag();
   			}	
           String cmmainfigurepath = tuijianlist.get(position).getCMMAINFIGUREPATH();
           String cmtitle = tuijianlist.get(position).getCMTITLE();
           Integer cmpresentprice = tuijianlist.get(position).getCMPRESENTPRICE();
           holder.tv_tuijian_title.setText(cmtitle);
           holder.tv_tuijian_shoujia.setText("" +cmpresentprice);                      
           ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + cmmainfigurepath, 
        		  holder.img_tuijian_gv_item, MyApplication.options);
   		   return layout;
   		}				
   		class viewHolder
   		{
   			ImageView img_tuijian_gv_item;
   			TextView tv_tuijian_title;
   			TextView tv_tuijian_shoujia;
   		}		
   	}     
       
	
	
}
