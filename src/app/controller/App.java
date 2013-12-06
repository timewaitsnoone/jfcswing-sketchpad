package app.controller;

import java.beans.*;
import java.util.*;

/**
 * This class implements the application controller.
 * It manages all of the application state information.
 * It provides application level calls that affect the entire app.
 */
public class App {

    private App() {} // singleton

    // System calls

    /**
     * Makes a request to close the application.
     * The application will attempt to close, if the document
     * need to be saved, it will prompt the user to save it.
     * If the user requests to save. The application will make
     * a save request. If the user requests not to save, the
     * application just exits. If the user aborts, application
     * remains open.
     */
    public static void requestClose() {

    }

    /**
     * Makes a request to save the currently opened document.
     * If the file to save it to, does not exist or has not
     * been assigned, prompt user for a suitable file to save
     * it to. Prompt user, if about to overwrite a file.
     */
    public static void requestSave() {

    }

    /**
     * Makes a request to open a new document.
     * Will attempt to save the current document first.
     * Prompts user to enter the initial size and background
     * color of the newly created document.
     */
    public static void requestNew() {

    }

    /**
     * Makes a request to open a document from a file.
     * Prompts user for a file to open.
     * Will attempt to save the current document first.
     */
    public static void requestOpen() {

    }

    // State

    /**
     * Map of all the properties and application data.
     *
     * propertyName     type        description
     * ================ =========== ============================
     * currentSketch    Sketch      the current document
     * zoomLevel        double      the level of zoom in the viewpoint
     * mode             String      the current application mode
     *
     *      Selection.pending       in selection mode; no item selected
     *      Selection.general       select, move, resize, delete, and flip drawings
     *      Selection.rotate        rotate drawings
     *
     *      Drawing.Freehand        draw freehand
     *      Drawing.Polygon         draw polygon
     *      Drawing.BoundedShape    draw any bounded shape
     *      Drawing.Image           draw image
     *      Drawing.Text            draw text
     *
     * currentDrawing   Drawing     the currently selected drawing
     * boundedShape     String      the currently selected bounded shape
     *
     *      Rectangle
     *      RoundedRectangle
     *      Ellipse
     *      Line
     *      Triangle
     *      RightTriangle
     *
     * fillColor        Color       the current fill Color
     * lineColor        Color       the current line Color
     * lineStroke       Stroke      the current line/stroke object
     */
    private static final Map<String, Object> properties =
            new HashMap<String, Object>();
    private static final PropertyChangeSupport notif =
            new PropertyChangeSupport(properties);

    /**
     * Returns true if the property given is set,
     * otherwise false.
     *
     * @param propertyName  the property field to check.
     * @return              true if property is set, otherwise false.
     */
    public static boolean hasClientProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    /**
     * Sets the given property to the given value.
     *
     * @param propertyName  the property field to set.
     * @param newValue      the value to set the property to.
     * @return              the old value of the property field.
     */
    public static Object putClientProperty(String propertyName, Object newValue) {
        Object oldValue = properties.put(propertyName, newValue);
        notif.firePropertyChange(propertyName, oldValue, newValue);
        return oldValue;
    }

    /**
     * Returns the value of the given property field.
     *
     * @param propertyName  the property field to retrieve.
     * @return              the value of the property field.
     */
    public static Object getClientProperty(String propertyName) {
        return properties.get(propertyName);
    }

    /**
     * Unsets the value of the given property field.
     *
     * @param propertyName  the property field to unset.
     * @return              the old value of the property field.
     */
    public static Object removeClientProperty(String propertyName) {
        Object oldValue = properties.remove(propertyName);
        notif.firePropertyChange(propertyName, oldValue, null);
        return oldValue;
    }

    // State listeners

    /**
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     * The same listener object may be added more than once, and will be called
     * as many times as it is added.
     * If <code>listener</code> is null, no exception is thrown and no action
     * is taken.
     *
     * @param listener  The PropertyChangeListener to be added
     */
    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        notif.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered
     * for all properties.
     * If <code>listener</code> was added more than once to the same event
     * source, it will be notified one less time after being removed.
     * If <code>listener</code> is null, or was never added, no exception is
     * thrown and no action is taken.
     *
     * @param listener  The PropertyChangeListener to be removed
     */
    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        notif.removePropertyChangeListener(listener);
    }

    /**
     * Returns an array of all the listeners that were added to the
     * PropertyChangeSupport object with addPropertyChangeListener().
     * <p>
     * If some listeners have been added with a named property, then
     * the returned array will be a mixture of PropertyChangeListeners
     * and <code>PropertyChangeListenerProxy</code>s. If the calling
     * method is interested in distinguishing the listeners then it must
     * test each element to see if it's a
     * <code>PropertyChangeListenerProxy</code>, perform the cast, and examine
     * the parameter.
     *
     * <pre>
     * PropertyChangeListener[] listeners = bean.getPropertyChangeListeners();
     * for (int i = 0; i < listeners.length; i++) {
     *   if (listeners[i] instanceof PropertyChangeListenerProxy) {
     *     PropertyChangeListenerProxy proxy =
     *                    (PropertyChangeListenerProxy)listeners[i];
     *     if (proxy.getPropertyName().equals("foo")) {
     *       // proxy is a PropertyChangeListener which was associated
     *       // with the property named "foo"
     *     }
     *   }
     * }
     *</pre>
     *
     * @see PropertyChangeListenerProxy
     * @return all of the <code>PropertyChangeListeners</code> added or an
     *         empty array if no listeners have been added
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return notif.getPropertyChangeListeners();
    }

    /**
     * Add a PropertyChangeListener for a specific property.  The listener
     * will be invoked only when a call on firePropertyChange names that
     * specific property.
     * The same listener object may be added more than once.  For each
     * property,  the listener will be invoked the number of times it was added
     * for that property.
     * If <code>propertyName</code> or <code>listener</code> is null, no
     * exception is thrown and no action is taken.
     *
     * @param propertyName  The name of the property to listen on.
     * @param listener  The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(
                String propertyName,
                PropertyChangeListener listener) {
        notif.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove a PropertyChangeListener for a specific property.
     * If <code>listener</code> was added more than once to the same event
     * source for the specified property, it will be notified one less time
     * after being removed.
     * If <code>propertyName</code> is null,  no exception is thrown and no
     * action is taken.
     * If <code>listener</code> is null, or was never added for the specified
     * property, no exception is thrown and no action is taken.
     *
     * @param propertyName  The name of the property that was listened on.
     * @param listener  The PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(
                String propertyName,
                PropertyChangeListener listener) {
        notif.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Returns an array of all the listeners which have been associated
     * with the named property.
     *
     * @param propertyName  The name of the property being listened to
     * @return all of the <code>PropertyChangeListeners</code> associated with
     *         the named property.  If no such listeners have been added,
     *         or if <code>propertyName</code> is null, an empty array is
     *         returned.
     */
    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return notif.getPropertyChangeListeners(propertyName);
    }

    /**
     * Check if there are any listeners for a specific property, including
     * those registered on all properties.  If <code>propertyName</code>
     * is null, only check for listeners registered on all properties.
     *
     * @param propertyName  the property name.
     * @return true if there are one or more listeners for the given property
     */
    public boolean hasListeners(String propertyName) {
        return notif.hasListeners(propertyName);
    }

} // App
