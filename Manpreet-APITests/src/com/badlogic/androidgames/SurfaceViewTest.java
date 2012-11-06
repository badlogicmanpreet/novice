package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {
	FastRenderView fastRenderView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		fastRenderView = new FastRenderView(this);
		setContentView(fastRenderView);
	}

	public void onResume() {
		super.onResume();
		fastRenderView.onResume();
	}

	public void onPause() {
		super.onPause();
		fastRenderView.onPause();
	}

	class FastRenderView extends SurfaceView implements Runnable {
		Thread renderThread;
		SurfaceHolder holder;
		volatile boolean running = false;

		public FastRenderView(Context context) {
			super(context);
			holder = getHolder();
		}

		public void onResume() {
			running = true;
			renderThread = new Thread(this);
			renderThread.start();
		}

		public void run() {
			while (running) {
				if (!holder.getSurface().isValid()) {
					continue;
				} else {
					Canvas canvas = holder.lockCanvas();
					canvas.drawRGB(255, 0, 0);
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}

		public void onPause() {
			running = false;
			while (true) {
				try {
					renderThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
