package com.example.hanlian.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanlian.Fragment.FireElementFragment;
import com.example.hanlian.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.img_receoce)
    ImageView imgReceoce;
    @BindView(R.id.tv_orderdetail_adress)
    TextView tvOrderdetailAdress;
    @BindView(R.id.lv_ordedetail)
    ListView lvOrdedetail;
    private String cmorderid;
    private SharedPreferences sp;
    private String cm_shopeaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initData() {


    }

    private void initView() {
        sp = getSharedPreferences("登录", 1);
        cm_shopeaddress = sp.getString("cm_shopeaddress", "");
        Intent intent = getIntent();
        cmorderid = intent.getStringExtra("cmorderid");
        tvOrderdetailAdress.setText(cm_shopeaddress); //地址

    }

    @OnClick(R.id.lv_ordedetail)
    public void onViewClicked() {
        lvOrdedetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }


    class MyAdapter extends BaseAdapter {

        private View layout;
        ViewHolder viewHolder1;


        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                layout = getLayoutInflater().inflate(R.layout.lv_orderdetailitem, null);
                viewHolder1 = new ViewHolder(convertView);
                layout.setTag(viewHolder1);
            } else {

                layout = convertView;
                viewHolder1 = (ViewHolder) layout.getTag();

            }
//            viewHolder1.tvColor.setText();
//            viewHolder1.tvJianyishenggao.setText()



            return layout;
        }


         class ViewHolder {
            @BindView(R.id.tv_color)
            TextView tvColor;
            @BindView(R.id.tv_state)
            TextView tvState;
            @BindView(R.id.relativeLayout)
            RelativeLayout relativeLayout;
            @BindView(R.id.textView18)
            TextView textView18;
            @BindView(R.id.tv_jianyishenggao)
            TextView tvJianyishenggao;
            @BindView(R.id.tv_number)
            TextView tvNumber;

             ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

//
//    class MyViewHolder {
//        TextView tv_state;
//        TextView tv_color;
//        TextView tv_jianyishenggao;
//        TextView tv_number;
//
//    }


}
