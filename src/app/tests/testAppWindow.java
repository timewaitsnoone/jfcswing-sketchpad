package app.tests;

import java.awt.*;

import javax.swing.*;

import app.component.*;
import app.util.*;

public class testAppWindow {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(600, 700));
		pane.setBackground(Color.WHITE);
		AppWindow window = new AppWindow(pane);
		window.setVisible(true);
	}
} // testAppWindow
