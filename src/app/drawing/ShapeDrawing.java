package app.drawing;

import java.awt.*;
import java.awt.geom.*;

import org.w3c.dom.*;

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
 *   - reflect (horizontal, vertical)
 */
public class ShapeDrawing implements Drawing {

	protected Shape  shape;
	protected Paint  fillPaint;
	protected Stroke stroke;
	protected Paint  strokePaint;

	public ShapeDrawing(Shape shape) {
		this.setShape(shape);
	}

	public ShapeDrawing(Shape shape, Paint fillPaint, Paint strokePaint, Stroke stroke) {
		this.setShape(shape);
		this.setFill(fillPaint);
		this.setStroke(strokePaint, stroke);
	}

	public Shape  getShape()       {return shape;}
	public Paint  getFillPaint()   {return fillPaint;}
	public Paint  getStrokePaint() {return strokePaint;}
	public Stroke getStroke() 	   {return stroke;}

	/**
	 * Set the internal shape object to that given.
	 *
	 * @param shape 	the shape
	 * @return			this drawing object
	 */
	public ShapeDrawing setShape(Shape shape) {
		this.shape = shape;
		return this;
	}

	/**
	 * Set the fill paint to that given.
	 *
	 * @param fillPaint 	the fill paint.
	 * @return				this drawing object
	 */
	public ShapeDrawing setFill(Paint fillPaint) {
		this.fillPaint = fillPaint;
		return this;
	}

	/**
	 * Set the stroke paint and type to that given.
	 *
	 * @param strokePaint 	the stroke paint.
	 * @param stroke 		the stroke type.
	 * @return				this drawing object
	 */
	public ShapeDrawing setStroke(Paint strokePaint, Stroke stroke) {
		this.strokePaint = strokePaint;
		this.stroke = stroke;
		return this;
	}

	@Override public void draw(Graphics2D g) {
		Graphics2D g2d = (Graphics2D)g.create();
			if (fillPaint != null) {
				g2d.setPaint(fillPaint);
				g2d.fill(shape);
			}
			if (strokePaint != null) {
				g2d.setPaint(strokePaint);
			}
			if (stroke != null) {
				g2d.setStroke(stroke);
				g2d.draw(shape);
			}
		g2d.dispose();
	} // draw

	@Override public Node getXMLNode(Document doc) {
		Node node = doc.createElement("shape");
		//NamedNodeMap attrs = node.getAttributes();
		//Attr data = doc.createAttribute("data");
		//Attr fill = doc.createAttribute("fill");
		//Attr stroke = doc.createAttribute("stroke");
			//data.setValue();
			//fill.setValue();
			//stroke.setValue();
		//attrs.setNamedItem(data);
		//attrs.setNamedItem(fill);
		//attrs.setNamedItem(stroke);
		return node;
	}

	// Transformations

	@Override public AffineTransform transform(AffineTransform at) {
		setShape(at.createTransformedShape(shape)); return at;}
	@Override public AffineTransform transform(AffineTransform at, double anchorx, double anchory) {
		AffineTransform _at = new AffineTransform();
			_at.translate(anchorx, anchory);
			_at.concatenate(at);
			_at.translate(-anchorx, -anchory);
		return transform(_at);}
	@Override public AffineTransform transformOnCenter(AffineTransform at) {
		return transform(at, getBounds().getCenterX(), getBounds().getCenterY());}

	@Override public AffineTransform translate(double tx, double ty) {
		return transform(AffineTransform.getTranslateInstance(tx, ty));}

	@Override public AffineTransform rotate(double theta) {
		return transformOnCenter(AffineTransform.getRotateInstance(theta));}
	@Override public AffineTransform rotate(double theta, double anchorx, double anchory) {
		return transform(AffineTransform.getRotateInstance(theta, anchorx, anchory));}
	@Override public AffineTransform rotate(double vecx, double vecy) {
		return transformOnCenter(AffineTransform.getRotateInstance(vecx, vecy));}
	@Override public AffineTransform rotate(double vecx, double vecy, double anchorx, double anchory) {
		return transform(AffineTransform.getRotateInstance(vecx, vecy, anchorx, anchory));}

	@Override public AffineTransform quadrantRotate(int numquadrants) {
		return transformOnCenter(AffineTransform.getQuadrantRotateInstance(numquadrants));}
	@Override public AffineTransform quadrantRotate(int numquadrants, double anchorx, double anchory) {
		return transform(AffineTransform.getQuadrantRotateInstance(numquadrants, anchorx, anchory));}

	@Override public AffineTransform scale(double sx, double sy) {
		return scale(sx, sy, getBounds2D().getX(), getBounds2D().getY());}
	@Override public AffineTransform scaleOnCenter(double sx, double sy) {
		return transformOnCenter(AffineTransform.getScaleInstance(sx, sy));}
	@Override public AffineTransform scale(double sx, double sy, double anchorx, double anchory) {
		return transform(AffineTransform.getScaleInstance(sx, sy), anchorx, anchory);}

	@Override public AffineTransform shear(double shx, double shy) {
		return shear(shx, shy, getBounds2D().getX(), getBounds2D().getY());}
	@Override public AffineTransform shearOnCenter(double shx, double shy) {
		return transformOnCenter(AffineTransform.getShearInstance(shx, shy));}
	@Override public AffineTransform shear(double shx, double shy, double anchorx, double anchory) {
		return transform(AffineTransform.getShearInstance(shx, shy), anchorx, anchory);}

	@Override public AffineTransform reflectHorizontal(double x) {
		return transform(AffineTransform.getScaleInstance(-1, 1), x, 0.0);}
	@Override public AffineTransform reflectHorizontal() {
		return transformOnCenter(AffineTransform.getScaleInstance(-1, 1));}
	@Override public AffineTransform reflectVertical(double y) {
		return transform(AffineTransform.getScaleInstance(1, -1), 0.0, y);}
	@Override public AffineTransform reflectVertical() {
		return transformOnCenter(AffineTransform.getScaleInstance(1, -1));}

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

} // ShapeDrawing
