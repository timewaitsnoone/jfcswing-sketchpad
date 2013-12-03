package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.components.AccordionPanel;
import app.components.ColorPicker;
import app.components.DrawingSelectionPane;
import app.components.SettingsPanel;
import app.util.UITheme;

public class testAccordionPanel {
    public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		AccordionPanel ap = new AccordionPanel(true);
			ap.add(new ColorPicker(Color.WHITE), "Color");
			ap.add(new SettingsPanel(), "Settings");
			ap.add(new DrawingSelectionPane(null), 1);
		frame.add(ap);
		frame.pack();
		frame.setVisible(true);
    }
} // testAccordionPanel
