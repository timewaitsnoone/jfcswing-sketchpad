package app.component;

import java.awt.*;
import javax.swing.colorchooser.*;

/**
 * Defines an interface for a paint/color picker.
 */
public interface PaintPicker {

    /**
     * Gets the current color value from the chooser.
     * By default, this delegates to the model.
     *
     * @return the current color value of the chooser
     */
    public Color getColor();

    /**
     * Gets the current paint object from the chooser.
     * By default, this delegates to the model.
     *
     * @return the current paint object of the chooser
     */
    public Paint getPaint();

    /**
     * Returns the data model that handles color selections.
     *
     * @return a <code>ColorSelectionModel</code> object
     */
	public ColorSelectionModel getSelectionModel();

    /**
     * Sets whether or not this component is enabled.
     * A component that is enabled may respond to user input,
     * while a component that is not enabled cannot respond to
     * user input.  Some components may alter their visual
     * representation when they are disabled in order to
     * provide feedback to the user that they cannot take input.
     * <p>Note: Disabling a component does not disable its children.
     *
     * <p>Note: Disabling a lightweight component does not prevent it from
     * receiving MouseEvents.
     *
     * @param enabled true if this component should be enabled, false otherwise
     * @see java.awt.Component#isEnabled
     * @see java.awt.Component#isLightweight
     *
     * @beaninfo
     *    preferred: true
     *        bound: true
     *    attribute: visualUpdate true
     *  description: The enabled state of the component.
     */
	public void setEnabled(boolean enabled);

    /**
     * Determines whether this component is enabled. An enabled component
     * can respond to user input and generate events. Components are
     * enabled initially by default. A component may be enabled or disabled by
     * calling its <code>setEnabled</code> method.
     * 
     * @return <code>true</code> if the component is enabled,
     *          <code>false</code> otherwise
     * @see #setEnabled
     */
	public boolean isEnabled();

} // PaintPicker
