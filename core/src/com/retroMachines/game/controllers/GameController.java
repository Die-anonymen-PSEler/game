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
	
	private void enterEvaluation() {
		evaluationScreen = new EvaluationScreen(game, this);
		game.setScreen(evaluationScreen);
	}
	
	/**
	 * this method will be called
	 */
	public void levelFinished() {
		saveProgress();
		dispose();
		
		game.setScreen(new LevelMenuScreen(game));
	}
	
	private void dispose() {
		// TODO Auto-generated method stub
		gameScreen.dispose();
		
	}
	
	/**
	 * saves the progress to the persistent storage
	 */
	private void saveProgress() {
		
	}

	public void jumpRetroMan() {
		// TODO Auto-generated method stub
		if (retroMan.canJump()) {
			retroMan.jump();
		}
	}

	public RetroMan getRetroMan() {
		// TODO Auto-generated method stub
		return retroMan;
	}
	
	private void collisionDetection() {
		
	}
}
