package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;
import com.retroMachines.game.controllers.ProfileController;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the game itself. These attributes are for instance the
 * last used profile.
 * 
 * @author RetroFactory
 */
public class GlobalVariables extends Model {

	/**
	 * the name of the table where the globalVariables are stored
	 */
	public static final String TABLE_NAME = "globalVariables";

	/**
	 * the key for the lastusedprofile id
	 */
	public static final String KEY_LAST_USED_PROFILE = "lastUsedProfile";

	public static final String KEY_SLOTS = "Slot_%d";

	/**
	 * Private Instance of itself to implement the singleton pattern.
	 */
	private static GlobalVariables instance;

	/**
	 * Private constructor so this class can not be instantiated from the
	 * outside. Please use getSingleton to retrieve a copy of this class.
	 */
	private GlobalVariables() {
		super();
		pref = Gdx.app.getPreferences(TABLE_NAME);
	}

	/**
	 * Creates a new instance of GlobalVariables and saves it to the attribute.
	 */
	private static void instantiate() {
		instance = new GlobalVariables();
	}

	/**
	 * This function checks if the persistent background storage has a record.
	 * 
	 * @return true if a record exists
	 */
	@Override
	public boolean hasRecord() {
		return true;
	}

	/**
	 * Stores all keyValue pairs in the database for persistent storage.
	 */
	@Override
	public void write() {
		pref.flush();
		return;
	}

	@Override
	public void fetch() {
	}

	@Override
	public void destroy() {
		// nothing to do. just override the value
	}

	/*
	 * Getter and Setter of this class
	 */

	/**
	 * Stores a keyValue pair within this class. If the key already exists it
	 * will override the previous value.
	 * 
	 * @param key
	 *            the key under which the value should be stored.
	 * @param value
	 *            the value that wil be stored.
	 */
	public void put(String key, String value) {
		pref.putString(key, value);
		write();
	}

	public void put(String key, int value) {
		put(key, String.valueOf(value));
	}

	public int nextFreeId() {
		for (int i = 1; i <= ProfileController.MAX_PROFILE_NUMBER; i++) {
			String value = pref.getString(String.format(KEY_SLOTS, i), "0");
			int result;
			try {
				result = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				result = -1;
			}
			if (result == 0) {
				return i;
			}
		}
		return rowId;
	}

	/**
	 * Returns the value according to the key.
	 * 
	 * @param key
	 *            of the key value pair for lookup
	 * @return the value that was found. null if the key does not exist.
	 */
	public String get(String key) {
		return pref.getString(key, "-1");
	}

	/**
	 * Returns the only instance of this class. If none has been created yet one
	 * will be created first.
	 * 
	 * @return the singleton.
	 */
	public static GlobalVariables getSingleton() {
		if (instance == null) {
			instantiate();
		}
		return instance;
	}
}
