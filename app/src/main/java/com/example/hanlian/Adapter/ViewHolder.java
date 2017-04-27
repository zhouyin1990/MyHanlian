package com.example.hanlian.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> mViews;
	private View mConvertView;
	private int mPosition;
//	private static LoadImage mImageLoader  = new LoadImage();

	public ViewHolder(Context context, ViewGroup parent, int layoutID,
			int position) {
		this.mPosition = position;
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutID, parent,
				false);
		mConvertView.setTag(this);
	}
	public ViewHolder(Context context, ViewGroup parent, View view,
			int position) {
		this.mPosition = position;
		mViews = new SparseArray<View>();
		mConvertView = view;
		mConvertView.setTag(this);
	}

	public static ViewHolder getViewHolder(Context context, ViewGroup parent,
                                           View convertView, int layoutID, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutID, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	public static ViewHolder getViewHolder(Context context, ViewGroup parent,
                                           View convertView, View view, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, view, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	public View getConvertView() {
		return mConvertView;
	}

	public <T extends View> T getView(int viewID) {
		View view = mViews.get(viewID);
		if (view == null) {
			view = mConvertView.findViewById(viewID);
			mViews.put(viewID, view);
		}
		return (T) view;
	}

	/**
	 * 在网络上获取图片并设置给imageView
	 * @param viewID
	 * @param url
	 * @param resID 图片加载的时候默认显示的资源
	 * @return
	 */
//	public ViewHolder setImageForURL(int viewID, String url, int resID) {
//		ImageView iv = getView(viewID);
//		iv.setTag(url);
//		iv.setImageResource(resID);
//		mImageLoader.getBitmapFromAsyncTask(iv, url);
//		return this;
//	}
	/**
	 * 为TextView设置文字抽取处理
	 * @param viewID
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewID, String text) {
		TextView tv = getView(viewID);
		tv.setText(text);
		return this;
	}

	/**
	 * 为imageView设置图片、通过resource查找资源
	 * @param viewID
	 * @param resID
	 * @return
	 */
	public ViewHolder setImageResource(int viewID, int resID) {
		ImageView iv = getView(viewID);
		iv.setImageResource(resID);
		return this;
	}

	/**
	 * 通过bitmap对象为imageView设置背景
	 * 
	 * @param viewID
	 * @param bitmap
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewID, Bitmap bitmap) {
		ImageView iv = getView(viewID);
		iv.setImageBitmap(bitmap);
		return this;
	}
	
	/**
	 * 为View设置背景图片、通过resource查找资源
	 * 
	 * @param viewID
	 * @param bitmap
	 * @return   
	 */
	public ViewHolder setBackgroundDrawable(int viewID, int drawable) {
		View iv = getView(viewID);
		iv.setBackgroundResource(drawable);
		return this;
	}
	
	/**
	 * 设置CheckBox选中状态
	 * @param viewID
	 * @param isChecked
	 * @return
	 */
	public ViewHolder setCheckBoxState(int viewID, boolean isChecked){
		CheckBox cb = getView(viewID);
		cb.setChecked(isChecked);
		return this;
	}
}
