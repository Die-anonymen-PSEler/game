package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
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
	protected Texture texture;
	
	/**
     * The skin of all Screens
     */
	protected Skin skin;
	
	public abstract void render(BatchTiledMapRenderer renderer, float parentAlpha);
	
	public abstract void setColor(String color);
	
}
