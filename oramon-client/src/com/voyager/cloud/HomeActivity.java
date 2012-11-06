package com.voyager.cloud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.voyager.cloud.R;
import com.voyager.cloud.RegisterActivity;

public class HomeActivity extends DashboardActivity {

	String tag = "HomeActivity";
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
	}

	protected void onStart() {
		super.onStart();
	}
}
