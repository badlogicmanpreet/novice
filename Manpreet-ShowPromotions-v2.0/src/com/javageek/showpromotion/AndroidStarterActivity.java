package com.javageek.showpromotion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AndroidStarterActivity extends Activity {

	float degrees = 0;
	Thread t;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		//setContentView(R.layout.search_view);
		Intent intent = new Intent(AndroidStarterActivity.this, SearchActivity.class);
		startActivity(intent);
	}

}
