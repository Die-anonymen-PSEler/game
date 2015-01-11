package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * RetroMan Class This class contains information regarding the character that
 * may run through the world. It also provides a rendering method so the screen
 * can show it to the user. It does not hold any information regarding the map
 * he is moving on. Collision detection is also performed within the controller.
 * 
 * @author RetroFactory
 * 
 */
public class RetroMan {

	/**
	 * The maximum velocity the character is allowed to have in x direction
	 */
	public static final float MAX_VELOCITY_X = 10f;

	/**
	 * The maximum velocity the character is allowed to have in y direction
	 */
	public static final float MAX_VELOCITY_Y = 10f;

	/**
	 * the width of the character for collision purposes
	 */
	public static final float WIDTH = 5f;

	/**
	 * the height of the character for collision purposes
	 */
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
	 * if the character is jumping the attribute should be true prevents jumping
	 * while the character is already in the air
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
	 * Constructs a new Object of the RetroMan and sets his coordinates and
	 * velocity to 0,0
	 */
	public RetroMan() {
		pos = new Vector2();
		velocity = new Vector2();
	}

	/**
	 * jumping part
	 */

	/**
	 * Attempts a jump in case the character is allowed to do so. See canJump
	 * for further information as to the condition.
	 */
	public void jump() {
		if (!canJump()) {
			state = State.JUMPING;
			velocity.add(9, 0);
		}
	}

	/**
	 * once the character has hit the bottom this method should be called in
	 * order to release the jump prohibition.
	 */
	public void landed() {
		if (canJump()) {
			state = State.STANDING;
		}
	}

	/**
	 * Checks whether the character is allowed to jump in order to avoid double
	 * jumps in mid air.
	 * 
	 * @return true if the character can jump; false otherwise
	 */
	private boolean canJump() {
		return state != State.JUMPING;
	}

	/**
	 * moving
	 */

	/**
	 * Adds negative velocity to the character however this does not update his
	 * position a call to the update method is needed for that
	 */
	public void goLeft() {

	}

	/**
	 * Adds positive velocity to the character however this does not update his
	 * position a call to the update method is needed for that
	 */
	public void goRight() {

	}

	/**
	 * Rturns the direction which the retroMan Face
	 * 
	 * @return FaceLeft true means Retroman looks left
	 */
	public boolean getFaceLeft() {
		return faceLeft;
	}

	/**
	 * 
	 */
	public void updateRetroMan() {

	}

	/**
	 * interacting with gameeelements
	 */

	/**
	 * 
	 * @param element
	 */
	public void pickupElement(GameElement element) {
		this.element = element;
	}

	/**
	 * returns if retroman has already picked up an elemnt
	 * 
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
	 * getters and setters
	 */

	/**
	 * Retrieves the position of the character as a Vector2
	 * 
	 * @return the current position as a Vector2
	 */
	public Vector2 getPos() {
		return pos;
	}

	/**
	 * rendering
	 */

	/**
	 * 
	 * @param deltaTime
	 */
	public TextureRegion render(float deltaTime) {
		if (hasPickedUpElement()) {
			element.render(deltaTime);
		}
		// TODO RENDER myself
		return null;
	}

	/**
	 * subclasses and enums
	 */

	/**
	 * the states in which the figure can be in
	 * 
	 * @author lucabecker
	 * 
	 */
	private enum State {
		/**
		 * if the character is standing on solid ground and not moving he is
		 * STANDING
		 */
		STANDING,

		/**
		 * if the characters x-velocity is not 0 and he is on solid ground he is
		 * RUNNING
		 */
		RUNNIG,

		/**
		 * if the character is not on solid ground he is JUMPING. his x-velocity
		 * may be 0
		 */
		JUMPING
	}
}
