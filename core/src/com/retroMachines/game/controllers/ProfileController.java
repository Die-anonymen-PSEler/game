package com.retroMachines.game.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.data.models.Profile;
import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.Statistic;

/**
 * The ProfileController is part of the controller of the RetroMachines.
 * It controls the different profiles of the users.
 * @author RetroFactory
 *
 */
public class ProfileController {

	/**
	 * String array which stores the ProfileNames of existing Profiles.
	 */
	private HashMap<String, Integer> profileNames;

	/**
	 * The currently active profile.
	 */
	private Profile profile;

	/**
	 * Calls to the application can be made via this object.
	 */
	private final RetroMachines game;

	/**
	 * The amount of profiles allowed in the game.
	 */
	public static final int MAX_PROFILE_NUMBER = 5; 
	
	/**
	 * The list of Listener that want to be notified about profile changes.
	 */
	public final List<OnProfileChangedListener> profileChangeListeners;

	/**
	 * Creates a new instance of the profile controller and loads the data from
	 * the background as well as loading the last profile.
	 * @param game The game which is called to. 
	 */
	public ProfileController(RetroMachines game) {
		this.game = game;
		profileChangeListeners = new LinkedList<OnProfileChangedListener>();
		profileNames = Profile.getProfileNameIdMap();
	}

	/**
	 * Removes the currently active profile.
	 */
	public void deleteProfile(String name) {
		int id = profileNames.get(name);
		boolean activeProfileKilled = (id == profile.getProfileId());
		Profile deleteMe = new Profile(id);
		deleteMe.getSetting().destroy();
		deleteMe.getStatistic().destroy();
		deleteMe.destroy();
		profileNames = Profile.getProfileNameIdMap();
		if (profileNames.size() == 0) {
			GlobalVariables gv = GlobalVariables.getSingleton();
			gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, "-1");
		}
		else {
			if (activeProfileKilled) {
				String key = (String) profileNames.keySet().toArray()[0];
				id = profileNames.get(key);
				profile = new Profile(id);
				updateLastUsedProfile();
			}
		}
		notifyProfileListeners();
	}

	/**
	 * Checks if a given user name is valid, meaning it is not occupied by
	 * another profile already.
	 */
	public boolean canUserBeCreated(String username) {
		if (profileNames.size() == MAX_PROFILE_NUMBER) {
			return false;
		}
		for (String name : profileNames.keySet()) {
			if (name.equals(username)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method for creating a new Profile.
	 */
	public void createProfile(String name) {
		if (!canUserBeCreated(name)) {
			return;
		}
		Statistic statistic = new Statistic();
		Setting setting = new Setting();
		profile = new Profile(name, setting, statistic);
		profileNames = Profile.getProfileNameIdMap();
		updateLastUsedProfile();
		notifyProfileListeners();
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Get the name of the currently active user.
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
	 * Getter for the active profile.
	 * @return The profile.
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Changes the current profile to another profile.
	 * 
	 * @param profileName
	 *            The name of the profile that is the next active profile.
	 */
	public void changeActiveProfile(String profileName) {
		int id = profileNames.get(profileName);
		profile = new Profile(id);
		updateLastUsedProfile();
		notifyProfileListeners();
	}
	
	private void updateLastUsedProfile() {
		GlobalVariables.getSingleton().put(GlobalVariables.KEY_LAST_USED_PROFILE, profile.getProfileId());
	}

	
	private void notifyProfileListeners() {
		for(OnProfileChangedListener listener : profileChangeListeners) {
			listener.profileChanged();
		}
	}
	
	/**
	 * Adds a class to the list of profileChangeListeners that are notified when the active profile changes.
	 * @param listener Class to be added.
	 */
	public void addProfileChangedListener(OnProfileChangedListener listener) {
		profileChangeListeners.add(listener);
	}
	
	/**
	 * Removes a class from the list of profileChangedListeners.
	 * @param listener Class to be removed.
	 */
	public void removeProfileChangedListener(OnProfileChangedListener listener) {
		profileChangeListeners.remove(listener);
	}

	public boolean loadLastProfile() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		int id = Integer.parseInt(gv.get(GlobalVariables.KEY_LAST_USED_PROFILE));
		if (id == -1) {
			return false;
		}
		profile = new Profile(id);
		return true;
	}

}
