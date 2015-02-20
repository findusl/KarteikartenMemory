package de.lehrbaum.view;

import javax.swing.JPanel;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private Controller c;
	
	public GamePanel(Controller c, int cardNumbers) {
		this.c = c;
	}
	
	public void setText(int fieldID, String text) {
		
	}
	
	public void hideField(int fieldID) {
		
	}
}