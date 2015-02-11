
package com.retroMachines.util.lambda.data;


/**
 * 
 * @author RetroFactory
 *
 */
public class Level{
	
   	private Data data;
   	private String description;
   	private boolean hasTutorialScreen;
   	private Number levelid;

   	/*
   	 * Setter
   	 */
   	/**
   	 * Setter for the data of the level.
   	 * @param data The data for the level.
   	 */
	public void setData(Data data){
		this.data = data;
	}

	/**
	 * Setter for the description of the level.
	 * @param description The description.
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * Setter if the level is a tutorial level.
	 * @param hasTutorialScreen True if the level is a tutorial level, else false.
	 */
	public void setHasTutorialScreen(boolean hasTutorialScreen){
		this.hasTutorialScreen = hasTutorialScreen;
	}

	/**
	 * Setter for the level ID.
	 * @param levelid The ID of the level.
	 */
	public void setLevelid(Number levelid){
		this.levelid = levelid;
	}
	
	/*
	 * Getter
	 */
	/**
	 * Getter for the data of the level.
	 * @return The data.
	 */
 	public Data getData(){
		return this.data;
	}
	
 	/**
 	 * Getter for the description of the level.
 	 * @return The description.
 	 */
 	public String getDescription(){
		return this.description;
	}
	
	/**
	 * Getter if the level is a tutorial level.
	 * @param hasTutorialScreen True if the level is a tutorial level, else false.
	 */
 	public boolean getHasTutorialScreen(){
		return this.hasTutorialScreen;
	}
	
 	/**
 	 * Getter for the level ID.
 	 * @return The ID.
 	 */
 	public Number getLevelid(){
		return this.levelid;
	}
}
