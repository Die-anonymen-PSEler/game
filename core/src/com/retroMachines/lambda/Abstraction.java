package com.retroMachines.lambda;

public class Abstraction extends Vertex {

	private Variable var;
	private Tree tree;
	
	public Abstraction(Tree tree, Variable var) {
		this.tree = tree;
		this.var = var;
	}
}
