package com.retroMachines.game.controllers;

import java.util.LinkedList;
import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Profile;

public class ProfileController {

	/**
	 * String array which stores the ProfileNames of exsiting Profiles
	 */
	private String[] profileNames;

	/**
	 * the currently active profile
	 */
	private Profile profile;

	/**
	 * calls to the app can be made via this object
	 */
	private final RetroMachines game;

	/**
	 * the amount of profiles allowed in the game
	 */
	public static final int MAX_PROFILE_NUMBER = 5;

	public final List<OnProfileChangedListener> profileChangeListeners;

	/**
	 * creates a new instance of the profile controller and loads the data from
	 * the background as well as loading the last profile
	 * 
	 * @param game
	 *            the game for calls towards the game
	 */
	public ProfileController(RetroMachines game) {
		this.game = game;
		profileChangeListeners = new LinkedList<OnProfileChangedListener>();
		profileNames = new String[MAX_PROFILE_NUMBER];
	}

	/**
	 * removes the currently active profile
	 */
	public void deleteCurrentProfile() {
	}

	/**
	 * checks if a given username is valid, meaning it is not occupied by
	 * another profile already
	 */
	public boolean isValidUsername(String username) {
		return false;
	}

	/**
	 * Method for creating a new Profile
	 */
	public boolean createProfile(String name) {
		return false;
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Getter for the Array of ProfileNames
	 * 
	 * @return
	 */
	public String[] getProfileNames() {
		return profileNames;
	}

	/**
	 * Get the name of the currently active user
	 * 
	 * @return The name of the currently active user; Empty String if no user is
	 *         active.
	 */
	public String getProfileName() {
		if (profile != null) {
			return profile.getProfileName();
		}
		return "";
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * changes to the current profile to another profile
	 * 
	 * @param profileName
	 *            the name of the profile
	 */
	public void changeActiveProfile(String profileName) {
		notifyProfileListeners();
	}

	
	private void notifyProfileListeners() {
		for(OnProfileChangedListener listener : profileChangeListeners) {
			listener.profileChanged();
		}
	}
	
	public void addProfileChangedListener(OnProfileChangedListener listener) {
		profileChangeListeners.add(listener);
	}
	
	public void removeProfileChangedListener(OnProfileChangedListener listener) {
		profileChangeListeners.remove(listener);
	}

}
