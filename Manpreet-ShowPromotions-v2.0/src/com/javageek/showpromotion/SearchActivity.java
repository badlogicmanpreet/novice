package com.javageek.showpromotion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class SearchActivity extends Activity {
	Button whereami_button;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_view);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d("TestingManpreet", "I am in");
		ImageView imageView = (ImageView)findViewById(R.id.search_imageView1);
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	             Intent intent = new Intent(SearchActivity.this, PromotionActivity.class);
	             startActivity(intent);
			}
		});
		// whereami_button = (Button) findViewById(R.id.search_button1);
		// whereami_button.setBackgroundColor(Color.LTGRAY);
	}

}
