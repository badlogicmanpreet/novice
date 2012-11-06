package com.voyager.cloud;

import java.util.ArrayList;

import com.voyager.cloud.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

public class HospitalReportActivity extends DashboardActivity {

	String tag = "HospitalReportActivity";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_report_activity);
		
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
		ArrayList<String> records = xmlProcessor.getHospitalReport();
		int rowCount = xmlProcessor.getHospitalRowCount();
	
		TableLayout table = (TableLayout) findViewById(R.id.hospitalTable);
//		ArrayList<String> records = new ArrayList<String>();
//		records.add("Items");
//		records.add("3");
//		records.add("Vendor");
//		records.add("11");
//		records.add("ItemLoc");
//		records.add("4");
		addDynamicRows(table, rowCount, records);
	}
	}
}
