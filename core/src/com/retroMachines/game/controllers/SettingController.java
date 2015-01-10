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
	
	private Setting settings;

	private final RetroMachines game;
	/**
	 * list of classes which implement the interface SettingsChangeListener and
	 * have to be notified if the settings are changed
	 */
	private List<SettingsChangeListener> toBeNotified;

	public SettingController(RetroMachines game) {
		this.game = game;
		settings = game.getProfileController().getProfile().getSetting();
	}

	public void add(SettingsChangeListener toBeAdded) {
		toBeNotified.add(toBeAdded);
	}

	public float getVolume() {
		// TODO Auto-generated method stub
		return settings.getVolume();
	}

}
