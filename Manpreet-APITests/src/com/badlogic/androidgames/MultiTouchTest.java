package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener {

	StringBuilder builder = new StringBuilder();
	TextView textView;
	float[] x = new float[10];
	float[] y = new float[10];
	boolean[] touched = new boolean[10];

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("MultiTouchTest");
		textView.setOnTouchListener(this);
		setContentView(textView);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		int pointerID = event.getPointerId(pointerIndex);

		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			touched[pointerID] = true;
			x[pointerID] = event.getX(pointerIndex);
			y[pointerID] = event.getY(pointerIndex);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_MOVE:
			int pointerCount = event.getPointerCount();
			for (int i = 0; i < pointerCount; i++) {
				pointerIndex = i;
				pointerID = event.getPointerId(pointerIndex);
                x[pointerID] = event.getX(pointerIndex);
                y[pointerID] = event.getY(pointerIndex);
			}
			break;
		}
		updateTextView();
		return true;
	}

	public void updateTextView() {
		builder.setLength(0);
		for (int i = 0; i < 10; i++) {
			builder.append(touched[i]);
			builder.append(", ");
			builder.append(x[i]);
			builder.append(", ");
			builder.append(y[i]);
			builder.append("\n");
		}
		textView.setText(builder.toString());
	}

}
