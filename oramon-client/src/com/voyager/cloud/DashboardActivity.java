package com.voyager.cloud;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

import com.voyager.cloud.R;

public abstract class DashboardActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void onStart() {
		super.onStart();
	}

	public void OnClick(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.button1:
			startActivity(new Intent(getApplicationContext(), ProcessReportActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(getApplicationContext(), JMSReportActivity.class));
			break;
		case R.id.button3:
			startActivity(new Intent(getApplicationContext(), HospitalReportActivity.class));
			break;
		case R.id.button4:
			break;
		case R.id.imageView1:
			startActivity(new Intent(getApplicationContext(), HomeActivity.class));
			break;
		case R.id.textView1:
			startActivity(new Intent(getApplicationContext(), HomeActivity.class));
			break;	
		default:
			System.out.println("Sorry click doesnt work");
		}
	}

	public void addDynamicRows(TableLayout table, int numRows, ArrayList<String> records) {
		TableRow row;
		TextView t1, t2;
		int col = 0;
		int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 1, getResources().getDisplayMetrics());

		for (int i = 0; i < numRows; i++) {
			row = new TableRow(this);
			t1 = new TextView(this);
			t2 = new TextView(this);

			t1.setText(records.get(col++));
			t2.setText(records.get(col++));
			t1.setTypeface(null, 1);
			t2.setTypeface(null, 1);
			t1.setTextSize(15);
			t2.setTextSize(15);

			row.addView(t1);
			row.addView(t2);

			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
	
	public void onClickHome (View v)
	{
	    goHome (this);
	}	

	public void goHome(Context context) 
	{
	    final Intent intent = new Intent(context, HomeActivity.class);
	    intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    context.startActivity (intent);
	}


}