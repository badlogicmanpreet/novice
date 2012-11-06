package com.voyager.cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.c2dm.C2DMessaging;

public class RegisterActivity extends Activity {

	String tag = "RegisterActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerWithCloud();
		invokeDashboard();
	}

	public void registerWithCloud() {
		// TODO Auto-generated method stub
		Log.d(tag, "Starting to register");
		C2DMessaging.register(this, "msghotra.voyager@gmail.com");
		Log.d(tag, "Registered successfuly");
	}

	public void invokeDashboard() {
		startActivity(new Intent(getApplicationContext(), StartPageActivity.class));
	}
}
