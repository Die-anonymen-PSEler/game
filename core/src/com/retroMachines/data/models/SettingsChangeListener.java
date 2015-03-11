package com.retroMachines.data.models;

/**
 * This interface is part of the model of RetroMachines. This interface can be
 * implemented by class that rely on the settings and need to be notified about
 * changes that were made to them.
 * 
 * @author RetroFactory
 */
public interface SettingsChangeListener {

	/**
	 * This method will be called by the SettingController once it changed the
	 * settings.
	 */
	public void onSettingsChanged();

}
