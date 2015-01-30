package com.retroMachines.game.controllers;

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
	private List<SettingsChangeListener> toBeNotified;

	/**
	 * Constructor which starts an instance of SettingController with all needed
	 * information.
	 * 
	 * @param game
	 *            Instance of the game that is needed to get the Profile settings.
	 */
	public SettingController(RetroMachines game) {
		this.game = game;
		game.getProfileController().addProfileChangedListener(this);
		settings = game.getProfileController().getProfile().getSetting();
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
		settings.setVolume(newVolume);
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
	
	public void setLeftMode(boolean enabled) {
		settings.setLeftControl(enabled);
	}
	/**
	 * Controls if the profile was changed.
	 */
	@Override
	public void profileChanged() {
		// TODO Auto-generated method stub
		settings = game.getProfileController().getProfile().getSetting();
	}

}
