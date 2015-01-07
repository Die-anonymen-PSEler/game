package com.retroMachines.lambda;

public class Variable extends Vertex{

	private char id; //id of variable
	
	public Variable (char id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
