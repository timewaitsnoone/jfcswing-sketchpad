package app.model;

import java.awt.*;
import java.awt.geom.*;

/**
 * This class provide a wrapper over a Shape
 * object. Associates a paint fill and stroke for the
 * shape. Defines draw and fill methods that paint the shape to
 * the graphics context, a shape outline and filled respectively.
 * Forwards the Shape methods.
 * 
 * Provides transforms on the shape. The transforms are:
 * 	 - scale
 *   - shear
 *   - translate
 *   - rotate
 *   - flip (horizontal, vertical)
 */
public class BasicShape2D implements Shape2D {

	private Shape  shape;
	private Paint  fillPaint;
	private Stroke stroke;
	private Paint  strokePaint;

	public BasicShape2D(Shape shape, Paint fillPaint, Paint strokePaint, Stroke stroke) {
		this.setShape(shape);
		this.setFill(fillPaint);
		this.setStroke(strokePaint, stroke);
	}

	public Shape  getShape()       {return shape;}
	public Paint  getFillPaint()   {return fillPaint;}
	public Paint  getStrokePaint() {return strokePaint;}
	public Stroke getStroke() 	   {return stroke;}

	public void setShape(Shape shape) {
		this.shape = shape;}
	public void setFill(Paint fillPaint) {
		this.fillPaint = fillPaint;}
	public void setStroke(Paint strokePaint, Stroke stroke) {
		this.strokePaint = strokePaint;
		this.stroke = stroke;}

	@Override public void draw(Graphics2D g, GraphicsStack gs) {
		if (fillPaint != null) {
			gs.push(); {
				g.setPaint(fillPaint);
				g.fill(shape);
			} gs.pop();
		}
		if (stroke != null && strokePaint != null) {
			gs.push(); {
				g.setPaint(strokePaint);
				g.setStroke(stroke);
				g.draw(shape);
			} gs.pop();
		}
	} // draw

	// Transforms
	
	@Override public void scale(double sx, double sy) {
		AffineTransform at = new AffineTransform();
	    at.scale(sx, sy);
	    setShape(at.createTransformedShape(shape));}
	@Override public void shear(double shx, double shy) {
		AffineTransform at = new AffineTransform();
	    at.shear(shx, shy);
	    setShape(at.createTransformedShape(shape));}
	@Override public void translate(double tx, double ty) {
		AffineTransform at = new AffineTransform();
	    at.translate(tx, ty);
	    setShape(at.createTransformedShape(shape));}
	@Override public void rotate(double theta) {
		AffineTransform at = new AffineTransform();
	    at.rotate(theta);
	    setShape(at.createTransformedShape(shape));}
	@Override public void flip(boolean orient) {
		AffineTransform at = new AffineTransform();
		if (orient) {
			at.scale(-1.0, 1.0);
			at.translate(shape.getBounds().getWidth(), 0.0);
		} else {
			at.scale(1.0, -1.0);
		    at.translate(0.0, shape.getBounds().getHeight());
		}
	    setShape(at.createTransformedShape(shape));
	} // flip

	// Shape methods -- Wrappers

	@Override public Rectangle getBounds() {
		return shape.getBounds();}
	@Override public Rectangle2D getBounds2D() {
		return shape.getBounds2D();}
	@Override public boolean contains(double x, double y, double w, double h) {
		return shape.contains(x, y, w, h);}
	@Override public boolean contains(double x, double y) {
		return shape.contains(x, y);}
	@Override public boolean contains(Rectangle2D r) {
		return shape.contains(r);}
	@Override public boolean contains(Point2D p) {
		return shape.contains(p);}
	@Override public boolean intersects(double x, double y, double w, double h) {
		return shape.intersects(x, y, w, h);}
	@Override public boolean intersects(Rectangle2D r) {
		return shape.intersects(r);}
	@Override public PathIterator getPathIterator(AffineTransform at) {
		return shape.getPathIterator(at);}
	@Override public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return shape.getPathIterator(at, flatness);}

} // BasicShape2D
