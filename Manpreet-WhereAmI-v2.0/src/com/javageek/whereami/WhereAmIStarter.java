package com.javageek.whereami;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WhereAmIStarter extends ListActivity {
	
	String TAG="WhereAmIStarter";

	// String[] activities = {"WhereAmIActivity","GooglePlaceActivity"};
	String[] activities = { "Where Am I", "Places next to you" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities));
	}

	protected void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);
		String activity = activities[position];
		if (activity.equalsIgnoreCase("Where Am I")) {
			activity = "WhereAmIActivity";
		} else if (activity.equalsIgnoreCase("Places next to you")) {
			activity = "MyPositionActivity";
		}
		try {
			Log.d(TAG, "activity = " + activity);
			Class clazz = Class.forName("com.javageek.whereami." + activity);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
