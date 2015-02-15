package com.retroMachines.game.gameelements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
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
		// TODO Auto-generated constructor stub
		// TODO update texture
	}

	@Override
	public TiledMapTileSet getTileSet() {
	    return AssetManager.getLights();
	}
	
}
