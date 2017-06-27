package com.example.hanlian.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenuListView;

public class MyListview  extends ListView{
	public MyListview(Context context) {
		super(context);
	}

	public MyListview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec
				,MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST));
	}
	  

}
