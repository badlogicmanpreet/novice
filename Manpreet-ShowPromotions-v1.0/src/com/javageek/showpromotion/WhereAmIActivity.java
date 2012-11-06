package com.javageek.showpromotion;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.javageek.showpromotion.R;

public class WhereAmIActivity extends MapActivity implements LocationListener {

	String TAG = "WhereAmIActivity";

	TextView text;
	MapView map;

	MapController mapController;
	LocationManager locationManager;
	Geocoder geocoder;
	String provider;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whereami_text_map_view);

		text = (TextView) findViewById(R.id.textView);
		map = (MapView) findViewById(R.id.mapView);
		map.setBuiltInZoomControls(true);

		mapController = map.getController();
		mapController.setZoom(16);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		geocoder = new Geocoder(this);

		//test providers (ref:0YBZI3g2ZOYA4knwv--o-nNhmnnB3n4xyxqSBhw)
//		List<String> providers = locationManager.getProviders(true);
//		for(String provider:providers){
//			Log.d(TAG, "provider = " + provider);
//		}
		
		//Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		provider = getBestProvider();
		this.text.setText("provider here is = " + provider);
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			this.onLocationChanged(location);
		}
	}
	
	public String getBestProvider(){
		Criteria myCriteria = new Criteria();
		myCriteria.setAccuracy(Criteria.NO_REQUIREMENT);
		myCriteria.setPowerRequirement(Criteria.POWER_LOW);
		// let Android select the right location provider for you
		String myProvider = locationManager.getBestProvider(myCriteria, true); 
		return myProvider;
	}

	protected void onResume() {
		super.onResume();
		//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
		locationManager.requestLocationUpdates(provider, 1000, 1, this);
	}

	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		String text = String.format("Latitude:\t %f\nLongitude:\t %f", location.getLatitude(), location.getLongitude());
		this.text.setText(text);

		try {
			List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
			for (Address address : addresses) {
				this.text.append("\n" + address.getAddressLine(0));
			}

			int latitude = (int) (location.getLatitude() * 1000000);
			int longitude = (int) (location.getLongitude() * 1000000);

			GeoPoint geoPoint = new GeoPoint(latitude, longitude);
			mapController.animateTo(geoPoint);

		} catch (IOException e) {
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

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}