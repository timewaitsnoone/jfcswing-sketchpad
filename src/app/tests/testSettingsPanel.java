package app.tests;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import app.components.SettingsPanel;
import app.util.UITheme;

public class testSettingsPanel {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel outer = new JPanel(new GridBagLayout());
			SettingsPanel pane = new SettingsPanel();
			outer.add(pane);
		frame.add(outer);				
		frame.pack();
		frame.setVisible(true);
	}
} // testSettingsPanel
