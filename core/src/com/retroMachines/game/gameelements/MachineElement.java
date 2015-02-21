package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.Constants;

/**
 * This class is part of the model of RetroMachines.
 * A machine element that represents the abstraction within the lambda calculus.
 * @author RetroFactory
 */
public class MachineElement extends GameElement {
	
	/**
	 * The ID of the color this machine element should have.
	 * The hex representation can be looked up within the constants class.
	 */
	private int colorId;
	
	
	/**
	 * Creates a new instance of MachineElement and assigns a texture to it
	 * for rendering purposes.
	 */
	public MachineElement(int colorId) {
		super();
		this.colorId = colorId;
		// TODO load texture
	}

	@Override
	public TiledMapTileSet getTileSet() {
		return AssetManager.getMachines();
	}
}
