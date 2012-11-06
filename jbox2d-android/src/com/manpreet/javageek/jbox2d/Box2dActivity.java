package com.manpreet.javageek.jbox2d;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class Box2dActivity extends Activity {

	public Box2dView pWorld;
	private Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// metrics
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		pWorld = new Box2dView(this, dm.widthPixels, dm.heightPixels);
		setContentView(pWorld);

		// add balls
		for (int i = 0; i < 12; i++) {
			pWorld.addBall();
		}

		Log.d("box2d", "create handler...");
		handler = new Handler();
		handler.post(update);
		Log.d("box2d", "create handler... done");
	}

	// test runnable
	Runnable test = new Runnable() {

		@Override
		public void run() {
			Log.d("test-run", "running...");
			handler.postDelayed(test, (long) (10 / pWorld.timeStep));
		}
	};

	// create runnable
	Runnable update = new Runnable() {

		@Override
		public void run() {
			Log.d("box2d", "in runnable... starting update");
			pWorld.update();
			Log.d("box2d", "in runnable... update done...");
			handler.postDelayed(update, (long) (10 / pWorld.timeStep));
			Log.d("box2d", "in runnable... postdelayed done...");
		}
	};

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
		handler.removeCallbacks(update);
	}

	public void onStop() {
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_box2d, menu);
		return true;
	}

}
