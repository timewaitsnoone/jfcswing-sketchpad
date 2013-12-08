package app.main;

import javax.swing.WindowConstants;
import app.component.AppWindow;
import app.util.UITheme;

public class Main {
    public static void main(String[] args) {
    	UITheme.setLookAndFeel();
    	AppWindow app = new AppWindow();
    	app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
} // Main
