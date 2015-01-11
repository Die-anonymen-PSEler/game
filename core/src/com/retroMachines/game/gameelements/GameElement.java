package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * Basic GameElement class
 * All children have to implement their own render method
 * so they can be displayed.
 * @author RetroFactory
 *
 */
public abstract class GameElement {
	
	/**
	 * A texture where the image of the GameElement can be stored
	 */
	protected Texture texture;
	
	public abstract void render(float deltaTime);
	
}
