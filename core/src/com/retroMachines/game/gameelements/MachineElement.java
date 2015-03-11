package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.AssetManager;

/**
 * This class is part of the model of RetroMachines. A machine element that
 * represents the abstraction within the lambda calculus.
 * 
 * @author RetroFactory
 */
public class MachineElement extends GameElement {

	/**
	 * Creates a new instance of MachineElement and assigns a texture to it for
	 * rendering purposes.
	 */
	public MachineElement(int colorId) {
		super();
	}

	@Override
	public TiledMapTileSet getTileSet() {
		return AssetManager.getMachines();
	}
}
