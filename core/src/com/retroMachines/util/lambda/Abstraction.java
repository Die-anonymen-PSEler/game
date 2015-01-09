package com.retroMachines.util.lambda;

import java.util.LinkedList;

public class Abstraction extends Vertex {

	private char id;
	private LinkedList<Character> idlist;
	
	public Abstraction(char id) {
		this.id = id;
		idlist = new LinkedList<Character>();
	}
	
	/**
	 * fulfills one step of beta-reduction
	 * @return true if this tree has changed, false otherwise
	 */
	public boolean betaReduction() {
		//TODO: beta reduction
		return false;
	}
	
	
	public boolean alphaConversion() {
		//TODO: implement alpha conversion
		return false;
	}
	
	public char getID() {
		return id;
	}
	
	public void setID(char id) {
		this.id = id;
	}


}
