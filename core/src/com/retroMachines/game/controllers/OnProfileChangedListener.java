package com.retroMachines.game.controllers;

/**
 * This interface is part of the controller of RetroMachines.
 * It is implemented if there is the need to be notified about changes
 * regarding the currently active profile in the game.
 * @author RetroFactory
 *
 */
public interface OnProfileChangedListener {
	
	/**
	 * Called whenever the active profile
	 * was changed.
	 */
	public void profileChanged();
}
