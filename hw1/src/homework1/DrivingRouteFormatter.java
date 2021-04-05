package homework1;

import java.text.*;

/**
 * The DrivingDirections class creates a textual description of directions
 * for traversing a route that are suitable for a driver of a vehicle.
 * <p>
 * Calling <code>computeDirections</code> should produce directions in the
 * following form:
 * </p>
 * <code>
 * Turn slight right onto Hankin Road and go 0.1 kilometers.<br>
 * Turn slight right onto Trumpeldor Avenue and go 0.7 kilometers.<br>
 * Turn left onto Hagalil and go 1.4 kilometers.<br>
 * Turn sharp left onto Hanita and go 1.4 kilometers.<br>
 * </code>
 * <p>
 * Each line should correspond to a single geographic feature of the route.
 * In the first line, "Hankin" is the name of the first
 * geographic feature of the route, and "0.1 kilometers" is the length of
 * the geographic feature. The length should be reported to tenth-of-a-
 * kilometer precision. Each line should be terminated by a newline and
 * should include no extra spaces other than those shown above.
 * </p>
 */
public class DrivingRouteFormatter extends RouteFormatter {
  
  	/**
  	 * <p>
     * Computes a single line of a multi-line directions String that
     * represents the instructions for traversing a single geographic
     * feature.
     * @requires 0 {@literal <}= origHeading {@literal <} 360
     * @param geoFeature the geographical feature to traverse.
     * @param origHeading the initial heading.
     * @return A newline-terminated <code>String</code> that gives
     * 		   directions on how to traverse this geographical feature.<br>
     * Calling <code>computeLine</code> with a GeoFeature instance and an
     * initial heading should produce a newline-terminated String in the
     * following form:
     * </p>
     * 
     * <p>
     * <code>
     * Turn sharp left onto Hanita and go 1.4 kilometers.<br>
     * </code>
     * </p>
     * In the output above, "Hanita" represents the name of the
     * geographic feature, and "1.4 kilometers" is the length of the
     * geographic feature. The length should be reported to
     * tenth-of-a-kilometer precision. The String should be terminated by a
     * newline and should include no extra spaces other than those shown
     * above.
     **/
  	public String computeLine(GeoFeature geoFeature, double origHeading) {	
  		// Implementation hint:
		// You may find the class java.text.DecimalFormat useful when
		// implementing this method. More info can be found at:
  	    // http://docs.oracle.com/javase/tutorial/java/data/numberformat.html
  		// and at:
  		// http://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html
		String turnDirection = getTurnString(origHeading, geoFeature.getStartHeading());  
  		DecimalFormat decimalNumber = new DecimalFormat("#.#");
		String decimalLength = decimalNumber.format(geoFeature.getLength());
		return turnDirection.concat(" onto ").concat(geoFeature.getName()).concat(" and go ").concat(decimalLength).concat(" kilometers.\n") ;
  	}

}
