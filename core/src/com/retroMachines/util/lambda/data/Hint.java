
package com.retroMachines.util.lambda.data;

import java.util.List;

/**
 * 
 * @author RetroFactory
 *
 */
public class Hint{
   	private Number color;
   	private List<Hint> hint;
   	private String type;

   	/*
   	 * Setter
   	 */
   	/**
   	 * Setter for the color of the hint.
   	 * @param color The color of the the hint.
   	 */
	public void setColor(Number color){
		this.color = color;
	}

	/**
	 * Setter for the hint.
	 * @param hint The hint.
	 */
	public void setHint(List<Hint> hint){
		this.hint = hint;
	}

	/**
	 * Setter for the type of the hint.
	 * @param type The type of the hint.
	 */
	public void setType(String type){
		this.type = type;
	}

	/*
	 * Getter
	 */
	/**
	 * Getter for the color of the hint.
	 * @return The color.
	 */
 	public Number getColor(){
		return this.color;
	}
 	
 	/**
 	 * Getter for the hint.
 	 * @return The hint.
 	 */
 	public List<Hint> getHint() {
		return this.hint;
	}
 	
 	/**
 	 * Getter for the type of the hint.
 	 * @return The type of the hint.
 	 */
 	public String getType(){
		return this.type;
	}
}
