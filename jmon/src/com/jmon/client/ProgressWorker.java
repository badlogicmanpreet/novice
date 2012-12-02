package com.jmon.client;

import javax.swing.SwingWorker;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jmon.net.SSHImpl;

public class ProgressWorker extends SwingWorker<Void, Void> {

	static int progress;
	String server;
	String user;
	String password;
	String port;
	Session session = null;
	SSHImpl sshImpl;

	public ProgressWorker(String server, String user, String password, String port) {
		this.server = server;
		this.user = user;
		this.password = password;
		this.port = port;
	}

	@Override
	protected Void doInBackground() throws Exception {
		progress = 0; //TODO - check if this property matters
		setProgress(0);

		JSch jsch = new JSch();
		setProgress(5);
		try {
			session = jsch.getSession(user, server, 22);
			setProgress(20);
			java.util.Properties config = new java.util.Properties();
			setProgress(25);
			config.put("StrictHostKeyChecking", "no");
			setProgress(30);
			session.setConfig(config);
			setProgress(40);
			session.setPassword(password);
			setProgress(50);
			session.connect();
			
			SSHImpl.channel = session.openChannel("exec");
			setProgress(80);
			
			sshImpl = new SSHImpl();
			//TODO - implement jmon on server side
			//sshImpl.cmd(". .profile; cd /appl/seebeyond/egtnbs01/scripts; topiclist.sh");
			sshImpl.cmd("pwd");
			setProgress(100);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void done() {
		try {
			super.get();

			System.out.println("done");
		} catch (Throwable t) {
		}
	}
}
