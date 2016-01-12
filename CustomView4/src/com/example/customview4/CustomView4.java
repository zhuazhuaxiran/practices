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
     * ��һȦ����ɫ 
     */  
    private int mFirstColor;  
  
    /** 
     * �ڶ�Ȧ����ɫ 
     */  
    private int mSecondColor;  
    /** 
     * Ȧ�Ŀ�� 
     */  
    private int mCircleWidth;  
    /** 
     * ���� 
     */  
    private Paint mPaint;  
    /** 
     * ��ǰ���� 
     */  
    private int mCurrentCount = 3;  
  
    /** 
     * �м��ͼƬ 
     */  
    private Bitmap mImage;  
    /** 
     * ÿ������ļ�϶ 
     */  
    private int mSplitSize;  
    /** 
     * ���� 
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
	
		//��ȡ�Զ�������
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
				mCount=td.getInt(attr, 20);  //Ĭ��ֵ20
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

	//��дonDraw����
	@Override
	protected void onDraw(Canvas canvas) {
		
		mPaint.setAntiAlias(true);  //�������
		mPaint.setStrokeWidth(mCircleWidth);  //����Բ���Ŀ��
		mPaint.setStrokeCap(Paint.Cap.ROUND);  //�����߶ζϵ���״ΪԲͷ
		mPaint.setStyle(Paint.Style.STROKE);   //���ÿ���
		
		int center=getWidth()/2;  //��ȡԲ�ĵ�x����
		int radius=center-mCircleWidth/2;   //�뾶
		
		/**
		 * ��С���
		 */
		drawOval(canvas, center, radius);
		/**
		 * �������������ε�λ��
		 */
		int relRadius=radius-mCircleWidth/2;//�����Բ�İ뾶
		
		/**
		 *relRadius��ȥ(����֮���Ŷ� ����relRadius)  +mCircleWidth,  Ϊ��Բ���������Σ� ��������ߵ�x�������
		 */
		
		mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
		
		mRect.right=(int) (mCircleWidth+relRadius+Math.sqrt(2) * 1.0f / 2 * relRadius);
		
		mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
		mRect.bottom=(int) (mRect.left + Math.sqrt(2) * relRadius);
		
		
		/**
		 * ���ͼƬ�Ƚ�С����ô����ͼƬ�ĳߴ���õ�������
		 */
		//ͼƬ�Ŀ��С�����������εı߳�
		if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
			//ǰ�벿��������Բ��Բ�ĵ�x�������
			mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f/ 2 - mImage.getWidth() * 1.0f / 2);
			mRect.right = (int) (mRect.left + mImage.getWidth());
			//ǰ�벿��������Բ��Բ�ĵ�y�������
			mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
			mRect.bottom = (int) (mRect.top + mImage.getHeight());
		}
		  // ��ͼ  
        canvas.drawBitmap(mImage, null, mRect, mPaint);  
	}
	
	/**
	 * ���ݲ�������ÿ��С��
	 * @param canvas
	 * @param center
	 * @param radius
	 */
	private void drawOval(Canvas canvas, int center, int radius) {
		//������Ҫ���ĸ����Լ���϶����ÿ��С����ռ�ı���  360
		float itemSize=(360*1.0f-mCount*mSplitSize)/mCount;
		
		//���ڶ����Բ������״�ʹ�С�Ľ���
		RectF oval=new RectF(center-radius, center-radius, center+radius, center+radius);
		
		mPaint.setColor(mFirstColor);
		for(int i=0;i<mCount;i++){
			//���ݽ��Ȼ�Բ��
			canvas.drawArc(oval, i*(itemSize+mSplitSize), itemSize, false, mPaint);
		}
		mPaint.setColor(mSecondColor);
		for(int i=0;i<mCurrentCount;i++){
			//���ݽ��Ȼ�Բ��
			canvas.drawArc(oval, i*(itemSize+mSplitSize), itemSize, false, mPaint);
		}
	}
	
	/**
	 * ��ǰ����+1
	 */
	public void up()
	{
		mCurrentCount++;
		postInvalidate();
	}

	/**
	 * ��ǰ����-1
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
			if (xUp > xDown)// �»�
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
