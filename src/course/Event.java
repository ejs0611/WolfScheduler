package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class
 * 
 * @author smithej
 *
 */
public class Event extends Activity {

	/** event Details*/
	private String eventDetails;
	
	/**
	 * getter for short display array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String str[] = new String[4];
		str[0] = "";
		str[1] = "";
		str[2] = getTitle();
		str[3] = getMeetingString();
		return str;
	}

	/**
	 * getter for long display array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String str[] = new String[7];
		str[0] = "";
		str[1] = "";
		str[2] = getTitle();
		str[3] = "";
		str[4] = "";
		str[5] = getMeetingString();
		
		str[6] = eventDetails;
		
		return str;
	}

	/** 
	 * event details getter method
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/** 
	 * setter for event details
	 * 
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/** 
	 * Event constructor
	 * 
	 * @param title title
	 * @param meetingDays meetingDays
	 * @param startTime startTime
	 * @param endTime endTime
	 * @param eventDetails event details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getTitle() + "," + super.getMeetingDays() + "," + super.getStartTime() + "," + super.getEndTime() + "," + eventDetails;
	}

	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// TODO Auto-generated method stub
		//super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		if(startTime == 0 || endTime == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if(startTime > endTime){
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		//create local variables to hold the counts for each weekday letter
		int mon = 0;
		int tue = 0;
		int wed = 0;
		int thu = 0;
		int fri = 0;
		int sat = 0;
		int sun = 0;
	      
		for(int i = 0; i < meetingDays.length(); i++) {
			if(meetingDays.charAt(i) == 'M') {
				mon++;
			} else if(meetingDays.charAt(i) == 'T') {
				tue++;
			} else if(meetingDays.charAt(i) == 'W') {
				wed++;
			} else if(meetingDays.charAt(i) == 'H') {
				thu++;
			} else if(meetingDays.charAt(i) == 'F') {
				fri++;
			} else if(meetingDays.charAt(i) == 'S') {
				sat++;
			} else if(meetingDays.charAt(i) == 'U') {
				sun++;
			} else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		
		if(mon > 1 || tue > 1 || wed > 1 || thu > 1 || fri > 1 || sat > 1 || sun > 1) { // checks for duplicates
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		
			
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		
		
	}

	/**
	 * Checks if activity is a duplicate
	 * 
	 * @param activity activity
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		// TODO Auto-generated method stub
		if(activity instanceof Event) {
			return getTitle().equals(((Event) activity).getTitle());
		} else {
			return false;
		}
	}

	
}
