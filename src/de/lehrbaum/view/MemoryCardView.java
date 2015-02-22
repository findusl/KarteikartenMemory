package de.lehrbaum.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class MemoryCardView extends JButton implements ActionListener {
	private static final String iconPath = "/icons/Sweetheart-Annie-Splash.png";
	private static Icon buttonIcon;
	
	protected Controller c;
	protected int cardID;
	protected JTextArea textArea;
	
	public MemoryCardView(Controller c, int id) {
		super();
		this.c = c;
		cardID = id;
		setBorder(new LineBorder(Color.BLACK, 6));
		if (buttonIcon == null) {
			URL url = getClass().getResource(iconPath);
			buttonIcon = new ImageIcon(url);
		}
		setIcon(buttonIcon);
		addActionListener(this);
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setVisible(false);
		textArea.setEditable(false);
		textArea.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				actionPerformed(null);
			}
		});
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
	
	public void setSolved() {
		setBorder(new LineBorder(Color.GREEN, 6));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.fieldClicked(cardID);
	}
	
	private static abstract class AbstractMouseListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {}
		
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}