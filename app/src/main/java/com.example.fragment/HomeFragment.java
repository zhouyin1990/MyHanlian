package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.example.hanlian.Activity.GoodsDetailActivity;
import com.example.hanlian.Activity.GoodsSortActivity;
import com.example.hanlian.Adapter.GoodsInfo;
import com.example.hanlian.Fristpager.Fristpager;
import com.example.hanlian.Fristpager.Good;
import com.example.hanlian.Fristpager.Result;
import com.example.hanlian.Fristpager.Show;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.example.widget.MyView;
import com.example.widget.newscrollview;
import com.example.widget.newscrollview.ScrollViewListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


import utils.TCHConstants;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 首页
 */
public class HomeFragment extends Fragment implements OnPageChangeListener,ScrollViewListener, OnClickListener  {

	private LayoutInflater inflater;
	private overlayAdapter overlayAdapter;
	View layout = null;
	private ViewPager mPager;
	protected boolean isDragging;
	private static final int DELAY_MILINS = 4000;
	private static final int MAX_LENGTH = 4000000;
	protected static final long DURATION = 3000;
	private int realnum = 5;
	private MyView myview;
	private GridView gridView_headview;
	private String[] str = new String[] {};
	private String[] mGridViewImgRes = new String[] {};
	private HomeMyAdapter homeMyAdapter;//首页详情gridview 适配器
	private DisplayImageOptions options;
	ArrayList<Good> homegoodlist = new ArrayList<Good>();
	ArrayList<Show> homeshowlist = new ArrayList<Show>();
//	ArrayList<JSONArray> homeshowlists = new ArrayList<JSONArray>();

	private newscrollview scrollView;
	private GridView gridview_detail;
	private int height ;
	
//	private JSONArray goodsList = new JSONArray();
	private JSONArray  jsonArrayGoods = new JSONArray();
	private JSONArray  jsonArrayShows = new JSONArray();
	
	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		if (layout == null) {
			layout = inflater.inflate(R.layout.fragment_home, container, false);
			intiUI();			
			getGoodsSort();
			intiUIL();
			intiData();
		}else
		{
			
			ViewGroup parent = (ViewGroup) layout.getParent();
	        if (parent!=null){	        	
	          parent.removeAllViewsInLayout();
	        }
		}		
		autoScroll();
		return layout;
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
				.displayer(new FadeInBitmapDisplayer(200))// 图片加载好之后渐入的时间
				.build();
	}
   
	int pageNum=1 ;
	int pageSize= 0 ;
	int isfirst = 1 ;
	private String cmhorizontalpath;
	private FragmentManager fm;
	private RelativeLayout layoutHead;
	private ViewPager viewpager;
	private void intiData() {				
		pageSize +=6 ;
		Map<String, String> params =new HashMap<String, String>();
		params.put("pageNum", pageNum+"");
		params.put("pageSize", pageSize+"");
		params.put("isfirst", isfirst +"");
	
		//TODO  动态添加 
		HTTPUtils.get(getContext(), TCHConstants.url.homeurl,params,new ResponseListener() {         
			@Override
			public void onResponse(String arg0) {
				
				try {
					
					 JSONObject jsonResult = new JSONObject(arg0.toString()).getJSONObject("Result");
					 
					 
					 jsonArrayGoods = jsonResult.getJSONArray("Goods");
					 jsonArrayShows = jsonResult.getJSONArray("Shows");
					 for (int i = 0; i < jsonArrayGoods.length(); i++) {
						 JSONObject jsonObject = jsonArrayGoods.getJSONObject(i);
						 String GOODSID = jsonObject.getString("CM_GOODSID");
						
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				Fristpager fristjson = GsonUtils.parseJSON(arg0, Fristpager.class);
				Result result = fristjson.getResult();
				List<Good> goods = result.getGoods();
				List<Show> shows = result.getShows();
				homegoodlist.clear();
				homegoodlist.addAll(goods);
				
				homeshowlist.clear();
				homeshowlist.addAll(shows);
				
//				myview.setRealNum(homeshowlist.size());
				myview.setRealNum(jsonArrayShows.length());
				BannerAdapter bannerAdapter = new BannerAdapter(fm);
				mPager.setAdapter(bannerAdapter);
				bannerAdapter.notifyDataSetChanged();				
				gridview_detail.setAdapter(homeMyAdapter);
				homeMyAdapter.notifyDataSetChanged();    
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {
			}
		});
	}

	private void intiUI() {
		
//		layout.findViewById(R.id.img_qcode).setOnClickListener(this);
//		layout.findViewById(R.id.img_message).setOnClickListener(this);
//		layout.findViewById(R.id.rl_search).setOnClickListener(this);
//		layout.findViewById(R.id.edittext_search).setOnClickListener(this);
//		layoutHead = (RelativeLayout) layout.findViewById(R.id.home_topbar);
		
		viewpager = (ViewPager) layout.findViewById(R.id.pager); //viewpager gaodu

		
		
		mPager = (ViewPager) layout.findViewById(R.id.pager);
		myview = (MyView) layout.findViewById(R.id.myView1);
		fm = getChildFragmentManager();
		gridView_headview = (GridView) layout.findViewById(R.id.gridView_headview);		
		
		scrollView = (newscrollview) layout.findViewById(R.id.scrollView1);						
        //获取顶部viewpager高度后，设置滚动监听
		ViewTreeObserver vto = mPager.getViewTreeObserver();  
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override  
            public void onGlobalLayout() {
            	mPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = mPager.getHeight();
                mPager.getWidth();                
                scrollView.setScrollViewListener(HomeFragment.this);		                
            }  
        });								
//        ViewTreeObserver vto = viewpager.getViewTreeObserver();  
//        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//        	@Override  
//        	public void onGlobalLayout() {
//        		viewpager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//        		height = viewpager.getHeight();
//        		viewpager.getWidth();                
//        		scrollView.setScrollViewListener(HomeFragment.this);		                
//        	}  
//        });								
		//下拉刷新用scrollview
		scrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				//重新加载数据
				intiData();
				scrollView.onRefreshComplete();										
			}
		});						
		overlayAdapter = new overlayAdapter();
		// 设置gridView 适配器
		gridView_headview.setAdapter(overlayAdapter);
		gridView_headview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), GoodsSortActivity.class);
				intent.putExtra("sort", str[position]);
				startActivity(intent);
			}
		});;
						
		// 
		gridview_detail = (GridView) layout.findViewById(R.id.gridView_detail);
		gridview_detail.setOnItemClickListener(new OnItemClickListener() {

			@Override  //  跳转到详情页
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent=new Intent(getActivity(), GoodsDetailActivity.class);											
				String goodsid = homegoodlist.get(position).getCMGOODSID();								
			    intent.putExtra("goodsid", goodsid) ; 
				startActivity(intent);												
			}
		});		
		homeMyAdapter = new HomeMyAdapter();
//      gridview_detail.setAdapter(homeMyAdapter);		      
//		myview.setRealNum(homeshowlist.size());
//		mPager.setAdapter(new BannerAdapter(fm));
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
					// Log.e("onPageScrollStateChanged", "SETTLING");
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
//				if(homeshowlist.size() != 0)
//				{
//					myview.move(arg0  %= homeshowlist.size(), arg1);
//				}
				if(jsonArrayShows.length() != 0)
				{
					myview.move(arg0  %= jsonArrayShows.length(), arg1);
				}
			}
		});
	}

	private View findViewById(int viewHead) {
		return null;
	}

	@Override
	public void onScrollChanged(newscrollview scrollView, int x, int y, int oldx, int oldy) {
	}
	class BannerAdapter extends FragmentPagerAdapter {

		public BannerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position ) {
			
			if( jsonArrayShows.length() != 0 )
			{
				
				position%=jsonArrayShows.length() ;
				BannerItemFragment ItemFragment = new BannerItemFragment(position);
				Bundle bundle = new Bundle();
				try {
					String url = jsonArrayShows.getJSONObject(position).getString("CM_MAINFIGUREPATH");
					
					bundle.putString("imgurl",url);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}		
				ItemFragment.setArguments(bundle);			
				return ItemFragment;
			}
			return null ;
		}

		@Override
		public int getCount() {
			return MAX_LENGTH;
		}
	}

	private void autoScroll() {
		if (mPager != null) {
			mPager.postDelayed(new Runnable() {
				public void run() {
					mPager.postDelayed(this, DELAY_MILINS);
					if (!isDragging) {
						int currenItem = mPager.getCurrentItem();
						int nextItem = currenItem + 1;
						mPager.setCurrentItem(nextItem);
					}
				}
			}, DELAY_MILINS);
		}

	}

	class HomeMyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return homegoodlist.size();
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
			if (convertView == null || convertView.getTag() !=null) {
				holder = new viewHolder();
				layout = inflater.inflate(R.layout.hot_girdviw_item, null);
				holder.img_hot_gv_item = (ImageView) layout.findViewById(R.id.img_hot_gv_item);
				holder.tv_hot_title = (TextView) layout.findViewById(R.id.tv_hot_title);
				holder.tv_hot_shoujia = (TextView) layout.findViewById(R.id.tv_hot_shoujia);
				layout.setTag(holder);
			} else {
				layout = convertView;
				holder = (viewHolder) layout.getTag();
			}
			String homecmtitle = homegoodlist.get(position).getCMTITLE();
			Integer homecmpresentprice = homegoodlist.get(position).getCMPRESENTPRICE();
			Integer homecmsales = homegoodlist.get(position).getCMSALES();
			String homecmmainfigurepath = homegoodlist.get(position).getCMMAINFIGUREPATH();											
			holder.tv_hot_title.setText(homecmtitle);
            holder.tv_hot_shoujia.setText(""+homecmpresentprice);			
		//	holder.tv_hot_salenumber.setText(" "+homecmsales);
			try {
				ImageLoader.getInstance().displayImage(
						TCHConstants.url.imgurl +  jsonArrayGoods.getJSONObject(position).getString("CM_MAINFIGUREPATH"),
						holder.img_hot_gv_item, MyApplication.options);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//图片地址
			
//			try {   	
//				
//				holder.tv_hot_title.setText( jsonArrayGoods.getJSONObject(position).getString("CM_TITLE")); // 标题
//				holder.tv_hot_shoujia.setText( jsonArrayGoods.getJSONObject(position).getString("CM_ORIGINALPRICE"));//售价
//				holder.tv_hot_salenumber.setText( jsonArrayGoods.getJSONObject(position).getString("CM_SALES")); // 销量
//				ImageLoader.getInstance().displayImage(
//						TCHConstants.url.imgurl +  jsonArrayGoods.getJSONObject(position).getString("CM_MAINFIGUREPATH"),
//						holder.img_hot_gv_item, MyApplication.options);//图片地址
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}									
			return layout;
		}

		class viewHolder {
			ImageView img_hot_gv_item;
			TextView tv_hot_title;
			TextView tv_hot_shoujia;
			TextView tv_hot_salenumber;
		}
	}
	
	private JSONArray goodsList = new JSONArray();// 大的类别数据
	private List<GoodsInfo> sortData = new ArrayList<GoodsInfo>();// 大的类别数据
	//获取主分类数据
	private void getGoodsSort() {
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", "1");
		params.put("classifyid", "0");
		HTTPUtils.get(getActivity(), TCHConstants.url.goodsSort,params,new ResponseListener() {         

			@Override
			public void onResponse(String arg0) {
						try {
							goodsList = new JSONObject(arg0.toString()).getJSONArray("Result");
							
							str = new String[goodsList.length()];
							mGridViewImgRes = new String[goodsList.length()];
							
							if (goodsList.length() > 0) {
								
								for (int i = 0; i < goodsList.length(); i++) {
									
									JSONObject goodsObject = goodsList.getJSONObject(i);
									String name = goodsObject.getString("CM_CLASSIFYNAME");
									String imageUrl = goodsObject.getString("CM_IMGPATH");
									int goodsId = goodsObject.getInt("CM_CLASSIFYID");
									sortData.add(new GoodsInfo(imageUrl, name, goodsId));
									
									str[i] = name;
									mGridViewImgRes[i] = imageUrl;
								}
								
								overlayAdapter.notifyDataSetChanged();						
							}else{ // 搜索为空
								Toast.makeText(getActivity(), "获取分类数据失败", Toast.LENGTH_SHORT).show();
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

	// gridview 适配器
	class overlayAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = inflater.inflate(R.layout.overlay_gridview_item, null);
			TextView mTextView = (TextView) layout.findViewById(R.id.tv_gridview_item);
			ImageView mImageView = (ImageView) layout.findViewById(R.id.img_gridview_item);
			// TODO 文字 
			 ImageLoader.getInstance().displayImage
			 (TCHConstants.url.imgurl + mGridViewImgRes[position], mImageView);
			mTextView.setText(str[position]);
			return layout;
		}

		@Override
		public int getCount() {
			return str.length;
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

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int position, float offset, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {

	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.rl_search:
//			gosearch();
//			break;
//		case R.id.edittext_search:
//			gosearch();
//			break;
//		case R.id.img_qcode:// 二维码扫描
//
//			break;
//		}

//	    }

//	private void gosearch() {
//		Intent intent = new Intent(getActivity(), SearchActivity.class);
//		startActivity(intent);
//		// 开启无动画
//		getActivity().overridePendingTransition(0, 0);
//	}
//
//	@Override
//	public void onScrollChanged(newscrollview scrollView, int x, int y, int oldx, int oldy) {
//
//		if(y<=height){
//			float scale =(float) y /height;
//			float alpha =  (255 * scale);
//
//
//			//只是layout背景透明(仿知乎滑动效果)
//			layoutHead.setBackgroundColor(Color.argb((int) alpha, 0xfd, 0x91, 0x5b));
//		}
		


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	}
