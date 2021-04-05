package homework2;

import java.io.*; 
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph and PathFinder.
 */
public class TestDriver {

	// String -> Graph: maps the names of graphs to the actual graph
	// TODO: Parameterize the next line correctly.
  	private final Map<String,Graph<WeightedNode> > graphs = new HashMap<String, Graph<WeightedNode> >();
  	// String -> WeightedNode: maps the names of nodes to the actual node
  	private final Map<String,WeightedNode> nodes = new HashMap<>();
	private final BufferedReader input;
  	private final PrintWriter output;


  	/**
  	 * Creates a new TestDriver.
     * @requires r != null and w != null
     * @effects Creates a new TestDriver which reads command from
     * r and writes results to w
  	 * @param r - input file
  	 * @param w - output file
  	 */
  	public TestDriver(Reader r, Writer w) {
    	input = new BufferedReader(r);
    	output = new PrintWriter(w);
  	}


  	/**
  	 * Executes the commands read from the input and writes results to the
  	 * output.
     * @effects Executes the commands read from the input and writes
     * 		    results to the output.
     * @throws IOException - if the input or output sources encounter an
     * 		   IOException.
     */
  	public void runTests() throws IOException {

    	String inputLine;
		while ((inputLine = input.readLine()) != null) {
			// echo blank and comment lines
      		if (inputLine.trim().length() == 0 ||
      		    inputLine.charAt(0) == '#') {
        		output.println(inputLine);
        		continue;
      		}

      		// separate the input line on white space
      		StringTokenizer st = new StringTokenizer(inputLine);
      		if (st.hasMoreTokens()) {
        		String command = st.nextToken();

        		List<String> arguments = new ArrayList<>();
        		while (st.hasMoreTokens())
          			arguments.add(st.nextToken());

        		executeCommand(command, arguments);
      		}
    	}

    	output.flush();
  	}


  	private void executeCommand(String command, List<String> arguments) {

    	try {
      		if (command.equals("CreateGraph")) {
        		createGraph(arguments);
      		} else if (command.equals("CreateNode")) {
        		createNode(arguments);
      		} else if (command.equals("AddNode")) {
        		addNode(arguments);
      		} else if (command.equals("AddEdge")) {
        		addEdge(arguments);
      		} else if (command.equals("ListNodes")) {
        		listNodes(arguments);
      		} else if (command.equals("ListChildren")) {
        		listChildren(arguments);
      		} else if (command.equals("FindPath")) {
        		findPath(arguments);
      		} else {
        		output.println("Unrecognized command: " + command);
      		}
    	} catch (Exception e) {
      		output.println("Exception: " + e.toString());
    	}
  	}


	private void createGraph(List<String> arguments) {
    	if (arguments.size() != 1)
      		throw new CommandException("Bad arguments to CreateGraph: " + arguments);  	
    	String graphName = arguments.get(0);
    	if (this.graphs.get(graphName) != null )
      		throw new CommandException("invalid graph name - already exist" );
    	createGraph(graphName);
  	}


  	private void createGraph(String graphName) {
  		Graph<WeightedNode> newGraph =  new Graph<WeightedNode>(graphName);  
  		graphs.put(graphName ,newGraph); 
  		output.println("created graph " + graphName );
  	}
 
  	
  	private void createNode(List<String> arguments) {
    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to createNode: " + arguments);

    	String nodeName = arguments.get(0);
    	String cost = arguments.get(1);
    	if (this.nodes.get(nodeName) != null)
      		throw new CommandException("invalid node name - already exist" );
    	createNode(nodeName, cost);
  	}


 	private void createNode(String nodeName, String cost) {
 		// TODO: Insert your code here.
 		WeightedNode newNode = new WeightedNode(nodeName, Integer.parseInt(cost));// FIXME - cost need to be converted to int . 
 		this.nodes.put(nodeName ,newNode );
  		output.println("created node " +nodeName + " with cost " + cost );
  	}
	

  	private void addNode(List<String> arguments) throws NullPointerException, ClassNotFoundException {
    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to addNode: " + arguments);

    	String graphName = arguments.get(0);
    	String nodeName = arguments.get(1);
    	addNode(graphName, nodeName);
  	}


  //	private void addNode(String graphName, String nodeName) throws NullPointerException, ClassNotFoundException {
  	private void addNode(String graphName, String nodeName) throws NullPointerException, ClassNotFoundException  {
  		Graph<WeightedNode> currentGraph = null;
  		//check of the given graph already exist    	
  		if ( this.graphs.get(graphName) != null ) {
  			currentGraph = this.graphs.get(graphName) ;
  		}
  		else {
  			int a =1  ;
  	//		throw new IllegalArgumentException("given graph does not exist ");
  		}
  		// get the node , and then check if its in the graph- if no , add it . 
  		if (this.nodes.get(nodeName) == null ) {
  			int a =1;
  	//		throw new IllegalArgumentException("node is already exist in the graph ");
  		}
  		currentGraph.AddNode(this.nodes.get(nodeName));
  		graphs.put(graphName, currentGraph);
  		output.println("added node " +nodeName + " to " + graphName );
  		
  	}


  	private void addEdge(List<String> arguments) throws IllegalArgumentException, NullPointerException, ClassNotFoundException {
    	if (arguments.size() != 3)
      		throw new CommandException(
				"Bad arguments to addEdge: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	String childName = arguments.get(2);
    	addEdge(graphName, parentName, childName);
  	}

	private void addEdge(String graphName, String parentName, String childName) throws IllegalArgumentException, NullPointerException, ClassNotFoundException {
		Graph<WeightedNode> currentGraph;
  		//check of the given graph already exist    	
  		if ( this.graphs.get(graphName) != null ) {
  			currentGraph = this.graphs.get(graphName) ;
  		}
  		else {
  			throw new IllegalArgumentException("given graph does not exist ");
  		}
  		// check if the parentName and childName in nodes list 
  		// + the uniqueness of the edge already in graph methods 
  		if ( this.nodes.get(parentName) == null ||  this.nodes.get(childName) == null) {
  			throw new IllegalArgumentException("one of the node doesnt exist in the graph ");
  		}

  		WeightedNode srcNode = this.nodes.get(parentName) ; 
  		WeightedNode dstNode = this.nodes.get(childName);
  		boolean res= currentGraph.AddEdge(srcNode, dstNode);
  		if(res == true ) {
  			this.graphs.put(graphName,currentGraph); 
  			output.println("added edge from " +parentName + " to " + childName +" in "+graphName );
  		}
  		else {
  			output.println("failed to add edge");

  		}
  		
  	}


  	private void listNodes(List<String> arguments) {
    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to listNodes: " + arguments);

    	String graphName = arguments.get(0);
    	listNodes(graphName);
  	}

  	private void listNodes(String graphName) {  		
		Graph<WeightedNode> currentGraph;
  		//check of the given graph already exist    	
  		if ( this.graphs.get(graphName) != null ) {
  			currentGraph = this.graphs.get(graphName) ;
  		}
  		else {
  			throw new IllegalArgumentException("given graph does not exist ");
  		}
  		ArrayList<WeightedNode> nodesList = currentGraph.ListNodes();
  		String s ="" ; 
  		for (WeightedNode n :nodesList ) {
  			s += " ";
  			s += n.getName();
  		}
  		output.println(graphName + " contains:" + s );

  	}

  	private void listChildren(List<String> arguments) throws NullPointerException, ClassNotFoundException, IllegalArgumentException {
    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to listChildren: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	listChildren(graphName, parentName);
  	}

  	private void listChildren(String graphName, String parentName) throws NullPointerException, ClassNotFoundException, IllegalArgumentException {  		
		Graph<WeightedNode> currentGraph;
  		//check of the given graph already exist    	
  		if ( this.graphs.get(graphName) != null ) {
  			currentGraph = this.graphs.get(graphName) ;
  		}
  		else {
  			throw new IllegalArgumentException("given graph does not exist ");
  		}
  		//check if the node is in the graph is in the graph method. 
  		WeightedNode srcNode = this.nodes.get(parentName) ; 
  		
 		ArrayList<WeightedNode> nodesList = currentGraph.ListChildren(srcNode) ; 
  		String s ="" ; 
  		for (WeightedNode n :nodesList ) {
  			s += " ";
  			s += n.getName();
  		}
  		output.println("the children of "+parentName +" in " +graphName +" are:" + s );

  	}


  	private void findPath(List<String> arguments) throws IllegalArgumentException, ClassNotFoundException {

    	String graphName;
    	List<String> sourceArgs = new ArrayList<>();
    	List<String> destArgs = new ArrayList<>();

    	if (arguments.size() < 1)
      		throw new CommandException(
				"Bad arguments to FindPath: " + arguments);

    	Iterator<String> iter = arguments.iterator();
    	graphName = iter.next();

		// extract source arguments
    	while (iter.hasNext()) {
      		String s = iter.next();
      		if (s.equals("->"))
        		break;
      		sourceArgs.add(s);
    	}

		// extract destination arguments
    	while (iter.hasNext())
      		destArgs.add(iter.next());

    	if (sourceArgs.size() < 1)
      		throw new CommandException(
				"Too few source args for FindPath");

    	if (destArgs.size() < 1)
      		throw new CommandException(
				"Too few dest args for FindPath");

    	findPath(graphName, sourceArgs, destArgs);
  	}


  	private void findPath(String graphName, List<String> sourceArgs,
  						  List<String> destArgs) throws IllegalArgumentException, ClassNotFoundException {
  		// get the correct graph 
  		Graph<WeightedNode> currentGraph;
  		if ( this.graphs.get(graphName) != null ) {
  			currentGraph = this.graphs.get(graphName) ;
  		}
  		else {
  			throw new IllegalArgumentException("given graph does not exist ");
  		}
  		//create set of the given starts
  		Set<Path<WeightedNode,WeightedNodePath>> starts = new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		for (String startNodeName : sourceArgs) {
  	  		WeightedNode startNode = this.nodes.get(startNodeName) ; 
  	  		WeightedNodePath startWeightedNodePath = new WeightedNodePath(startNode) ; 
  	  		starts.add(startWeightedNodePath);
  		}
  		//create set of the given goals
  		Set<Path<WeightedNode,WeightedNodePath>> goals = new HashSet<Path<WeightedNode, WeightedNodePath> >();
  		for (String goalNodeName : destArgs) {
  	  		WeightedNode goalNode = this.nodes.get(goalNodeName) ; 
  	  		WeightedNodePath goalWeightedNodePath = new WeightedNodePath(goalNode) ; 
  	  		goals.add(goalWeightedNodePath);
  		}
        Path<WeightedNode,WeightedNodePath> result = PathFinder.findShortestPath(currentGraph,starts,goals);   
        if (result != null) {
	        String s = "";
	        Iterator<WeightedNode> iter = result.iterator();
	        while(iter.hasNext()){
	  			s += " ";
	        	s += iter.next().getName();
	        }    
	        output.println("shortest path in "+graphName+":" + s);
        }
        else{
        	output.println("there is no path between "
        +sourceArgs + " and" +destArgs +"in " +graphName);
        	}
  	}

	private static void printUsage() {
		System.err.println("Usage:");
		System.err.println("to read from a file: java TestDriver <name of input script>");
		System.err.println("to read from standard input: java TestDriver");
	}

/**
 * 
 * @param args - arg from the line
 */
	public static void main(String args[]) {

		try {
			// check for correct number of arguments
			if (args.length > 1) {
				printUsage();
				return;
			}

			TestDriver td;
			if (args.length == 0)
				// no arguments - read from standard input
				td = new TestDriver(new InputStreamReader(System.in),
								    new OutputStreamWriter(System.out));
			else {
				// one argument - read from file
				java.nio.file.Path testsFile = Paths.get(args[0]);
				if (Files.exists(testsFile) && Files.isReadable(testsFile)) {
					td = new TestDriver(
							Files.newBufferedReader(testsFile, Charset.forName("US-ASCII")),
							new OutputStreamWriter(System.out));
				} else {
					System.err.println("Cannot read from " + testsFile.toString());
					printUsage();
					return;
				}
			}

			td.runTests();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}
}


/**
 * This exception results when the input file cannot be parsed properly.
 */
class CommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
  	}

  	public CommandException(String s) {
		super(s);
  	}
}