package com.linc.howtopreferenceactivity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class MyPreferenceFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.fragment);
		PreferenceManager pm= getPreferenceManager();
		pm.setSharedPreferencesName("mySetting");
	}
//	http://www.it165.net/pro/html/201410/23922.html
}
