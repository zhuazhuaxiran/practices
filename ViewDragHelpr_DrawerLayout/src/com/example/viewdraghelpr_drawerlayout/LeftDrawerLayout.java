package com.example.viewdraghelpr_drawerlayout;

import com.example.viewdraghelpr_drawerlayout.utils.L;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class LeftDrawerLayout extends ViewGroup {

	private static final int MIN_DRAWER_MARGIN=64;  //dp
	/**
     * Minimum velocity that will be detected as a fling
     */
	private static final int MIN_FLING_VELOCITY=400;  //dips per second

	/**
	 * drawer离父容器右边的最小外边距
	 */
	private int mMinDrawerMargin;
	
	private View mContentView;
	private View mLeftMenuView;
	/**
     * drawer显示出来的占自身的百分比
     */
	private float mLeftMenuOnScrren;
	private ViewDragHelper mDragger;
	
	
	public LeftDrawerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//setup drawer's minMargin
		final float density=getResources().getDisplayMetrics().density;
		final float minVel=MIN_FLING_VELOCITY*density;
		
		mMinDrawerMargin=(int) (MIN_DRAWER_MARGIN*density+0.5f);
		
		mDragger=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				 L.e("tryCaptureView");
				return child==mLeftMenuView;
			}
			
			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				int  newLeft=Math.max(-child.getWidth(), Math.min(left, 0));
				return newLeft;
			}
			
			@Override
			public void onEdgeDragStarted(int edgeFlags, int pointerId) {
				  L.e("onEdgeDragStarted");
	              mDragger.captureChildView(mLeftMenuView, pointerId);
			}
			
			@Override
			public void onViewReleased(View releasedChild, float xvel,
					float yvel) {
				L.e("onViewReleased");
				final int childWidth=releasedChild.getWidth();
				float offset=(childWidth+releasedChild.getLeft())*1.0f/childWidth;
				mDragger.settleCapturedViewAt(xvel > 0 || xvel == 0 && offset > 0.5f ? 0 : -childWidth, releasedChild.getTop());
				invalidate();
			}
			@Override
			public void onViewPositionChanged(View changedView, int left,
					int top, int dx, int dy) {
				final int childWidth=changedView.getWidth();
				float offset=(childWidth+left)/childWidth;
				mLeftMenuOnScrren=offset;
				
				//offset  can callback here
				changedView.setVisibility(offset==0 ? View.INVISIBLE:View.VISIBLE);
				invalidate();
			}
			
			@Override
			public int getViewHorizontalDragRange(View child) {
				return mLeftMenuView == child ? child.getWidth() : 0;
			}
		});
		  //设置edge_left track
		mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
		mDragger.setMinVelocity(minVel);
	}

	  @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	    {
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

	        setMeasuredDimension(widthSize, heightSize);

	        View leftMenuView = getChildAt(1);
	        MarginLayoutParams lp = (MarginLayoutParams)
	                leftMenuView.getLayoutParams();

	        final int drawerWidthSpec = getChildMeasureSpec(widthMeasureSpec,
	                mMinDrawerMargin + lp.leftMargin + lp.rightMargin,
	                lp.width);
	        final int drawerHeightSpec = getChildMeasureSpec(heightMeasureSpec,
	                lp.topMargin + lp.bottomMargin,
	                lp.height);
	        leftMenuView.measure(drawerWidthSpec, drawerHeightSpec);


	        View contentView = getChildAt(0);
	        lp = (MarginLayoutParams) contentView.getLayoutParams();
	        final int contentWidthSpec = MeasureSpec.makeMeasureSpec(
	                widthSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
	        final int contentHeightSpec = MeasureSpec.makeMeasureSpec(
	                heightSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);
	        contentView.measure(contentWidthSpec, contentHeightSpec);

	        mLeftMenuView = leftMenuView;
	        mContentView = contentView;


	    }

	    @Override
	    protected void onLayout(boolean changed, int l, int t, int r, int b)
	    {
	        View menuView = mLeftMenuView;
	        View contentView = mContentView;

	        MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
	        contentView.layout(lp.leftMargin, lp.topMargin,
	                lp.leftMargin + contentView.getMeasuredWidth(),
	                lp.topMargin + contentView.getMeasuredHeight());

	        lp = (MarginLayoutParams) menuView.getLayoutParams();

	        final int menuWidth = menuView.getMeasuredWidth();
	        int childLeft = -menuWidth + (int) (menuWidth * mLeftMenuOnScrren);
	        menuView.layout(childLeft, lp.topMargin, childLeft + menuWidth,
	                lp.topMargin + menuView.getMeasuredHeight());
	    }

	    @Override
	    public boolean onInterceptTouchEvent(MotionEvent ev)
	    {
	        boolean shouldInterceptTouchEvent = mDragger.shouldInterceptTouchEvent(ev);
	        return shouldInterceptTouchEvent;
	    }


	    @Override
	    public boolean onTouchEvent(MotionEvent event)
	    {
	        mDragger.processTouchEvent(event);
	        return true;
	    }


	    @Override
	    public void computeScroll()
	    {
	        if (mDragger.continueSettling(true))
	        {
	            invalidate();
	        }
	    }

	    public void closeDrawer()
	    {
	        View menuView = mLeftMenuView;
	        mLeftMenuOnScrren = 0.f;
	        mDragger.smoothSlideViewTo(menuView, -menuView.getWidth(), menuView.getTop());
	    }

	    public void openDrawer()
	    {
	        View menuView = mLeftMenuView;
	        mLeftMenuOnScrren = 1.0f;
	        mDragger.smoothSlideViewTo(menuView, 0, menuView.getTop());
	    }

	    @Override
	    protected LayoutParams generateDefaultLayoutParams()
	    {
	        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    }

	    public LayoutParams generateLayoutParams(AttributeSet attrs)
	    {
	        return new MarginLayoutParams(getContext(), attrs);
	    }

	    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
	    {
	        return new MarginLayoutParams(p);
	    }

	}