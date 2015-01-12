package com.retroMachines.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;

/**
 * GameController This class represents the controller for the actual game. It
 * sets up levels and also disposes them afterwards. It saves progress to the
 * persistent storage.
 * 
 * @author RetroFactory
 * 
 */
public class GameController {

	/**
	 * a reference to the main class for calls
	 */
	private final RetroMachines game;

	/**
	 * The GameScreen for displaying the actual level to the player
	 */
	private GameScreen gameScreen;

	/**
	 * The EvaluationScreen when the player triggers this part of the game
	 */
	private EvaluationScreen evaluationScreen;

	/**
	 * the game character that is controlled by the player
	 */
	private RetroMan retroMan;

	/**
	 * the map that is currently active and may be shown to the user in case the
	 * gameScreen is also active
	 */
	private TiledMap map;

	/**
	 * makes an instance of GameController
	 * 
	 * @param game
	 */
	public GameController(RetroMachines game) {
		this.game = game;
		retroMan = new RetroMan();
	}

	/**
	 * Initializes a given level and fires it up.
	 * 
	 * @param levelId
	 *            the level to be started
	 */
	public void startLevel(int levelId) {
		boolean lefti = game.getSettingController().getLeftiMode();
		gameScreen = new GameScreen(game, this, lefti);
		gameScreen.setMap(map);
		game.setScreen(gameScreen);
	}
	
	/**
	 * Cut a level short.
	 */
	public void abortLevel() {
		
	}

	/**
	 * This method removes the GameScreen and puts up the EvaluationScreen. It
	 * will also trigger the evaluation.
	 */
	private void enterEvaluation() {
		evaluationScreen = new EvaluationScreen(game, this);
		game.setScreen(evaluationScreen);
	}

	/**
	 * this method will be called once a level has been complete including the
	 * evaluation. Afterwards the LevelMenuScreen will be shown to the user
	 */
	public void levelFinished() {
		saveProgress();
		dispose();

		game.setScreen(new LevelMenuScreen(game));
	}

	/**
	 * disposes all objects that are in use by this controller.
	 */
	private void dispose() {
		// TODO Auto-generated method stub
		gameScreen.dispose();

	}

	/**
	 * saves the progress to the persistent storage
	 */
	private void saveProgress() {

	}

	/**
	 * Checks if all stackers have an element in it
	 * 
	 * @return true if alle stackers have an element in it
	 */
	private boolean checkPlacementofElements() {
		ArrayList<GameElement> stackerElements = checkStackerPositions();
		return false;
	}

	// -------------------------------
	// --------Show something---------
	// -------------------------------

	/**
	 * Shows the Level Menu Screen on gameScreen
	 */
	public void showLevelMenuScreen() {

	}

	/**
	 * Shows the Task Screen on gameScreen
	 */
	public void showTask() {

	}

	/**
	 * Shows the Hint Screen on gameScreen
	 */
	public void showHint() {

	}

	// --------------------------
	// --------Retro-Man---------
	// --------------------------

	/**
	 * delegates a jump call to retroMan
	 */
	public void jumpRetroMan() {
		// TODO Auto-generated method stub
	}

	/**
	 * delegates an interact call to retroMan
	 */
	public void interactRetroMan() {
		// TODO Auto-generated method stub
	}

	/**
	 * delegates a goLeft call to retroMan
	 */
	public void goLeftRetroMan() {

	}

	/**
	 * delegates a goRight call to retroMan
	 */
	public void goRightRetroMan() {

	}

	/**
	 * returns the RetroMan instance
	 * @return the RetroMan instance that is held by this controller
	 */
	public RetroMan getRetroMan() {
		// TODO Auto-generated method stub
		return retroMan;
	}

	/**
	 * Map Logic
	 */

	/**
	 * Returns GameElement at a given position in TiledMap and deletes it.
	 * 
	 * @param posObj
	 *            Position in TiledMap of Gameelement
	 * @return Gamelelemnt at this Pos ( null when empty)
	 */
	public GameElement getGameElement(Vector2 posObj) {
		return null;
	}

	/**
	 * Returns a List of GameElements wich are in the Stackers. If one stacker
	 * doesn't have an Element in it, it returns null
	 * 
	 * @return null if not all stackers are filled; else a list of GameElements
	 *         which are placed in the stackers
	 */
	public ArrayList<GameElement> checkStackerPositions() {
		ArrayList<GameElement> stackerElements = new ArrayList<GameElement>();
		return stackerElements;
	}

	/**
	 * Sets GameElement at a specific Position in tiledMap
	 * 
	 * @param posObj
	 *            Position where the Object should be placed
	 * @param element
	 *            Element which should be placed
	 * @return false when element was placed successfully
	 */
	public boolean setGameElement(Vector2 posObj, GameElement element) {
		return false;
	}

	/**
	 * performs a collision detection to stop the character in case of walls or
	 * anything other solid standing in it's way
	 */
	private void collisionDetection() {

	}
}
