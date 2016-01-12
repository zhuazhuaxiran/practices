package com.linc.howtopreferenceactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MulPreScreen extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mulprescreen);
	}
}
