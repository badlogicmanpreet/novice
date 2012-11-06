package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTest extends Activity {
	StringBuilder builder = new StringBuilder();
	TextView textView;

	String TAG = "LifeCycleTest";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText(builder.toString());
		setContentView(textView);
		log("created");
	}

	private void log(String text) {
		Log.d(TAG, text);
		builder.append(text);
		builder.append('\n');
		textView.setText(builder.toString());
	}

	@Override
	protected void onResume() {
		super.onResume();
		log("resumed");
	}

	@Override
	protected void onPause() {
		super.onPause();
		log("paused");

		if (isFinishing()) {
			log("finishing");
		}
	}
}
