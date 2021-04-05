package homework1;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * the iteration on GeoFeature list 
 * 
 * representation invaraiant :
 * length > 0 
 * it_list != null 
 */
public class GeoFeatureIterator implements Iterator<GeoFeature> {
	
	private double length;
	private LinkedList<GeoFeature> it_list;
	private int index;
	
	GeoFeatureIterator(LinkedList<GeoFeature> list, double length){
		this.length = length;
		it_list = new LinkedList<GeoFeature>();
		for(GeoFeature Feature : list ) {
			it_list.add(Feature);
		}
		this.index = -1;
		
	}

	@Override
	public boolean hasNext() {
		if (index+1 < length)
			return true;
		else return false;
	}

	@Override
	public GeoFeature next() {
		return it_list.get(++index);
	}
	

}
