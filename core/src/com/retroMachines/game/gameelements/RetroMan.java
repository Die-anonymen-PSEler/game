package com.retroMachines.game.gameelements;

import javafx.stage.StageStyle;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class RetroMan {
	
	public static final float WIDTH = 5f;
	
	public static final float HEIGHT = 5f;
	
	/**
	 * true if the character is looking to the left; false otherwise
	 */
	private boolean faceLeft;
	
	/**
	 * the position of the character on the screen
	 */
	private final Vector2 pos;
	
	/**
	 * if the character is jumping the attribute should be true
	 * prevents jumping while the character is already in the air
	 */
	private State state;
	
	/**
	 * contains a potential gameelement if the character has picked one up
	 */
	private GameElement element;
	
	/**
	 * the texture of the character
	 */
	private Texture texture;
	
	/**
	 * 
	 */
	private final Vector2 velocity;
	
	/**
	 * 
	 */
	public RetroMan() {
		pos = new Vector2();
		velocity = new Vector2();
	}
	
	
	
	
	/**
	 * jumping part
	 */
	
	/**
	 * 
	 */
	public void jump() {
		if (!canJump()) {
			state = State.JUMPING;
			velocity.add(9, 0);
		}
	}
	
	/**
	 * Call this method when the character is supposed to jump
	 */
	public void landed() {
		if (canJump()) {
			state = State.STANDING;
		}
	}
	
	
	
	
	/**
	 * Movement
	 */
	
	public void goLeft() {
		
	}
	
	public void goRight() {
		
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
		//TODO RENDER myself
		return null;
	}

	public boolean canJump() {
		return state != State.JUMPING;
	}

	public Vector2 getPos() {
		return pos;
	}
	
	private enum State{
		STANDING,
		RUNNIG,
		JUMPING
	}
}
