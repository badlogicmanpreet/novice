package com.javageek.showpromotion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WhereAmIStarter extends ListActivity {

	String TAG = "WhereAmIStarter";

	String[] activities = { "Where Am I", "Places around you", "Show promotions" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities));
	}

	protected void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);
		String activity = activities[position];
		if (activity.equalsIgnoreCase("Where Am I")) {
			activity = "WhereAmIActivity";
		} else if (activity.equalsIgnoreCase("Places around you")) {
			activity = "PlacesAroundYouActivity";
		} else if (activity.equalsIgnoreCase("Show promotions")){
			activity = "PromotionActivity";
		}
		try {
			Class clazz = Class.forName("com.javageek.showpromotion." + activity);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
