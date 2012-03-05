public interface DecisionTreeInterface extends BinaryTreeInterface{	/** Task: Gets the data in the current node.	 *  @return the data object in the current node */	public Object getCurrentData();	/** Task: Determines whether current node contains an answer.	 *  @return true if the current node is a leaf */	public boolean isAnswer();	/** Task: Sets the current node to the left (right) child of	 *  the current node. */	public void advanceToNo();	public void advanceToYes();	/** Task: Sets the current node to the root of the tree.*/	public void reset();} // end DecisionTreeInterface