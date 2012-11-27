package com.c2dm.voyager;

import com.google.android.c2dm.C2DMessaging;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class C2DMActivity extends Activity {

	String tag = "C2DMActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d(tag, "Starting to register");
		C2DMessaging.register(this, "manpreeteducation@gmail.com");
		Log.d(tag, "Registered successfuly");
		//String regID = C2DMessaging.getRegistrationId(this);
		//Log.d(tag, regID);
	}
}