package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.gameelements.GameElement;
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
public class EvaluationScreen extends AbstractScreen implements InputProcessor{

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
		inputMultiplexer.addProcessor(this);
		initialize();
	}

	private void initialize() {
		skin = AssetManager.getMenuSkin();

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
		
		buttonTable.add(buttonNextEvaluationStep).padRight(screenWidth / DEFAULTPADDING);
		buttonTable.add(buttonEvaluationStart).padLeft(screenWidth / DEFAULTPADDING);
		
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
		printTree(tree.getStart(), new Vector2(Constants.EVALUATIONSCREEN_PADDING, Constants.EVALUATIONSCREEN_PADDING));
	}
	
	/**
	 * Updates the evaluation animation.
	 * @return true animation finished successfully, false otherwise.
	 */
	public boolean updateScreen() {
		
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
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		// music.play();
	}
	
	private void printTree(Vertex actVertex, Vector2 position) {
		while(actVertex != null) {
			int centerVertex = (Constants.GAMEELEMENT_WIDTH * (actVertex.getWidth() - 1)) / 2;
			Vector2 pos = new Vector2(position.x + centerVertex, position.y);
			actVertex.getGameElement().setPosition(pos);
			stage.addActor(actVertex.getGameElement());
			// print Family
			if(actVertex.getfamily() != null) {
				position.y += Constants.GAMELEMENT_PADDING;
				Vector2 famPos = new Vector2(position.x, position.y + Constants.GAMEELEMENT_WIDTH);
				printTree(actVertex.getfamily(), famPos);
				position.y -= Constants.GAMELEMENT_PADDING;
			}
			
			position.x = position.x + (Constants.GAMEELEMENT_WIDTH * actVertex.getWidth());
			position.x += Constants.GAMELEMENT_PADDING;
			actVertex = actVertex.getnext();
		}
	}
	
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
			evaController.StepEvaluationClicked();
		}
	}
	
	/**
	 * Button which shows allt Evaluationsteps
	 * 
	 * @author Retro Factory
	 */
	private class EvaluationStart extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			evaController.AutoEvaluationClicked();
		}
	}
	
	public void runAnimation(GameElement g, Action a) {
		
		g.addAction(a);
	}
}
