package app.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import app.component.MainToolbar;
import app.util.UITheme;

public class testMainToolbar {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new MainToolbar(), BorderLayout.NORTH);
		frame.setMinimumSize(new Dimension(600, 480));
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setVisible(true);
	}
} // testMainToolbar
