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
		 * ���������
		 * 
		 */
		private LayoutInflater mLayoutInflater;

		/**
		 * Fragment�������
		 * 
		 */
		private Class mFragmentArray[] = {  Fragment2.class,
				Fragment3.class, Fragment4.class, Fragment5.class };
		/**
		 * ���ͼƬ����
		 * 
		 */
		private int mImageArray[] = {
				R.drawable.tab_message_btn, R.drawable.tab_selfinfo_btn,
				R.drawable.tab_square_btn, R.drawable.tab_more_btn };

		/**
		 * ѡ�޿�����
		 * 
		 */
		private String mTextArray[] = {  "��Ϣ", "����", "����", "����" };
		/**
		 * 
		 * 
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_main, null);
		mLayoutInflater = LayoutInflater.from(getActivity());

		// �ҵ�TabHost
		mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
		// �õ�fragment�ĸ���
		int count = mFragmentArray.length;
		for (int i = 0; i < count; i++) {
			// ��ÿ��Tab��ť����ͼ�ꡢ���ֺ�����
			TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
					.setIndicator(getTabItemView(i));
			// ��Tab��ť��ӽ�Tabѡ���
			mTabHost.addTab(tabSpec, mFragmentArray[i], null);
			// ����Tab��ť�ı���
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);
		}
		
		return view;
		}

		/**
		 *
		 * ��ÿ��Tab��ť����ͼ�������
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
