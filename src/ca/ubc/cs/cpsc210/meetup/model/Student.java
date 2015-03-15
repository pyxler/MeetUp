package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalStudentException;

/**
 * 
 * @author Peter
 * @since 2015-03-02
 */

public class Student {
	
	private String lastName;
	private String firstName;
	private int id;
	private Schedule schedule;
	
	public Student(String lastName, String firstName, int id){
		if (lastName.equals("") || firstName.equals("")){
			throw new IllegalStudentException();
		}
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
		this.schedule = new Schedule();
		
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public int getId(){
		return id;
	}
	
	public Schedule getSchedule(){
		if (schedule == null){
			schedule = new Schedule();
		}
		
		return schedule;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}
	
	
}
