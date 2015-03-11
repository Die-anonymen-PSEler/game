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
 * The ProfileController is part of the controller of the RetroMachines. It
 * controls the different profiles of the users.
 * 
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
	@SuppressWarnings("unused")
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
	 * 
	 * @param game
	 *            The game which is called to.
	 */
	public ProfileController(RetroMachines game) {
		this.game = game;
		profileChangeListeners = new LinkedList<OnProfileChangedListener>();
		profileNames = getProfileNameIdMap();
	}

	public HashMap<String, Integer> getProfileNameIdMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		GlobalVariables gv = GlobalVariables.getSingleton();
		for (int i = 1; i <= MAX_PROFILE_NUMBER; i++) {
			if (gv.get(String.format(GlobalVariables.KEY_SLOTS, i)).equals("1")) {
				Profile tempProfile = new Profile(i);
				map.put(tempProfile.getProfileName(), i);
			} else {
			}
		}
		return map;
	}

	/**
	 * Removes the currently active profile.
	 */
	public void deleteProfile(String name) {
		int id = profileNames.get(name);
		boolean activeProfileKilled = (id == profile.getProfileId());
		new Profile(id).destroy();
		new Statistic(id).destroy();
		new Setting(id).destroy();
		GlobalVariables.getSingleton().put(
				String.format(GlobalVariables.KEY_SLOTS, id), 0);
		profileNames = getProfileNameIdMap();
		if (profileNames.size() == 0) {
			GlobalVariables gv = GlobalVariables.getSingleton();
			gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, "-1");
		} else {
			if (activeProfileKilled) {
				String key = (String) profileNames.keySet().toArray()[0];
				id = profileNames.get(key);
				profile = new Profile(id);
				profile.setSetting(new Setting(id));
				profile.setStatistic(new Statistic(id));
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
		if (profileNames.size() == MAX_PROFILE_NUMBER || username == null
				|| username.equals("")) {
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
		int freeId = GlobalVariables.getSingleton().nextFreeId();
		Statistic statistic = new Statistic(freeId);
		Setting setting = new Setting(freeId);
		profile = new Profile(freeId, name, setting, statistic);
		GlobalVariables.getSingleton().put(
				String.format(GlobalVariables.KEY_SLOTS, freeId), 1);
		profileNames = getProfileNameIdMap();
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
		return (profile == null || !profile.hasRecord()) ? null : profile
				.getProfileName();
	}

	/**
	 * Getter for the active profile.
	 * 
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
		int id = 0;
		id = profileNames.get(profileName);
		profile = new Profile(id);
		profile.setSetting(new Setting(id));
		profile.setStatistic(new Statistic(id));
		updateLastUsedProfile();
		notifyProfileListeners();
	}

	/**
	 * Adds a class to the list of profileChangeListeners that are notified when
	 * the active profile changes.
	 * 
	 * @param listener
	 *            Class to be added.
	 */
	public void addProfileChangedListener(OnProfileChangedListener listener) {
		profileChangeListeners.add(listener);
	}

	/**
	 * Removes a class from the list of profileChangedListeners.
	 * 
	 * @param listener
	 *            Class to be removed.
	 */
	public void removeProfileChangedListener(OnProfileChangedListener listener) {
		profileChangeListeners.remove(listener);
	}

	public boolean loadLastProfile() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		int id = Integer
				.parseInt(gv.get(GlobalVariables.KEY_LAST_USED_PROFILE));
		if (id == -1) {
			return false;
		}
		Statistic statistic = new Statistic(id);
		Setting setting = new Setting(id);
		profile = new Profile(id);
		profile.setStatistic(statistic);
		profile.setSetting(setting);
		return true;
	}

	private void updateLastUsedProfile() {
		GlobalVariables.getSingleton().put(
				GlobalVariables.KEY_LAST_USED_PROFILE, profile.getProfileId());
	}

	private void notifyProfileListeners() {
		for (OnProfileChangedListener listener : profileChangeListeners) {
			listener.profileChanged();
		}
	}

	public String[] getAllProfiles() {
		String[] result = new String[profileNames.size()];
		int i = 0;
		for (String name : profileNames.keySet()) {
			result[i] = name;
			i++;
		}
		return result;
	}

}