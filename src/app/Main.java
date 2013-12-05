package app;

import javax.swing.*;
import app.component.*;
import app.util.*;

/**
 * This is the main class of the application.
 */
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				configAppState();
				createAndShowGUI();
			}
		});
	}
	private static void configAppState() {
		// TODO
	}
	private static void createAndShowGUI() {
		UITheme.setLookAndFeel();
		AppWindow window = new AppWindow(new JPanel());
		window.setVisible(true);
	}
} // Main
