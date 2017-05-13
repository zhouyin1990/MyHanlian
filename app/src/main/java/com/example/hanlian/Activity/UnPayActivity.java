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
import com.example.hanlian.R;
import com.example.hanlian.TestToken.TestToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import UnpayModel.TBORDERDETAIL;
import UnpayModel.Unpay;
import okhttp3.Call;
import utils.TCHConstants;

public class UnPayActivity extends Activity implements OnClickListener{

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
	private Button btn_pay;
	private Button btn_cannelorder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_un_pay);
//		PushAgent.getInstance(this).onAppStart();
		intiUi();
		intiUIL();
		intidata();
	}


	
	private void intiUi() {
		unpay_listview = (ListView) findViewById(R.id.listview_unpay);
		img_uppayback = (ImageView) findViewById(R.id.img_uppayback);
		img_uppayback.setOnClickListener(this);
		btn_cannelorder = (Button) findViewById(R.id.btn_cannelorder);
		btn_pay = (Button) findViewById(R.id.btn_pay);
		btn_cannelorder.setOnClickListener(this);
		btn_pay.setOnClickListener(this);



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
			Button    btn_cannelorder ;
			Button    btn_pay;

		}
	}



	@Override
	public void onClick(View v) {
	  switch (v.getId()) {
	case R.id.img_uppayback:
		finish();
		break;
		  case R.id.btn_cannelorder:
			  cancelorder();
			  break;
		  case R.id.btn_pay:
			  pay();
			  break;
	default:
		break;
	}
		
		
	}

	private void pay() {

    //todo 跳转到支付页面支付




	}
    //todo 返回token 赋值
	private void cancelorder() {
//		String cmorderid = unlist.get(0).getCMORDERID();
		HashMap<String, String> parms = new HashMap<>();

		for (int i = 0; i < unlist.size(); i++) {

			String orderid = unlist.get(i).getCMORDERID();
			parms.put("orderid", orderid);
			parms.put("token", TCHConstants.url.token);
		}

//		parms.put("orderid",cmorderid);
//		parms.put("token",TCHConstants.url.token);


		OkHttpUtils.get().params(parms).url(TCHConstants.url.DelMyOrder).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				Log.e("Exception e ==",""+e);
			}

			@Override
			public void onResponse(String response, int id) {
				Log.e("response  ==",""+response);
				try {
					JSONObject jsonObject = new JSONObject(response);
					String token = jsonObject.getString("Token");
					TCHConstants.url.token=token ;
					int errorCode = jsonObject.getInt("ErrorCode");
					if (errorCode==0)
					{
						Toast.makeText(UnPayActivity.this ,"已删除订单",Toast.LENGTH_SHORT).show();
					}else
					{
                      Log.e("取消订单errorCode==",""+errorCode);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
