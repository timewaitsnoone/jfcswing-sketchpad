package app.tests;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import app.component.SwitchingPanel;
import app.component.ColorPicker;
import app.component.DrawingSelectionPane;
import app.component.SettingsPanel;
import app.util.UITheme;

public class testSwitchingPanel {
    public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		SwitchingPanel pane = new SwitchingPanel();
			pane.add(new ColorPicker(Color.WHITE), "Color");
			pane.add(new SettingsPanel(), "Settings");
			pane.add(new DrawingSelectionPane(null), 1);
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
    }
} // testSwitchingPanel
