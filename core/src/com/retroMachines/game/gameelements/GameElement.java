package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
	
	public abstract void draw (Batch batch, float parentAlpha);
	
}
