package com.retroMachines.util.lambda;

import java.util.LinkedList;

public class Abstraction extends Vertex {

	/**
	 * unique identifier 
	 */
	private char id;
	/**
	 * list of all id's of vertices corresponding to this abstraction
	 */
	private LinkedList<Character> idlist;
	
	/**
	 * constructor
	 * @param id id to set
	 */
	public Abstraction(char id) {
		this.id = id;
		idlist = new LinkedList<Character>();
	}
	
	/**
	 * fulfills one step of beta-reduction.
	 * @return true if this abstraction has changed, false otherwise
	 */
	public boolean betaReduction() {
		//TODO: beta reduction
		return false;
	}
	
	/**
	 * fulfills alpha conversion.
	 * Makes sure that all vertices have unique id's.
	 * @return true if at least one id changed, false if no id changed.
	 */
	public boolean alphaConversion() {
		//TODO: implement alpha conversion
		return false;
	}
	
	/**
	 * getter for id
	 * @return id
	 */
	public char getID() {
		return id;
	}
	
	/**
	 * setter for id.
	 * @param id to set.
	 */
	public void setID(char id) {
		this.id = id;
	}


}
