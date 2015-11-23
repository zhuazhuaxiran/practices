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
        intent.putExtra("test", "��ӿ���");
        spec=tabHost.newTabSpec("��ӿ���").setIndicator("��ӿ���").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this,TestActivity.class);
        intent.putExtra("test", "�ҵĿ���");
        spec=tabHost.newTabSpec("�ҵĿ���").setIndicator("�ҵĿ���").setContent(intent);
        tabHost.addTab(spec);
        
        intent=new Intent().setClass(this, TestActivity.class);
        intent.putExtra("test", "�ҵ�֪ͨ");
        spec=tabHost.newTabSpec("�ҵ�֪ͨ").setIndicator("�ҵ�֪ͨ").setContent(intent);
        tabHost.addTab(spec);
        
     
        intent=new Intent().setClass(this, TestActivity.class);
        intent.putExtra("test", "����");
        spec=tabHost.newTabSpec("����").setIndicator("����").setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(1);
        
        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.main_tab_addExam://��ӿ���
					tabHost.setCurrentTabByTag("��ӿ���");
					break;
				case R.id.main_tab_myExam://�ҵĿ���
					tabHost.setCurrentTabByTag("�ҵĿ���");
					break;
				case R.id.main_tab_message://�ҵ�֪ͨ
					tabHost.setCurrentTabByTag("�ҵ�֪ͨ");
					break;
				case R.id.main_tab_settings://����
					tabHost.setCurrentTabByTag("����");
					break;
				default:
					//tabHost.setCurrentTabByTag("�ҵĿ���");
					break;
				}
			}
		});
    }
    
   
}
