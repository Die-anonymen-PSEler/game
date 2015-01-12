package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class is part of the model of RetroMachines.
 * It is abstract and it's children describe the Elements that can be moved within the game.
 * They have to implement a render method so they can be displayed to the user.
 * @author RetroFactory
 */
public abstract class GameElement {
	
	/**
	 * A texture where the image of the GameElement can be stored.
	 */
	protected Texture texture;
	
	/**
	 * Render method to display the GameElement to the screen.
	 * @param deltaTime
	 */
	public abstract void render(float deltaTime);
	
}
