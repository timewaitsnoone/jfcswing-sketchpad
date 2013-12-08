package app.component;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import app.util.*;

/**
 * Custom button that is attached to a
 * popup that contains a color picker.
 */
public class ColorPickerButton extends JToggleButton {

	private static final String PATH = "/assets/htdocs/cpButton.html";
	private static final String HTML = FileIO.getHTML(PATH);
	private String icon = "";
	private ColorPicker cp;

	/**
	 * Constructs a new color picker button.
	 * 
	 * @param icon				the name of the icon to display
	 * @param initialColor		the initial color
	 */
	public ColorPickerButton(String icon, Color initialColor) {
		if (MyFont.ICONS.containsKey(icon)) {
			this.icon = MyFont.ICONS.get(icon);
		}
		this.cp = new ColorPicker(initialColor);
		UIToolbox.attachPopupPane(this, this.cp, null, PopupPane.BOTTOM_LEFT, true);
		this.cp.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override public void stateChanged(ChangeEvent e) {
				updateText();
			}
		}); // addChangeListener
		updateText();
		setMargin(new Insets(5, 10, 5, 10));
	}

    /** Refreshes the display on the button. */
    private void updateText() {
        String color = colorToHex(cp.getColor());
        setText(HTML.replace("{ICON}", icon).replace("{HEXCOLOR}", color));
    }

    /**
     * Returns the hexcode of the given color.
     *
     * @return the hexcode of the given color.
     */
    private String colorToHex(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return String.format("%02x%02x%02x", r, g, b);
    }    

} // ColorPickerButton
