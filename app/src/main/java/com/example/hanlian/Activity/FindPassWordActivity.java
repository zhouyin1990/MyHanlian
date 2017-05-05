package com.example.hanlian.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.Fristpager.Fristpager;
import com.example.hanlian.R;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import utils.TCHConstants;
import utils.ValidatorUtils;

public class FindPassWordActivity extends Activity implements OnClickListener  
{
	private EditText edphone;	
	int type ;
	private String strphone ;
	String code_yzm ;
	private EditText ed_yzm;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_pass_word);
		initUI();

	}


	private void initUI() {	
		
		findViewById(R.id.img_findpasswordback).setOnClickListener(this);
		edphone = (EditText) findViewById(R.id.ed_phone_find);
		
		findViewById(R.id.btn_yonghuming).setOnClickListener(this);
		findViewById(R.id.btn_mima).setOnClickListener(this);		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_findpasswordback:
			finish();
			break;
		case R.id.btn_yonghuming:
			type=1 ;
			showdigao();
			
			break;
		case R.id.btn_mima:
			showPassWordigao();	
			type=2 ;
			break;
		default:
			break;
		}				
	}
	private void showPassWordigao() {		
	    strphone =edphone.getText().toString().trim();
		boolean mobile = ValidatorUtils.isMobile(strphone);
		if(strphone!=null && mobile==true)
		{   
			getpassWordCode(strphone); 
			//dialog();
		}else
		{
			Toast.makeText(FindPassWordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
			return ;
		}		
	}
	private void showdigao() {		
	    strphone = edphone.getText().toString().trim();
		boolean mobile = ValidatorUtils.isMobile(strphone);
		if(strphone!=null && mobile==true)
		{   
			getUseCode(); 			
		}else
		{
			Toast.makeText(FindPassWordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
			return;
		}	
	}

	private void dialog() 
	{
		final Dialog dialog = new Dialog(FindPassWordActivity.this, R.style.MyDialog);
		View view1 = LayoutInflater.from(FindPassWordActivity.this).inflate(R.layout.finddialog, null);
		dialog.setContentView(view1);
		
		ed_yzm = (EditText) view1.findViewById(R.id.edit_findyzm);		
		
				
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
					if(type==1 )
					{
						//找回用户名 TODO // 如何发送到手机
					  Findusename();
					}else if (type == 2)
					{
					//	重置密码  
						getfindPassWord();					
					}					
					dialog.dismiss();
				}
			}
           

		});
		dialog.show();
	}
	
	   // 重置秘密dialog
	private void getfindPassWord() {
		    final String code_yzm = ed_yzm.getText().toString().trim();
			final String strphone = edphone.getText().toString().trim();
			final Dialog dialog = new Dialog(this, R.style.MyDialog);
		        View view = LayoutInflater.from(this).inflate(R.layout.item_resetting_password, null);
		        dialog.setContentView(view);
		        view.findViewById(R.id.tv_quit).setOnClickListener(new OnClickListener() {
		            @Override
		            public void onClick(View v) {
		            dialog.dismiss();
		            }
		        });
		        final EditText passwordEdit = (EditText) view.findViewById(R.id.et_password);
		        final EditText passwordAgainEdit = (EditText) view.findViewById(R.id.et_password_again);
		        view.findViewById(R.id.tv_confirm).setOnClickListener(new OnClickListener() {
		            @Override
		            public void onClick(View v) {
		                String password = passwordEdit.getText().toString().trim();
		                String passwordAgain = passwordAgainEdit.getText().toString().trim();
		                if (TextUtils.isEmpty(password)) {
		                    Toast.makeText(FindPassWordActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
		                    return;
		                }
		                if (TextUtils.isEmpty(passwordAgain)) {
		                    Toast.makeText(FindPassWordActivity.this, "请再次填写密码", Toast.LENGTH_SHORT).show();
		                    return;
		                }
		                if (!TextUtils.equals(password, passwordAgain)) {
		                    Toast.makeText(FindPassWordActivity.this, "两次密码不一致，请检查", Toast.LENGTH_SHORT).show();
		                    return;
		                }
		                  //重置密码
		                resettingPassword(code_yzm, password, passwordAgain,strphone);
		                dialog.dismiss();
		            }					
		        });
		        dialog.show(); 			 			 						
		}
	
	private void resettingPassword(String code_yzm, String password, String passwordAgain,String strphone) {
			Map<String, String> params =new HashMap<String, String>();	
			params.put("code", code_yzm);
			params.put("newpassword" ,password);
			params.put("confirmpassword", passwordAgain);
			params.put("phone" ,strphone);

			 HTTPUtils.get(FindPassWordActivity.this, TCHConstants.url.ResettingPassword,params , new ResponseListener() {
					
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						try {
							JSONObject jsonObject = new JSONObject(arg0);
							int errorcode = jsonObject.getInt("ErrorCode");
							if(errorcode==0)
							{
							    Toast.makeText(FindPassWordActivity.this, "重置密码成功", Toast.LENGTH_SHORT).show();
							}else
							{
								Toast.makeText(FindPassWordActivity.this, "请求成功 重置失败"+errorcode, Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}		

					}
					@Override
					public void onErrorResponse(VolleyError arg0) {
						Toast.makeText(FindPassWordActivity.this, "arg0"+arg0, Toast.LENGTH_SHORT).show();
						
					}
				});				 
			
			
		}

    // 找回用户名
	private void Findusename() {
		
	   String code_yzm = ed_yzm.getText().toString().trim();
	   strphone = edphone.getText().toString().trim();
        Map<String, String> params =new HashMap<String, String>();				
		params.put("code", code_yzm);
		params.put("phone" ,strphone);
		HTTPUtils.get(FindPassWordActivity.this, TCHConstants.url.BackAccount, params , new ResponseListener() {
			@Override
			public void onResponse(String arg0) {
				try {
					JSONObject jsonObject = new JSONObject(arg0);
					int errorcode = jsonObject.getInt("ErrorCode");
					if(errorcode==0)
					{
						String result = jsonObject.getString("Result");
						showNameUseDialog(result);
					}else
					{
						Toast.makeText(FindPassWordActivity.this, "请求成功 获取失败", Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}					
			
			@Override
			public void onErrorResponse(VolleyError arg0) {	
				Toast.makeText(FindPassWordActivity.this, "请求失败"+arg0.toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void showNameUseDialog(String result) {
		
		    final Dialog dialog = new Dialog(this, R.style.MyDialog);
	        View view2 = LayoutInflater.from(this).inflate(R.layout.dialog_score222, null);
	        dialog.setContentView(view2);
	        ((TextView) view2.findViewById(R.id.tv_sure)).setText("确定");     
	        
	        ((TextView) view2.findViewById(R.id.textView1)).setText("找回成功,请牢记用户名:");

	        ((TextView) view2.findViewById(R.id.tv_account)).setText(result);      

	        view2.findViewById(R.id.tv_sure).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View arg0) {
	                if (dialog != null && dialog.isShowing()) {
	                    dialog.dismiss();
	                    finish();
	                }
	            }
	        });
	        dialog.show();	
				
	}
	
    //找回用户验证码	
	private void getUseCode() {				
	 strphone = edphone.getText().toString().trim();
		
		Map<String, String> params =new HashMap<String, String>();
		params.put("phone", strphone);			
		HTTPUtils.get(FindPassWordActivity.this, TCHConstants.url.SendBackAccountCode, params , new ResponseListener() {
			@Override
			public void onResponse(String arg0) {
							
				try {
					int errcode  = new JSONObject(arg0.toString()).getInt("ErrorCode");
					if(errcode ==0){
					  dialog();
			          Toast.makeText(FindPassWordActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
					}else{
						
						Toast.makeText(FindPassWordActivity.this, "验证码发送失败"+errcode, Toast.LENGTH_SHORT).show();
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
	
	
    //找回发送密码的code
    private void getpassWordCode(String strphone) {
		Map<String, String> params2 =new HashMap<String, String>();
		params2.put("phone",strphone);
		HTTPUtils.get(FindPassWordActivity.this, TCHConstants.url.SendRetrieveCode, params2 , new ResponseListener() {
			@Override
			public void onResponse(String arg0) {							
				try {
					int errcode  = new JSONObject(arg0.toString()).getInt("ErrorCode");
					if(errcode ==0){
						dialog();
						Toast.makeText(FindPassWordActivity.this, "发送验证码成功，请注意查收", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(FindPassWordActivity.this, "errcode="+errcode, Toast.LENGTH_SHORT).show();
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
	
    
    
}
