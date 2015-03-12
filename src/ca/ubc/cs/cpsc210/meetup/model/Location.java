package ca.ubc.cs.cpsc210.meetup.model;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * 
 * @author Peter
 * @since 2015-03-02
 *
 */

public abstract class Location {
	// the latitude and longitude of the location
	protected LatLon latlon;
	
	// the text displayed (???)
	// ***
	private String displayText;
	
	public Location(){
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayText == null) ? 0 : displayText.hashCode());
		result = prime * result + ((latlon == null) ? 0 : latlon.hashCode());
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
		if (!(obj instanceof Location)) {
			return false;
		}
		Location other = (Location) obj;
		if (displayText == null) {
			if (other.displayText != null) {
				return false;
			}
		} else if (!displayText.equals(other.displayText)) {
			return false;
		}
		if (latlon == null) {
			if (other.latlon != null) {
				return false;
			}
		} else if (!latlon.equals(other.latlon)) {
			return false;
		}
		return true;
	}
	
}
