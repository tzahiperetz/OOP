package homework1;

import java.util.Iterator;

public interface Myinterface {
	/**
	 * 
	 * @return iterator on geoSegment
	 */
	Iterator<GeoSegment> SegmentIterator();
	/**
	 * 
	 * @return iterator on geoFeature
	 */
	Iterator<GeoFeature> FeatureIterator();
}
