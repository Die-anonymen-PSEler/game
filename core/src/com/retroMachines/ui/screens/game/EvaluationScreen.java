package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * This class is part of the view of RetroMachines.
 * It shows the evaluation of the lambda term after all GameElements 
 * were placed in the stacks by the user.
 * The animation is started and ended in this screen and can be paused.
 * 
 * @author RetroFactory
 * 
 */
public class EvaluationScreen extends AbstractScreen {

	/**
	 * Reference to the GameController for it to handle events that are
	 * broadcasted to the screen.
	 */
	private EvaluationController evaController;

	/**
	 * The root of the lambda term structure for generation on the screen.
	 */
	private LevelTree tree;

	/**
	 * A stage to add the different actors to for the purpose of rendering.
	 */
	private Stage stage;

	/**
	 * If true the render method will kick of the animation and render it to the
	 * screen; if false the render method will stop the animation.
	 */
	private boolean animationInProgress;

	/**
	 * Creates a new instance of EvaluationScreen.
	 * @param game the actual game
	 * @param evaluationController the gameController of the actual game
	 */
	public EvaluationScreen(RetroMachines game, EvaluationController evaluationController) {
		super(game);
		this.evaController = evaluationController;
		stage = new Stage();
		animationInProgress = false;
	}

	/**
	 * Assigns a lambda term to the screen for the animation.
	 * 
	 * @param t
	 *            the lambda term in question
	 */
	public void setLambaTerm(LevelTree t) {
		this.tree = t;
	}

	/**
	 * Sets animationProgress to true and triggers the animation and displays it
	 * to the user.
	 */
	public void startAnimation() {
		animationInProgress = true;
		// TODO start animation
	}
	
	/**
	 * Updates the evaluation animation.
	 * @return true animation finished successfully, false otherwise.
	 */
	public boolean updateScreen() {
		return false;
	}

	/**
	 * Sets animationInProgress to false and freezes the animation in it's
	 * current position.
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
	 * Performs the next reduction on the lambda term and triggers the
	 * animation.
	 */
	private void nextStep() {

	}

}
