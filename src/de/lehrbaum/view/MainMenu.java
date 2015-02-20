package de.lehrbaum.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	Controller c;
	
	public MainMenu(Controller c) {
		super();
		this.c = c;
		initialize();
	}
	
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}