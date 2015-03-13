package com.retroMachines.ui.screens.game;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.ui.screens.AbstractScreen;
import com.retroMachines.util.ActionListElement;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.EvaluationOptimizer;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Vertex;

/**
 * This class is part of the view of RetroMachines. It shows the evaluation of
 * the lambda term after all GameElements were placed in the stacks by the user.
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
	 * stage for the buttons
	 */
	private Stage buttonStage;

	private Vector2 screenPadding;

	/**
	 * Creates a new instance of EvaluationScreen.
	 * 
	 * @param game
	 *            the actual game
	 * @param evaluationController
	 *            the gameController of the actual game
	 */
	public EvaluationScreen(RetroMachines game,
			EvaluationController evaluationController) {
		super(game);
		buttonStage = new Stage();
		this.evaController = evaluationController;
		inputMultiplexer.addProcessor(this);
		initialize();
	}

	private void initialize() {
		skin = RetroAssetManager.getMenuSkin();
		screenPadding = new Vector2();
		screenPadding.x = screenWidth * Constants.EVALUATIONSCREEN_PADDING_X;
		screenPadding.y = screenHeight * Constants.EVALUATIONSCREEN_PADDING_Y;

		Table table = new Table(skin);
		table.setBounds(0, 0, screenWidth, screenHeight);

		Table buttonTable = new Table(skin);

		Button buttonNextEvaluationStep = new Button(skin, "nextStep");
		buttonNextEvaluationStep.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonNextEvaluationStep.addListener(new NextEvaluationStep());

		Button buttonEvaluationStart = new Button(skin, "right");
		buttonEvaluationStart.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonEvaluationStart.addListener(new EvaluationStart());

		// Make Table
		table.add().expand().row();

		buttonTable.add(buttonNextEvaluationStep).padRight(
				screenWidth / DEFAULTPADDING);
		buttonTable.add(buttonEvaluationStart).padLeft(
				screenWidth / DEFAULTPADDING);

		table.add(buttonTable).padBottom(screenHeight / DEFAULTPADDING);

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
		printTree(tree.getStart(), new Vector2(screenPadding));
	}

	/**
	 * Updates the evaluation animation.
	 * 
	 * @return true animation finished successfully, false otherwise.
	 */
	public boolean isScreenUpdated() {

		return false;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		buttonStage.act(Gdx.graphics.getDeltaTime());
		buttonStage.draw();
	}

	/**
	 * Is called when this screen should be displayed. Starts to play the sound.
	 */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		// music.play();
	}

	private void printTree(Vertex actVertex, Vector2 position) {
		while (actVertex != null) {
			int centerVertex = (Constants.GAMEELEMENT_WIDTH * (actVertex
					.getWidth() - 1)) / 2;
			Vector2 pos = new Vector2(position.x + centerVertex, position.y);
			actVertex.getGameElement().setPosition(pos);
			stage.addActor(actVertex.getGameElement());
			// print Family
			if (actVertex.getfamily() != null) {
				Vector2 famPos = new Vector2(position.x, position.y
						+ Constants.GAMEELEMENT_WIDTH);
				printTree(actVertex.getfamily(), famPos);
			}

			position.x += (Constants.GAMEELEMENT_WIDTH * actVertex.getWidth());
			actVertex = actVertex.getnext();
		}
	}

	/**
	 * sets an element to the stage
	 * @param g gameElement to be set
	 */
	public void setOnStage(GameElement g) {
		stage.addActor(g);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	/**
	 * Button which shows next Evaluationstep
	 * 
	 * @author Retro Factory
	 */
	private class NextEvaluationStep extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			evaController.stepEvaluationClicked();
		}
	}

	/**
	 * Button which shows all Evaluationsteps
	 * 
	 * @author Retro Factory
	 */
	private class EvaluationStart extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			evaController.autoEvaluationClicked();
		}
	}

	/**
	 * runs the animation for the evaluation
	 */
	public void runAnimation() {
		LinkedList<ActionListElement> actionList = EvaluationOptimizer.getActionList();
		for (int i = 0; i < actionList.size(); i++) {
			ActionListElement actListEle = actionList.get(i);
			actListEle.getGameElement().addAction(actListEle.getAction());
		}
	}

	/**
	 * returns the padding of the screen
	 * @return vector of screen padding
	 */
	public Vector2 getScreenPadding() {
		return new Vector2(screenPadding);
	}
}
