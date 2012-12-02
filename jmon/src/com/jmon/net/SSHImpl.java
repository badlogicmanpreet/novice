package com.jmon.net;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHImpl implements INet {

	Session session = null;
	public static Channel channel = null;
	String command = null;
	String result = null;

	public Channel connect(String server, String user, String password, String port) {
		JSch jsch = new JSch();

		try {
			session = jsch.getSession(user, server, 22);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(password);
			session.connect();

			channel = session.openChannel("exec");
		} catch (JSchException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel;
	}

	@Override
	public String cmd(String command) {
		try {
			// command =". .profile; cd /appl/seebeyond/egtnbs01/scripts; topiclist.sh";
			System.out.println(channel.isConnected());
			System.out.println(channel.toString());
			((ChannelExec) channel).setCommand(command);

			InputStream in = channel.getInputStream();

			channel.connect();

			result = readInputStreamAsString(in);
			System.out.println(result);
		} catch (JSchException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		}
		return result;
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
}
