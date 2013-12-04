package app.component;

import app.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * This class constructs the main toolbar
 * for the application window.
 */
public class MainToolbar extends JToolBar {

    private Map<String, AbstractButton> buttons = new HashMap<String, AbstractButton>();

	private void configButtons() {
		buttons.put("NEW"      , UIToolbox.createButton(UIToolbox.BUTTON      , "NEW"      , "NEW"    ));
        buttons.put("OPEN"     , UIToolbox.createButton(UIToolbox.BUTTON      , "OPEN"     , "OPEN"   ));
        buttons.put("SAVE"     , UIToolbox.createButton(UIToolbox.BUTTON      , "SAVE"     , "SAVE"   ));
        buttons.put("PRINT"    , UIToolbox.createButton(UIToolbox.BUTTON      , "PRINT"    , "PRINT"  ));
        buttons.put("UNDO"     , UIToolbox.createButton(UIToolbox.BUTTON      , "UNDO"     , "UNDO"   ));
        buttons.put("REDO"     , UIToolbox.createButton(UIToolbox.BUTTON      , "REDO"     , "REDO"   ));
        buttons.put("SELECT"   , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "SELECT"   , "SELECT" ));
        buttons.put("DRAW"     , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "DRAW"     , "PENCIL" ));
        buttons.put("STYLE"    , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "STYLE"    , "PAINT"  ));
        buttons.put("LAYERS"   , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LAYERS"   , "LAYERS" ));
        buttons.put("LIBRARY"  , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LIBRARY"  , "LIBRARY"));
        buttons.put("SETTINGS" , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "SETTINGS" , "GEAR"   ));
	}

	public MainToolbar() {
		setFloatable(false);
		setLayout(new BorderLayout());
		configButtons();
		JToolBar leftGroup = new JToolBar();
			leftGroup.add(buttons.get("NEW"));
			leftGroup.add(buttons.get("OPEN"));
			leftGroup.add(buttons.get("SAVE"));
			leftGroup.add(buttons.get("PRINT"));
			leftGroup.addSeparator();
            leftGroup.add(buttons.get("UNDO"));
            leftGroup.add(buttons.get("REDO"));
            leftGroup.addSeparator();
            ButtonGroup modeGroup = new ButtonGroup();
            	modeGroup.add((AbstractButton)leftGroup.add(buttons.get("SELECT")));
            	modeGroup.add((AbstractButton)leftGroup.add(buttons.get("DRAW")));
            	buttons.get("SELECT").setSelected(true);
            	buttons.get("DRAW").addItemListener(new ItemListener() {
            		private final String original = buttons.get("DRAW").getText();
            		@Override public void itemStateChanged(ItemEvent e) {
						if (!buttons.get("DRAW").isSelected()) {
							buttons.get("DRAW").setText(original);
						} // not selected
					}
				}); // addItemListener
			leftGroup.addSeparator();
			leftGroup.add(buttons.get("STYLE"));
			leftGroup.add(buttons.get("LAYERS"));
			leftGroup.addSeparator();
			leftGroup.setFloatable(false);
			leftGroup.setOpaque(false);
		add(leftGroup, BorderLayout.WEST);
		JToolBar rightGroup = new JToolBar();
        ButtonGroup rightButtonGroup = new ButtonGroup();
			rightGroup.addSeparator();
			rightButtonGroup.add((AbstractButton)rightGroup.add(buttons.get("LIBRARY")));
			rightButtonGroup.add((AbstractButton)rightGroup.add(buttons.get("SETTINGS")));
			rightGroup.setFloatable(false);
			rightGroup.setOpaque(false);
		add(rightGroup, BorderLayout.EAST);
        // popups
        JToolBar drawingModePane   = new DrawingSelectionPane(buttons.get("DRAW"));
        JPanel   stylingPane       = new StylingPane(buttons.get("STYLE"));
        JToolBar layeringPane      = new LayeringOpsPane(buttons.get("LAYERS"));
        JPanel   settingPane       = new SettingsPanel();
        // attach popups
        UIToolbox.attachPopupPane(buttons.get("DRAW")     , drawingModePane, modeGroup       , PopupPane.CENTER, false);
        UIToolbox.attachPopupPane(buttons.get("STYLE")    , stylingPane    , null            , PopupPane.CENTER, true );
        UIToolbox.attachPopupPane(buttons.get("LAYERS")   , layeringPane   , null            , PopupPane.CENTER, true );
        UIToolbox.attachPopupPane(buttons.get("SETTINGS") , settingPane    , rightButtonGroup, PopupPane.RIGHT , true );
	}

} // MainToolbar
