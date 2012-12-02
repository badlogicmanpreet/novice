package com.jmon.learn;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class Example_PopupMenu extends JFrame {

	Toolkit toolkit;
	JPopupMenu jPopupMenu;

	public Example_PopupMenu() {
		initUI();
	}

	public void initUI() {
		toolkit = this.getToolkit();

		jPopupMenu = new JPopupMenu();

		JMenuItem jMenuItem = new JMenuItem("Beep");
		jMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Beep");
				toolkit.beep();
			}
		});

		jPopupMenu.add(jMenuItem);

		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON3) {
					jPopupMenu.show(event.getComponent(), event.getX(), event.getY());
				}
			}
		});

		setTitle("Popup Menu");
		setSize(250, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Example_PopupMenu example = new Example_PopupMenu();
				example.setVisible(true);
			}
		});
	}
}
