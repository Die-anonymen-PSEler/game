package com.retroMachines.util.lambda;

/**
 * This class is part of the controller of RetroMachines. It models the tree
 * which stores the lambda terms.
 * @author RetroFactory
 * 
 */
public class Tree {

	/**
	 * Root of the tree, top level of lambda term.
	 */
	private Vertex start;


	/**
	 * Creates the tree object of a given vertex.
	 * 
	 * @param start
	 *            root of tree to create
	 */
	public Tree(Vertex start) {
		this.start = start;
	}

	/**
	 * Getter for the starting point.
	 * 
	 * @return start
	 */
	public Vertex getStart() {
		return start;
	}

}
