package com.retroMachines.lambda.test;

import com.retroMachines.lambda.Tree;

public abstract class AbstractTerm {

	protected String term; 
	
	protected Tree tree;
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("erwartet: " + term + "\n");
		sb.append("bekommen: " + tree.toString() + "\n");
		return sb.toString();
	}
	
}
