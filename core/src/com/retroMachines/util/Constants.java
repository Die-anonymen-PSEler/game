package com.retroMachines.util;

/**
 * Constants class is part of the model of RetroMachines. It contains the
 * constants that are available through out the entire game and may be used.
 * 
 * @author RetroFactory
 * 
 */
public abstract class Constants {

	/**
	 * String array containing all possible colors for the gameElements. 7
	 * colors in total are available.
	 */
	public static final String[] COLOR_HEX = { "#0033CC", // blue
			"#00CC00", // green
			"#FF3300", // red
			"#FFFF00", // yellow
			"#660066", // purple
			"#663300", // brown
			"#66FFFF" // light blue
	};

	/**
	 * String array containing path to all possible characters.
	 */
	public static final String[] pathCharacter = {
	// TO-DO
	};

	/**
	 * This is the amount of the volume that can be changed by the user.
	 */
	public static final float VOLUME_DELTA = 0.1f;

	/**
	 * This is the initial volume of the game.
	 */
	public static final float INITIAL_VOLUME = 0.5f;

	/**
	 * the id of the last level of the game
	 */
	public static final int MAX_LEVEL_ID = 6;

	public static final float WORLD_GRAVITY = -0.5f;
	
	public static final float DAMPING = 0.87f;
	
	public static final int TILE_SIZE = 64;
}
