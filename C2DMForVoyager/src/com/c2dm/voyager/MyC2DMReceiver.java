package com.c2dm.voyager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyC2DMReceiver extends BroadcastReceiver {

	String tag = "MyC2DMReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d(tag, "In Receiver");
		String action = intent.getAction();
		Log.d(tag, action);
		if (action.equals("com.google.android.c2dm.intent.REGISTRATION")) {
			String regID = intent.getStringExtra("registration_id");
			Log.d(tag, regID);
			String error = intent.getStringExtra("error");
			String unregistered = intent.getStringExtra("unregistered");

			if (error != null) {
				Log.d(tag, error);
			} else if (regID != null) {
				// send regID to the AppServer
				Log.d(tag, "Starting RegService");
				Intent i = new Intent(context, RegService.class);
				i.putExtra("regID", regID);
				context.startService(i);
				Log.d(tag, "Started RegService");
			} else if (unregistered != null) {
				Log.d(tag, unregistered);
			}
		} else if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
			Log.d(tag, "received message from the App .. well done");
		}
	}
}
