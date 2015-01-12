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
import com.retroMachines.util.lambda.Tree;

/**
 * The GameController is part of the controller of RetroMachines.
 * This class represents the controller for the actual game. It
 * sets up levels and also disposes them afterwards. It saves progress to the
 * persistent storage.
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
	 * The EvaluationScreen when the user triggers this part of the game.
	 */
	private EvaluationScreen evaluationScreen;

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
	 * The lambdaTree represents the Lambdaterm in our Game
	 */
	private Tree lambdaTree;

	/**
<<<<<<< HEAD
	 * makes an instance of GameController
=======
	 * True if LevelMenu is shown. No other Button clicks like steering of
	 * RetroMAn are now possible True if LevelMenu, Hint or Task is shown. No
	 * other Buttonclicks like steering of RetroMan are now possible
	 */
	private boolean popupScreenIsShown;

	/**
	 * Makes a new instance of the GameController.
>>>>>>> origin/master
	 * 
	 * @param game The game that is to be controlled.
	 */
	public GameController(RetroMachines game) {
		this.game = game;
		retroMan = new RetroMan();
	}

	/**
<<<<<<< HEAD
	 * Initializes a given level and fires it up.
=======
	 * Sets and initializes a given level and starts it.
>>>>>>> origin/master
	 * 
	 * @param levelId
	 *            ID of the level that is to be started.
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
	 * Finishes the level once it has been completed including the
	 * evaluation. Afterwards the LevelMenuScreen will be shown to the user.
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
		// TODO Auto-generated method stub
	}

	/**
	 * Delegates an interact call to RetroMan.
	 */
	public void interactRetroMan() {
		// TODO Auto-generated method stub
	}

	/**
	 * Delegates a goLeft call to RetroMan.
	 */
	public void goLeftRetroMan() {

	}

	/**
	 * Delegates a goRight call to RetroMan.
	 */
	public void goRightRetroMan() {

	}

	/**
	 * Returns the instance of the RetroMan.
	 * @return The instance of the RetroMan which is held by this controller.
	 */
	public RetroMan getRetroMan() {
		// TODO Auto-generated method stub
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
		return null;
	}

	/**
	 * Returns a List of GameElements which are in the depots. If one depot
	 * doesn't contain an Element, it returns null.
	 * 
	 * @return Null, if not all depots are filled, else a list of GameElements
	 *         which are placed in the depots.
	 */
	public ArrayList<GameElement> checkdepotPositions() {
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
		return false;
	}

	/**
	 * Performs a collision detection to stop the character in case of walls or
	 * any other solid object standing in the way.
	 */
	private void collisionDetection() {

	}
	
	// --------------------------
	// --------Evaluation--------
	// --------------------------
	
	/**
	 * Starts the hole evaluation process
	 */
	public void evaluationClicked() {
		if(checkPlacementofElements()){
			buildlambdaTree();
			enterEvaluationScreen();
			evaluate();
			checkEvaluationResult();			
		}
	}
	
	/**
	 * Removes the GameScreen and puts up the EvaluationScreen. It
	 * also triggers the evaluation.
	 */
	private void enterEvaluationScreen() {
		evaluationScreen = new EvaluationScreen(game, this);
		game.setScreen(evaluationScreen);
	}
	
	/**
	 * Checks if all the depots contain an element.
	 * 
	 * @return True if all the depots contain an element.
	 */
	private boolean checkPlacementofElements() {
		ArrayList<GameElement> depotElements = checkdepotPositions();
		return false;
	}
	
	/**
	 * Builds the lambdaTree for evaluation with data of the map
	 */
	private void buildlambdaTree() {
		
	}
	
	/**
	 * Evaluates the lambdaTree as long as possible
	 */
	private void evaluate() {
		
	}
	
	/**
	 * is called after evaluation and checks if the result the right one to finish the level
	 */
	private void checkEvaluationResult() {
		
	}
	
	/**
	 * Fulfills one step of beta-reduction.
	 * 
	 * @return true if this abstraction has changed, false otherwise
	 */
	private boolean betaReduction() {
		// TODO: beta reduction
		return false;
	}

	/**
	 * Fulfills alpha conversion. Makes sure that all vertices have unique id's.
	 * 
	 * @return true if at least one id changed, false if no id changed.
	 */
	private boolean alphaConversion() {
		// TODO: implement alpha conversion
		return false;
	}
	
	/**
	 * Updates the evaluation screen to animate the evaluation
	 */
	private void updateEvaluationScreen(){
		
	}
}
