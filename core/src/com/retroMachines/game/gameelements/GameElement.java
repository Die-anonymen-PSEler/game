package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
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
	 * A textureregion where the image of the GameElement can be stored.
	 */
	protected TextureRegion textureRegion;
	
	/**
	 * position in the map before pickup
	 */
	protected Vector2 pos;
	
	/**
	 * position during evaluation
	 */
	protected Vector2 evalPos;
	
	/**
     * The skin of all Screens
     */
	protected Skin skin;
	
	public GameElement() {
		pos = new Vector2();
		evalPos = new Vector2();
	}
	
	public abstract void setColor(String color);
	
	/**
	 * getter for TileSet belonging to GameElement
	 * @return TiledMapTileSet belonging to GameElement
	 */
	public abstract TiledMapTileSet getTileSet();
	
}
