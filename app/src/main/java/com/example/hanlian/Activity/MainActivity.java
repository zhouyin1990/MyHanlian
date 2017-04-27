package com.example.hanlian.Activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragment.FireElementFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.NewFragment;
import com.example.fragment.ShoppingCartFragment;
import com.example.fragment.UserFragment;
import com.example.hanlian.R;
import com.umeng.message.PushAgent;

public class MainActivity extends FragmentActivity {
	
	private FragmentTabHost mTabHost;
	//String letters[] = new String[] { "simple", "contacts", "custom", "throttle" };
	String indicatorLetter[] = new String[] { "首页", "热门","新品" ,"购物车", "我的" };
	Class clas[] =new Class[]{HomeFragment.class ,FireElementFragment.class , NewFragment.class ,ShoppingCartFragment.class ,UserFragment.class };
	int images[] =new int [] {R.drawable.home_selector ,R.drawable.fire_element_selector ,R.drawable.new_selector ,
			R.drawable.shoping_cart_selector ,R.drawable.uesr_selector};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PushAgent.getInstance(this).onAppStart();
	    intiUI();
	}
	private void intiUI() {	
		mTabHost = (FragmentTabHost) findViewById
				(android.R.id.tabhost);
		mTabHost.setup
		(this, getSupportFragmentManager(), R.id.realtabcontent);
		LayoutInflater inflater = getLayoutInflater();
		for (int i = 0; i < indicatorLetter.length; i++) {
			View indicatorView = inflater.inflate(R.layout.indicator_item, null);
			ImageView imageView = (ImageView) indicatorView.findViewById(R.id.ima_indicator);
			TextView textView = (TextView) indicatorView.findViewById(R.id.tv_title_indicator);
			textView.setText(indicatorLetter[i]);
			imageView.setImageResource(images[i]);
			mTabHost.addTab(mTabHost.newTabSpec(indicatorLetter[i]).setIndicator(indicatorView), clas[i], null);
	}


	}

	/**
	 * 打开二维码扫描界面，用于给片段调用
	 */
//	public void scanQRCode() {
//		// 打开扫描界面扫描条形码或二维码
//		Intent openCameraIntent = new Intent(this, CaptureActivity.class);
//		startActivityForResult(openCameraIntent, 0);
//	}
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
//		// 处理扫描结果（在界面上显示）
//		if (resultCode == Activity.RESULT_OK) {
//			Bundle bundle = data.getExtras();
//			String scanResult = bundle.getString("result");
//			// TODO 处理扫描结果 0
//			Toast.makeText(this, scanResult, Toast.LENGTH_LONG).show();
//			if (scanResult.indexOf("http//") != -1) {
//				Uri uri = Uri.parse(scanResult);
//				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//				startActivity(intent);
//			}
//		}
	}

	
	
	
	
	

