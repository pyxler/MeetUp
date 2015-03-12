package ca.ubc.cs.cpsc210.meetup.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/*
 * Represent a student's schedule consisting of all sections they must attend
 */
public class Schedule {

	// TODO: Implement fields
	private SortedSet<Section> sections;

	/**
	 * Constructor
	 */
	public Schedule() {
		// TODO: Complete implementation of construtor
		sections = new TreeSet<Section>();
	}

	/**
	 * Add a section to the student's schedule 
	 * REQUIRES: section is not null
	 * MODIFIES: this 
	 * EFFECTS: if section is not well-formed, throws
	 * IllegalSectionInitialization otherwise, section is remembered in the
	 * schedule
	 */
	public void add(Section section) throws IllegalSectionInitialization {
		// TODO: Complete implementation
		if (section == null){
			throw new IllegalSectionInitialization();
		}
		
		sections.add(section);
	}

	/**
	 * Retrieve the earliest start time in the schedule on a given day 
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" 
	 * EFFECTS: Returns the start and end
	 *    times of the earliest section on that day or null
	 */
	public CourseTime startTime(String dayOfWeek) throws IllegalCourseTimeException{
		// TODO: Complete implementation
		if ((dayOfWeek != "MWF") && (dayOfWeek != "TR")){
			return null;
		}
		
		//create a set of just the dayOfWeek
		SortedSet<Section> sectionsWanted = new TreeSet<Section>();
		
		for (Section s: sections){
			if (s.getDay().equals(dayOfWeek)){
				sectionsWanted.add(s);
			}
		}
		
		//Get the earliest section from sections with dayOfWeek
		Section earliestSection = sectionsWanted.first();
		
		CourseTime earliestTime = new CourseTime(earliestSection.getStartTime(),
													earliestSection.getEndTime());
		return earliestTime;
		
	}

	/**
	 * Retrieve the latest start time in the schedule on a given day 
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" 
	 * EFFECTS: Returns the start and end
	 *    times of the latest section on that day or null
	 */
	public CourseTime endTime(String dayOfWeek) {
		// TODO: Complete implementation
		if (!dayOfWeek.equals("MWF") && !dayOfWeek.equals("TR")){
			return null;
		}
		
		//create a set of just the dayOfWeek
		SortedSet<Section> sectionsWanted = new TreeSet<Section>();
		
		for (Section s: sections){
			if (s.getDay().equals(dayOfWeek)){
				sectionsWanted.add(s);
			}
		}
		
		//Get the earliest section from sections with dayOfWeek
		Section latestSection = sectionsWanted.last();
		
		try {
			CourseTime latestTime = new CourseTime(latestSection.getStartTime(), latestSection.getEndTime());
			
			return latestTime;
		}catch (IllegalCourseTimeException e) {
			e.printStackTrace();
		}
		
		return null;			
	}

	/**
	 * Find the start time of all two hour breaks less than the end time
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" 
	 * EFFECTS: Returns a set of the end time before any 2 hour break. The end time is in HH:MM format.
	 */
	public Set<String> getStartTimesOfBreaks(String dayOfWeek) {
		// TODO: Complete implementation
		Set<String> endTimesBeforeBreak = new HashSet<String>();
		
		Iterator<Section> iterator = sections.iterator();
		
		Section prev = iterator.next();
		DateFormat f = new SimpleDateFormat("hh:mm");
		
		while(iterator.hasNext()){
			Section next = iterator.next();
			
			try{
				Date d1 = f.parse(prev.getEndTime());
				Date d2 = f.parse(next.getStartTime());
				
				long milliDifference = d2.getTime() - d1.getTime();
				long seconds = milliDifference/1000;
				long minutes = seconds / 60;
				
				if (minutes >= 120){
					endTimesBeforeBreak.add(prev.getEndTime());
				}
			}catch (ParseException e){
				e.printStackTrace();
			}
			prev = next;
		}
		
		return endTimesBeforeBreak;
		
	}

	/**
	 * In which building was I before the given timeOfDay on the given dayOfWeek
	 * REQUIRES: dayOfWeek is "MWF or "TR" and timeOfDay is non-null and of
	 * format HH:MM 
	 * EFFECTS: The Building in which the student last was before
	 * timeOfDay on dayOfWeek or null
	 */
	public Building whereAmI(String dayOfWeek, String timeOfDay) {
	   // TODO: Complete implementation
		
		// day input is not correct, or the timeOfDay is not correct
		if (((dayOfWeek != "MWF") && (dayOfWeek != "TR")) ||
				(timeOfDay == null)){
			return null;
		}
		
		// Filter the sorted sections to just the inputted day
		SortedSet<Section> daySections = new TreeSet<Section>();
		
		for(Section s: sections){
			if (s.getDay().equals(dayOfWeek)){
				daySections.add(s);
			}
		}
		
		// iterate over the sections
		Iterator<Section> iterator = daySections.iterator();
		Section prev = iterator.next();
		DateFormat f = new SimpleDateFormat("hh:mm");
		
		// set the current time
		try {
			Date currentTime = f.parse(timeOfDay);
		
			while (iterator.hasNext()){
				Section next = iterator.next();
				
				// turn the current course times into hour and minute format
				Date courseStartTime = f.parse(prev.getStartTime());
				Date courseEndTime = f.parse(prev.getEndTime());
				
				// return the current building if current time is between the start and end time
				// of current class
				if ((currentTime.after(courseStartTime)) && (currentTime.before(courseEndTime))){
					return prev.getBuilding();
				}
				
				// turn the next course start time into hour and minute format
				Date nextStartTime = f.parse(next.getStartTime());
				
				// if the currentTime is before the next start time, return the previous building
				if (currentTime.before(nextStartTime)){
					return prev.getBuilding();
				}
				
				prev = next;
			}
		}catch(ParseException e){
		}
		
		return prev.getBuilding();
	}


}
