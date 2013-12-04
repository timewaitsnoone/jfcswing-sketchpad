package app.component;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import app.util.*;

/**
 * Modified ColorPicker.
 * Adds a toggle button to allow the
 * no color option.
 */
public class ColorPicker2
	extends ColorPicker
	implements ActionListener, PaintPicker {

	private final JToggleButton noColor;
	private final String text;

	/**
	 * Construct a modified Color picker.
	 * 
	 * @param color 	the initial color
	 * @param text 		to display on the no color toggle button
	 */
	public ColorPicker2(Color color, String text) {
		super(color);
		add(noColor = new JToggleButton(), GBC);
		add(new JPanel(), GBC); // padding
		this.text = text; 
		updateNoColorButton();
		noColor.addActionListener(this);
		noColor.setHorizontalAlignment(SwingConstants.LEFT);
	}

	/**
	 * Updates the text of the no color toggle button.
	 */
	private void updateNoColorButton() {
    	final String template = "<html><body style=\"text-align: left;\">"
			+ "<span style=\"font-family: Canvas;\">{ICON}</span>"
    		+ "<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>"
			+ "<span>{LABEL}</span>"
    		+ "</body></html>";
        String icon = MyFont.ICONS.get((noColor.isSelected() ? "" : "UN") + "CHECKED");
        noColor.setBackground(noColor.isSelected() ? null : Color.BLACK);
        noColor.setText(template
			.replace("{ICON}", icon)
			.replace("{LABEL}", "No " + text));
	}

	@Override public void actionPerformed(ActionEvent e) {
		setEnabled(!noColor.isSelected());
		updateNoColorButton();
		Color old = chooser.getColor();
		csmodel.setSelectedColor(Color.WHITE);
		csmodel.setSelectedColor(old);
		chooser.setVisible(isEnabled());
		getParent().revalidate();
	}

	@Override public Color getColor() { 
		return noColor.isSelected() ? null : super.getColor();
	}
	
	@Override public Color getPaint() { 
		return getColor();
	}

} // ColorPicker2