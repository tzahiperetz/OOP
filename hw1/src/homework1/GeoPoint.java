package homework1;
import java.lang.Math; 

/**
 * A GeoPoint is a point on the earth. GeoPoints are immutable.
 * <p>
 * North latitudes and east longitudes are represented by positive numbers.
 * South latitudes and west longitudes are represented by negative numbers.
 * <p>
 * The code may assume that the represented points are nearby the Technion.
 * <p>
 * <b>Implementation direction</b>:<br>
 * The Ziv square is at approximately 32 deg. 46 min. 59 sec. N
 * latitude and 35 deg. 0 min. 52 sec. E longitude. There are 60 minutes
 * per degree, and 60 seconds per minute. So, in decimal, these correspond
 * to 32.783098 North latitude and 35.014528 East longitude. The 
 * constructor takes integers in millionths of degrees. To create a new
 * GeoPoint located in the the Ziv square, use:
 * <code>GeoPoint zivCrossroad = new GeoPoint(32783098,35014528);</code>
 * <p>
 * Near the Technion, there are approximately 110.901 kilometers per degree
 * of latitude and 93.681 kilometers per degree of longitude. An
 * implementation may use these values when determining distances and
 * headings.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   latitude :  real        // latitude measured in degrees
 *   longitude : real        // longitude measured in degrees
 * </pre>
 **/
public class GeoPoint {

	/** Minimum value the latitude field can have in this class. **/
	public static final int MIN_LATITUDE  =  -90 * 1000000;
	    
	/** Maximum value the latitude field can have in this class. **/
	public static final int MAX_LATITUDE  =   90 * 1000000;
	    
	/** Minimum value the longitude field can have in this class. **/
	public static final int MIN_LONGITUDE = -180 * 1000000;
	    
	/** Maximum value the longitude field can have in this class. **/
	public static final int MAX_LONGITUDE =  180 * 1000000;

  	/**
   	 * Approximation used to determine distances and headings using a
     * "flat earth" simplification.
     */
  	public static final double KM_PER_DEGREE_LATITUDE = 110.901;

  	/**
     * Approximation used to determine distances and headings using a
     * "flat earth" simplification.
     */
  	public static final double KM_PER_DEGREE_LONGITUDE = 93.681;
  	
	// Implementation hint:
	// Doubles and floating point math can cause some problems. The exact
	// value of a double can not be guaranteed except within some epsilon.
	// Because of this, using doubles for the equals() and hashCode()
	// methods can have erroneous results. Do not use floats or doubles for
	// any computations in hashCode(), equals(), or where any other time 
	// exact values are required. (Exact values are not required for length 
	// and distance computations). Because of this, you should consider 
	// using ints for your internal representation of GeoPoint. 

  	
  	// 
  	/* Abstraction Function :
  	 * GeoPoint represented by the spherical coordinating system 
  	 * we use the vertical(latitude) line, and the horizontal (longitude) line .
  	 * a GeoPoint is given by crossing both lines 
  	 */ 
	
  	private final int latitude  ;
  	private final int longitude ;
  
  	/**
 	 * representation invariant:
 	 * Latitude is value between -90 to 90
 	 * longitude is between -180 and 180 
 	 */
  
  	private void checkRep() {
  		assert this.latitude <= MAX_LATITUDE   : "Wrong value of latitude - max latitude is 90"     ; 
  		assert this.latitude >= MIN_LATITUDE   : "Wrong value of latitude - min latitude is -90"    ;
  		assert this.longitude <= MAX_LONGITUDE : "Wrong value of longitude - max longitude is 180"  ;
  		assert this.longitude >= MIN_LONGITUDE : "Wrong value of longitude - min longitude is -180" ;
  	}
  	/**
  	 * Constructs GeoPoint from a latitude and longitude.
  	 * 
     * @requires the point given by (latitude, longitude) in millionths
   	 *           of a degree is valid such that:
   	 *           (MIN_LATITUDE {@literal <}= latitude {@literal <}= MAX_LATITUDE) and
     * 	 		 (MIN_LONGITUDE {@literal <}= longitude {@literal <}= MAX_LONGITUDE)
   	 * @param latitude - vertical (y axis) line.
  	 * @param longitude - horizontal (x axis) line.
   	 * @effects constructs a GeoPoint from a latitude and longitude
     *          given in millionths of degrees.
   	 **/

  	public GeoPoint(int latitude, int longitude) {
  		this.latitude=latitude ;
  		this.longitude=longitude ;
  		checkRep();
  	}
  	 
  	/**
     * Returns the latitude of this.
     * @return the latitude of this in millionths of degrees.
     */
  	public int getLatitude() {
  		checkRep();
  		return this.latitude ;  
  	}


  	/**
     * Returns the longitude of this.
     * @return the latitude of this in millionths of degrees.
     */
  	public int getLongitude() {
  		checkRep();
  		return this.longitude ; 
  	}


  	/**
     * Computes the distance between GeoPoints.
     * @requires gp != null
     * @return the distance from this to gp, using the flat-surface, near
     *         the Technion approximation.
  	 * @param gp - the point that going to calculate distance to 
  	 */
  	public double distanceTo(GeoPoint gp) {
  		checkRep();
  		double xDist = ((0.000001) * KM_PER_DEGREE_LONGITUDE  * Math.abs((this.getLongitude() - gp.getLongitude()))) ; 
  		double yDist = (KM_PER_DEGREE_LATITUDE * (0.000001) * (this.getLatitude() - gp.getLatitude())) ;
  		checkRep();
  		return (Math.sqrt((xDist * xDist) + (yDist * yDist)));
  	}


  	/**
     * Computes the compass heading between GeoPoints.
     * @requires gp != null &amp;&amp; !this.equals(gp)
     * @return the compass heading h from this to gp, in degrees, using the
     *         flat-surface, near the Technion approximation, such that
     *         0 {@literal <}= h {@literal <} 360. In compass headings, north = 0, east = 90,
     *         south = 180, and west = 270.
  	 * @param gp - the point that going to calculate the direction to .
  	 */
  	public double headingTo(GeoPoint gp) {
		 //	Implementation hints:
		 // 1. You may find the mehtod Math.atan2() useful when
		 // implementing this method. More info can be found at:
		 // http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
		 //
		 // 2. Keep in mind that in our coordinate system, north is 0
		 // degrees and degrees increase in the clockwise direction. By
		 // mathematical convention, "east" is 0 degrees, and degrees
		 // increase in the counterclockwise direction. 
  		checkRep();
  		double directionX = KM_PER_DEGREE_LONGITUDE * (0.000001) * (gp.getLongitude() - this.getLongitude());
  		double directionY = KM_PER_DEGREE_LATITUDE  * (0.000001) * (gp.getLatitude() - this.getLatitude());
  		checkRep();
  		// 90 -  due to differnece between mathematical convention and our coordinate system
  		Double angle = Math.atan2(directionY, directionX) ;
  		
  	    // We need to map to cord system when 0 degree is at 3 O'clock, 270 at 12 O'clock
  	    if (angle < 0)
  	    	angle = Math.abs(angle);
  	    else
  	    	angle = 2 * Math.PI - angle;
  	    // adjust between output of atan2 and our cordinate system
  	    double angle_degree = 90 + Math.toDegrees(angle) ;

  	    // we work with angle range between 0 and 360 
  		if (angle_degree > 360) {
  			return (angle_degree - 360);
  		}
  		else if (angle_degree < 0) {
  			return (angle_degree + 360);
  		}
  		else if(angle_degree == 360){
  			angle_degree = 0 ;
  		}
		return angle_degree;
  	}


  	/**
     * Compares the specified Object with this GeoPoint for equality.
     * @return gp != null &amp;&amp; (gp instanceof GeoPoint) &amp;&amp;
     * 		   gp.latitude = this.latitude &amp;&amp; gp.longitude = this.longitude
     **/
  	public boolean equals(Object gp) {
  		checkRep();
  		boolean condA = (gp != null );
  		boolean condB = (gp instanceof GeoPoint) ; 
  		boolean condC = false ;
  		boolean condD = false ;
  		if (condB == true) {
  			condC = (((GeoPoint)gp).latitude == this.latitude) ; 
  	  		condD = ((((GeoPoint)gp).longitude == this.longitude)) ;		
  		}
  		checkRep();
  	 	return (condA && condB && condC && condD);
  	}


  	/**
     * Returns a hash code value for this GeoPoint.
     * @return a hash code value for this GeoPoint.
   	 **/
  	public int hashCode() {
  		checkRep();
    	return (this.getLatitude()+this.getLongitude());
  	}

  	/**
     * Returns a string representation of this GeoPoint. the form is (Latitude,Longitude)
     * @return a string representation of this GeoPoint. 
     * 
     **/ 
  	public String toString() {
  		checkRep();
  		String latString = String.valueOf(this.latitude);
  		String longString = String.valueOf(this.latitude);
  		checkRep();
  		return ("("+latString+","+ longString +")");
  	}

}
