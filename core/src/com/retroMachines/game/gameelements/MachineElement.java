package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.RetroAssetManager;

/**
 * This class is part of the model of RetroMachines. A machine element that
 * represents the abstraction within the lambda calculus.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class MachineElement extends GameElement {

	/**
	 * Creates a new instance of MachineElement and assigns a texture to it for
	 * rendering purposes.
	 * 
	 * @param color The color of the machineElement.
	 */
	public MachineElement(int colorId) {
		super();
	}

	/**
	 * Get method for the tileSet of the game element.
	 * 
	 * @return The tileSet of the element.
	 */
	@Override
	public TiledMapTileSet getTileSet() {
		return RetroAssetManager.getMachines();
	}
}
