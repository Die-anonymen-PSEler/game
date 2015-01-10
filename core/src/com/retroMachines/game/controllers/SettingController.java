package com.retroMachines.game.controllers;

import java.util.List;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.SettingsChangeListener;

/**
 * SettingsController
 * 
 * @author Retro Factory
 * 
 */
public class SettingController {

	private final RetroMachines game;
	/**
	 * list of classes which implement the interface SettingsChangeListener and
	 * have to be notified if the settings are changed
	 */
	private List<SettingsChangeListener> toBeNotified;

	public SettingController(RetroMachines game) {
		this.game = game;
	}

	public void add(SettingsChangeListener toBeAdded) {
		toBeNotified.add(toBeAdded);
	}

}
