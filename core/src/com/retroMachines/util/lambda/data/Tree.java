package com.retroMachines.util.lambda.data;

import java.util.List;

/**
 * The lambda calculus is represented as a tree. This class forms the tree. 
 * It is used for the evaluation.
 * @author RetroFactory
 *
 */
public class Tree {
	
	private Number color;
	private List<Tree> tree;
	
	
	/*
	 * Setter
	 */
	/**
	 * Sets the color of the element of the tree.
	 * @param color The color for the element.
	 */
	public void setColor(Number color) {
		this.color = color;
	}
		
	/**
	 * Sets the parameter as the tree of the lambda calculus.
	 * @param tree The tree that is set as the tree of the lambda calculus.
	 */
	public void setTree(List<Tree> tree) {
		this.tree = tree;
	}
	
	/*
	 * Getter
	 */
	/**
	 * Getter for the color of the element of the tree.
	 * @return The color.
	 */
	public Number getColor() {
		return this.color;
	}
	
	/**
	 * Getter for the tree.
	 * @return The tree.
	 */
	public List<Tree> getTree() {
		return this.tree;
	}


}
