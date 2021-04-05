package homework1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A RouteFormatter class knows how to create a textual description of
 * directions from one location to another. The class is abstract to
 * support different textual descriptions.
 */
public abstract class RouteFormatter {

  	/**
     * Give directions for following this Route, starting at its start point
     * and facing in the specified heading.
     * @requires route != null &amp;&amp;
     * 			0 {@literal <}= heading {@literal <} 360
     * @param route the route for which to print directions.
   	 * @param heading the initial heading.
     * @return A newline-terminated directions <code>String</code> giving
     * 	       human-readable directions from start to end along this route.
     **/
  	public String computeDirections(Route route, double heading) {
  		// Implementation hint:
		// This method should call computeLine() for each geographic
		// feature in this route and concatenate the results into a single
		// String.
  		LinkedList<GeoSegment> newGeoSementList= new LinkedList<GeoSegment>() ;
  		LinkedList<GeoFeature> newGeoFeatureList= new LinkedList<GeoFeature>() ;
  		//creates segmentsLost.
  		Iterator<GeoSegment> iteratorGS = route.getGeoSegments();
  		while (iteratorGS.hasNext()) {
  			GeoSegment tmpGS = iteratorGS.next();
  			GeoSegment newGeoSegment = new GeoSegment(tmpGS.getName(),tmpGS.getP1(),tmpGS.getP2());
  			newGeoSementList.add(newGeoSegment);
  		}
  		
  		//Creates Features list . each feature require its Segment sequence . 
  		Iterator<GeoFeature> iteratorFT = route.getGeoFeatures();
  		while (iteratorFT.hasNext()) {
  			GeoFeature tmpFeature = iteratorFT.next();
  	  		LinkedList<GeoSegment> tmpGeoSementList= new LinkedList<GeoSegment>() ;
  	  		Iterator<GeoSegment> innerIteratorGS = tmpFeature.getGeoSegments();
  	  		while (innerIteratorGS.hasNext()) {
  	  			GeoSegment tmpGS = innerIteratorGS.next();
  	  			GeoSegment newGeoSegment = new GeoSegment(tmpGS.getName(),tmpGS.getP1(),tmpGS.getP2());
  	  			tmpGeoSementList.add(newGeoSegment);
  	  		}

  			GeoFeature newGeoFeature = new GeoFeature(tmpFeature.getStart(),tmpFeature.getEnd(),
  					tmpFeature.getStartHeading(),tmpFeature.getEndHeading(),tmpGeoSementList ,tmpFeature.getName(),
  					tmpFeature.getLength());
  			newGeoFeatureList.add(newGeoFeature);
  		}
  		Route newRoute = new Route(route.getStart(),route.getEnd(),route.getStartHeading(),route.getEndHeading(),newGeoFeatureList,
  				newGeoSementList,route.getLength(),newGeoSementList.getLast());
  		
  		
  		String finalStr = ""; 
  		double currentHeading = heading; 
  		Iterator<GeoFeature> iteratorDirections = newRoute.getGeoFeatures();
  		while(iteratorDirections.hasNext()) {
  			GeoFeature tmpFeature = iteratorDirections.next();
  			String tmpStr = computeLine(tmpFeature,currentHeading);
  			String tem = finalStr.concat(tmpStr);
  			finalStr = tem;
  			currentHeading = tmpFeature.getEndHeading();	
  		}
  	//	System.out.println(finalStr);
  		return finalStr;
  	}
  	
  	
  	/**
     * Computes a single line of a multi-line directions String that
     * represents the instructions for traversing a single geographic
     * feature.
     * @requires geoFeature != null
     * @param geoFeature the geographical feature to traverse.
   	 * @param origHeading the initial heading.
     * @return A newline-terminated <code>String</code> that gives directions
     * 		   on how to traverse this geographic feature.
     */
  	public abstract String computeLine(GeoFeature geoFeature, double origHeading);


  	/**
     * Computes directions to turn based on the heading change.
     * @requires 0 <= oldHeading < 360 &&
     *           0 <= newHeading < 360
     * @param origHeading the start heading.
   	 * @param newHeading the desired new heading.
     * @return English directions to go from the old heading to the new
     * 		   one. Let the angle from the original heading to the new
     * 		   heading be a. The turn should be annotated as:
     * <p>
     * <pre>
     * Continue             if a < 10 
     * Turn slight right    if 10 <= a < 60 
     * Turn right           if 60 <= a < 120
     * Turn sharp right     if 120 <= a < 179
     * U-turn               if 179 <= a
     * Turn left 
     * 
     * </pre>
     * and likewise for left turns.
     */
  	protected String getTurnString(double origHeading, double newHeading) {
  		double direction = newHeading - origHeading;
  		if (direction<0) {
  			direction+=360;
  		}
  		if (direction == 360 ) {
  			direction = 0;
  		}
  		
  		String returnString ="wrong direction";
  		if ( ((0 <= direction) && (direction < 10)) || ((350 < direction) && direction < 360)){
  			returnString ="Continue" ;
  		}
  		if ( 60 > direction && direction >= 10) {
  			returnString ="Turn slight right";
  		}
  		if ( 120 > direction && direction >= 60) {
  			returnString = "Turn right";
  		}
  		if ( 179 > direction && direction >= 120) {
  			returnString = "Turn sharp right";
  		}
  		if ( (181 >= direction && direction >= 179)) {
  			returnString = "U-turn";
  		}
  		if ( 350 >= direction && direction > 300) {
  			returnString = "Turn slight left";
  		}
  		if ( 300 >= direction && direction > 240) {
  			returnString = "Turn left";
  		}
  		if ( 240 >= direction && direction > 181) {
  			returnString = "Turn sharp left";
  		}
  		return returnString;
  	}

}
