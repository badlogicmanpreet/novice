package com.jmon.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jmon.net.SSHImpl;

public class EnvScreen extends JDialog {

	MFrame mFrame;
	Container container;
	JTextField serverNameTF;
	JTextField userNameTF;
	JPasswordField passwordTF;
	JTextField portTF;
	SSHImpl sshImpl;
	Connector connector;

	public EnvScreen(MFrame mFrame) {
		super(mFrame, "Environment Details", true);
		setUndecorated(true);
		this.mFrame = mFrame;
		connector = new Connector(mFrame);
		initUI();
	}

	private void initUI() {
		Color color = new Color(245, 245, 245);
		// env field area
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(color);
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;

		JLabel serverNameLB = new JLabel("Server Name: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(serverNameLB, cs);
		serverNameTF = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(serverNameTF, cs);

		JLabel userNameLB = new JLabel("User Name: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(userNameLB, cs);
		userNameTF = new JTextField(10);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(userNameTF, cs);

		JLabel passwordLB = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(passwordLB, cs);
		passwordTF = new JPasswordField(10);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(passwordTF, cs);

		JLabel portLB = new JLabel("Port: ");
		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(portLB, cs);
		portTF = new JTextField(2);
		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		panel.add(portTF, cs);

		panel.setBorder(new TitledBorder("Details"));

		// ok & cancel logic
		JPanel panelBT = new JPanel();
		panelBT.setBackground(color);
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String server = serverNameTF.getText();
				String user = userNameTF.getText();
				String password = passwordTF.getText();
				String port = portTF.getText();
				connect(server, user, password, port);
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelBT.add(ok);
		panelBT.add(cancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(panelBT, BorderLayout.PAGE_END);
		pack();
		setResizable(false);
		setLocationRelativeTo(mFrame);
		getContentPane().setBackground(color);
		setVisible(true);

	}

	private void connect(String server, String user, String password, String port) {
		connector.createProgressBar(server, user, password, port);
		setVisible(false);
	}

}
