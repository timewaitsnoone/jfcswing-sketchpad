package app.components;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import app.util.*;

/**
 * This class implements a selection of
 * operations for manipulating the layering
 * of the drawings.
 */
public class LayeringOpsPane extends JToolBar {

	private final Map<String, AbstractButton> BUTTONS = new HashMap<String, AbstractButton>();
	private final ButtonGroup BGROUP;

	private void configButtons() {
        BUTTONS.put("BRING-FORW" , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "BRING-FORW" , "BRING-FORW" ));
        BUTTONS.put("BRING-FRONT", UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "BRING-FRONT", "BRING-FRONT"));
        BUTTONS.put("SEND-BACKW" , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "SEND-BACKW" , "SEND-BACKW" ));
		BUTTONS.put("SEND-BACK"  , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "SEND-BACK"  , "SEND-BACK"  ));
		BUTTONS.put("GROUP"      , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "GROUP"      , "GROUP"      ));
		BUTTONS.put("UNGROUP"    , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "UNGROUP"    , "UNGROUP"    ));
	}

	public LayeringOpsPane(final AbstractButton invoker) {
		setFloatable(false);
		setFont(getFont().deriveFont(12.f));
		setLayout(new GridLayout(1, BUTTONS.size()));
		configButtons();
		BGROUP = new ButtonGroup();
			BGROUP.add((AbstractButton)add(BUTTONS.get("BRING-FORW" )));
			BGROUP.add((AbstractButton)add(BUTTONS.get("BRING-FRONT")));
			BGROUP.add((AbstractButton)add(BUTTONS.get("SEND-BACKW" )));
			BGROUP.add((AbstractButton)add(BUTTONS.get("SEND-BACK"  )));
			BGROUP.add((AbstractButton)add(BUTTONS.get("GROUP"      )));
			BGROUP.add((AbstractButton)add(BUTTONS.get("UNGROUP"    )));
		setPreferredSize(new Dimension(220, 35));
		if (invoker != null) {
			invoker.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					if (invoker.isSelected()) {BGROUP.clearSelection();}
				}
			}); // addActionListener
		}
	}

} // LayeringOpsPane
