package homework2;

import java.util.Iterator;

/**
 * A Path models a sequence of nodes and the cost for traveling along
 * such a sequence. Paths are immutable.
 * <p>
 * The cost of traversing a path must not decrease as the path is
 * extended with new nodes.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   cost : double              // cost of traversing this Path
 *   elements : sequence        // the nodes in this Path
 * </pre>
 * 
 * <p>
 * The first generic argument (N) is the type of nodes in the path.
 * The second generic argument ( P ) should be the name of the
 * implementing class itself (see WeightedNodePath for an example). Why is this
 * second argument necessary? Imagine that this interface was defined as
 *  public interface Path&lt;N&gt; . Then the  extend 
 * function would be defined as returning a  Path&lt;N&gt; . But this
 * is not specific enough; for example, the extend method on WeightedNodePath
 * could return a NodeCountingPath, or vice versa! The second generic
 * argument lets us force the implementing class to define an extend method that
 * returns an element of the same type.
 * </p>
 */
public interface Path<N, P extends Path<N,P>>
			extends Iterable<N>, Comparable<Path<?,?>>  {

	/**
     * Creates an extended path by adding a new node to its end.
     * @requires n != null and 
     *           n is a valid node type for this particular path 
	 *	 		 implementation
     * @return a new Path p such that
     *	         p.elements == this.elements + [n] and 
     *           p.cost >= this.cost
	 * @param n - node
	 */
	public P extend(N n);

  
    /**
     * Returns this.cost.
     * @return this.cost
     */
	public double getCost();
 
	
    /**
     * Returns the end of the path.
     * @return the end of the path
     */
	public N getEnd();
    
    
    /**
     * Returns an Iterator over the elements in the path .
     * @return an Iterator over the elements in the path in order from start
     *         to end.
     */

	public Iterator<N> iterator();

}
