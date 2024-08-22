/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/** 
 * Conflict Interface 
 * 
 * @author Eric Smith
 *
 */
public interface Conflict {
	/**
	 * checkConflict method
	 * 
	 * @param possibleConflictingActivity Activity that might be conflicting with this
	 * @throws ConflictException If there is a conflict this exception is thrown
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

	
}
