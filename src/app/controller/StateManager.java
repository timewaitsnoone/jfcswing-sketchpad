package app.controller;

import java.beans.*;
import java.util.*;

/**
 * This class maintains the state of an entity.
 * Notifies the listeners of the changes in state.
 */
public class StateManager {

    private PropertyChangeSupport notifier = new PropertyChangeSupport(this);
    private Map<String, Object> properties = new HashMap<String, Object>(); 

    public StateManager() {}

    /**
     * Returns true if the given property is set,
     * false otherwise.
     * 
     * @param propertyName	the property ID to set
     * @return				true if the property is set,
     * 						false otherwise.
     */
    public boolean hasProperty(String propertyName) {
    	return properties.containsKey(propertyName);
    }

    /**
     * Returns the value of the given property.
     * 
     * @param propertyName	the property ID to set
     * @return				the current value of the property
     */
    public Object getProperty(String propertyName) {
    	return properties.get(propertyName);
    }

    /**
     * Sets the property in the state manager to the new value
     * and returns the previous value.
     * 
     * @param propertyName	the property ID to set
     * @param newValue		the new value of the property
     * @return				the old value of the property or null.
     */
    public Object setProperty(String propertyName, Object newValue) {
    	Object oldValue = properties.put(propertyName, newValue);
    	notifier.firePropertyChange(propertyName, oldValue, newValue);
    	return oldValue;
    }
    
    // Property listener methods

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    	notifier.addPropertyChangeListener(propertyName, listener);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    	notifier.addPropertyChangeListener(listener);}
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        notifier.removePropertyChangeListener(listener);}
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        notifier.removePropertyChangeListener(propertyName, listener);}
    public void hasListeners(String propertyName) {
        notifier.hasListeners(propertyName);}
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return notifier.getPropertyChangeListeners();}
    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return notifier.getPropertyChangeListeners(propertyName);}

} // AppStateManager
