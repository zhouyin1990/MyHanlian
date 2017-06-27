package com.example.hanlian.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanlian.R;
import com.example.hanlian.alipay.PayUtil;

import UnpayModel.Unpay;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.TCHConstants;

public class PayActivity extends AppCompatActivity {

    @BindView(R.id.re_alipay)
    RelativeLayout reAlipay;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.tv_jifen_back)
    ImageView Imag_Back;
    @BindView(R.id.confim_pay)
    Button confimPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        //订单money
        tvOrderMoney.setText(TCHConstants.url.ordermoney);


    }

    @OnClick({R.id.tv_jifen_back, R.id.confim_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_jifen_back:
                break;
            case R.id.confim_pay:
                aliipay();
                break;
        }
    }
    private void aliipay() {
        PayUtil payUtil = new PayUtil(PayActivity.this, TCHConstants.url.orderid, new PayUtil.CallbackListener() {
            @Override
            public void updateOrderState() {
                Log.e("支付成功", "支付成功后执行");
                Intent intent =new Intent(PayActivity.this,AllOrderActivity.class);
                startActivity(intent);
            }
        });
        payUtil.pay();
    }
}
