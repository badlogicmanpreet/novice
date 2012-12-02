package com.jmon.learn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class Example_CheckBox extends JFrame {

	public JLabel jLabel;

	public Example_CheckBox() {
		initUI();
	}

	public void initUI() {
		JMenuBar jMenuBar = new JMenuBar();

		// TODO
		JMenu jMenu = new JMenu("View");
		jMenu.setMnemonic(KeyEvent.VK_F);

		JCheckBoxMenuItem jMenuItem = new JCheckBoxMenuItem("Status Bar");
		jMenuItem.setState(true);
		jMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jLabel.isVisible()) {
					jLabel.setVisible(false);
				} else {
					jLabel.setVisible(true);
				}
			}
		});

		jMenu.add(jMenuItem);

		jMenuBar.add(jMenu);

		setJMenuBar(jMenuBar);

		jLabel = new JLabel("Status Bar");
		jLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		add(jLabel, BorderLayout.SOUTH);

		setTitle("JCheckBoxMenuItem");
		setSize(360, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Example_CheckBox example = new Example_CheckBox();
				example.setVisible(true);
			}
		});
	}

}
