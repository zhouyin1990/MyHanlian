package com.example.hanlian.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.hanlian.R;
import com.xinbo.utils.ScreenUtils;

public class MyView extends View
{

	private  float cx = 15;
	private float cy = 8;
	private Paint paint1;
	private Paint paint2;
	private Paint paint3;
	private float offset;
	private int num;
	private ViewPager mpager;
	private float radius;
	private int color_bg;
	private int color_force;
	private int color_ring;
	private float dimension;
	private float distance;

	public MyView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Indicator);
		context.obtainStyledAttributes(attrs, R.styleable.Indicator);
		//圆角
		radius = ta.getDimension(R.styleable.Indicator_content_radius, 6);
		//背景色
		color_bg = ta.getColor(R.styleable.Indicator_content_bgcolor, Color.WHITE);
		//前景色
		color_force = ta.getColor(R.styleable.Indicator_content_forecolor,Color.RED);
		//环 色
		color_ring = ta.getColor(R.styleable.Indicator_content_ringcolor,Color.RED);
		//圆环宽度
		dimension = ta.getDimension(R.styleable.Indicator_content_ringwidth, 0);
		//距离
		distance = ta.getFloat(R.styleable.Indicator_content_distance,5);
		ta.recycle();
		initPain();
		
	}
	
	private void initPain()
	{
		//Բ
		paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint1.setColor(color_bg);
		//�ƶ���Բ
		paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint2.setColor(color_force);
		//Բ��
		paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint3.setStyle(Style.STROKE);
		paint3.setStrokeWidth(dimension);
		paint3.setColor(color_ring);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		int screenWidth = ScreenUtils.getScreenWidth(getContext());
		cx = (float) (screenWidth/2 - (num-1)*1.5*radius);
		for (int i = 0; i < num; i++)
		{
			canvas.drawCircle(cx + i*distance*radius, cy, radius, paint1);
			canvas.drawCircle(cx + i*distance*radius, cy, radius, paint3);
		}
		
		canvas.drawCircle(cx + offset, cy, radius, paint2);
	}
	
	public void move(int position,float percent)
	{
		offset = percent*distance*radius + position*distance*radius;
		if(position == num - 1)
		{
			offset = position * distance * radius;
		}
		invalidate();
	} 
	public void setRealNum(int num)
	{
		this.num = num;
	}
	
	public void setIndicator(ViewPager mpager)
	{
		
		this.mpager = mpager;
		this.num = mpager.getAdapter().getCount();
	}
}
