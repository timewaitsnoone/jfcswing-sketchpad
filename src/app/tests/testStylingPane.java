package app.tests;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import app.component.StylingPane;
import app.util.UITheme;

public class testStylingPane {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		StylingPane pane = new StylingPane(null);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}
} // testStylingPane
