package com.retroMachines.game.controllers;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.ui.screens.game.EvaluationScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.Dummy;
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
	private LinkedList<Vertex> vertexList;
	
	/**
	 * The EvaluationScreen when the user triggers this part of the game.
	 */
	private EvaluationScreen evaluationScreen;
	
	/**
	 * contains all trees and methods according to lambda model
	 */
	private RetroLevel level;
	
	private Vertex evalutionPointer;
	
	private int actStep;
	
	private int offsetX;
	
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
		actStep = 0;
		lambdaTree = level.getEvaluationTree();
		game.setScreen(evaluationScreen);
		evaluationScreen.setLambaTerm(lambdaTree);
		evalutionPointer = new Dummy();
		evalutionPointer.setnext(lambdaTree.getStart());
		offsetX = 0;
		step1AlphaConversion();
	}
	
	private void step1AlphaConversion() {
		actStep++;
		evalutionPointer.getnext().alphaConversion();
		step2ReadInAndDelete();
	}
	
	private void step2ReadInAndDelete() {
		Vertex readIn = evalutionPointer.getnext().getReadIn();
		readIn.readInAnimation(evalutionPointer.getnext().getGameElement().getPosition(), this);
		// Next Step by Animation
	}
	
	public void step3BetaReduction() {
		vertexList = evalutionPointer.getnext().betaReduction(this);
		// Next Step by Animation
	}
	
	public void step4UpdatePositions() {
		Vector2 start = new Vector2();
		start.x = Constants.EVALUATIONSCREEN_PADDING + offsetX; 
		start.y = Constants.EVALUATIONSCREEN_PADDING;
		evalutionPointer.getnext().setGameelementPosition(start ,new Vector2(0,0), this);
		// Next Step by Animation
	}
	
	public void step5InsertReadIn() {	
		for(Vertex v : vertexList) {
			evaluationScreen.setOnStage(v);
		}
		evalutionPointer.getnext().MoveOutOfSpace(this);
	}
	
	public void step6DeleteWorker() {
		// Update pointer if needed
		if(evalutionPointer.getnext().getnext() != null) {
			Vertex pointer = new Dummy();
			pointer.setnext(evalutionPointer.getnext().getfamily().getnext());
			if(pointer.getnext() != null) {
				while (pointer.getnext().getnext() != null) {
					pointer.setnext(pointer.getnext().getnext());
				}
			}
			
			pointer.getnext().setnext(evalutionPointer.getnext().getnext());
		}
		
		evalutionPointer.getnext().getfamily().updateGameelementPosition(0, -1, this);
	}
	
	public void nextStep() {
		// TODO Search next Vertex to evaluate and Save Result
		
	}
	
	private void checkEvaluation() {
		//TODO Check evaluation result
	}
}
