package com.voyager.cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class Receiver extends BroadcastReceiver {

	String tag = "Receiver";

	@Override
	public void onReceive(final Context context, Intent intent) {
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
				// send regID to the Server
				Log.d(tag, "Starting RegService");
				Intent i = new Intent(context, RegisterService.class);
				i.putExtra("regID", regID);
				context.startService(i);
				Log.d(tag, "Started RegService");
			} else if (unregistered != null) {
				Log.d(tag, unregistered);
			}
		} else if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
			//Log.d(tag, "Well Done - right from voyager server");
			//Log.d(tag, "In Jsch Call");

			Thread background = new Thread(new Runnable() {
				 public void run() {
						SecureChannel secureChannel = new SecureChannel();
						String oramonXML = secureChannel.getOramonXML();
			    	 
						Log.d(tag, "Starting UtilService");
						Intent i = new Intent(context, UtilService.class);
						i.putExtra("oramonXML", oramonXML);
						context.startService(i);
						Log.d(tag, "Started UtilService");						
				 }
			});
			
			background.start();
			
			
			//SecureChannel secureChannel = new SecureChannel();
			//String oramonXML = secureChannel.getOramonXML();

			/*AsyncTask task = new AsyncTaskThread().execute(1000);
			String oramonXML = null;
			try {
				Log.d(tag, "Start get ...");
				oramonXML = (String) task.get();
				Log.d(tag, "XML String after get = " + oramonXML);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			Log.d(tag, "Jsch Call completed");
			
			Log.d(tag, "Starting UtilService");
			Intent i = new Intent(context, UtilService.class);
			i.putExtra("oramonXML", oramonXML);
			context.startService(i);
			Log.d(tag, "Started UtilService");
			*/
		}
	}

}
