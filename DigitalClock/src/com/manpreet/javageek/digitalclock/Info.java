package com.manpreet.javageek.digitalclock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class Info extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyDigitalClock(this));
	}

}
