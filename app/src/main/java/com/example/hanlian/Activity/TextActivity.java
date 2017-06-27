package com.example.hanlian.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanlian.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends AppCompatActivity {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

     private List<String> mdata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        for (int i = 0; i <mdata.size() ; i++) {

            mdata.add("item"+i);

        }
        recycleview.setLayoutManager(new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false));
        recycleview.setAdapter(new Myadapter());

    }

 class  Myadapter extends RecyclerView.Adapter<MyViewHolder>
 {


     @Override
     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View inflate = getLayoutInflater().inflate(R.layout.recycleitem, null);
         return new MyViewHolder(inflate);
     }

     @Override
     public void onBindViewHolder(MyViewHolder holder, int position) {
         String data = mdata.get(position);
         holder.settitle(data);

     }

     @Override
     public int getItemCount() {
         return mdata.size();
     }
 }


    class MyViewHolder extends  RecyclerView.ViewHolder
    {


        private final TextView tv_recyle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            tv_recyle = (TextView) itemView.findViewById(R.id.tv_recycle);

        }

         public void settitle (String titel)
         {
              tv_recyle.setText(titel);
         }
    }



}
