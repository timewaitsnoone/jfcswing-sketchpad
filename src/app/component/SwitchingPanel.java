package app.component;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * SwitchingPanel is a custom UI component that provides collapsible panels
 * which gives hierarchy to information, and presents only the relevant
 * information to the users on a need to know basis. Enables grouping of
 * items in a long list, sections in a large documents or controls
 * in a settings panel.
 */
public class SwitchingPanel
        extends JPanel
        implements ItemListener {

	protected final Map<JRadioButton, Component> PANELS = new HashMap<JRadioButton, Component>();
	protected final GridBagConstraints GBC = new GridBagConstraints();
	protected final Set<Component> showingPanel = new HashSet<Component>();
	protected final ButtonGroup buttonGroup = new ButtonGroup();
    protected final JPanel buttonPanel;
	protected int panelWidth = 0;

    /**
     * Constructs a new Accordion Panel.
     * Links the tabs as grouped.
     */
    public SwitchingPanel() {
    	setLayout(new GridBagLayout());
	    	GBC.insets    = new Insets(5, 5, 5, 5);
	    	GBC.weightx   = 1.0;
	    	GBC.fill      = GridBagConstraints.HORIZONTAL;
	    	GBC.gridwidth = GridBagConstraints.REMAINDER;
	    buttonPanel = new JPanel(new GridBagLayout());
        super.addImpl(buttonPanel, GBC, 0);
    }

    /**
     * Hide and show the contents of the collapsible sections.
     *
     * @param button    the button associated with the toggled panel
     * @param show      flag to move visible (true) or hide (false).
     */
    private void togglePanelVisibility(JRadioButton button, boolean show) {
    	Component panel = PANELS.get(button);
        if (panel.isShowing() == show) {return;}
        if (show) {
            showingPanel.add(panel);
        } else {
            showingPanel.remove(panel);
        }
        panel.setVisible(show);
        button.getParent().validate();
        getParent().revalidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override protected void addImpl(Component panel, Object constraint, int idx) {
    	if (idx < 0) {
            idx = PANELS.size();
        }
        String label = constraint == null ? "Panel " + (idx + 1) : constraint.toString();
    	JRadioButton rb = new JRadioButton(label);
    	PANELS.put(rb, panel);
        buttonPanel.add(rb, GBC);
        buttonGroup.add(rb);
        rb.addItemListener(this);
    	super.addImpl(panel, GBC, idx + 1);
        // update width
        int width = panel.getPreferredSize().width;
    	if (width > panelWidth) {
    		panelWidth = width;
    		for (JRadioButton button : PANELS.keySet()) {
    			button.setPreferredSize(new Dimension(width,
    					button.getPreferredSize().height));
    		}
    	}
    	if (panel instanceof JPanel) {
    		((JPanel)panel).setBorder(new EtchedBorder());
    	}
        panel.setVisible(false); // init closed
    }

    /**
     * Returns the panel associated with the given button (key).
     *
     * @param key       the button to lookup
     * @return          the panel associated with the key,
     *                  else null if not found
     */
    public Component getComponent(Object key) {
		return PANELS.get(key);
    }

    /**
     * Returns the number of panels in the accordion.
     *
     * @return the number of panels in the accordion.
     */
    public int getPanelCount() {
		return PANELS.size();
    }

    /**
     * Returns a set of the currently visible panels.
     *
     * @return a set of the currently visible panels.
     */
    public Set<Component> getVisiblePanels() {
		return showingPanel;
    }

    /**
     * Attach the given EventListener to the buttons.
     *
     * @param listener      the EventListener to attach.
     */
    public void addButtonEvent(EventListener listener) {
    	if (listener == null) {
    		return;
    	}
    	for (JRadioButton button : PANELS.keySet()) {
    		if (listener instanceof ActionListener) {
    			button.addActionListener((ActionListener)listener);
    		} else if (listener instanceof MouseListener) {
    			button.addMouseListener((MouseListener)listener);
    		} else if (listener instanceof ItemListener) {
				button.addItemListener((ItemListener)listener);
    		}
    	} // foreach button
    }

    @Override public void revalidate() {
    	super.revalidate();
    	if (getParent() == null) {return;}
    	getParent().revalidate();
    }

    @Override public void itemStateChanged(ItemEvent e) {
        JRadioButton button = (JRadioButton)e.getSource();
        switch (e.getStateChange()) {
            case ItemEvent.SELECTED:
                togglePanelVisibility(button, true);
                break;
            case ItemEvent.DESELECTED:
                togglePanelVisibility(button, false);
                break;
        }
    }

} // SwitchingPanel
