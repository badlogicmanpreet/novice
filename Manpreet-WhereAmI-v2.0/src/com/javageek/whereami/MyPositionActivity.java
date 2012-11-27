package com.javageek.whereami;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class MyPositionActivity extends Activity implements LocationListener {

	String TAG = "MyPositionActivity";
	TextView textView;
	LocationManager locationManager;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		Log.d(TAG, "In My position activity");
		textView = (TextView) findViewById(R.id.textView1);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			Log.d(TAG, location.toString());
			this.onLocationChanged(location);
		}
	}

	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
	}

	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Log.d(TAG, location.getProvider());
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		setProgressBarIndeterminateVisibility(true);
		PlacesList placesList = null;
		String text = "Result \n";
		try {
			placesList = new PlaceRequest().performSearch(latitude, longitude);
			for (Place place : placesList.results) {
				text = text + place.name + "\n";
			}
			textView.setText(text);
    		setProgressBarIndeterminateVisibility(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Intent returnIntent = new Intent();
//		returnIntent.putExtra("latitude", latitude);
//		returnIntent.putExtra("longitude", longitude);
//		setResult(RESULT_OK, returnIntent);
//		finish();
//		Log.d(TAG, "Done = " + latitude + " " + longitude);
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
