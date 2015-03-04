package com.retroMachines.game.controllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.Vertex;

/**
 * The GameController is part of the controller of RetroMachines. This class
 * represents the controller for the actual game. It sets up levels and also
 * disposes them afterwards. It saves progress to the persistent storage.
 * 
 * @author RetroFactory
 * 
 */
public class GameController {

	/**
	 * A reference to the main class for the different calls.
	 */
	private final RetroMachines game;

	/**
	 * The GameScreen for displaying the actual level to the user.
	 */
	private GameScreen gameScreen;

	/**
	 * The game character that is controlled by the user.
	 */
	private RetroMan retroMan;

	/**
	 * 
	 */
	private RetroLevel level;
	
	/**
	 * controls Evaluation
	 */
	private EvaluationController evaControl;
	
	/**
	 * level begin time
	 */
	private long levelBegin;
	
	/**
	 * variable to count the steps made by the character
	 */
	private float tempStepCounter;
	
	/**
	 * true if the level was finished successfully
	 */
	private boolean levelfinished;
	
	/**
	 * Makes a new instance of the GameController.
	 * 
	 * @param game
	 *            The game that is to be controlled.
	 */
	public GameController(RetroMachines game) {
		this.game = game;
	}

	

	/**
	 * Sets and initializes a given level and starts it.
	 * 
	 * @param levelId
	 *            ID of the level that is to be started.
	 */
	public void startLevel(int levelId) {
		levelfinished = false;
		SettingController settingController = game.getSettingController();
		retroMan = new RetroMan(Constants.TEXTURE_ANIMATION_NAMES[settingController.getCurrentCharacterId()]);
		tempStepCounter = 0;
		boolean left = game.getSettingController().getLeftMode();

		RetroLevel.LevelBuilder builder = new RetroLevel.LevelBuilder();
		builder.prepare(levelId);
		level = builder.getLevel();
		gameScreen = new GameScreen(game, this, left);
		gameScreen.setMap(level.getMap());
		levelBegin = System.currentTimeMillis();
		game.setScreen(gameScreen);
		if (level.hasTutorial() && settingController.getTutorialFinished(levelId)) {
			gameScreen.showDialogChain(level.getDialogChain());
			settingController.setTutorialFinished(levelId, true);
		}
	}

	/**
	 * Cut a level short.
	 */
	public void abortLevel() {
		AssetManager.reloadMap(level.getId() - 1);
		saveProgress();
		game.setScreen(new LevelMenuScreen(game));
	}

	/**
	 * Finishes the level once it has been completed including the evaluation.
	 * Afterwards the LevelMenuScreen will be shown to the user.
	 */
	public void levelFinished() {
		saveProgress();
		dispose();
		AssetManager.reloadMap(level.getId() - 1);
		game.getStatisticController().incLevelCompleted(level.getId());
		game.setScreen(new LevelMenuScreen(game));
	}

	/**
	 * Disposes all objects that are in use by this controller.
	 */
	private void dispose() {
		gameScreen.dispose();
	}

	/**
	 * Saves the progress of the game to the persistent storage.
	 */
	private void saveProgress() {
		long now = System.currentTimeMillis();
		float diff = (now - levelBegin) / 1000f;
		game.getStatisticController().incPlayTime(diff);
		game.getStatisticController().incStepCounter((int) tempStepCounter);
	}

	// --------------------------
	// --------Retro-Man---------
	// --------------------------

	/**
	 * Delegates a jump call to RetroMan.
	 */
	public void jumpRetroMan() {
		if (retroMan.canJump()) {
			retroMan.jump();
		}
	}

	/**
	 * Delegates an interact call to RetroMan.
	 */
	public void interactRetroMan() {
		if (!retroMan.canJump()) {
			// picking up elements does not work while falling
			return;
		}
		
		// end of level
		if(levelfinished) {
			Rectangle retroManRect = new Rectangle(retroMan.getPos().x,
					retroMan.getPos().y, RetroMan.WIDTH, RetroMan.HEIGHT);
			int startX, startY, endX, endY;
			if (retroMan.getVelocity().x > 0) {
				startX = endX = (int) (retroMan.getPos().x + RetroMan.WIDTH + retroMan
						.getVelocity().x);
			} else {
				startX = endX = (int) (retroMan.getPos().x + retroMan.getVelocity().x);
			}
			startY = (int) (retroMan.getPos().y);
			endY = (int) (retroMan.getPos().y + RetroMan.HEIGHT);
			Array<Rectangle> tiles = level.getTiles(startX, startY, endX, endY, Constants.DOOR_CLOSED_LAYER);
			for (Rectangle tile : tiles) {
				if (retroManRect.overlaps(tile)) {
					levelFinished();
				}
			}
		} else {
			// when evel finished no reposition possible
	 		Vector2 elementPosition = retroMan.nextPosition();
	 		GameElement element = level.getGameElement(elementPosition);
			if (retroMan.hasPickedUpElement() && element == null && level.isValidGameElementPosition(elementPosition)) {
				GameElement previous = retroMan.layDownElement();
				level.placeGameElement(previous, elementPosition);
			} else if (!retroMan.hasPickedUpElement() && element != null) {
				retroMan.pickupElement(element);
				level.removeGameElement(element, elementPosition);
			}
		}
	}

	/**
	 * Delegates a goLeft call to RetroMan.
	 */
	public void goLeftRetroMan() {
		retroMan.goLeft();
	}

	/**
	 * Delegates a goRight call to RetroMan.
	 */
	public void goRightRetroMan() {
		retroMan.goRight();
	}

	/**
	 * Returns the instance of the RetroMan.
	 * 
	 * @return The instance of the RetroMan which is held by this controller.
	 */
	public RetroMan getRetroMan() {
		return retroMan;
	}

	// --------------------------
	// --------Map-logic---------
	// --------------------------

	/**
	 * Performs a collision detection to stop the character in case of walls or
	 * any other solid object standing in the way.
	 * @param delta 
	 */
	public void update(float deltaTime) {
		if (Math.abs(retroMan.getVelocity().x) < RetroMan.MIN_VELOCITY_X && retroMan.canJump()) {
			retroMan.getVelocity().x = 0;
			retroMan.standing();
		}
		retroMan.getVelocity().add(0, Constants.WORLD_GRAVITY);
		if (Math.abs(retroMan.getVelocity().y) > RetroMan.MAX_VELOCITY_Y) {
			retroMan.getVelocity().y = Math.signum(retroMan.getVelocity().y) * RetroMan.MAX_VELOCITY_Y;
		}
		float previousPosition = retroMan.getPos().x;
		retroMan.getVelocity().scl(deltaTime);
		
		collisionDetection();
				
		retroMan.getPos().add(retroMan.getVelocity());
		retroMan.getVelocity().scl(1 / deltaTime);
		// update the step counter
		tempStepCounter += (retroMan.getPos().x - previousPosition);
		retroMan.getVelocity().x *= RetroMan.DAMPING;
		
	}
	
	private void collisionDetection() {
		Rectangle retroManRect = new Rectangle(retroMan.getPos().x,
				retroMan.getPos().y, RetroMan.WIDTH, RetroMan.HEIGHT);
		int startX, startY, endX, endY;
		if (retroMan.getVelocity().x > 0) {
			startX = endX = (int) (retroMan.getPos().x + RetroMan.WIDTH + retroMan
					.getVelocity().x);
		} else {
			startX = endX = (int) (retroMan.getPos().x + retroMan.getVelocity().x);
		}
		startY = (int) (retroMan.getPos().y);
		endY = (int) (retroMan.getPos().y + RetroMan.HEIGHT);
		Array<Rectangle> tiles = level.getTiles(startX, startY, endX, endY, Constants.SOLID_LAYER_ID);
		tiles.addAll(level.getTiles(startX, startY, endX, endY, Constants.DEPOT_LAYER));
		tiles.addAll(level.getTiles(startX, startY, endX, endY, Constants.OBJECT_LAYER_ID));
		retroManRect.x = retroManRect.x + retroMan.getVelocity().x;
		for (Rectangle tile : tiles) {
			if (retroManRect.overlaps(tile)) {
				retroMan.getVelocity().x = 0;
				break;
			}
		}
		
		retroManRect.x = retroMan.getPos().x;

		// detection upwards
		if (retroMan.getVelocity().y > 0) {
			startY = endY = (int) (retroMan.getPos().y + RetroMan.HEIGHT + retroMan
					.getVelocity().y);
		} else {
			startY = endY = (int) (retroMan.getPos().y + retroMan.getVelocity().y);
		}
		startX = (int) (retroMan.getPos().x);
		endX = (int) (retroMan.getPos().x + RetroMan.WIDTH);
		tiles = level.getTiles(startX, startY, endX, endY, Constants.SOLID_LAYER_ID);
		tiles.addAll(level.getTiles(startX, startY, endX, endY, Constants.DEPOT_LAYER));
		tiles.addAll(level.getTiles(startX, startY, endX, endY, Constants.OBJECT_LAYER_ID));
		retroManRect.y += retroMan.getVelocity().y;
		for (Rectangle tile : tiles) {
			if (retroManRect.overlaps(tile)) {
				if (retroMan.getVelocity().y > 0) {
					retroMan.getPos().y = tile.y - RetroMan.HEIGHT;
				} else {
					retroMan.getPos().y = tile.height + tile.y;
					retroMan.landed();
				}
				retroMan.getVelocity().y = 0;
				break;
			}
		}
	}

	// --------------------------
	// --------Evaluation--------
	// --------------------------

	/**
	 * Starts the evaluation process.
	 */
	public void evaluationClicked() {
		if (level.allDepotsFilled()) {
			evaControl = new EvaluationController(level, game, this);
			evaControl.startEvaluation();
		} else {
			gameScreen.showValidateError(level.getErrorMessage());
		}
	}
	
	public void evaluationComplete() {
		game.setScreen(gameScreen);
		level.getMap().getLayers().get(Constants.DOOR_CLOSED_LAYER).setVisible(false);
		levelfinished = true;
	}
	
	public void evaluationInComplete() {
		levelfinished = false;
		gameScreen.showValidateError(Constants.RetroStrings.SOLUTION_INVALID);
		game.setScreen(gameScreen);

	}
	
	/**
	 * checks if retroman is in the correct position and opens the door in case he is.
	 */
	public void walkThroughDoor() {
		if (!levelfinished) {
			return;
		}
		Rectangle retroManRect = new Rectangle(retroMan.getPos().x,
				retroMan.getPos().y, RetroMan.WIDTH, RetroMan.HEIGHT);
		int startX, startY, endX, endY;
		if (retroMan.getVelocity().x > 0) {
			startX = endX = (int) (retroMan.getPos().x + RetroMan.WIDTH + retroMan
					.getVelocity().x);
		} else {
			startX = endX = (int) (retroMan.getPos().x + retroMan.getVelocity().x);
		}
		startY = (int) (retroMan.getPos().y);
		endY = (int) (retroMan.getPos().y + RetroMan.HEIGHT);
		Array<Rectangle> tiles = level.getTiles(startX, startY, endX, endY, Constants.DOOR_CLOSED_LAYER);
		for (Rectangle tile : tiles) {
			if (retroManRect.overlaps(tile)) {
				levelFinished();
			}
		}
	}
	
	public Vertex getLevelHint() {
		return level.getLambdaUtil().getHintTree().getStart();
	}
	
	public Vertex getLevelTarget() {
		return level.getLambdaUtil().getTargetTree().getStart();
	}
}
