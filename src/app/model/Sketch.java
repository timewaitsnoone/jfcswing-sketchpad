package app.model;

import java.awt.*;
import java.io.*;
import java.util.*;
import app.drawing.*;

/**
 * This class implements the document object model.
 * Facilitates the in-memory representation of the sketch/document.
 */
public class Sketch extends ArrayList<Drawing> {

    private Dimension size;
    private Color background;
    private File file = null;

    /**
     * Creates an empty sketch of the given size with
     * given background color.
     *
     * @param size          of the sketch
     * @param background    color of the sketch
     */
    public Sketch(Dimension size, Color background) {
        setSize(size);
        setBackground(background);
    }

    /**
     * Creates a sketch given an input file.
     *
     * @param file      input file to create sketch.
     */
    public Sketch(File file) {
        // TODO
    }

    // Getters

    public Color getBackground() {
        return background;}
    public File getFile() {
        return file;}
    public Dimension getSize() {
        return size;}

    // Setters

    public void setBackground(Color background) {
        this.background = background;}
    public void setFile(File file) {
        this.file = file;}
    public void setSize(Dimension size) {
        this.size = size;}

    /**
     * Resize the sketch and all the drawing
     * with in it.
     *
     * @param size      to scale the sketch to.
     */
    public void resize(Dimension size) {
        double sx = size.getWidth()  / this.size.getWidth();
        double sy = size.getHeight() / this.size.getHeight();
        this.setSize(size);
        for (Drawing drawing : this) {
            drawing.scale(sx, sy, 0.0, 0.0);
        }
    }

    /**
     * Serializes the sketch to a XML file.
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
     * Serializes the sketch to a XML file.
     * Returns true if successful, false otherwise.
     *
     * @param file      output file to serialize sketch.
     * @return 			true if successful, false otherwise.
     */
    public boolean saveAs(File file) {
        setFile(file);
        return save();
    }

    /**
     * Serializes the sketch as an image file.
     * Returns true if successful, false otherwise.
     *
     * @param format    the image format to generate.
     * @return          true if successful, false otherwise.
     */
    public boolean saveImage(int format) {
        // TODO generate image
        // TODO Serialize
        return false;
    }

} // Sketch
