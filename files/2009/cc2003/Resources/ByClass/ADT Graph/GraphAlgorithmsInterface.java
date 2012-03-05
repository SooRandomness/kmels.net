public interface GraphAlgorithmsInterface{	/** Task: Performs a depth-first traversal of a graph.	 *  @param origin an object that labels the origin vertex of the 	 *  traversal	 *  @return a queue of labels of the vertices in the traversal, with	 *  the label of the origin vertex at the queue�s front */	public QueueInterface getDepthFirstTraversal(Object origin);	/** Task: Performs a breadth-first traversal of a graph.	 *  @param origin an object that labels the origin vertex of the 	 *  traversal	 *  @return a queue of labels of the vertices in the traversal, with	 *  the label of the origin vertex at the queue�s front */	public QueueInterface getBreadthFirstTraversal(Object origin);	/** Task: Performs a topological sort of the vertices in a graph	 *  without cycles.	 *  @return a stack of vertex labels in topological order, beginning	 *  with the stack�s top */	public StackInterface getTopologicalSort();	/** Task: Finds the path between two given vertices that has the	 *  shortest length.	 *  @param begin an object that labels the path�s origin vertex	 *  @param end an object that labels the path�s destination vertex 	 *  @return a stack of labels of the vertices along the path, with	 *  the label of the origin vertex at the top and	 *  the label of the destination at the bottom */	public StackInterface getShortestPath(Object begin, Object end);	/** Task: Finds the path between two given vertices that has the	 *  least cost.	 *  @param begin an object that labels the path�s origin vertex	 *  @param end an object that labels the path�s destination vertex	 *  @return a stack of labels of the vertices along the path, with	 *  the label of the origin vertex at the top and	 *  the label of the destination at the bottom */	public StackInterface getCheapestPath(Object begin, Object end);} // end GraphAlgorithmsInterface