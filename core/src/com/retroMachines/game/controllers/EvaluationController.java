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
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Vertex;

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
	 * The resultTree represents the result of the Evaluation 
	 */
	private LevelTree resultTree;
	
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
	
	/**
	 * evaluationPointer . next is always the actual worker in evaluation
	 */
	private Vertex evalutionPointer;
	
	/**
	 * resultPointer.next is always last element added to resultTree
	 */
	private Vertex resultPointer;
	
	private int offsetX;
	
	private boolean result;
	
	public EvaluationController(RetroLevel level, RetroMachines g, GameController gameControl) {
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
		evalutionPointer = new Dummy();
		resultPointer = new Dummy();
		evalutionPointer.setnext(lambdaTree.getStart());
		offsetX = 0;
		evaluationScreen.setAutoStep();
		evaluationScreen.setNextStep();

	}
	
	public void step1AlphaConversion() {
		evalutionPointer.getnext().alphaConversion();
		step2ReadInAndDelete();
	}
	
	private void step2ReadInAndDelete() {
		Vertex readIn = evalutionPointer.getnext().getReadIn();
		if(readIn != null) {
			readIn.readInAnimation(evalutionPointer.getnext().getGameElement().getPosition(), this);
			// Next Step by Animation
		} else {
			step3BetaReduction();
		}

	}
	
	public void step3BetaReduction() {
		vertexList = evalutionPointer.getnext().betaReduction(this);
		// Next Step by Animation
	}
	
	public void step4UpdatePositions() {
		Vector2 start = new Vector2();
		start.x = Constants.EVALUATIONSCREEN_PADDING + offsetX; 
		start.y = Constants.EVALUATIONSCREEN_PADDING;
		evalutionPointer.getnext().reorganizePositions(start ,new Vector2(0,0), this);
		// Next Step by Animation
	}
	
	public void step5InsertReadIn() {	
		// only Abstraction returns a list with elements else it is empty
		for(Vertex v : vertexList) {
			evaluationScreen.setOnStage(v);
		}
		// At the end worker disappears when its an Abstraction or Application
		evalutionPointer.getnext().DeleteAfterBetaReduction(this);
	}
	
	public void step6UpdateFamilyPositions() {
		evalutionPointer.getnext().UpdatePositionsAfterBetaReduction(this);
	}
	
	public void nextStep() {
		//creating copy of current tree
		LevelTree oldTree = new LevelTree(evalutionPointer.cloneMe());
		Vertex result = evalutionPointer.getnext().getEvaluationResult();


		if(result != null) {
			//check whether there is an infinite evaluation (same tree after evaluation as before)
			if(result.getnext() != null) {
				LevelTree newTree = new LevelTree(result.getnext());
				if (oldTree.equals(newTree)) {
					//we can stop evaluation at this point
					resultTree = oldTree;
					checkEvaluation();
					return;
				}
			}
			// make resulTree if needed
			if(resultTree != null) {
				resultPointer.getnext().setnext(result);
				resultPointer.setnext(resultPointer.getnext().getnext());
			} else {
				resultTree = new LevelTree(result);
				resultPointer.setnext(resultTree.getStart());
			}
		}
		
		evalutionPointer.setnext(evalutionPointer.getnext().updatePointerAfterBetaReduction());
		if(evalutionPointer.getnext() == null) {
			//End of Evaluation
			resultPointer.getnext().setnext(null);
			checkEvaluation();
		} else {
			//Wenn alle steps starten geklickt starte n�chsten schritt sonst wart auf eingabe
			if(evaluationScreen.getAutoStep()) {
				evaluationScreen.setNextStep();
			} else {
				step1AlphaConversion();
			}
		}
	}
	
	/**
	 * This method is called after the last evaluation step ,
	 *  evaluation result is saved in resultTree
	 */
	private void checkEvaluation() {
		result = resultTree.equals(level.getLambdaUtil().getTargetTree());
		if(result) {
			gameController.evaluationComplete();
		} else {
			gameController.evaluationInComplete();
		}
	}
}
