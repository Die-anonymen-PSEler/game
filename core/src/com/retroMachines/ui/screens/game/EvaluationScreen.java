package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.ui.screens.AbstractScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Vertex;

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
	 * If true the render method will kick of the animation and render it to the
	 * screen; if false the render method will stop the animation.
	 */
	private boolean animationInProgress;
	
	/**
	 * 
	 * @return
	 */
	private Stage buttonStage;

	/**
	 * Creates a new instance of EvaluationScreen.
	 * @param game the actual game
	 * @param evaluationController the gameController of the actual game
	 */
	public EvaluationScreen(RetroMachines game, EvaluationController evaluationController) {
		super(game);
		buttonStage = new Stage();
		this.evaController = evaluationController;
		animationInProgress = false;
		initialize();
	}

	private void initialize() {
		skin = AssetManager.getMenuSkin();

		Table table = new Table(skin);
		table.setBounds(0, 0, screenWidth, screenHeight);

		Button buttonMenu = new Button(skin, "menu");
		buttonMenu.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonMenu.setColor(1, 1, 1, 0.66f);
		
		//Make Table
		table.add(buttonMenu).padTop(screenHeight / DEFAULTPADDINGx2).padLeft(screenWidth/ DEFAULTPADDINGx4).left();
		table.add().expand().row();
		buttonStage.addActor(table);
		

		inputMultiplexer.addProcessor(buttonStage);
	}

	/**
	 * Assigns a lambda term to the screen for the animation.
	 * 
	 * @param t
	 *            the lambda term in question
	 */
	public void setLambaTerm(LevelTree t) {
		this.tree = t;
		printTree(tree.getStart(), new Vector2(Constants.EVALUATIONSCREEN_PADDING, Constants.EVALUATIONSCREEN_PADDING));
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

		//stage.act();
		//stage.draw();
		
		buttonStage.act();
		buttonStage.draw();
	}

	/**
	 * Performs the next reduction on the lambda term and triggers the
	 * animation.
	 */
	private void nextStep() {

	}
	
	private void printTree(Vertex actVertex, Vector2 position) {
		while(actVertex != null) {
			int centerVertex = (Constants.GAMEELEMENT_WIDTH * (actVertex.getWidth() - 1)) / 2;
			Vector2 pos = new Vector2(position.x + centerVertex, position.y);
			actVertex.getGameElement().setPosition(pos);
			stage.addActor(actVertex.getGameElement());
			// print Family
			if(actVertex.getfamily() != null) {
				Vector2 famPos = new Vector2(position.x, position.y + Constants.GAMEELEMENT_WIDTH);
				printTree(actVertex.getfamily(), famPos);
			}
			
			position.x = position.x + (Constants.GAMEELEMENT_WIDTH * actVertex.getWidth());
			actVertex = actVertex.getnext();
		}
	}
	
	public void setOnStage(Vertex v) {
		stage.addActor(v.getGameElement());
	}
}
