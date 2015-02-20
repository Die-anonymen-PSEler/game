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
	 * tag for error or log messages.
	 */
	public static final String LOG_TAG = "RetroFactory";
	
	/**
	 * TileSet name in TiledMap 
	 */
	public static final String TILESETNAME_MACHINE = "Maschinen";
	
	/**
	 * TileSet name in TiledMap 
	 */
	public static final String TILESETNAME_METALOBJECTS = "Metallobjekte";
	
	/**
	 * TileSet name in TiledMap 
	 */
	public static final String TILESETNAME_LIGHT = "Ampeln";
	
	/**
	 * TileSet name in TiledMap 
	 */
	public static final String TILESETNAME_DEPOT = "Rohre";

	/**
	 * String array containing path to all possible characters.
	 */
	public static final String[] pathCharacter = {
	// TO-DO
	};
	
	/**
	 * maximum value of id
	 */
	public static final int MAX_COLOR_ID = 10;

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
	
	/**
	 * the gravity that is dragging the character towards the grounds
	 */
	public static final float WORLD_GRAVITY = -0.5f;	
	
	/**
	 * the size of pixels each tile has.
	 */
	public static final int TILE_SIZE = 64;
	
	/**
	 * the layer id on which retroman may walk
	 */
	public static final int SOLID_LAYER_ID = 1;
	
	/**
	 * the layer id which has a opened door
	 */
	public static final int DOOR_OPEN_LAYER = 2;
	
	/**
	 * the layer id which has a closed door
	 */
	public static final int DOOR_CLOSED_LAYER = 3;
	
	/**
	 * the layer id where the Pipes of the depots are.
	 */
	public static final int PIPE_LAYER = 4;
	
	/**
	 * the layer id where the depots for the gameelements are.
	 */
	public static final int DEPOT_LAYER = 5;
	
	/**
	 * the layer id where the lambda objects will be placed
	 */
	public static final int OBJECT_LAYER_ID = 6;

	public static final int LEFT_RETROMAN_OFFSET = -1;
	
	public static final int RIGHT_RETROMAN_OFFSET = 2;
}
