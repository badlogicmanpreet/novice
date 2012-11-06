package com.javageek.showpromotion;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.javageek.showpromotion.MyItemizedOverlay;
import com.javageek.showpromotion.R;

import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PromotionActivity extends MapActivity implements LocationListener {

	String TAG = "PromotionActivity";
	LocationManager locationManager;

	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyItemizedOverlay itemizedOverlay;
	Bundle savedInstanceState;

	Set<GeoPoint> set;
	String provider;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.promotions_map_view);

		mapView = (MapView) findViewById(R.id.promomapview);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();

		// testOverlays();

		// try {

		// MyXMLParser root = new MyXMLParser(xml, "PlaceSearchResponse");
		// for(MyXMLParser result:root.children("result")){
		// for(MyXMLParser geometry:result.children("geometry")){
		// for(MyXMLParser location:geometry.children("location")){
		// for(MyXMLParser lat:location.children("lat")){
		// String lats = lat.content();
		// Log.d(TAG, "Manpreet = " + lats);
		// }
		// for(MyXMLParser lng:location.children("lng")){
		// String lngs = lng.content();
		// Log.d(TAG, "Manpreet = " + lngs);
		// }
		// }
		// }
		// }

		// MyXMLParser lat =
		// root.child("result").child("geometry").child("location").child("lat");
		// MyXMLParser lng =
		// root.child("result").child("geometry").child("location").child("lng");

		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// // first overlay
		// drawable = getResources().getDrawable(R.drawable.marker);
		// itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		//		
		// GeoPoint point = new
		// GeoPoint((int)(51.5174723*1E6),(int)(-0.0899537*1E6));
		// OverlayItem overlayItem = new OverlayItem(point,
		// "Tomorrow Never Dies (1997)",
		// "(M gives Bond his mission in Daimler car)");
		// itemizedOverlay.addOverlay(overlayItem);
		//		
		// GeoPoint point2 = new
		// GeoPoint((int)(51.515259*1E6),(int)(-0.086623*1E6));
		// OverlayItem overlayItem2 = new OverlayItem(point2,
		// "GoldenEye (1995)",
		// "(Interiors Russian defence ministry council chambers in St Petersburg)");
		// itemizedOverlay.addOverlay(overlayItem2);
		//		
		// mapOverlays.add(itemizedOverlay);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		provider = getBestProvider();

		//Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
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

		double latitude = location.getLatitude();
		double longitude = location.getLongitude();

//		double latitude = 13.761760;
//		double longitude = 100.638889;

		
		set = new HashSet<GeoPoint>();

		setProgressBarIndeterminateVisibility(true);

		// first overlay
		drawable = getResources().getDrawable(R.drawable.me_3);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);

		GeoPoint whereamiPoint = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
		OverlayItem overlayItem = new OverlayItem(whereamiPoint, "This is my location...", "(click on nearby markers to watch promotions...)");
		itemizedOverlay.addOverlay(overlayItem);
		mapOverlays.add(itemizedOverlay);

		String xml;
		try {
			xml = new PromotionRequest().performSearch(latitude, longitude);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			XMLHandler myXMLHandler = new XMLHandler();
			xr.setContentHandler(myXMLHandler);
			InputSource inStream = new InputSource();

			inStream.setCharacterStream(new StringReader(xml));

			xr.parse(inStream);

			ArrayList<XMLMaster> xmlMasterList = myXMLHandler.getXMLMasterList();
			for (int i = 0; i < xmlMasterList.size(); i++) {
				XMLMaster xmlMaster = xmlMasterList.get(i);
				Log.d(TAG, Double.toString(xmlMaster.getLAT()));
				Log.d(TAG, Double.toString(xmlMaster.getLNG()));

				// generic overlay
				Drawable drawableGeneric = null;
				MyItemizedOverlay itemizedOverlayGeneric = null;
				GeoPoint whereamiPointGeneric = null;
				OverlayItem overlayItemGeneric = null;

				drawableGeneric = getResources().getDrawable(R.drawable.marker_3);
				itemizedOverlayGeneric = new MyItemizedOverlay(drawableGeneric, mapView);

				whereamiPointGeneric = new GeoPoint((int) (xmlMaster.getLAT() * 1E6), (int) (xmlMaster.getLNG() * 1E6));
				overlayItemGeneric = new OverlayItem(whereamiPointGeneric, "This is my location...",
						"(click on nearby markers to watch promotions...)");
				itemizedOverlayGeneric.addOverlay(overlayItemGeneric);
				mapOverlays.add(itemizedOverlayGeneric);
				set.add(whereamiPointGeneric);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// InputStream xml = new PromotionRequest().performSearch(latitude,
		// longitude);
		// MyXMLParser root = new MyXMLParser(xml, "PlaceSearchResponse");
		// MyXMLParser lat =
		// root.child("result").child("geometry").child("location").child("lat");
		// MyXMLParser lng =
		// root.child("result").child("geometry").child("location").child("lng");
		// Log.d(TAG, "Manpreet = " + lat.content());
		// Log.d(TAG, "Manpreet = " + lng.content());
		//			
		//            
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		if (savedInstanceState == null) {

			// final MapController mc = mapView.getController();
			// mc.animateTo(whereamiPoint);
			// mc.setZoom(20);

			// testing the zoom controls
			final MapController mc = mapView.getController();
			mc.setCenter(whereamiPoint);
			int minLatitude = Integer.MAX_VALUE;
			int maxLatitude = Integer.MIN_VALUE;
			int minLongitude = Integer.MAX_VALUE;
			int maxLongitude = Integer.MIN_VALUE;

			Iterator it = set.iterator();
			while (it.hasNext()) {
				// Get element
				GeoPoint element = (GeoPoint) it.next();

				int lat = element.getLatitudeE6();
				int lon = element.getLongitudeE6();

				maxLatitude = Math.max(lat, maxLatitude);
				minLatitude = Math.min(lat, minLatitude);
				maxLongitude = Math.max(lon, maxLongitude);
				minLongitude = Math.min(lon, minLongitude);
			}

			mc.animateTo(new GeoPoint((maxLatitude + minLatitude) / 2, (maxLongitude + minLongitude) / 2));
			mc.zoomToSpan(Math.abs(maxLatitude - minLatitude), Math.abs(maxLongitude - minLongitude));

		} else {

			// example restoring focused state of overlays
			int focused;
			focused = savedInstanceState.getInt("focused_1", -1);
			if (focused >= 0) {
				itemizedOverlay.setFocus(itemizedOverlay.getItem(focused));
			}
		}

		setProgressBarIndeterminateVisibility(false);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		// example saving focused state of overlays
		if (itemizedOverlay.getFocus() != null)
			outState.putInt("focused_1", itemizedOverlay.getLastFocusedIndex());
		super.onSaveInstanceState(outState);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 1, "Remove Overlay");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {

			// example hiding balloon before removing overlay
			if (itemizedOverlay.getFocus() != null) {
				itemizedOverlay.hideBalloon();
			}
			mapOverlays.remove(itemizedOverlay);
			mapView.invalidate();

		}
		return true;
	}

	public void testOverlays() {
		setProgressBarIndeterminateVisibility(true);

		// first overlay
		drawable = getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);

		GeoPoint whereamiPoint = new GeoPoint((int) (13.764792 * 1E6), (int) (100.644486 * 1E6));
		OverlayItem overlayItem = new OverlayItem(whereamiPoint, "This is my location...", "(click on nearby markers to watch promotions...)");
		itemizedOverlay.addOverlay(overlayItem);
		mapOverlays.add(itemizedOverlay);

		String xml;
		try {
			xml = new PromotionRequest().performSearch(13.764792, 100.644486);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			XMLHandler myXMLHandler = new XMLHandler();
			xr.setContentHandler(myXMLHandler);
			InputSource inStream = new InputSource();

			inStream.setCharacterStream(new StringReader(xml));

			xr.parse(inStream);

			ArrayList<XMLMaster> xmlMasterList = myXMLHandler.getXMLMasterList();
			for (int i = 0; i < xmlMasterList.size(); i++) {
				XMLMaster xmlMaster = xmlMasterList.get(i);
				Log.d(TAG, Double.toString(xmlMaster.getLAT()));
				Log.d(TAG, Double.toString(xmlMaster.getLNG()));

				// generic overlay
				Drawable drawableGeneric = null;
				MyItemizedOverlay itemizedOverlayGeneric = null;
				GeoPoint whereamiPointGeneric = null;
				OverlayItem overlayItemGeneric = null;

				drawableGeneric = getResources().getDrawable(R.drawable.marker);
				itemizedOverlayGeneric = new MyItemizedOverlay(drawableGeneric, mapView);

				whereamiPointGeneric = new GeoPoint((int) (xmlMaster.getLAT() * 1E6), (int) (xmlMaster.getLNG() * 1E6));
				overlayItemGeneric = new OverlayItem(whereamiPointGeneric, "This is my location...",
						"(click on nearby markers to watch promotions...)");
				itemizedOverlayGeneric.addOverlay(overlayItemGeneric);
				mapOverlays.add(itemizedOverlayGeneric);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (savedInstanceState == null) {

			final MapController mc = mapView.getController();
			mc.animateTo(whereamiPoint);
			mc.setZoom(8);

		} else {
			// example restoring focused state of overlays
			int focused;
			focused = savedInstanceState.getInt("focused_1", -1);
			if (focused >= 0) {
				itemizedOverlay.setFocus(itemizedOverlay.getItem(focused));
			}
		}

		setProgressBarIndeterminateVisibility(false);
	}

}
