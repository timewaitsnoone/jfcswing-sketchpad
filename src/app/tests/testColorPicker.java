package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.component.ColorPicker;
import app.util.UITheme;

public class testColorPicker {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		ColorPicker cp;
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(cp = new ColorPicker(Color.WHITE));
		frame.pack();
		frame.setVisible(true);
		System.out.println(cp.getWidth());
		System.out.println(cp.getHeight());
	}
} // testColorPicker
