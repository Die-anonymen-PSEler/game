package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * The RetroMan Class contains information regarding the character that may run
 * through the world. It also provides a rendering method so the screen can show
 * it to the user. It does not hold any information regarding the map he is
 * moving on. Collision detection is also performed within the controller.
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
	private boolean faceLeft = false;

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
	 * contains a potential gameElement if the character has picked one up
	 */
	private GameElement element;

	/**
	 * Contain index of gamenElement in {@value vertexList} which is currently
	 * hold by RetroMan. -1, if nothing is hold.
	 */
	private int indexOfElementHold;

	/**
	 * the texture of the character
	 */
	private Texture texture;
	
	private float stateTime;
	
	private Animation standRight;
	private Animation standLeft;
	private Animation standERight;
	private Animation standELeft;
	private Animation runningRight;
	private Animation runningRightE;
	private Animation runningLeft;
	private Animation runningLeftE;
	private Animation jumpingRight;
	private Animation jumpingLeft;
	private Animation jumpingERight;
	private Animation jumpingELeft;
	
	/**
	 * the current velocity
	 */
	private final Vector2 velocity;

	/**
	 * Constructs a new Object of the RetroMan and sets his coordinates and
	 * velocity to 0,0
	 */
	public RetroMan() {
		pos = new Vector2();
		velocity = new Vector2();
		
		//The animation
		texture = new Texture("map/Animation.png");
		TextureRegion[] regions = TextureRegion.split(texture, 60, 64)[0];
		standRight = new Animation(0, regions[0]);
		standLeft = new Animation(0, regions[7]);
		standERight = new Animation(0, regions[2]);
		standELeft = new Animation(0, regions[5]);
		runningRight = new Animation(0.15f, regions[0], regions[1]);
		runningRight.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		runningRightE = new Animation(0.15f, regions[2], regions[3]);
		runningRightE.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		runningLeft = new Animation(0.15f, regions[6], regions[7]);
		runningLeft.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		runningLeftE = new Animation(0.15f, regions[4], regions[5]);
		runningLeftE.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		jumpingRight = new Animation(0, regions[8]);
		jumpingLeft = new Animation(0, regions[10]);
		jumpingERight = new Animation(0, regions[9]);
		jumpingELeft = new Animation(0, regions[11]);
	}

	/**
	 * jumping part
	 */

	/**
	 * Attempts a jump in case the character is allowed to do so. See canJump
	 * for further information as to the condition.
	 */
	public void jump() {
		if (canJump()) {
			if (hasPickedUpElement()) {
				if (faceLeft) {
					state = State.JUMPINGELEFT;
				} else {
					state = State.JUMPINGERIGHT;
				}
			} else {
				if (faceLeft) {
					state = State.JUMPINGLEFT;
				} else {
					state = State.JUMPINGRIGHT;
				}
			}
			velocity.add(0, 9);
		}
	}

	/**
	 * once the character has hit the bottom this method should be called in
	 * order to release the jump prohibition.
	 */
	public void landed() {
		if (faceLeft) {
		if (hasPickedUpElement()) {
			state = State.STANDINGELEFT;
		} else {
			state = State.STANDINGLEFT;
		}
		} else {
			if (hasPickedUpElement()) {
				state = State.STANDINGERIGHT;
			} else {
				state = State.STANDINGRIGHT;
			}
		}
	}

	/**
	 * Checks whether the character is allowed to jump in order to avoid double
	 * jumps in mid air.
	 * 
	 * @return true if the character can jump; false otherwise
	 */
	private boolean canJump() {
		if (state == State.JUMPINGLEFT || state == State.JUMPINGRIGHT || 
				state == State.JUMPINGELEFT || state == State.JUMPINGERIGHT) {
			return false;
		}
		return true;
	}

	/**
	 * moving
	 */

	/**
	 * Adds negative velocity to the character however this does not update his
	 * position a call to the update method is needed for that
	 */
	public void goLeft() {
		velocity.add(-9, 0);
		faceLeft = true;
		if (hasPickedUpElement()) {
			state = State.RUNNINGLEFTE;
		} else {
			state = State.RUNNINGLEFT;
		}

		//updateRetroMan(deltaTime);
	}

	/**
	 * Adds positive velocity to the character however this does not update his
	 * position a call to the update method is needed for that
	 */
	public void goRight() {
		velocity.add(9, 0);
		faceLeft = false;
		if (hasPickedUpElement()) {
			state = State.RUNNINGRIGHTE;
		}
		else {
		state = State.RUNNINGRIGHT;
		}
		//updateRetroMan(deltaTime);
	}

	/**
	 * Returns the direction which the retroMan Face
	 * 
	 * @return FaceLeft true means the RetroMan is looking to the left
	 */
	public boolean getFaceLeft() {
		return faceLeft;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	/**
	 * 
	 */
	public void updateRetroMan(float deltaTime) {
		if (deltaTime == 0) {
			return;
		}

	}

	/**
	 * Interacting with the game elements.
	 */

	/**
	 * 
	 * @param element
	 */
	public void pickupElement(GameElement element) {
		this.element = element;
	}

	/**
	 * Returns true if the RetroMan has already picked up an element.
	 * 
	 * @return true if the RetroMan currently holds a game element; false
	 *         otherwise.
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
			//TODO Ändern
			Batch batch = null;
			float parentAlpha = 100;
			element.draw(batch, parentAlpha);
		}
		// render the RetroMan
		renderRetroMan(deltaTime);
		// TODO RENDER myself
		return null;
	}

	private void renderRetroMan(float deltaTime) {
		// based on the RetroMan state, get the animation frame
		TextureRegion frame = null;
		switch (state) {
		case STANDINGRIGHT:
			frame = standRight.getKeyFrame(stateTime);
			break;
		case STANDINGERIGHT:
			frame = standERight.getKeyFrame(stateTime);
			break;
		case STANDINGLEFT:
			frame = standLeft.getKeyFrame(stateTime);
			break;
		case STANDINGELEFT:
			frame = standELeft.getKeyFrame(stateTime);
			break;
		case RUNNINGRIGHT:
			frame = runningRight.getKeyFrame(stateTime);
			break;
		case RUNNINGRIGHTE:
			frame = runningRightE.getKeyFrame(stateTime);
			break;
		case RUNNINGLEFT:
			frame = runningLeft.getKeyFrame(stateTime);
			break;
		case RUNNINGLEFTE:
			frame = runningLeftE.getKeyFrame(stateTime);
			break;
		case JUMPINGRIGHT:
			frame = jumpingRight.getKeyFrame(stateTime);
			break;
		case JUMPINGERIGHT:
			frame = jumpingERight.getKeyFrame(stateTime);
			break;
		case JUMPINGLEFT:
			frame = jumpingLeft.getKeyFrame(stateTime);
			break;
		case JUMPINGELEFT:
			frame = jumpingELeft.getKeyFrame(stateTime);
			break;
		}
	}
	
	/**
	 * subclasses and enums
	 */

	/**
	 * The states in which the figure can be in.
	 * 
	 * @author RetroMachines
	 * 
	 */
	private enum State {
		/**
		 * if the character is facing right and is standing on solid ground and not moving he is
		 * STANDINGRIGHT
		 */
		STANDINGRIGHT,
		
		/**
		 * if the character is facing left and is standing on solid ground and not moving he is
		 * STANDINGLEFT
		 */
		STANDINGLEFT,

		/**
		 * if the character is facing right and is standing on solid ground and he is carrying an 
		 * element and he is not moving he is STANDINGERIGHT
		 */
		STANDINGERIGHT,
		
		/**
		 * if the character is facing left and is standing on solid ground and he is carrying an 
		 * element and he is not moving he is STANDINGELEFT
		 */
		STANDINGELEFT,
		
		/**
		 * if the characters x-velocity is not 0 and x-velocity is >0 and he is on solid ground he is
		 * RUNNINGRIGHT
		 */
		RUNNINGRIGHT,

		/**
		 * if the characters x-velocity is not 0 and x-velocity is >0 and he is carrying an element and
		 * he is on solid ground he is RUNNINGRIGHTE
		 */
		RUNNINGRIGHTE,
		
		/**
		 * if the characters x-velocity is not 0 and x-velocity is <0 and he is on solid ground he is
		 * RUNNINGLEFT
		 */
		RUNNINGLEFT,
		
		/**
		 * if the characters x-velocity is not 0 and x-velocity is <0. He is carrying an element and
		 * he is on solid ground he is RUNNINGLEFTE
		 */
		RUNNINGLEFTE,

		/**
		 * if the character is facing right and is not on solid ground then he is JUMPINGLEFT. His x-velocity
		 * may be 0
		 */
		JUMPINGRIGHT,	
		
		/**
		 * if the character is facing left and is not on solid ground and he is carrying an element, 
		 * he is JUMPINGERIght. His x-velocity may be 0
		 */
		JUMPINGERIGHT,
		
		/**
		 * if the character is facing left and is not on solid ground then he is JUMPINGLEFT. His x-velocity
		 * may be 0
		 */
		JUMPINGLEFT,	
		
		/**
		 * if the character is facing left and is not on solid ground and he is carrying an element, 
		 * he is JUMPINGELEFT. His x-velocity may be 0
		 */
		JUMPINGELEFT		

	}

}
