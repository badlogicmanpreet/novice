package com.javageek.whereami;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NetworkActivity extends Activity {

	Button whereami;
	LocationManager locationManager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		whereami = (Button) findViewById(R.id.whereami_button);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new MyLocationListener());

		whereami.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				whereami();
			}
		});
	}

	public void whereami() {
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		String message = String.format("Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());

		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

}
