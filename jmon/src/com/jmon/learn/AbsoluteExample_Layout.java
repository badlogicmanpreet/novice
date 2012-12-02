package com.jmon.learn;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AbsoluteExample_Layout extends JFrame {

	public AbsoluteExample_Layout() {
		initUI();
	}

	public void initUI() {
		setLayout(null);

		JButton ok = new JButton("Ok");
		ok.setBounds(50, 50, 80, 25);

		JButton close = new JButton("Close");
		close.setBounds(150, 50, 80, 25);

		this.add(ok);
		this.add(close);

		setTitle("Absolute Layout");
		setSize(300, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				AbsoluteExample_Layout example = new AbsoluteExample_Layout();
				example.setVisible(true);
			}
		});
	}

}
