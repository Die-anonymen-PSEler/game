
package com.retroMachines.util.lambda.data;

/**
 * 
 * @author RetroFactory
 *
 */
public class Gameelements{
   	private Number color;
   	private Number posx;
   	private Number posy;
   	private String type;

   	/*
   	 * Setter
   	 */
   	/**
   	 * Setter for the color of the game element.
   	 * @param color The color that is set as the color of the game element.
   	 */
	public void setColor(Number color){
		this.color = color;
	}

	/**
	 * Setter for the x position of the game element.
	 * @param posx The x position.
	 */
	public void setPosx(Number posx){
		this.posx = posx;
	}

	/**
	 * Setter for the y position of the game element.
	 * @param posy The y position.
	 */
	public void setPosy(Number posy){
		this.posy = posy;
	}

	/**
	 * Setter for the type of the game element.
	 * @param type The type of the game element.
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/*
	 * Getter
	 */
   	/**
   	 * Getter for the color of the game element.
   	 * @return The color.
   	 */
 	public Number getColor(){
		return this.color;
	}
 	
 	/**
 	 * Getter for the x position of the game element.
 	 * @return The x position.
 	 */
 	public Number getPosx(){
		return this.posx;
	}
 	
 	/**
 	 * Getter for the y position of the game element.
 	 * @return The y position.
 	 */
 	public Number getPosy(){
		return this.posy;
	}
 	
 	/**
 	 * Getter for the type of the element.
 	 * @return The type of the game element.
 	 */
 	public String getType(){
		return this.type;
	}
}
