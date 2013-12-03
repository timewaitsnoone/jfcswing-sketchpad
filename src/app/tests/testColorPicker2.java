package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.components.ColorPicker2;
import app.util.UITheme;

public class testColorPicker2 {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new ColorPicker2(Color.WHITE, "Color"));
		frame.pack();
		frame.setVisible(true);
	}
} // testColorPicker2
