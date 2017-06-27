package com.example.hanlian.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanlian.DateModel.WuliuBean;
import com.example.hanlian.R;
import com.xinbo.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import utils.JsonHelper;
import utils.LogUtil;
import utils.TCHConstants;

public class LogisticsActivity extends AppCompatActivity {


    @BindView(R.id.tv_express_name)
    TextView tvExpressName;
    @BindView(R.id.express_listview)
    ListView expressListview;

    @BindView(R.id.tv_express_number)
    TextView tv_express_number;
    private ExpressAdapter expressAdapter;

    @BindView(R.id.img_daoshouhuo_back)
    ImageView  img_daoshouhuo_back ;

    private  String TAG ="LogisticsActivity==" ;

    private int selectItem = -1;

    private JSONArray data;// 厂家信息

    private ArrayList<String> logisticsinfoListcontext =new ArrayList<>();
    private ArrayList<String> logisticsinfoListtime =new ArrayList<>();
    int mSelect =0 ; //选中项

    ViewHolder viewHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        ButterKnife.bind(this);
        intiUI();
        InitData();
    }

    private void InitData() {
        //返回
        img_daoshouhuo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HashMap<String, String> parms = new HashMap<>();
        parms.put("orderdetailid",TCHConstants.url.Orderdetailsids);

        OkHttpUtils.get().params(parms).url(TCHConstants.url.Logistices).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("物流error log==", ""+e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("logistices response==",response);


                try {
                    //返回物流信息json
                    JSONObject wuliujson = new JSONObject(response);
                    // 物流字符串
                    String result = wuliujson.getString("Result");
                    // 转物流信息对象
                    JSONObject jsonObject = new JSONObject(result);

                    JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");

                    String mailNo = showapi_res_body.getString("mailNo");
                    String expTextName = showapi_res_body.getString("expTextName");

                    tvExpressName.setText(expTextName);
                    tv_express_number.setText(mailNo);





//                    Log.e("expTextName==",expTextName);
//                    Log.e("mailNo==",mailNo);

                    LogUtil.v("expTextName ==",""+expTextName);
                    JSONArray data = showapi_res_body.getJSONArray("data");
                    for (int i = 0; i <data.length() ; i++) {
                        String time = data.getJSONObject(i).getString("time");
                        String context = data.getJSONObject(i).getString("context");

                        Log.e(" time==",time);
                        Log.e(" context==",context);

                        logisticsinfoListcontext.add(context);
                        logisticsinfoListtime.add(time);
                    }

                    Log.e("size ==",logisticsinfoListtime.size()+"");

                    Log.e("logisticsinfoListtime==",logisticsinfoListtime.size()+"");



                    expressAdapter = new ExpressAdapter();
                    expressListview.setAdapter(expressAdapter);
                    expressAdapter.notifyDataSetChanged();

              } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void intiUI() {



        expressListview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                expressAdapter.changeSelected(position);

                if(mSelect==position)
                {
                    viewHolder.tv_view.setVisibility(View.INVISIBLE);


                }else
                {
                    viewHolder.tv_view.setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class ExpressAdapter extends BaseAdapter
    {


        private View layout;


        public void changeSelected(int positon){ //刷新方法
            if(positon != mSelect){
                mSelect = positon;
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {


            return logisticsinfoListcontext.size();
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
             ViewHolder viewHolder =null ;

            if(convertView == null)
            {
                layout = getLayoutInflater().inflate(R.layout.logistics_lv_item, null);
                viewHolder=new ViewHolder();
                viewHolder.tv_logistics_content= (TextView)layout.findViewById(R.id.tv_logistics_content);
                viewHolder.tv_logistics_time=(TextView)layout.findViewById(R.id.tv_logistics_time);
                viewHolder.tv_view=(TextView)layout.findViewById(R.id.tv_view);
                layout.setTag(viewHolder);
            }else
            {
                layout = convertView;
                viewHolder = (ViewHolder) layout.getTag();
            }


            // 物流时间和信息

            if(logisticsinfoListcontext.size()!=0 && logisticsinfoListtime.size()!=0)
            {
             viewHolder.tv_logistics_content.setText(logisticsinfoListcontext.get(position));
             viewHolder.tv_logistics_time.setText(logisticsinfoListtime.get(position));
            }else
            {

            }

            return layout;
        }
    }


   class  ViewHolder
   {
       TextView  tv_logistics_content ;
       TextView  tv_logistics_time ;
       TextView  tv_view ;
   }



}
