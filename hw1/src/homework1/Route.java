package homework1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A Route is a path that traverses arbitrary GeoSegments, regardless
 * of their names.
 * <p>
 * Routes are immutable. New Routes can be constructed by adding a segment 
 * to the end of a Route. An added segment must be properly oriented; that 
 * is, its p1 field must correspond to the end of the original Route, and
 * its p2 field corresponds to the end of the new Route.
 * <p>
 * Because a Route is not necessarily straight, its length - the distance
 * traveled by following the path from start to end - is not necessarily
 * the same as the distance along a straight line between its endpoints.
 * <p>
 * Lastly, a Route may be viewed as a sequence of geographical features,
 * using the <code>getGeoFeatures()</code> method which returns an Iterator of
 * GeoFeature objects.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint            // location of the start of the route
 *   end : GeoPoint              // location of the end of the route
 *   startHeading : angle        // direction of travel at the start of the route, in degrees
 *   endHeading : angle          // direction of travel at the end of the route, in degrees
 *   geoFeatures : sequence      // a sequence of geographic features that make up this Route
 *   geoSegments : sequence      // a sequence of segments that make up this Route
 *   length : real               // total length of the route, in kilometers
 *   endingGeoSegment : GeoSegment  // last GeoSegment of the route
 * </pre>
 **/

public class Route implements Myinterface{

	
 	// TODO Write abstraction function and representation invariant
	/* abstraction function:
	 * start is the start point of the route
	 * end is the end point of the route
	 * startheading is the angle of the first segment
	 * endheading is the angle of the last segment
	 * featureSequence is the list of the Features in the route
	 * GeoSegmentSequence is the list of the Geosegments in the route
	 * length is the total lenge of all the segments in the route
	 * endSegment is the last GeoSegment of the route
	 */

	private  final GeoPoint start;
	private final GeoPoint end;
	private final double startHeading;
	private final double endHeading;
	private final LinkedList<GeoFeature> featureSequence;
	private final LinkedList<GeoSegment> GeoSegmentSequence;
	private final double length;
	private final GeoSegment endSegment;
	
	/* representation invariant:
	 * all geoPoints are not null
	 * all angles between 0 and 360
	 * GeoSegmentSequence[i].point 2 == GeoSegmentSequence[i+1].point 1
	 * length is non negative
	 * endendSegment is the last segment in the list and not null
	 * the list is valid and not null ptr.
	 */
	
	void checkRep() {
  		assert (this.start != null) : "start is null" ; 
  		assert (this.end != null)   : "end is null" ; 
  		assert (this.startHeading >= 0 && this.startHeading < 360) : "Invalid start angle " ; 
  		assert (this.endHeading >= 0 && this.endHeading < 360) : "Invalid end angle " ; 
  		assert (this.featureSequence != null) : "featureSequence is null" ; 
  		assert (this.GeoSegmentSequence != null) : "GeoSegmentSequence is null" ; 
  		assert (this.length >= 0) : "length is negative" ; 
  		for (int i = 0;i< this.length; i++ ) {
  			assert (GeoSegmentSequence.get(i) != null) : "GeoSegment is null" ;
  			if (i!= this.length -1){
  				assert(GeoSegmentSequence.get(i).getP2().equals(GeoSegmentSequence.get(i+1).getP1())) : "the segments are not connected";
  			}
  			else {
  				assert(GeoSegmentSequence.get(i).equals(endSegment)):"the last segment in the list is not the same like endSegment";
  			}
  		} 
	}
	
	
  	/**
  	 * Constructs a new Route.
     * @requires gs != null
     * @effects Constructs a new Route, r, such that
     *	        r.startHeading = gs.heading &amp;&amp;
     *          r.endHeading = gs.heading &amp;&amp;
     *          r.start = gs.p1 &amp;&amp;
     *          r.end = gs.p2
	 * @param gs - the first geoSegment in the route .
	 */
  	public Route(GeoSegment gs) {
  		GeoSegment NewGeoSegment = new GeoSegment(gs.getName(),gs.getP1(),gs.getP2());
  		GeoFeature NewGeoFeature = new GeoFeature(NewGeoSegment);
  		this.start = new GeoPoint(NewGeoSegment.getP1().getLatitude(),NewGeoSegment.getP1().getLongitude());
  		this.end = new GeoPoint(NewGeoSegment.getP2().getLatitude(),NewGeoSegment.getP2().getLongitude());
  		this.startHeading = NewGeoSegment.getHeading();
  		this.endHeading = NewGeoSegment.getHeading();
  		this.featureSequence = new LinkedList<GeoFeature>() ; 
  		featureSequence.add(NewGeoFeature);
  		this.GeoSegmentSequence = new LinkedList<GeoSegment>() ;  
  		GeoSegmentSequence.add(NewGeoSegment);
  		this.length = NewGeoSegment.getLength();
  		this.endSegment = NewGeoSegment;
  		checkRep();
  	}
	/**
	 * Constructs a new Route.
	 * @requires newStart,newEnd ,newFeatureSequence ,newSegmenetsSequence ,newEndSegment != null
	 *			 newLength greater or equal 0 
	 *			 newStartHeading and newEndHeading between 0 and 360
	 * @effects Constructs a new Route, r, such that
	 *	        r.startHeading = newStartHeading &amp;&amp;
	 *          r.endHeading = newEndHeading &amp;&amp;
	 *          r.start = newStart &amp;&amp;
	 *          r.end = newEnd &amp;&amp;
	 *          r.featureSequence = newFeatureSequence &amp;&amp;
	 *          r.GeoSegmentSequence = newSegmenetsSequence &amp;&amp;
	 *          r.length = newLength &amp;&amp;
	 * @param newStart - first point of the route
	 * @param newEnd - last point of the route 
	 * @param newStartHeading - the heading in the begining of the route 
	 * @param newEndHeading -the heading in the end of the route 
	 * @param newFeatureSequence - holds all the features in the route 
	 * @param newSegmenetsSequence - holds all the segments in the route  
	 * @param newLength - route length
	 * @param newEndSegment - the last segment in the route . 
	 * 
	 */
  	public Route(GeoPoint newStart, GeoPoint newEnd, double newStartHeading, double newEndHeading ,
  			LinkedList<GeoFeature> newFeatureSequence, LinkedList<GeoSegment> newSegmenetsSequence, double newLength, GeoSegment newEndSegment) {
  		this.start = newStart;
  		this.end = newEnd;
  		this.startHeading = newStartHeading;
  		this.endHeading = newEndHeading;
  		this.featureSequence = newFeatureSequence;
  		this.GeoSegmentSequence = newSegmenetsSequence;
  		this.length = newLength;
  		this.endSegment = newEndSegment;
  		checkRep();
  	}

    /**
     * Returns location of the start of the route.
     * @return location of the start of the route.
     **/
  	public GeoPoint getStart() {
  		checkRep();
  		return new GeoPoint(this.start.getLatitude(),this.start.getLongitude());
  	}

  	/**
  	 * Returns location of the end of the route.
     * @return location of the end of the route.
     **/
  	public GeoPoint getEnd() {
  		checkRep();
  		return new GeoPoint(this.end.getLatitude(),this.end.getLongitude());
  	}


  	/**
  	 * Returns direction of travel at the start of the route, in degrees.
   	 * @return direction (in compass heading) of travel at the start of the
   	 *         route, in degrees.
   	 **/
  	public double getStartHeading() {
  		checkRep();
  		return this.startHeading;
  	}


  	/**
  	 * Returns direction of travel at the end of the route, in degrees.
     * @return direction (in compass heading) of travel at the end of the
     *         route, in degrees.
     **/
  	public double getEndHeading() {
  		checkRep();
  		return this.endHeading;
  	}


  	/**
  	 * Returns total length of the route.
     * @return total length of the route, in kilometers.  NOTE: this is NOT
     *         as-the-crow-flies, but rather the total distance required to
     *         traverse the route. These values are not necessarily equal.
   	 **/
  	public double getLength() {
  		checkRep();
  		return this.length;
  	}


  	/**
     * Creates a new route that is equal to this route with gs appended to
     * its end.
   	 * @requires gs != null &amp;&amp; gs.p1 == this.end
     * @return a new Route r such that
     *         r.end = gs.p2 &amp;&amp;
     *         r.endHeading = gs.heading &amp;&amp;
     *         r.length = this.length + gs.length
  	 * @param gs - the geoSegment that going to add .
  	 */
  	public Route addSegment(GeoSegment gs) {
  		checkRep();
  		GeoSegment newGS = new GeoSegment(gs.getName(),gs.getP1(),gs.getP2()) ; // end segment
  		GeoFeature newGeoFeature = new GeoFeature(newGS); // nead only if the last FT is diffrent
  		GeoPoint newStart = new GeoPoint(this.getStart().getLatitude() ,this.getStart().getLongitude());
  		GeoPoint newEnd = new GeoPoint(newGS.getP2().getLatitude() ,newGS.getP2().getLongitude());
  		double newLength = this.length + newGS.getLength();
  		
  		double newStartHeading = this.startHeading;
  		double newEndHeading = newGS.getHeading();
  		GeoSegment newEndSegment = newGS;
  		
  		// create a copy for segments list
  		LinkedList<GeoSegment> newSegmenetsSequence = new LinkedList<GeoSegment>();
  		Iterator<GeoSegment> segmentsIterator = this.getGeoSegments();
  		while(segmentsIterator.hasNext()) {
  			GeoSegment temGeoSegment = segmentsIterator.next();
  			GeoSegment newGeoSegment = new GeoSegment(temGeoSegment.getName(),temGeoSegment.getP1(),temGeoSegment.getP2()) ;
  			newSegmenetsSequence.add(newGeoSegment);
  		}
  		newSegmenetsSequence.add(newGS);
  		// create a copy for features list
  		LinkedList<GeoFeature> newFeatureSequence = new LinkedList<GeoFeature>();
  		Iterator<GeoFeature> featuresIterator = this.getGeoFeatures();
  		while(featuresIterator.hasNext()) {
  			GeoFeature temGeoFeature = featuresIterator.next();
  			//all the features except the last
  			if(featuresIterator.hasNext()) {
  				LinkedList<GeoSegment> newInnerSegmenetsSequence = new LinkedList<GeoSegment>();
  				Iterator<GeoSegment> innerSegmentsIterator = temGeoFeature.getGeoSegments();
  				while(innerSegmentsIterator.hasNext()) {
  					GeoSegment temGeoSegment = innerSegmentsIterator.next();
  					GeoSegment newGeoSegment = new GeoSegment(temGeoSegment.getName(),temGeoSegment.getP1(),temGeoSegment.getP2()) ;
  					newInnerSegmenetsSequence.add(newGeoSegment);
  				}
  				GeoFeature newGeoFeatureIter = new GeoFeature(temGeoFeature.getStart(),temGeoFeature.getEnd(),temGeoFeature.getStartHeading(),temGeoFeature.getEndHeading(),newInnerSegmenetsSequence,temGeoFeature.getName(),temGeoFeature.getLength());
  				newFeatureSequence.add(newGeoFeatureIter);
  	  		}
  			// last feature
  			else {
  				if (newGS.getName().equals(temGeoFeature.getName())) {
  					GeoFeature lastFeature = temGeoFeature.addSegment(newGS);
  					newFeatureSequence.add(lastFeature);
  				}
  				else {
  					LinkedList<GeoSegment> newInnerSegmenetsSequence = new LinkedList<GeoSegment>();
  					Iterator<GeoSegment> innerSegmentsIterator = temGeoFeature.getGeoSegments();
  	  				while(innerSegmentsIterator.hasNext()) {
  	  					GeoSegment temGeoSegment = innerSegmentsIterator.next();
  	  					GeoSegment newGeoSegment = new GeoSegment(temGeoSegment.getName(),temGeoSegment.getP1(),temGeoSegment.getP2()) ;
  	  					newInnerSegmenetsSequence.add(newGeoSegment);
  	  				}
  	  				GeoFeature newGeoFeaturelast = new GeoFeature(temGeoFeature.getStart(),temGeoFeature.getEnd(),temGeoFeature.getStartHeading(),temGeoFeature.getEndHeading(),newInnerSegmenetsSequence,temGeoFeature.getName(),temGeoFeature.getLength());
  	  				newFeatureSequence.add(newGeoFeaturelast);
  	  				newFeatureSequence.add(newGeoFeature);
  				}
  			}
  		}
  		checkRep();
  		return new Route(newStart,newEnd,newStartHeading,newEndHeading,newFeatureSequence,newSegmenetsSequence,newLength,newEndSegment);
  	}


    /**
     * Returns an Iterator of GeoFeature objects. The concatenation
     * of the GeoFeatures, in order, is equivalent to this route. No two
     * consecutive GeoFeature objects have the same name.
     * @return an Iterator of GeoFeatures such that
     * <pre>
     *      this.start        = a[0].start &amp;&amp;
     *      this.startHeading = a[0].startHeading &amp;&amp;
     *      this.end          = a[a.length - 1].end &amp;&amp;
     *      this.endHeading   = a[a.length - 1].endHeading &amp;&amp;
     *      this.length       = sum(0 {@literal <}= i {@literal <} a.length) . a[i].length &amp;&amp;
     *      for all integers i
     *          (0 {@literal <}= i {@literal <} a.length - 1 ={@literal >} (a[i].name != a[i+1].name &amp;&amp;
     *                                     a[i].end  == a[i+1].start))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoFeature
     **/
  	public Iterator<GeoFeature> getGeoFeatures() {
  		return FeatureIterator();
  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this route.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &amp;&amp;
     *      this.startHeading = a[0].heading &amp;&amp;
     *      this.end          = a[a.length - 1].p2 &amp;&amp;
     *      this.endHeading   = a[a.length - 1].heading &amp;&amp;
     *      this.length       = sum (0 {@literal <}= i {@literal <} a.length) . a[i].length
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     **/
  	public Iterator<GeoSegment> getGeoSegments() {
  		return SegmentIterator();
  	}


  	/**
     * Compares the specified Object with this Route for equality.
     * @return true iff (o instanceof Route) &amp;&amp;
     *         (o.\ and this.geoFeatures contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
  		checkRep();
  		boolean condA = (o != null) ; 
  		boolean condB = (o instanceof Route);
  		boolean condC = false ;
  		if(condB == true && (this.length == ((Route)o).getLength())) {
  			for (int i = 0; i< this.length; i++) {
  				Iterator<GeoFeature> iteratorFT = this.getGeoFeatures();
  				if (iteratorFT.hasNext()) {
  	  				condC = (iteratorFT.next().equals(this.featureSequence.get(i)));
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
  		checkRep();
  		int value = 0;
  		for (GeoFeature feature : this.featureSequence ) {
  			value += feature.hashCode();
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
  		return ("(Route - start from:"+this.start.toString()+",end at:"+this.end.toString()+")");
  	}


	@Override
	public Iterator<GeoSegment> SegmentIterator() {
		return new GeoSegmentIterator(this.GeoSegmentSequence, this.GeoSegmentSequence.size());
	}


	@Override
	public Iterator<GeoFeature> FeatureIterator() {
		return new GeoFeatureIterator(this.featureSequence,this.featureSequence.size());
	}



}
