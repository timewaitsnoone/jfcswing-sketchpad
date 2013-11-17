package app.model;

import java.awt.*;

/**
 * This interface defines the shape method headers.
 * Additions to shape interface are the drawing method
 * and transforms.
 */
public interface Shape2D extends Shape {
	public void draw(Graphics2D g, GraphicsStack gs);
	public void scale(double sx, double sy);
	public void shear(double shx, double shy);
	public void translate(double tx, double ty);
	public void rotate(double theta);
	public void flip(boolean orient);
} // Shape2D
