package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener {

	TextView textView;
	StringBuilder builder = new StringBuilder();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
        textView.setText("start test");		
        setContentView(textView);        
        SensorManager manager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
			textView.setText("No sensor");
		} else {
			//textView.setText(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size());
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			textView.setText(accelerometer.getName());
//			//Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
			if(!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)){
				textView.setText("not registered");
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("x: " + event.values[0]);
		builder.append("y: " + event.values[1]);
		builder.append("z: " + event.values[2]);
		textView.setText(builder.toString());
	}

}
