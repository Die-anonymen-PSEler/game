package com.retroMachines.util.lambda;

public class Variable extends Vertex{

	/**
	 * unique identifier of Variable
	 */
	private char id; 
	
	/**
	 * constructor
	 * @param id id to set
	 */
	public Variable (char id) {
		this.id = id;
	}
	
	/**
	 * getter for id.
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * setter for id
	 * @param id id to set
	 */
	public void setId(char id) {
		this.id = id;
	}

}
