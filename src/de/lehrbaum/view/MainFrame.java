package de.lehrbaum.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		super("Mediziner Memory");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setPanel(JPanel panel) {
		setContentPane(panel);
		pack();
		setVisible(true);
	}
}