package com.example.hanlian.Activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.example.hanlian.R;

public class FristActivity extends Activity {
	private ImageView welcomeimg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frist);
//		PushAgent.getInstance(this).onAppStart();
		welcomeimg  = (ImageView) findViewById(R.id.Img_welcome);
		AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
		anima.setDuration(100);
		welcomeimg.startAnimation(anima);
		anima.setAnimationListener(new AlphaAnima());
	}
	//��������
		private class AlphaAnima implements AnimationListener
		{
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				SharedPreferences sp = getSharedPreferences("登录", 1);
				String success = sp.getString("success","");


//				if("登录成功".equals(success)){
//					//已经登录的用户 直接进入app
//					startActivity(new Intent(FristActivity.this, MainActivity.class ));
//					finish();
//				}else{
//					//未登录的用户 跳转登录页面
					startActivity(new Intent(FristActivity.this, MainActivity.class ));
//					startActivity(new Intent(FristActivity.this,LoginActivity.class ));
					finish();
//				}
//
//				startActivity(new Intent(FristActivity.this, MainActivity.class ));
//				finish();

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			//��������
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				return false;
			}
			return super.onKeyDown(keyCode, event);
		}
		
	
}
