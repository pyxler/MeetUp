package ca.ubc.cs.cpsc210.meetup.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;


/**
 * 
 * @author Peter
 * @since 03-09-15
 */

/*
 * Provide a factory for places that have been "seen"
 */
public class PlaceFactory {
	
	// Key is the name of the building, value is the set of places with that name
	private Map<String, Set<Place>> places;
	
	// Singleton pattern
	private static PlaceFactory instance = null;
	
	/**
	 * 
	 * Return the factory instance
	 * EFFECTS: returns a factory to look up a place
	 * 
	 */
	public static PlaceFactory getInstance(){
		if (instance == null)
			instance = new PlaceFactory();
		
		return instance;	
	}
	
	
	/**
	 * Resets the instance by turning it to null
	 */
	public static void reset(){
		instance = null;
	}
	
	public Map<String, Set<Place>> getPlaces(){
		return places;
	}
	
	/**
	 * Singleton pattern: called by getInstance()
	 * ensures that only one of these objects exist
	 */
	protected PlaceFactory(){
		places = new HashMap<String, Set<Place>>();
	}
	
	// EFFECTS: returns a given place set depending on the name
	//			adds to the set if not exist
	public Set<Place> get(String name){
		
		Set<Place> placeOfInterest = places.get(name);
		
		// place does not exist, add it
		if(placeOfInterest == null){
			Place newPlace = new Place(name);
			add(newPlace);
			return places.get(name);
		}
		
		// else return the place we got in the first place
		return placeOfInterest;
	}
	
	public void add(Place place){
		String name = place.getName();
		Collection<Set<Place>> values = places.values();
		boolean create = true;
		
		LatLon placeLatLon = place.latlon;
		
		for (Set<Place> p: values){
			for (Place a: p){
				if(a.latlon.equals(placeLatLon)){
					create = false;
				}
			}
		}
		
		if(create){
			//Map already has the name in there, add it
			if (places.containsKey(name)){
				Set<Place> placesOfInterest = places.get(name);
				placesOfInterest.add(place);
			}
			// map doesn't have the name, create new one
			else{
				Set<Place> placeToAdd = new HashSet<Place>();
				placeToAdd.add(place);
				places.put(name, placeToAdd);
			}
		}
		
	}
	
	
}
