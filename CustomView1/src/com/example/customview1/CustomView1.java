package com.example.customview1;

import java.util.HashSet;
import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomView1 extends View {

	/** 
     * 文本 
     */  
    private String mTitleText;  
    /** 
     * 文本的颜色 
     */  
    private int mTitleTextColor;  
    /** 
     * 文本的大小 
     */  
    private int mTitleTextSize;  
  
    /** 
     * 绘制时控制文本绘制的范围 
     */  
    private Rect mBound;  
    private Paint mPaint;  
	public CustomView1(Context context){
		this(context,null);
	}
	 public CustomView1(Context context, AttributeSet attrs)  
	    {  
	        this(context, attrs, 0);  
	    }  
	public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		TypedArray td=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView1, defStyleAttr, 0);
		
		int count=td.getIndexCount();
		
		for(int i=0;i<count;i++){
			int attr=td.getIndex(i);
			switch (attr) {
			case R.styleable.CustomView1_titleText:
				mTitleText=td.getString(attr);
				break;
			case R.styleable.CustomView1_titleTextColor:
				mTitleTextColor=td.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomView1_titleTextSize:
				mTitleTextSize=td.getDimensionPixelSize(attr, 
						(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
				break;
			default:
				break;
			}
		}
		td.recycle();
		 /** 
         * 获得绘制文本的宽和高 
         */  
        mPaint = new Paint();  
        mPaint.setTextSize(mTitleTextSize);  
        mBound = new Rect();  
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);  
        
        
        //添加点击事件
        this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTitleText=randomText();
				invalidate();
			}
		});
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int widthMode=MeasureSpec.getMode(widthMeasureSpec);
		int widthSize=MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
	    int heightSize = MeasureSpec.getSize(heightMeasureSpec);  
	    int width;  
	    int height ;  
		
	    if (widthMode == MeasureSpec.EXACTLY)  
	    {  
	        width = widthSize;  
	    } else  
	    {  
	        mPaint.setTextSize(mTitleTextSize);  
	        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);  
	        float textWidth = mBound.width();  
	        //文本宽度加上左右的padding值
	        int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());  
	        width = desired;  
	    }  
	  
	    if (heightMode == MeasureSpec.EXACTLY)  
	    {  
	        height = heightSize;  
	    } else  
	    {  
	        mPaint.setTextSize(mTitleTextSize);  
	        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);  
	        float textHeight = mBound.height();  
	        //文本高度加上上下的padding值
	        int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());  
	        height = desired;  
	    }
	  
	    setMeasuredDimension(width, height);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
		
		mPaint.setColor(mTitleTextColor);
		canvas.drawText(mTitleText, getWidth()/2-mBound.width()/2, getHeight()/2+mBound.height()/2, mPaint);
	}
	
	
	private String randomText(){
		
		Random random=new Random();

		HashSet<Integer> set=new HashSet<Integer>();
		while(set.size()<4){
			int randomInt=random.nextInt(10);
			set.add(randomInt);
		}
		StringBuffer sb=new StringBuffer();
		for(Integer i:set){
			sb.append(""+i);
		}
		return sb.toString();
	}
}
