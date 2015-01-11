package com.retroMachines.game.controllers;

import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.SettingsChangeListener;

/**
 * SettingsController
 * 
 * @author Retro Factory
 * 
 */
public class SettingController {
	
	/**
	 * Private Attribut of Settings Database which stores the setting informations
	 */
	private Setting settings;
	
	/**
	 * Instance of the Game it selfe
	 */
	private final RetroMachines game;
	
	/**
	 * list of classes which implement the interface SettingsChangeListener and
	 * have to be notified if the settings are changed
	 */
	private List<SettingsChangeListener> toBeNotified;
	
	/**
	 * Constructer which starts an instance of SettingController with all neede Informtaions
	 * @param game instance of Game needed to get ProfileSettings
	 */
	public SettingController(RetroMachines game) {
		this.game = game;
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

}
