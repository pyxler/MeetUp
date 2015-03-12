package ca.ubc.cs.cpsc210.meetup.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Peter Lu
 * @since 2015-03-02
 */

public class Course {
	
	// The code corresponding to the type of course
	private String code;
	
	// The course number
	private int number;
	
	private List<Section> sections;
	
	/**
	 * Constructor
	 * 
	 * @param code
	 * @param number
	 */
	public Course(String code, int number){
		this.code = code;
		this.number = number;
		sections = new ArrayList<Section>();
	}
	
	public String getCode(){
		return code;
	}
	
	public int getNumber(){
		return number;
	}

	/**
	 * Add a section to the course
	 * @param section
	 */
	public void addSection(Section section){
		sections.add(section);
	}

	/**
	 * @return the sections
	 */
	public List<Section> getSections() {
		return sections;
	}

	/**
	 * @param sections the sections to set
	 */
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * 
	 * @param sectionId
	 * @return Section if it exists, null otherwise
	 */
	public Section getSection(String sectionId){
		for (Section s: sections){
			if (s.getName().equals(sectionId))
				return s;
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + number;
		result = prime * result
				+ ((sections == null) ? 0 : sections.hashCode());
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
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (sections == null) {
			if (other.sections != null) {
				return false;
			}
		} else if (!sections.equals(other.sections)) {
			return false;
		}
		return true;
	}
	
}