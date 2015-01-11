package com.retroMachines.game.controllers;

import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.SettingsChangeListener;

/**
 * SettingsController
 * 
 * @author RetroFactory
 * 
 */
public class SettingController implements OnProfileChangedListener {
	
	/**
	 * Private Attribut of Settings Database which stores the setting informations
	 */
	private Setting settings;
	
	/**
	 * Instance of the Game itself
	 */
	private final RetroMachines game;
	
	/**
	 * list of classes which implement the interface SettingsChangeListener and
	 * have to be notified if the settings are changed
	 */
	private List<SettingsChangeListener> toBeNotified;
	
	/**
	 * Constructor which starts an instance of SettingController with all needed informations
	 * @param game instance of Game needed to get ProfileSettings
	 */
	public SettingController(RetroMachines game) {
		this.game = game;
		game.getProfileController().addProfileChangedListener(this);
		settings = game.getProfileController().getProfile().getSetting();
	}
	
	/**
	 * Adds a class to the List of Classes which must be notified if settings are changed
	 * @param toBeAdded new Classes which should be notified when settings change
	 */
	public void add(SettingsChangeListener toBeAdded) {
		toBeNotified.add(toBeAdded);
	}
	
	/**
	 * sets the Volume to a new loudness specified by the user in the SettingsMenuScreen
	 * @param newVolume the new Volume of the sound
	 */
	public void setVolume(float newVolume) {
		settings.setVolume(newVolume);
	}
	
	/**
	 * returns the currently set volume within the settings instance
	 * @return a float variable within the range of 0-1.0
	 */
	public float getVolume() {
		// TODO Auto-generated method stub
		return settings.getVolume();
	}
	
	/**
	 * Returns true if the Lefti Mode is activated for this Player
	 * @return true when LeftiMode is activated 
	 */
	public boolean getLeftiMode() {
		return settings.isLeftControl();
	}

	@Override
	public void profileChanged() {
		// TODO Auto-generated method stub
		settings = game.getProfileController().getProfile().getSetting();
	}

}
