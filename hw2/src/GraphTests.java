package homework2;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// import junit.*;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

/**
 * activate the testing script which uses external test files
 * @param testFile - testinf file
 */
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}
/**
 * testing creatGraph
 */
	@Test 
	public void createGraph() {
		Graph g1 = new Graph<WeightedNode>("g1");		
		Graph g2 = new Graph<WeightedNode>("g2");
	}
	/**
	 * testing get nodes cost
	 */
	@Test 
	public void nodesGetCost() {
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2", 2);
		
		int cost = n1.getCost();
		assertEquals(1 ,cost);
	}
	/**
	 *  checking comprasion between nodes - when they not
	 */
	@Test
	public void nodesEqualFalse() {
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		int n2 = 2 ; 
		boolean res = n1.equals(n2);
		assertEquals(res ,false);
	}
	/**
	 * checking comprasion between nodes - when they do
	 */
	@Test
	public void nodesEqualTrue() {
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2", 2);
		WeightedNode n3 = n1;  
		boolean res = n1.equals(n3);
		assertEquals(res ,true);
	}
	/**
	 *  checking compare to method of node 
	 */
	@Test
	public void nodesCompreToA() {
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2", 2);
		int res = n2.compareTo(n1);
		assertEquals(res ,1);
	}
	/**
	 *  checking toString to method of node 
	 */
	@Test
	public void nodesToString() {
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2", 2);
		String res = n1.toString();
		String expected = "[n1: 1]"; 
		assertEquals(res ,expected);
	}

	/**
	 * checking addNode method of graph - valid case 
	 * @throws ClassNotFoundException - if node is not good instance
	 */
	@Test
	public void addNodeGraphGood() throws ClassNotFoundException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		boolean res = g1.AddNode(n1);
		assertEquals(res ,true);
	}
	/**
	 * checking addNode method of graph - invalid case - node is null
	 * @throws ClassNotFoundException - if node is not good instance
	 */
	@Test (expected = IllegalArgumentException.class )
	public void addNullNodeGraph() throws  ClassNotFoundException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = null ; 	
		g1.AddNode(n1);
	}

	/**
	 * checking addNode method of graph - invalid case - node already in graph
	 * @throws ClassNotFoundException if node is  wrong class
	 */
	@Test
	public void addNodeAlreadyInGraph() throws  ClassNotFoundException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 =  new WeightedNode("n1" ,1);		
		boolean tmp = g1.AddNode(n1);
		boolean res = g1.AddNode(n1);
		assertEquals(res ,false);

	}

	/**
	 * checking addEdge method of graph 
	 * @throws ClassNotFoundException if node is wrong class
	 * @throws IllegalArgumentException if nodes are not in graphs
	 */
	@Test
	public void addEdgeGood() throws  ClassNotFoundException, IllegalArgumentException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2" ,2);	
		g1.AddNode(n1);
		g1.AddNode(n2);
		boolean res = g1.AddEdge(n1,n2);
		assertEquals(res ,true);
	}

	/**
	 * checking addEdge method of graph - invalid case - nodes isnt in graph
	 * @throws ClassNotFoundException if node is wrong class
	 * @throws IllegalArgumentException if nodes are not in graphs
	 */
	@Test
	public void addEdgeBad() throws  ClassNotFoundException, IllegalArgumentException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2" ,2);	
		boolean res = g1.AddEdge(n1,n2);
		assertEquals(res ,false);
	}

	/**
	 * checking addEdge method of graph - invalid case - src node is null 
	 * @throws ClassNotFoundException  if node is wrong class
	 * @throws IllegalArgumentException if nodes are not in graphs
	 */
	@Test (expected = IllegalArgumentException.class )
	public void addEdgeNullSrc() throws  ClassNotFoundException, IllegalArgumentException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = null ; 
		g1.AddNode(n1);
		g1.AddEdge(n1, n2); 
	}
	/**
	 *  checking addEdge method of graph - invalid case - dst node is null 
	 * @throws ClassNotFoundException  if node is wrong class
	 * @throws IllegalArgumentException if nodes are not in graphs
	 */
	@Test (expected = IllegalArgumentException.class )
	public void addEdgeNullDst() throws ClassNotFoundException, IllegalArgumentException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = null ; 
		g1.AddNode(n1);
		g1.AddEdge(n2, n1); 
	}
	/**
	 *  checking listChildren method of graph - invalid case -  node is null 
	 *  @throws IllegalArgumentException if nodes are not in graphs
	 *  @throws ClassNotFoundException -  if wrong class of nodes 
	 */
	@Test (expected = IllegalArgumentException.class )
	public void listChildrenNullNode() throws IllegalArgumentException , ClassNotFoundException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = null ; 
		g1.AddNode(n1);
		g1.ListChildren(n2);
	}

	/**
	 * checking listChildren method of graph - invalid case -  node is not in graph 
	 * @throws IllegalArgumentException  - if node isnt in graph
	 * @throws ClassNotFoundException - if wrong class of nodes 
	 */
	@Test (expected = IllegalArgumentException.class )
	public void listChildrenNotInGraph() throws IllegalArgumentException, ClassNotFoundException {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2" ,2);
		g1.AddNode(n1);
		g1.ListChildren(n2);
	}

	/**
	 * checking findPath method of graph -
	 * @throws ClassNotFoundException - if nodes are in wrong type
	 * @throws IllegalArgumentException - if node or edge isnt in graph
	 */
	@Test
	public void checkFindPathGood() throws ClassNotFoundException, IllegalArgumentException  {
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");		
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2" ,2);
		g1.AddNode(n1);
		g1.AddNode(n2);
		g1.AddEdge(n1, n2);
		WeightedNodePath start = new WeightedNodePath(n1);
		WeightedNodePath goal  = new WeightedNodePath(n2);

  		Set<Path<WeightedNode,WeightedNodePath>> goals = 
  				new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		Set<Path<WeightedNode,WeightedNodePath>> starts = 
  				new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		goals.add(goal);
  		starts.add(start);
  		PathFinder<WeightedNode,WeightedNodePath> p =
  				new PathFinder<WeightedNode,WeightedNodePath>() ;
  		Path<WeightedNode,WeightedNodePath> result = 
  				p.findShortestPath(g1, starts, goals);
        if (result != null) {
	        String s = "";
	        Iterator<WeightedNode> iter = result.iterator();
	        while(iter.hasNext()){
	  			s += " ";
	        	s += iter.next().getName();
	        }    
  		assertEquals(s ," n1 n2"); 
        }
	}

	/**
	 * checking findPath method of graph - invalid case - graph is null 
	 * @throws ClassNotFoundException - if nodes are in wrong type
	 * @throws IllegalArgumentException - if node or edge isnt in graph
	 */
	@Test (expected = IllegalArgumentException.class )
	public void checkFindPathGraphNull() throws ClassNotFoundException, IllegalArgumentException  {
		Graph<WeightedNode> g1 = null ; 	
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2" ,2);
		WeightedNodePath start = new WeightedNodePath(n1);
		WeightedNodePath goal  = new WeightedNodePath(n2);

  		Set<Path<WeightedNode,WeightedNodePath>> goals = 
  				new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		Set<Path<WeightedNode,WeightedNodePath>> starts = 
  				new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		goals.add(goal);
  		starts.add(start);
  		PathFinder<WeightedNode,WeightedNodePath> p =
  				new PathFinder<WeightedNode,WeightedNodePath>() ;
  		Path<WeightedNode,WeightedNodePath> result = 
  				p.findShortestPath(g1, starts, goals);
	}
	/**
	 *  checking findPath method of graph - invalid case - starts is null
	 * @throws ClassNotFoundException - if nodes are in wrong type
	 * @throws IllegalArgumentException - if node or edge isnt in graph 
	 */
	@Test (expected = IllegalArgumentException.class )
	public void checkFindPathNodeNull() throws ClassNotFoundException, IllegalArgumentException  {
		Graph<WeightedNode> g1 = null ; 	
		WeightedNode n1 = new WeightedNode("n1" ,1);		
		WeightedNode n2 = new WeightedNode("n2" ,2);
		WeightedNodePath start = new WeightedNodePath(n1);
		WeightedNodePath goal  = new WeightedNodePath(n2);

  		Set<Path<WeightedNode,WeightedNodePath>> goals = 
  				new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		Set<Path<WeightedNode,WeightedNodePath>> starts = null ;
  		goals.add(goal);
  		PathFinder<WeightedNode,WeightedNodePath> p =
  				new PathFinder<WeightedNode,WeightedNodePath>() ;
  		Path<WeightedNode,WeightedNodePath> result = 
  				p.findShortestPath(g1, starts, goals);
	}
}


