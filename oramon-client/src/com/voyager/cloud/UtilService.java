package com.voyager.cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class UtilService extends IntentService {

	String tag = "UtilService";

	public UtilService(){
		  super("UtilService");	
	}
	
	public UtilService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(tag, "In UtilService");
		String oramonXML = intent.getStringExtra("oramonXML");
        Log.d(tag, "oramonXML is " + oramonXML);
        writeFile(oramonXML);
	}

	public void fileTest(String oramonXML) {
		// this.getDir("example", MODE_PRIVATE);
		// http://www.anddev.org/working%5Fwith%5Ffiles-t115.html
		try {
			FileOutputStream f = this.openFileOutput("oramon", MODE_PRIVATE);
			String source = oramonXML;
			byte buf[] = source.getBytes();
			for (int i = 0; i < buf.length; i++) {
				f.write(buf[i]);
			}
			f.close();
			BufferedReader reader = new BufferedReader(new FileReader(new File("/data/data/com.voyager.cloud/files/oramon")));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Log.d(tag, "final output = " + line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeFile(String file) {
		FileOutputStream f;
		try {
			f = this.openFileOutput("oramon", MODE_PRIVATE);
			String source = file;
			byte buf[] = source.getBytes();
			for (int i = 0; i < buf.length; i++) {
				f.write(buf[i]);
			}
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String readFile(){
		BufferedReader reader;
		String file = null;
		try {
			reader = new BufferedReader(new FileReader(new File("/data/data/com.voyager.cloud/files/oramon")));
			while ((file = reader.readLine()) != null) {
				Log.d(tag, file);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
}
