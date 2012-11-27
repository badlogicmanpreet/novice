package com.jsch.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream.GetField;

import org.rev6.scf.ScpFile;
import org.rev6.scf.ScpUpload;
import org.rev6.scf.SshConnection;
import org.rev6.scf.SshException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class TestOramonSch {

	public static void main(String args[]) {
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;

		getOramonXML();
		//sendFile();
		/*try {
			session = jsch.getSession("msingh", "osrserver01.zapto.org", 22);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword("helios");
			session.connect();

			//System.out.println("echo " + "hello" + " >> /home/msingh/MyApps/Oramon/Hello.txt");
			// exec 'scp -f rfile' remotely
			//String command = "echo man >> /home/msingh/MyApps/Oramon/man.txt";
			String command = "/home/msingh/MyApps/Oramon/RegistrationService.sh " + "/home/msingh/MyApps/Oramon ID-Manpreet";
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			String data = readInputStreamAsString(in);
			System.out.println("data is : " + data);

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}

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

	public static void sendFile() {
		String host = "osrserver01.zapto.org";
		String username = "msingh";
		String password = "helios";
		String remotePath = "foo.txt";

		SshConnection ssh = null;

		try {
			ssh = new SshConnection(host, username, password);
			ssh.connect();
			ScpFile scpFile = new ScpFile(new File("D:/Test/Test.txt"), remotePath);
			ssh.executeTask(new ScpUpload(scpFile));
		} catch (SshException e) {
			e.printStackTrace();
		} finally {
			if (ssh != null) {
				ssh.disconnect();
			}
		}
	}
	
	public static void getOramonXML() {
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
			//command = "pwd";
			//channel = session.openChannel("exec");
			channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel;
			channelSftp.cd("/home/msingh/MyApps/Oramon");
			InputStream in = channelSftp.get("oramon.xml");
			
			//((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp OutputStream out =
			//channel.getOutputStream();
			//InputStream in = channel.getInputStream();

			//channel.connect();

			String data = readInputStreamAsString(in);
			System.out.println(data);
		} catch (JSchException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
