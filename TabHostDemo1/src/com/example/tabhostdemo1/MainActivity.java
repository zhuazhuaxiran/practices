package com.example.tabhostdemo1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;



public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
	private TabHost tabHost;
	private TextView main_tab_new_message;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        main_tab_new_message=(TextView) findViewById(R.id.main_tab_new_message);
        main_tab_new_message.setVisibility(View.VISIBLE);
        main_tab_new_message.setText("10");
        
        tabHost=this.getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        
        intent=new Intent().setClass(this, TestActivity.class);
        intent.putExtra("test", "添加考试");
        spec=tabHost.newTabSpec("添加考试").setIndicator("添加考试").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this,TestActivity.class);
        intent.putExtra("test", "我的考试");
        spec=tabHost.newTabSpec("我的考试").setIndicator("我的考试").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this, TestActivity.class);
        intent.putExtra("test", "我的通知");
        spec=tabHost.newTabSpec("我的通知").setIndicator("我的通知").setContent(intent);
        tabHost.addTab(spec);
        
     
        intent=new Intent().setClass(this, TestActivity.class);
        intent.putExtra("test", "设置");
        spec=tabHost.newTabSpec("设置").setIndicator("设置").setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(1);
        
        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.main_tab_addExam://添加考试
					tabHost.setCurrentTabByTag("添加考试");
					break;
				case R.id.main_tab_myExam://我的考试
					tabHost.setCurrentTabByTag("我的考试");
					break;
				case R.id.main_tab_message://我的通知
					tabHost.setCurrentTabByTag("我的通知");
					break;
				case R.id.main_tab_settings://设置
					tabHost.setCurrentTabByTag("设置");
					break;
				default:
					//tabHost.setCurrentTabByTag("我的考试");
					break;
				}
			}
		});
    }
    
   
}
