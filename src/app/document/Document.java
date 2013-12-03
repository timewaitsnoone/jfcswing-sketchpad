package app.document;

import java.awt.*;
import java.io.*;
import java.util.*;
import app.drawing.*;

/**
 * This class implements the document object model.
 * Facilitates the in-memory representation of the document.
 */
public class Document extends ArrayList<Drawing> {

	private Dimension size;
	private Color background;
	private File file = null;
	
	/**
	 * Creates an empty document of the given size with
	 * given background color.
	 * 
	 * @param size			of the document
	 * @param background	color of the document
	 */
	public Document(Dimension size, Color background) {
		this.setSize(size);
		this.setBackground(background);
	}

	/**
	 * Creates a document given an XML file.
	 * 
	 * @param file 	the XML file to create the document with.
	 */
	public Document(File file) {
		this.setFile(file);
		// TODO Deserialize
		// TODO Build the document
	}

	// Getters
	
	public Dimension getSize() {
		return size;}
	public Color getBackground() {
		return background;}
	public File getFile() {
		return file;}

	// Setters
	
	public void setSize(Dimension size) {
		this.size = size;}
	public void setBackground(Color background) {
		this.background = background;}
	public void setFile(File file) {
		this.file = file;}

	/**
	 * Serializes the document to a XML file.
	 * Returns true if successful, false otherwise.
	 * 
	 * @return true if successful, false otherwise.
	 */
	public boolean save() {
		// TODO build XML
		// TODO Serialize
		return false;
	}

	/**
	 * Serializes the document as an image file.
	 * Returns true if successful, false otherwise.
	 *
	 * @param format 	the document format to generate.
	 * @return 			true if successful, false otherwise.
	 */
	public boolean saveImage(int format) {
		// TODO build XML
		// TODO Serialize
		return false;
	}

} // Document
