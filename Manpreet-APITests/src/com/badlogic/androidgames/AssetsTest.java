package com.badlogic.androidgames;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class AssetsTest extends Activity {

	TextView textView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		setContentView(textView);
		AssetManager assetManager = this.getAssets();

		try {
			InputStream inputStream = assetManager.open("AssetsTest.txt");
			String text = readInputStreamToString(inputStream);
			textView.setText(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readInputStreamToString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		int ch = inputStream.read();
		while (ch != -1) {
			bStream.write(ch);
			ch = inputStream.read();
		}

		return bStream.toString();
	}

}
