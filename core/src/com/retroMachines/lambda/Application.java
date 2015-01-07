package com.retroMachines.lambda;

public class Application extends Vertex {

	private Tree t1;
	private Tree t2;
	
	public Application(Tree t1, Tree t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public String toString() {
		return t1.toString() + " " + t2.toString();
	}
}
