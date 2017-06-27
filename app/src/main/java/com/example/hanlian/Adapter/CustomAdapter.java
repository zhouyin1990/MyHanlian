package com.example.hanlian.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CustomAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> mDatas;
	private int layoutID = -1;
	private View layoutView ;
	
	public CustomAdapter(Context mContext, List<T> mDatas, int layoutID) {
		super();
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.layoutID = layoutID;
	}
	
	public CustomAdapter(Context mContext, List<T> mDatas, View view) {
		super();
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.layoutView = view;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (layoutID == -1) {
			holder = ViewHolder.getViewHolder(mContext, parent, convertView, layoutView, position);
		}else{
			holder = ViewHolder.getViewHolder(mContext, parent, convertView, layoutID, position);
		}
		convert(holder, getItem(position));

		return holder.getConvertView();
	}
	
	/**
	 * getView()用户自己实现 
	 * @param holder
	 * @param t
	 */
	public abstract void convert(ViewHolder holder, T t);

}
