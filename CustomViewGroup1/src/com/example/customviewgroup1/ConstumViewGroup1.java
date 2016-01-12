package com.example.customviewgroup1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ConstumViewGroup1  extends ViewGroup{

	
	public ConstumViewGroup1(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public ConstumViewGroup1(Context context)
	{
		super(context);
	}

	public ConstumViewGroup1(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	
	//��д����ĸ÷���������MarginLayoutParams��ʵ�������Ϊ���ǵ�ViewGroupָ������LayoutParamsΪMarginLayoutParams��
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}
	
	
	/**
	 * ��������ChildView�Ŀ�Ⱥ͸߶� Ȼ�����ChildView�ļ������������Լ��Ŀ�͸�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		 /** 
         * ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ 
         */  
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);  
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);  
        
        // ��������е�childView�Ŀ�͸�  
        measureChildren(widthMeasureSpec, heightMeasureSpec);  
        
        /** 
         * ��¼�����wrap_content�����õĿ�͸� 
         */  
        int width = 0;  
        int height = 0;  
  
        int cCount = getChildCount();  
  
        int cWidth = 0;  
        int cHeight = 0;  
        MarginLayoutParams cParams = null;  
        
     // ���ڼ����������childView�ĸ߶�
     		int lHeight = 0;
     		// ���ڼ����ұ�����childView�ĸ߶ȣ����ո߶�ȡ����֮���ֵ
     		int rHeight = 0;

     		// ���ڼ����ϱ�����childView�Ŀ��
     		int tWidth = 0;
     		// ���ڼ�����������childiew�Ŀ�ȣ����տ��ȡ����֮���ֵ
     		int bWidth = 0;

     		/**
    		 * ����childView����ĳ��Ŀ�͸ߣ��Լ����õ�margin���������Ŀ�͸ߣ���Ҫ����������warp_contentʱ
    		 */
     		for (int i = 0; i < cCount; i++)
    		{
    			View childView = getChildAt(i);
    			cWidth = childView.getMeasuredWidth();
    			cHeight = childView.getMeasuredHeight();
    			cParams = (MarginLayoutParams) childView.getLayoutParams();

    			// ��������childView
    			if (i == 0 || i == 1)
    			{
    				tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
    			}

    			//��������childView
    			if (i == 2 || i == 3)
    			{
    				bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
    			}

    			//����
    			if (i == 0 || i == 2)
    			{
    				lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
    			}

    			//����
    			if (i == 1 || i == 3)
    			{
    				rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
    			}

    		}
     		width = Math.max(tWidth, bWidth);
    		height = Math.max(lHeight, rHeight);

    		/**
    		 * �����wrap_content����Ϊ���Ǽ����ֵ
    		 * ����ֱ������Ϊ�����������ֵ
    		 */
    		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
    				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
    				: height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		
		int count=getChildCount();
		int cWidth=0;
		int cHeight=0;
		
		MarginLayoutParams cParams=null;
		
		/**
		 * ��������childView�������͸ߣ��Լ�margin���в���
		 */
		for(int i=0;i<count;i++){
			View childView=getChildAt(i);
			
			cWidth=childView.getMeasuredWidth();
			cHeight=childView.getMeasuredHeight();
			
			cParams=(MarginLayoutParams) childView.getLayoutParams();
			
			int cl=0,ct=0,cr=0,cb=0;
			
			switch (i) {
			case 0:
				cl=cParams.leftMargin;
				ct=cParams.topMargin;
				break;
			case 1:  
                cl = getWidth() - cWidth - cParams.leftMargin  
                        - cParams.rightMargin;  
                ct = cParams.topMargin;  
  
                break;  
            case 2:  
                cl = cParams.leftMargin;  
                ct = getHeight() - cHeight - cParams.bottomMargin;  
                break;  
            case 3:  
                cl = getWidth() - cWidth - cParams.leftMargin  
                        - cParams.rightMargin;  
                ct = getHeight() - cHeight - cParams.bottomMargin;  
                break;  
  
            }  
            cr = cl + cWidth;  
            cb = cHeight + ct;  
            childView.layout(cl, ct, cr, cb);  
			
		}
	}
}
