package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
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
	
	/**
	 * Set the texture with specific color
	 * Possible Colors:
	 * 		Black, Blue, Brown, Green, Grey, Orange, Pink, Purple, Red, White, Yellow
	 */
	public void setColor(String color) {
		skin = AssetManager.getGameelementskin(); 
		textureRegion = skin.get(color + "Object", TextureRegion.class);
	}

	@Override
	public void render(BatchTiledMapRenderer renderer, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TiledMapTileSet getTileSet() {
		return AssetManager.getObjects();
	}

}
