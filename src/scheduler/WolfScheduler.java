package edu.ncsu.csc216.wolf_scheduler.scheduler;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

import java.util.ArrayList;

/**
 * WolfScheduler class
 * 
 * @author smithej
 *
 */
public class WolfScheduler {
	
	/** title */
	private String title;
	
	/** catalog arraylist */
	private ArrayList<Course> catalog;
	
	/** schedule arraylist */
	private ArrayList<Activity> schedule;

	/**
	 * WolfScheduler Constructor
	 * 
	 * @param filename filename
	 */
	public WolfScheduler(String filename) {
		this.schedule = new ArrayList<Activity>();
		this.title = "My Schedule";
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
		
	}

	/**
	 * getter of string array of course catalog
	 * 
	 * @return CourseCatalog
	 */
	public String[][] getCourseCatalog() {
		// TODO Auto-generated method stub
		String[][] cat = new String[catalog.size()][4];
		for(int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			cat[i] = c.getShortDisplayArray();
		}
		return cat;
	}

	/**
	 * getter of string array of scheduled courses
	 * 
	 * @return course schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] cat = new String[schedule.size()][4];
		for(int i = 0; i < schedule.size(); i++) {
			Activity c = schedule.get(i);
			cat[i] = c.getShortDisplayArray();
		}
		return cat;
	}

	/**
	 * getter of string array of full course catalog
	 * 
	 * @return CourseCatalog
	 */
	public String[][] getFullScheduledActivities() {
		String[][] cat = new String[schedule.size()][6];
		for(int i = 0; i < schedule.size(); i++) {
			Activity c = schedule.get(i);
			cat[i] = c.getLongDisplayArray();
		}
		return cat;
	}

	
	/**
	 * getter of string course title
	 * 
	 * @return title
	 */
	public String getScheduleTitle() {
		// TODO Auto-generated method stub
		return title;
	}
	
	/**
	 * set schedule title
	 * 
	 * @param title title
	 */
	public void setScheduleTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	
	/**
	 * exportSchedule to file
	 * 
	 * @param filename filename
	 */
	public void exportSchedule(String filename) {
		// TODO Auto-generated method stub
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);
		} catch(Exception e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}

	
	/**
	 * get a course from the course catalog
	 * 
	 * @param name course name
	 * @param section section number
	 * @return course
	 */
	public Course getCourseFromCatalog(String name, String section) {
		// TODO Auto-generated method stub
		for(int i = 0; i < catalog.size(); i++) {
			if(name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return catalog.get(i);
			}
		}
		return null;
	}

	
	/**
	 * add course to schedule
	 * 
	 * @param name name of course
	 * @param section section of course
	 * @return boolean if successful
	 */
	public boolean addCourseToSchedule(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			if(name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				for(int x = 0; x < schedule.size(); x++) {
					if(catalog.get(i).isDuplicate(schedule.get(x))) {
						throw new IllegalArgumentException("You are already enrolled in " + name);
					}
					
					try {
						catalog.get(i).checkConflict(schedule.get(x));
					} catch (ConflictException e) {
						throw new IllegalArgumentException("The course cannot be added due to a conflict.");
					}
				}
				schedule.add(catalog.get(i));
				return true;
			}
		}
		return false;
	}

	
	/**
	 * remove course from schedule
	 * @param idx TODO
	 * 
	 * @return boolean if course successfully removed
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	/**
	 * resetSchedule sets the schedule to empty
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}
	
	/**
	 * add event to schedule
	 * 
	 * @param eventTitle title
	 * @param eventMeetingDays meeting days
	 * @param eventStartTime start time
	 * @param eventEndTime end time
	 * @param eventDetails details
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for(int i = 0; i < schedule.size(); i++) {
			if(event.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			try {
				event.checkConflict(schedule.get(i));
			} catch(ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		
		
		schedule.add(event);
	}

}
