package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This class is part of the model of RetroMachines.
 * It is abstract and it's children describe the Elements that can be moved within the game.
 * They have to implement a render method so they can be displayed to the user.
 * @author RetroFactory
 */
public abstract class GameElement extends Actor {
	
	/**
	 * A texture where the image of the GameElement can be stored.
	 */
	protected TextureRegion textureRegion;
	
	/**
     * The skin of all Screens
     */
	protected Skin skin;
	
	/**
	 * getter for TileSet belonging to GameElement
	 * @return TiledMapTileSet belonging to GameElement
	 */
	public abstract TiledMapTileSet getTileSet();
	
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	
	public void setTextureRegion(TextureRegion region) {
		this.textureRegion = region;
	}
	
}
