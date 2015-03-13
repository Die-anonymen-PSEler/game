package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.RetroAssetManager;

/**
 * This class is part of the model of RetroMachines. A variable element that
 * represents the application within the lambda calculus.
 * 
 * @author RetroFactory
 */
public class MetalElement extends GameElement {

	/**
	 * Creates a new instance of MetalElement
	 * @param the color of the metalElement
	 */
	public MetalElement(int colorId) {
		super();
	}

	@Override
	public TiledMapTileSet getTileSet() {
		return RetroAssetManager.getObjects();
	}

}
