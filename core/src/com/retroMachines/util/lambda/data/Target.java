
package com.retroMachines.util.lambda.data;

import java.util.List;

/**
 * 
 * @author RetroFactory
 *
 */
public class Target{
   	private Number color;
   	private List<Target> target;
   	private String type;
   	
   	
 	/*
 	 * Setter
 	 */
 	/**
 	 * Setter for the color of the target.
 	 * @param color The color that is set as the color of the target.
 	 */
	public void setColor(Number color){
		this.color = color;
	}

 	/**
 	 * Setter for the targets.
 	 * @param target The list of targets.
 	 */
	public void setTarget(List<Target> target){
		this.target = target;
	}

 	/**
 	 * Setter for the type of the target.
 	 * @param type The type of the target.
 	 */
	public void setType(String type){
		this.type = type;
	}
	
	/*
	 * Getter
	 */
   	/**
   	 * Getter for the color of the target.
   	 * @return The color.
   	 */
 	public Number getColor(){
		return this.color;
	}
	
	/**
	 * Getter for the targets.
	 * @return The list of targets.
	 */
 	public List<Target> getTarget(){
		return this.target;
	}
	
	/**
	 * Getter for the type of the target.
	 * @return The type.
	 */
 	public String getType(){
		return this.type;
	}
 	
}
