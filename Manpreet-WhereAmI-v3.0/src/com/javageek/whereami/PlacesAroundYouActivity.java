package com.javageek.whereami;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class PlacesAroundYouActivity extends Activity implements LocationListener {

	String TAG = "MyPositionActivity";
	TextView textView;
	LocationManager locationManager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.places_text_view);

		textView = (TextView) findViewById(R.id.textView1);

//		PlacesList placesList = null;
//		String text = "Result \n";
//		try {
//			placesList = new PlaceRequest().performSearch(13.764792,100.644486);
//			for (Place place : placesList.results) {
//				text = text + place.name + "\n";
//			}
//			textView.setText(text);
//			setProgressBarIndeterminateVisibility(false);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (location != null) {
			this.onLocationChanged(location);
		}
	}

	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
	}

	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {

		double latitude = location.getLatitude();
		double longitude = location.getLongitude();

//		double latitude = 13.761760;
//		double longitude = 100.638889;

		
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
			e.printStackTrace();
		}
	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

}
