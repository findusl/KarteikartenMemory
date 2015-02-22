package de.lehrbaum.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	protected Controller c;
	private int numCards;
	private int cardsPerRow;
	private MemoryCardView[] cards;
	
	public GamePanel(Controller c, int cardNumbers) {
		super();
		this.c = c;
		numCards = cardNumbers;
		double x = Math.sqrt(numCards);
		cardsPerRow = (int) x;
		//round up
		if (cardsPerRow < x)
			cardsPerRow++;
		
		initialize();
	}
	
	public void setText(int fieldID, String text) {
		cards[fieldID].setText(text);
	}
	
	public void hideField(int fieldID) {
		cards[fieldID].hideText();
	}
	
	public void setSolved(int fieldID) {
		cards[fieldID].setSolved();
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		double width = preferredSize.getWidth();
		double height = preferredSize.getHeight();
		double picSize = Double.min(width, height);
		picSize /= cardsPerRow;
		Dimension d = new Dimension((int) picSize, (int) picSize);
		for (MemoryCardView view : cards)
			view.setPreferredSize(d);
		super.setPreferredSize(preferredSize);
	}
	
	protected void initialize() {
		setLayout(new GridLayout(0, cardsPerRow));
		cards = new MemoryCardView[numCards];
		for (int i = 0; i < numCards; i++) {
			MemoryCardView view = new MemoryCardView(c, i);
			cards[i] = view;
			add(view);
		}
	}
}