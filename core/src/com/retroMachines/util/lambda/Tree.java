package com.retroMachines.util.lambda;

public class Tree {
	
	/**
	 * root of tree, top level of lambda term
	 */
	private Vertex start; 
	
	/**
	 * creates Tree representation of given lambda-term in string representation
	 * @param term string representation of labmda-term
	 */
	public Tree(String term) {
		//TODO: create tree from term
	}
	
	/**
	 * creates Tree object of given vertex.
	 * @param start root of tree to create
	 */
	public Tree(Vertex start) {
		this.start = start;
	}
	
	/**
	 * getter for start
	 * @return start
	 */
	public Vertex getStart() {
		return start;
	}
	

}
