package com.retroMachines.data.models;

/**
 * listens if the game settings were changed
 * @author RetroFactory
 *
 */
public interface SettingsChangeListener {
	/**
	 * sends a notification if game settings were changed
	 */
	public void onSettingsChanged();
	
}
