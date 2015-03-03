package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.AssetManager;

/**
 * This class is part of the model of RetroMachines.
 * A light element that represents the application within the lambda calculus.
 * @author RetroFactory
 */
public class LightElement extends GameElement {
	
	/**
	 * Creates a new instance of LightElement and assigns a texture to it
	 * for rendering purposes.
	 */
	public LightElement() {
		super();
	}

	@Override
	public TiledMapTileSet getTileSet() {
	    return AssetManager.getLights();
	}
	
}
