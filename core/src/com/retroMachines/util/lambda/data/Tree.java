package com.retroMachines.util.lambda.data;

import java.util.List;

public class Tree {
	
	private Number color;
	private List<Tree> tree;
	
	public Number getColor() {
		return this.color;
	}
	public void setColor(Number color) {
		this.color = color;
	}
	public List<Tree> getTree() {
		return this.tree;
	}
	public void setTree(List<Tree> tree) {
		this.tree = tree;
	}

}
