package com.retroMachines.util.lambda;

/**
 * This class is part of the controller of RetroMachines. It models the variable
 * of the lambda term (the metal Element).
 * 
 * @author RetroFactory
 * 
 */
public class Variable extends Vertex {

	/**
	 * Unique identifier of Variable.
	 */
	private char id;

	/**
	 * Initializes a new instance of Variable.
	 * 
	 * @param id
	 *            id to set
	 */
	public Variable(char id) {
		this.id = id;
	}

	/**
	 * Getter for the ID.
	 * 
	 * @return the id of the variable
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the ID.
	 * 
	 * @param id
	 *            id to set
	 */
	public void setId(char id) {
		this.id = id;
	}

}
