
package com.retroMachines.util.lambda.data;

import java.util.List;

public class Data{
   	private List<Gameelements> gameelements;
   	private List<Hint> hint;
   	private List<Target> target;
   	private List<Tree> tree;

 	public List<Gameelements> getGameelements(){
		return this.gameelements;
	}
	public void setGameelements(List<Gameelements> gameelements){
		this.gameelements = gameelements;
	}
 	public List<Hint> getHint(){
		return this.hint;
	}
	public void setHint(List<Hint> hint){
		this.hint = hint;
	}
 	public List<Target> getTarget(){
		return this.target;
	}
	public void setTarget(List<Target> target){
		this.target = target;
	}
 	public List<Tree> getTree(){
		return this.tree;
	}
	public void setTree(List<Tree> tree){
		this.tree = tree;
	}
}
