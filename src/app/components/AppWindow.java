package app.components;

import java.awt.*;
import javax.swing.*;

/**
 * This class implements the application window.
 */
public class AppWindow extends JFrame {
	public AppWindow(JPanel pane) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // FIXME 
		getContentPane().add(new MainToolbar(), BorderLayout.NORTH);
		getContentPane().add(new DrawingViewport(pane), BorderLayout.CENTER);
		setMinimumSize(new Dimension(600, 480));
		setPreferredSize(new Dimension(800, 600));
		pack();
	}
} // AppWindow
