package de.lehrbaum.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import de.lehrbaum.model.Controller;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public MainFrame() {
		super("Mediziner Memory");
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMinimumSize(new Dimension(300, 200));
		int width = (int) (screenSize.getWidth() / 2);
		int height = (int) (screenSize.getHeight() / 2);
		setPreferredSize(new Dimension(width, height));
		setLocationByPlatform(true);
	}
	
	public void setPanel(JPanel panel) {
		setContentPane(panel);
		panel.setPreferredSize(getPreferredSize());
		pack();
		setVisible(true);
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		GamePanel panel = new GamePanel(new Controller(), 5);
		frame.setPanel(panel);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		panel.setText(3,
			"Dies ist ein etwas längerer Text und soll mir mal zeigen wie der darbestellt wird.");
	}
}