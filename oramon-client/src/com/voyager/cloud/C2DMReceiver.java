package com.voyager.cloud;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.c2dm.C2DMBaseReceiver;

public class C2DMReceiver extends C2DMBaseReceiver {

	String tag = "C2DMReceiver";

	public C2DMReceiver() {
		super("msghotra.voyager@gmail.com");
	}

	@Override
	public void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessage(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

	}

	public void onRegistered(Context context, String registrationId)
			throws IOException {
		Log.d(tag, registrationId);
	}

}
