package com.javageek.showpromotion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

public class SearchActivity extends Activity {
	Button whereami_button;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.search_view);
		EditText search = (EditText)findViewById(R.id.search_editText1);
		search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
		            performSearch();
		            return true;
		        }
		        return false;
		    }
		});
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d("TestingManpreet", "I am in");
//		ImageView searchView = (ImageView)findViewById(R.id.searchView1);
//		searchView.setBackgroundColor(Color.BLUE);
//		ImageView imageView = (ImageView)findViewById(R.id.search_imageView1);
//		imageView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//	             Intent intent = new Intent(SearchActivity.this, PromotionActivity.class);
//	             startActivity(intent);
//			}
//		});
		// whereami_button = (Button) findViewById(R.id.search_button1);
		// whereami_button.setBackgroundColor(Color.LTGRAY);
	}

	void performSearch(){
		Toast.makeText(this, "Store locations", Toast.LENGTH_LONG).show();
	    Intent intent = new Intent(SearchActivity.this, PromotionActivity.class);
        startActivity(intent);
	}
}
