package app.controller;

import java.beans.*;
import java.util.*;

public class AppManager extends StateManager {

	private static final AppManager SELF = new AppManager(); // singleton
	
	public Map<String, DocumentManager> DOCS = new HashMap<String, DocumentManager>();

	private AppManager() {} // no public constructor

	public static void addDocument(String name, DocumentManager doc) {
		SELF.DOCS.put(name, doc);}
	public static DocumentManager getDocument(String name) {
		return SELF.DOCS.get(name);}
	public static void removeDocument(String name) {
		SELF.DOCS.remove(name);}
    public static boolean hasAppProperty(String propertyName) {
    	return SELF.hasProperty(propertyName);}
    public static Object getAppProperty(String propertyName) {
    	return SELF.getProperty(propertyName);}
    public static Object setAppProperty(String propertyName, Object newValue) {
    	return SELF.setProperty(propertyName, newValue);}
    public static void addAppPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    	SELF.addPropertyChangeListener(propertyName, listener);}
    public static void addAppPropertyChangeListener(PropertyChangeListener listener) {
    	SELF.addPropertyChangeListener(listener);}
    public static void removeAppPropertyChangeListener(PropertyChangeListener listener) {
    	SELF.removePropertyChangeListener(listener);}
    public static void removeAppPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    	SELF.removePropertyChangeListener(propertyName, listener);}
    public static void hasAppListeners(String propertyName) {
    	SELF.hasListeners(propertyName);}
    public static PropertyChangeListener[] getAppPropertyChangeListeners() {
        return SELF.getPropertyChangeListeners();}
    public static PropertyChangeListener[] getAppPropertyChangeListeners(String propertyName) {
        return SELF.getPropertyChangeListeners(propertyName);}	

} // AppManager
