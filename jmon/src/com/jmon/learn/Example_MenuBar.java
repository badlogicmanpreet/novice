package com.jmon.learn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Example_MenuBar extends JFrame {

	public Example_MenuBar() {
		initUI();
	}

	public void initUI() {
		JMenuBar jMenuBar = new JMenuBar();
		ImageIcon iconNew = new ImageIcon(getClass().getResource("/icons/new.png"));
		ImageIcon iconOpen = new ImageIcon(getClass().getResource("/icons/open.png"));
		ImageIcon iconSave = new ImageIcon(getClass().getResource("/icons/save.png"));
		ImageIcon iconExit = new ImageIcon(getClass().getResource("/icons/exit.png"));

		JMenu jMenuFile = new JMenu("File");
		jMenuFile.setMnemonic(KeyEvent.VK_F);

		JMenuItem jMenuItemNew = new JMenuItem("New", iconNew);
		JMenuItem jMenuItemOpen = new JMenuItem("Open", iconOpen);
		JMenuItem jMenuItemSave = new JMenuItem("Save", iconSave);

		JMenu jMenuImport = new JMenu("Import");
		jMenuImport.setMnemonic(KeyEvent.VK_M);

		JMenuItem jMenuItemFiles = new JMenuItem("Import Files ...");
		JMenuItem jMenuItemBookMarks = new JMenuItem("Import Bookmarks ...");
		JMenuItem jMenuItemMails = new JMenuItem("Import Mails ...");

		jMenuImport.add(jMenuItemFiles);
		jMenuImport.add(jMenuItemBookMarks);
		jMenuImport.add(jMenuItemMails);

		JMenuItem jMenuItemExit = new JMenuItem("Exit", iconExit);
		jMenuItemExit.setMnemonic(KeyEvent.VK_C);
		jMenuItemExit.setToolTipText("Exit Application");
		jMenuItemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		jMenuFile.add(jMenuItemNew);
		jMenuFile.add(jMenuItemOpen);
		jMenuFile.add(jMenuItemSave);
		jMenuFile.addSeparator();
		jMenuFile.add(jMenuImport);
		jMenuFile.addSeparator();
		jMenuFile.add(jMenuItemExit);

		jMenuBar.add(jMenuFile);
		// jMenuBar.add(jMenuImport);

		setJMenuBar(jMenuBar);

		setTitle("Simple Menu");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Example_MenuBar example = new Example_MenuBar();
				example.setVisible(true);
			}
		});
	}

}
