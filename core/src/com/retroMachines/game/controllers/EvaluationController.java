package com.retroMachines.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.Dummy;
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
	private RetroLevel level;
	
	public EvaluationController(RetroLevel level, RetroMachines g) {
		this.level = level;
		game = g;
		evaluationScreen = new EvaluationScreen(g, this);
	}
	
	/**
	 * Updates the evaluation screen to animate the evaluation.
	 */
	private void updateEvaluationScreen(){
		//TODO: implement
	}

	/**
	 * 
	 */
	public void startEvaluation() {
		lambdaTree = level.getEvaluationTree();
		game.setScreen(evaluationScreen);
		evaluationScreen.setLambaTerm(lambdaTree);
		
		Vertex actWorker = new Dummy();
		actWorker.setnext(lambdaTree.getStart());
		while(actWorker.getnext() != null) {
			if(actWorker.getnext().getType().equals("Variable")) {
				lambdaTree.setStart(actWorker.getnext());
			}
			actWorker.getnext().alphaConversion();
			if(!actWorker.getnext().betaReduction()) {
				Gdx.app.log(Constants.LOG_TAG, "Application Beta-Reduction error");
			}
			actWorker.setnext(actWorker.getnext().getnext());
			
		}
		System.out.println(lambdaTree.getStart().getType() + " + " + lambdaTree.getStart().getColor());
	}
	
	
}
