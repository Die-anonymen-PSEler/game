package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.lambda.Tree;
import com.retroMachines.ui.screens.AbstractScreen;

public class EvaluationScreen extends AbstractScreen implements ContactListener {
	
	private GameController gameController;
	
	private Tree tree;
	
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
	
	public void startAnimation() {
		animationInProgress = true;
		// TODO start animation
	}
	
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
	
	
	
	
	
	
	
	
	
	/**
	 * ContactListener Section
	 */
	

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
}
