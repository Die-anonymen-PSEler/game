package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.retroMachines.data.AssetManager;

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
		this.colorId = colorId;
		// TODO load texture
	}

	/**
	 * Set the texture with specific color
	 * Possible Colors:
	 * 		Black, Blue, Brown, Green, Grey, Orange, Pink, Purple, Red, White, Yellow
	 */
	public void setColor(String color) {
		skin = AssetManager.getGameelementskin(); 
		texture = skin.get(color + "Machine", TextureRegion.class).getTexture();	
	}

	@Override
	public void render(BatchTiledMapRenderer renderer, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TiledMapTileSet getTileSet() {
		// TODO Auto-generated method stub
		return null;
	}
}
