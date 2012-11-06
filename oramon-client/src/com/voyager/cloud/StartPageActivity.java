package com.voyager.cloud;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class StartPageActivity extends DashboardActivity {

	String tag = "StartPageActivity";

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startpage_activity);
		
//		Toast toast = Toast.makeText(this, "Registeration Done Successfully",Toast.LENGTH_LONG);
//		toast.setGravity(Gravity.BOTTOM, 0, 0);
//		toast.show();
	}
}