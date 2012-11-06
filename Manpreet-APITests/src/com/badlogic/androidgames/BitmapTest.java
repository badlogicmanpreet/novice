package com.badlogic.androidgames;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BitmapTest extends Activity {

	class RenderView extends View {
		Bitmap bob565;
		Bitmap bob4444;
		Rect dst = new Rect();

		public RenderView(Context context) {
			super(context);

			try {
				AssetManager assetManager = context.getAssets();
				InputStream inputStream = assetManager.open("bobrgb888.png");
				bob565 = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
				Log.d("BitmapTest", "bob565 format is: " + bob565.getConfig());

				inputStream = assetManager.open("bobargb8888.png");
				Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_4444;
				bob4444 = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
				Log.d("BitmapTest", "bob4444 format is: " + bob4444.getConfig());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		protected void onDraw(Canvas canvas) {
			dst.set(50, 50, 100, 100);
			canvas.drawBitmap(bob565, null, dst, null);
			canvas.drawBitmap(bob4444, null, dst, null);
			invalidate();
		}
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new RenderView(this));
	}

}
