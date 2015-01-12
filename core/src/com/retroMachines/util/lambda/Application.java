package com.retroMachines.util.lambda;

import java.util.LinkedList;

/**
 * This class is part of the controller of RetroMachines. It models the
 * application of our lambda term, the machines.
 * 
 * @author RetroFactory
 * 
 */
public class Application extends Vertex {

	/**
	 * List of all ID's of vertices corresponding to this abstraction.
	 */
	private LinkedList<Character> idlist;
	/**
	 * Tells whether this application is already processed. False until process
	 * is finished.
	 */
	private boolean isChecked;

	/**
	 * Constructor sets empty IDList, isChecked to false.
	 */
	public Application() {
		idlist = new LinkedList<Character>();
		isChecked = false;
	}

	/**
	 * Sets isChecked to true.
	 */
	public void setChecked() {
		isChecked = true;
	}

}
