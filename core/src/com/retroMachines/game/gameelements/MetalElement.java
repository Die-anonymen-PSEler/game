package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.RetroAssetManager;

/**
 * This class is part of the model of RetroMachines. A variable element that
 * represents the application within the lambda calculus.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class MetalElement extends GameElement {

	/**
	 * Creates a new instance of MetalElement.
	 * 
	 * @param colorId 
	 * 				The color of the metalElement.
	 */
	public MetalElement(int colorId) {
		super();
	}

	/**
	 * Get method for the tileSet of the game element.
	 * 
	 * @return The tileSet of the element.
	 */
	@Override
	public TiledMapTileSet getTileSet() {
		return RetroAssetManager.getObjects();
	}

}
