package ca.ubc.cs.cpsc210.meetup.model;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * 
 * @author Peter
 * @since 03-09-15
 */

public class Place extends Location{

	// The name of the place
	private String name;
	protected List<String> tags;
	
	// Constructor for the place, give name and latlon of place
	public Place(String name, LatLon latlon){
		this.latlon = latlon;
		this.name = name;
		tags = new ArrayList<String>();
	}
	
	// Constructor, just the name of the place
	public Place(String name){
		this.name = name;
		tags = new ArrayList<String>();
	}
	
	// adds a tag to the list of tags
	// MODIFIES: tags
	// EFFECTS: adds a tag to list of tags
	public void addTag(String tag){
		tags.add(tag);
	}
	
	// MODIFIES: nothing
	// EFFECTS: returns true if list of tags contains given tag
	public boolean containsTag(String tag){
		return tags.contains(tag);
	}
	
	// EFFECTS: returns the name of the place
	public String getName(){
		return name;
	}
	
	public LatLon getLatLon(){
		return latlon;
	}
	
}
