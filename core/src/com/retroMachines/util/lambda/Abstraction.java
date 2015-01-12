package com.retroMachines.util.lambda;

import java.util.LinkedList;

/**
 * This class is part of the controller of RetroMachines. It models the
 * abstraction of our lambda term, the lights.
 * 
 * @author RetroFactory
 * 
 */
public class Abstraction extends Vertex {

	/**
	 * Unique identifier.
	 */
	private char id;
	/**
	 * List of all ID's of vertices corresponding to this abstraction.
	 */
	private LinkedList<Character> idlist;

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param id
	 *            ID to set.
	 */
	public Abstraction(char id) {
		this.id = id;
		idlist = new LinkedList<Character>();
	}

	/**
	 * Fulfills one step of beta-reduction.
	 * 
	 * @return True if this abstraction has changed, false otherwise.
	 */
	public boolean betaReduction() {
		// TODO: beta reduction
		return false;
	}

	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique id's.
	 * 
	 * @return true if at least one id changed, false if no id changed.
	 */
	public boolean alphaConversion() {
		// TODO: implement alpha conversion
		return false;
	}

	/**
	 * Getter for the ID.
	 * 
	 * @return ID
	 */
	public char getID() {
		return id;
	}

	/**
	 * Setter for the ID.
	 * 
	 * @param id
	 *            to set.
	 */
	public void setID(char id) {
		this.id = id;
	}

}
