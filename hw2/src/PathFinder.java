package homework2;


import java.util.*;

/**
 * PathFinder is a class that enable finding a shortest path between nodes
 */
public class PathFinder<N, P extends Path<N, P> >  {
	/**
	 *  finds the shortest paths from given list of starting nodes and goal nodes 
	 *  shortest paths calculated in terms of the nodes cost .
	 *  @requires two groups(start+goal) of degenerated paths that represent nodes .
	 *  the nodes must exist in the graph  
	 * @param <N> node 
	 * @param <P> path
	 * @param graph - given graph
	 * @param starts - set of given start degenrated paths(node)
	 * @param goals -  set of given ending degenrated paths(node)
	 * @throws IllegalArgumentException - if one of the inputs is null
	 * @throws ClassNotFoundException - if wrong class is used 
	 * @return a path - shortest path that starts with one of the given starting node ,and finished
	 *  in one of the goal node .
	 */
	public static<N, P extends Path<N,P>> Path<N, P>
		findShortestPath(Graph<N> graph, Set<Path<N, P>> starts, Set<Path<N, P>> goals) 
		throws  IllegalArgumentException, ClassNotFoundException {
		
		if(graph == null)
			throw new IllegalArgumentException("graph is null");
		if(starts == null)
			throw new IllegalArgumentException("starts is null");
		if(goals == null)
			throw new IllegalArgumentException("goals is null");

		// The priority queue contains nodes with priority equal to the cost 
		// of the shortest path to reach that node
		// initiated with starts nodes
	    Queue<Path<N,P>> active = new PriorityQueue<Path<N, P>>();
	    active.addAll(starts);
	    Set<N> finished = new HashSet<N>();
	    
	    while (!active.isEmpty()) {
	        Path<N, P> queueMin = active.remove();	        
	        // if arrived here , it is guranteed that this is the absolute shortest path
	        // from any start or point - due to the priorityQueue
	        for (Path<N, P> goal :goals ){
		        if (goal.getEnd().equals(queueMin.getEnd())) 
		            return queueMin;
	        }
	        
	        // loop over all the children of the last node 
	        for(N c : graph.ListChildren(queueMin.getEnd())) {
	        	Path<N,P> cpath = queueMin.extend(c);
		        if ((finished.contains(c) == false) && (active.contains(cpath) == false)) {
		            active.add(cpath); 
		        }
		    }

		    finished.add(queueMin.getEnd());		    
	    }
	    // if arrived here , no valid path found 
		return null; 
    }
}