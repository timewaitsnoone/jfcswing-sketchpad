package app.components;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import app.util.*;

/**
 * TabbedPane is a custom UI component that provides collapsible panels
 * laid out as tabbed pages. This gives hierarchy to information, and
 * presents only the relevant information to the users on a need to
 * know basis. Enables grouping of items into sections in a settings panel.
 *
 * Unlike the JTabbedPane, tabbed area resizes based on the size of opened
 * content. Behaves as a psuedo-dropdown panel. Tabs are styled icons.
 */
public class TabbedPane extends JPanel {

    protected final Map<TabButton, Component> PANELS = new HashMap<TabButton, Component>();
    protected final GridBagConstraints GBC = new GridBagConstraints();
    protected final JToolBar TOOLBAR = new JToolBar();
    protected final ButtonGroup BGROUP = new ButtonGroup();
    protected Component showingPanel = null;

	/**
	 * Constructs a new TabbedPane
	 */
	public TabbedPane() {
        setLayout(new GridBagLayout());
            GBC.insets    = new Insets(1, 3, 0, 3);
            GBC.weightx   = 1.0;
            GBC.fill      = GridBagConstraints.HORIZONTAL;
            GBC.gridwidth = GridBagConstraints.REMAINDER;
        TOOLBAR.setFloatable(false);
        TOOLBAR.setBorder(new EtchedBorder());
        super.add(TOOLBAR, GBC);
	}

    /**
     * Hide and show the contents of the collapsible sections.
     *
     * @param button    the button associated with the toggled panel
     * @param show      flag to move visible (true) or hide (false).
     */
    private void togglePanelVisibility(TabButton button, boolean show) {
        Component panel = PANELS.get(button);
        if (panel.isShowing() == show) {
            return;
        }
        if (show) {
            showingPanel = panel;
        }
        panel.setVisible(show);
        button.getParent().validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override protected void addImpl(Component comp, Object constraint, int idx) {
    	if (getComponentCount() == 0) {
        	super.addImpl(comp, constraint, 0);
        	return;
        }
    	if (constraint instanceof TabButton) {
    		if (idx < 0) {
                idx = PANELS.size();
            }
            TabButton tab = (TabButton)constraint;
            PANELS.put(tab, comp);
            TOOLBAR.setLayout(new GridLayout(1, PANELS.size()));
            TOOLBAR.add(tab, idx);
            BGROUP.add(tab);
            super.addImpl(comp, GBC, idx + 1);
            int width = getPreferredSize().width;
        	if (comp.getPreferredSize().width > getPreferredSize().width) {
        		width = comp.getPreferredSize().width;
        	}
    		setPreferredSize(new Dimension(width, getPreferredSize().height));
            comp.setVisible(false);
        }
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
     * Returns the currently visible panel.
     *
     * @return the currently visible panel.
     */
    public Component getVisiblePanel() {
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
        for (TabButton tb : PANELS.keySet()) {
            if (listener instanceof ActionListener) {
                tb.addActionListener((ActionListener)listener);
            } else if (listener instanceof MouseListener) {
                tb.addMouseListener((MouseListener)listener);
            } else if (listener instanceof ItemListener) {
                tb.addItemListener((ItemListener)listener);
            }
        } // foreach button
    }

    /**
     * HTML template for the content to be
     * display on the TabButton.
     */
    private static final String HTML =
            FileIO.getHTML("/assets/htdocs/tabButton.html");

    /**
     * Class definition of a tab button.
     * Button enables to expanding and collapsing of panels in the
     * Tabbed Panel.
     */
    public class TabButton extends JToggleButton implements ItemListener {

        private String label;
        private String icon;
        private Color colorPreview;

        /**
         * Construct a new tab button given label and icon.
         *
         * @param id				the button name and identifier
         * @param label             to display on the button.
         * @param icon              to display on the button.
         * @param colorPreview      the color to display as a preview
         */
        public TabButton(String id, String label, String icon, Color colorPreview) {
        	this.label = label;
            this.icon = icon;
            this.colorPreview = colorPreview;
            updateText();
            setActionCommand(id);
            setName(id);
            setMargin(new Insets(5, 0, 5, 0));
            addItemListener(this);
            setRequestFocusEnabled(true);
        }

        /**
         * Construct a new tab button given label and icon.
         * Color preview disabled.
         *
         * @param id				the button name and identifier
         * @param label             to display on the button.
         * @param icon              to display on the button.
         */
        public TabButton(String id, String label, String icon) {
            this(id, label, icon, null);
        }

        /**
         * Construct a new tab button given label and icon.
         * Color preview disabled.
         *
         * @param label             to display on the button.
         * @param icon              to display on the button.
         */
        public TabButton(String label, String icon) {
            this(label, label, icon, null);
        }

        /**
         * Set the label on the button.
         *
         * @param label      to display on the button
         */
        @Override public void setLabel(String label) {
            this.label = label;
            updateText();
        }

        /**
         * Set the icon on the button.
         *
         * @param icon      to display on the button
         */
        public void setIcon(String icon) {
            this.icon = icon;
            updateText();
        }

        /**
         * Set the color preview.
         *
         * @param colorPreview      the color to display as a preview
         */
        public void setColorPreview(Color colorPreview) {
            this.colorPreview = colorPreview;
            updateText();
        }

        /**
         * Refreshes the text displayed on the button.
         */
        private void updateText() {
            boolean showPreview = isEnabled() && colorPreview != null;
            String color = colorToHex(showPreview ? colorPreview : Color.WHITE);
            String iconGlyph = MyFont.ICONS.get(icon);
            setText(HTML
                .replace("{ICON}", iconGlyph != null ? iconGlyph : " ")
                .replace("{LABEL}", label)
                .replace("{SWITCH}", showPreview ? "" : "No")
                .replace("{HEXCOLOR}", color));
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

        /**
         * {@inheritDoc}
         */
        @Override public void itemStateChanged(ItemEvent e) {
            TabButton button = (TabButton)e.getSource();
            switch (e.getStateChange()) {
                case ItemEvent.SELECTED:
                    togglePanelVisibility(button, true);
                    break;
                case ItemEvent.DESELECTED:
                    togglePanelVisibility(button, false);
                    break;
            }
        }

    } // TabButton

} // TabbedPane
