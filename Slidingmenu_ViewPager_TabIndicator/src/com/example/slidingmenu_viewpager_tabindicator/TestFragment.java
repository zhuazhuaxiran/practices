package com.example.slidingmenu_viewpager_tabindicator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

public class TestFragment extends Fragment {

	private TabPageIndicator mIndicator;
	private TabAdapter mAdapter;
	private ViewPager mViewPager;

	private Context context;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("创建");
		View view = inflater.inflate(R.layout.test_fragment, null);

		mViewPager = (ViewPager) view.findViewById(R.id.id_pager);

		mIndicator=(TabPageIndicator) view.findViewById(R.id.id_indicator);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		// 这里的这个fragmentmanager应该怎样获得，是通过getActivity然后getSupportFragmentManager获得，还是直接获得？
//		mAdapter = new TabAdapter(getFragmentManager());
		mAdapter=new TabAdapter(getActivity().getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager,0);
	}
	
	
	public int getNowPage(){
		System.out.println("当前页，当前页，当前页，当前页，当前页，当前页，"+mViewPager.getCurrentItem());
		return mViewPager.getCurrentItem();
	}
}
