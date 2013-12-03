package app.components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class implements the application window.
 */
public class AppWindow extends JFrame {
	public AppWindow(JPanel pane) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // FIXME 
		CloseCheck closeCheck = new CloseCheck();
		this.addWindowListener(closeCheck);	
		getContentPane().add(new MainToolbar(), BorderLayout.NORTH);
		getContentPane().add(new DrawingViewport(pane), BorderLayout.CENTER);
		setMinimumSize(new Dimension(600, 480));
		setPreferredSize(new Dimension(800, 600));
		pack();
	}
} // AppWindow

class CloseCheck extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		int optionSelected = JOptionPane.showConfirmDialog(null, "Do you want to save changes?", 
				"Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (optionSelected == 0) {
			//TODO Call Save Command
			System.exit(0);
		} else if (optionSelected == 1) {
			System.exit(0);
		}
	}
} // CloseCheck