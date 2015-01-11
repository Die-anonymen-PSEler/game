package com.retroMachines.game.controllers;

/**
 * implement this interface if you want to be notified about changes
 * regarding the currently active profile in the game.
 * @author RetroFactory
 *
 */
public interface OnProfileChangedListener {
	
	/**
	 * this method will be called whenever the active profile
	 * was changed.
	 */
	public void profileChanged();
}
