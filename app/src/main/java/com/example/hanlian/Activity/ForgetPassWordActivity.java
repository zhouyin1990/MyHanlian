package com.example.hanlian.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.hanlian.R;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.TCHConstants;

public class ForgetPassWordActivity extends AppCompatActivity {


    @BindView(R.id.img_forgetpasswordback)
    ImageView imgForgetpasswordback;
    @BindView(R.id.ed_newpassword)
    EditText edNewpassword;
    @BindView(R.id.ed_oldpassword)
    EditText edOldpassword;
    @BindView(R.id.ed_confimpassword)
    EditText edConfimpassword;
    @BindView(R.id.btn)
    Button btnOrder;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.linearLayout3)
    RelativeLayout linearLayout3;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.linearlayout1)
    RelativeLayout linearlayout1;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.linearLayout4)
    RelativeLayout linearLayout4;
    private AlertView alertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        imgForgetpasswordback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 修改密码
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = edOldpassword.getText().toString().trim();
                String newPassWord = edNewpassword.getText().toString().trim();
                String confimpassword = edConfimpassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPassword)) {
                    showAlertView("请填旧写密码");
                    edOldpassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(newPassWord)) {
                    showAlertView("请填写新密码");
                    edNewpassword.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(confimpassword)) {
                    showAlertView("请填写确认密码");
                    edConfimpassword.requestFocus();
                    return;
                }
                if (!TextUtils.equals(newPassWord, confimpassword)) {
                    showAlertView("两次密码不一致，请检查");
                    edConfimpassword.requestFocus();
                    return;
                }

                SharedPreferences sp = getSharedPreferences("登录", 1);
                String token = sp.getString("Token", "");
                HashMap<String, String> parms = new HashMap<>();
                parms.put("oldpassword", oldPassword);
                parms.put("newpassword", newPassWord);
                parms.put("confirmpassword", confimpassword);
                parms.put("token", token);
                HTTPUtils.get(ForgetPassWordActivity.this, TCHConstants.url.ModifyPassword, parms, new ResponseListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ForgetPassWordActivity.this, "ErrorCode=" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s) {
                        try {

                            JSONObject jsonObject = new JSONObject(s);
                            int errcode = jsonObject.getInt("ErrorCode");
                            if (errcode == 0) {
                                showAlertView2("修改成功");
                            } else {
                                Toast.makeText(ForgetPassWordActivity.this,
                                        "修改失败-" + errcode, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void showAlertView(String message) {
        alertView = new AlertView("提示", message, "确定", null, null,
                ForgetPassWordActivity.this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                alertView.dismiss();
            }
        });
        alertView.show();
    }

    private void showAlertView2(String message) {
        alertView = new AlertView("提示", message, "确定", null, null,
                ForgetPassWordActivity.this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                alertView.dismiss();
                Intent intent = new Intent(ForgetPassWordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        alertView.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (alertView != null && alertView.isShowing()) {
            alertView.dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R.id.tv_detail_title)
    public void onViewClicked() {

    }
}
