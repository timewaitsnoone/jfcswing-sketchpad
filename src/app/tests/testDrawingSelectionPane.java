package app.tests;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.component.DrawingSelectionPane;
import app.util.UITheme;

public class testDrawingSelectionPane {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		DrawingSelectionPane pane = new DrawingSelectionPane(null);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}
} // testDrawingSelectionPane
