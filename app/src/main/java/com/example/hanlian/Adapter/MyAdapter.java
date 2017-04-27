package com.example.hanlian.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanlian.Activity.GoodsSortDetail;
import com.example.hanlian.MyApplication.MyApplication;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import utils.TCHConstants;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

	private List<GoodsInfo> mGoodsInfo;
	private Context mContext;

	public MyAdapter(List<GoodsInfo> goodsInfo, Context context) {
        mGoodsInfo = goodsInfo;
        mContext = context;
    }
	
	static class ViewHolder extends RecyclerView.ViewHolder {
        View goodsView;
        ImageView goodsImage;
        TextView goodsName;

        public ViewHolder(View view) {
            super(view);
            goodsView = view;
            goodsImage = (ImageView) view.findViewById(R.id.item_sort_image);
            goodsName = (TextView) view.findViewById(R.id.item_sort_name);
        }
    }

	@Override
	public int getItemCount() {
		return mGoodsInfo.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		GoodsInfo goods = mGoodsInfo.get(position);
        ImageLoader.getInstance().displayImage(TCHConstants.url.imgurl + goods.getImage(),holder.goodsImage, MyApplication.options);
        holder.goodsName.setText(goods.getName());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_sort, arg0, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.goodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GoodsInfo fruit = mGoodsInfo.get(position);
            }
        });
        holder.goodsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GoodsInfo goods = mGoodsInfo.get(position);             
                Intent intent = new Intent(mContext, GoodsSortDetail.class);
                intent.putExtra("sort", goods.name);
                mContext.startActivity(intent);
            }
        });
        return holder;
	}

}
