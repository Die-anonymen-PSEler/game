package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.util.Constants;

/**
 * The RetroMan Class contains information regarding the character that may run
 * through the world. It also provides a rendering method so the screen can show
 * it to the user. It does not hold any information regarding the map he is
 * moving on. Collision detection is also performed within the controller.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class RetroMan {

	/**
	 * The maximum velocity the character is allowed to have in y direction.
	 */
	public static final float MAX_VELOCITY_Y = 15f;

	/**
	 * The width of the character for collision purposes.
	 */
	public final static float WIDTH = 0.95f;

	/**
	 * The height of the character for collision purposes.
	 */
	public final static float HEIGHT = 0.95f;

	/**
	 * The impulse that is added to the retroMan upon jumping.
	 */
	public final static float JUMPING_IMPULSE = 25f;

	/**
	 * The impulse that is added to the retroMan upon running.
	 */
	public static final float RUNNING_IMPULSE = 1f;

	/**
	 * The minimum velocity that is considered moving.
	 */
	public static final float MIN_VELOCITY_X = 1f;

	/**
	 * The amount by which the x velocity is reduced so the character will get
	 * slower.
	 */
	public static final float DAMPING = 0.87f;

	/**
	 * The distance to placing a game element.
	 */
	public static final int ELEMENT_OFFSET = 1;

	//The position of the character on the screen.
	private final Vector2 pos;

	//The current velocity.
	private final Vector2 velocity;
	
	//True if the character is looking to the left. False otherwise.
	private boolean faceLeft = false;

	/*
	 * If the character is jumping the attribute should be true prevents jumping
	 * while the character is already in the air.
	 */
	private State state;


	 // Contains a potential gameElement if the character has picked one up

	private GameElement element;

	// The texture of the character.
	private Texture texture;

	// RetroMan is looking to the right and stands.
	private Animation standRight;
	
	// RetroMan is looking to the right, carries an element and stands.
	private Animation standERight;
	
	// RetroMan is looking to the right and is jumping.
	private Animation jumpingRight;
	
	// RetroMan is looking to the right, carries an element and is jumping.
	private Animation jumpingERight;
	
	// The animation of RetroMan while running.
	private Animation runingAnimation;
	
	// The animation of a running RetroMan if he is carrying an element.
	private Animation runingAnimationCarry;

	// The sum of the time played.
	private float timeSum;

	/**
	 * Constructs a new object of the RetroMan and sets his coordinates and
	 * velocity to 0,0.
	 * 
	 * @param textureName The name of the texture.
	 */
	public RetroMan(String textureName) {
		this(textureName, 15, 5);
	}

	/**
	 * Constructor to initialize a new instance of RetroMan.
	 * @param textureName The name of the texture of RetroMan.
	 * @param x The x position at the starting point.
	 * @param y The y position at the starting point.
	 */
	public RetroMan(String textureName, float x, float y) {
		pos = new Vector2(x, y);
		velocity = new Vector2();

		state = State.STANDING;

		// The animation
		texture = RetroAssetManager.getTexture(textureName);
		TextureRegion[] regions = TextureRegion.split(texture, 60, 64)[0];
		TextureRegion[] runingFrames = new TextureRegion[2];
		TextureRegion[] runingFramesCarry = new TextureRegion[2];
		runingFrames[0] = regions[0];
		runingFrames[1] = regions[1];
		runingFramesCarry[0] = regions[2];
		runingFramesCarry[1] = regions[3];

		runingAnimation = new Animation(0.25f, runingFrames);
		runingAnimationCarry = new Animation(0.25f, runingFramesCarry);
		standRight = new Animation(0, regions[0]);
		standERight = new Animation(0, regions[2]);
		jumpingRight = new Animation(0, regions[4]);
		jumpingERight = new Animation(0, regions[5]);

		timeSum = 0;
	}

	private void renderRetroMan(BatchTiledMapRenderer renderer, float deltaTime) {
		// based on the RetroMan state, get the animation frame
		TextureRegion frame = null;
		timeSum += deltaTime;
		switch (state) {
		case STANDING:
			frame = standRight.getKeyFrame(deltaTime);
			break;
		case STANDINGE:
			frame = standERight.getKeyFrame(deltaTime);
			break;
		case RUNNING:
			frame = runingAnimation.getKeyFrame(timeSum, true);
			break;
		case RUNNINGE:
			frame = runingAnimationCarry.getKeyFrame(timeSum, true);
			break;
		case JUMPING:
			frame = jumpingRight.getKeyFrame(deltaTime);
			break;
		case JUMPINGE:
			frame = jumpingERight.getKeyFrame(deltaTime);
			break;
		default:
			break;
		}

		Batch batch = renderer.getBatch();
		batch.begin();
		if (faceLeft) {
			batch.draw(frame, pos.x + WIDTH, pos.y, -WIDTH, HEIGHT);
		} else {
			batch.draw(frame, pos.x, pos.y, WIDTH, HEIGHT);
		}
		batch.end();
	}

	/*
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
			velocity.add(0, JUMPING_IMPULSE);
		}
	}

	/**
	 * Once the character has hit the bottom this method should be called in
	 * order to release the jump prohibition.
	 */
	public void landed() {
		if (state == State.JUMPING || state == State.JUMPINGE) {
			if (hasPickedUpElement()) {
				state = State.STANDINGE;
			} else {
				state = State.STANDING;
			}
		}
	}

	/**
	 * Checks whether the character is allowed to jump in order to avoid double
	 * jumps in mid air.
	 * 
	 * @return True if the character can jump. False otherwise.
	 */
	public boolean canJump() {
		if (state == State.JUMPING || state == State.JUMPINGE
				|| Math.abs(velocity.y) > Constants.FLOAT_EPSILON) {
			return false;
		}
		return true;
	}

	/*
	 * moving
	 */

	/**
	 * Adds negative velocity to the character however this does not update his
	 * position a call to the update method is needed for that.
	 */
	public void goLeft() {
		velocity.add(-RUNNING_IMPULSE, 0);
		faceLeft = true;
		if (state == State.STANDING) {
			state = State.RUNNING;
		} else if (state == State.STANDINGE) {
			state = State.RUNNINGE;
		}
	}

	/**
	 * Adds positive velocity to the character however this does not update his
	 * position a call to the update method is needed for that.
	 */
	public void goRight() {
		velocity.add(RUNNING_IMPULSE, 0);
		faceLeft = false;
		if (state == State.STANDING) {
			state = State.RUNNING;
		} else if (state == State.STANDINGE) {
			state = State.RUNNINGE;
		}
	}

	/**
	 * Set the state of RetroMan to standing in regards to whether he is carrying a 
	 * game element or not.
	 */
	public void standing() {
		if (hasPickedUpElement()) {
			state = State.STANDINGE;
		} else {
			state = State.STANDING;
		}
	}

	/**
	 * Get method for the direction which the RetroMan faces.
	 * 
	 * @return FaceLeft true means the RetroMan is looking to the left.
	 */
	public boolean isFacedLeft() {
		return faceLeft;
	}

	/**
	 * Get method for the position that would be next to him. Takes into account his
	 * facing direction.
	 */
	public Vector2 nextPosition() {
		int offset;
		Vector2 elementPos;
		if (isFacedLeft()) {
			offset = -ELEMENT_OFFSET;
			elementPos = new Vector2(((int) pos.x) + offset, (int) pos.y);
		} else {
			offset = ELEMENT_OFFSET;
			elementPos = new Vector2(((int) (pos.x + WIDTH)) + offset,
					(int) pos.y);
		}
		return elementPos;
	}

	/*
	 * Interacting with the game elements.
	 */

	/**
	 * Method to pick up an element.
	 * @param element The element that is picked up.
	 */
	public void pickupElement(GameElement element) {
		this.element = element;
		switch (state) {
		case STANDING:
			state = State.STANDINGE;
			break;
		case RUNNING:
			state = State.RUNNINGE;
			break;
		default:
			break;
		}
	}

	/**
	 * Get method for whether RetroMan has already picked up an element or not.
	 * 
	 * @return True if the RetroMan currently holds a game element. False
	 *         otherwise.
	 */
	public boolean hasPickedUpElement() {
		return element != null;
	}

	/**
	 * Method to lay down an element.
	 * 
	 * @return The element which was laid down.
	 */
	public GameElement layDownElement() {
		GameElement gameElement = element;
		element = null;
		switch (state) {
		case STANDINGE:
			state = State.STANDING;
			break;
		case RUNNINGE:
			state = State.RUNNING;
			break;
		default:
			break;
		}
		return gameElement;
	}

	/*
	 * rendering
	 */

	/**
	 * Renders the RetroMan.
	 * 
	 * @param renderer The texture of the RetroMan.
	 * @param deltaTime The scale for the position.
	 */
	public void render(BatchTiledMapRenderer renderer, float deltaTime) {
		renderRetroMan(renderer, deltaTime);
		if (hasPickedUpElement()) {
			TextureRegion elementTexture = element.getTextureRegion();
			Batch batch = renderer.getBatch();
			batch.begin();
			batch.draw(elementTexture, pos.x, pos.y + HEIGHT, 1, 1);
			batch.end();
		}
	}

	/*
	 * getters and setters
	 */

	/**
	 * Get method for the velocity of the RetroMan.
	 * 
	 * @return The velocity of RetroMan.
	 */
	public Vector2 getVelocity() {
		return velocity;
	}

	/**
	 * Retrieves the position of the character as a Vector2.
	 * 
	 * @return The current position as a Vector2.
	 */
	public Vector2 getPos() {
		return pos;
	}

	/*
	 * enums
	 */

	/**
	 * The states in which the figure can be in.
	 * 
	 * @author RetroMachines
	 * @version 1.0
	 */
	private enum State {
		/**
		 * If the character is facing right and is standing on solid ground and
		 * not moving he is STANDINGRIGHT.
		 */
		STANDING,

		/**
		 * If the character is facing right and is standing on solid ground and
		 * he is carrying an element and he is not moving he is STANDINGERIGHT.
		 */
		STANDINGE,

		/**
		 * If the characters x-velocity is not 0 and x-velocity is >0 and he is
		 * on solid ground he is RUNNINGRIGHT.
		 */
		RUNNING,

		/**
		 * If the characters x-velocity is not 0 and x-velocity is >0 and he is
		 * carrying an element and he is on solid ground he is RUNNINGRIGHTE.
		 */
		RUNNINGE,

		/**
		 * If the character is facing right and is not on solid ground then he
		 * is JUMPINGLEFT. His x-velocity may be 0.
		 */
		JUMPING,

		/**
		 * If the character is facing left and is not on solid ground and he is
		 * carrying an element, he is JUMPINGERIght. His x-velocity may be 0.
		 */
		JUMPINGE
	}

}
