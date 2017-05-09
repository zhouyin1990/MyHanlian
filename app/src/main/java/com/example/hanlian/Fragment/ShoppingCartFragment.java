package com.example.hanlian.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.example.hanlian.Activity.GoodsDetailActivity;
import com.example.hanlian.Adapter.Result;
import com.example.hanlian.Adapter.Tuijian;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.example.widget.MyListview;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DetailsModle.CMOTHER;
import utils.CardGoodsInfo;
import utils.DButils;
import utils.OnCheckChangeListener1;
import utils.OnNumberChangeListener;
import utils.OnNumberChangeListener2;
import utils.OnTotalPriceChangeListener;
import utils.TCHConstants;
import utils.Utils;

/**
 * 购物车
 */
public class ShoppingCartFragment extends Fragment implements OnClickListener {

	private LayoutInflater mInflater;
	private ArrayList<CardGoodsInfo> infolist = new ArrayList<CardGoodsInfo>();
	private View layout;
	private MyListview listView_card;
	private CardAdapter cardAdapter;
	private CheckBox select_all;
	private boolean isSelectAll;
	private TextView tv_count;
	private TextView tv_totalprice;
	private int allcouts = 0;
	private int allprice = 0;
	private TextView tv_jiesuan;
	private GridView tuijian_gv;
	ArrayList<Result> tuijianlist =new ArrayList<Result>();
	private String[] split;
	private ArrayList<String[]> splitslist = new ArrayList<String[]>();
	int pageNum=1 ;
	int pageSize= 0 ;
	private tuijianAdapter tuijianAdapter;
	private boolean isFirst;


	public ShoppingCartFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mInflater = inflater;
//		if (layout == null) {
			layout = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
			// 测试数据
			initUI();
			initData();
			
			Log.e("数量==", allcouts + "");
			Log.e("价格==", allprice + "");

//		} else {
//			ViewGroup parent = (ViewGroup) layout.getParent();
//			if (parent != null) {
//				parent.removeAllViewsInLayout();
//			}
//		}

		return layout;
	}
	// 离开界面初始化选中状态及数量
	@Override
	public void onPause() {
		super.onPause();
		for (int i = 0; i < infolist.size(); i++) {
			infolist.get(i).setIscheck(false);
		}
		allcouts = 0;
		allprice = 0;
		isSelectAll = false;
		select_all.setChecked(false);
		cardAdapter.notifyDataSetChanged();
		
	}
    //TODO 解决在购物车进入推荐商品 详情页加入购物车返回后不能实时显示(需要切换界面才能显示)
	@Override
    public void onResume() {
        super.onResume();
        isFirst = true;
        allcouts = 0;
		allprice = 0;
		isSelectAll = false;
		select_all.setChecked(false);
		List<CardGoodsInfo> list1 = DButils.query();



		infolist.clear();
		infolist.addAll(list1);
		cardAdapter.notifyDataSetChanged();
	}

    private void intidata1() {
		pageSize +=10 ;
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", 0+"");
		params.put("classifyID",0+"");
		params.put("pageNum",  pageNum+"");
		params.put("pageSize",pageSize+"");
		
		HTTPUtils.get(getContext(), TCHConstants.url.QueryPromotionData, params, new ResponseListener() {
			
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
				    tuijian_gv.setAdapter(tuijianAdapter);
				    
				    tuijianAdapter.notifyDataSetChanged();
				}
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				
			}
		});
		
		
	}

	private void initData() {
		List<CardGoodsInfo> list = DButils.query();

	//	JSONObject goodslist = list.get(0).getGOODSLIST();

	//	Log.e("goodslist==",goodslist.toString());

		sortData(list);
		
		infolist.clear();
		infolist.addAll(list);

		cardAdapter.notifyDataSetChanged();

		intidata1();//推荐
	}
	// TODO 数据排序
	private List<CardGoodsInfo> sortData(List<CardGoodsInfo> list){
		
		Map<Integer, ArrayList<CardGoodsInfo>> data = new HashMap<Integer, ArrayList<CardGoodsInfo>>();
		
		if(list.size() < 3){
			return list;
		}
		
		for (int i = 0; i < list.size() - 1; i++) {
			ArrayList<CardGoodsInfo> dataList = new ArrayList<CardGoodsInfo>();
			
			dataList.add(list.get(0));
			
			String firstName = list.get(0).getShoppingName();
			
			for (int j = i + 1; j < list.size(); j++) {
				String name = list.get(j).getShoppingName();
				
				if(firstName.equals(name)){
					dataList.add(list.get(j));
					list.remove(j);
					j--;
				}
			}
			data.put(i, dataList);
			list.remove(0);
		}
		
		for (int i = 0; i < data.size(); i++) {
			list.addAll(data.get(i));
		}
		
		return list;
	}

	private void initUI() {
		titlebar = layout.findViewById(R.id.titlebar);
		initPopWindow();
		
		tuijianAdapter = new tuijianAdapter();
		// 结算
		tv_jiesuan = (TextView) layout.findViewById(R.id.tv_jiesuan);

		tv_jiesuan.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unused")
			@Override
			public void onClick(View v) {

				JSONArray array3 = new JSONArray();
				for (int i = 0; i < infolist.size(); i++) {

					boolean ischeck = infolist.get(i).isIscheck();
					if (ischeck) {
						JSONObject object3 = new JSONObject();
						try {
							object3.put("GOODSLIST", infolist.get(i).getGOODSLIST());

							array3.put(i, object3);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				if (array3 != null) {
					final JSONObject object4 = new JSONObject();
					try {
						object4.put("INTEGRAL", 0);
						object4.put("ORDERS", array3);
						submitOrder(object4, TCHConstants.url.token);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else{
					Toast.makeText(getContext(), "请选择商品", Toast.LENGTH_SHORT).show();
				}
			}
		});

		// 数量
		tv_count = (TextView) layout.findViewById(R.id.tv_count);
		// 总价
		tv_totalprice = (TextView) layout.findViewById(R.id.tv_totalprice);
		// 全选
		select_all = (CheckBox) layout.findViewById(R.id.select_all);
		select_all.setOnCheckedChangeListener(new OnCheckedChangeListener() { 

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				isSelectAll = true;
				
				if (isChecked) {
					buttonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.checked));
					if (infolist.size() != 0) {
						for (int i = 0; i < infolist.size(); i++) {
							infolist.get(i).setIscheck(true);
						}

					}
				} else {
					buttonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.uncheck));
					for (int i = 0; i < infolist.size(); i++) {
						infolist.get(i).setIscheck(false);
					}

				}
				cardAdapter.notifyDataSetChanged();

			}
		});

		listView_card = (MyListview) layout.findViewById(R.id.listView_card);
		cardAdapter = new CardAdapter();
		listView_card.setAdapter(cardAdapter);
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		listView_card.setMenuCreator(creator);

//		listView_card.setOnItemClickListener(new OnItemClickListener() {
//                     // 跳转到详情
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				// TODO
//				CardGoodsInfo cardGoodsInfo = infolist.get(position);
//				String goodsid = cardGoodsInfo.getGoodsid();
//				Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
//				intent.putExtra("goodsid", goodsid);
//				startActivity(intent);
//			}
//		});

		listView_card.setOnMenuItemClickListener(new MyListview.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				switch (index) {
				case 0:
					if (infolist.size() != 0) {
						CardGoodsInfo cardGoodsInfo = infolist.get(position);
						DButils.delete("goodsid = ?", cardGoodsInfo);
						infolist.remove(position);
						cardAdapter.notifyDataSetChanged();
					}
					break;
				}
				return false;
			}
		});

		cardAdapter.setOnNumberChangeListener(new OnNumberChangeListener2() {

			@Override
			public void onNumberChange(int num) {
				allcouts += num;
				tv_count.setText("共" + allcouts + "件");
				Log.e("num", num + "");
			}

		});
		cardAdapter.setOntotalpricechangelistener(new OnTotalPriceChangeListener() {

			@Override
			public void TotalPriceChange(int totalprice) {
				allprice += totalprice;
				tv_totalprice.setText("总计:¥" + allprice);
				Log.e("totalprice", totalprice + "");
//				Log.e("allprice", allprice + "");
			}
		});
         tuijian_gv = (GridView) layout.findViewById(R.id.shopcar_gridview);
		
		 tuijian_gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent=new Intent(getActivity(), GoodsDetailActivity.class);
				 String goodsid = tuijianlist.get(position).getCMGOODSID();
				 intent.putExtra("goodsid", goodsid) ;      			
				 startActivity(intent);
			}
		});		
	}
	//-------------------------提交订单------------------------------	
	private void submitOrder(JSONObject object4, String token) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("token", token);// TODO
			params.put("goodsjson", object4.toString());// json

			HTTPUtils.post(getContext(), TCHConstants.url.SUBMITORDER,
					params, new ResponseListener() {

						@Override
						public void onResponse(String arg0) {
							try {
								JSONObject object = new JSONObject(arg0);
								int errocode = object.getInt("ErrorCode");
								if (errocode == 0) {
									Toast.makeText(getContext(), "购物车提交订单",
											Toast.LENGTH_SHORT).show();
									//TODO
									for (int i = 0; i < infolist.size(); i++) {
										boolean ischeck = infolist.get(i).isIscheck();
										if(ischeck){
											DButils.delete("goodsid = ?", infolist.get(i));
											infolist.remove(i);
											cardAdapter.notifyDataSetChanged();
										}
									}									
									
								} else {
									Toast.makeText(getContext(),
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

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	class CardAdapter extends BaseAdapter {
		
		private OnNumberChangeListener2 numchangelistener;

		public void setOnNumberChangeListener(OnNumberChangeListener2 onNumberChangeListener) {
			this.numchangelistener = onNumberChangeListener;
		}

		private OnTotalPriceChangeListener ontotalpricechangelistener;

		public void setOntotalpricechangelistener(OnTotalPriceChangeListener ontotalpricechangelistener) {
			this.ontotalpricechangelistener = ontotalpricechangelistener;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHold hold = null;
			if (convertView == null) {
				hold = new ViewHold();
				view = mInflater.inflate(R.layout.item_cart, null);
				hold.relative = (RelativeLayout) view.findViewById(R.id.item_cart_relative);
				hold.shoppingName = (TextView) view.findViewById(R.id.shoppingname);
				hold.goodsImage = (ImageView) view.findViewById(R.id.image);
				hold.name = (TextView) view.findViewById(R.id.name);
				hold.price = (TextView) view.findViewById(R.id.price);
				hold.number = (TextView) view.findViewById(R.id.number);
				hold.imageChange = (ImageView) view.findViewById(R.id.image_change);
				hold.checkBox = (CheckBox) view.findViewById(R.id.check);
				view.setTag(hold);
			} else {
				view = convertView;
				hold = (ViewHold)view.getTag();
			}
			
			final CardGoodsInfo goodsInfo = infolist.get(position);
			
			hold.name.setText(goodsInfo.getTitle());
			hold.price.setText("¥"+ goodsInfo.getTotalprice());
			hold.number.setText(goodsInfo.getCount() + "");
			hold.checkBox.setChecked(goodsInfo.isIscheck());
			
			String shoppingName = goodsInfo.getShoppingName();
			
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
			
			ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + goodsInfo.getImgURL(),
					hold.goodsImage);
           
			// 选中监听
			hold.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					
					
					goodsInfo.setIscheck(isChecked);

					int count = goodsInfo.getCount();
					int totalprice = goodsInfo.getTotalprice();
					
					if (isChecked) {
						buttonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.checked));
						if(!isSelectAll){
							numchangelistener.onNumberChange(count);
							ontotalpricechangelistener.TotalPriceChange(totalprice);
						}
					} else {
						buttonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.uncheck));
						if(!isSelectAll){
							numchangelistener.onNumberChange(-count);
							ontotalpricechangelistener.TotalPriceChange(-totalprice);
						}
					}
				}
			});
			// TODO 弹出ppwod
			hold.imageChange.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + goodsInfo.getImgURL(), image_goods);
					tv_goods_code.setText(goodsInfo.getShoppingID());
					tv_goods_price.setText("¥" + goodsInfo.getTitle());
					
					if(popupWindow != null) 
					{
						popupWindow.showAtLocation(titlebar, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);				
					}
				}
			});
			
			isSelectAll = false;
			
			// 重新进入界面时归零
			if(isFirst){
				numchangelistener.onNumberChange(0);
				ontotalpricechangelistener.TotalPriceChange(0);
				isFirst = false;
			}
			
			return view;
		}

		@Override
		public int getCount() {
			return infolist.size();
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
	
	class ViewHold {
		TextView name;
		TextView price;
		TextView number;
		TextView shoppingName;
		ImageView goodsImage;
		CheckBox checkBox;
		RelativeLayout relative;
		ImageView imageChange;
	}





	
	class tuijianAdapter extends  BaseAdapter
	{

		@Override
		public int getCount() {
			return tuijianlist.size();
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
   			    layout = mInflater.inflate(R.layout.tuijian_gridview_item, null); // 复用 hot gv item
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

	private PopupWindow popupWindow;
	private TextView tv_sure;
	private ArrayList<String> sizelist = new ArrayList<String>();
	private String cmsellerid;
	private String cmsellername;
	private String cmgoodsid;
	private Integer cmsales;
	private String path;
	private String cmtitle;
	private View linearlayout_bottom2;
	private String goodsid;
	private String goodsid2;
	private int width;
	private ArrayList<DetailsModle.Result> resultList = new ArrayList<DetailsModle.Result>();
	private ArrayList<CMOTHER> cmotherlist = new ArrayList<CMOTHER>();
	private Map<Integer, int[]> map = new HashMap<Integer, int[]>();//Integer 选中颜色的poistion  int[] 数量的数组
	private EditText editText;
	private View parent;
	private TextView tv_goods_price;
	private ImageView image_goods;
	private TextView tv_goods_code;
	private ListView listView_choose;
	
	// TODO 初始化点击购物车时候的弹窗
	private void initPopWindow() {
		View view = mInflater.inflate(R.layout.layout_pop_addcar, null);
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		Drawable background = new BitmapDrawable();//
		popupWindow.setFocusable(true);// 是为了解决，当点击其他位罢的时候，不关闭
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(background);
		image_goods = (ImageView) view.findViewById(R.id.image_goods);
		tv_goods_price = (TextView) view.findViewById(R.id.tv_goods_price);
		tv_goods_code = (TextView) view.findViewById(R.id.tv_goods_code);
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
		linearlayout_bottom2 = view.findViewById(R.id.linerlayout_wancheng);
		linearlayout_bottom2.setVisibility(View.GONE);
		linearlayout_bottom2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				linearlayout_bottom2.setVisibility(View.GONE);
				popupWindow.dismiss();
			}
		});

		listView_choose = (ListView) view.findViewById(R.id.listView_choose);
		
		width = listView_choose.getWidth();
		
		
//        cmtitle = resultList.get(0).getCMTITLE();
//        cmtitle = infolist.get(0).getTitle();
//		//商家id
//		cmsellerid = resultList.get(0).getCMSELLERID();
//		// 商家名称
//		cmsellername = resultList.get(0).getCMSELLERNAME();
//		// 商品id
//		cmgoodsid = resultList.get(0).getCMGOODSID();
//		tv_goods_code.setText(cmgoodsid);
//		// 价格
//		cmsales = resultList.get(0).getCMPRESENTPRICE();
//		tv_goods_price.setText("¥" + cmsales +"");
//		// 图片路径
//		path = resultList.get(0).getCMMAINFIGUREPATH();
//		ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + path, image_goods);

		ColorAdapter colorAdapter = new ColorAdapter();
		listView_choose.setAdapter(colorAdapter);

		colorAdapter.setOncheckchangelistener(new OnCheckChangeListener1() {
			@Override
			public void onCheckChange(int[] num, int position, boolean check) {
				if (check) {
					map.put(position, num);
				} else {
					map.remove(position); 
				}
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
			final ViewHoldColor hold;

			if (convertView == null) {
				hold = new ViewHoldColor();
				layout = mInflater.inflate(R.layout.layout_item_color, null);
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
				hold = (ViewHoldColor) layout.getTag();
			}

			if (cmotherlist.size() != 0) {
				CMOTHER cmother = cmotherlist.get(position);
				String cmspecstock = cmother.getCMSPECSTOCK();
				String[] split = cmspecstock.split("\\|");
				hold.strlist.add(split);
			}

			String cmcolor = cmotherlist.get(position).getCMCOLOR();
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

			// 设置布局管理器
			LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
			linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			hold.recycleview.setLayoutManager(linearLayoutManager);

			SizeAdpeter sizeAdpeter = new SizeAdpeter(hold.tv_goods_color, cmcolor, hold.strlist, hold.kucun, hold.change);
			hold.recycleview.setAdapter(sizeAdpeter);

			sizeAdpeter.setOnNumberChangeListener(new OnNumberChangeListener() {

				@Override
				public void onNumberChange(int[] num) {
					hold.change = num;
				}
			});

			return layout;
		}

	}
	
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

		class Holder extends RecyclerView.ViewHolder {

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
					change[i] = Integer.parseInt(split[1]);
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
//			hold.et_count.setText(change[position] + "");//库存
			hold.et_count.setText("0");

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
						Toast.makeText(getActivity(), "超出库存", Toast.LENGTH_SHORT).show();
					}
					changeNum = 0;
					for (int i = 0; i < myNumber.length; i++) {
						changeNum = changeNum + myNumber[i];
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
						myNumber[position] = x;
						hold.et_count.setText(x + "");

						if (onNumberChangeListener != null) {
							onNumberChangeListener.onNumberChange(change);
						}
					}
					changeNum = 0;
					for (int i = 0; i < myNumber.length; i++) {
						changeNum = changeNum + myNumber[i];
						goods_color.setText(cmcolor + ":" + changeNum);
					}
				}
			});
			hold.et_count.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					final Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);  //dialog 提示输入
					final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog4, null);
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
								myNumber[position] = x;
								hold.et_count.setText(x + "");
								dialog.dismiss();
								if (onNumberChangeListener != null) {
									onNumberChangeListener.onNumberChange(change);
								}
							} else {
								Toast.makeText(getActivity(), "超出库存", Toast.LENGTH_SHORT).show();
							}
							changeNum = 0;
							for (int i = 0; i < myNumber.length; i++) {
								changeNum = changeNum + myNumber[i];
								goods_color.setText(cmcolor + ":" + changeNum);
							}
						}
					});
				}
			});
		}

		@Override
		public Holder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View view = mInflater.inflate(R.layout.layout_item_choose, arg0, false);
			RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(view.getLayoutParams());
			params.width = (Utils.getDisplayWidthPixels(getActivity()) - 200) / 3;
			view.setLayoutParams(params);
			return new Holder(view);
		}
		

	}

	TextWatcher mEtWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
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
	private View titlebar;

	
	class ViewHoldColor {
		TextView tv_goods_color;
		RecyclerView recycleview;
		ArrayList<String[]> strlist;
		boolean ischeck;
		int[] kucun;
		int[] change;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pop_confirm:// 确定
			
			break;

		default:
			break;
		}
		
	}

}
