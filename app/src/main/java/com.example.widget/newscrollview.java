package com.example.widget;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import android.content.Context;
import android.util.AttributeSet;

public class newscrollview  extends PullToRefreshScrollView
{

	
	public interface ScrollViewListener {

		void onScrollChanged(newscrollview scrollView, int x, int y,
                             int oldx, int oldy);

	}

	private ScrollViewListener scrollViewListener = null;

	public newscrollview(Context context) {
		super(context);
	}

	public newscrollview(Context context, AttributeSet attrs,
			int defStyle) {
	//	super(context, attrs, defStyle);  报错
		super(context, attrs);
	}

	public newscrollview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}
	
	

}
