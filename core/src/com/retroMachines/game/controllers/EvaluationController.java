package com.retroMachines.game.controllers;

import java.util.ArrayList;

import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Vertex;

public class EvaluationController {
	
	/**
	 * A reference to the main class for the different calls.
	 */
	private final RetroMachines game;
	
	/**
	 * The lambdaTree represents the Lambdaterm in our Game
	 */
	private LevelTree lambdaTree;
	
	/**
	 * List of all gameElements in this level
	 */
	private ArrayList<Vertex> vertexList;
	
	/**
	 * The EvaluationScreen when the user triggers this part of the game.
	 */
	private EvaluationScreen evaluationScreen;
	
	/**
	 * contains all trees and methods according to lambda model
	 */
	private LambdaUtil util;
	
	public EvaluationController(LambdaUtil util, RetroMachines g) {
		this.util = util;
		game = g;
	}
	
	
	
	/**
	 * Removes the GameScreen and puts up the EvaluationScreen. It
	 * also triggers the evaluation.
	 */
	public void enterEvaluation() {
		evaluationScreen = new EvaluationScreen(game, this);
		game.setScreen(evaluationScreen);
		util.getLevelTree().evaluate();
	}
	
	
	/**
	 * getter for util
	 * @return
	 */
	public LambdaUtil getLambdaUtil() {
		return util;
	}
	
	
	/**
	 * Updates the evaluation screen to animate the evaluation.
	 */
	private void updateEvaluationScreen(){
		//TODO: implement
	}
	
	
}
