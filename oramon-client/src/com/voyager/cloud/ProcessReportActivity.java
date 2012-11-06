package com.voyager.cloud;

import java.util.ArrayList;

import com.voyager.cloud.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

public class ProcessReportActivity extends DashboardActivity {
	
	String tag = "ProcessReportActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.process_report_activity);
		
		Log.d(tag, "In Jsch Call");
		SecureChannel secureChannel = new SecureChannel();
		String oramonXML = secureChannel.readFile();
		Log.d(tag, "OramonXML = " + oramonXML);
		Log.d(tag, "Jsch Call completed");
		
		if(oramonXML == null){
			Log.d(tag, "OramonXML check = " + oramonXML);
			Toast.makeText(this, "No Records to Display",Toast.LENGTH_LONG).show();
		} else {
		XMLProcessor xmlProcessor = XMLProcessor.getSingletonObject(oramonXML);
		ArrayList<String> records = xmlProcessor.getProcessReport();
		int rowCount = xmlProcessor.getPRRowCount();
		
		TableLayout table = (TableLayout) findViewById(R.id.processTable);
//		ArrayList<String> records = new ArrayList<String>();
//		records.add("ewItemsFromRMS");
//		records.add("up");
//		records.add("ewVendorFromRMS");
//		records.add("down");
//		records.add("ewItemLocFromRMS");
//		records.add("up");
		addDynamicRows(table, rowCount, records);
		}
	}

}
