package com.example.slidingmenu_viewpager_tabindicator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment
{

	private int newsType = 0;

	public MainFragment(int newsType)
	{
		this.newsType = newsType;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		System.out.println("onActivityCreated"+TabAdapter.TITLES[newsType]);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.tab_item_fragment_main, null);
		TextView tip = (TextView) view.findViewById(R.id.id_tip);
		tip.setText(TabAdapter.TITLES[newsType]);
		
		System.out.println("onCreateView"+TabAdapter.TITLES[newsType]);
		return view;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("onCreate"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		System.out.println("onAttach"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("onResume"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onStart() {
		super.onStart();
		System.out.println("onStart"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("onStop"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("onPause"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		System.out.println("onDestroyView"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("onDestroy"+TabAdapter.TITLES[newsType]);
	}
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		System.out.println("onDetach"+TabAdapter.TITLES[newsType]);
	}
}
