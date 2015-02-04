package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.retroMachines.util.Constants;

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
	public static final float WIDTH = 1.25f;

	/**
	 * the height of the character for collision purposes
	 */
	public static final float HEIGHT = 1.25f;

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
	private Animation standERight;
	private Animation runningRight;
	private Animation runningRightE;
	private Animation jumpingRight;
	private Animation jumpingERight;
	
	/**
	 * the current velocity
	 */
	private final Vector2 velocity;

	/**
	 * Constructs a new Object of the RetroMan and sets his coordinates and
	 * velocity to 0,0
	 */
	public RetroMan() {
		this(15, 5);
	}
	
	public RetroMan(float x, float y) {
		pos = new Vector2(x, y);
		velocity = new Vector2();
		
		state = State.STANDING;
		
		//The animation
		texture = new Texture("map/Animation.png");
		TextureRegion[] regions = TextureRegion.split(texture, 60, 64)[0];
		standRight = new Animation(0, regions[0]);
		standERight = new Animation(0, regions[2]);
		runningRight = new Animation(0.15f, regions[0], regions[1]);
		runningRight.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		runningRightE = new Animation(0.15f, regions[2], regions[3]);
		runningRightE.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		jumpingRight = new Animation(0, regions[8]);
		jumpingERight = new Animation(0, regions[9]);
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
				state = State.JUMPINGE;
			} else {
				state = State.JUMPING;
			}
			velocity.add(0, 1);
		}
	}

	/**
	 * once the character has hit the bottom this method should be called in
	 * order to release the jump prohibition.
	 */
	public void landed() {
		if (hasPickedUpElement()) {
			state = State.STANDINGE;
		} else {
			state = State.STANDING;
		}
	}

	/**
	 * Checks whether the character is allowed to jump in order to avoid double
	 * jumps in mid air.
	 * 
	 * @return true if the character can jump; false otherwise
	 */
	public boolean canJump() {
		if (state == State.JUMPING || state == State.JUMPINGE) {
			return false;
		}
		return true;
	}

	/**
	 * moving
	 */
	
	/**
	 * Sets the velocity.
	 */
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}

	/**
	 * Adds negative velocity to the character however this does not update his
	 * position a call to the update method is needed for that
	 */
	public void goLeft() {
		velocity.add(-0.5f, 0);
		faceLeft = true;
		if (state == State.STANDING) {
			state = State.RUNNING;
		}
		else if (state == State.STANDINGE) {
			state = State.RUNNINGE;
		}
	}

	/**
	 * Adds positive velocity to the character however this does not update his
	 * position a call to the update method is needed for that
	 */
	public void goRight() {
		velocity.add(0.5f, 0);
		faceLeft = false;
		if (state == State.STANDING) {
			state = State.RUNNING;
		}
		else if (state == State.STANDINGE) {
			state = State.RUNNINGE;
		}
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
		pos.add(velocity);
		velocity.x  = 0;
		if (Math.abs(velocity.y) > MAX_VELOCITY_Y) {
			if (velocity.y < 0) {
				velocity.y = -MAX_VELOCITY_Y;
			} else {
				velocity.y  = MAX_VELOCITY_Y;
			}
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
	public TextureRegion render(BatchTiledMapRenderer renderer, float deltaTime) {
		if (hasPickedUpElement()) {
			//TODO ändern
			Batch batch = null;
			float parentAlpha = 100;
			element.draw(batch, parentAlpha);
		}
		// render the RetroMan
		renderRetroMan(renderer, deltaTime);
		// TODO RENDER myself
		return null;
	}

	private void renderRetroMan(BatchTiledMapRenderer renderer, float deltaTime) {
		// based on the RetroMan state, get the animation frame
		TextureRegion frame = null;
		switch (state) {
		case STANDING:
			frame = standRight.getKeyFrame(stateTime);
			break;
		case STANDINGE:
			frame = standERight.getKeyFrame(stateTime);
			break;
		case RUNNING:
			frame = runningRight.getKeyFrame(stateTime);
			break;
		case RUNNINGE:
			frame = runningRightE.getKeyFrame(stateTime);
			break;
		case JUMPING:
			frame = jumpingRight.getKeyFrame(stateTime);
			break;
		case JUMPINGE:
			frame = jumpingERight.getKeyFrame(stateTime);
			break;
		}
		
		Batch batch = renderer.getBatch();
		batch.begin();
		if (faceLeft) {
			batch.draw(frame, pos.x + WIDTH, pos.y, -WIDTH, HEIGHT);
		}
		else {
			batch.draw(frame, pos.x, pos.y, WIDTH, HEIGHT);
		}
		batch.end();
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
		STANDING,

		/**
		 * if the character is facing right and is standing on solid ground and he is carrying an 
		 * element and he is not moving he is STANDINGERIGHT
		 */
		STANDINGE,
		
		/**
		 * if the characters x-velocity is not 0 and x-velocity is >0 and he is on solid ground he is
		 * RUNNINGRIGHT
		 */
		RUNNING,

		/**
		 * if the characters x-velocity is not 0 and x-velocity is >0 and he is carrying an element and
		 * he is on solid ground he is RUNNINGRIGHTE
		 */
		RUNNINGE,

		/**
		 * if the character is facing right and is not on solid ground then he is JUMPINGLEFT. His x-velocity
		 * may be 0
		 */
		JUMPING,	
		
		/**
		 * if the character is facing left and is not on solid ground and he is carrying an element, 
		 * he is JUMPINGERIght. His x-velocity may be 0
		 */
		JUMPINGE
	}

}
