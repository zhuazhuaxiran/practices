package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.example.fragmenttabhost.R;

public class Fragment1 extends Fragment{



	    @Override
	    public void onDestroyView(){
	        super.onDestroyView();
	        mTabHost =null;
	    }
	    
	    /**
		 * FragmentTabhost
		 */
		private FragmentTabHost mTabHost;

		/**
		 * 布局填充器
		 * 
		 */
		private LayoutInflater mLayoutInflater;

		/**
		 * Fragment数组界面
		 * 
		 */
		private Class mFragmentArray[] = {  Fragment2.class,
				Fragment3.class, Fragment4.class, Fragment5.class };
		/**
		 * 存放图片数组
		 * 
		 */
		private int mImageArray[] = {
				R.drawable.tab_message_btn, R.drawable.tab_selfinfo_btn,
				R.drawable.tab_square_btn, R.drawable.tab_more_btn };

		/**
		 * 选修卡文字
		 * 
		 */
		private String mTextArray[] = {  "消息", "好友", "搜索", "更多" };
		/**
		 * 
		 * 
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_main, null);
		mLayoutInflater = LayoutInflater.from(getActivity());

		// 找到TabHost
		mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
		// 得到fragment的个数
		int count = mFragmentArray.length;
		for (int i = 0; i < count; i++) {
			// 给每个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, mFragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);
		}
		
		return view;
		}

		/**
		 *
		 * 给每个Tab按钮设置图标和文字
		 */
		private View getTabItemView(int index) {
			View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
			imageView.setImageResource(mImageArray[index]);
			TextView textView = (TextView) view.findViewById(R.id.textview);
			textView.setText(mTextArray[index]);
			return view;
		}

}
