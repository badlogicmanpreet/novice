package com.jmon.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Connector implements PropertyChangeListener {

	public final static int ONE_SECOND = 1000;
	public static JProgressBar progressBar;
	public static Timer timer;
	public static int lengthOfTask = 0;
	public MFrame mFrame;
	JDialog dialog;
	ProgressWorker task;

	public Connector(MFrame mFrame) {
		this.mFrame = mFrame;
	}

	public void createProgressBar(String server, String user, String password, String port) {
		dialog = new JDialog(mFrame, " ", true);
		dialog.setUndecorated(true);
		JPanel panel = new JPanel();
		panel.setBackground(Color.lightGray);
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setIndeterminate(true);
		progressBar.setBackground(Color.white);
		panel.add(progressBar);
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.pack();
		dialog.setLocationRelativeTo(mFrame);
		startProgress(server, user, password, port);
		dialog.setVisible(true);
	}

	public void startProgress(String server, String user, String password, String port) {
		task = new ProgressWorker(server, user, password, port);
		task.addPropertyChangeListener(this);
		task.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		}
		if (task.isDone()) {
			progressBar.setVisible(false);
			dialog.setVisible(false);
		}
	}

}
