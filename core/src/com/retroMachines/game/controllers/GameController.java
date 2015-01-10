package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;

/**
 * GameController
 * This class represents the controller for the actual game.
 * It sets up levels and also disposes them afterwards.
 * It saves progress to the persistent storage.
 * @author lucabecker
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
	
	public GameController(RetroMachines game) {
		this.game = game;
		retroMan = new RetroMan();
	}
	
	/**
	 * sets initializes a given level and fires it up
	 * @param levelId the level to be started
	 */
	public void startLevel(int levelId) {
		gameScreen = new GameScreen(game, this);
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
	 * delegates a jump call to the retroMan
	 */
	public void jumpRetroMan() {
		// TODO Auto-generated method stub
		if (retroMan.canJump()) {
			retroMan.jump();
		}
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
	 * performs a collision detection to stop the character 
	 * in case of walls are anything solid standing in it's way
	 */
	private void collisionDetection() {
		
	}
}
