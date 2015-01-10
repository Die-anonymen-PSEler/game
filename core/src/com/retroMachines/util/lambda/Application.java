package com.retroMachines.util.lambda;

import java.util.LinkedList;

public class Application extends Vertex {
	
	/**
	 * list of all id's of vertices corresponding to this abstraction
	 */
	private LinkedList<Character> idlist;
	/**
	 * tells whether this application is already processed.
	 * False until process is finished
	 */
	private boolean isChecked;

	/**
	 * constructor
	 * sets empty idList, isChecked to false
	 */
	public Application() {
		idlist = new LinkedList<Character>();
		isChecked = false;
	}
	
	/**
	 * sets isChecked to true
	 */
	public void setChecked() {
		isChecked = true;
	}

}
