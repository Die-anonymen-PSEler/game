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
	
	private Vertex evalutionPointer;
	
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
		evalutionPointer = new Dummy();
		evalutionPointer.setnext(lambdaTree.getStart());

	}
	
	private void step1AlphaConversion() {
		evalutionPointer.getnext().alphaConversion();
	}
	
	private void step2ReadInAndDelete() {
		
	}
	
	private void step3BetaReduction() {
		
	}
	
	private void step4UpdatePositions() {
		
	}
	
	private void step5InsertReadIn() {
		
	}
	
	private void step6DeleteWoker() {
		
	}
	
	
}
