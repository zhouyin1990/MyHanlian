package com.example.hanlian.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.DateModel.DetailBean;
import com.example.hanlian.DateModel.LoginModel;
import com.example.hanlian.R;
import com.google.gson.Gson;
//import com.umeng.message.PushAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import loginModel.Login;
import okhttp3.Call;
import utils.CountDownButtonHelper;
import utils.CountDownButtonHelper.OnFinishListener;
import utils.TCHConstants;

public class LoginActivity extends Activity implements OnClickListener{

	
	private EditText mpassname;
	private EditText mpassword;
	private EditText mshuruyzm;
	private EditText srphone;
	private CountDownButtonHelper helper;
	private Button btn_getyzm;
	private TextView  tv_wangjimima ;


	SharedPreferences sp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
//		PushAgent.getInstance(this).onAppStart();

		sp = this.getSharedPreferences("登录", 1);

		intiUi();		
	}	
	private void intiUi() {
		mpassname = (EditText) findViewById(R.id.ed_passname);
		mpassword = (EditText) findViewById(R.id.ed_password);
		mshuruyzm = (EditText) findViewById(R.id.ed_shuruyzm);
		srphone = (EditText) findViewById(R.id.ed_phNum);
		btn_getyzm = (Button) findViewById(R.id.btn_getyzm);
	    tv_wangjimima = (TextView) findViewById(R.id.tv_wangjimima);
		tv_wangjimima.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

		mpassname.setOnClickListener(this);
		mpassword.setOnClickListener(this);
		mshuruyzm.setOnClickListener(this);
		srphone.setOnClickListener(this);
		btn_getyzm.setOnClickListener(this);//获取验证码
		tv_wangjimima.setOnClickListener(this);

		mpassname.setText(sp.getString("username", ""));
		srphone.setText(sp.getString("phone", ""));


		findViewById(R.id.btn_login).setOnClickListener(this);//登录

         findViewById(R.id.tv_call).setOnClickListener(v ->
				 {

					 Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:4006009388"));
					 if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
						 return;
					 }
					 startActivity(intent);

				 }
		 );


	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_getyzm: //获取验证码
			String phNum = srphone.getText().toString();
			
	//		Toast.makeText(LoginActivity.this, "phNum"+phNum, Toast.LENGTH_SHORT).show();
			if(!"".equals(phNum)){
				//倒计时
				TimeHelp();
				//获取验证码
				getCode();
			}else{
				Toast.makeText(LoginActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.btn_login: //登录
		     login();
//			startActivity(new Intent(LoginActivity.this, MainActivity.class));
//			finish();

			break;
		case R.id.ed_phNum: //手机验证是否符合规则
			break;
		case R.id.tv_wangjimima :
			 Intent intent10= new Intent(LoginActivity.this, FindPassWordActivity.class);
			 startActivity(intent10);  
			break;
		default:
		}
	}			
	private void TimeHelp(){
		helper = new CountDownButtonHelper(btn_getyzm, "已发送",60, 1);
		btn_getyzm.setEnabled(false);
		
        helper.setOnFinishListener(new OnFinishListener() {

            @Override
            public void finish() {
            	btn_getyzm.setEnabled(true);
            	btn_getyzm.setText("发送验证码");
            }
        });
        helper.start();
	}

	private void getCode() {
		//获取验证码
		String phNum = srphone.getText().toString().trim();

        OkHttpUtils.get().addParams("phone",phNum).url(TCHConstants.url.getcode).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                Log.e("response==",response);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    int errorCode = jsonObject.getInt("ErrorCode");
                    if (errorCode==0)
                    {
                        //获取成功
                        Toast.makeText(LoginActivity.this, "验证码获取成功，请注意查收短信", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        Toast.makeText(LoginActivity.this, "验证码获取失败", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

	}


	private void login() //登录
	{

//		SharedPreferences sp = getSharedPreferences("登录", 1);

		final String usrname = mpassname.getText().toString().trim();

	    final String userpassword = mpassword.getText().toString().trim();
		final String useryzm = mshuruyzm.getText().toString().trim();
		final String phone = srphone.getText().toString().trim();



		if(TextUtils.isEmpty(usrname)){
			  Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
			  return;
		  }
		  if(TextUtils.isEmpty(userpassword)){
			  Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			  return;
		  }
		  if(TextUtils.isEmpty(useryzm)){
			  Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
			  return;
		  }
		  
		  //登录接口
		HashMap<String, String> map = new HashMap<>();
		map.put("account",usrname);
		map.put("password",userpassword);
		map.put("code",useryzm);

		HTTPUtils.get(LoginActivity.this, TCHConstants.url.Login, map, new ResponseListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Toast.makeText(LoginActivity.this, "volleyError="+volleyError, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onResponse(String arg0) {
				LoginModel loginJSON = new Gson().fromJson(arg0, LoginModel.class);
				int errorCode = loginJSON.getErrorCode();
				if(errorCode ==0)
				{
					String token = loginJSON.getToken();
                    TCHConstants.url.token=token;
                    Log.e("logintoen==",token);
					LoginModel.ResultBean result = loginJSON.getResult();
					String cm_shopeaddress = result.getCM_SHOPEADDRESS();

					String cm_name = result.getCM_NAME();
					int cm_integral = result.getCM_INTEGRAL();
					long cm_phone = result.getCM_PHONE();
//					SharedPreferences sp = getSharedPreferences("登录", 1);
					Editor editor = sp.edit();
					editor.putString("success", "登录成功");
					//保存用户名，
					editor.putString("username", usrname);
					editor.putString("phone", phone);

					editor.putString("cm_shopeaddress",cm_shopeaddress);

                    editor.putString("cm_name",cm_name);
					editor.putString("cm_phone",""+cm_phone);

					editor.putInt("cm_integral",cm_integral);

					editor.commit();

					startActivity(new Intent(LoginActivity.this, MainActivity.class));
					finish();
				}else if (errorCode ==6)
				{
					Toast.makeText(LoginActivity.this ,"账号或密码错误 请检查" ,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

}

