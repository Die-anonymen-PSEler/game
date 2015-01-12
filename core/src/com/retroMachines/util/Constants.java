package com.retroMachines.util;

/**
 * Constants class
 * This class holds constants that are available through out the entire game
 * and may be used. 
 * @author RetroFactory
 *
 */
public abstract class Constants {
	
	/**
	 * String array containing all possible colors for the gameElements
	 * 7 colors are currently available
	 */
	public static final String[] colorHex = {
		"#0033CC", // blue
		"#00CC00", // green
		"#FF3300", // red
		"#FFFF00", // yellow
		"#660066", // purple
		"#663300", // brown
		"#66FFFF" // light blue
	};
	
	/**
	 * String array containing path to all possible characters
	 */
	public static final String[] pathCharacter = {
		// TO-DO 
	};
	
	/**
	 * this is the amount of which the volume may be changed by the user
	 */
	public static final float VOLUME_DELTA = 0.1f;
	
	/**
	 * this is the initial volume of the game
	 */
	public static final float INITIAL_VOLUME = 0.5f;
}
