package com.jmon.learn;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

public class Example_FlowLayout extends JFrame {

	public Example_FlowLayout() {
		initUI();
	}

	public void initUI() {
		JPanel panel = new JPanel();

		JTextArea textArea = new JTextArea("Text Area");
		textArea.setPreferredSize(new Dimension(100, 100));

		JButton button = new JButton("Button");

		JTree tree = new JTree();

		panel.add(textArea);
		panel.add(button);
		panel.add(tree);

		getContentPane().add(panel);

		pack();

		setTitle("FlowLayout");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Example_FlowLayout example = new Example_FlowLayout();
				example.setVisible(true);
			}
		});
	}

}
