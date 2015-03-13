package com.retroMachines.util;

/**
 * Constants class is part of the model of RetroMachines. It contains the
 * constants that are available through out the entire game and may be used.
 * 
 * @author RetroFactory
 * 
 */
public class Constants {

	/**
	 * The name of the background image file
	 */
	public static final String BACKGROUND_PATH = "Background.png";

	/**
	 * the name of each available character texture. these names have to match
	 * the file names and will be used by the assetmanager.
	 */
	public static final String[] TEXTURE_ANIMATION_NAMES = { "Robot",
			"BlueMan", "Horse", "Unicorn" };

	/**
	 * tag for error or log messages.
	 */
	public static final String LOG_TAG = "RetroFactory";

	/**
	 * String array containing path to all possible characters.
	 */
	public static final String[] PATH_CHARACTER = {
	// TO-DO
	};

	/**
	 * maximum value of id
	 */
	public static final int MAX_COLOR_ID = 11;

	/**
	 * This is the amount of the volume that can be changed by the user.
	 */
	public static final float VOLUME_DELTA = 0.1f;

	/**
	 * the id of the last level of the game
	 */
	public static final int MAX_LEVEL_ID = 8;

	/**
	 * the amount of seconds in a minute used by the statistic screen
	 */
	public static final int SECONDS_IN_MINUTE = 60;

	/*
	 * map related constants especially the id of each layer
	 */

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

	/*
	 * constants regarding the animation of the evaluation
	 */

	public static final int DEPOTLAYER_Y_DIF = 3;

	public static final int GAMEELEMENT_WIDTH = 100;

	public static final int GAMEELEMENT_ANIMATION_WIDTH = 90;

	public static final int GAMELEMENT_PADDING = 25;

	public static final int ABSTRACTION_INPUT = 35;

	public static final int ABSTRACTION_OUTPUT = 25;

	public static final int EVALUATION_DEFALT_LAYER_DIF = 1;

	public static final float EVALUATIONSCREEN_PADDING_X = 0.1f;

	public static final float EVALUATIONSCREEN_PADDING_Y = 0.3f;

	public static final int DEPOT_ID = 8;

	public static final float GAMEELEMENT_SCALING = 0.25f;

	public static final float ACTION_MOVINGTIME = 2f;

	public static final float ACTION_TIME = 1f;

	public static final float FLOAT_EPSILON = 1E-1f;

	/**
	 * the names of the buttons that are in the skin file
	 * 
	 * @author RetroFactory
	 */
	public static class ButtonStrings {

		public static final String HOME = "home";

		public static final String PLAY = "play";

		public static final String OK = "ok";

		public static final String BACK = "back";

		public static final String ABORT = "abort";

		public static final String CONTROL_LEFT = "controlLeft";

		public static final String CONTROL_RIGHT = "controlRight";

		public static final String NEXT_CHAR = "nextChar";

		public static final String SOUND_UP = "soundUp";

		public static final String SOUND_DOWN = "soundDown";

		public static final String SOUND_OFF = "soundOff";

		public static final String LOCKED = "locked";

		public static final String CHANGE_PROFILE = "changeProfile";

		public static final String ADD_PROFILE = "addProfile";

		public static final String DELETE_PROFILE = "deleteProfile";
	}

	/**
	 * Further strings that are used by the game.
	 * 
	 * @author lucabecker
	 * 
	 */
	public static class RetroStrings {
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

		public static final String VARIABLE_TYPE = "Variable";

		public static final String APPLICATION_TYPE = "Application";

		public static final String ABSTRACTION_TYPE = "Abstraction";

		/*
		 * error messages that will be displayed to the user
		 */

		public static final String ABSTRACTION_FAMILY_INVALID = "Jede Maschine braucht ein Verarbeitungsbereich!";

		public static final String ABSTRACTION_NEXT_INVALID = "Jede Maschine braucht eine Eingabe!";

		public static final String APPLICATION_FAMILY_INVALID = "Jede Ampel braucht eine Verarbeitungsbereich!";

		public static final String VARIABLE_FAMILY_INVALID = "Kein Metallobjekt hat einen Verarbeitungsbereich!";

		public static final String SOLUTION_INVALID = "Leider nicht richtig. Versuch es nochmal";

		public static final String NOT_ALL_PLACED = "In allen Ablagen muss ein Objekt platziert sein";
	}
}
