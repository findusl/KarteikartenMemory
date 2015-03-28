package de.lehrbaum.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	protected Controller c;
	protected String[] categories;
	protected JList<String> catList;
	
	public MainMenu(Controller c, String[] categories) {
		super();
		this.c = c;
		this.categories = categories;
		initialize();
	}
	
	protected void initialize() {
		setBackground(Color.white);
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		add(Box.createRigidArea(new Dimension(10, 20)));
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> l = catList.getSelectedValuesList();
				c.startGameClicked(l.toArray(new String[l.size()]));
			}
		});
		startButton.setAlignmentX(CENTER_ALIGNMENT);
		add(startButton);
		add(Box.createRigidArea(new Dimension(10, 10)));
		JButton addCardButton = new JButton("Karte hinzufügen");
		addCardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.createCardClicked();
			}
		});
		addCardButton.setAlignmentX(CENTER_ALIGNMENT);
		add(addCardButton);
		add(Box.createRigidArea(new Dimension(10, 10)));
		catList = new JList<String>(categories);
		catList.setAlignmentX(CENTER_ALIGNMENT);
		catList.setMinimumSize(new Dimension(300, 10));
		catList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(catList);
		setAlignmentY(CENTER_ALIGNMENT);
	}
}