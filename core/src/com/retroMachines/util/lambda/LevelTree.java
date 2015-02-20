package com.retroMachines.util.lambda;

/**
 * This class is part of the controller of RetroMachines. It models the tree
 * which stores the lambda terms.
 * @author RetroFactory
 * 
 */
public class LevelTree {

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
	public LevelTree(Vertex start) {
		this.start = start;
	}
	
	/**
	 * One step of evaluation
	 */
	public void evaluate() {
		if (start == null) {
			return; //empty tree, nothing to evaluate
		}
		start.alphaConversion();
		start.betaReduction();
		start = start.updateStart();
	}
	
	public boolean compareTree(LevelTree tree) {
		
	}

	/*
	 * Setter
	 */
	/**
	 * setter for starting point
	 * @param start new start vertex
	 */
	public void setStart(Vertex start) {
		this.start = start;
	}

	/*
	 * Getter
	 */
	/**
	 * Getter for the starting point.
	 * 
	 * @return start
	 */
	public Vertex getStart() {
		return start;
	}

}
