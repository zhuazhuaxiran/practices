package com.example.slidingmenu_viewpager_tabindicator;

import com.example.slidingmenu_viewpager_tabindicator.SlidingMenu.AAAA;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements AAAA
{
	private SlidingMenu mMenu;

	private Button mTestButton;
	private FragmentManager fm;
	private TestFragment testFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		mTestButton=(Button) findViewById(R.id.id_menu_testButton);
		//获得FragmentManager对象
		fm=getSupportFragmentManager();
		
		mTestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(testFragment==null){
					testFragment=new TestFragment();
				}else{
					mMenu.closeMenu();
					return;
				}
				FragmentTransaction transaction=fm.beginTransaction();
				
				transaction.add(R.id.id_main_fl, testFragment, "0");
				transaction.commit();
				mMenu.closeMenu();
			}
		});

	}
	@Override
	public int getCurPage() {
		return testFragment==null ? 0:testFragment.getNowPage();
	}

	
	
	//实现接口的方法
	
//	public void toggleMenu(View view)
//	{
//		mMenu.toggle();
//	}
}
