package com.example.hanlian.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import collectionModel.Querycollection;
import collectionModel.Result;
import delecollectionModel.Deltecollection;
import utils.TCHConstants;

public class CollectionActivity extends Activity implements OnClickListener {
	private DisplayImageOptions options;
	private ListView collertionlistview;
	private CollectionMyAdapter collectionMyAdapter;
	private int pageNum = 1;
	private int pageSize = 0;
	ArrayList<Result> list = new ArrayList<Result>();
	private SharedPreferences sp;
	private TextView mtv_baby;
	private TextView mtv_store;
	private ImageView mTV_back;
	private JSONArray goodsList = new JSONArray();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
//		PushAgent.getInstance(this).onAppStart();
		getWindow().getAttributes().gravity = Gravity.RIGHT; // 居中
		// 查询收藏
		intiUI();
		intiUIL();
		intidata();

	}

	// @Override
	// protected void onDestroy() {
	// // TODO Auto-generated method stub
	// super.onDestroy();
	// ViewGroup parent =(ViewGroup) root.getParent ;
	// if(parent != null)
	// {
	// parent.removeAllViewsInLayout();
	// }
	//
	// }


	private void intidata() {

					// 查询收藏
		pageSize += 10;
		Map<String, String> parms = new HashMap<String, String>();
					Intent intent = getIntent();
					parms.put("pageNum", pageNum + "");
					parms.put("pageSize", pageSize + "");
					parms.put("token", TCHConstants.url.token);
					// TODO
					HTTPUtils.get(CollectionActivity.this, TCHConstants.url.QueryMyCollectionurl, parms,
							new ResponseListener() {
								@Override
								public void onResponse(String arg0) {
									// 后续添加 适配器变化
									Querycollection querycollection = GsonUtils.parseJSON(arg0, Querycollection.class);
									Integer errorCode = querycollection.getErrorCode();

									if (errorCode == 0) {
										List<Result> result = querycollection.getResult();
										list.clear();
										list.addAll(result);
										collertionlistview.setAdapter(collectionMyAdapter);
										collectionMyAdapter.notifyDataSetChanged();
									} else {

									}
								}
								@Override
								public void onErrorResponse(VolleyError arg0) {

								}
							});

	}
	private void intiUI() {
		mtv_baby = (TextView) findViewById(R.id.tv_baby);
		mtv_store = (TextView) findViewById(R.id.tv_store);
		mtv_baby.setOnClickListener(this);
		mtv_store.setOnClickListener(this);
		mTV_back = (ImageView) findViewById(R.id.img_collectionback);
		mTV_back.setOnClickListener(this);

		collertionlistview = (ListView) findViewById(R.id.listview_collection);
		// collertionlistview.setSelector(new ColorDrawable()); //
		collectionMyAdapter = new CollectionMyAdapter();
		collertionlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				final Dialog dialog = new Dialog(CollectionActivity.this, R.style.MyDialog);
				View view1 = LayoutInflater.from(CollectionActivity.this).inflate(R.layout.dialog_score2, null);
				dialog.setContentView(view1);
				((Button) view1.findViewById(R.id.no)).setText("取消");
				((Button) view1.findViewById(R.id.no)).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						if (dialog != null && dialog.isShowing()) {
							dialog.dismiss();
						}
					}
				});
				((Button) view1.findViewById(R.id.yes)).setText("确定");
				((Button) view1.findViewById(R.id.yes)).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						if (dialog != null && dialog.isShowing()) {
						//	DelMyCollection();
							Delollection();


							dialog.dismiss();
						}
					}
				});
				dialog.show();
			}
		});
	}

	private void Delollection() {

		Map<String, String> parm = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {

		//	String cmgoodsid = list.get(i).getCMGOODSID();
			String cmgoodsid = list.get(i).getCMGOODSID();


			parm.put("goodsid", cmgoodsid);
			parm.put("token", TCHConstants.url.token);
		}

		// 删除收藏
		HTTPUtils.get(CollectionActivity.this, TCHConstants.url.DelMyCollectionurl, parm,
				new ResponseListener() {

					@Override
					public void onResponse(String arg0) {
						Deltecollection Deltejson = GsonUtils.parseJSON(arg0, Deltecollection.class);
						Integer errorCode2 = Deltejson.getErrorCode();
						
						//// TODO: 2017/5/11  删除不返回token
//						String token = Deltejson.getToken();
//						TCHConstants.url.token=token;

						if (errorCode2 == 0) {
							intidata();
							Toast.makeText(CollectionActivity.this,"取消收藏", Toast.LENGTH_SHORT).show();
						}
					}
					@Override
					public void onErrorResponse(VolleyError arg0) {

					}
				});
	}

//	private void DelMyCollection() {
//		// 刷新测试token
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("account", account);
//		params.put("password", password);
//		HTTPUtils.get(CollectionActivity.this, TCHConstants.url.GETTESTTOKEN, params, new ResponseListener() {
//
//			@Override
//			public void onResponse(String arg0) {
//
//				TestToken parseJSON = GsonUtils.parseJSON(arg0, TestToken.class);
//				Integer errorCode = parseJSON.getErrorCode();
//				if (errorCode == 0) {
//					TestTkoen = parseJSON.getToken();
//					Map<String, String> parm = new HashMap<String, String>();
//
//					// String cmgoodsid = list.get(0).getCMGOODSID();
//					// String cmgoodsid = list.get().getCMGOODSID();
//					for (int i = 0; i < list.size(); i++) {
//
//						String cmgoodsid = list.get(i).getCMGOODSID();
//						parm.put("goodsid", cmgoodsid);
//						parm.put("token", token);
//					}
//
//					// 删除收藏
//					HTTPUtils.get(CollectionActivity.this, TCHConstants.url.DelMyCollectionurl, parm,
//							new ResponseListener() {
//
//								@Override
//								public void onResponse(String arg0) {
//									Deltecollection Deltejson = GsonUtils.parseJSON(arg0, Deltecollection.class);
//									Integer errorCode2 = Deltejson.getErrorCode();
//									if (errorCode2 == 0) {
//
//										intidata();
//
//										Toast.makeText(CollectionActivity.this,"取消收藏", Toast.LENGTH_SHORT).show();
//									}
//								}
//
//								@Override
//								public void onErrorResponse(VolleyError arg0) {
//
//								}
//							});
//
//				}
//			}
//
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//
//			}
//		});
//
//	}
























	class CollectionMyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// 后续添加动态界面
			return list.size();
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
				layout = getLayoutInflater().inflate(R.layout.collection_listviewitem, null);
				holder.img_colletion_item = (ImageView) layout.findViewById(R.id.img_colletion_item);
				holder.tv_colltion_tvtitle = (TextView) layout.findViewById(R.id.tv_colltion_tvtitle);
				holder.tv_colltion_shoujia = (TextView) layout.findViewById(R.id.tv_colltion_shoujia);
				layout.setTag(holder);
			} else {
				layout = convertView;
				holder = (viewHolder) layout.getTag();
			}

			String cmtitle = list.get(position).getCMTITLE();
			Integer cmsales = list.get(position).getCMSALES();
			Integer cmpresentprice = list.get(position).getCMPRESENTPRICE();
			String cmfigurespath = list.get(position).getCMMAINFIGUREPATH();
			holder.tv_colltion_tvtitle.setText(cmtitle);
			holder.tv_colltion_shoujia.setText("" + cmpresentprice);
			ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + cmfigurespath, holder.img_colletion_item,
					options);
//			try {
//				holder.tv_hot_title.setText(goodsList.getJSONObject(position).getString("CM_TITLE"));
//				holder.tv_hot_shoujia.setText(goodsList.getJSONObject(position).getString("CM_ORIGINALPRICE"));//售价
//				holder.tv_hot_salenumber.setText(goodsList.getJSONObject(position).getString("CM_SALES")); // 销量
//				ImageLoader.getInstance().displayImage(
//						TCHConstants.url.imgurl + goodsList.getJSONObject(position).getString("CM_MAINFIGUREPATH"),
//						holder.img_hot_gv_item, MyApplication.options);//图片地址
//
//
//
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}



			return layout;
		}

		class viewHolder {
			ImageView img_colletion_item;
			TextView tv_colltion_tvtitle;
			TextView tv_colltion_shoujia;
		}
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_store: // 店铺
			mtv_baby.setTextColor(getResources().getColor(R.color.black));
			mtv_store.setTextColor(getResources().getColor(R.color.red));
			collertionlistview.setVisibility(View.GONE);
			break;
		case R.id.tv_baby: // 宝贝
			mtv_baby.setTextColor(getResources().getColor(R.color.red));
			mtv_store.setTextColor(getResources().getColor(R.color.black));
			collertionlistview.setVisibility(View.VISIBLE);
			
			break;
		case R.id.img_collectionback:// 返回
			finish();
			break;

		default:
			break;
		}
	}

}
