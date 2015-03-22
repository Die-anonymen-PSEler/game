package com.retroMachines.game.controllers;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.util.lambda.EvaluationOptimizer;
import com.retroMachines.util.lambda.LevelTree;

/**
 * The EvaluationController takes care of the evaluation. It starts the
 * evaluation itself and the animation for it.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class EvaluationController {

	/**
	 * Reference to the gameController for information regarding the user input.
	 */
	private final GameController gameController;

	/**
	 * A reference to the main class for the different calls.
	 */
	private final RetroMachines game;

	/**
	 * The lambdaTree represents the Lambda-term in our game.
	 */
	private LevelTree lambdaTree;

	/**
	 * The EvaluationScreen when the user triggers this part of the game.
	 */
	private EvaluationScreen evaluationScreen;

	/**
	 * Contains all trees and methods according to lambda model.
	 */
	private RetroLevel level;

	/**
	 * Creates a new instance of EvaluationController.
	 * 
	 * @param level
	 *            The actual level.
	 * @param g
	 *            Instance of the actual game.
	 * @param gameControl
	 *            The gameController of the game.
	 */
	public EvaluationController(RetroLevel level, RetroMachines g,
			GameController gameControl) {
		this.level = level;
		this.gameController = gameControl;
		game = g;
		evaluationScreen = new EvaluationScreen(g, this);
	}

	/**
	 * Method to start the evaluation.
	 */
	public void startEvaluation() {
		lambdaTree = level.getEvaluationTree();
		game.setScreen(evaluationScreen);
		evaluationScreen.setLambaTerm(lambdaTree);
		EvaluationOptimizer.initialize(this);
		EvaluationOptimizer.addMeToListnerList(this);
	}

	/**
	 * When the button for the next step is clicked.
	 */
	public void stepEvaluationClicked() {
		EvaluationOptimizer.nextStepClicked();
	}

	/**
	 * When the button for the whole evaluation is clicked.
	 */
	public void autoEvaluationClicked() {
		EvaluationOptimizer.autoStepClicked();
	}	

	/**
	 * Starts the animation of the evaluation.
	 */
	public void startAnimation() {
		evaluationScreen.runAnimation();
	}
	
	/*
	 * Getter and Setter
	 */

	/**
	 * Get method for the gameController of this class.
	 * 
	 * @return The gameController.
	 */
	public GameController getGameController() {
		return gameController;
	}

	/**
	 * Get method for the level of this class.
	 * 
	 * @return The level.
	 */
	public RetroLevel getLevel() {
		return level;
	}

	/**
	 * Get method for the lambdaTree of this class.
	 * 
	 * @return The lambdaTree.
	 */
	public LevelTree getlambdaTree() {
		return lambdaTree;
	}

	/**
	 * Get method for the padding of evaluation screen.
	 * 
	 * @return The padding of the evaluation screen.
	 */
	public Vector2 getEvaluationscreenPadding() {
		return evaluationScreen.getScreenPadding();
	}

	/**
	 * Places a gameElement on the stage.
	 * 
	 * @param g
	 *            The gameElement that is to be placed.
	 */
	public void setOnStage(GameElement g) {
		evaluationScreen.setOnStage(g);
	}
}
