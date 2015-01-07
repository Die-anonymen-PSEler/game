package com.retroMachines.lambda.test;

import com.retroMachines.lambda.*;

/**
 *Es soll der gegebene Ausdruck mittels der Lambda-Datenstruktur repräsentiert werden. Hier geschieht das von Hand, soll aber später von einem 
 * Parser übernommen werden.
 * @author maik
 *
 */
public class Term1 extends AbstractTerm {
	
	private final String term = "Lx.x";
	
	private Tree tree;

	public Term1() {
		Variable var = new Variable('x');
		Vertex vertex = new Abstraction(new Tree(var), var);
		tree = new Tree(vertex);
		super.term = term;
		super.tree = tree;
	}
	
//	public String toString() {
//		return super.toString();
//	}

}
