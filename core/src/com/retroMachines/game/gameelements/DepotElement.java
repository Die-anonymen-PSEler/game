package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.RetroAssetManager;

public class DepotElement extends GameElement {

	/**
	 * Creates a new instance of DepotElement and assigns a texture to it for
	 * rendering purposes.
	 */
	public DepotElement() {
		super();
	}

	@Override
	public TiledMapTileSet getTileSet() {
		return RetroAssetManager.getDepots();
	}

}
