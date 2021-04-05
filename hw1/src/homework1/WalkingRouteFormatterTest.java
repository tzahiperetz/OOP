package homework1;

public class WalkingRouteFormatterTest {
	
	private WalkingRouteFormatter mDirections;
  	private Route mShortRoute;

  
  	public WalkingRouteFormatterTest() {
    	mDirections = new WalkingRouteFormatter();
    	mShortRoute = new Route(new GeoSegment("Hankin Road",
    			new GeoPoint(32782269,35013820), new GeoPoint(32783098,35014528)));
    	mShortRoute = mShortRoute.addSegment(new GeoSegment("Trumpeldor Avenue",
    			new GeoPoint(32783098,35014528), new GeoPoint(32785295,35017833)));
       	mShortRoute = mShortRoute.addSegment(new GeoSegment("Trumpeldor Avenue",
    			new GeoPoint(32785295,35017833), new GeoPoint(32787081,35020735)));
       	mShortRoute = mShortRoute.addSegment(new GeoSegment("Hagalil Avenue",
    			new GeoPoint(32787081,35020735), new GeoPoint(32789768,35018578)));
       	mShortRoute = mShortRoute.addSegment(new GeoSegment("Hagalil Avenue",
    			new GeoPoint(32789768,35018578), new GeoPoint(32795631,35010296)));
  	}
  	
  	
  	public void test() {
		String directions =
			"Turn slight right onto Trumpeldor Avenue and walk for 15 minutes.\n" +
    		"Turn left onto Hagalil and walk for 27 minutes.\n";
		
		System.out.println(mDirections.computeDirections(mShortRoute, 0));

		if (mDirections.computeDirections(mShortRoute, 0).equals(directions))
			System.out.println("Test passed correctly");
		else
			System.out.println("Test not passed correctly");
	}
  	
  	
	public static void main(String[] args) {
		WalkingRouteFormatterTest directionsTest = new WalkingRouteFormatterTest();
		directionsTest.test();
	}
}
/*
 * 
 		new GeoPoint(32787081,35020735),	//  3. Trumpeldor Avenue & Hagalil Intersection
		new GeoPoint(32789768,35018578),	//  4. Water Tower (on Hagalil)
		new GeoPoint(32795631,35010296),	//  5. Hagalil & Hanita Intersection
		
new GeoPoint(32782269,35013820),	//  0. Hankin Road, Natan Komoi & A.D. Gordon Intersection
new GeoPoint(32783098,35014528),	//  1. Ziv Square (Hankin Road, Trumpledor Avenue, Shalom Aleichem & Berl Intersection)
new GeoPoint(32785295,35017833),	//  2. Trumpeldor Avenue & Hanita Intersection
new GeoPoint(32787081,35020735),	//  3. Trumpeldor Avenue & Hagalil Intersection
*/