package com.manpreet.android.widget.example;

import java.util.Random;

import com.manpreet.android.widget.example.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		for (int widgetId : allWidgetIds) {
           int number = new Random().nextInt(100);
           
           RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
           remoteViews.setTextViewText(R.id.update, String.valueOf(number));
           
		}

	}
}
