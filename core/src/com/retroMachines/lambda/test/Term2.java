package com.retroMachines.lambda.test;

import com.retroMachines.lambda.*;

public class Term2 extends AbstractTerm {
	
	private final String term = "Lx.Lz.x";
	
	private Tree tree;
	
	public Term2() {
		Variable x = new Variable('x');
		Variable z = new Variable('z');
		Vertex ver = new Abstraction(new Tree(x), z);
		Vertex ver2 = new Abstraction(new Tree(ver), x);
		tree = new Tree(ver2);
		super.term = term;
		super.tree = tree;
	}
	
	
	

}
