package com.jmon.learn;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloWorldFrame extends JFrame {

	public HelloWorldFrame() {
		JLabel jLabel = new JLabel("Hello World");
		add(jLabel);
		this.setSize(100, 100);
		setVisible(true);
	}

	public static void main(String args[]) {
		new HelloWorldFrame();
	}

}
