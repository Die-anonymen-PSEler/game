package com.retroMachines.game.controllers;

import java.util.LinkedList;
import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.SettingsChangeListener;

/**
 * SettingsController is part of the controller of RetroMachines.
 * It controls the different setting of the game e.g. the volume and 
 * the side of the buttons in the game.
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
	 *            Instance of the game that is needed to get the Profile settings.
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
	 * Adds a class to the list of classes which must be notified if the settings
	 * are changed.
	 * 
	 * @param toBeAdded
	 *            The new class which should be notified when the settings are changed.
	 */
	public void add(SettingsChangeListener toBeAdded) {
		toBeNotified.add(toBeAdded);
	}
	
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
		if (newVolume != settings.getVolume()) {
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
		// TODO Auto-generated method stub
		return settings.getVolume();
	}

	/**
	 * Returns true if the LeftiMode is activated for this Player. False otherwise.
	 * 
	 * @return True when LeftiMode is activated
	 */
	public boolean getLeftMode() {
		return settings.isLeftControl();
	}
	
	/**
	 * Sets the left mode for the current setting and notifies listeners
	 * @param enabled true to enable left mode; false to disable (aka right mode)
	 */
	public void setLeftMode(boolean enabled) {
		settings.setLeftControl(enabled);
		notifyListeners();
	}
	
	/**
	 * This method should only be used for DEBUG purposes only!
	 * Assigns a settings object to this controller.
	 * normally this controller should be linked to the profile
	 * @param setting
	 */
	public void setSetting(Setting setting) {
		this.settings = setting;
	}
	
	public boolean soundEnabled() {
		if (settings.getVolume() == 0.0f || !settings.isSoundOnOff()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Controls if the profile was changed.
	 */
	@Override
	public void profileChanged() {
		// TODO Auto-generated method stub
		settings = game.getProfileController().getProfile().getSetting();
		notifyListeners();
	}

	public void toggleCharacter() {
		
	}
}
