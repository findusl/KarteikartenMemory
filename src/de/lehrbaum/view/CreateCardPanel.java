package de.lehrbaum.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class CreateCardPanel extends MyPanel {
	
	private JTextField nameField;
	private JTextArea desArea;
	
	public CreateCardPanel(Controller c) {
		super(c);
		initialize();
	}
	
	protected void initialize() {
		Dimension verticalSpace = new Dimension(0, 5);
		//nameLabel:
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(LEFT_ALIGNMENT);
		//nameField:
		nameField = new JTextField();
		addHint(nameField, "Der Name der Medizin.");
		nameField.setAlignmentX(LEFT_ALIGNMENT);
		//descriptionLabel:
		JLabel desLabel = new JLabel("Beschreibung");
		desLabel.setAlignmentX(LEFT_ALIGNMENT);
		//desField:
		JScrollPane desScrollPane = new JScrollPane();
		desScrollPane.setAlignmentX(LEFT_ALIGNMENT);
		desArea = new JTextArea();
		addHint(desArea, "Die Beschreibung der Medizin");
		desArea.setLineWrap(true);
		desArea.setWrapStyleWord(true);
		desScrollPane.setViewportView(desArea);
		//abort Button:
		JButton abort = new JButton("Abbrechen");
		abort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.createCardAborted();
			}
		});
		//save Button:
		JButton save = new JButton("Speichern");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				if (name.isEmpty()) {
					c.createCardAborted();
					return;
				}
				String description = desArea.getText();
				c.addCard(name, description);
			}
		});
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHonorsVisibility(true);
		layout.setHorizontalGroup(layout
			.createParallelGroup(Alignment.LEADING)
			.addComponent(nameLabel)
			.addComponent(nameField)
			.addComponent(desLabel)
			.addComponent(desScrollPane)
			.addGroup(Alignment.TRAILING,
				layout.createSequentialGroup().addComponent(abort).addComponent(save)));
		layout.setVerticalGroup(layout
			.createSequentialGroup()
			.addComponent(nameLabel)
			.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addComponent(desLabel)
			.addComponent(desScrollPane)
			.addGroup(layout.createParallelGroup()
				.addComponent(abort)
				.addComponent(save)));
	}
}