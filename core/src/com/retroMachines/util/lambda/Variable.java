package com.retroMachines.util.lambda;

public class Variable extends Vertex{

	private char id; //id of variable
	
	public Variable (char id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(char i) {
		id = i;
	}

}
