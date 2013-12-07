package app.controller.operation;

import app.model.*;
import app.model.drawing.*;
import app.controller.*;

/**
 * This class implements the operation for creating
 * new drawing and adding it to the sketch. 
 */
public class DeleteOperation implements Operation {
	private Sketch sketch;
	private Drawing drawing;
	private int index;
	public DeleteOperation(Drawing drawing) {
		this.sketch = (Sketch)App.getClientProperty("currentSketch");
		this.drawing = drawing;
		this.index = sketch.indexOf(drawing);
	}
	@Override public boolean perform() {
		sketch.remove(index);
		return true;
	}
	@Override public boolean revert() {
		sketch.add(index, drawing);
		return true;
	}
} // CreateOperation
