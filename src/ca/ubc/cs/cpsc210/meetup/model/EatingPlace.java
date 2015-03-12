package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class EatingPlace extends Place{

	public EatingPlace(String name, LatLon latlon) {
		super(name, latlon);
	}
	
	public EatingPlace(String name){
		super(name);
	}
	
}
