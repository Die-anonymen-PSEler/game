package com.retroMachines.game.controllers;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.util.ActionListElement;
import com.retroMachines.util.lambda.EvaluationOptimizer;
import com.retroMachines.util.lambda.LevelTree;

public class EvaluationController {

	/**
	 * reference to the gameController for information regarding the user input
	 */
	private final GameController gameController;

	/**
	 * A reference to the main class for the different calls.
	 */
	private final RetroMachines game;

	/**
	 * The lambdaTree represents the Lambdaterm in our Game
	 */
	private LevelTree lambdaTree;

	/**
	 * The EvaluationScreen when the user triggers this part of the game.
	 */
	private EvaluationScreen evaluationScreen;

	/**
	 * contains all trees and methods according to lambda model
	 */
	private RetroLevel level;

	/**
	 * creates a new instance of EvaluationController
	 * @param level the actual level
	 * @param g instance of the actual game
	 * @param gameControl gameController of the game
	 */
	public EvaluationController(RetroLevel level, RetroMachines g,
			GameController gameControl) {
		this.level = level;
		this.gameController = gameControl;
		game = g;
		evaluationScreen = new EvaluationScreen(g, this);
	}

	/**
	 * 
	 */
	public void startEvaluation() {
		lambdaTree = level.getEvaluationTree();
		game.setScreen(evaluationScreen);
		evaluationScreen.setLambaTerm(lambdaTree);
		EvaluationOptimizer.addMeToListnerList(this);
		EvaluationOptimizer.initialize(this);
	}

	/**
	 * when the button for the next step is clicked
	 */
	public void stepEvaluationClicked() {
		EvaluationOptimizer.nextStepClicked();
	}

	/**
	 * when the button for the whole evaluation is clicked
	 */
	public void autoEvaluationClicked() {
		EvaluationOptimizer.autoStepClicked();
	}
	
	/**
	 * returns the gameController of this class
	 * @return gameController
	 */
	public GameController getGameController() {
		return gameController;
	}

	/**
	 * returns the level of this class
	 * @return level
	 */
	public RetroLevel getLevel() {
		return level;
	}

	/**
	 * returns the lambdaTree of this class
	 * @return lambdaTree
	 */
	public LevelTree getlambdaTree() {
		return lambdaTree;
	}

	/**
	 * starts the animation
	 */
	public void startAnimation() {
		evaluationScreen.runAnimation();
	}

	/**
	 * places a gameElement on the stage
	 * @param g the gameElement to be placed
	 */
	public void setOnStage(GameElement g) {
		evaluationScreen.setOnStage(g);
	}

	/**
	 * returns the padding of evaluation screen
	 * @return the padding of evaluation screen
	 */
	public Vector2 getEvaluationscreenPadding() {
		return evaluationScreen.getScreenPadding();
	}
}
