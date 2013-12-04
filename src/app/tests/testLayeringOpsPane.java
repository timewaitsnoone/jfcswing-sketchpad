package app.tests;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.component.LayeringOpsPane;
import app.util.UITheme;

public class testLayeringOpsPane {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		LayeringOpsPane pane = new LayeringOpsPane(null);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}
} // testLayeringOpsPane
