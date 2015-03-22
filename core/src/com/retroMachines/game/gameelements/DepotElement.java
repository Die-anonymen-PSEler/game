package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.RetroAssetManager;

/**
 * The class DepotElements are the depots in the game.
 * @author RetroFactory
 * @version 1.0
 */
public class DepotElement extends GameElement {

	/**
	 * Creates a new instance of DepotElement and assigns a texture to it for
	 * rendering purposes.
	 */
	public DepotElement() {
		super();
	}

	/**
	 * Get method for the tileSet of the map.
	 * 
	 * @return The tileSet of the map.
	 */
	@Override
	public TiledMapTileSet getTileSet() {
		return RetroAssetManager.getDepots();
	}

}
