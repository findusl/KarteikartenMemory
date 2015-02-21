package de.lehrbaum.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class MainMenu extends MyPanel {
	public MainMenu(Controller c) {
		super(c);
		initialize();
	}
	
	protected void initialize() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		add(Box.createRigidArea(new Dimension(10, 20)));
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.startGameClicked();
			}
		});
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		add(startButton);
		JButton addCardButton = new JButton("Karte hinzufügen");
		addCardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.createCardClicked();
			}
		});
		addCardButton.setAlignmentX(CENTER_ALIGNMENT);
		add(addCardButton);
		setAlignmentY(CENTER_ALIGNMENT);
	}
}