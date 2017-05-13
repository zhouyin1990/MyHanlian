package com.example.hanlian.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import DetailsModle.Result;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import utils.CardGoodsInfo;
import utils.DButils;
import utils.TCHConstants;
/*
 *
 * @ 确认订单Activity
 * */
public class FirmorderActivity extends Activity  implements OnClickListener{

	private ArrayList<CardGoodsInfo> infolist = new ArrayList<CardGoodsInfo>();
	private ListView confim_list;
	private boolean isShow;
	private int index = -1;//详情显示的位置
	private MyAdapter myAdapter;
	private TextView tv_count;
	private TextView tv_totalprice;
	private int totalNumber;//总数量
	private int totalPrice;//总价格
	private String name;//名称
	private JSONObject productObject;
	private ArrayList<DetailsModle.Result> resultList = new ArrayList<Result>();// 商品数据
	private ArrayList<productSpec> specList = new ArrayList<productSpec>();// 商品规格
	private TextView tv_shouhuoname;
	private TextView tv_phone;
	private TextView tv_adress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confim);
//		PushAgent.getInstance(this).onAppStart();
		intiUI();
		intiData();

	}

	private void intiData() {
		List<CardGoodsInfo> list = DButils.query();
		infolist.clear();
		infolist.addAll(list);
//		myAdapter.notifyDataSetChanged();
		SharedPreferences sp = getSharedPreferences("登录", 1);
		String cm_shopeaddress = sp.getString("cm_shopeaddress","");
		String cm_phone = sp.getString("cm_phone","");
		String cm_name = sp.getString("cm_name", "");
		tv_adress.setText(cm_shopeaddress);
		tv_phone.setText(cm_phone);
		tv_shouhuoname.setText(cm_name);

		try {
			Intent intent = getIntent();
			String data = intent.getStringExtra("order");

			if ("action".equals(intent.getAction())) {
				resultList = (ArrayList<DetailsModle.Result>) intent.getSerializableExtra("resultList");
			}
			productObject = new JSONObject(data);
			JSONArray oRDERS = productObject.getJSONArray("ORDERS");
			JSONArray goodSLIST = oRDERS.getJSONObject(0).getJSONArray("GOODSLIST");
			JSONArray goodsDETAILS = goodSLIST.getJSONObject(0).getJSONArray("GOODSDETAILS");

//			Log.e("goodsDETAILS===", goodsDETAILS.toString());

			for (int i = 0; i < goodsDETAILS.length(); i++) {
				int number = 0 ;
				String size = "";

				String id = goodsDETAILS.getJSONObject(i).getString("GOODSDETAILSID");
				String color = goodsDETAILS.getJSONObject(i).getString("COLOR");
				String specNumber = goodsDETAILS.getJSONObject(i).getString("SPEC_NUMBER");
				// 建议身高100CM_0|建议身高110CM_3|建议身高120CM_0|建议身高130CM_0|建议身高140CM_0|
				Log.e("specNumber===", specNumber);

				String[] split = specNumber.split("\\|");
				for (int j = 0; j < split.length; j++) {
					String[] split2 = split[j].split("_");
					int parseInt = Integer.parseInt(split2[1]);
					if(parseInt > 0){
						totalNumber += parseInt;
						number = parseInt;
						size = split2[0];
						specList.add(new productSpec(color, size, number));
					}
				}
			}
			myAdapter.notifyDataSetChanged();
			tv_count.setText("共" + totalNumber + "件");
			tv_totalprice.setText("合计：¥ " + totalPrice);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void intiUI() {

		tv_count = (TextView) findViewById(R.id.tv_count);
		tv_totalprice = (TextView) findViewById(R.id.tv_totalprice);
		findViewById(R.id.tv_jiesuan).setOnClickListener(this);
		findViewById(R.id.tv_collectionback).setOnClickListener(this);

		tv_shouhuoname = (TextView) findViewById(R.id.tv_shouhuoname);
		tv_phone=(TextView)findViewById(R.id.tv_phone);
		tv_adress=(TextView)findViewById(R.id.tv_orderadress);

		confim_list = (ListView) findViewById(R.id.confim_list);
		myAdapter = new MyAdapter();
		confim_list.setAdapter(myAdapter);
	}

	class MyAdapter extends BaseAdapter{


		@Override
		public int getCount() {
			return resultList.size();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHold hold = null;
			if (convertView == null) {
				hold = new ViewHold();
				view = getLayoutInflater().inflate(R.layout.item_confim,null);
				hold.relative = (RelativeLayout) view.findViewById(R.id.item_cart_relative);
				hold.shoppingName = (TextView) view.findViewById(R.id.shoppingname);
				hold.goodsImage = (ImageView) view.findViewById(R.id.image);
				hold.name = (TextView) view.findViewById(R.id.name);
				hold.price = (TextView) view.findViewById(R.id.price);
				hold.number = (TextView) view.findViewById(R.id.number);
				hold.detail = (TextView) view.findViewById(R.id.detail);
				hold.spec_listview = (ListView) view.findViewById(R.id.spec_listview);
				view.setTag(hold);
			} else {
				view = convertView;
				hold = (ViewHold)view.getTag();
			}

			Result result = resultList.get(position);

			Log.e("result===", result.toString());

			hold.name.setText(result.getCMTITLE());
			hold.price.setText(result.getCMPRESENTPRICE()+ "");
			hold.number.setText(totalNumber +"");

			tv_totalprice.setText("合计：¥ " + result.getCMPRESENTPRICE() * totalNumber);

			ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + result.getCMMAINFIGUREPATH(),
					hold.goodsImage);

//			String shoppingName = goodsInfo.getShoppingName();
			String shoppingName = result.getCMSELLERNAME();
			hold.shoppingName.setText(shoppingName);
			// 隐藏相同的商店名称
//			if(position > 0){
//				String lastName = infolist.get(position - 1).getShoppingName();
//				if(lastName.equals(shoppingName)){
//					hold.shoppingName.setVisibility(View.GONE);
//					hold.shoppingName.setText(shoppingName);
//				} else {
//					hold.shoppingName.setVisibility(View.VISIBLE);
//					hold.shoppingName.setText(shoppingName);
//				}
//			} else {
//				hold.shoppingName.setVisibility(View.VISIBLE);
//				hold.shoppingName.setText(shoppingName);
//			}
			if(isShow && index == position){
				//hold.spec_listview.setVisibility(View.GONE);

				hold.spec_listview.setVisibility(View.VISIBLE);//显示规格listview
			} else {
				hold.spec_listview.setVisibility(View.GONE);//

			//hold.spec_listview.setVisibility(View.VISIBLE)
			}
			specAdapter specAdapter = new specAdapter();
			setListViewHeightBasedOnChildren(hold.spec_listview);
			hold.spec_listview.setAdapter(specAdapter);

			// TODO 弹出详情
			hold.detail.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					index = position;
					isShow = !isShow;

					myAdapter.notifyDataSetChanged();
				}
			});
			return view;
		}

	}

	class specAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return specList.size();
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
			View view = null;
			ViewHold2 hold = null;

			if (convertView == null) {
				hold = new ViewHold2();
				view = getLayoutInflater().inflate(R.layout.item_confim_size, null);
				hold.color = (TextView) view.findViewById(R.id.color);
				hold.size = (TextView) view.findViewById(R.id.size);
				hold.number = (TextView) view.findViewById(R.id.number);
				view.setTag(hold);
			} else {
				view = convertView;
				hold = (ViewHold2)view.getTag();
			}
			productSpec productSpec = specList.get(position);
			hold.color.setText(productSpec.color + "");
			hold.size.setText(productSpec.size + "");
			hold.number.setText(productSpec.number + "");
			return view;
		}

	}

	/**
	 * scrollview嵌套listview显示不全 解决
	 *
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	class ViewHold {
		TextView name;
		TextView price;
		TextView number;
		TextView shoppingName;
		ImageView goodsImage;
		RelativeLayout relative;
		TextView detail;
		ListView spec_listview;
	}

	class ViewHold2 {
		TextView color;
		TextView size;
		TextView number;
	}



	class productSpec{
		String color;
		String size;
		int number;
		public productSpec(String color, String size, int number) {
			super();
			this.color = color;
			this.size = size;
			this.number = number;
		}
		@Override
		public String toString() {
			return "productSpec [color=" + color + ", size=" + size + ", number=" + number + "]";
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_collectionback:
				finish();
				break;
			case R.id.tv_jiesuan:
				//todo  跳到支付页面
				Intent intent=new Intent(FirmorderActivity.this ,PayActivity.class);
				startActivity(intent);

				//--------提交成功后-----------
				break;

			default:
				break;
		}

	}



//		tv_jiesuan.setOnClickListener(new OnClickListener() {
//
//			@SuppressWarnings("unused")
//			@Override
//			public void onClick(View v) {
//
//				JSONArray array3 = new JSONArray();
//				for (int i = 0; i < infolist.size(); i++) {
//
//					boolean ischeck = infolist.get(i).isIscheck();
//					if (ischeck) {
//						JSONObject object3 = new JSONObject();
//						try {
//							object3.put("GOODSLIST", infolist.get(i).getGOODSLIST());
//
//							array3.put(i, object3);
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//				if (array3 != null) {
//					final JSONObject object4 = new JSONObject();
//					try {
//						object4.put("INTEGRAL", 0);
//						object4.put("ORDERS", array3);
//						submitOrder(object4, TCHConstants.url.token);
//
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}else{
//					Toast.makeText(getContext(), "请选择商品", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});

	//-------------------------提交订单------------------------------
	private void submitOrder(JSONObject object4, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);// TODO
		params.put("goodsjson", object4.toString());// json

		HTTPUtils.post(FirmorderActivity.this, TCHConstants.url.SUBMITORDER,
				params, new ResponseListener() {

					@Override
					public void onResponse(String arg0) {
						try {
							JSONObject object = new JSONObject(arg0);
							int errocode = object.getInt("ErrorCode");
							if (errocode == 0) {
								Toast.makeText(FirmorderActivity.this, "提交订单成功",
										Toast.LENGTH_SHORT).show();
								String token1 = object.getString("Token");
								TCHConstants.url.token=token1;

								//TODO 提交成功后 删除该商品
								for (int i = 0; i < infolist.size(); i++) {
									boolean ischeck = infolist.get(i).isIscheck();
									if(ischeck){
										DButils.delete("goodsid = ?", infolist.get(i));
										infolist.remove(i);
										myAdapter.notifyDataSetChanged();
									}
								}

							} else {
								Toast.makeText(FirmorderActivity.this,
										"ErrorCode = " + errocode, Toast.LENGTH_SHORT).show();
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







}