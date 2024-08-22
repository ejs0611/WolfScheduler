/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Conflict Exception.
 * 
 * @author smithej
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Conflict Exception without String
	 * 
	 */
	public ConflictException(){
		super("Schedule conflict.");
	}
	
	/**
	 * Conflict Exception with String
	 * 
	 * @param x String for Conflict Exception
	 */
	public ConflictException(String x) {
		super(x);
	}
}
