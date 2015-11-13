package com.example.customview4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class CustomView4 extends View {

	/** 
     * 第一圈的颜色 
     */  
    private int mFirstColor;  
  
    /** 
     * 第二圈的颜色 
     */  
    private int mSecondColor;  
    /** 
     * 圈的宽度 
     */  
    private int mCircleWidth;  
    /** 
     * 画笔 
     */  
    private Paint mPaint;  
    /** 
     * 当前进度 
     */  
    private int mCurrentCount = 3;  
  
    /** 
     * 中间的图片 
     */  
    private Bitmap mImage;  
    /** 
     * 每个块块间的间隙 
     */  
    private int mSplitSize;  
    /** 
     * 个数 
     */  
    private int mCount;  
  
    private Rect mRect;  
	
	
	
	
	public CustomView4(Context context){
		this(context,null);
	}
	public CustomView4(Context context,AttributeSet attrs){
		this(context,attrs,0);
	}
	public CustomView4(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	
		//获取自定义属性
		TypedArray td=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView4, defStyleAttr, 0);
		
		int count=td.getIndexCount();
		for(int i=0;i<count;i++){
			int attr=td.getIndex(i);
			switch (attr) {
			case R.styleable.CustomView4_firstColor:
				mFirstColor=td.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomView4_secondColor:
				mSecondColor=td.getColor(attr, Color.WHITE);
				break;
			case R.styleable.CustomView4_circleWidth:
				mCircleWidth=td.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
				break;
			case R.styleable.CustomView4_dotCount:
				mCount=td.getInt(attr, 20);  //默认值20
				break;
			case R.styleable.CustomView4_splitSize:
				mSplitSize=td.getInt(attr, 20);
				break;
			case R.styleable.CustomView4_bg:
				mImage=BitmapFactory.decodeResource(getResources(), td.getResourceId(attr, 0));
			}
		}
		td.recycle();
		mPaint=new Paint();
		mRect=new Rect();
	}

	//重写onDraw方法
	@Override
	protected void onDraw(Canvas canvas) {
		
		mPaint.setAntiAlias(true);  //消除锯齿
		mPaint.setStrokeWidth(mCircleWidth);  //设置圆环的宽度
		mPaint.setStrokeCap(Paint.Cap.ROUND);  //定义线段断电形状为圆头
		mPaint.setStyle(Paint.Style.STROKE);   //设置空心
		
		int center=getWidth()/2;  //获取圆心的x坐标
		int radius=center-mCircleWidth/2;   //半径
		
		/**
		 * 画小块块
		 */
		drawOval(canvas, center, radius);
		/**
		 * 计算内切正方形的位置
		 */
		int relRadius=radius-mCircleWidth/2;//获得内圆的半径
		
		/**
		 *relRadius减去(二分之根号二 乘以relRadius)  +mCircleWidth,  为内圆内切正方形， 左边那条边的x轴的坐标
		 */
		
		mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
		
		mRect.right=(int) (mCircleWidth+relRadius+Math.sqrt(2) * 1.0f / 2 * relRadius);
		
		mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
		mRect.bottom=(int) (mRect.left + Math.sqrt(2) * relRadius);
		
		
		/**
		 * 如果图片比较小，那么根据图片的尺寸放置到正中心
		 */
		//图片的宽度小于内切正方形的边长
		if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
			//前半部分是内切圆的圆心的x轴的坐标
			mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f/ 2 - mImage.getWidth() * 1.0f / 2);
			mRect.right = (int) (mRect.left + mImage.getWidth());
			//前半部分是内切圆的圆心的y轴的坐标
			mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
			mRect.bottom = (int) (mRect.top + mImage.getHeight());
		}
		  // 绘图  
        canvas.drawBitmap(mImage, null, mRect, mPaint);  
	}
	
	/**
	 * 根据参数画出每个小块
	 * @param canvas
	 * @param center
	 * @param radius
	 */
	private void drawOval(Canvas canvas, int center, int radius) {
		//根据需要画的个数以及间隙计算每个小块所占的比例  360
		float itemSize=(360*1.0f-mCount*mSplitSize)/mCount;
		
		//用于定义的圆弧的形状和大小的界限
		RectF oval=new RectF(center-radius, center-radius, center+radius, center+radius);
		
		mPaint.setColor(mFirstColor);
		for(int i=0;i<mCount;i++){
			//根据进度画圆弧
			canvas.drawArc(oval, i*(itemSize+mSplitSize), itemSize, false, mPaint);
		}
		mPaint.setColor(mSecondColor);
		for(int i=0;i<mCurrentCount;i++){
			//根据进度画圆弧
			canvas.drawArc(oval, i*(itemSize+mSplitSize), itemSize, false, mPaint);
		}
	}
	
	/**
	 * 当前数量+1
	 */
	public void up()
	{
		mCurrentCount++;
		postInvalidate();
	}

	/**
	 * 当前数量-1
	 */
	public void down()
	{
		mCurrentCount--;
		postInvalidate();
	}

	private int xDown, xUp;

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{

		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			xDown = (int) event.getY();
			break;

		case MotionEvent.ACTION_UP:
			xUp = (int) event.getY();
			if (xUp > xDown)// 下滑
			{
				down();
			} else
			{
				up();
			}
			break;
		}
		return true;
	}
}
