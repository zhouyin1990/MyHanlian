package com.example.hanlian.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.NullDecoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.hanlian.Fragment.BannerItemFragment;
import com.example.hanlian.R;
import com.example.widget.MyListview;
import com.example.widget.MyView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import DetailsModle.CMOTHER;
import DetailsModle.Details;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import collectionModel.Joincollection;
import collectionModel.Querycollection;
import delecollectionModel.Deltecollection;
import utils.CardGoodsInfo;
import utils.DButils;
import utils.OnCheckChangeListener1;
import utils.OnNumberChangeListener;
import utils.TCHConstants;
import utils.Utils;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsDetailActivity extends FragmentActivity implements OnClickListener {

	private TextView tv_goods_title;
	private TextView tv_goods_price;
	private TextView tv_goods_kinds;
	private static final int DELAY_MILINS = 4000;
	private static final int MAX_LENGTH = 4000000;
	protected static final long DURATION = 3000;
	private ViewPager mPager;
	private MyView myview;
	protected boolean isDragging;
	private int realnum = 5;
	private Runnable action;
	private ImageAdapter adapter;
	private PopupWindow popupWindow;
	private View parent;
	private ImageView image_collection;
	boolean islike = false;
	private String[] splits; //轮播图


	ArrayList<String[]> detailsplitslist = new ArrayList<String[]>();
	private String[] split2 ;//详情

//  12
	private String[] split3  ;
//  21


	private String cmfigurespath;
	private int screenWidth;
	private int width;
	private OnchangeListen onchangelisten;
	private ArrayList<CMOTHER> cmotherlist = new ArrayList<CMOTHER>();
	private ArrayList<String[]> splitslist = new ArrayList<String[]>();
	private ArrayList<DetailsModle.Result> resultList = new ArrayList<DetailsModle.Result>(); //详情
	ArrayList<collectionModel.Result> queryresultlist = new ArrayList<collectionModel.Result>();
	private SharedPreferences sp;
	private int pageNum = 1;
	private int pageSize = 0;
	private String TestTkoen ;
	private boolean isCart = false;//true--加入购物车   false--立即购买
	private TextView pop_count;
	private int pop_number;// 一手进货数量
	private boolean isChangeAll;// 是否选中所有
	private boolean isChangeJia;// 加
	private boolean isChangeJian;// 减
	private SizeAdpeter sizeAdpeter;
	private JSONObject object;
//	存放详情图片地址
	private   ArrayList<String> arrayList = new ArrayList<>();
//	private MyListview listview_detail;
	private RecyclerView recyclerView;
	private Myrecviewadapter myrecviewadapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_detail);
//		PushAgent.getInstance(this).onAppStart();
		initUI();
		initData();
		iscollection1();// 进入时候判断是否收藏
	}

	@Override
	protected void onResume() {
		super.onResume();
		pop_number = 0;
		isChangeAll = false;
		isChangeJia = false;
		isChangeJian = false;
		map2.clear();
	}
	private void initData() {
//		HashMap<String, String> params = new HashMap<String , String >();
//		params.put("account",account);
//		params.put("password",password);
		Map<String, String> detailparms = new HashMap<String, String>();
		final Intent intent = getIntent();
		goodsid = intent.getStringExtra("goodsid");
	//	String token = sp.getString("Token","");


		detailparms.put("goodsid",goodsid.toString().trim());
		detailparms.put("token",TCHConstants.url.token.toString().trim());
		HTTPUtils.get(GoodsDetailActivity.this, TCHConstants.url.detailsurl, detailparms, new ResponseListener()
		{
			@Override
			public void onErrorResponse(VolleyError arg0) {
			}
			@Override
			public void onResponse(String arg0) {
				Log.e("arg0=",arg0);


				Details detailsjson = GsonUtils.parseJSON(arg0, Details.class);
				DetailsModle.Result result = detailsjson.getResult();

				if(result!=null)
				{
					resultList.clear();
					resultList.add(result);
					cmfigurespath = result.getCMFIGURESPATH();
					Integer price = result.getCMPRESENTPRICE();
					Integer salesnumber = result.getCMSALES();
					String cmtitle = result.getCMTITLE();
					List<CMOTHER> cmother = result.getCMOTHER();
					cmotherlist.addAll(cmother);
					tv_goods_title.setText(cmtitle);
					tv_goods_price.setText("¥" +price);
					splits = cmfigurespath.split("\\|"); //分割图片
					String cmhtml = result.getCMHTML();

					Log.e("cm_chtml",cmhtml+"");

					if(cmhtml!=null)
					{
						String[] detailsplit = cmhtml.split("\"");
						for (int i = 0; i < detailsplit.length; i++) {
							if (detailsplit[i].length() > 25) {
								arrayList.add(detailsplit[i]);
							}
						}

					}
					Log.e("arrayListsize==",arrayList.size()+"");
					splitslist.clear();
					splitslist.add(splits);
					myview.setRealNum(splitslist.get(0).length);
					mPager.setAdapter(new BannerAdapter(getSupportFragmentManager()));

//                    //详情c-tu  // TODO: 2017/5/4 切割字符串
//					DetailBean detailBean = GsonUtils.parseJSON(arg0, DetailBean.class);
//					DetailBean.ResultBean result1 = detailBean.getResult();
//					String cm_chtml = result1.getCM_HTML();

//					Log.e("cm_chtml",cm_chtml);


//					String spl = "<br />\n";
//					//将字符串末尾那个分隔符去掉
////					String substring = cm_chtml.substring(0, cm_chtml.length() - 6);
////
////					split2 = substring.split("<br />\n");
////
////					for (int i = 0; i < split2.length; i++) {
////						//以问好作为分隔符 问好是特殊字符  需要两个斜杠
////						split3 = split2[i].split("\\?");
////
////						detailsplitslist.clear();
////						detailsplitslist.add(split3);
////
////					}

					//// TODO: 2017/6/26
//					listview_detail.setAdapter(adapter);
//					setListViewHeightBasedOnChildren(listview_detail);
//					adapter.notifyDataSetChanged();


					myrecviewadapter.notifyDataSetChanged();

					initPopWindow();
				}
			}
		});

	}

//	private void iscollection()
//	{		// 刷新token
//		HashMap<String, String> params = new HashMap<String , String >();
//		params.put("account", account);
//		params.put("password", password);
//
//		HTTPUtils.get(GoodsDetailActivity.this, TCHConstants.url.GETTESTTOKEN, params, new ResponseListener() {
//
//			@Override
//			public void onResponse(String arg0) {
//				TestToken parseJSON = GsonUtils.parseJSON(arg0, TestToken.class);
//				Integer errorCode = parseJSON.getErrorCode();
//				if(errorCode==0)
//				{
//					TestTkoen = parseJSON.getToken();
//					if( !"".equals(TestTkoen))
//					{
//						pageSize += 10;
//						Map<String, String> queryparms = new HashMap<String, String>();
//						Intent intent = getIntent();
//						goodsid = intent.getStringExtra("goodsid");
//						queryparms.put("pageNum",pageNum +"");
//						queryparms.put("pageSize",pageSize+"");
//						queryparms.put("token",TestTkoen);
//						//查询
//						HTTPUtils.get(GoodsDetailActivity.this, TCHConstants.url.QueryMyCollectionurl, queryparms, new ResponseListener() {
//							@Override
//							public void onResponse(String arg0) {
//								Querycollection querycollection = GsonUtils.parseJSON(arg0, Querycollection.class);
//								Integer errorCode = querycollection.getErrorCode();
//								List<collectionModel.Result> queryresult = querycollection.getResult();
//								if(queryresult!=null &&queryresult.size() !=0)
//								{
//									queryresultlist.clear();
//									queryresultlist.addAll(queryresult);
//									for (int i = 0; i < queryresultlist.size(); i++)
//									{
//										String cmgoodsid = queryresult.get(i).getCMGOODSID();
//										if (!"".equals(goodsid.toString().trim())){
//											if(cmgoodsid.equals(goodsid.toString().trim())){
//												islike=true ;
//												image_collection.setImageResource(R.drawable.like);//以收藏
//											}
//										}
//									}
//								}
//							}
//
//							@Override
//							public void onErrorResponse(VolleyError arg0) {
//							}
//						});
//					} else {
//
//					}
//				}
//			}
//
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//			}
//		});
//
//	}


	private void iscollection1()
	{
		pageSize += 10;
		Map<String, String> queryparms = new HashMap<String, String>();
		Intent intent = getIntent();
		goodsid = intent.getStringExtra("goodsid");
		queryparms.put("pageNum",pageNum +"");
		queryparms.put("pageSize",pageSize+"");
		queryparms.put("token",TCHConstants.url.token);
		//查询
		HTTPUtils.get(GoodsDetailActivity.this, TCHConstants.url.QueryMyCollectionurl, queryparms, new ResponseListener() {
			@Override
			public void onResponse(String arg0) {
				Querycollection querycollection = GsonUtils.parseJSON(arg0, Querycollection.class);
//				Integer errorCode = querycollection.getErrorCode();
				List<collectionModel.Result> queryresult = querycollection.getResult();
				if(queryresult!=null &&queryresult.size() !=0)
				{
					queryresultlist.clear();
					queryresultlist.addAll(queryresult);
					for (int i = 0; i < queryresultlist.size(); i++)
					{
						String cmgoodsid = queryresult.get(i).getCMGOODSID();
						if (!"".equals(goodsid.toString().trim())){
							if(cmgoodsid.equals(goodsid.toString().trim())){
								islike=true ;
								image_collection.setImageResource(R.drawable.like);//以收藏
							}
						}
					}
				}
			}
			@Override
			public void onErrorResponse(VolleyError arg0) {
			}
		});
	}

	private void initUI() {
		sp = getSharedPreferences("登录",1);



//
//		String token = "9FEE28405425AADE99BFA37911157205269372F175897"
//				+ "876FC97C9A11928AE7F204AC60AF90FC344068F0D30420FCB27";
//
//		sp.edit().putString("token1", token).commit();
//		sp.edit().putString("token2", token).commit();

		// 返回
		TextView tv_detail_back = (TextView) findViewById(R.id.tv_detail_back);
		tv_detail_back.setOnClickListener(this);
		image_collection = (ImageView) findViewById(R.id.img_collection);
		image_collection.setOnClickListener(this);

		// 商品标题
		tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
		// 价格
		tv_goods_price = (TextView) findViewById(R.id.tv_goods_price);
		// 分类
		tv_goods_kinds = (TextView) findViewById(R.id.tv_goods_kinds);
		re_kinds = (RelativeLayout) findViewById(R.id.re_kinds);
		re_kinds.setOnClickListener(this);

		//直接购买
		TextView tv_pay = (TextView) findViewById(R.id.tv_pay);
		tv_pay.setOnClickListener(this);
		// 加入购物车
		TextView tv_addCar = (TextView) findViewById(R.id.tv_addCar);
		tv_addCar.setOnClickListener(this);
		// 图片信息的listview
//		listview_detail = (MyListview) findViewById(R.id.listview_detail);
       //todo recviewadr

		recyclerView = (RecyclerView)findViewById(R.id.recycleview);
		recyclerView.setLayoutManager(new LinearLayoutManager(GoodsDetailActivity.this,LinearLayoutManager.VERTICAL,false));
		((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

		myrecviewadapter = new Myrecviewadapter();
		recyclerView.setAdapter(myrecviewadapter);



		adapter = new ImageAdapter();


		// 初始化scrollview
		ScrollView scrollview = (ScrollView) findViewById(R.id.scrollview);
		scrollview.smoothScrollTo(0, 0);

		parent = findViewById(R.id.RelativeLayout1);

		mPager = (ViewPager) findViewById(R.id.pager);
		myview = (MyView) findViewById(R.id.myView1);
		FragmentManager fm = getSupportFragmentManager();

		mPager.setCurrentItem(MAX_LENGTH / 2);
		mPager.addOnPageChangeListener(new OnPageChangeListener() {

			public void onPageScrollStateChanged(int state) {
				switch (state) {
					case ViewPager.SCROLL_STATE_IDLE:
						isDragging = false;
						break;
					case ViewPager.SCROLL_STATE_DRAGGING:
						isDragging = true;
						break;
					case ViewPager.SCROLL_STATE_SETTLING:
						isDragging = false;
						break;
					default:
						break;
				}
			}
			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

				if (splitslist.size() != 0) {
					String[] splits = splitslist.get(0);
					if (splits != null) {
						if (splits.length != 0) {
							myview.move(arg0 %= splits.length, arg1);
						}
					}
				}
			}
		});
		autoScroll();
	}




 class Myrecviewadapter  extends RecyclerView.Adapter<Myrecviewholder>
 {


	 @Override
	 public Myrecviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
		 View inflate = getLayoutInflater().inflate(R.layout.layout_item_detail, null);
		 Myrecviewholder holder = new Myrecviewholder(inflate);

		 return holder;
	 }

	 @Override
	 public void onBindViewHolder(Myrecviewholder holder, int position) {
		 //更新数据
		 String s = arrayList.get(position);
		 holder.setimg(s);

	 }

	 @Override
	 public int getItemCount() {
		 return arrayList.size();
	 }
 }


class Myrecviewholder extends  RecyclerView.ViewHolder
{


	private final ImageView img;

	public Myrecviewholder(View itemView) {
		super(itemView);


		  img = (ImageView) itemView.findViewById(R.id.image_goods);

	}

	public void  setimg (String s)
	{
		Glide.with(GoodsDetailActivity.this).load(s).into(img);
	}

}














	// 轮播图适配器
	class BannerAdapter extends FragmentPagerAdapter {

		public BannerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (splitslist.get(0).length != 0) {
				position %= splitslist.get(0).length;
				BannerItemFragment fragment = new BannerItemFragment(position);
				Bundle bundle = new Bundle();
				String[] splits = splitslist.get(0);
				bundle.putString("imgurl", splits[position]);
				fragment.setArguments(bundle);
				return fragment;
			}
			return null;
		}

		@Override
		public int getCount() {
			return MAX_LENGTH;
		}
	}

	private void autoScroll() {

		action = new Runnable() {
			public void run() {
				mPager.postDelayed(this, DELAY_MILINS);
				if (!isDragging && mPager != null) {
					int currenItem = mPager.getCurrentItem();
					int nextItem = currenItem + 1;
					mPager.setCurrentItem(nextItem);
				}
			}
		};
		mPager.postDelayed(action, DELAY_MILINS);
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mPager.removeCallbacks(action);
	}

	// 图片信息适配器 		TODO
	class ImageAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = null;
			ViewHolder hold = null;
			if (convertView == null) {
				hold = new ViewHolder();
				layout = getLayoutInflater().inflate(R.layout.layout_item_detail, null);
				hold.image_goods = (ImageView) layout.findViewById(R.id.image_goods);
				layout.setTag(hold);
			} else {
				layout = convertView;
				hold = (ViewHolder) layout.getTag();
			}

            hold.image_goods.setTag(arrayList.get(position));
            hold.image_goods.setImageResource(R.drawable.err);

//            // 通过 tag 来防止图片错位
//            if (hold.image_goods.getTag() != null && hold.image_goods.getTag().equals(arrayList.get(position))) {
//                hold.image_goods.setImageBitmap(result);
//            }
            if (arrayList.size() != 0) {
				if(!TextUtils.isEmpty(arrayList.get(position)))
				{
//					ImageLoader.getInstance().displayImage(arrayList.get(position), hold.image_goods, MyApplication.options);
                    // 通过 tag 来防止图片错位
                    if (hold.image_goods.getTag() != null && hold.image_goods.getTag().equals(arrayList.get(position))) {
                        Glide.with(GoodsDetailActivity.this).load(arrayList.get(position)).dontAnimate().placeholder(R.drawable.ic_empty);
                    }

					Glide.with(GoodsDetailActivity.this).load(arrayList.get(position)).dontAnimate().placeholder(R.drawable.ic_empty);
				}

				final ViewHolder finalHold = hold;
				Glide.with(GoodsDetailActivity.this).load(arrayList.get(position)).asBitmap().into(new SimpleTarget<Bitmap>() {
					@Override
					public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
						finalHold.image_goods.setImageBitmap(resource);
					}

					@Override
					public void onLoadFailed(Exception e, Drawable errorDrawable) {
						super.onLoadFailed(e, errorDrawable);
//						LogUtil.e("LLpp:加载失败："+resultBean.pic+" title:"+resultBean.title);
						finalHold.image_goods.setVisibility(View.GONE);
					}
				});

			}
			return layout;
		}

		@Override
		public int getCount() {
			// TODO1
			return  arrayList.size();
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
		ImageView image_goods;
	}



	/**
	 * scrollview嵌套listview显示不全 解决
	 *
	 * @param listview_detail
	 */
	public static void setListViewHeightBasedOnChildren(MyListview listview_detail) {
		ListAdapter listAdapter = listview_detail.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listview_detail);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listview_detail.getLayoutParams();
		params.height = totalHeight
				+ (listview_detail.getDividerHeight() * (listAdapter.getCount() - 1));
		listview_detail.setLayoutParams(params);
	}






	private int totalCount = 0;//购买的总量
	private TextView tv_sure, tv_totalprice, tv_goods_price1;
	private RelativeLayout re_kinds;
	private String arg0;
	//	private int count;
	private Map<Integer, int[]> map = new HashMap<Integer, int[]>();//Integer 选中颜色的poistion  int[] 数量的数组
	private Map<Integer, int[]> map2 = new HashMap<Integer, int[]>();//Integer 选中颜色的poistion  int[] 数量的数组
	private Map<String, int[]> mapData = new HashMap<String, int[]>();//选中颜色  int[] 数量的数组
	//	private String size;
	private ArrayList<String> strlist = new ArrayList<String>();
	private ArrayList<String> strlistData = new ArrayList<String>();
	private ArrayList<ArrayList<String>> strlist2 = new ArrayList<ArrayList<String>>();
	private ArrayList<Integer> cmidlist = new ArrayList<Integer>();
	private ArrayList<String> cmidlistColor = new ArrayList<String>();

	// TODO 初始化点击购物车时候的弹窗
	private void initPopWindow() {
		View view = getLayoutInflater().inflate(R.layout.layout_pop_addcar, null);
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		Drawable background = new BitmapDrawable();//
		popupWindow.setFocusable(true);// 是为了解决，当点击其他位罢的时候，不关闭
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(background);
		ImageView image_goods = (ImageView) view.findViewById(R.id.image_goods);
		tv_goods_price1 = (TextView) view.findViewById(R.id.tv_goods_price);
		TextView tv_goods_code = (TextView) view.findViewById(R.id.tv_goods_code);
		tv_sure = (TextView) view.findViewById(R.id.tv_sure);
		tv_totalprice = (TextView) view.findViewById(R.id.tv_totalprice);
		view.findViewById(R.id.pop_confirm).setOnClickListener(this);
		ImageView image_close = (ImageView) view.findViewById(R.id.image_close);
		image_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});

		view.findViewById(R.id.pop_all).setOnClickListener(this);
		view.findViewById(R.id.pop_jia).setOnClickListener(this);
		view.findViewById(R.id.pop_jian).setOnClickListener(this);
		pop_count = (TextView) view.findViewById(R.id.pop_count);

		linearlayout_bottom2 = view.findViewById(R.id.linerlayout_wancheng);
		linearlayout_bottom2.setVisibility(View.GONE);
		linearlayout_bottom2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				linearlayout_bottom2.setVisibility(View.GONE);
				popupWindow.dismiss();
			}
		});

		ListView listView_choose = (ListView) view.findViewById(R.id.listView_choose);

		width = listView_choose.getWidth();
		cmtitle = resultList.get(0).getCMTITLE();
		// 商家id
		cmsellerid = resultList.get(0).getCMSELLERID();
		// 商家名称
		cmsellername = resultList.get(0).getCMSELLERNAME();
		// 商品id
		cmgoodsid = resultList.get(0).getCMGOODSID();
		tv_goods_code.setText(cmgoodsid);
		// 价格
		cmsales = resultList.get(0).getCMPRESENTPRICE();
		tv_goods_price1.setText("¥" + cmsales +"");
		// 图片路径
		path = resultList.get(0).getCMMAINFIGUREPATH();
		ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + path, image_goods);
		colorAdapter = new ColorAdapter();
		listView_choose.setAdapter(colorAdapter);

		colorAdapter.setOncheckchangelistener(new OnCheckChangeListener1() {
			@Override
			public void onCheckChange(int[] num, int position, boolean check) {
				if (check) {
					map.put(position, num);
					map2.put(position, num);// 保存数据
					map2Data();
				} else {
					map.remove(position);
					map2.remove(position);
					map2Data();
				}
			}
		});
	}

	int number;//各颜色的顺序下标
	// 加入购物车
	private void addCart(){
		JSONArray array = new JSONArray();

		int toalcount = 0;
		if (map != null && map.size() != 0) {
			Iterator<Entry<Integer, int[]>> it = map.entrySet().iterator();
			strlist2.clear();
			number = 0;

			while (it.hasNext()) {

				Entry<Integer, int[]> entry = it.next();
				String size;
				int count;

				Integer key = entry.getKey();
				int[] value = entry.getValue();
				// cmotherlist颜色 ； sizelist尺寸；
				String cmcolor = cmotherlist.get(key).getCMCOLOR();
				int cmgoodsdetailsid = cmotherlist.get(key).getCMGOODSDETAILSID();

				cmidlist.add(cmgoodsdetailsid);// 各颜色商品id
				cmidlistColor.add(cmcolor);// 各颜色商品
				strlist.clear();
				for (int i = 0; i < value.length; i++) {
					size = sizelist.get(i);
					if (value.length != 0) {
						count = value[i];
						toalcount += count;
						strlist.add(size + "_" + count);
					}
				}

				String str ="";
				for (int j = 0; j < strlist.size(); j++) {
					//拼接商品的尺寸和数量
					str += strlist.get(j) + "|";
				}

				JSONObject object = new JSONObject();
				try {
					object.put("GOODSDETAILSID", cmidlist.get(number));
					object.put("SPEC_NUMBER", str);
					object.put("COLOR",cmcolor );
					array.put(number, object);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				number ++;
			}
			JSONArray array2 = new JSONArray();
			JSONObject object2 = new JSONObject();
			try {
				object2.put("CM_SELLERID", resultList.get(0).getCMSELLERID());
				object2.put("GOODSID", resultList.get(0).getCMGOODSID());
				object2.put("GOODSDETAILS", array);
				array2.put(0, object2);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			JSONObject object3 = new JSONObject();
			try {
				object3.put("GOODSLIST", array2);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			CardGoodsInfo cardGoodsInfo = new CardGoodsInfo();
			cardGoodsInfo.setGoodsid(cmgoodsid);
			cardGoodsInfo.setTitle(cmtitle);
			cardGoodsInfo.setImgURL(path);
			cardGoodsInfo.setShoppingID(cmsellerid);
			cardGoodsInfo.setShoppingName(cmsellername);
			cardGoodsInfo.setTotalprice(toalcount * cmsales);
			cardGoodsInfo.setCount(toalcount);
			cardGoodsInfo.setGOODSLIST(object3.toString());
			Log.e("cardGoodsInfo==",cardGoodsInfo.toString());

			DButils.insert(cardGoodsInfo);
			Toast.makeText(GoodsDetailActivity.this, "成功添加购物车", Toast.LENGTH_SHORT).show();
			linearlayout_bottom2.setVisibility(View.VISIBLE);
		} else {
			Toast.makeText(GoodsDetailActivity.this, "请选择颜色", Toast.LENGTH_SHORT).show();
		}
	}

	private JSONObject object1;
	private Integer key;
	//立即支付
	private void setPay(){
		strlist.clear();
		strlistData.clear();
		cmidlist.clear();
		cmidlistColor.clear();
		if (map != null && map.size() != 0   ) {
			Iterator<Entry<Integer, int[]>> it = map.entrySet().iterator();

			while (it.hasNext()) {
				String size;
				int count;
				Entry<Integer, int[]> entry = it.next();
				key = entry.getKey();
				int[] value = entry.getValue();
				// cmotherlist颜色 ； sizelist尺寸；
				String cmcolor = cmotherlist.get(key).getCMCOLOR();
				int cmgoodsdetailsid = cmotherlist.get(key).getCMGOODSDETAILSID();
				cmidlist.add(cmgoodsdetailsid);
				cmidlistColor.add(cmcolor);
				strlist.clear();
				for (int i = 0; i < value.length; i++) {
					size = sizelist.get(i);
					count = value[i];
					strlist.add(size + "_" + count);
				}
				String str ="";
				for (int j = 0; j < strlist.size(); j++) {
					//拼接商品的尺寸和数量
					str += strlist.get(j) + "|";
				}
				strlistData.add(str);
			}
			// TODO 得到拼接好的json
			final JSONObject object4 = getJson();
			submitOrder(object4,TCHConstants.url.token);

		} else {
			Toast.makeText(GoodsDetailActivity.this, "请选择颜色和尺寸", Toast.LENGTH_SHORT).show();
		}
	}

	//-----------------------拼接json------------------------
	private JSONObject getJson() {
		JSONArray array = new JSONArray();

		for (int i = 0; i < cmidlist.size(); i++) {

			JSONObject object = new JSONObject();
			try {
				object.put("GOODSDETAILSID", cmidlist.get(i));
				object.put("SPEC_NUMBER", strlistData.get(i));
				object.put("COLOR", cmidlistColor.get(i));
				array.put(i, object);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		JSONArray array2 = new JSONArray();

		JSONObject object2 = new JSONObject();
		try {
			object2.put("CM_SELLERID", resultList.get(0).getCMSELLERID());
			object2.put("GOODSID", resultList.get(0).getCMGOODSID());
			object2.put("GOODSDETAILS", array);
			array2.put(0, object2);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONArray array3 = new JSONArray();
		JSONObject object3 = new JSONObject();
		try {
			object3.put("GOODSLIST", array2);

			array3.put(0, object3);//0 JSONArray的第一个元素
		} catch (JSONException e) {
			e.printStackTrace();
		}

		final JSONObject object4 = new JSONObject();

        // todo
		JSONArray array4 = new JSONArray();
		JSONArray array5=new JSONArray() ;

		try {
			object4.put("INTEGRAL", array4);
			object4.put("ORDERS", array3);
			object4.put("BALANCE", "0");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object4;
	}

	//-------------------------提交订单------------------------------
	private void submitOrder(final JSONObject object4, String token) {

//		String token2 = sp.getString("token1", "");

		Map<String, String> params = new HashMap<String, String>();
		params.put("token", TCHConstants.url.token);//TODO
		params.put("goodsjson", object4.toString());// json
//		params.put("source", "1");// 手机端
//		params.put("paytype", "1");// 支付方式
		// 暂时写1
		// 支付宝

		HTTPUtils.post(GoodsDetailActivity.this, TCHConstants.url.SUBMITORDER,
				params, new ResponseListener() {
					@Override
					public void onResponse(String arg0) {
						Log.e("======submitOrder====", arg0);
						try {
							JSONObject object = new JSONObject(arg0);
							int errocode = object.getInt("ErrorCode");
							if (errocode == 0) {

								String token1 = object.getString("Token");
								TCHConstants.url.token=token1;
								String result = object.getString("Result");
								// 截取订单号
//								String orderid = result.substring(9,27);
								String orderid =result.substring(result.indexOf(":")+1,result.indexOf(","));

								// 截取 订单金额
								String money = result.substring(result.lastIndexOf(":") + 1, result.length() - 1);
								TCHConstants.url.orderid=orderid;
								TCHConstants.url.ordermoney=money;
								Log.e("orderid=",orderid);
								Log.e("money=",money);
								//TODO 提交成功跳转到确认订单页
								Intent intent = new Intent(GoodsDetailActivity.this, DetailFirmorderActivity.class);
								intent.setAction("action");
								intent.putExtra("order", object4.toString());
								intent.putExtra("resultList",resultList);
								startActivity(intent);

							} else {
								Log.e("ErrorCode = ",""+ errocode);
								Toast.makeText(GoodsDetailActivity.this ,"商品信息错误" ,Toast.LENGTH_SHORT).show();

							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("======1====", "onErrorResponse");
					}
				});
	}


	// 颜色适配器
	class ColorAdapter extends BaseAdapter {
		private OnCheckChangeListener1 oncheckchangelistener;

		public OnCheckChangeListener1 getOncheckchangelistener() {
			return oncheckchangelistener;
		}

		public void setOncheckchangelistener(OnCheckChangeListener1 oncheckchangelistener) {
			this.oncheckchangelistener = oncheckchangelistener;
		}

		@Override
		public int getCount() {
			return cmotherlist.size();
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
			View layout = null;
			final ViewHold hold;

			if (convertView == null) {
				hold = new ViewHold();
				layout = getLayoutInflater().inflate(R.layout.layout_item_color, null);
				hold.tv_goods_color = (TextView) layout.findViewById(R.id.tv_goods_color);
				hold.recycleview = (RecyclerView) layout.findViewById(R.id.recycleview);
				hold.strlist = new ArrayList<String[]>();
				hold.ischeck = false;
				if (cmotherlist.size() != 0) {
					CMOTHER cmother = cmotherlist.get(position);
					String cmspecstock = cmother.getCMSPECSTOCK();
					String[] split = cmspecstock.split("\\|");
					hold.kucun = new int[split.length];
					hold.change = new int[split.length];
				}
				layout.setTag(hold);
			} else {
				layout = convertView;
				hold = (ViewHold) layout.getTag();
			}

			if (cmotherlist.size() != 0) {
				CMOTHER cmother = cmotherlist.get(position);
				String cmspecstock = cmother.getCMSPECSTOCK();
				String[] split = cmspecstock.split("\\|");
				hold.strlist.add(split);
			}

			final String cmcolor = cmotherlist.get(position).getCMCOLOR();
			hold.tv_goods_color.setText(cmcolor + ":0");
			hold.tv_goods_color.setBackgroundDrawable(null);
			oncheckchangelistener.onCheckChange(hold.change, position, false);
			hold.tv_goods_color.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					if (!hold.ischeck) {
						hold.tv_goods_color.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg));
						oncheckchangelistener.onCheckChange(hold.change, position, true);
						hold.ischeck = true;

					} else {
						hold.tv_goods_color.setBackgroundDrawable(null);
						hold.ischeck = false;
						oncheckchangelistener.onCheckChange(hold.change, position, false);

					}

				}
			});

			if(map2.size() > 0 && isChangeAll){

				Iterator<Entry<Integer, int[]>> it = map2.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Integer, int[]> entry = it.next();

					Integer key = entry.getKey();
					int[] value = entry.getValue();
					String color = cmotherlist.get(key).getCMCOLOR();

					if(cmcolor.equals(color)){

						hold.tv_goods_color.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg));
						oncheckchangelistener.onCheckChange(hold.change, position, true);
						hold.ischeck = true;
					}
				}
			}


			if(isChangeAll){
				hold.tv_goods_color.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg));
				oncheckchangelistener.onCheckChange(hold.change, position, true);
				hold.ischeck = true;
			}

			// 设置布局管理器
			final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GoodsDetailActivity.this);
			linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			hold.recycleview.setLayoutManager(linearLayoutManager);

			sizeAdpeter = new SizeAdpeter(hold.tv_goods_color, cmcolor, hold.strlist, hold.kucun, hold.change);
			hold.recycleview.setAdapter(sizeAdpeter);
			// 自动滑动到底部
			if(isChangeAll){
				hold.recycleview.smoothScrollToPosition(hold.strlist.get(0).length);
			}

			sizeAdpeter.setOnNumberChangeListener(new OnNumberChangeListener() {

				@Override
				public void onNumberChange(int[] num) {
					hold.change = num;
				}
			});

			return layout;
		}

	}

	class ViewHold {
		TextView tv_goods_color;
		RecyclerView recycleview;
		ArrayList<String[]> strlist;
		boolean ischeck;
		int[] kucun;
		int[] change;
	}
	private ArrayList<String> sizelist = new ArrayList<String>();
	private String cmsellerid;
	private String cmsellername;
	private String cmgoodsid;
	private Integer cmsales;// 价格
	private String path;// 图片路径
	private String cmtitle;
	private View linearlayout_bottom2;
	private String goodsid;
	private String goodsid2;

	// 尺寸的适配器
	class SizeAdpeter extends RecyclerView.Adapter<SizeAdpeter.Holder> {
		int changeNum;// 各颜色选择数量
		int index = -1;
		int i;
		private int[] counts;
		private int[] total;

		private OnNumberChangeListener onNumberChangeListener;
		private ArrayList<String[]> strlist;
		private int[] kucun;
		private int[] change;
		private int position;
		private TextView goods_color;
		private String cmcolor;
		private int[] myNumber = new int[10];//选中颜色的各样式数量

		public SizeAdpeter(TextView goods_color,String cmcolor, ArrayList<String[]> strlist, int[] kucun, int[] change) {
			this.strlist = strlist;
			this.kucun = kucun;
			this.change = change;
			this.goods_color = goods_color;
			this.cmcolor = cmcolor;
		}

		public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
			this.onNumberChangeListener = onNumberChangeListener;
		}

		class Holder extends android.support.v7.widget.RecyclerView.ViewHolder {

			private TextView tv_size, tv_jian, tv_jia;
			private TextView et_count;
			public Holder(View itemView) {
				super(itemView);
				tv_size = (TextView) itemView.findViewById(R.id.tv_size);
				tv_jian = (TextView) itemView.findViewById(R.id.tv_jian);
				tv_jia = (TextView) itemView.findViewById(R.id.tv_jia);
				et_count = (TextView) itemView.findViewById(R.id.et_count);
				String[] strings = strlist.get(0);
				for (int i = 0; i < strings.length; i++) {
					String str = strings[i];
					String[] split = str.split("_");
					kucun[i] = Integer.parseInt(split[1]);
					sizelist.add(split[0]);

				}
				onNumberChangeListener.onNumberChange(change);
			}

		}

		@Override
		public int getItemCount() {
			return strlist.get(0).length;
		}

		@Override
		public void onBindViewHolder(final Holder hold, final int position) {

			String[] strings = strlist.get(0);
			String str = strings[position];
			String[] split = str.split("_");
			hold.tv_size.setText(split[0]);// 尺寸
			hold.et_count.setText("0");

			if(pop_number > 0){
				if(isChangeJia){
					hold.et_count.setText("" + (pop_number - 1));
				} else {
					hold.et_count.setText("" + (pop_number + 1));
				}
			} else {
				hold.et_count.setText("" + pop_number);
			}

			if(isChangeAll){

				if(isChangeJia){
					if(change[position] < kucun[position]){// 小于库存
//						change[position] = change[position] + pop_number;

						int k = kucun[position];
						String xianshi = hold.et_count.getText().toString().trim();// 显示的数量
						int x = Integer.parseInt(xianshi);

						if (x < k) {
							// 当显示数量小于库存 可以继续增加
							x += 1;
							change[position] = x;
							myNumber[position] = x;
							hold.et_count.setText(x +"");
							if (onNumberChangeListener != null) {
								onNumberChangeListener.onNumberChange(change);
							}

						} else {
							Toast.makeText(GoodsDetailActivity.this, "超出库存", Toast.LENGTH_SHORT).show();
						}

						changeNum = 0;
						for (int i = 0; i < change.length; i++) {
							changeNum = changeNum + change[i];
							goods_color.setText(cmcolor + ":" +changeNum);
						}

					}
				} else {
					if(change[position] > 0){// 不能小于0
//						change[position] = change[position] - 1;
					}

					String xianshi = hold.et_count.getText().toString().trim();//显示的数量
					int x = Integer.parseInt(xianshi);
					if (x > 0) {
						x -= 1;
						change[position] = x;
						hold.et_count.setText(x + "");

						if (onNumberChangeListener != null) {
							onNumberChangeListener.onNumberChange(change);
						}
					}

					if (x == 0) {
						change[position] = x;
						hold.et_count.setText(x + "");

						if (onNumberChangeListener != null) {
							onNumberChangeListener.onNumberChange(change);
						}
					}





					changeNum = 0;
					for (int i = 0; i < change.length; i++) {
						changeNum = changeNum + change[i];
						goods_color.setText(cmcolor + ":" + changeNum);
					}
				}

			}

			hold.tv_jia.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int k = kucun[position];

					String xianshi = hold.et_count.getText().toString().trim();// et
					// 显示的数量
					int x = Integer.parseInt(xianshi);
					if (x < k) {
						// 当显示数量小于库存 可以继续增加
						x += 1;
						change[position] = x;
						myNumber[position] = x;
						hold.et_count.setText(x +"");
						if (onNumberChangeListener != null) {
							onNumberChangeListener.onNumberChange(change);
						}

					} else {
						Toast.makeText(GoodsDetailActivity.this, "超出库存", Toast.LENGTH_SHORT).show();
					}
					changeNum = 0;
					for (int i = 0; i < change.length; i++) {
						changeNum = changeNum + change[i];
						goods_color.setText(cmcolor + ":" +changeNum);
					}
				}
			});
			hold.tv_jian.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String xianshi = hold.et_count.getText().toString().trim();// et
					// 显示的数量
					int x = Integer.parseInt(xianshi);
					if (x > 0) {
						x -= 1;
						change[position] = x;
//						myNumber[position] = x;
						hold.et_count.setText(x + "");

						if (onNumberChangeListener != null) {
							onNumberChangeListener.onNumberChange(change);
						}
					}
					changeNum = 0;
					for (int i = 0; i < change.length; i++) {
						changeNum = changeNum + change[i];
						goods_color.setText(cmcolor + ":" + changeNum);
					}
				}
			});
			hold.et_count.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					final Dialog dialog = new Dialog(GoodsDetailActivity.this, R.style.MyDialog);  //dialog 提示输入
					final View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog4, null);
					dialog.setContentView(view);
					dialog.show();
					editText = (EditText) view.findViewById(R.id.edit);
					String num = hold.et_count.getText().toString();
					editText.setText(num);
					editText.setSelection(num.length());
					editText.addTextChangedListener(mEtWatcher);
					((Button) view.findViewById(R.id.no)).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							dialog.dismiss();
						}
					});
					((Button) view.findViewById(R.id.yes)).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							//显示的数量
							String xianshi = editText.getText().toString().trim();// et
							int x = Integer.parseInt(xianshi);
							if (x > 0) {
								change[position] = x;
//								myNumber[position] = x;
								hold.et_count.setText(x + "");
								dialog.dismiss();
								if (onNumberChangeListener != null) {
									onNumberChangeListener.onNumberChange(change);
								}
							} else {
								Toast.makeText(GoodsDetailActivity.this, "超出库存", Toast.LENGTH_SHORT).show();
							}
							changeNum = 0;
							for (int i = 0; i < change.length; i++) {
								changeNum = changeNum + change[i];
								goods_color.setText(cmcolor + ":" + changeNum);
							}
						}
					});
				}
			});

		}

		@Override
		public Holder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View view = getLayoutInflater().inflate(R.layout.layout_item_choose, arg0, false);
			RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(view.getLayoutParams());
			params.width = (Utils.getDisplayWidthPixels(GoodsDetailActivity.this) - 200) / 3;
			view.setLayoutParams(params);
			return new Holder(view);
		}


	}
	private EditText editText;
	TextWatcher mEtWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

			//startsWith是String类中的一个方法，用来检测某字符串是否以另一个字符串开始，返回值为boolean类型
			if (s.toString().trim().startsWith("0") && s.toString().trim().length() == 1) {
				editText.setText("1");
				editText.setSelection(1);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};
	private ColorAdapter colorAdapter;

	public interface OnchangeListen {
		void setText();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_detail_back:// 返回
				finish();
				break;
			case R.id.re_kinds:// 分类
				if(popupWindow != null)
				{
					popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				}
				break;
			case R.id.tv_pay:// 直接购买
				isCart = false;
				if(popupWindow != null)
				{
					popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				}
				break;
			case R.id.tv_addCar:// 加入购物车
				isCart = true;
				if(popupWindow != null)
				{
					popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				}
				break;
			case R.id.img_collection:// 收藏
				Joincollection();
				break;
			case R.id.pop_confirm:// 确定按钮
				if(isCart){
					addCart();
				} else {
					setPay();
					//跳转到确认订单页
				}
				break;
			case R.id.pop_all:// 一手进货
				pop_number++;
				isChangeJia = true;
				isChangeJian = false;
				changeAllNumber(pop_number);

				break;
			case R.id.pop_jia:// 一手进货 加
				pop_number++;
				isChangeJia = true;
				isChangeJian = false;
				changeAllNumber(pop_number);

				break;
			case R.id.pop_jian:// 一手进货 减
				pop_number--;
				isChangeJia = false;
				isChangeJian = true;
				changeAllNumber(pop_number);

				break;
			default:
				break;
		}
	}
	// 一手进货
	private void changeAllNumber(int number){
		isChangeAll = true;
		if(number >= 0){

			pop_count.setText(number + "");
			colorAdapter.notifyDataSetChanged();
			sizeAdpeter.notifyDataSetChanged();
		} else {
		}
	}

	private void map2Data(){

		if (map2 != null && map2.size() != 0) {

			Iterator<Entry<Integer, int[]>> it = map2.entrySet().iterator();

			while (it.hasNext()) {

				Entry<Integer, int[]> entry = it.next();

				Integer key = entry.getKey();
				int[] value = entry.getValue();
				// cmotherlist颜色 ； sizeli尺寸；
				String cmcolor = cmotherlist.get(key).getCMCOLOR();
				mapData.put(cmcolor, value);
			}

		}
	}



    private  void  Joincollection() {
		Intent intent = getIntent();
		goodsid2 = intent.getStringExtra("goodsid");

		if (!"".equals(TCHConstants.url.token)) {
			Map<String, String> parms = new HashMap<String, String>();
			parms.put("goodsid", goodsid2.toString().trim());
			parms.put("token", TCHConstants.url.token);
			if (!islike) {
				HTTPUtils.get(GoodsDetailActivity.this, TCHConstants.url.JoinCollectionurl, parms,
						new ResponseListener() {
							@Override
							public void onResponse(String arg0) {
								Joincollection joincollection = GsonUtils.parseJSON(arg0, Joincollection.class);
								// 收藏token作为参数传到查询收藏界面
								Integer errorCode = joincollection.getErrorCode();
								String token3 = joincollection.getToken();
								if (errorCode == 0) {
									if (!"".equals(TCHConstants.url.token)) {
										TCHConstants.url.token=token3;
										islike = true;
										image_collection.setImageResource(R.drawable.like);
										Toast.makeText(GoodsDetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
									}
								} else {

								}
							}

							@Override
							public void onErrorResponse(VolleyError arg0) {

							}
						});
			} else {
				Map<String, String> parm = new HashMap<String, String>();
				parm.put("goodsid", goodsid.toString().trim());
				parm.put("token", TCHConstants.url.token);

				// 取消收藏
				HTTPUtils.get(GoodsDetailActivity.this, TCHConstants.url.DelMyCollectionurl, parm,
						new ResponseListener() {

							@Override
							public void onResponse(String arg0) {
								Deltecollection Deltejson = GsonUtils.parseJSON(arg0, Deltecollection.class);
								Integer errorCode2 = Deltejson.getErrorCode();
								if (errorCode2 == 0) {
									islike = false;
									image_collection.setImageResource(R.drawable.unlike);
									Toast.makeText(GoodsDetailActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
								}
							}
							@Override
							public void onErrorResponse(VolleyError arg0) {

							}
						});
			}
		}

	}
}



