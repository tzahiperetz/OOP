package homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * A Graph is mutable collection of Nodes and Edges .
 * Nodes are the points in our graphs , and Edges are used to direct from two points .
 * Nodes are unique ,thus we cannot have same identical nodes in out graph. moreover ,
 * a Node can point to himself
 * we use directional edges . hence , edge from a->b doesn't means that b->a exist . 
 * Two edges from same source and destination are not allowed (for example two edges from a->b) 
 * 
 * Graph operations - 
 * 		Create an empty graph 
 * 		Adding edges.
 *		Adding nodes.
 *		Find path between two nodes.
 *		Print graph Nodes.
 *		Print children list of a given node.
 */



public class Graph<E> {
	/* abstraction function:
	 * we create mutable directed graph class, such that
	 * it allow to add nodes and edges to the graph
	 * 
	 * nodesMap holds the graph nodes .
	 * it maps between a particular element , to all the reachable children from it. 
	 * 
	 * the purpiose
	 */
	private Map<E,Set<E>> nodesMap;
	private String name;
	
	

	/** Representation Invariant
	 *  each element in the graph is unique .
	 *  in nodesMap all the keys are unique ,and in each corresponding set ,all the elements are unique .  
	 */
	private void checkRep(){
  	//	assert (this.nodesMap != null) : "nodesMap is null" ; 

		List<E> nodes = new LinkedList<E>();
		// nodes duplication chcek 
		for(E n : this.nodesMap.keySet()){
			nodes.add(n);
			if (nodes.size() == 1 ) 
				continue ;
			int counter = 0 ; 
			for (int i = 0 ; i < nodes.size();i=i+1)
			{
				if (n.equals(nodes.get(i)))  counter = counter+1 ;
				if( counter > 1) throw new IllegalArgumentException("error - duplications of nodes ");
			}
		}

		// edges duplication check 
		
		for(Set<E> edgesSet : this.nodesMap.values()){
			List<E> edgesTmpList = new LinkedList<E>();
			for (E e : edgesSet) {
				edgesTmpList.add(e);
				int counter = 0 ; 
				for  (int i =0 ; i<edgesTmpList.size() ; i++) {
					if (e.equals(edgesTmpList.get(i))) counter = counter+1 ;
					if( counter > 1) throw new IllegalArgumentException("error - duplications of nodes ");
				}
				
			}
		}
	}


	/**
	 * @requires - None
	 * @effects create a new graph
	 * @param name - the name of the graph
	 */
	public Graph(String name) {
		this.name=name;
		this.nodesMap = new HashMap<E,Set<E>>();
		checkRep();
	}
	/**
	 * @requires - None
	 * @effects adding a new node to graph if this node doesn't in the graph
	 * @param node - the node that going to add to the graph
	 * @throws IllegalArgumentException - if node is null 
	 * @throws ClassNotFoundException - for invalid node class
	 * @return true if addnode succeed - false otherwise
	 */
	public boolean AddNode(E node) throws IllegalArgumentException,ClassNotFoundException {	
		checkRep();
		if (node == null ) throw new IllegalArgumentException("node is null");
		if (!(node instanceof Object)) throw new ClassNotFoundException("Invalid class");
		if (this.nodesMap.containsKey(node)) return false;
		HashSet<E> newSet = new HashSet<E>();
		this.nodesMap.put(node,newSet);
		checkRep();
		return true;
	}
	/**
	 * @requires - None
	 * @effects adding a new edge between srcNode to dstNode if both are valid and exist in the graph
	 * @param srcNode - source Node of the edge
	 * @param dstNode - destination Node of the edge
	 * @throws NullPointerException - if srcNode or dstNode are null 
	 * @throws ClassNotFoundException - if the class of the Nodes is not defined .
	 * @throws IllegalArgumentException - if there is a mismatch between the inputs types.
	 *  true of addedge worked , false otherwise 
	 * @return true if add edge succeed - false otherwise

	 */
	public boolean AddEdge(E srcNode ,E dstNode) throws ClassNotFoundException,IllegalArgumentException {
		checkRep();
		if (srcNode == null ) throw new IllegalArgumentException("src Node is null");
		if (dstNode == null ) throw new IllegalArgumentException("dst Node is null");
		if (!(srcNode instanceof Object) || !(dstNode instanceof Object)) throw new ClassNotFoundException("Invalid class of src or dst Node");
	//	if (!(srcNode.getClass().equals(dstNode))) 
	//		throw new IllegalArgumentException("mismatch between source and destination types");
		if (!this.nodesMap.containsKey(srcNode) || !this.nodesMap.containsKey(dstNode)) return false;
		if (this.nodesMap.get(srcNode).contains(dstNode)) return false;
		checkRep();

		return this.nodesMap.get(srcNode).add(dstNode);
	}
	/**
	 * @requires - None
	 * @effects print all the nodes in the graph 
	 * @return - list of the nodes in graph
	 */
	public ArrayList<E> ListNodes() {
		checkRep();
		ArrayList<E> myList = new ArrayList<E>();
		myList.addAll(this.nodesMap.keySet());
		myList.sort(null);
		checkRep();
		return myList; 
	}
	/**
	 * @requires - None
	 * @effects adding a new node to graph if this node doesn't in the graph
	 * @param node - the node that going to add to the graph
	 * @throws NullPointerException - if Node or dstNode are null 
	 * @throws ClassNotFoundException - if the class of the Node is not defined .
	 * @return sorted list of the children of the given node 
	 */

	public ArrayList<E> ListChildren(E node) throws ClassNotFoundException,IllegalArgumentException {
		checkRep();
		if (node == null ) throw new IllegalArgumentException("node is null");
		if (!(node instanceof Object)) throw new ClassNotFoundException("Invalid class");
		if (!(this.nodesMap.containsKey(node))) throw new IllegalArgumentException("The given node doesn't exist in the graph ");
		ArrayList<E> myList = new ArrayList<E>();
		myList.addAll(this.nodesMap.get(node));
		myList.sort(null);
		checkRep();
		return myList; 
		
	}
	
}
