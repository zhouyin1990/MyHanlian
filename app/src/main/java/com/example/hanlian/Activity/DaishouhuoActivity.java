package com.example.hanlian.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import DaishouhuoModel.Daishouhuo;
import okhttp3.Call;
import utils.LogUtil;
import utils.TCHConstants;

public class DaishouhuoActivity extends Activity  implements OnClickListener{



	private DisplayImageOptions options;
	private ImageView img_BACK;
	private ListView listview_daishouhuo;
    private int pageNum = 1;
    private int pageSize = 0;
	ArrayList<DaishouhuoModel.Result>daishouhuolist =new ArrayList<DaishouhuoModel.Result>() ;
	ArrayList<DaishouhuoModel.TBORDERDETAIL>  TB_list =new ArrayList<DaishouhuoModel.TBORDERDETAIL>();
	private TextView tv_more;
    private DaishouhuoAdapter adapter;

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

		tv_more = (TextView)findViewById(R.id.tv_daishouhuo_more);
		img_BACK = (ImageView) findViewById(R.id.img_daoshouhuo_back);
		listview_daishouhuo = (ListView) findViewById(R.id.listview_daishouhuo);
		img_BACK.setOnClickListener(this);

        adapter = new DaishouhuoAdapter();
        listview_daishouhuo.setAdapter(adapter);
        listview_daishouhuo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent=new Intent(DaishouhuoActivity.this , OrderDetailActivity.class);
			   String cmorderid = daishouhuolist.get(position).getCMORDERID();
			   intent.putExtra("cmorderid", cmorderid);
               startActivity(intent );

           }
       });



	}

	private void initData() {

			    pageSize += 10;
				Map<String, String> parms1 = new HashMap<String, String>();
				Intent intent = getIntent();
				parms1.put("pageNum", pageNum +"");
				parms1.put("pageSize", pageSize +"");
				parms1.put("token", TCHConstants.url.token);
				// TODO
				HTTPUtils.get(DaishouhuoActivity.this, TCHConstants.url.daishouhuoUrl, parms1, new ResponseListener() {

					@Override
					public void onResponse(String arg0) {

						LogUtil.e(" 待收货json==",""+arg0);


					Daishouhuo daishouhuojson = GsonUtils.parseJSON(arg0, Daishouhuo.class);

					List<DaishouhuoModel.Result> result = daishouhuojson.getResult();
					if(result!=null)

					daishouhuolist.clear();
					daishouhuolist.addAll(result);
					adapter.notifyDataSetChanged();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
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
				holder.btn_confirmshouhuo = (Button) layout.findViewById(R.id.btn_confirmshouhuo);
				holder.btn_querywuliu=(Button)layout.findViewById(R.id.btn_querywuliu);


				holder.tv_order_time=(TextView)layout.findViewById(R.id.tv_order_time);
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


			List<DaishouhuoModel.TBORDERDETAIL> tborderdetails = daishouhuolist.get(position).getTBORDERDETAILS();
			String cmcreatetime = daishouhuolist.get(position).getCMCREATETIME();


			TB_list.clear();
			TB_list.addAll(tborderdetails);
			DaishouhuoModel.TBORDERDETAIL tborderdetail2 = TB_list.get(0);


			String cmtitle = (String) tborderdetail2.getCMTITLE();
			Double cmpresentprice = (Double) tborderdetail2.getCMPRESENTPRICE();
			String cmmainfigurepath = (String) tborderdetail2.getCMMAINFIGUREPATH();


			Integer cmmoney = tborderdetail2.getCMMONEY();


			Integer cmnumber = tborderdetail2.getCMNUMBER();


			holder.tv_shangpin_name.setText(cmtitle);
			holder.tv_shoujia.setText("¥" +cmpresentprice);
			holder.tv_xiaoliang.setText("X"+cmnumber);
			holder.tv_price.setText("合计 :¥"+cmmoney);
			holder.tv_order_time.setText(cmcreatetime);
			holder.tv_total_sales.setText("共"+cmnumber +"件商品");
			if(cmmainfigurepath!=null)
			{
				ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl+cmmainfigurepath, holder.img_shangpin_name, options);
			}

			holder.btn_querywuliu.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//物流详情
					for (int i = 0; i <daishouhuolist.size() ; i++) {

						String cmorderdetailsids = daishouhuolist.get(position).getCMORDERDETAILSIDS();

						String trim = cmorderdetailsids.toString().trim();
						String orderdetailsids = trim.substring(0, trim.length() - 1);



						TCHConstants.url.Orderdetailsids=orderdetailsids;


						Log.e("Orderdetailsids==",TCHConstants.url.Orderdetailsids);

					}
					Intent intent=new Intent(DaishouhuoActivity.this,LogisticsActivity.class);
					startActivity(intent );

				}
			});



			holder.btn_confirmshouhuo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					confimorder();


				}
			});




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
		Button    btn_confirmshouhuo ;
		TextView  tv_order_time;
		Button    btn_querywuliu ;
	}

	public void confimorder() {
		HashMap<String, String> parms = new HashMap<>();
		parms.put("orderid", TCHConstants.url.orderid);
		parms.put("token", TCHConstants.url.token);

		Log.e("orderid",TCHConstants.url.orderid);
		Log.e("token", TCHConstants.url.token);

		OkHttpUtils.get().params(parms).url(TCHConstants.url.ConfirmOrder).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {

				Log.e("确认收货ErrorCode ==", "" + e);

			}

			@Override
			public void onResponse(String response, int id) {
				Log.e("确认收货json ==", response);
				try {
					JSONObject jsonObject = new JSONObject(response);

					int errorCode = jsonObject.getInt("ErrorCode");
					if (errorCode == 0) {
						String token = jsonObject.getString("Token");
//						TCHConstants.url.token = token;
						//todo 测试
						Log.e("confimordeToken==", token);
						//
						initData();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
