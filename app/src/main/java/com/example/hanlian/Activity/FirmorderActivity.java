package com.example.hanlian.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.List;

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
	private int number;//总数量
	private int price;//总价格
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confim);
		PushAgent.getInstance(this).onAppStart();
		intiUI();		
		intiData();
				
	}

	private void intiData() {
		List<CardGoodsInfo> list = DButils.query();
		infolist.clear();
		infolist.addAll(list);
		myAdapter.notifyDataSetChanged();
		
		for (int i = 0; i < infolist.size(); i++) {
			number += infolist.get(i).getCount();
			price += infolist.get(i).getTotalprice();
		}
		
		tv_count.setText("共" + number + "件 ");
		tv_totalprice.setText("合计：¥ " + price);
	}

	private void intiUI() {
		
		tv_count = (TextView) findViewById(R.id.tv_count);
		tv_totalprice = (TextView) findViewById(R.id.tv_totalprice);
		findViewById(R.id.tv_jiesuan).setOnClickListener(this);
		
		confim_list = (ListView) findViewById(R.id.confim_list);
		myAdapter = new MyAdapter();
		confim_list.setAdapter(myAdapter);
	}
	
	class MyAdapter extends BaseAdapter{

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

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHold hold = null;
			
			if (convertView == null) {
				hold = new ViewHold();
				view = getLayoutInflater().inflate(R.layout.item_confim, null);
				hold.relative = (RelativeLayout) view.findViewById(R.id.item_cart_relative);
				hold.shoppingName = (TextView) view.findViewById(R.id.shoppingname);
				hold.goodsImage = (ImageView) view.findViewById(R.id.image);
				hold.name = (TextView) view.findViewById(R.id.name);
				hold.price = (TextView) view.findViewById(R.id.price);
				hold.number = (TextView) view.findViewById(R.id.number);
				hold.detail = (TextView) view.findViewById(R.id.detail);
				hold.detail_layout = (LinearLayout) view.findViewById(R.id.confirm_detail_layout);
				hold.color = (TextView) view.findViewById(R.id.color);
				hold.size = (TextView) view.findViewById(R.id.size);
				hold.number2 = (TextView) view.findViewById(R.id.number2);
				view.setTag(hold);
			} else {
				view = convertView;
				hold = (ViewHold)view.getTag();
			}

			final CardGoodsInfo goodsInfo = infolist.get(position);
			hold.name.setText(goodsInfo.getTitle());
			hold.price.setText("¥" + goodsInfo.getTotalprice());
			hold.number.setText(goodsInfo.getCount() + "");
			
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
			
			if(isShow && index == position){
				hold.detail_layout.setVisibility(View.VISIBLE);
			} else {
				hold.detail_layout.setVisibility(View.GONE);
			}
           
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
	
	class ViewHold {
		TextView name;
		TextView price;
		TextView number;
		TextView shoppingName;
		ImageView goodsImage;
		RelativeLayout relative;
		TextView detail;
		LinearLayout detail_layout;
		TextView color;
		TextView size;
		TextView number2;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_jiesuan:
			//Toast.makeText(this, "提交订单", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		
	}

	
}
