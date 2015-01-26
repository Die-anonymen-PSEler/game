package com.retroMachines.game.controllers;

import java.util.ArrayList;

import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.util.lambda.Tree;
import com.retroMachines.util.lambda.Vertex;

public class EvaluationController {
	
	/**
	 * A reference to the main class for the different calls.
	 */
	private final RetroMachines game;
	
	/**
	 * The lambdaTree represents the Lambdaterm in our Game
	 */
	private Tree lambdaTree;
	
	/**
	 * List of all gameElements in this level
	 */
	private ArrayList<Vertex> vertexList;
	
	/**
	 * The EvaluationScreen when the user triggers this part of the game.
	 */
	private EvaluationScreen evaluationScreen;
	
	
	public EvaluationController(Tree t, RetroMachines g) {
		lambdaTree = t;
		game = g;
	}
	
	
	
	/**
	 * Removes the GameScreen and puts up the EvaluationScreen. It
	 * also triggers the evaluation.
	 */
	public void enterEvaluation() {
		evaluationScreen = new EvaluationScreen(game, this);
		game.setScreen(evaluationScreen);
		lambdaTree.evaluate();
	}
	
	
	
	/**
	 * Updates the evaluation screen to animate the evaluation.
	 */
	private void updateEvaluationScreen(){
		
	}
}
