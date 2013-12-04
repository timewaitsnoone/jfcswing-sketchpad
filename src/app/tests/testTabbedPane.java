package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.component.ColorPicker;
import app.component.SettingsPanel;
import app.component.TabbedPane;
import app.util.UITheme;

public class testTabbedPane {
    public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		TabbedPane ap = new TabbedPane();
			ap.add(new ColorPicker(Color.WHITE), ap.new TabButton("Fill", "BUCKET"));
			ap.add(new ColorPicker(Color.WHITE), ap.new TabButton("Outline", "PEN"));
			ap.add(new SettingsPanel(), ap.new TabButton("Settings", "GEAR"));
		frame.add(ap);
		frame.pack();
		frame.setVisible(true);
    }
} // testAccordionPanel
