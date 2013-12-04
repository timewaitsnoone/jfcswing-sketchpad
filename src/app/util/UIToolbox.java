package app.util;

import java.awt.*;

import javax.swing.*;

import app.component.*;

/**
 * This static class contains a collection of commonly
 * used methods for constructing, displaying, manipulating
 * user interface components.
 */
public class UIToolbox {

    private UIToolbox() { } // no constructor

    /**
     * Computes the constrast and returns the foreground
     * color appropriate for the given background color.
     *
     * @param bg    the background color
     * @return      the constrasting foreground color
     */
    public static Color getContrastYIQ(Color bg) {
    	if (bg == null) {
    		return null;
    	}
        int r = bg.getRed();
        int g = bg.getGreen();
        int b = bg.getBlue();
        int yiq = ((r*299)+(g*587)+(b*114))/1000;
        if (bg.getAlpha() < 64) {
            return Color.WHITE;
        }
        return (yiq >= 128) ? Color.BLACK : Color.WHITE;
    }

    // ----- Create Icon Buttons -----

    public static final int BUTTON = 0;
    public static final int TOGGLEBUTTON = 1;
    public static final int MENUBUTTON = 2;

    /**
     * Helper method for creating buttons.
     *
     * @param   type        the type of the button.
     * @param   id          the button reference ID.
     * @param   icon        the name of the icon to place on the button
     * @param 	size		the font point size
     * @param	padding		the amount of padding around the button
     * @return              the button created.
     */
    public static AbstractButton createButton(int type, String id, String icon, float size, int padding) {
        AbstractButton ab = null;
        switch (type) {
            case BUTTON:       ab = new JButton();       break;
            case TOGGLEBUTTON: ab = new JToggleButton(); break;
            case MENUBUTTON:   ab = new JMenu();         break;
            default:           return null;
        }
        ab.setFont(MyFont.ICON_FONT.deriveFont(size));
        ab.setText(MyFont.ICONS.get(icon));
        ab.setMargin(new Insets(padding, padding, padding, padding));
        ab.setActionCommand(id);
        ab.setName(id);
        return ab;
    }
    public static AbstractButton createButton(int type, String id, String icon) {
        return createButton(type, id, icon, 16.f, 5);
    }

    // ---- Popup Pane -----

    /**
     * Attaches a popup pane to a button.
     * Returns the created popup.
     *
     * @param invoker	           the button that invokes the popup panel
     * @param popupPanel	       the popup panel to attach
     * @param bgroup			   the button group for which button is contained (if applicable)
     * @param orientation          the orientation of the placement of the popup.
     * @param deselectOnClose      flag for deselecting the button on close of popup
     * @return                     the popup created
     */
    public static JPopupMenu attachPopupPane(
            AbstractButton invoker,
            Component popupPanel,
            ButtonGroup bgroup,
            int orientation,
            boolean deselectOnClose) {
    	PopupPane popup = new PopupPane(popupPanel);
    	popup.configInvoker(invoker, bgroup, deselectOnClose, orientation);
        return popup;
    }

    /**
     * Returns the popup menu that is an ancestor of the
     * given component. If the component is not a child of a popup,
     * return null.
     * 
     * @param comp		the component to get the popup of
     * @return			the popup menu or null, if none found.
     */
    public static JPopupMenu getAncestorPopup(Component comp) {
    	while (comp != null) {
			if (comp instanceof JPopupMenu) {
				return (JPopupMenu)comp;
			}
			comp = comp.getParent();
		}
    	return null;
    }

} // UIToolbox
