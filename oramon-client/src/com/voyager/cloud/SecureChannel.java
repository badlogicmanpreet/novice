package com.voyager.cloud;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.rev6.scf.ScpFile;
import org.rev6.scf.ScpUpload;
import org.rev6.scf.SshConnection;
import org.rev6.scf.SshException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SecureChannel {

	String tag = "SecureChannel";

	public static void main(String args[]) {
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;

		// sendFile();
		/*
		 * try { session = jsch.getSession("msingh", "osrserver01.zapto.org",
		 * 22); java.util.Properties config = new java.util.Properties();
		 * config.put("StrictHostKeyChecking", "no"); session.setConfig(config);
		 * session.setPassword("helios"); session.connect();
		 * 
		 * // exec 'scp -f rfile' remotely String command =
		 * "ps -ef|grep msingh"; channel = session.openChannel("exec");
		 * ((ChannelExec) channel).setCommand(command);
		 * 
		 * // get I/O streams for remote scp OutputStream out =
		 * channel.getOutputStream(); InputStream in = channel.getInputStream();
		 * 
		 * channel.connect();
		 * 
		 * String data = readInputStreamAsString(in);
		 * System.out.println("data is : " + data);
		 * 
		 * } catch (JSchException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	public void sendRegID(String regID) {
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;
		String command = null;

		try {
			session = jsch.getSession("msingh", "osrserver01.zapto.org", 22);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword("helios");
			session.connect();

			//command = "CheckFile.sh RegistrationID.txt";
			//command = "echo " + regID + " >> /home/msingh/MyApps/Oramon/RegistrationID.txt";
			command = "/home/msingh/MyApps/Oramon/RegistrationService.sh " + "/home/msingh/MyApps/Oramon " + regID;
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp OutputStream out =
			channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			String data = readInputStreamAsString(in);
			Log.d(tag, "data is : " + data);
		} catch (JSchException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		}

	}

	// public boolean checkRegIDFile(){
	// BufferedReader reader;
	// try {
	// reader = new BufferedReader(new FileReader(new
	// File("/data/data/com.voyager.cloud/files/RegistrationID.txt")));
	// String line = null;
	// while ((line = reader.readLine()) != null) {
	// Log.d(tag, "checkRegIDFile = " + line);
	// }
	//		        
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static String readInputStreamAsString(InputStream in) throws IOException {

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

	public void sendFile() {
		String host = "osrserver01.zapto.org";
		String username = "msingh";
		String password = "helios";
		String remotePath = "foo.txt";

		SshConnection ssh = null;

		try {
			if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				Log.d(tag, "External SD card not mounted");
				// Toast.makeText(this, "External SD card not mounted",
				// Toast.LENGTH_LONG).show();
			}
			File rootDir = Environment.getExternalStorageDirectory();
			Log.d(tag, rootDir.getAbsolutePath());
			File file = new File(rootDir.getAbsolutePath(), "Test.txt");
			FileWriter writer;
			try {
				writer = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(writer);
				out.write("Hi Manpreet");
				Log.d(tag, "Data Writen");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ssh = new SshConnection(host, username, password);
			ssh.connect();
			ScpFile scpFile = new ScpFile(file, remotePath);
			ssh.executeTask(new ScpUpload(scpFile));
		} catch (SshException e) {
			e.printStackTrace();
		} finally {
			if (ssh != null) {
				ssh.disconnect();
			}
		}
	}
	
	public String getOramonXML() {
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;
		String command = null;
        String oramonXML = null;
        
		try {
			session = jsch.getSession("msingh", "osrserver01.zapto.org", 22);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword("helios");
			session.connect();

			//command = "CheckFile.sh RegistrationID.txt";
			//command = "echo " + regID + " >> /home/msingh/MyApps/Oramon/RegistrationID.txt";
//			command = "pwd";
//			channel = session.openChannel("exec");
//			((ChannelExec) channel).setCommand(command);
//
//			// get I/O streams for remote scp OutputStream out =
//			channel.getOutputStream();
//			InputStream in = channel.getInputStream();
//
//			channel.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel;
			channelSftp.cd("/home/msingh/MyApps/Oramon");
			InputStream in = channelSftp.get("oramon.xml");

			oramonXML = readInputStreamAsString(in);
			
	  } catch (JSchException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(tag, "OramonXML is : " + oramonXML);
		return oramonXML;
	}
	
	public String readFile() {
		BufferedReader reader;
		String file = null;
		try {
			Log.d(tag, "In readFile()");
			reader = new BufferedReader(new FileReader(new File("/data/data/com.voyager.cloud/files/oramon")));
			file = reader.readLine();

//			while ((file = reader.readLine()) != null) {
//				Log.d(tag, file);
//			}
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
