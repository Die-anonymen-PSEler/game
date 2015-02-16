package com.retroMachines.game.gameelements;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.retroMachines.data.AssetManager;

/**
 * This class is part of the model of RetroMachines.
 * A variable element that represents the application within the lambda calculus.
 * @author RetroFactory
 */
public class MetalElement extends GameElement {

	/**
	 * The ID of the color this metal element should have the hex representation
	 * can be looked up within the constants class
	 */
	private int colorId;

	/**
	 * Creates a new instance of MetalElement
	 */
	public MetalElement(int colorId) {
		this.colorId = colorId;
	}

	@Override
	public TiledMapTileSet getTileSet() {
		return AssetManager.getObjects();
	}

}
