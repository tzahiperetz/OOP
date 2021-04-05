package homework1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A GeoFeature represents a route from one location to another along a
 * single geographic feature. GeoFeatures are immutable.
 * <p>
 * GeoFeature abstracts over a sequence of GeoSegments, all of which have
 * the same name, thus providing a representation for nonlzinear or nonatomic
 * geographic features. As an example, a GeoFeature might represent the
 * course of a winding river, or travel along a road through intersections
 * but remaining on the same road.
 * <p>
 * GeoFeatures are immutable. New GeoFeatures can be constructed by adding
 * a segment to the end of a GeoFeature. An added segment must be properly
 * oriented; that is, its p1 field must correspond to the end of the original
 * GeoFeature, and its p2 field corresponds to the end of the new GeoFeature,
 * and the name of the GeoSegment being added must match the name of the
 * existing GeoFeature.
 * <p>
 * Because a GeoFeature is not necessarily straight, its length - the
 * distance traveled by following the path from start to end - is not
 * necessarily the same as the distance along a straight line between
 * its endpoints.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint       // location of the start of the geographic feature
 *   end : GeoPoint         // location of the end of the geographic feature
 *   startHeading : angle   // direction of travel at the start of the geographic feature, in degrees
 *   endHeading : angle     // direction of travel at the end of the geographic feature, in degrees
 *   geoSegments : sequence	// a sequence of segments that make up this geographic feature
 *   name : String          // name of geographic feature
 *   length : real          // total length of the geographic feature, in kilometers
 * </pre>
 **/

public class GeoFeature implements Iterable<GeoSegment>{
	
	// Implementation hint:
	// When asked to return an Iterator, consider using the iterator() method
	// in the List interface. Two nice classes that implement the List
	// interface are ArrayList and LinkedList. If comparing two Lists for
	// equality is needed, consider using the equals() method of List. More
	// info can be found at:
	//   http://docs.oracle.com/javase/8/docs/api/java/util/List.html
	
	/*abstraction function
	 * geoFeature represents sequence of segments with the same name , and actions on them
	 * start - a location point of the start of the geographic feature
	 * end - a location point of the end of the geographic feautre .
	 * startHeading - angle of the start .
	 * endHeading - angle of the end .
	 * geoSegments - sequence of all the geoSegments of the geoFeature
	 * name - name of the geographic feature . 
	 * length - total length of the feature .
	 */ 
	
	private final GeoPoint start ; 
	private final GeoPoint end ; 
	private final double startHeading ;
	private final double endHeading ;
	private final LinkedList<GeoSegment> segmenetsSequence;
	private final String name ;
	private final double length ; 
	
	/*
	 * representation function :
	 * all geoPoints are not null
	 * segmenetsSequence is not null
	 * all the segments belong to the same feature (has the same name) 
	 * segmenetsSequence[i].point 2 == segmenetsSequence[i+1].point 1
	 * all angles between 0 and 360
	 * length is >= 0 
	 * name is not null 
	 */
	void checkRep() {
  		assert (this.start != null) : "start is  null" ; 
  		assert (this.end != null)   : "end is  null" ; 
  		assert (this.startHeading >= 0 && this.startHeading < 360) : "Invalid start angle " ; 
  		assert (this.endHeading >= 0 && this.endHeading < 360) : "Invalid end angle " ; 
  		assert (this.segmenetsSequence != null) : "segmenetsSequence is null" ; 
  		for (int i = 0;i< this.length; i++ ) {
  			assert (segmenetsSequence.get(i) != null) : "GeoSegment is null" ;
  			if (i!= this.length -1){
  				assert(segmenetsSequence.get(i).getP2().equals(segmenetsSequence.get(i+1).getP1())) : "the segments are not connected";
  			}
  		}
  		assert (this.name != null) : "name is  null" ; 
  		assert (this.length >= 0) : "length is negative" ; 
	}
	
	/**
     * Constructs a new GeoFeature.
     * @requires gs != null
     * @effects Constructs a new GeoFeature, r, such that
     *	        r.name = gs.name &amp;&amp;
     *          r.startHeading = gs.heading &amp;&amp;
     *          r.endHeading = gs.heading &amp;&amp;
     *          r.start = gs.p1 &amp;&amp;
     *          r.end = gs.p2
	 * @param gs - the first geoSegment that created in geoFeature .
	 */
  	public GeoFeature(GeoSegment gs) {
  		GeoSegment newGS = new GeoSegment(gs.getName(),gs.getP1(),gs.getP2()) ;  		
  		this.start = newGS.getP1();
  		this.end = newGS.getP2();
  		this.startHeading = this.start.headingTo(end);
  		this.endHeading = this.start.headingTo(end);
  		this.segmenetsSequence = new LinkedList<GeoSegment>() ;  
  		segmenetsSequence.add(newGS);
  		this.name = newGS.getName();
  		this.length = newGS.getLength() ; 
  		checkRep();
  	}
  	
  	/**
  	 * Construct a new GeoFeature
  	 * @requires all argument != null ,  
  	 * @effects Constructs a new GeoFeature , given all the GeoFeature fields .
	 * @param start - first point of geoFeature.
	 * @param end - last point of geoFeature .
	 * @param startHeading - the heading in the begining of the Feature
	 * @param endHeading - the heading in the end of the Feature
	 * @param segmenetsSequence - list of the segments in the Feature
	 * @param name - name of the geoFeature
	 * @param length - length in [meters] of the geoFeature . 
 */
  	public GeoFeature(GeoPoint start ,GeoPoint end , double startHeading , double endHeading ,
  			LinkedList<GeoSegment> segmenetsSequence , String name ,double length ) {
  		this.start = start ; 
  		this.end = end ;
  		this.startHeading = startHeading;
  		this.endHeading = endHeading;
  		this.segmenetsSequence = segmenetsSequence;
  		this.name = name ;
  		this.length = length ;
  		checkRep();
  	}

  		
  	
 	/**
 	  * Returns name of geographic feature
      * @return name of geographic feature
      */
  	public String getName() {
  		checkRep();
  		return (this.name);
  	}


  	/**
  	 * Returns location of the start of the geographic feature.
     * @return location of the start of the geographic feature.
     */
  	public GeoPoint getStart() {
  		checkRep();
  		GeoPoint newGeoPoint = new GeoPoint(this.start.getLatitude(),this.start.getLongitude());
  		checkRep();
  		return (newGeoPoint);
  	}


  	/**
  	 * Returns location of the end of the geographic feature.
     * @return location of the end of the geographic feature.
     */
  	public GeoPoint getEnd() {
  		checkRep();
  		GeoPoint newGeoPoint = new GeoPoint(this.end.getLatitude(),this.end.getLongitude());
  		checkRep();
  		return (newGeoPoint);
  	}


  	/**
  	 * Returns direction of travel at the start of the geographic feature.
     * @return direction (in standard heading) of travel at the start of the
     *         geographic feature, in degrees.
     */
  	public double getStartHeading() {
  		checkRep();
  		return this.startHeading;
  	}


  	/**
  	 * Returns direction of travel at the end of the geographic feature.
     * @return direction (in standard heading) of travel at the end of the
     *         geographic feature, in degrees.
     */
  	public double getEndHeading() {
  		checkRep();
  		return this.endHeading; 
  	}


  	/**
  	 * Returns total length of the geographic feature, in kilometers.
     * @return total length of the geographic feature, in kilometers.
     *         NOTE: this is NOT as-the-crow-flies, but rather the total
     *         distance required to traverse the geographic feature. These
     *         values are not necessarily equal.
     */
  	public double getLength() {
  		checkRep();
  		double length = 0 ;
  		for (GeoSegment seg : segmenetsSequence ) {
  			length += seg.getLength();
  		}  	
  		checkRep();
  		return length; 
  	}


  	/**
   	 * Creates a new GeoFeature that is equal to this GeoFeature with gs
   	 * appended to its end.
     * @requires gs != null &amp;&amp; gs.p1 = this.end &amp;&amp; gs.name = this.name.
     * @return a new GeoFeature r such that
     *         r.end = gs.p2 &amp;&amp;
     *         r.endHeading = gs.heading &amp;&amp;
     *    	   r.length = this.length + gs.length
  	 * @param gs - the segment which would be added to the geoFeature .
  	 */
  	public GeoFeature addSegment(GeoSegment gs) {
  		checkRep();
  		GeoSegment newGS = new GeoSegment(gs.getName(),gs.getP1(),gs.getP2()) ; 
  		// check last p2 point  = new gs p1 , check if the names equals 
  		assert (newGS.getP1().equals(this.end)) : "last p2 point != new gs p1" ; 
  		assert (newGS.getName().equals(this.name)) : "wrong name " ; 
  		//
  		GeoPoint newStart = new GeoPoint(this.start.getLatitude() ,this.start.getLongitude());
  		GeoPoint newEnd = new GeoPoint(newGS.getP2().getLatitude() ,newGS.getP2().getLongitude());
  		double newStartHeading = this.startHeading;
  		double newEndHeading = newGS.getHeading();
  		LinkedList<GeoSegment> newSegmenetsSequence = new LinkedList<GeoSegment>()  ;
  		for (GeoSegment seg : this.segmenetsSequence ) {
  			GeoSegment newGeoSegment = new GeoSegment(seg.getName(),seg.getP1(),seg.getP2()) ;
  			//
  			newSegmenetsSequence.add(newGeoSegment);
  		}
  		newSegmenetsSequence.add(newGS);
  		double newLength = this.length + gs.getLength() ; 
  		checkRep();
  		GeoFeature newGF = new GeoFeature(newStart ,newEnd, newStartHeading , newEndHeading ,
  				newSegmenetsSequence , this.name ,newLength) ; 
  		return newGF;
  		
  	}
  	


	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this GeoFeature. All the
     * GeoSegments have the same name.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &amp;&amp;
     *      this.startHeading = a[0].heading &amp;&amp;
     *      this.end          = a[a.length - 1].p2 &amp;&amp;
     *      this.endHeading   = a[a.length - 1].heading &amp;&amp;
     *      this.length       = sum(0 {@literal <}= i {@literal <} a.length) . a[i].length &amp;&amp;
     *      for all integers i
     *          (0 {@literal <}= i {@literal >} a.length-1 ={@literal >} (a[i].name == a[i+1].name &amp;&amp;
     *                                   a[i].p2d  == a[i+1].p1))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     */
  	
  	public Iterator<GeoSegment> getGeoSegments() {
  		checkRep();
  		return this.iterator();
  	}


  	/**
     * Compares the argument with this GeoFeature for equality.
     * @return o != null &amp;&amp; (o instanceof GeoFeature) &amp;&amp;
     *         (o.geoSegments and this.geoSegments contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
  		checkRep();
  		boolean condA = (o != null) ; 
  		boolean condB = (o instanceof GeoFeature);
  		boolean condC = false ;
  		if(condB == true && (this.length == ((GeoFeature)o).getLength())) { // FIXME
  			for (int i = 0; i< this.segmenetsSequence.size(); i++) {
  				Iterator<GeoSegment> innerIteratorGS = ((GeoFeature)o).getGeoSegments();
  				if (innerIteratorGS.hasNext()) {
  	  				condC = (innerIteratorGS.next().equals(this.segmenetsSequence.get(i)));
  	  	  			if (condC == false) {
  	  	  				break;
  	  	  			}	
  				}
  			}
  		}
  		checkRep();
  		return (condA && condB && condC) ;
  	}


  	/**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
  	public int hashCode() {
    	// This implementation will work, but you may want to modify it
    	// improved performance.
  		checkRep();
  		int value = 0;
  		for (GeoSegment seg : segmenetsSequence ) {
  			value += seg.hashCode();
  		}
  		checkRep();
    	return value;
  	}


  	/**
  	 * Returns a string representation of this.
   	 * @return a string representation of this.
     **/
  	public String toString() {
  		checkRep();
  		String geoFeatureName = this.getName();
  		String stringStart = this.start.toString();
  		String stringend = this.end.toString();
  		checkRep();
  		return ("(Feature is:\""+geoFeatureName+"\",start from:"+stringStart+",end at:"+stringend+")");
  	}

	@Override
	public Iterator<GeoSegment> iterator() {
		return new GeoSegmentIterator(this.segmenetsSequence, this.segmenetsSequence.size());
	}
}
