package app.component;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class implements the application window.
 */
public class AppWindow extends JFrame {
	public AppWindow(JPanel pane) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				//App.requestClose();
			}
		});	
		getContentPane().add(new MainToolbar(), BorderLayout.NORTH);
		getContentPane().add(new DrawingViewport(pane), BorderLayout.CENTER);
		setMinimumSize(new Dimension(600, 480));
		setPreferredSize(new Dimension(800, 600));
		pack();
	}
} // AppWindow