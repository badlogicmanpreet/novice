package com.jmon.client;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class MFrame extends JFrame {

	boolean run = false;
	static MFrame mFrame;
	Color color;

	public MFrame(boolean run) {
		this.run = run;
	}

	public MFrame() {
		initUI();
		setUndecorated(true);
	}

	private void initUI() {
		color = new Color(198, 226, 255);
		JMenuBar menuBar = new JMenuBar();
		ImageIcon envIcon = new ImageIcon(getClass().getResource("/icons/new.png"));
		ImageIcon openIcon = new ImageIcon(getClass().getResource("/icons/open.png"));
		ImageIcon exitIcon = new ImageIcon(getClass().getResource("/icons/exit.png"));

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenu nw = new JMenu("New");
		JMenuItem environment = new JMenuItem("Environment", envIcon);
		environment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("TODO");
				EnvScreen envScreen = new EnvScreen(mFrame);
			}
		});

		nw.add(environment);

		JMenuItem open = new JMenuItem("Open", openIcon);
		JMenuItem close = new JMenuItem("Close");
		JMenuItem closeALL = new JMenuItem("CloseALL");
		JMenuItem properties = new JMenuItem("Properties");
		JMenuItem exit = new JMenuItem("Exit", exitIcon);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		file.add(nw);
		file.add(open);
		file.addSeparator();
		file.add(close);
		file.add(closeALL);
		file.addSeparator();
		file.add(properties);
		file.addSeparator();
		file.add(exit);

		menuBar.add(file);
		// menuBar.setBackground(color);

		setJMenuBar(menuBar);

		// TODO - add tabs
		Color tabsSelColor = new Color(224, 238, 224);
		UIManager.put("TabbedPane.selected", tabsSelColor);
		JTabbedPane tabs = new JTabbedPane();
		tabs.setBackground(color);
		ImageIcon tabsIcon = new ImageIcon(getClass().getResource("/icons/tabs.png"));
		tabs.addTab("JMS Monitor", tabsIcon, createJMSComp());
		tabs.addTab("Service Manager", tabsIcon, createJMSComp());

		setContentPane(tabs);

		// setLayout(null);
		setTitle("JMON");
		setSize(700, 400);
		setLocationRelativeTo(null);
		// getContentPane().setBackground(Color.gray);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createFrame() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mFrame = new MFrame();
				mFrame.setVisible(true);
			}
		});

	}

	public JPanel createJMSComp() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(color);

		return panel;
	}
}
