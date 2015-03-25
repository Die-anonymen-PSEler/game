package com.retroMachines.util;

import com.retroMachines.game.controllers.ProfileController;

/**
 * Constants class is part of the model of RetroMachines. It contains the
 * constants that are available through out the entire game and may be used.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public abstract class Constants {

	/**
	 * The name of the background image file.
	 */
	public static final String BACKGROUND_PATH = "Background.png";

	/**
	 * The name of each available character texture. These names have to match
	 * the file names and will be used by the assetManager.
	 */
	public static final String[] TEXTURE_ANIMATION_NAMES = { "Robot",
			"BlueMan", "Horse", "Unicorn" };

	/**
	 * Tag for error or log messages.
	 */
	public static final String LOG_TAG = "RetroFactory";

	/**
	 * Maximum value of ID
	 */
	public static final int MAX_COLOR_ID = 11;

	/**
	 * This is the amount of the volume that can be changed by the user.
	 */
	public static final float VOLUME_DELTA = 0.1f;

	/**
	 * The ID of the last level of the game.
	 */
	public static final int MAX_LEVEL_ID = 8;

	/**
	 * The amount of seconds in a minute used by the statistic screen.
	 */
	public static final int SECONDS_IN_MINUTE = 60;

	/*
	 * map related constants especially the id of each layer
	 */

	/**
	 * The gravity that is dragging the character towards the grounds.
	 */
	public static final float WORLD_GRAVITY = -0.5f;

	/**
	 * The size of pixels each tile has.
	 */
	public static final int TILE_SIZE = 64;

	/**
	 * The layer id on which retroMan may walk.
	 */
	public static final int SOLID_LAYER_ID = 1;

	/**
	 * The layer ID which has a opened door.
	 */
	public static final int DOOR_OPEN_LAYER = 2;

	/**
	 * The layer ID which has a closed door.
	 */
	public static final int DOOR_CLOSED_LAYER = 3;

	/**
	 * The layer ID where the Pipes of the depots are.
	 */
	public static final int PIPE_LAYER = 4;

	/**
	 * The layer ID where the depots for the game elements are.
	 */
	public static final int DEPOT_LAYER = 5;

	/**
	 * The layer ID where the lambda objects will be placed.
	 */
	public static final int OBJECT_LAYER_ID = 6;

	/*
	 * constants regarding the animation of the evaluation
	 */

	/**
	 * The layer where the depots are.
	 */
	public static final int DEPOTLAYER_Y_DIF = 3;

	/**
	 * The width of the game elements.
	 */
	public static final int GAMEELEMENT_WIDTH = 100;

	/**
	 * The width of the animation of the game elements.
	 */
	public static final int GAMEELEMENT_ANIMATION_WIDTH = 90;

	/**
	 * The padding of the game elements.
	 */
	public static final int GAMELEMENT_PADDING = 15;

	/**
	 * The input size of the abstraction.
	 */
	public static final int ABSTRACTION_INPUT = 35;

	/**
	 * The output size of the abstraction.
	 */
	public static final int ABSTRACTION_OUTPUT = 25;

	/**
	 * The layer where the evaluation happens.
	 */
	public static final int EVALUATION_DEFALT_LAYER_DIF = 1;

	/**
	 * The padding of the evaluation screen to the x axis.
	 */
	public static final float EVALUATIONSCREEN_PADDING_X = 0.1f;

	/**
	 * The padding of the evaluation screen to the y axis.
	 */
	public static final float EVALUATIONSCREEN_PADDING_Y = 0.3f;

	/**
	 * ID of the depots.
	 */
	public static final int DEPOT_ID = 8;

	/**
	 * The scaling of the game elements.
	 */
	public static final float GAMEELEMENT_SCALING = 0.25f;

	/**
	 * The time how long the moving in an action is.
	 */
	public static final float ACTION_MOVINGTIME = 2f;

	/**
	 * The time how long an action is.
	 */
	public static final float ACTION_TIME = 1f;

	/**
	 * Epsilon for float comparisons.
	 */
	public static final float FLOAT_EPSILON = 1E-1f;

	
	
	/**
	 * The names of the buttons that are in the skin file.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	public abstract static class ButtonStrings {
		/**
		 * The home button.
		 */
		public static final String HOME = "home";

		/**
		 * The play button.
		 */
		public static final String PLAY = "play";

		/**
		 * The OK button.
		 */
		public static final String OK = "ok";

		/**
		 * The back button.
		 */
		public static final String BACK = "back";

		/**
		 * The abort button.
		 */
		public static final String ABORT = "abort";

		/**
		 * The left hand control button.
		 */
		public static final String CONTROL_LEFT = "controlLeft";

		/**
		 * The right hand control button.
		 */
		public static final String CONTROL_RIGHT = "controlRight";

		/**
		 * The next character button.
		 */
		public static final String NEXT_CHAR = "nextChar";

		/**
		 * The sound up button.
		 */
		public static final String SOUND_UP = "soundUp";

		/**
		 * The sound quieter button.
		 */
		public static final String SOUND_DOWN = "soundDown";

		/**
		 * The sound off button.
		 */
		public static final String SOUND_OFF = "soundOff";

		/**
		 * The locked button (e.g. level locked).
		 */
		public static final String LOCKED = "locked";

		/**
		 * The change profile button.
		 */
		public static final String CHANGE_PROFILE = "changeProfile";

		/**
		 * The add profile button.
		 */
		public static final String ADD_PROFILE = "addProfile";

		/**
		 * The delete profile button.
		 */
		public static final String DELETE_PROFILE = "deleteProfile";
	}

	/**
	 * Further strings that are used by the game.
	 * 
	 * @author RetroFactory
	 * @version 1.0 
	 */
	public abstract static class RetroStrings {
		/**
		 * TileSet name in TiledMap.
		 */
		public static final String TILESETNAME_MACHINE = "Maschinen";

		/**
		 * TileSet name in TiledMap.
		 */
		public static final String TILESETNAME_METALOBJECTS = "Metallobjekte";

		/**
		 * TileSet name in TiledMap.
		 */
		public static final String TILESETNAME_LIGHT = "Ampeln";

		/**
		 * TileSet name in TiledMap.
		 */
		public static final String TILESETNAME_DEPOT = "Rohre";

		public static final String VARIABLE_TYPE = "Variable";

		public static final String APPLICATION_TYPE = "Application";

		public static final String ABSTRACTION_TYPE = "Abstraction";

		public static final String DUMMY_TYPE = "Dummy";
		/*
		 * error messages that will be displayed to the user
		 */

		public static final String ABSTRACTION_FAMILY_INVALID = "Jede Maschine braucht ein Verarbeitungsbereich!";

		public static final String ABSTRACTION_NEXT_INVALID = "Jede Maschine braucht eine Eingabe!";

		public static final String APPLICATION_FAMILY_INVALID = "Jede Ampel braucht eine Verarbeitungsbereich!";

		public static final String VARIABLE_FAMILY_INVALID = "Kein Metallobjekt hat einen Verarbeitungsbereich!";

		public static final String SOLUTION_INVALID = "Leider nicht richtig. Versuch es nochmal.";

		public static final String NOT_ALL_PLACED = "In allen Ablagen muss ein Objekt platziert sein.";

		public static final String ERROR_SIMILAR_USER_EXISTS = "Es existiert bereits ein ähnlicher Benutzer.";

		public static final String ERROR_MAX_USER = "Es können maximal " + ProfileController.MAX_PROFILE_NUMBER + " erstellt werden.";

		public static final String ERROR_EMTPY_USER = "Bitte geben Sie einen Benutzernamen an.";
	}
}
