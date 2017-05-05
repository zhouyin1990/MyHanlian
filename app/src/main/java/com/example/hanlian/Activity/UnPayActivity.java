package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hanlian.R;
import com.example.hanlian.TestToken.TestToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.umeng.message.PushAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import UnpayModel.TBORDERDETAIL;
import UnpayModel.Unpay;
import utils.TCHConstants;

public class UnPayActivity extends Activity implements OnClickListener{

	String account ="111111" ;
    String password ="222222";
    private int pageNum = 1;
    private int pageSize = 0;; 
    private DisplayImageOptions options;
	private TextView TV_dianpu_name;
	private TextView TV_unpay;
	private ListView unpay_listview;
	private PayAdapter payAdapter;
	String uptoekn ;
	ArrayList<UnpayModel.Result> unlist =new ArrayList<UnpayModel.Result>();
	ArrayList<TBORDERDETAIL> orderdetailslist =new ArrayList<TBORDERDETAIL>();
	private ImageView img_uppayback;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_pay);
		PushAgent.getInstance(this).onAppStart();
		intiUi();
		intiUIL();
		intidata();
	}


	
	private void intiUi() {
		unpay_listview = (ListView) findViewById(R.id.listview_unpay);
		img_uppayback = (ImageView) findViewById(R.id.img_uppayback);
		img_uppayback.setOnClickListener(this);
		payAdapter = new PayAdapter();	               
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
	
	
	private void intidata() {

					Map<String, String> parms1 = new HashMap<String, String>();
					Intent intent = getIntent();
					parms1.put("pageNum", pageNum +"");
					parms1.put("pageSize", pageSize +"");
					parms1.put("token", TCHConstants.url.token);
					// TODO
					HTTPUtils.get(UnPayActivity.this,TCHConstants.url.QueryMyOrders_Arrearageuri, parms1, new ResponseListener() {

						@Override
						public void onResponse(String arg0) {
							Unpay unpayjson = GsonUtils.parseJSON(arg0, Unpay.class);
							List<UnpayModel.Result> result = unpayjson.getResult();
							unlist.clear();
							unlist.addAll(result);
							unpay_listview.setAdapter(payAdapter);
							payAdapter.notifyDataSetChanged();
						}

						@Override
						public void onErrorResponse(VolleyError arg0) {

						}
					});
	}


	


	class PayAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// 后续添加动态界面
		//return list.size();
			return unlist.size();
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
			if (convertView == null) {
				holder = new viewHolder();
				layout = getLayoutInflater().inflate(R.layout.unpay_listview_item, null);
				holder.img_shangpin_name = (ImageView) layout.findViewById(R.id.img_shangpin_name);
				holder.tv_shangpin_name = (TextView) layout.findViewById(R.id.tv_shangpin_name);
				holder.tv_shoujia = (TextView) layout.findViewById(R.id.tv_shoujia);
				holder.tv_xiaoliang = (TextView) layout.findViewById(R.id.tv_xiaoliang);
				holder.tv_price = (TextView) layout.findViewById(R.id.tv_price);
				holder.tv_total_sales = (TextView)layout.findViewById(R.id.tv_total_sales);
				layout.setTag(holder);
			} else {
				layout = convertView;
				holder = (viewHolder) layout.getTag();
			}
			List<TBORDERDETAIL> tborderdetails = unlist.get(position).getTBORDERDETAILS();
			orderdetailslist.clear();
			orderdetailslist.addAll(tborderdetails);
			orderdetailslist.size();
			String cmtitle = orderdetailslist.get(0).getCMTITLE();
			Integer cmpresentprice = orderdetailslist.get(0).getCMPRESENTPRICE(); //单价
			Integer cmmoney = orderdetailslist.get(0).getCMMONEY();  //总价
			Integer cmnumber = orderdetailslist.get(0).getCMNUMBER();// 销量
			String cmmainfigurepath = orderdetailslist.get(0).getCMMAINFIGUREPATH(); //图片路境
			holder.tv_shangpin_name.setText(cmtitle);
			holder.tv_shoujia.setText("¥" +cmpresentprice);
			holder.tv_xiaoliang.setText("X"+cmnumber);
			holder.tv_price.setText("合计 :¥"+cmmoney);
			holder.tv_total_sales.setText("共"+cmnumber +"件商品");		
			if(cmmainfigurepath !=null)
			{
				
				ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl+cmmainfigurepath, holder.img_shangpin_name, options);							
			}
			return layout;
		}

		class viewHolder {
			ImageView img_shangpin_name;
			TextView tv_shangpin_name;
			TextView tv_shoujia;
			TextView tv_xiaoliang;
			TextView tv_total_sales ;
			TextView  tv_price ; // 总价
		}
	}



	@Override
	public void onClick(View v) {
	  switch (v.getId()) {
	case R.id.img_uppayback:
		finish();
		break;

	default:
		break;
	}
		
		
	}
			
		
	
}
