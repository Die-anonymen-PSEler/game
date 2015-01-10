package com.retroMachines.game.gameelements;

import java.awt.image.TileObserver;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
	private void land() {
		if (canJump()) {
			state = State.STANDING;
		}
	}
	
	
	
	
	/**
	 * RetroMan Goes Left
	 */
	public void goLeft() {
		
	}
	
	/**
	 * RetroMan Goes Right
	 */
	public void goRight() {
		
	}
	
	/**
	 * Rturns the direction which the retroMan Face
	 * @return FaceLeft true means Retroman looks left
	 */
	public boolean getFaceLeft() {
		return faceLeft;
	}
	
	
	/**
	 * 
	 * @param element
	 */
	public void pickupElement(GameElement element) {
		this.element = element;
	}
	
	/**
	 * returns if retroman has already picked up an elemnt
	 * @return
	 */
	public boolean hasPickedUpElement() {
		return element != null;
	}
	
	public GameElement layDownElement() {
		GameElement g = element;
		element = null;
		return g;
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

	private boolean canJump() {
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
