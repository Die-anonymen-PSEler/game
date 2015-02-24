package com.retroMachines.game.controllers;

import java.util.Date;
import java.util.LinkedList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.game.RetroLevel.LevelBuilder;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.LambdaUtil;
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
	private Date levelBegin;
	
	/**
	 * 
	 */
	private float tempStepCounter;
	
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
		retroMan = new RetroMan();
		tempStepCounter = 0;
		boolean left = game.getSettingController().getLeftMode();
		gameScreen = new GameScreen(game, this, left);
		RetroLevel.LevelBuilder builder = new RetroLevel.LevelBuilder();
		builder.prepare(levelId);
		level = builder.getLevel();
		gameScreen.setMap(level.getMap());
		levelBegin = new Date();
		game.setScreen(gameScreen);
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
		Date now = new Date();
		long diff = now.getTime() - levelBegin.getTime();
		long diffMinutes = diff / (60 * 1000) % 60;
		game.getStatisticController().incPlayTime(diffMinutes);
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
		retroMan.getVelocity().add(0, Constants.WORLD_GRAVITY);

		if (Math.abs(retroMan.getVelocity().y) > RetroMan.MAX_VELOCITY_Y) {
			retroMan.getVelocity().y = Math.signum(retroMan.getVelocity().y) * RetroMan.MAX_VELOCITY_Y;
		}
		
		if (Math.abs(retroMan.getVelocity().x) < RetroMan.MIN_VELOCITY_X && retroMan.canJump()) {
			retroMan.getVelocity().x = 0;
			retroMan.standing();
		}
		// multiply by delta time so we know how far we go
		// in this frame
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
		//collision detection
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
			evaControl = new EvaluationController(level, game);
			evaControl.startEvaluation();
		} else {
			gameScreen;
		}
	}
	
	/**
	 * checks if retroman is in the correct position and opens the door in case he is.
	 */
	/*
	public void doorTestMethod() {
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
		Array<Rectangle> tiles = getTiles(startX, startY, endX, endY, Constants.DOOR_CLOSED_LAYER);
		for (Rectangle tile : tiles) {
			if (retroManRect.overlaps(tile)) {
				map.getLayers().get(Constants.DOOR_CLOSED_LAYER).setVisible(false);
			}
		}
	}
	*/
}
