package de.lehrbaum.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
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
	protected JTextArea textArea;
	
	public MemoryCardView(Controller c, int id) {
		super();
		this.c = c;
		cardID = id;
		setBorder(new LineBorder(Color.BLACK, 6));
		setIcon(buttonIcon);
		addActionListener(this);
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setVisible(false);
		add(textArea);
	}
	
	@Override
	public void setText(String text) {
		setIcon(null);
		setToolTipText(text);
		textArea.setText(text);
		textArea.setToolTipText(text);
		textArea.setVisible(true);
	}
	
	public void hideText() {
		textArea.setVisible(false);
		setIcon(buttonIcon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.fieldClicked(cardID);
	}
}
