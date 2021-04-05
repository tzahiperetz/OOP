package homework1;

import java.util.Iterator;
import java.util.LinkedList;

public class GeoSegmentIterator implements Iterator<GeoSegment> {
	
	private double length;
	private LinkedList<GeoSegment> it_list;
	private int index;
	

	GeoSegmentIterator(LinkedList<GeoSegment> list, double length){
		this.length = length;
		this.it_list = new LinkedList<GeoSegment>();
		for(GeoSegment seg:list) {
			it_list.add(seg);
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
	public GeoSegment next() {
		return it_list.get(++index);
		
	}
	
}
