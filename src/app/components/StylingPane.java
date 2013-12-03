package app.components;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import app.util.*;

/**
 * This class implements a panel that displays
 * options to style and transform the drawings.
 */
public class StylingPane extends TabbedPane implements ActionListener {

	private final AbstractButton invoker;
	private final Map<String, TabButton> BUTTONS = new HashMap<String, TabButton>();

	private final ColorPicker2 fillPanel;
	private final AccordionPanel outlinePanel;
	private final ColorPicker2 outlineColorPanel;
	
	/**
	 * Construct a new Styling pane.
	 * 
	 * @param invoker 		the button invoking this panel
	 */
	public StylingPane(AbstractButton invoker) {
		this.invoker = invoker;
		configButtons();
		add(fillPanel = new ColorPicker2(Color.RED, "Fill"), BUTTONS.get("FILL"));
			BUTTONS.get("FILL").setColorPreview(fillPanel.getColor());
		add(outlinePanel = new AccordionPanel(true), BUTTONS.get("OUTLINE"));
			outlineColorPanel = new ColorPicker2(Color.WHITE, "Outline Color");
			BUTTONS.get("OUTLINE").setColorPreview(outlineColorPanel.getColor());
			outlinePanel.add(outlineColorPanel, "Outline Color");
			outlinePanel.add(new JPanel(), "Outline Style");
		add(new JPanel(), BUTTONS.get("TEXT")); 
		add(new JPanel(), BUTTONS.get("TRANSFORM"));
		configListeners();
	}

	/**
	 * Initialize the buttons.
	 */
	private void configButtons() {
		BUTTONS.put("FILL"     , new TabButton("FILL"     , "Fill"     , "BUCKET"   ));
        BUTTONS.put("OUTLINE"  , new TabButton("OUTLINE"  , "Outline"  , "PEN"      ));
        BUTTONS.put("TEXT"     , new TabButton("TEXT"     , "Font"     , "TEXT"     ));
        BUTTONS.put("TRANSFORM", new TabButton("TRANSFORM", "Transform", "TRANSFORM"));
	}                                                                   

	/**
	 * Initialize the event listeners.
	 */
	private void configListeners() {
		if (invoker != null) {
			invoker.addActionListener(this);
		}
		addButtonEvent(this);
		// listeners on color panels
		fillPanel.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override public void stateChanged(ChangeEvent e) {
				BUTTONS.get("FILL").setColorPreview(fillPanel.getColor());
			}}); // fill addChangeListener
		outlineColorPanel.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override public void stateChanged(ChangeEvent e) {
				BUTTONS.get("OUTLINE").setColorPreview(outlineColorPanel.getColor());
			}}); // outline color addChangeListener
	}

	@Override public void actionPerformed(ActionEvent e) {
		Component visible = getVisiblePanel();
		if (invoker != null && e.getSource() == invoker) { // reset panel on open
			BGROUP.clearSelection();
			if (visible != null) {
				visible.setVisible(false);
				super.showingPanel = null;
			}
		}
		revalidate();
	}

	@Override public void revalidate() {
		JPopupMenu popup = UIToolbox.getAncestorPopup(this);
		Component visible = getVisiblePanel();
		int width = getPreferredSize().width;
		int height = 0;
		if (TOOLBAR != null) {
			height += TOOLBAR.getPreferredSize().height;
		}
		if (visible != null) {
			height += visible.getPreferredSize().height;
		}
		if (popup != null) {
			popup.setPreferredSize(new Dimension(width, height));
			popup.revalidate();
		}
		super.revalidate();
	}

} // StylingPane
