package com.example.slidingmenu_viewpager_tabindicator;

import com.example.slidingmenu_viewpager_tabindicator.utils.ScreenUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.nineoldandroids.view.ViewHelper;

public class SlidingMenu extends HorizontalScrollView
{
	/**
	 * 屏幕宽度
	 */
	private int mScreenWidth;
	/**
	 * dp
	 */
	private int mMenuRightPadding;
	/**
	 * 菜单的宽度
	 */
	private int mMenuWidth;
	private int mHalfMenuWidth;

	private boolean isOpen;

	private boolean once;

	private ViewGroup mMenu;
	private ViewGroup mContent;

	//我添加的
	private Context context;
	
	
	
	public SlidingMenu(Context context)
	{
		this(context, null, 0);
	}
	public SlidingMenu(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);

	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		
		//我添加的
		this.context=context;
		
		
		mScreenWidth = ScreenUtils.getScreenWidth(context);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.SlidingMenu_rightPadding:
				// 默认50
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50f,
								getResources().getDisplayMetrics()));// 默认10DP
				break;
			}
		}
		a.recycle();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		/**
		 * 显式的设置一个宽度
		 */
		if (!once)
		{
			//第一层的LinearLayout。看布局文件
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			//第一个是菜单
			mMenu = (ViewGroup) wrapper.getChildAt(0);
			//第二个是内容
			mContent = (ViewGroup) wrapper.getChildAt(1);

			// 菜单的宽度为，屏幕宽度减去右边的偏移量。
			mMenuWidth = mScreenWidth - mMenuRightPadding;
			//菜单的一半宽度，用于和滑动的距离比较，决定显示菜单还是内容
			mHalfMenuWidth = mMenuWidth / 2;
			mMenu.getLayoutParams().width = mMenuWidth;
			//内容的宽度为屏幕宽度
			mContent.getLayoutParams().width = mScreenWidth;

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			// 将菜单隐藏
			this.scrollTo(mMenuWidth, 0);
			once = true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
		// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
			} else
			{
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 打开菜单
	 */
	public void openMenu()
	{
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}

	/**
	 * 关闭菜单
	 */
	public void closeMenu()
	{
		if (isOpen)
		{
			this.smoothScrollTo(mMenuWidth, 0);
			isOpen = false;
		}
	}

	/**
	 * 切换菜单状态
	 */
	public void toggle()
	{
		if (isOpen)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuWidth;
		float leftScale = 1 - 0.3f * scale;
		float rightScale = 0.8f + scale * 0.2f;
		
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);

		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);

	}
	
	
	//我添加关于点击屏幕事件的传递
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println("X轴的坐标："+ev.getX());
		System.out.println("X轴的坐标dechazhi："+(old-ev.getX()));
		int pageNum = 0;
		if(((Activity)context) instanceof MainActivity){
			aaa=(AAAA)context;
			pageNum=aaa.getCurPage();
		}
		
		if(!isOpen&&pageNum!=0){
			return false;
		}
		if(!isOpen&&pageNum==0){
			if(ev.getAction()==MotionEvent.ACTION_DOWN)
			{
				old=(int) ev.getX();
			}
			if((old-ev.getX())>20){
				return false;
			}
		}
		return super.onInterceptTouchEvent(ev);
	}

	private int old;
	
	private AAAA aaa;
	public interface AAAA{
		int getCurPage();
	}
}
