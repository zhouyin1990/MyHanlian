package com.example.hanlian.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.alipay.sdk.app.PayTask;
import com.android.volley.VolleyError;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import utils.TCHConstants;

public class PayUtil {
	Activity activity;
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;
	//private double price; // 订单 标题 描述 价格
	private String sign = "";//签名
	private String orderid = TCHConstants.url.orderid;//用户ID

	private CallbackListener callbackListener; // 回调修改订单状态的接口

	public interface CallbackListener {
		public void updateOrderState(); // 修改订单状态
        
	}
	
	public PayUtil(Activity activity, String orderid, CallbackListener callbackListener) {
		this.activity = activity;
		this.orderid = orderid;
		this.callbackListener = callbackListener;
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				Log.e("payResult", payResult.toString());				
				// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
				String resultInfo = payResult.getResult();
				String resultStatus = payResult.getResultStatus();
				String memo = payResult.getMemo();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					callbackListener.updateOrderState(); // 调用回调接口修改订单状态
				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(activity, "支付结果确认中", Toast.LENGTH_SHORT).show();
					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			}
			default:
				break;
			}
		};
	};
	
	public void pay(){
		
		HashMap<String, String> params = new HashMap<String , String >();		
		params.put("orderid",orderid);
	//	params.put("money", price +"");
		
		HTTPUtils.get(activity, TCHConstants.url.AliPay, params, new ResponseListener() {
			
			@Override
			public void onResponse(String arg0) {
				try {
					sign = new JSONObject(arg0.toString()).getString("Result");
					// 仅需对sign 做URL编码
//					sign = URLEncoder.encode(sign, "UTF-8"); 
//					Log.e("签名", sign);
					Runnable payRunnable = new Runnable() {
						
						@Override
						public void run() {
							PayTask alipay = new PayTask(activity);
							String result = alipay.pay(sign, true); // 调用支付接口进行支付
							
//							Map<String, String> result = alipay.payV2(sign, false);
							Log.e("支付接口", result.toString());
							
							Message msg = new Message();
							msg.what = SDK_PAY_FLAG;
							msg.obj = result;
							mHandler.sendMessage(msg);
						}
					};
					
					// 必须异步调用
					Thread payThread = new Thread(payRunnable);
					payThread.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
			}
		});
		
	}

}
