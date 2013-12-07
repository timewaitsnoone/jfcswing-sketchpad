package app.controller.operation;

import app.model.*;
import app.model.drawing.*;
import app.controller.*;

/**
 * This class implements the operation for creating
 * new drawing and adding it to the sketch. 
 */
public class CreateOperation implements Operation {
	private Drawing drawing;
	public CreateOperation(Drawing drawing) {
		this.drawing = drawing;
	}
	@Override public boolean perform() {
		Sketch sketch = (Sketch)App.getClientProperty("currentSketch");
		return sketch.add(drawing);
	}
	@Override public boolean revert() {
		Sketch sketch = (Sketch)App.getClientProperty("currentSketch");
		return sketch.remove(drawing);
	}
} // CreateOperation
