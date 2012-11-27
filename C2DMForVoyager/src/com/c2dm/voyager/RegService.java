package com.c2dm.voyager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Entity;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class RegService extends IntentService {

	String tag = "RegService";
	
	
	public RegService(){
	  super("RegService");	
	}
	
	public RegService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(tag, "In RegService");
		String regID = intent.getStringExtra("regID");
        Log.d(tag, "regID is " + regID);
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		String deviceID = telephonyManager.getDeviceId();
		Log.d(tag, "deviceID is " + deviceID);
		callHTTPClient(deviceID, regID);
	}
	
	private void callHTTPClient(String deviceID, String regID){
		Log.d(tag, "In HTTP Call");
		HttpClient httpClient = new DefaultHttpClient();
		String postURL = "http://10.0.2.2:8080/token";
		HttpPost httpPost = new HttpPost(postURL);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("accountName", "manpreeteducation@gmail.com"));
		params.add(new BasicNameValuePair("registrationId", regID));
		try {
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params);
			httpPost.setEntity(ent);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int resultStatus = httpResponse.getStatusLine().getStatusCode();
			Log.d(tag, "resultStatus is " + resultStatus);
			HttpEntity httpEntity = httpResponse.getEntity();
			if(httpEntity != null){
				Log.d(tag, "Success " + EntityUtils.toString(httpEntity));
				Log.d(tag, httpEntity.toString());
			} else {
				Log.d(tag, "Failure " + EntityUtils.toString(httpEntity));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
