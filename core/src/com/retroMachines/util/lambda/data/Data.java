
package com.retroMachines.util.lambda.data;

import java.util.List;

/**
 * 
 * @author RetroFactory
 *
 */
public class Data{
   	private List<Gameelements> gameelements;
   	private List<Hint> hint;
   	private List<Target> target;
   	private List<Tree> tree;


   	/*
   	 * Setter
   	 */
   	/**
   	 * Setter for the game elements.
   	 * @param gameelements The List of the game elements.
   	 */
	public void setGameelements(List<Gameelements> gameelements){
		this.gameelements = gameelements;
	}

	/**
	 * Setter for the hints.
	 * @param hint The list of hints.
	 */
	public void setHint(List<Hint> hint){
		this.hint = hint;
	}

	/**
	 * Setter for the targets.
	 * @param target The list of targets.
	 */
	public void setTarget(List<Target> target){
		this.target = target;
	}

	/**
	 * Setter for the tree of the lambda calculus.
	 * @param tree The tree.
	 */
	public void setTree(List<Tree> tree){
		this.tree = tree;
	}
	
	/*
	 * Getter
	 */
	/**
	 * Getter for the game elements.
	 * @return The list of game elements.
	 */
 	public List<Gameelements> getGameelements(){
		return this.gameelements;
	}
 	
 	/**
 	 * Getter for the hints.
 	 * @return The list of hints.
 	 */
 	public List<Hint> getHint(){
		return this.hint;
	}
 	
 	/**
 	 * Getter for the targets.
 	 * @return The list of targets.
 	 */
 	public List<Target> getTarget(){
		return this.target;
	}
 	
 	/**
 	 * Getter for the trees.
 	 * @return The list of trees.
 	 */
 	public List<Tree> getTree(){
		return this.tree;
	}
}
