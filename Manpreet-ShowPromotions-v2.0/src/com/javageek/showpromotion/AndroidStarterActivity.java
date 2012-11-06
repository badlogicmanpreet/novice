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
		setContentView(R.layout.android_starter_view);

		// animation
		/*
		 * Animation anim = new RotateAnimation(0.0f, 360.0f,
		 * Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		 * anim.setInterpolator(new LinearInterpolator());
		 * anim.setRepeatCount(Animation.INFINITE); anim.setDuration(60000);
		 * final ImageView imageView = (ImageView)
		 * findViewById(R.id.starter_image); imageView.startAnimation(anim);
		 */

		// button click
		/*
		 * final Animation animation = new AlphaAnimation(1, 0); // Change alpha
		 * from fully visible to invisible animation.setDuration(500); //
		 * duration - half a second animation.setInterpolator(new
		 * LinearInterpolator()); // do not alter animation rate
		 * animation.setRepeatCount(Animation.INFINITE); // Repeat animation
		 * infinitely animation.setRepeatMode(Animation.REVERSE); // Reverse
		 * animation at the end so the button will fade back in
		 */
		Button button1 = (Button) findViewById(R.id.starter_button1);
		// button.startAnimation(animation);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Testing click", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(AndroidStarterActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});
		
		Button button2 = (Button)findViewById(R.id.starter_button2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			Intent intent = new Intent(AndroidStarterActivity.this, WhereAmIActivity.class);
			startActivity(intent);
			}
		});
	}

}
