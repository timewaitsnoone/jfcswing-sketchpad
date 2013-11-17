package app.model;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * This class provides a group collection of shapes.
 * It is a list of shapes. Provides shape and transform methods
 * for interacting with the group as a whole (as one shape)
 */
public class Shape2DGroup extends ArrayList<Shape2D> implements Shape2D {

	@Override public void draw(Graphics2D g, GraphicsStack gs) {
		for (Shape2D s : this) {s.draw(g, gs);}}

	// Transforms

	@Override public void scale(double sx, double sy) {} // TODO
	@Override public void shear(double shx, double shy) {} // TODO
	@Override public void translate(double tx, double ty) {} // TODO
	@Override public void rotate(double theta) {} // TODO
	@Override public void flip(boolean orient) {} // TODO	

	// Shape methods

	private Area getOutlineArea() {
		Area a = new Area();
		for (Shape s : this) {a.add(new Area(s));}
		return a;
	}

	@Override public Rectangle getBounds() {
		Rectangle r = new Rectangle();
		for (Shape s : this) {r.add(s.getBounds());}
		return r;}
	@Override public Rectangle2D getBounds2D() {
		return getBounds();}
	@Override public boolean contains(double x, double y) {
		for (Shape s : this) {if (s.contains(x, y)) {return true;}}
		return false;}
	@Override public boolean contains(Point2D p) {
		for (Shape s : this) {if (s.contains(p)) {return true;}}
		return false;}
	@Override public boolean contains(double x, double y, double w, double h) {
		for (Shape s : this) {if (s.contains(x, y, w, h)) {return true;}}
		return false;}
	@Override public boolean contains(Rectangle2D r) {
		for (Shape s : this) {if (s.contains(r)) {return true;}}
		return false;}
	@Override public boolean intersects(double x, double y, double w, double h) {
		for (Shape s : this) {if (s.intersects(x, y, w, h)) {return true;}}
		return false;}
	@Override public boolean intersects(Rectangle2D r) {
		for (Shape s : this) {if (s.intersects(r)) {return true;}}
		return false;}
	@Override public PathIterator getPathIterator(AffineTransform at) {
		return getOutlineArea().getPathIterator(at);}
	@Override public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return getOutlineArea().getPathIterator(at, flatness);}

} // Shape2DGroup
