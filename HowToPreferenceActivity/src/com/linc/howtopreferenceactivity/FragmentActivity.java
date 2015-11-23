package com.linc.howtopreferenceactivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class FragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity);

		FragmentManager fm = getFragmentManager();
		fm.beginTransaction()
				.replace(R.id.id_fragment, new MyPreferenceFragment(),
						"preference").commit();
	}

}
