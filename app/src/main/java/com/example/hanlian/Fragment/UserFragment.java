package com.example.hanlian.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.Activity.AllOrderActivity;
import com.example.hanlian.Activity.CollectionActivity;
import com.example.hanlian.Activity.DaishouhuoActivity;
import com.example.hanlian.Activity.ForgetPassWordActivity;
import com.example.hanlian.Activity.GoodsDetailActivity;
import com.example.hanlian.Activity.JiFenActivity;
import com.example.hanlian.Activity.KeFuActivity;
import com.example.hanlian.Activity.LoginActivity;
import com.example.hanlian.Activity.MoneyPagceActivity;
import com.example.hanlian.Activity.TuihuanhuoActivity;
import com.example.hanlian.Activity.UnPayActivity;
import com.example.hanlian.DateModel.personinfo;
import com.example.hanlian.R;
import com.google.gson.Gson;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import utils.TCHConstants;

/**
 * A simple {@link Fragment} subclass.
 *        我的
 */
public class UserFragment extends Fragment implements OnClickListener{

	

	private View layout;
	private RelativeLayout recollection;
	private TextView unregist;
	private TextView jifeninfo , tv_quanbudingdan,tv_daifukuang,tv_daishouhuo,tv_tuihuanhuo;	
	private RelativeLayout re_forgetPassWord;
	private TextView tv_goforget;
	private TextView shopname;
	private TextView tv_jifen;
	private TextView tv_adress1;
	private TextView tv_usename;
	private SharedPreferences sp;

	public UserFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(layout == null )
		{
			layout = inflater.inflate(R.layout.fragment_user, container, false);		
			intiUI();
			intidata();
			
		}else
		{
			ViewGroup parent = (ViewGroup) layout.getParent();
	        if (parent!=null){	        	
	          parent.removeAllViewsInLayout();
	        }
		}
		return layout;
	}					

	private void intidata() {
		intidouxiangjifen(); // 头像积分
	}	
	
	
	
	
	private void intidouxiangjifen() {		
	    // 刷新token
					Map<String, String> parms = new HashMap<String, String>();
					parms.put("token", TCHConstants.url.token);

		OkHttpUtils.get().params(parms).url(TCHConstants.url.QueryMyInformationurl).build().execute(new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int id) {
				Log.e("Exception e==",e+"");
			}

			@Override
			public void onResponse(String response, int id) {

//				personinfo personinfo = new Gson().fromJson(response, personinfo.class);

				try {
					JSONObject jsonObject = new JSONObject(response);
					String token = jsonObject.getString("Token");
					TCHConstants.url.token=token;

					String cm_shopname = jsonObject.getJSONObject("Result").getString("CM_SHOPNAME");
					String cm_integral = jsonObject.getJSONObject("Result").getString("CM_INTEGRAL");

					String cm_name = jsonObject.getJSONObject("Result").getString("CM_NAME");
					String CM_SHOPEADDRESS = jsonObject.getJSONObject("Result").getString("CM_SHOPEADDRESS");


					jifeninfo.setText("积分:"+cm_integral);

					shopname.setText("店铺名:"+cm_shopname);

					tv_usename.setText("用户名:"+cm_name);

					tv_adress1.setText(CM_SHOPEADDRESS);

				} catch (JSONException e) {
					e.printStackTrace();
				}


			}
		});


	}

	private void intiUI() {

		sp = getContext().getSharedPreferences("登录", 1);
		recollection = (RelativeLayout) layout.findViewById(R.id.re_collection);
		recollection.setOnClickListener(this);
		layout.findViewById(R.id.re_jifen).setOnClickListener(this);
		layout.findViewById(R.id.re_kefu).setOnClickListener(this);
		layout.findViewById(R.id.re_moneypagck).setOnClickListener(this);
		unregist = (TextView) layout.findViewById(R.id.tv_unrigster); //注销
		jifeninfo = (TextView) layout.findViewById(R.id.tv_jifen1);
		tv_quanbudingdan = (TextView) layout.findViewById(R.id.tv_quanbudingdan);
		tv_daifukuang = (TextView) layout.findViewById(R.id.tv_daifukung);	
		tv_daishouhuo = (TextView) layout.findViewById(R.id.tv_daishouhuo);
		tv_tuihuanhuo = (TextView) layout.findViewById(R.id.tv_tuihuanhuo);	
		tv_quanbudingdan.setOnClickListener(this);
		tv_daifukuang.setOnClickListener(this);
		tv_daishouhuo.setOnClickListener(this);
		tv_tuihuanhuo.setOnClickListener(this);	
		unregist.setOnClickListener(this);

		shopname = (TextView) layout.findViewById(R.id.shop_name);
		tv_jifen = (TextView) layout.findViewById(R.id.tv_jifen);
		tv_adress1 = (TextView) layout.findViewById(R.id.tv_detialadress);
		tv_usename = (TextView) layout.findViewById(R.id.tv_usename);


		re_forgetPassWord = (RelativeLayout) layout.findViewById(R.id.re_xiugai_password);
		re_forgetPassWord.setOnClickListener(this);

		tv_goforget = (TextView) layout.findViewById(R.id.tv_go1);
		tv_goforget.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {		
			switch (v.getId()) {
			case R.id.re_collection :  //收藏
				Intent intent=new Intent(getActivity(), CollectionActivity.class) ;
				startActivity(intent);				
				break;
			case R.id.tv_quanbudingdan :  //全部订单
				Intent orderintent=new Intent(getActivity(), AllOrderActivity.class) ;
				startActivity(orderintent);				
				break;
			case R.id.tv_daifukung :  //代付款
				Intent unpayintent=new Intent(getActivity(), UnPayActivity.class) ;
				startActivity(unpayintent);				
				break;
			case R.id.tv_daishouhuo :  //待收货
				Intent daishouhuointent=new Intent(getActivity(), DaishouhuoActivity.class) ;
				startActivity(daishouhuointent);				
				break;			
			case R.id.tv_tuihuanhuo :  //退换货
				Intent tuihuanhuointent=new Intent(getActivity(), TuihuanhuoActivity.class) ;
				startActivity(tuihuanhuointent);

				startActivity(new Intent(getContext(),TuihuanhuoActivity.class));



				break;
			case  R.id.re_jifen:// 积分
				Intent jifenintent=new Intent(getActivity(), JiFenActivity.class) ;				
				startActivity(jifenintent);
				break; 
			case  R.id.re_kefu: // 客服
				Intent intent1=new Intent(getActivity(), KeFuActivity.class) ;				
				startActivity(intent1);
				break; 
 			case  R.id.re_moneypagck: // 钱包 
 				Intent intent3=new Intent(getActivity(),MoneyPagceActivity.class) ;				
				startActivity(intent3);
				break; 				
			case R.id.tv_unrigster :
				// 清空登录token 跳转到登录界面
				cleartoken();			
				break;

				case R.id.re_xiugai_password :
					Intent intent13=new Intent(getActivity(), ForgetPassWordActivity.class) ;
					startActivity(intent13);
					break;
				case R.id.tv_go1 :
					Intent intent14=new Intent(getActivity(), ForgetPassWordActivity.class) ;
					startActivity(intent14);
					break;
			default:
			}
		
	}

	private void cleartoken() {
	    Editor editor = sp.edit();
		String token = sp.getString("Token","");
		editor.clear();
		editor.commit();
		Intent intent= new Intent(getContext(), LoginActivity.class);
		startActivity(intent);
		getActivity().finish();
	    }

	}



