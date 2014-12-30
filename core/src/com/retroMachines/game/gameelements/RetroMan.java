package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RetroMan {
	
	/**
	 * true if the character is looking to the left; false otherwise
	 */
	private boolean faceLeft;
	
	/**
	 * 
	 */
	private final Vector2 pos;
	
	/**
	 * 
	 */
	private boolean jumping;
	
	/**
	 * 
	 */
	private GameElement element;
	
	/**
	 * 
	 */
	public RetroMan() {
		pos = new Vector2();
	}
	
	/**
	 * 
	 */
	public void jump() {
		jumping = true;
	}
	
	/**
	 * 
	 */
	public void landed() {
		jumping = false;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void pickupElement(GameElement element) {
		this.element = element;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasPickedUpElement() {
		return element != null;
	}
	
	/**
	 * 
	 * @param deltaTime
	 */
	public TextureRegion render(float deltaTime) {
		if (hasPickedUpElement()) {
			element.render(deltaTime);
		}
		return null;
	}
}
