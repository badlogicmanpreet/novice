 package com.voyager.cloud;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Entity;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class RegisterService extends IntentService {

	String tag = "RegisterService";
	
	
	public RegisterService(){
	  super("RegisterService");	
	}
	
	public RegisterService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(tag, "In RegisterService");
		String regID = intent.getStringExtra("regID");
        Log.d(tag, "regID is " + regID);
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		String deviceID = telephonyManager.getDeviceId();
		Log.d(tag, "deviceID is " + deviceID);
		callSecureChannel(deviceID, regID);
	}
	
	private void callSecureChannel(String deviceID, String regID){
		Log.d(tag, "In Jsch Call");
		SecureChannel secureChannel = new SecureChannel();
		secureChannel.sendRegID(regID);
		Log.d(tag, "Jsch Call completed");
/*		Log.d(tag, "In HTTP Call");
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        HttpParams params = new BasicHttpParams();
        params.setParameter("Test", regID);
        try {
			request.setURI(new URI("http://10.0.2.2:8080?key="+regID));
			request.setParams(params);
			HttpResponse response = client.execute(request);
			System.out.println(response.toString());
        } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
       
//		HttpClient httpClient = new DefaultHttpClient();
//		String postURL = "http://10.0.2.2:8080";
//		HttpPost httpPost = new HttpPost(postURL);
//		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//		params.add(new BasicNameValuePair("accountName", "manpreeteducation@gmail.com"));
//		params.add(new BasicNameValuePair("registrationId", regID));
//		try {
//			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params);
//			httpPost.setEntity(ent);
//			HttpResponse httpResponse = httpClient.execute(httpPost);
//			int resultStatus = httpResponse.getStatusLine().getStatusCode();
//			Log.d(tag, "resultStatus is " + resultStatus);
//			HttpEntity httpEntity = httpResponse.getEntity();
//			if(httpEntity != null){
//				Log.d(tag, "Success " + EntityUtils.toString(httpEntity));
//				Log.d(tag, httpEntity.toString());
//			} else {
//				Log.d(tag, "Failure " + EntityUtils.toString(httpEntity));
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void createRegIDFile(String regID) {
		FileOutputStream f;
		try {
			f = this.openFileOutput("RegistrationID.txt", MODE_PRIVATE);
			byte buf[] = regID.getBytes();
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
	
}
