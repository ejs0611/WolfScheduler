package edu.ncsu.csc216.wolf_scheduler.course;
/**
 * Activity object superclass
 * 
 * @author Eric Smith
 *
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/** 
	 * returns array of string for Activity
	 * 
	 * @return ShortDisplayArray
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * returns array of strings for Activity
	 * 
	 * @return LongDisplayArray
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * checks if is a duplicate
	 * 
	 * @param activity activity
	 * @return true false if it is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	
	/**
	 * Activity constructor
	 * 
	 * @param title title
	 * @param meetingDays meeting days
	 * @param startTime start time
	 * @param endTime end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		if(title == null ||  "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	
	

	/**
	 * Returns the Course's title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if(title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
			
		} else {
			this.title = title;
		}
		
	}

	/**
	 * Returns the Course's meeting days.
	 * @return the meeting days
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * @return the start time
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * @return the end time
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * sets meetings days and start and end time
	 * 
	 * @param meetingDays meeting days
	 * @param startTime start time
	 * @param endTime end time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times."); // IAE = IllegalArgumentException
		}
		int startHour = startTime / 100;
		int startMin = startTime % 100;
			
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		if(startHour < 0 || startHour > 23) {
	    	throw new IllegalArgumentException("Invalid meeting days and times.");
	    }				
		if(startMin < 0 || startMin > 59) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
			
		if(endHour < 0 || endHour > 23) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
			
		if(endMin < 0 || endMin > 59) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * meeting String getter method
	 * 
	 * @return meeting string
	 */
	public String getMeetingString() {
		if("A".equals(meetingDays)){
			return "Arranged";
		} else {
			return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
		}
		
	}

	/**
	 * time string getter
	 * 
	 * @param time input military time
	 * @return time string
	 */
	private String getTimeString(int time) {
		int hours = time / 100;
		String ampm = "AM";
		if(hours >= 12) {
			ampm = "PM";
			hours = hours - 12;
		}
		if(hours == 0) {
			hours = 12;
		}
		int mins = time % 100;
		String minString = mins + "";
		if(mins < 10) {
			minString = "0" + minString;
		}
		//MW 1:30PM-2:45PM
		//do the leading 0 on minutes
		return hours + ":" + minString + ampm;
	}

	/**
	 * checks if activities are conflicting
	 * 
	 * @param possibleConflictingActivity conflicting activity
	 * @throws ConflictException if conflicting
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		for(int i = 0; i < this.getMeetingDays().length(); i++) {
			for(int x = 0; x < possibleConflictingActivity.getMeetingDays().length(); x++) {
				if(!this.getMeetingDays().equals("A") && !possibleConflictingActivity.getMeetingDays().equals("A") && this.getMeetingDays().charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(x)) {
					if(this.getStartTime() <= possibleConflictingActivity.getStartTime() && possibleConflictingActivity.getStartTime() <= this.getEndTime()) {
						throw new ConflictException();
					} else if(this.getStartTime() <= possibleConflictingActivity.getEndTime() && possibleConflictingActivity.getEndTime() <= this.getEndTime()) {
						throw new ConflictException();
					} else if(possibleConflictingActivity.getStartTime() <= this.getStartTime() && this.getStartTime() <= possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					} else if(possibleConflictingActivity.getStartTime() <= this.getEndTime() && this.getEndTime() <= possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
				}
			}
		}
		
		
	}

	/**
	 * hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	

}