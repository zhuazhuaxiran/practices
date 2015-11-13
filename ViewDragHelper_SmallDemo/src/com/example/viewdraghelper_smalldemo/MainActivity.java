package com.example.viewdraghelper_smalldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void canClick(View v){
		Toast.makeText(this, "Œ“ « ∞¥≈•", Toast.LENGTH_LONG).show();
	}
}
