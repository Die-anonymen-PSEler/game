package com.retroMachines.game.controllers;

import java.util.ArrayList;

import com.retroMachines.data.AssetManager;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.Tree;

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
	 * the layer where the walls are retroMan can't go trough
	 */
	public static final int WALL_LAYER = 5;

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
	 * The map that is currently active and may be shown to the user if the
	 * gameScreen is active.
	 */
	private TiledMap map;

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
	 * controls Evaluation
	 */
	private EvaluationController evaControl;

	/**
	 * Sets and initializes a given level and starts it.
	 * 
	 * @param levelId
	 *            ID of the level that is to be started.
	 */
	public void startLevel(int levelId) {
		retroMan = new RetroMan();
		boolean left = game.getSettingController().getLeftMode();
		gameScreen = new GameScreen(game, this, left);
		map = AssetManager.loadMap(levelId);
		gameScreen.setMap(map);
		game.setScreen(gameScreen);
	}

	/**
	 * Cut a level short.
	 */
	public void abortLevel() {

	}

	/**
	 * Finishes the level once it has been completed including the evaluation.
	 * Afterwards the LevelMenuScreen will be shown to the user.
	 */
	public void levelFinished() {
		saveProgress();
		dispose();

		game.setScreen(new LevelMenuScreen(game));
	}

	/**
	 * Disposes all objects that are in use by this controller.
	 */
	private void dispose() {
		// TODO Auto-generated method stub
		gameScreen.dispose();

	}

	/**
	 * Saves the progress of the game to the persistent storage.
	 */
	private void saveProgress() {

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
		GameElement element = standsBesideGameElement();
		if (retroMan.hasPickedUpElement()) {
			if (element != null) {
				return;
			} else {
				retroMan.layDownElement();
			}
		} else {
			if (element != null) {
				retroMan.pickupElement(element);
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
	 * Returns GameElement at a given position in TiledMap and deletes it.
	 * 
	 * @param posObj
	 *            Position in TiledMap of the GameElement.
	 * @return The GameElement at this position ( null when empty).
	 */
	public GameElement getGameElement(Vector2 posObj) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);
		Cell cell = layer.getCell((int) posObj.x, (int) posObj.y);
		cell.getTile();
		return null;
	}

	/**
	 * Returns a List of GameElements which are in the depots. If one depot
	 * doesn't contain an Element, it returns null.
	 * 
	 * @return Null, if not all depots are filled, else a list of GameElements
	 *         which are placed in the depots.
	 */
	public ArrayList<GameElement> checkDepotPositions() {
		ArrayList<GameElement> depotElements = new ArrayList<GameElement>();
		return depotElements;
	}

	/**
	 * Sets GameElement at a specific Position in the TiledMap.
	 * 
	 * @param posObj
	 *            Position where the Object should be placed.
	 * @param element
	 *            Element which should be placed.
	 * @return True if the element was placed successfully; otherwise false.
	 */
	public boolean setGameElement(Vector2 posObj, GameElement element) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);
		Cell cell = layer.getCell((int) posObj.x, (int) posObj.y);
		cell.getTile();
		// TODO: Element in TiledMapTile umwandeln und setzen
		// cell.setTile(element);
		return false;
	}

	/**
	 * Checks if RetroMan stands beside a GameElement that he can pick up.
	 * 
	 * @return if he stands next to a GameElement the element; null otherwise.
	 */
	private GameElement standsBesideGameElement() {
		return null;
	}

	/**
	 * Performs a collision detection to stop the character in case of walls or
	 * any other solid object standing in the way.
	 */
	public void collisionDetection(int layer) {
		int startX, startY, endX, endY;
		if (retroMan.getVelocity().x > 0) {
			startX = endX = (int) (retroMan.getPos().x + retroMan.WIDTH + retroMan
					.getVelocity().x);
		} else {
			startX = endX = (int) (retroMan.getPos().x + retroMan.getVelocity().x);
		}
		if (retroMan.getVelocity().y > 0) {
			startY = endY = (int) (retroMan.getPos().y + retroMan.HEIGHT + retroMan
					.getVelocity().y);
		} else {
			startY = endY = (int) (retroMan.getPos().y + retroMan.getVelocity().y);
		}
		TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer) map
				.getLayers().get(layer);
		MapObjects objects = collisionObjectLayer.getObjects();
		Rectangle playerRectangle = new Rectangle((float) startX,
				(float) startY, (float) endX, (float) endY);

		for (RectangleMapObject rectangleObject : objects
				.getByType(RectangleMapObject.class)) {

			Rectangle rectangle = rectangleObject.getRectangle();
			if (Intersector.overlaps(rectangle, playerRectangle)) {
				System.out.println("collision detected");
				getRetroMan().setVelocity(0f, 0f);
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
		if (checkPlacementofElements()) {
			evaControl = new EvaluationController(buildlambdaTree(), game);
			checkEvaluationResult();
		}
	}

	/**
	 * Checks if all the depots contain an element.
	 * 
	 * @return True if all the depots contain an element.
	 */
	private boolean checkPlacementofElements() {
		ArrayList<GameElement> depotElements = checkDepotPositions();
		return false;
	}

	/**
	 * Builds the lambdaTree for the evaluation with data of the map.
	 * 
	 * @return returns the Tree Object
	 */
	private Tree buildlambdaTree() {
		return null;

	}

	/**
	 * Is called after the evaluation and checks if the result is the correct
	 * one to finish the level.
	 */
	private void checkEvaluationResult() {

	}
}
