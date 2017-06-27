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
	private boolean isChange;
	private boolean isShow;
	private int index;//详情显示的位置
	private MyAdapter myAdapter;
	private TextView tv_count;
	private TextView tv_totalprice;
	private int totalNumber;//总数量
	private int totalPrice;//总价格
	private String name;//名称
	private JSONArray ordersArray;
	private JSONArray submitOrder;
	private ArrayList<ProductDetails> resultList = new ArrayList<>();// 商品数据

	private ArrayList<productSpec> specList = new ArrayList<>();// 商品规格 ArrayList<productSpec>

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

			JSONObject productObject = new JSONObject(data);

			Log.e("productObject==", productObject.toString());

			ordersArray = productObject.getJSONArray("ORDERS");

			totalNumber = 0;
			totalPrice = 0;

			for (int i = 0; i < ordersArray.length(); i++) {

				JSONObject details = ordersArray.getJSONObject(i);

				String title = details.getString("title");
				String ImgURL = details.getString("ImgURL");
				String shoppingName = details.getString("shoppingName");
				int count = details.getInt("count");
				int totalprice = details.getInt("totalprice");

				resultList.add(new ProductDetails(title, ImgURL, shoppingName, count, totalprice));

				totalNumber += count;
				totalPrice += totalprice;
			}

			myAdapter.notifyDataSetChanged();
			tv_count.setText("共" + totalNumber + "件");
			tv_totalprice.setText("合计：¥ " + totalPrice);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void getData(int index){

		try {

			JSONObject details = ordersArray.getJSONObject(index);

			JSONArray GOODSLIST = details.getJSONArray("GOODSLIST");

			int number = 0 ;
			String size = "";
			specList.clear();

			for (int j = 0; j < GOODSLIST.length(); j++) {
				String color = GOODSLIST.getJSONObject(j).getString("COLOR");
				String id = GOODSLIST.getJSONObject(j).getString("GOODSDETAILSID");
				String specNumber = GOODSLIST.getJSONObject(j).getString("SPEC_NUMBER");

				String[] split = specNumber.split("\\|");
				for (int k = 0; k < split.length; k++) {
					String[] split2 = split[k].split("_");
					int parseInt = Integer.parseInt(split2[1]);
					if(parseInt > 0){
						number = parseInt;
						size = split2[0];
						specList.add(new productSpec(color, size, number));
					}
				}
			}
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

			ProductDetails productDetails = resultList.get(position);

			hold.name.setText(productDetails.title);
			hold.price.setText(productDetails.price+ "");
			hold.number.setText(productDetails.number +"");

			ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + productDetails.imageUrl, hold.goodsImage);

			String shoppingName = productDetails.shoppingName;
			hold.shoppingName.setText(shoppingName);
			// 隐藏相同的商店名称
			if(position > 0){
				String lastName = infolist.get(position - 1).getShoppingName();
				if(lastName.equals(shoppingName)){
					hold.shoppingName.setVisibility(View.GONE);
					hold.shoppingName.setText(shoppingName);
				} else {
					hold.shoppingName.setVisibility(View.VISIBLE);
					hold.shoppingName.setText(shoppingName);
				}
			} else {
				hold.shoppingName.setVisibility(View.VISIBLE);
				hold.shoppingName.setText(shoppingName);
			}
			if(isShow && index == position){
				hold.spec_listview.setVisibility(View.VISIBLE);//显示规格listview
			} else {
				hold.spec_listview.setVisibility(View.GONE);//
			}

			specAdapter specAdapter = new specAdapter(index);
			setListViewHeightBasedOnChildren(hold.spec_listview);
			hold.spec_listview.setAdapter(specAdapter);

			// TODO 弹出详情
			hold.detail.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					index = position;
					isShow = !isShow;

					getData(index);

					myAdapter.notifyDataSetChanged();
				}
			});
			return view;
		}

	}

	class specAdapter extends BaseAdapter{

		int index;

		public  specAdapter(int index){
			this.index = index;
		}

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

	class ProductDetails{
		String title;
		String imageUrl;
		String shoppingName;
		int price;
		int number;
		public ProductDetails(String title, String imageUrl, String shoppingName, int price, int number) {
			super();
			this.title = title;
			this.imageUrl = imageUrl;
			this.shoppingName = shoppingName;
			this.price = price;
			this.number = number;
		}

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

				JSONObject object = getJson();

				Log.e("object4==",object.toString());

				submitOrder(object);

				break;

			default:
				break;
		}

	}

	//-----------------------拼接json------------------------
	private JSONObject getJson() {

		JSONObject object4 = new JSONObject();
		JSONArray array3 = new JSONArray();
		try {

			for (int i = 0; i < ordersArray.length(); i++) {
				JSONArray array = new JSONArray();

				JSONObject details = ordersArray.getJSONObject(i);

				String GOODSID = details.getString("GOODSID");
				String CM_SELLERID = details.getString("CM_SELLERID");

				JSONArray GOODSLIST = details.getJSONArray("GOODSLIST");

				for (int j = 0; j < GOODSLIST.length(); j++) {
					String color = GOODSLIST.getJSONObject(j).getString("COLOR");
					String id = GOODSLIST.getJSONObject(j).getString("GOODSDETAILSID");
					String specNumber = GOODSLIST.getJSONObject(j).getString("SPEC_NUMBER");

					JSONObject object = new JSONObject();
					object.put("GOODSDETAILSID", id);
					object.put("SPEC_NUMBER", specNumber);
					object.put("COLOR", color);
					array.put(j, object);
				}

				JSONObject object2 = new JSONObject();
				object2.put("CM_SELLERID", CM_SELLERID);
				object2.put("GOODSID", GOODSID);
				object2.put("GOODSDETAILS", array);
				JSONArray array2 = new JSONArray();



				array2.put(0, object2);

				JSONObject object3 = new JSONObject();
				object3.put("GOODSLIST", array2);


				array3.put(i, object3);
			}

//			JSONArray array3 = new JSONArray();
//			JSONObject object3 = new JSONObject();
//			object3.put("GOODSLIST", array2);
//
//			array3.put(0, object3);

//			final JSONObject object4 = new JSONObject();


			object4.put("INTEGRAL", 0);
			object4.put("BALANCE", "0");
			object4.put("ORDERS", array3);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object4;
	}

	//-------------------------提交订单------------------------------
	private void submitOrder(JSONObject object4) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", TCHConstants.url.token);// TODO
		params.put("goodsjson", object4.toString());// json

		HTTPUtils.post(FirmorderActivity.this, TCHConstants.url.SUBMITORDER,
				params, new ResponseListener() {

					@Override
					public void onResponse(String arg0) {
						try {
							JSONObject object = new JSONObject(arg0);

							Log.e("提交订单==", object.toString());

							int errocode = object.getInt("ErrorCode");
							if (errocode == 0) {
								String token1 = object.getString("Token");
								Log.e("token1==", token1);
								TCHConstants.url.token = token1;
								Log.e("TCHConstantoken==", TCHConstants.url.token);

								String result = object.getString("Result");
								// 截取订单和金额
								String orderid =result.substring(result.indexOf(":")+1,result.indexOf(","));
								String money = result.substring(result.lastIndexOf(":") + 1, result.length() - 1);
                                Log.e("orderid== " , orderid);
                                Log.e("money== " , money);
								TCHConstants.url.ordermoney=money ;
								Intent intent=new Intent(FirmorderActivity.this ,PayActivity.class);
								startActivity(intent);

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

						Log.e("arg0==", arg0.toString());


					}
				});


	}




}