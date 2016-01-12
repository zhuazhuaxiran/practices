package com.example.viewdraghelper_smalldemo;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class VDHLayout extends LinearLayout {

	//����view����Ӧ�����еĿؼ�
	private View mDragView;
	private View mAutoBackView;
	private View mEdgeTrackerView;
	
	private Point mAutoBackOriginPos=new Point();
	private ViewDragHelper mDragger;
	public VDHLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		/**
		 * Parameters
			forParent 	Parent view to monitor
			sensitivity 	Multiplier for how sensitive the helper should be about detecting the start of a drag. Larger values are more sensitive. 1.0f is normal.
			cb 	Callback to provide information and receive events
		 */
		mDragger=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
					/**
					 * A Callback is used as a communication channel with the
					 * ViewDragHelper back to the parent view using it.
					 */
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				
//				return false;
//				return true;
				//mEdgeTrackerView��ֱֹ���ƶ�
				return child==mDragView||child==mAutoBackView;
				
			}
			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				
//			return super.clampViewPositionHorizontal(child, left, dx);
				return left;
			}
			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {

//				return super.clampViewPositionVertical(child, top, dy);
				return top;
			}
			
			
			// ��ָ�ͷŵ�ʱ�����
			@Override
			public void onViewReleased(View releasedChild, float xvel,
			float yvel) {
				
				//mAutoBackView��ָ�ͷ�ʱ�����Զ���ȥ
				if(releasedChild==mAutoBackView){
					mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
					invalidate();
				}
				
			}
			
			//�ڱ߽��϶�ʱ�ص�
			
			@Override
			public void onEdgeDragStarted(int edgeFlags, int pointerId) {
				
				mDragger.captureChildView(mEdgeTrackerView, pointerId);
			}
			//�ڲ��¼��������ĵ���¼���ʱ��
			@Override
			public int getViewHorizontalDragRange(View child)
			{
			     return getMeasuredWidth()-child.getMeasuredWidth()*4;
			}

			@Override
			public int getViewVerticalDragRange(View child)
			{
			     return getMeasuredHeight()-child.getMeasuredHeight();
			}
		});
		
		mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		//�ҵ��޸�
		/*mDragger.shouldInterceptTouchEvent(ev);
		return true;*/
		return mDragger.shouldInterceptTouchEvent(ev);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDragger.processTouchEvent(event);
		return true;
	}
	
	@Override
	public void computeScroll() {
		if(mDragger.continueSettling(true)){
			
			invalidate();
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		
		mAutoBackOriginPos.x=mAutoBackView.getLeft();
		mAutoBackOriginPos.y=mAutoBackView.getTop();
		
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mDragView=getChildAt(0);
		mAutoBackView=getChildAt(1);
		mEdgeTrackerView=getChildAt(2);
	}
}
