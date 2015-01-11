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
 * GameController
 * This class represents the controller for the actual game.
 * It sets up levels and also disposes them afterwards.
 * It saves progress to the persistent storage.
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
	 * the game Character that is controlled by the player
	 */
	private RetroMan retroMan;
	
	/**
	 * the map that is currently active and may be shown to the user in case the gamescreen is also active
	 */
	private TiledMap map;
	
	/**
	 * True if LevelMenu is shown No other Buttonclicks like steering of RetroMAn are now possible
	 * True if LevelMenu, Hint or Task is shown, No other Buttonclicks like steering of RetroMAn are now possible
	 */
	private boolean SomethingOnScreen;
	
	/**
	 * makes an instance of GameController
	 * @param game
	 */
	public GameController(RetroMachines game) {
		this.game = game;
		retroMan = new RetroMan();
	}
	
	/**
	 * sets initializes a given level and fires it up
	 * @param levelId the level to be started
	 */
	public void startLevel(int levelId) {
		boolean lefti = game.getSettingController().getLeftiMode();
		gameScreen = new GameScreen(game, this, lefti);
		gameScreen.setMap(map);
		game.setScreen(gameScreen);
	}
	
	/**
	 * This method will remove the GameScreen and put up the EvaluationScreen
	 * It will also trigger the evaluation
	 */
	private void enterEvaluation() {
		evaluationScreen = new EvaluationScreen(game, this);
		game.setScreen(evaluationScreen);
	}
	
	/**
	 * this method will be called once a level has been complete
	 * including the evaluation. Afterwards the LevelMenuScreen will be shown to the user
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
	 * @return true if alle stackers have an element in it
	 */
	private boolean checkPlacementofElements() {
		ArrayList<GameElement> stackerElements = checkStackerPositions();
		return false;
	}
	
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

	
	//--------------------------
	//--------Retro-Man---------
	//--------------------------
	
	/**
	 * delegates a jump call to the retroMan
	 */
	public void jumpRetroMan() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * delegates a interact call to the retroMan
	 */
	public void interactRetroMan() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * delegates a goLeft call to the retroMan
	 */
	public void goLeftRetroMan() {
		
	}
	
	/**
	 * delegates a go right call to the retroMan
	 */
	public void goRightRetroMan() {
		
	}
	
	/**
	 * returns the RetroMan instance
	 * @return
	 */
	public RetroMan getRetroMan() {
		// TODO Auto-generated method stub
		return retroMan;
	}
	
	
	
	
	
	/**
	 * Map Logic
	 */
	
	
	
	/**
	 * Returns Gameelement at given Pos in TiledMap and deletes it.
	 * @param posObj Position in TiledMap of Gameelement
	 * @return Gamelelemnt at this Pos  ( null when empty)
	 */
	public GameElement getGameElement(Vector2 posObj) {
		return null;
	}
	
	/**
	 * Returns a List of Gameelements wich are in the Stackers if one STacker doesnt have an Element in it it returns null
	 * @return null if not alle stackers are field  else a list of GameElement which are in the stackers placed
	 */
	public ArrayList<GameElement> checkStackerPositions() {
		ArrayList<GameElement> stackerElements = new ArrayList<GameElement>();
		return stackerElements;
	}
	
	
	/**
	 * Sets Gameelement at specific Position in tiledMap
	 * @param posObj Position where The Object should Placed
	 * @param element Eleement which should placed
	 * @return false when element placed succesfull
	 */
	public boolean setGameElement(Vector2 posObj, GameElement element) {
		return false;
	}
	
	/**
	 * performs a collision detection to stop the character 
	 * in case of walls are anything solid standing in it's way
	 */
	private void collisionDetection() {
		
	}
}
