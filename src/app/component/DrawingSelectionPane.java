package app.component;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import app.util.*;

/**
 * This class implements a selection for choosing
 * a drawing object type when in drawing mode.
 */
public class DrawingSelectionPane extends JToolBar {

	private final Map<String, AbstractButton> BUTTONS = new HashMap<String, AbstractButton>();
	private final ButtonGroup BGROUP;

	private void configButtons() {
		BUTTONS.put("LINE",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LINE",     "LINE"));
		BUTTONS.put("FREEHAND", UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "FREEHAND", "FREEHAND"));
		BUTTONS.put("CURVE",    UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "CURVE",    "CURVE"));
		BUTTONS.put("RECT",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "RECT",     "RECTANGLE"));
		BUTTONS.put("RRECT",    UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "RRECT",    "ROUNDRECT"));
        BUTTONS.put("OVAL",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "OVAL",     "OVAL"));
        BUTTONS.put("POLYGON",  UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "POLYGON",  "POLYGON"));
        BUTTONS.put("TEXT",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "TEXT",     "TEXT"));
        BUTTONS.put("IMAGE",    UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "IMAGE",    "IMAGE"));
	}

	public DrawingSelectionPane(final AbstractButton invoker) {
		setFloatable(false);
		setFont(getFont().deriveFont(12.f));
		setLayout(new GridLayout(1, BUTTONS.size()));
		configButtons();
		BGROUP = new ButtonGroup();
			BGROUP.add((AbstractButton)add(BUTTONS.get("LINE")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("FREEHAND")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("CURVE")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("RECT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("RRECT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("OVAL")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("POLYGON")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("TEXT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("IMAGE")));
		setPreferredSize(new Dimension(350, 35));
		for (final AbstractButton button : BUTTONS.values()) {
			button.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					if (button.isSelected()) {
						getParent().setVisible(false);
						if (invoker != null) {invoker.setText(button.getText());}
					}
				}
			}); // addActionListener
		} // foreach button
		if (invoker != null) {
			invoker.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					if (invoker.isSelected() && BGROUP.getSelection() == null) {
						BUTTONS.get("LINE").setSelected(true);
						invoker.setText(BUTTONS.get("LINE").getText());
					} // no button selected
				}
			}); // addActionListener
		} // invoker exists
	}

} // DrawingSelectionPane
