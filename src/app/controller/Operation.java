package app.controller;

/**
 * This interface defines an operation and
 * the associated methods to perform and undo the operation.
 */
public interface Operation {

	/**
	 * Performs the operation.
	 * Returns a boolean indicating success or failure.
	 *
	 * @return true if succeed or failed.
	 */
	public boolean perform();

	/**
	 * Undoes the operation.
	 * Returns a boolean indicating success or failure.
	 *
	 * @return true if succeed or failed.
	 */
	public boolean revert();

} // Operation
