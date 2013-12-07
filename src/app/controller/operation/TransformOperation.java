package app.controller.operation;

import java.awt.geom.*;

import app.controller.*;
import app.model.drawing.*;

/**
 * This class implements the operation for
 * transforming drawings. 
 */
public class TransformOperation implements Operation {
	private Drawing drawing;
	private AffineTransform transform;
	private AffineTransform inverse = null;
	public TransformOperation(Drawing drawing, AffineTransform at) {
		this.drawing = drawing;
		this.transform = at;
		try {
			this.inverse = at.createInverse();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
	}
	@Override public boolean perform() {
		drawing.transform(transform);
		return true;
	}
	@Override public boolean revert() {
		if (inverse == null) {
			return false;
		}
		drawing.transform(inverse);
		return true;
	}
} // CreateOperation
