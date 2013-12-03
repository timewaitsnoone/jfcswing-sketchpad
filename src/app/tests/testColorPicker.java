package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.components.ColorPicker;
import app.util.UITheme;

public class testColorPicker {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new ColorPicker(Color.WHITE));
		frame.pack();
		frame.setVisible(true);
	}
} // testColorPicker
