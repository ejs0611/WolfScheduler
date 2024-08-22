package edu.ncsu.csc216.wolf_scheduler.course;


/**
 * Course class describes course
 * 
 * @author Eric Smith
 */
public class Course extends Activity {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** min name length */
	private int minNameLength = 5;
	
	/** max name length */
	private int maxNameLength = 8;
	
	/** min letter count */
	private int minLetterCount = 1;
	
	/** max letter count */
	private int maxLetterCount = 4;
	
	/** section length */
	private int sectionLength = 3;
	
	/** min credits */
	private int minCredits = 1;
	
	/** max credits */
	private int maxCredits = 5;
	
	
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
		setName(name);
	    //setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    //setMeetingDaysAndTime(meetingDays, startTime, endTime);
	    
	    
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
	    this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}
	
	
	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Course's section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section.
	 * @param section the section to set
	 */
	public void setSection(String section) {
		if(section == null || section.length() != sectionLength) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for(int i = 0; i < sectionLength; i++) {
			if(!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	
	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits.
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if(credits > maxCredits || credits < minCredits) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}
	
	/**
	 * Returns the Course's instructor ID.
	 * @return the instructor id
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course's instructor id.
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	
	
	

	

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
	    //Throw exception if the name is null
	    if(name == null) {
	        throw new IllegalArgumentException("Invalid course name.");
	    }
	    //Throw exception if the name is an empty string
	    if("".equals(name)) {
	        throw new IllegalArgumentException("Invalid course name.");
	    }
	    
	    //Throw exception if the name contains less than 5 character or greater than 8 characters
	    if(name.length() < minNameLength || name.length() > maxNameLength) {
	    	throw new IllegalArgumentException("Invalid course name.");
	    }
	    //Check for pattern of L[LLL] NNN
	    
	    int letters = 0;
	    int digits = 0;
	    boolean space = false;
	    for(int i = 0; i < name.length(); i++) {
	        if(!space) {
	            if(Character.isLetter(name.charAt(i))) { //the character at i is a letter
	                letters++;
	            } else if(name.charAt(i) == ' ') { //the character at i is a space
	                space = true;
	            } else {
	                throw new IllegalArgumentException("Invalid course name.");
	            }
	        } else if(space) { //a space is found
	            if(Character.isDigit(name.charAt(i))) {
	            		digits++;
	            } else {
	                throw new IllegalArgumentException("Invalid course name.");
	        	}
	        }
	    }
	    /*if(space == false) {
	    	throw new IllegalArgumentException("Invalid course name.");
	    }*/
	    //Check that the number of letters is correct
	    if(letters < minLetterCount || letters > maxLetterCount) { //letter counter is less than one or more than 4
	        throw new IllegalArgumentException("Invalid course name.");
	    }
	    //Check that the number of digits is correct
	    if(digits != 3) { //digit counter is not 3
	        throw new IllegalArgumentException("Invalid course name.");
	    }
	    this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + maxLetterCount;
		result = prime * result + maxNameLength;
		result = prime * result + minCredits;
		result = prime * result + minLetterCount;
		result = prime * result + minNameLength;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + sectionLength;
		return result;
	}

	@Override
	public String[] getShortDisplayArray() {
		String str[] = new String[4];
		str[0] = name;
		str[1] = section;
		str[2] = getTitle();
		str[3] = getMeetingString();
		return str;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (maxLetterCount != other.maxLetterCount)
			return false;
		if (maxNameLength != other.maxNameLength)
			return false;
		if (minCredits != other.minCredits)
			return false;
		if (minLetterCount != other.minLetterCount)
			return false;
		if (minNameLength != other.minNameLength)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
//		if (sectionLength != other.sectionLength)
//			return false;
		return true;
	}

	@Override
	public String[] getLongDisplayArray() {
		String str[] = new String[7];
		str[0] = name;
		str[1] = section;
		str[2] = getTitle();
		str[3] = "" + credits;
		str[4] = instructorId;
		str[5] = getMeetingString();
		str[6] = "";
		
		return str;
	}

	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// TODO Auto-generated method stub
		//super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		if("A".equals(meetingDays)) { // meetingDays is "A" // Arranged
			if(startTime != 0 || endTime != 0) {// startTime is NOT 0 OR endTime is NOT 0
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		    //set meetingDays to the parameter; startTime and endTime to 0
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		} else {
			if(startTime > endTime){
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			//create local variables to hold the counts for each weekday letter
			int mon = 0;
			int tue = 0;
			int wed = 0;
			int thu = 0;
			int fri = 0;
		      
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
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			
			if(mon > 1 || tue > 1 || wed > 1 || thu > 1 || fri > 1) { // checks for duplicates
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
				
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * checks if activity is a duplicate
	 * 
	 * @param activity activity object 
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		// TODO Auto-generated method stub
		//return false;
		if(activity instanceof Course) {
			return getName().equals(((Course) activity).getName());
		} else {
			return false;
		}
	}
	
	
	
	

}

