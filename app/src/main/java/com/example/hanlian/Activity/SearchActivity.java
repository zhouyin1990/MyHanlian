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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SearchDataModel.Result;
import SearchDataModel.Searchdata;
import utils.TCHConstants;

public class SearchActivity extends Activity implements OnClickListener{

	private TextView msearch;
	private EditText ed_searchgoods;
	ArrayList<Result> goodslist =new ArrayList<Result>();
	private String band;
	private String cmgoodsid;
	private String trim;
	private DisplayImageOptions  options;
	
	private SearchAdapter searchAdapter;
	private String newcmtitle;
	private GridView searchgridview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
//		PushAgent.getInstance(this).onAppStart();
		intiUI();	
		intiUIL();
	}
	private void intiUI() {
	findViewById(R.id.img_back).setOnClickListener(this);
	msearch = (TextView) findViewById(R.id.tv_search);
	ed_searchgoods = (EditText) findViewById(R.id.edittext_search);
	searchgridview = (GridView) findViewById(R.id.gridView_search);
	msearch.setOnClickListener(this);	
	searchAdapter = new SearchAdapter();
	
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
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:	    
	        finish();		
			break;
		case R.id.tv_search:		
			gosearch();						
			break;
		default:
			break;
		}		
	}

	private void gosearch() 
	{		
		try {
			trim = ed_searchgoods.getText().toString().trim();
			urlStr = URLEncoder.encode(trim, "UTF-8"); 	
	        intidata();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							
	}
	
	int pageNum=1 ;
	int pageSize= 8;
	private String str;
	private String urlStr;	
	private void intidata() {
		Map<String, String> params =new HashMap<String, String>();//在家一个参数？	
		params.put("content",urlStr);
		params.put("pageNum", pageNum+"");
		params.put("pageSize", pageSize+"");			
		HTTPUtils.get(SearchActivity.this, TCHConstants.url.searchurl,params, new ResponseListener() {
			@Override
			public void onResponse(String arg0) {				
				Searchdata searchdata = GsonUtils.parseJSON(arg0, Searchdata.class);
				 List<Result> result = searchdata.getResult();
				 goodslist.clear();
				 goodslist.addAll(result);				
				 searchgridview.setAdapter(searchAdapter);
				 searchAdapter.notifyDataSetChanged();	
				 searchgridview.setOnItemClickListener(new OnItemClickListener() 
				    {

						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							Intent intent=new Intent(SearchActivity.this, GoodsDetailActivity.class);
							startActivity(intent);	
						} 
				        
				    }); 
				 
			}			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(SearchActivity.this, "网络错误 请检查网络", Toast.LENGTH_SHORT).show();
			}
		});
	}
     
	   class SearchAdapter extends BaseAdapter
	   	{	   		
	   		private View layout;
			
	   		@Override
	   		public int getCount() {
	   			//TODO
	   			return goodslist.size();
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
	   			    layout = getLayoutInflater().inflate(R.layout.hot_girdviw_item, null); // 复用 hot gv item
	   			    holder.img_hot_gv_item = (ImageView) layout.findViewById(R.id.img_hot_gv_item);
	   			    holder.tv_hot_title = (TextView) layout.findViewById(R.id.tv_hot_title);
	   	            holder.tv_hot_shoujia = (TextView) layout.findViewById(R.id.tv_hot_shoujia);		
	   			    layout.setTag(holder);
	   			}else
	   			{
	   				layout = convertView;
	   				holder = (viewHolder) layout.getTag();
	   			}	
	   			newcmtitle = goodslist.get(position).getCMTITLE();
	   			Integer newcmpresentprice = goodslist.get(position).getCMPRESENTPRICE();
	   			Integer newcmsales = goodslist.get(position).getCMSALES(); // xiaoliao
	   			String newcmmainfigurepath = goodslist.get(position).getCMMAINFIGUREPATH();
	   			holder.tv_hot_title.setText(newcmtitle);
	   			holder.tv_hot_shoujia.setText(""+newcmpresentprice);
	   			ImageLoader.getInstance().displayImage
	   			(TCHConstants.url.searchurl, holder.img_hot_gv_item, options);												
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

