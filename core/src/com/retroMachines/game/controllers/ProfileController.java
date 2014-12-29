package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Profile;

public class ProfileController {
	
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
	
	
	/**
	 * creates a new instance of the profile controller
	 * @param game the game for calls towards the game
	 */
	public ProfileController(RetroMachines game) {
		this.game = game;
	}


	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}


	/**
	 * changes to the current profile to another profile
	 * @param profileName the name of the profile 
	 */
	public void changeActiveProfile(String profileName) {
		
	}
	
	/**
	 * removes the currently active profile 
	 */
	public void deleteCurrentProfile() {
		
	}
	
	/**
	 * checks if a given username is valid, meaning it is not 
	 * occupied by another profile already
	 */
	public boolean isValidUsername(String username) {
		return false;
	}
	
	/**
	 * Get the name of the currently active user
	 * @return The name of the currently active user; Empty String if no user is active.
	 */
	public String getProfileName() {
		if (profile != null) {
			return profile.getProfileName();
		}
		return "";
	}
	
	/**
	 * 
	 */
	
}
