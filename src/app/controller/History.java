package app.controller;

import java.util.*;

/**
 * This class implements the history list for the
 * applications. Supports undo and redo operations.
 */
public class History {

	private final Stack<Operation> DONE = new Stack<Operation>();
	private final Stack<Operation> UNDONE = new Stack<Operation>();
	
	/**
	 * Perform a new operation.
	 * Add the operation to the history queue.
	 * Clears the undone stack.
	 * 
	 * @param op		the new operation.
	 * @param action	perform the operation if true.
	 */
	public void perform(Operation op, boolean action) {
		if (action) {
			op.perform();
		}
		UNDONE.clear();
		DONE.push(op);
	}

	/**
	 * Perform an undo operation.
	 * Moves the operation from the done stack to undone stack.
	 */
	public void undo() {
		if (!canUndo()) {return;}
		Operation op = DONE.pop();
		op.revert();
		UNDONE.push(op);
	}
	
	/**
	 * Perform an redo operation.
	 * Moves the operation from the undone stack to done stack.
	 */
	public void redo() {
		if (!canRedo()) {return;}
		Operation op = UNDONE.pop();
		op.revert();
		DONE.push(op);
	}

	// Queries
	
	/**
	 * Returns true if there exists an undo operation.
	 * Otherwise false.
	 * 
	 * @return 		true if there exists an undo
	 * 				operation; otherwise false.
	 */
	public boolean canUndo() {
		return !DONE.isEmpty();
	}

	/**
	 * Returns true if there exists an redo operation.
	 * Otherwise false.
	 * 
	 * @return 		true if there exists an redo
	 * 				operation; otherwise false.
	 */
	public boolean canRedo() {
		return !UNDONE.isEmpty();
	}

} // History
