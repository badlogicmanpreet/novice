package com.manpreet.javageek.digitalclock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {

	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {
//			RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget);
//
//			Intent clockIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(
//					new ComponentName("com.android.alarmclock", "com.android.alarmclock.AlarmClock"));
//			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clockIntent, 0);
//
//			remoteView.setOnClickPendingIntent(R.id.Widget, pendingIntent);
//			AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), remoteView);

			// Testing
			String pattern = "mm-dd-yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date now = new Date();
			String[] timeZones = TimeZone.getAvailableIDs();
			TimeZone timeZone = null;
			for (int i = 0; i < timeZones.length; i++) {
				timeZone = TimeZone.getTimeZone(timeZones[i]);
				format.setTimeZone(timeZone);
				String date = format.format(now);
				Log.d("Date", "Date is:" + date);
				//Log.d("TimeZone Test", timeZone.getDisplayName());
				Log.d("TimeZone Test", timeZone.toString());
				
			}
		}
	}
}
