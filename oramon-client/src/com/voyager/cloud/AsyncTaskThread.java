package com.voyager.cloud;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class AsyncTaskThread extends AsyncTask<Integer, Integer, String> {

	String tag = "AsyncTaskThread";
	String oramonXML;
	
	@Override
	protected String doInBackground(Integer... numSeconds) {

		int totalSecs = numSeconds[0].intValue();
		Log.d(tag, "Total SECS: " + totalSecs);

		Log.d(tag, "In doInBackground");
		oramonXML = getOramonXML();
		Log.d(tag, "XML String is = " + oramonXML);
		return oramonXML;
	}

	protected void onProgressUpdate(Integer... progress) {

		float percentage = ((float) progress[0] / (float) 10) * 100;
		Log.d(tag, "Percentage of progress: " + percentage);
	}

	public String getOramonXML() {
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;
		String command = null;
		String oramonXML = null;

		try {
			session = jsch.getSession("msingh", "osrserver01.zapto.org", 22);

			publishProgress(1);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");

			publishProgress(2);

			session.setConfig(config);
			session.setPassword("helios");

			publishProgress(3);

			session.connect();

			publishProgress(4);

			channel = session.openChannel("sftp");

			publishProgress(5);

			channel.connect();

			publishProgress(6);

			ChannelSftp channelSftp = (ChannelSftp) channel;

			publishProgress(7);

			channelSftp.cd("/home/msingh/MyApps/Oramon");

			publishProgress(8);

			InputStream in = channelSftp.get("oramon.xml");

			publishProgress(9);

			oramonXML = readInputStreamAsString(in);

			publishProgress(10);
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
		Log.d(tag, "OramonXML is : " + oramonXML);
		return oramonXML;
	}

	public String readInputStreamAsString(InputStream in) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(in);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result = bis.read();
		while (result != -1) {
			byte b = (byte) result;
			buf.write(b);
			result = bis.read();
		}
		return buf.toString();
	}

}
