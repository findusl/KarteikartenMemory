package de.lehrbaum.view;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import de.lehrbaum.model.Controller;

public abstract class MyPanel extends JPanel {
	private static final Object hintKey = new Object();
	
	protected final Controller c;
	
	public MyPanel(Controller c) {
		super();
		this.c = c;
		initialize();
	}
	
	protected abstract void initialize();
	
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