package com.jmon.learn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Example_Panel extends JFrame {

	public Example_Panel() {
		initUI();
	}

	public void initUI() {
		JPanel jPanel = new JPanel();
		getContentPane().add(jPanel);

		jPanel.setLayout(null);
		jPanel.setToolTipText("Panel Container");

		JButton jButton = new JButton("Quit");
		jButton.setBounds(50, 60, 80, 30);
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		jPanel.add(jButton);

		setTitle("Quit Button");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Example_Panel example = new Example_Panel();
				example.setVisible(true);

			}
		});
	}
}
