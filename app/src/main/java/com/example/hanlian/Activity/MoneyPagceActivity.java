package com.example.hanlian.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.hanlian.R;
import com.umeng.message.PushAgent;

public class MoneyPagceActivity extends Activity implements OnClickListener {

	private Button btn_tixian;
	private Button btn_chongzhi;
	private TextView mtv_yue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_money_pagce);
		PushAgent.getInstance(this).onAppStart();
		intiUI();
	}

	private void intiUI() {
		btn_tixian = (Button) findViewById(R.id.btn_tixian);
		btn_chongzhi = (Button) findViewById(R.id.btn_chongzhi);
		btn_tixian.setOnClickListener(this);
		btn_chongzhi.setOnClickListener(this);
		mtv_yue = (TextView) findViewById(R.id.tv_yue);
		findViewById(R.id.tv_money_back).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tixian://提现			
			break;
		case R.id.btn_chongzhi://充值
			
			break;
		case R.id.tv_money_back://
			finish();
			break;

		default:
			break;
		}
		
	}

	
}
