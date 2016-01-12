package com.example.customview2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class CustomView2 extends View {

	private static final int IMAGE_SCALE_FITXY = 0;
	private Bitmap mImage;
	private int mImageScale;
	private String mTitle;
	private int mTextColor;
	private int mTextSize;
	private Rect rect;
	private Paint mPaint;
	private Rect mTextBound;
	private int mWidth;
	private int mHeight;

	public CustomView2(Context context){
		this(context,null);
	}
	public CustomView2(Context context,AttributeSet attrs){
		this(context,attrs,0);
	}
	
	//����Զ�������
	public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray td=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView2, defStyleAttr, 0);
		
		int count=td.getIndexCount();
		
		for(int i=0;i<count;i++){
			int attr=td.getIndex(i);
			switch (attr) {
			case R.styleable.CustomView2_image:  
                mImage = BitmapFactory.decodeResource(getResources(), td.getResourceId(attr, 0));  
                break;  
            case R.styleable.CustomView2_imageScaleType:  
                mImageScale = td.getInt(attr, 0);  
                break;  
            case R.styleable.CustomView2_titleText:  
                mTitle = td.getString(attr);  
                break;  
            case R.styleable.CustomView2_titleTextColor:  
                mTextColor = td.getColor(attr, Color.BLACK);  
                break;  
            case R.styleable.CustomView2_titleTextSize:  
                mTextSize = td.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,  
                        16, getResources().getDisplayMetrics()));  
                break;  
			}
		}
		
		td.recycle();
		rect = new Rect();  
        mPaint = new Paint();  
        mTextBound = new Rect();  
        mPaint.setTextSize(mTextSize);  
        // ���������������Ҫ�ķ�Χ  
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound); 
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		/** 
	     * ���ÿ�� 
	     */  
	    int specMode = MeasureSpec.getMode(widthMeasureSpec);  
	    int specSize = MeasureSpec.getSize(widthMeasureSpec);  
	  
	    if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate  
	    {  
	        Log.e("xxx", "EXACTLY");  
	        mWidth = specSize;  
	    } else  
	    {  
	        // ��ͼƬ�����Ŀ�  
	        int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();  
	        // ����������Ŀ�  
	        int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();  
	  
	        if (specMode == MeasureSpec.AT_MOST)// wrap_content  
	        {  
	        	//�������ֺ�ͼƬ˭�Ŀ�ȴ󣬲���˭
	            int desire = Math.max(desireByImg, desireByTitle);
	            //�����ĺ�ϵͳ���Ǹ�С����˭��
	            mWidth = Math.min(desire, specSize);  
	            Log.e("xxx", "AT_MOST");  
	        }  
	    }  
	  
	    /*** 
	     * ���ø߶� 
	     */  
	  
	    specMode = MeasureSpec.getMode(heightMeasureSpec);  
	    specSize = MeasureSpec.getSize(heightMeasureSpec);  
	    if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate  
	    {  
	        mHeight = specSize;  
	    } else  
	    {  
	        int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();  
	        if (specMode == MeasureSpec.AT_MOST)// wrap_content  
	        {  
	            mHeight = Math.min(desire, specSize);  
	        }  
	    }  
	    setMeasuredDimension(mWidth, mHeight);  
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
        /** 
         * �߿� 
         */  
        mPaint.setStrokeWidth(4);  
        mPaint.setStyle(Paint.Style.STROKE);  
        mPaint.setColor(Color.CYAN);  
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);  
  
        rect.left = getPaddingLeft();  
        rect.right = mWidth - getPaddingRight();  
        rect.top = getPaddingTop();  
        rect.bottom = mHeight - getPaddingBottom();  
  
        mPaint.setColor(mTextColor);  
        mPaint.setStyle(Style.FILL);  
        /** 
         * ��ǰ���õĿ��С��������Ҫ�Ŀ�ȣ��������Ϊxxx... 
         */  
        if (mTextBound.width() > mWidth)  
        {  
            TextPaint paint = new TextPaint(mPaint);  
            String msg = TextUtils.ellipsize(mTitle, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),  
                    TextUtils.TruncateAt.END).toString();  
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);  
  
        } else  
        {  
            //������������������  
            canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);  
        }  
  
        //ȡ��ʹ�õ��Ŀ飬��������ռ�õĲ���
        rect.bottom -= mTextBound.height();  
  
        if (mImageScale == IMAGE_SCALE_FITXY)  
        {  
            canvas.drawBitmap(mImage, null, rect, mPaint);  
        } else  
        {  
            //������еľ��η�Χ  
            rect.left = mWidth / 2 - mImage.getWidth() / 2;  
            rect.right = mWidth / 2 + mImage.getWidth() / 2;  
            rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;  
            rect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;  
  
            canvas.drawBitmap(mImage, null, rect, mPaint);  
        }  
  
	}
}
