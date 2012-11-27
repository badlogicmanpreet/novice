package com.javageek.whereami;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class GooglePlaceActivity extends Activity {
	/** Called when the activity is first created. */
	// Button btn1;
	TextView txt1;
	double latitude;
	double longitude;
	
	String TAG = "GooglePlaceActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		//setContentView(R.layout.main);
		// btn1 = (Button)findViewById(R.id.button1);
		//txt1 = (TextView) findViewById(R.id.textView1);
		// btn1.setOnClickListener(l);
		Log.d(TAG, "Starting google places");
		getGooglePlaces();
	}

	private class SearchSrv extends AsyncTask<Void, Void, PlacesList> {
		@Override
		protected PlacesList doInBackground(Void... params) {
			// TODO Auto-generated method stub
			PlacesList pl = null;
			try {
				pl = new PlaceRequest().performSearch(latitude, longitude);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pl;
		}

		@Override
		protected void onPostExecute(PlacesList result) {
			// TODO Auto-generated method stub
			String text = "Result \n";

			if (result != null) {
				for (Place place : result.results) {
					text = text + place.name + "\n";
				}
				txt1.setText(text);
			}
			setProgressBarIndeterminateVisibility(false);
		}
	}

	public void getGooglePlaces() {
		Log.d(TAG, "In google places");
		getPosition();
//		SearchSrv srv = new SearchSrv();
//		setProgressBarIndeterminateVisibility(true);
//		srv.execute();
}

	View.OnClickListener l = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			SearchSrv srv = new SearchSrv();
			setProgressBarIndeterminateVisibility(true);
			srv.execute();

		}
	};

	public void getPosition() {
		Log.d(TAG, "Get Position");
		Intent intent = new Intent(this, MyPositionActivity.class);
		startActivity(intent);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "on Activity Result");
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				latitude = Double.parseDouble(data.getStringExtra("latitude"));
				longitude = Double.parseDouble(data.getStringExtra("longitude"));
			}
		}
		PlacesList pl = null;
    	try {
			pl = new PlaceRequest().performSearch(latitude, longitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Log.d(TAG, latitude);
		//Log.d(TAG, longitude);
	}

}
