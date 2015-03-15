package ca.ubc.cs.cpsc210.meetup.parser;

import java.io.Reader;
import java.util.Map;
import java.util.Set;

import javax.xml.ws.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.ubc.cs.cpsc210.meetup.model.EatingPlace;
import ca.ubc.cs.cpsc210.meetup.model.Place;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * Foursquare location result parse (JSON)
 */
public class PlacesParser {

	PlaceFactory placeFactory;
	JSONTokener tokener;
	String name;
	LatLon latlon;
	double lat;
	double lng;
	JSONObject obj;
	
	/**
	 * Parse JSON from Foursquare output stored into a file
	 * REQUIRES: input is a file with valid data
	 * EFFECTS: parsed data is put into PlaceFactory
	 */
	public void parse(Reader input) {
		
		placeFactory = PlaceFactory.getInstance();
		tokener = new JSONTokener(input);
		name = "";
		lat = 0;
		lng = 0;
		
		try {
			obj = new JSONObject(tokener);
			JSONArray venues = obj.getJSONObject("response").getJSONArray("venues");
			
			for (int i = 0; i < venues.length(); i++){
				JSONObject curr = venues.getJSONObject(i);
				
				String name = curr.getString("name");
				
				JSONObject location = curr.getJSONObject("location");
				
				lat = location.getDouble("lat");
				lng = location.getDouble("lng");
				latlon = new LatLon(lat, lng);
		
				EatingPlace currentPlace = new EatingPlace(name, latlon);
				placeFactory.add(currentPlace);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
