package com.retroMachines.game.controllers;



import com.badlogic.gdx.math.Vector2;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.ui.screens.game.EvaluationScreen;
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

	public void stepEvaluationClicked() {
		EvaluationOptimizer.nextStepClicked();
	}

	public void autoEvaluationClicked() {
		EvaluationOptimizer.autoStepClicked();
	}

	public GameController getGameController() {
		return gameController;
	}

	public RetroLevel getLevel() {
		return level;
	}

	public LevelTree getlambdaTree() {
		return lambdaTree;
	}

	public void startAnimation() {
		evaluationScreen.runAnimation();
	}

	public void setOnStage(GameElement g) {
		evaluationScreen.setOnStage(g);
	}

	public Vector2 getEvaluationscreenPadding() {
		return evaluationScreen.getScreenPadding();
	}
}
