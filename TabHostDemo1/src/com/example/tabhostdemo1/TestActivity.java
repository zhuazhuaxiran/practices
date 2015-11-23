package com.example.tabhostdemo1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {

	private TextView mText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		mText=(TextView) findViewById(R.id.id_text);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		mText.setText(bundle.getString("test"));
	}
	
}
