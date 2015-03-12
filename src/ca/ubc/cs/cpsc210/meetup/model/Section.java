package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/**
 * Represent a section for a course
 */
public class Section implements Comparable<Section> {

	// TODO: Add necessary fields
	
	// Time of course is provided to implement comparable
	private CourseTime timeOfCourse;
	
	private String name;
	private String day;
	private String startTime;
	private String endTime;
	private Building building;
	
	private Course course;
	
	/**
	 * Constructor 
	 * REQUIRES: name is not null, day is "MWF" or "TR", startTime
	 *   is before endTime and building is not null 
	 * EFFECTS: object is initialized
	 *   or the exception IllegalSectionInitialization has occurred
	 */
	public Section(String name, String day, String startTime, String endTime,
			Building building) throws IllegalSectionInitialization{
		
		//initialize fields
		this.name = name;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.building = building;
		
		// initialize new courseTime
		try {
			timeOfCourse = new CourseTime(startTime,endTime);
		} catch (IllegalCourseTimeException e) {
			e.printStackTrace();
		}
		
		course = new Course("", 0);
	}

	/**
	 * @return the timeOfCourse
	 */
	public CourseTime getTimeOfCourse() {
		return timeOfCourse;
	}

	/**
	 * @param timeOfCourse the timeOfCourse to set
	 */
	public void setTimeOfCourse(CourseTime timeOfCourse) {
		this.timeOfCourse = timeOfCourse;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	// TODO: Add necessary methods
	
	public void setCourse(Course course){
		this.course = course;
	}

	@Override
	public int compareTo(Section o) {
		return timeOfCourse.compareTo(o.timeOfCourse);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((building == null) ? 0 : building.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result
				+ ((timeOfCourse == null) ? 0 : timeOfCourse.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Section)) {
			return false;
		}
		Section other = (Section) obj;
		if (building == null) {
			if (other.building != null) {
				return false;
			}
		} else if (!building.equals(other.building)) {
			return false;
		}
		if (day == null) {
			if (other.day != null) {
				return false;
			}
		} else if (!day.equals(other.day)) {
			return false;
		}
		if (endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!endTime.equals(other.endTime)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!startTime.equals(other.startTime)) {
			return false;
		}
		if (timeOfCourse == null) {
			if (other.timeOfCourse != null) {
				return false;
			}
		} else if (!timeOfCourse.equals(other.timeOfCourse)) {
			return false;
		}
		return true;
	}

}
