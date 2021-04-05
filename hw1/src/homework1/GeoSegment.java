package homework1;

/**
 * <p>
 * A GeoSegment models a straight line segment on the earth. GeoSegments 
 * are immutable.
 * </p>
 * A compass heading is a nonnegative real number less than 360. In compass
 * headings, north = 0, east = 90, south = 180, and west = 270.
 * <p>
 * When used in a map, a GeoSegment might represent part of a street,
 * boundary, or other feature.
 * As an example usage, this map
 * 
 *  Trumpeldor   a
 *  Avenue       |
 *               i--j--k  Hanita
 *               |
 *               z
 * could be represented by the following GeoSegments:
 * ("Trumpeldor Avenue", a, i), ("Trumpeldor Avenue", z, i),
 * ("Hanita", i, j), and ("Hanita", j, k).
 * </p>
 * 
 * <p>
 * A name is given to all GeoSegment objects so that it is possible to
 * differentiate between two GeoSegment objects with identical
 * GeoPoint endpoints. Equality between GeoSegment objects requires
 * that the names be equal String objects and the end points be equal
 * GeoPoint objects.
 * </p>
 *
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   name : String       // name of the geographic feature identified
 *   p1 : GeoPoint       // first endpoint of the segment
 *   p2 : GeoPoint       // second endpoint of the segment
 *   length : real       // straight-line distance between p1 and p2, in kilometers
 *   heading : angle     // compass heading from p1 to p2, in degrees
 * </pre>
 **/
public class GeoSegment  {

	
  	// TODO Write abstraction function and representation invariant
	/*
	 abstraction function
	 * This class represent straight line between two GeoPoint .
	 * name - the name of the geographic feaute
	 * P1 - first GeoPoint
	 * P2 - second GeoPoint
	 * length - distance between P1 and P2
	 * heading - compass heading angle from P1 to P2 
	 */
	
	private final String name ;
	private final GeoPoint p1 ;
	private final GeoPoint p2 ;
	private final Double length ;
	private final Double angle ;

  	/*
 	 * representation invariant:
 	 * all the variables are not null 
 	 * length is positive double
 	 * angle is between 0 and 360 
 	 */
  	private void checkRep() {
  		assert (this.p1 != null)      		 : "p1 is null" ; 
  		assert (this.p2 != null)      		 : "p2 is null" ; 
  		assert (this.length >= 0)      		 : "length has negative value" ;
  		assert (angle >= 0 && angle <= 360 ) : "wrong angle value" ;
  	}
  	

  	/**
     * Constructs a new GeoSegment with the specified name and endpoints.
     * @requires name != null &amp;&amp; p1 != null &amp;&amp; p2 != null
     * @effects constructs a new GeoSegment with the specified name and endpoints.
	 * @param name - name of the Segment 
	 * @param p1 - source point	
	 * @param p2 - destination point
	 */
  	public GeoSegment(String name, GeoPoint p1, GeoPoint p2) {
  		this.name = name ;
  	    this.p1 = new GeoPoint(p1.getLatitude(),p1.getLongitude());
  	    this.p2 = new GeoPoint(p2.getLatitude(),p2.getLongitude());
  	    this.length = this.p1.distanceTo(this.p2);
  	    if (this.length == 0) {
  	    	this.angle = 0.0 ;
  	    }
  	    else {
  	  	    this.angle = this.p1.headingTo(this.p2);

  	    }
  		checkRep();
  	}


  	/**
     * Returns a new GeoSegment like this one, but with its endpoints reversed.
     * @return a new GeoSegment gs such that gs.name = this.name
     *         &amp;&amp; gs.p1 = this.p2 &amp;&amp; gs.p2 = this.p1
     **/
  	public GeoSegment reverse() {
  		checkRep();
  		GeoSegment revered = new GeoSegment(this.getName(),this.getP2(),this.getP1());
  		checkRep();
  		return revered; 
  	}

  	/**
  	 * Returns the name of this GeoSegment.
     * @return the name of this GeoSegment.
     */
  	public String getName() {
  		checkRep();
  		return (this.name) ;
  	}


  	/**
  	 * Returns first endpoint of the segment.
     * @return first endpoint of the segment.
     */
  	public GeoPoint getP1() {
  		checkRep();
  		GeoPoint newP1 = new GeoPoint(this.p1.getLatitude(),this.p1.getLongitude());
  		
  		checkRep();
  		return newP1;
  	}

  	/**
  	 * Returns second endpoint of the segment.
     * @return second endpoint of the segment.
     */
  	public GeoPoint getP2() {
  		checkRep();
  		GeoPoint newP2 = new GeoPoint(this.p2.getLatitude(),this.p2.getLongitude());
  		checkRep();
  		return newP2;
  	}


  	/**
  	 * Returns the length of the segment.
     * @return the length of the segment, using the flat-surface, near the
     *         Technion approximation.
     */
  	public double getLength() {
  		checkRep();
  		return (this.length);
  	}


  	/**
  	 * Returns the compass heading from p1 to p2.
     * @requires none
     * @return the compass heading from p1 to p2, in degrees, using the
     *         flat-surface, near the Technion approximation.
     **/
  	public double getHeading() {
  		checkRep();
  		return (this.angle);
  	}

  	/**
     * Compares the specified Object with this GeoSegment for equality.
     * @return gs != null &amp;&amp; (gs instanceof GeoSegment)
     *         &amp;&amp; gs.name = this.name &amp;&amp; gs.p1 = this.p1 &amp;&amp; gs.p2 = this.p2
   	 **/
  	public boolean equals(Object gs) {
  		checkRep();
  		boolean condA = (gs != null) ; 
  		boolean condB = (gs instanceof GeoSegment);
  		boolean condC = false ;
  		boolean condD = false ; 
  		boolean condE = false ;
  		if(condB == true) {
  	  		condC = (((GeoSegment)gs).name.equals(this.name))  ;
  	  		condD = (((GeoSegment)gs).p1.equals(this.p1));
  	  		condE = (((GeoSegment)gs).p2.equals(this.p2));
  		}

  		checkRep();
  		return (condA && condB && condC && condD && condE) ; 
  	}


  	/**
  	 * Returns a hash code value for this.
     * @return a hash code value for this.
     **/
  	public int hashCode() {
    	// This implementation will work, but you may want to modify it 
    	// for improved performance. 
  		checkRep();
  		int hashedP1 = this.p1.hashCode();
  		int hashedP2 = this.p2.hashCode();
  		checkRep();
    	return (hashedP1+hashedP2);
  	}


  	/**
  	 * Returns a string representation of this.
     * @return a string representation of this.
     *  form of ( GeoSegment_name , p1 cordinates , p2 cordinates)
     **/
  	public String toString() {
  		checkRep();
  		String geoSegmentName = this.getName();
  		String stringP1 = this.p1.toString();
  		String stringP2 = this.p2.toString();
  		checkRep();
  		return ("(\""+geoSegmentName+"\","+stringP1+","+stringP2+")");
  	}

}


