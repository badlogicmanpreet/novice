package com.jsch.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class TestScriptRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String host = "";
		String user = "oraias";
		String password = "pass2word";
		String cmd = "";
		JSch jsch = new JSch();

		Session session;
		try {
			session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(cmd);

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
