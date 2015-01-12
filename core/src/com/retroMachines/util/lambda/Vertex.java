package com.retroMachines.util.lambda;

/**
 * This class is part of the controller of RetroMachines. It models the vertices
 * of the graph of the lambda term.
 * 
 * @author RetroFactory
 * 
 */
public abstract class Vertex {

	/**
	 * Reference to next input
	 */
	protected Vertex next;
	/**
	 * Reference to the output tree
	 */
	protected Vertex family;

}
