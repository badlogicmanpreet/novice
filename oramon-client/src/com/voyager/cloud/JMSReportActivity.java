package com.voyager.cloud;

import java.util.ArrayList;

import com.voyager.cloud.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

public class JMSReportActivity extends DashboardActivity {

	String tag = "JMSReportActivity";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jms_report_activity);
		
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
		ArrayList<String> records = xmlProcessor.getJMSReport();
		int rowCount = xmlProcessor.getJMSRowCount();
		
		TableLayout table = (TableLayout) findViewById(R.id.JMSTable);
//		ArrayList<String> records = new ArrayList<String>();
//		records.add("etItemsFromRMS");
//		records.add("4");
//		records.add("etVendorFromRMS");
//		records.add("10");
//		records.add("etItemLoc");
//		records.add("3");
		addDynamicRows(table, rowCount, records);
	}
	}
}
