package com.example.jniccalledjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	static{
		System.loadLibrary("ccalledjava");
//		CCalledJava cc=new CCalledJava("cc");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CCalledJava.wrap_main();
	}
}
