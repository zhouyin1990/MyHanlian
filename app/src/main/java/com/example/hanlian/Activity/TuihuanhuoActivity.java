package com.example.hanlian.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hanlian.R;
import com.example.hanlian.TestToken.TestToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.umeng.message.PushAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shouhoushenqingModel.Result;
import shouhoushenqingModel.Shouhoushenqing;
import shouhoushenqingModel.TBORDERDETAIL;
import utils.TCHConstants;

public class TuihuanhuoActivity extends Activity implements OnClickListener {

    private ListView mlistview_jindu;
    private ListView mlistview_shouhou;
    private int type;
    private int pageNum = 1;
    private int pageSize = 0;
    private DisplayImageOptions options;
    private Button sh, jd;

    private ArrayList<Result> shouhoulist = new ArrayList<Result>();
    private ArrayList<TBORDERDETAIL> tb_list = new ArrayList<TBORDERDETAIL>();
    private ImageView imgtuhuanhuo_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuihuanhuo);
        PushAgent.getInstance(this).onAppStart();

        initView();
        intiUIL();
        intidata();

    }

    private void intidata() {

        HashMap<String, String> paramsshouhou = new HashMap<String, String>();
        paramsshouhou.put("pageNum", pageNum + "");
        paramsshouhou.put("pageSize", pageSize + "");
        paramsshouhou.put("token", TCHConstants.url.token);

        HTTPUtils.get(TuihuanhuoActivity.this, TCHConstants.url.MYshouhou, paramsshouhou, new ResponseListener() {

            private Integer errorCode2;

            @Override
            public void onResponse(String arg0) {
                // TODO Auto-generated method stub
                Shouhoushenqing shouhoujson = GsonUtils.parseJSON(arg0, Shouhoushenqing.class);
                errorCode2 = shouhoujson.getErrorCode();
                String token = shouhoujson.getToken();
                TCHConstants.url.token=token;
                Toast.makeText(TuihuanhuoActivity.this,
                        "token == " + token, Toast.LENGTH_SHORT).show();
                if (errorCode2 == 0) {
                    List<Result> result = shouhoujson.getResult();
                    if (result != null && result.size() != 0) {
                        shouhoulist.clear();
                        shouhoulist.addAll(result);
                        ShouhouAdapter shouhouAdapter = new ShouhouAdapter();
                        mlistview_shouhou.setAdapter(shouhouAdapter);
                        shouhouAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(TuihuanhuoActivity.this,
                            "ErrorCode = " + errorCode2, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }


    private void intiUIL() {
        // TODO Auto-generated method stub

        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.err)
                // 设置图片在下载期间显示的默认图片
                .showImageForEmptyUri(R.drawable.err)
                // 设置URI错误或者为空时显示的图片
                .showImageOnFail(R.drawable.err)
                // 设置图片加载或者解析出现错误时设置的图片
                .cacheInMemory(true)
                // 是否内存缓存
                .cacheOnDisk(true)
                // 是否外存缓存
                .considerExifParams(true)
                // .displayer(new RoundedBitmapDisplayer(100))// 是否设置图片圆角
                .displayer(new FadeInBitmapDisplayer(400))// 图片加载好之后渐入的时间
                .build();

    }

    private void initView() {
        sh = (Button) findViewById(R.id.button1);
        jd = (Button) findViewById(R.id.button2);
        jd.setOnClickListener(this);
        sh.setOnClickListener(this);
        TextView mTextView = (TextView) findViewById(R.id.textView1);
        mlistview_jindu = (ListView) findViewById(R.id.listview_jindu);
        mlistview_shouhou = (ListView) findViewById(R.id.listview_shouhou);
        imgtuhuanhuo_back = (ImageView) findViewById(R.id.imgtuhuanhuo_back);
        imgtuhuanhuo_back.setOnClickListener(this);
    }


    class ShouhouAdapter extends BaseAdapter {

        View layout = null;
        ViewHolder holder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            if (convertView == null) {
                holder = new ViewHolder();
                layout = getLayoutInflater().inflate(R.layout.shouhou_listview_item, null);
                holder.img_shouhou = (ImageView) layout.findViewById(R.id.img_shouhou);
                holder.tv_dingdanbianhao = (TextView) layout.findViewById(R.id.tv_dingdanbianhao);
                holder.tv_order_time = (TextView) layout.findViewById(R.id.tv_order_time);
                holder.tv_state = (TextView) layout.findViewById(R.id.tv_state);
                holder.tv_title = (TextView) layout.findViewById(R.id.tv_title);
                holder.tv_salenumber = (TextView) layout.findViewById(R.id.tv_salenumber);
                holder.btn_sqshouhou = (Button) layout.findViewById(R.id.btn_sqshouhou);
                layout.setTag(holder);
            } else {
                layout = convertView;
                holder = (ViewHolder) layout.getTag();
            }
            String cmcreatetime = shouhoulist.get(position).getCMCREATETIME();
            holder.tv_order_time.setText("下单时间：" + cmcreatetime);
            String cmorderid = shouhoulist.get(position).getCMORDERID();
            holder.tv_dingdanbianhao.setText("订单编号：" + cmorderid);
            List<TBORDERDETAIL> tborderdetails = shouhoulist.get(position).getTBORDERDETAILS();
            tb_list.clear();
            tb_list.addAll(tborderdetails);
            Integer cmnumber = tb_list.get(0).getCMNUMBER();
            holder.tv_salenumber.setText("销量x" + cmnumber);
            String cmtitle = tb_list.get(0).getCMTITLE();
            holder.tv_title.setText(cmtitle);
            Integer cmservicestate = tb_list.get(0).getCMSERVICESTATE();
            Integer cmorderstate = shouhoulist.get(position).getCMORDERSTATE();

            if (cmorderstate == 0) {
                holder.tv_state.setText("正常");
            } else if (cmorderstate == 1) {
                holder.tv_state.setText("换货");
            } else if (cmorderstate == 2) {
                holder.tv_state.setText("退货");
            } else if (cmorderstate == 3) {
                holder.tv_state.setText("返修");
            } else if (cmorderstate == 4) {
                holder.tv_state.setText("完成");
            }
            String cmmainfigurepath = tb_list.get(0).getCMMAINFIGUREPATH();
            if (cmmainfigurepath != null) {
                ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + cmmainfigurepath, holder.img_shouhou, options);
            }

            return layout;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return shouhoulist.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    class ViewHolder {
        ImageView img_shouhou;
        TextView tv_dingdanbianhao;
        TextView tv_order_time;
        TextView tv_state;
        TextView tv_title;
        TextView tv_salenumber;
        Button btn_sqshouhou;

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.imgtuhuanhuo_back:
                finish();
                break;
            case R.id.button1:
                sh.setTextColor(getResources().getColor(R.color.white));
                sh.setBackgroundColor(getResources().getColor(R.color.red));
                jd.setTextColor(getResources().getColor(R.color.red));
                jd.setBackgroundColor(getResources().getColor(R.color.white));
                mlistview_shouhou.setVisibility(View.VISIBLE);
                mlistview_jindu.setVisibility(View.GONE);
                break;
            case R.id.button2:
                sh.setTextColor(getResources().getColor(R.color.red));
                sh.setBackgroundColor(getResources().getColor(R.color.white));
                jd.setTextColor(getResources().getColor(R.color.white));
                jd.setBackgroundColor(getResources().getColor(R.color.red));
                mlistview_shouhou.setVisibility(View.GONE);
                mlistview_jindu.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }


    }

}
