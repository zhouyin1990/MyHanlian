package com.example.hanlian.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.hanlian.R;
import com.umeng.message.PushAgent;

public class KeFuActivity extends Activity  implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ke_fu);
		PushAgent.getInstance(this).onAppStart();
		intiUI();
	}

	private void intiUI() {
     findViewById(R.id.tv_kefu_back).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		  switch (v.getId()) {
		case R.id.tv_kefu_back:

			finish();
			
			break;

		default:
			break;
		}
	}

	
}
