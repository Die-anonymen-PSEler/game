package com.retroMachines.game.controllers;

import java.util.LinkedList;
import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.SettingsChangeListener;
import com.retroMachines.util.Constants;

/**
 * SettingsController is part of the controller of RetroMachines. It controls
 * the different setting of the game e.g. the volume and the side of the buttons
 * in the game.
 * 
 * @author RetroFactory
 * 
 */

public class SettingController implements OnProfileChangedListener {

	/**
	 * Private attribute of the Settings Database which stores the setting
	 * informations.
	 */
	private Setting settings;

	/**
	 * Instance of the Game itself.
	 */
	private final RetroMachines game;

	/**
	 * List of classes which implement the interface SettingsChangeListener and
	 * have to be notified if the settings are changed.
	 */
	private final List<SettingsChangeListener> toBeNotified;

	/**
	 * Constructor which starts an instance of SettingController with all needed
	 * information.
	 * 
	 * @param game
	 *            Instance of the game that is needed to get the Profile
	 *            settings.
	 */
	public SettingController(RetroMachines game) {
		this.game = game;
		toBeNotified = new LinkedList<SettingsChangeListener>();
	}

	/**
	 * completes the setup of the controller
	 */
	public void initialize() {
		game.getProfileController().addProfileChangedListener(this);
		if (game.getProfileController().getProfile() != null) {
			// no profile available waiting for notification
			settings = game.getProfileController().getProfile().getSetting();
		}
	}

	/**
	 * Adds a class to the list of classes which must be notified if the
	 * settings are changed.
	 * 
	 * @param toBeAdded
	 *            The new class which should be notified when the settings are
	 *            changed.
	 */
	public void add(SettingsChangeListener toBeAdded) {
		toBeNotified.add(toBeAdded);
	}

	/**
	 * removes a class from the list of classes which must be notified if the
	 * settings are changed.
	 * 
	 * @param listener
	 *            the class to be removed
	 */
	public void removeListener(SettingsChangeListener listener) {
		toBeNotified.remove(listener);
	}

	private void notifyListeners() {
		for (SettingsChangeListener listener : toBeNotified) {
			listener.onSettingsChanged();
		}
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Assigns the volume to a new loudness specified by the user in the
	 * SettingsMenuScreen.
	 * 
	 * @param newVolume
	 *            The new volume of the sound.
	 */
	public void setVolume(float newVolume) {
		if (Math.abs(newVolume - settings.getVolume()) > 1E-14) {
			settings.setVolume(newVolume);
			notifyListeners();
		}
	}

	/**
	 * Returns the currently set volume within the settings instance.
	 * 
	 * @return A float variable within the range of 0-1.0.
	 */
	public float getVolume() {
		return settings.getVolume();
	}

	/**
	 * Returns true if the LeftiMode is activated for this Player. False
	 * otherwise.
	 * 
	 * @return True when LeftiMode is activated
	 */
	public boolean isLeftMode() {
		return settings.isLeftControl();
	}

	/**
	 * Sets the left mode for the current setting and notifies listeners
	 * 
	 * @param enabled
	 *            true to enable left mode; false to disable (aka right mode)
	 */
	public void setLeftMode(boolean enabled) {
		settings.setLeftControl(enabled);
		notifyListeners();
	}

	/**
	 * changes the character
	 * 
	 * @return the number of the next character
	 */
	public int toggleCharacter() {
		int currentId = settings.getSelectedCharacter();
		if (currentId + 1 < Constants.TEXTURE_ANIMATION_NAMES.length) {
			settings.setSelectedCharacter(currentId + 1);
		} else {
			// start over
			settings.setSelectedCharacter(0);
		}
		notifyListeners();
		return settings.getSelectedCharacter();
	}

	/**
	 * returns the ID of the current character
	 * 
	 * @return ID of current character
	 */
	public int getCurrentCharacterId() {
		return settings.getSelectedCharacter();
	}

	/**
	 * Assigns a new character id to the settings. IllegalArgumentException will
	 * be thrown in case the parameter exceeds the range.
	 * 
	 * @param i
	 *            range (0 - {@link Constants}.TEXTURE_ANIMATION_NAMES.length -
	 *            1)
	 */
	public void setCharacterId(int i) {
		if (i >= 0 && i < Constants.TEXTURE_ANIMATION_NAMES.length) {
			settings.setSelectedCharacter(i);
		} else {
			throw new IllegalArgumentException(
					"The value exceeds the bounds 0 and "
							+ Constants.TEXTURE_ANIMATION_NAMES.length + ": "
							+ i);
		}
	}

	/**
	 * This method should only be used for DEBUG purposes only! Assigns a
	 * settings object to this controller. normally this controller should be
	 * linked to the profile
	 * 
	 * @param setting
	 */
	public void setSetting(Setting setting) {
		this.settings = setting;
	}


	/**
	 * checks whether the sound is enabled or not
	 * 
	 * @return true if the sound is enabled, false otherwise
	 */
	public boolean isSoundEnabled() {
		if (settings.getVolume() < 1E-14 || !settings.isSoundOnOff()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param levelId
	 *            the level for which it should be checked
	 * @return true if it's finished, false otherwise
	 */
	public boolean isTheTutorialFinished(int levelId) {
		boolean value = settings.isTutorialFinished(levelId);
		return value;
	}

	/**
	 * sets whether the tutorial is finished or not
	 * @param levelId the level for which this should be set
	 * @param value true if it's finished, false otherwise
	 */
	public void setTutorialFinished(int levelId, boolean value) {
		settings.setTutorialFinished(levelId, value);
	}

	/**
	 * sets all tutorials to not finished
	 */
	public void resetTutorials() {
		for (int i = 0; i < Constants.MAX_LEVEL_ID; i++) {
			settings.setTutorialFinished(i, false);
		}
	}

	/**
	 * Controls if the profile was changed.
	 */
	@Override
	public void profileChanged() {
		settings = game.getProfileController().getProfile().getSetting();
		notifyListeners();
	}

}
