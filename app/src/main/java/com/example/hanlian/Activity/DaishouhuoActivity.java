package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DaishouhuoModel.Daishouhuo;
import utils.TCHConstants;

public class DaishouhuoActivity extends Activity  implements OnClickListener{

	
	
	private DisplayImageOptions options;
	private ImageView img_BACK;
	private ListView listview_daishouhuo;
    private int pageNum = 1;
    private int pageSize = 0;
	String uptoekn ;
	ArrayList<DaishouhuoModel.Result>daishouhuolist =new ArrayList<DaishouhuoModel.Result>() ;
	ArrayList<DaishouhuoModel.TBORDERDETAIL>  TB_list =new ArrayList<DaishouhuoModel.TBORDERDETAIL>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daishouhuo);
//		PushAgent.getInstance(this).onAppStart();
		initUI();
		intiUIL();
		initData();
	}

	private void initUI() {
		img_BACK = (ImageView) findViewById(R.id.img_daoshouhuo_back);
		listview_daishouhuo = (ListView) findViewById(R.id.listview_daishouhuo);
		img_BACK.setOnClickListener(this);
		
	}

	private void initData() {
	 HashMap<String, String> params = new HashMap<String , String >();		
	 params.put("account", "08750112");
	 params.put("password", "222222");
	 HTTPUtils.get(DaishouhuoActivity.this,TCHConstants.url.GETTESTTOKEN, params, new ResponseListener() {
		
		@Override
		public void onResponse(String arg0) {
			TestToken parseJSON = GsonUtils.parseJSON(arg0, TestToken.class);
			Integer errorCode = parseJSON.getErrorCode();
			if(errorCode == 0)
			{
			  String uptoekn = parseJSON.getToken();
			  //查询订单		
			    pageSize += 10;				
				Map<String, String> parms1 = new HashMap<String, String>();
				Intent intent = getIntent();
				parms1.put("pageNum", pageNum +"");
				parms1.put("pageSize", pageSize +"");
				parms1.put("token", uptoekn);     
				// TODO 
				HTTPUtils.get(DaishouhuoActivity.this, TCHConstants.url.daishouhuoUrl, parms1, new ResponseListener() {
					
					@Override
					public void onResponse(String arg0) {
		            //链接成功但内容不显示
						Toast.makeText(DaishouhuoActivity.this,
								"onResponse = " , Toast.LENGTH_SHORT).show();
					  
					Daishouhuo daishouhuojson = GsonUtils.parseJSON(arg0, Daishouhuo.class);
//					List<DaishouhuoModel.Result> result = daishouhuojson.getResult();
//					daishouhuolist.clear();
//					daishouhuolist.addAll(result);
					
					List<DaishouhuoModel.Result> result = daishouhuojson.getResult();
					daishouhuolist.clear();
					daishouhuolist.addAll(result);										
					DaishouhuoAdapter adapter = new DaishouhuoAdapter();
					listview_daishouhuo.setAdapter(adapter);
					adapter.notifyDataSetChanged();	
					
					}
					
					@Override
					public void onErrorResponse(VolleyError arg0) {
					//	Toast.makeText(DaishouhuoActivity.this, arg0.toString(), 100).show();

					}
				});
				
		
			  
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_daoshouhuo_back:
			finish();
			break;
		default:
			break;
		}
	}

	class DaishouhuoAdapter extends BaseAdapter{
		View layout = null;
		ViewHolder holder;
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				holder = new ViewHolder();
				layout = getLayoutInflater().inflate(R.layout.layout_item_allorder, null);
				holder.img_shangpin_name = (ImageView) layout.findViewById(R.id.img_shangpin_name);
				holder.tv_shangpin_name = (TextView) layout.findViewById(R.id.tv_shangpin_name);
				holder.tv_shoujia = (TextView) layout.findViewById(R.id.tv_shoujia);
				holder.tv_xiaoliang = (TextView) layout.findViewById(R.id.tv_xiaoliang);
				holder.tv_price = (TextView) layout.findViewById(R.id.tv_price);
				holder.tv_state = (TextView) layout.findViewById(R.id.tv_state);
				holder.tv_total_sales = (TextView)layout.findViewById(R.id.tv_total_sales);
				holder.btn_wuliu  = (Button) layout.findViewById(R.id.btn_wuliu);
				holder.btn_querenshouhuo = (Button) layout.findViewById(R.id.btn_querenshouhuo);
				layout.setTag(holder);
			}else{
				layout = convertView;
				holder = (ViewHolder) layout.getTag();
			}			
			Integer cmstate = daishouhuolist.get(position).getCMSTATE(); 
			if(cmstate==0)
			{
				holder.tv_state.setText("待支付");
			}else if(cmstate==1)
			{
				holder.tv_state.setText("已取消");
			}else if(cmstate==2)
		    {
				holder.tv_state.setText("待发货");
		    }else if(cmstate==3)
	        {
		    	holder.tv_state.setText("已发货");
	        }else if(cmstate==4)
		    {
	        	holder.tv_state.setText("已完成");
		    }	
		
		//	List<DaishouhuoModel.TBORDERDETAIL> tborderdetails2list = daishouhuolist.get(position).getTBORDERDETAILS();
			
			List<DaishouhuoModel.TBORDERDETAIL> tborderdetails = daishouhuolist.get(position).getTBORDERDETAILS();
			
			
			TB_list.clear();
			TB_list.addAll(tborderdetails);
			DaishouhuoModel.TBORDERDETAIL tborderdetail2 = TB_list.get(0);
			
	//		String cmtitle = tborderdetail2.getCMTITLE();
			
			String cmtitle = (String) tborderdetail2.getCMTITLE();			
			Integer cmpresentprice = (Integer) tborderdetail2.getCMPRESENTPRICE();
			String cmmainfigurepath = (String) tborderdetail2.getCMMAINFIGUREPATH();						
			
			Integer cmmoney = tborderdetail2.getCMMONEY();
			Integer cmnumber = tborderdetail2.getCMNUMBER();			
			holder.tv_shangpin_name.setText(cmtitle);
			holder.tv_shoujia.setText("¥" +cmpresentprice);
			holder.tv_xiaoliang.setText("X"+cmnumber);
			holder.tv_price.setText("合计 :¥"+cmmoney);
			holder.tv_total_sales.setText("共"+cmnumber +"件商品");	
			if(cmmainfigurepath!=null)
			{
				ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl+cmmainfigurepath, holder.img_shangpin_name, options);
				//Glide.with(DaishouhuoActivity.this).load(TCHConstants.url.imgurl+cmmainfigurepath).into(holder.img_shangpin_name);
			}
			return layout;
		}
		@Override
		public int getCount() {
			return daishouhuolist.size();
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
		TextView  tv_shangpin_name;
		TextView  tv_shoujia;
		TextView  tv_xiaoliang;
		TextView  tv_total_sales ;
		TextView  tv_price ;
		TextView  tv_state ;
		Button    btn_wuliu ;
		Button    btn_querenshouhuo ;		
	}
	
	
	
	
}
