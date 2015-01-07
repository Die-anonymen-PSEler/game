package com.retroMachines.lambda;

public class Tree {
	
	private Vertex start; //root of tree, op level of lamdbda term
	
	/**
	 * creates Tree representation of given lambda-term in string representation
	 * @param term string representation of labmda-term
	 */
	public Tree(String term) {
		//TODO: create tree from term
	}
	
	public Tree(Vertex start) {
		this.start = start;
	}
	
	/**
	 * fulfills one step of beta-reduction
	 * @return true if this tree has changed, false otherwise
	 */
	public boolean betaReduction() {
		//TODO: beta reduction
		return false;
	}
	
	/**
	 * fulfills alphConversion on given variable.
	 * Creates new unique variable and replaces every appearance of var in lambda-term where needed.
	 * New Variable will be returned 
	 * @param var variable to change
	 * @return new created variable
	 */
	public Variable alphaConversion(Variable var) {
		//TODO: implement alpha conversion
		return null;
	}
	
	public String toString() {
		return start.toString();
	}

}
