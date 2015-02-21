package de.lehrbaum.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class CreateCardPanel extends JPanel {
	private static final Object hintKey = new Object();
	
	protected Controller c;
	private JTextField nameField;
	private JTextArea desArea;
	
	public CreateCardPanel(Controller c) {
		super();
		this.c = c;
		initialize();
	}
	
	protected void initialize() {
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
	
	public static void addHint(final JTextComponent comp, final String hint) {
		comp.putClientProperty(hintKey, hint);
		final Font normalFont = comp.getFont();
		final Font hintFont = normalFont.deriveFont(Font.ITALIC);
		if (!comp.hasFocus()) {
			comp.setText(hint);
			comp.setFont(hintFont);
		}
		FocusPropertyListener listener = new FocusPropertyListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(focusPropertyName)
					&& evt.getNewValue() instanceof Boolean) {
					boolean focused = (boolean) evt.getNewValue();
					if (focused)
						focusGained(null);
					else
						focusLost(null);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (comp.getText().isEmpty()) {
					comp.setFont(hintFont);
					comp.setText(hint);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (comp.getText().equals(hint)) {
					comp.setText(new String());
					comp.setFont(normalFont);
				}
			}
		};
		comp.addFocusListener(listener);
		comp.addPropertyChangeListener(FocusPropertyListener.focusPropertyName, listener);
	}
	
	private abstract static class FocusPropertyListener implements FocusListener,
		PropertyChangeListener {
		/**
		 * The name of the focus property.
		 */
		public static final String focusPropertyName = "focus";
	}
}