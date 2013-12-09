package app.component;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import app.main.AppConfig;
import app.util.*;

/**
 * This class implements a selection for choosing
 * a drawing object type when in drawing mode.
 */
public class DrawingSelectionPane extends JToolBar {

	private final Map<String, AbstractButton> BUTTONS = new HashMap<String, AbstractButton>();
	private final ButtonGroup BGROUP;

	private void configButtons() {
		BUTTONS.put("LINE",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LINE",     "LINE"      , "Draw Line"));
		BUTTONS.put("FREEHAND", UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "FREEHAND", "FREEHAND"  , "Draw Free Hand"));
		BUTTONS.put("CURVE",    UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "CURVE",    "CURVE"     , "Draw Curves"));
		BUTTONS.put("RECT",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "RECT",     "RECTANGLE" , "Draw Rectangle"));
		BUTTONS.put("RRECT",    UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "RRECT",    "ROUNDRECT" , "Draw Round Rectangle"));
        BUTTONS.put("OVAL",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "OVAL",     "OVAL"      , "Draw Oval"));
        BUTTONS.put("POLYGON",  UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "POLYGON",  "POLYGON"   , "Draw Polygon"));
        BUTTONS.put("TEXT",     UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "TEXT",     "TEXT"      , "Draw Text"));
        BUTTONS.put("IMAGE",    UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "IMAGE",    "IMAGE"     , "Draw Image"));
	}

	public DrawingSelectionPane(final AbstractButton invoker) {
		setFloatable(false);
		setFont(getFont().deriveFont(12.f));
		setLayout(new GridLayout(1, BUTTONS.size()));
		configButtons();
		BGROUP = new ButtonGroup();
			BGROUP.add((AbstractButton)add(BUTTONS.get("LINE")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("FREEHAND")));
			//BGROUP.add((AbstractButton)add(BUTTONS.get("CURVE")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("RECT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("RRECT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("OVAL")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("POLYGON")));
			//BGROUP.add((AbstractButton)add(BUTTONS.get("TEXT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("IMAGE")));
		setPreferredSize(new Dimension(350, 35));
		for (final AbstractButton button : BUTTONS.values()) {
			button.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					if (button.isSelected()) {
						getParent().setVisible(false);
						if (invoker != null) {invoker.setText(button.getText());}
						String action = button.getActionCommand();
						if ("LINE".equals(action)) {
							AppConfig.mode = AppConfig.Mode.LINE;
						} else if ("FREEHAND".equals(action)) {
							AppConfig.mode = AppConfig.Mode.FREEHAND;
						} else if ("RECT".equals(action)) {
							AppConfig.mode = AppConfig.Mode.RECTANGLE;
						} else if ("RRECT".equals(action)) {
							AppConfig.mode = AppConfig.Mode.ROUNDRECT;
						} else if ("OVAL".equals(action)) {
							AppConfig.mode = AppConfig.Mode.OVAL;
						} else if ("POLYGON".equals(action)) {
							AppConfig.mode = AppConfig.Mode.POLYGON;
						}
					}
				}
			}); // addActionListener
		} // foreach button
		BUTTONS.get("IMAGE").addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (!BUTTONS.get("IMAGE").isSelected()) {return;}
				AppConfig.mode = AppConfig.Mode.IMAGE;
				ImageFileChooser imgChooser = new ImageFileChooser();
				getParent().setVisible(false);
				int returnVal = imgChooser.showOpenDialog((Component) e.getSource());
				if (returnVal == ImageFileChooser.APPROVE_OPTION) {
					AppConfig.preview = null;
					AppConfig.imagePath = imgChooser.getSelectedFileWithExtension();
				}
			}
		}); // addActionListener
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
