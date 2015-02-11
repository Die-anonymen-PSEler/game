
package com.retroMachines.util.lambda.data;

import java.util.List;

/**
 * 
 * @author RetroFactory
 *
 */
public class Family{
   	private Number color;
   	private List<Family> family;
   	private String type;


   	/*
   	 * Setter
   	 */
   	/**
   	 * Setter for the color of the family.
   	 * @param color The color of the family.
   	 */
	public void setColor(Number color){
		this.color = color;
	}

	/**
	 * Setter for the family.
	 * @param family The family.
	 */
	public void setFamily(List<Family> family){
		this.family = family;
	}

	/**
	 * Setter for the type of the family.
	 * @param type The type of the family.
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/*
	 * Getter
	 */
	/**
	 * Getter for the color of the family.
	 * @return The color of the family.
	 */
 	public Number getColor(){
		return this.color;
	}
 	
 	/**
 	 * Getter for the family.
 	 * @return The family.
 	 */
 	public List<Family> getFamily(){
		return this.family;
	}
 	
 	/**
 	 * Getter for the type of the family.
 	 * @return The type of the family.
 	 */
 	public String getType(){
		return this.type;
	}
}
