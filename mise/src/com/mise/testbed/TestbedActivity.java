package com.mise.testbed;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;

public class TestbedActivity extends Activity {

	private LocationManager locationManager;

	private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1;
	private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000;
	private static final long POINT_RADIUS = 1;
	private static final long PROX_ALERT_EXPIRATION = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testbed);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATE,
				MINIMUM_DISTANCECHANGE_FOR_UPDATE, new MyLocationListener());

		// test notification
		//testNotify();

		// test proximity
		testLocationProximity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_testbed, menu);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void testNotify() {
//		NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher)
//				.setContentTitle("my notify test").setContentText("hello world!");
//
//		Intent resultIntent = new Intent(this, ResultActivity.class);
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//		stackBuilder.addParentStack(ResultActivity.class);
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//		builder.setContentIntent(resultPendingIntent);
//
//		NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		nManager.notify(1, builder.build());
	}

	public void testLocationProximity() {
		Intent intent = new Intent("com.mise.testbed.PROXIMITY_ALERT");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		Log.d("params", "" + location.getLatitude());
		Log.d("params", "" + location.getLongitude());
		locationManager.addProximityAlert(17.4269809, 78.3318046, POINT_RADIUS,
				PROX_ALERT_EXPIRATION, pendingIntent);

		IntentFilter intentFilter = new IntentFilter("com.mise.testbed.PROXIMITY_ALERT");
        registerReceiver(new ProximityIntentReceiver(), intentFilter);
	}

	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}
}
