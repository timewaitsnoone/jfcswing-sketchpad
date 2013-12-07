package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import app.component.ColorPickerButton;
import app.util.UITheme;

public class testColorPickerButton {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JToolBar pane = new JToolBar();
			pane.setFloatable(false);
			pane.add(new ColorPickerButton("BUCKET", Color.RED));
			pane.add(new ColorPickerButton("PEN", Color.GREEN));
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}
}
