package de.lehrbaum.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class MemoryCardView extends JButton implements ActionListener {
	private static final Icon buttonIcon;
	
	static {
		buttonIcon = new ImageIcon("res/Sweetheart-Annie-Splash.png");
	}
	
	protected Controller c;
	protected int cardID;
	
	public MemoryCardView(Controller c, int id) {
		super();
		this.c = c;
		cardID = id;
		setBorder(new LineBorder(Color.BLACK, 10));
		setIcon(buttonIcon);
		addActionListener(this);
	}
	
	@Override
	public void setText(String text) {
		setIcon(null);
		setToolTipText(text);
		super.setText(text);
	}
	
	public void hideText() {
		super.setText("");
		setIcon(buttonIcon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.fieldClicked(cardID);
	}
}
