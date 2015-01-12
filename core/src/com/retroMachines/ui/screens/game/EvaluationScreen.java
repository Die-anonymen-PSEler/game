package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.util.lambda.Tree;
import com.retroMachines.ui.screens.AbstractScreen;
/**
 * This class is part of the view of RetroMachines.
 * It shows the evaluation of the 
 * @author RetroFactory
 *
 */
public class EvaluationScreen extends AbstractScreen {
	
	/**
	 * reference to the GameController for it to handle events
	 * that are broadcasted to the screen
	 */
	private GameController gameController;
	
	/**
	 * the root of the lamba term structure for generation on the screen
	 */
	private Tree tree;
	
	/**
	 * a stage to add the different actors to for the purpose of rendering
	 */
	private Stage stage;
	
	/**
	 * if true the render method will kick of the animation and render it to the screen
	 * if false the render method will stop the animation
	 */
	private boolean animationInProgress;

	public EvaluationScreen(RetroMachines game, GameController gameController) {
		super(game);
		this.gameController = gameController;
		stage = new Stage();
		animationInProgress = false;
	}

	/**
	 * assigns a lambda term to the screen for the animation
	 * @param t the lambda term in question
	 */
	public void setLambaTerm(Tree t) {
		this.tree = t;
	}
	
	/**
	 * sets animationProgress to true and triggers the animation
	 * and displays it to the user
	 */
	public void startAnimation() {
		animationInProgress = true;
		// TODO start animation
	}
	
	/**
	 * sets animationInProgress to false and freezes the animation
	 * in it's current position
	 */
	public void pauseAnimation() {
		animationInProgress = false;
		// TODO stop animation
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		
		stage.act();
		stage.draw();
	}
	
	/**
	 * performs the next reduction on the lambda term 
	 * and triggers the animation.
	 */
	private void nextStep() {
		
	}
	
}
