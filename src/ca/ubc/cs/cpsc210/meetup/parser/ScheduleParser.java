package ca.ubc.cs.cpsc210.meetup.parser;

import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalStudentException;
import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.model.Student;
import ca.ubc.cs.cpsc210.meetup.model.StudentManager;

/**
 * Parse XML of a schedule (Sax parser)
 */

public class ScheduleParser extends DefaultHandler {
	
	String lastName;
	String firstName;
	int id;
	int studentId;
	StudentManager manager;
	Section section;
	
	private StringBuffer accumulator;

	// TODO: Add necessary fields

	public ScheduleParser(StudentManager manager) {
		// TODO: Complete implementation of this constructor
		accumulator = new StringBuffer();
		this.manager = manager;
	}

	/**
	 * Called at the start of the document (as the name suggests)
	 */
	@Override
	public void startDocument() {
		// TODO: Complete implementation of this method
		System.out.println("Starting the document.");
	}

	/**
	 * Called when the parsing of an element starts. (e.g., <book>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		// TODO: Complete implementation of this method
		accumulator.setLength(0);
		System.out.println("Started element:" + qName);
		
		//Reinitialize all values if it is a student
		if (qName.toLowerCase().equals("student")){
			lastName = null;
			firstName = null;
			id = 0;
		}
		
		//If it is a Section, create a new one
		if (qName.toLowerCase().equals("section")){
			manager.addSectionToSchedule(studentId,
										atts.getValue("courseCode"),
										Integer.parseInt(atts.getValue("courseNumber")),
										atts.getValue("name"));
		}
		
	}

	/**
	 * Called for values of elements
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void characters(char[] temp, int start, int length) {
		// Remember the value parsed
		accumulator.append(temp, start, length);
	}

	/**
	 * Called when the end of an element is seen. (e.g., </title>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	@Override
	public void endElement(String uri, String localName, String qName){
		// TODO: Complete implementation of this method
		System.out.println("Ended element: " + qName);
		System.out.println("Buffer looks like: " + accumulator);
		
		//Assign the appropriate variables
		if(qName.toLowerCase().equals("lastname")){
			lastName = accumulator.toString();
		} else if(qName.toLowerCase().equals("firstname")){
			firstName = accumulator.toString();
		} else if(qName.toLowerCase().equals("id")){
			id = Integer.parseInt(accumulator.toString());
			System.out.println("id assigned");
		} 
		
		// if student then create a new student with all the fields assigned before
		else if(qName.toLowerCase().equals("student")){
			try {
				manager.addStudent(lastName, firstName, id);
			} catch (IllegalStudentException e) {
				e.printStackTrace();
			}
		}
		
		if(qName.toLowerCase().equals("studentid")){
			studentId = Integer.parseInt(accumulator.toString());
		}
		
		accumulator.setLength(0);
	}
	
	@Override
	public void endDocument(){
		System.out.println("Stopped reading the document.");
	}

}
