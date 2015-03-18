package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.gameelements.DepotElement;
import com.retroMachines.ui.RetroDialogChain;
import com.retroMachines.ui.RetroDialogChain.DialogChainFinishedListener;
import com.retroMachines.ui.screens.AbstractScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;
import com.retroMachines.util.Constants;
import com.retroMachines.util.Constants.ButtonStrings;
import com.retroMachines.util.lambda.Vertex;

/**
 * This class is part of the view of RetroMachines. It displays the actual game
 * to the user. It's controller is the gameController which handles all the
 * logic. If something needs to be rendered for the game it should be placed
 * within the render method of this class.
 * 
 * @author RetroFactory
 * 
 */
public class GameScreen extends AbstractScreen implements
		DialogChainFinishedListener {

	private static final float ZOOM_ADDITION = 0.20f;
	private static final float RETROMAN_BUTTON_OFFSET = 2;
	private final static float DIALOGWIDTH = (4f / 5f);
	private final static float DIALOGHEIGHT = (7f / 9f);
	private final static float PADDING_THIRTY = 30f;
	private final static float DIALOG_FONTSIZE = 1.5f;
	private final static float DIALOGTEXTWIDTH = (7f / 10f);

	/**
	 * a render for displaying the map and everything else to the screen.
	 */
	private OrthogonalTiledMapRenderer renderer;

	/**
	 * OrthographicCamera so we can look from the side onto our map.
	 */
	private OrthographicCamera camera;

	/**
	 * reference to the gameController for information regarding the user input
	 */
	private final GameController gameController;

	/**
	 * the dialog which is shown when hintButton is pressed
	 */
	private HintDialog hintDialog;

	/**
	 * the dialog which is shown when pauseButton is pressed
	 */
	private PauseDialog pauseDialog;

	/**
	 * the dialog which is shown when targetButton is pressed
	 */
	private TaskDialog taskDialog;

	/*
	 * map attributes
	 */

	/**
	 * like in css the order goes top-> right -> bottom -> left
	 */
	private final int[] mapBounds;

	/**
	 * 
	 */
	private final float[] camBounds;

	/**
	 * True if LevelMenu is shown. No other Button clicks like steering of
	 * RetroMan are now possible True if LevelMenu, Hint or Task is shown. No
	 * other ButtonClicks like steering of RetroMan are now possible
	 */
	private boolean popupScreenIsShown;

	private boolean leftMode;

	/*
	 * Buttons
	 */
	private Button buttonLeft;

	private Button buttonRight;

	private Button buttonA;

	/**
	 * 
	 * @param game
	 * @param gameController
	 * @param leftiMode
	 */
	public GameScreen(RetroMachines game, GameController gameController,
			boolean leftMode) {
		super(game);
		this.gameController = gameController;
		this.leftMode = leftMode;
		mapBounds = new int[4];
		camBounds = new float[4];
		initialize();
	}

	public void initialize() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 35, 20);
		camera.zoom -= ZOOM_ADDITION;
		camera.update();

		stage = new Stage();
		inputMultiplexer.addProcessor(this);

		drawButtons();

		// instanciate Dialogs
		hintDialog = new HintDialog("", skin, "default");
		pauseDialog = new PauseDialog("", skin, "default");
		taskDialog = new TaskDialog("", skin, "default");

	}

	/**
	 * Is called when this screen should be displayed. Starts to play the sound.
	 */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		// music.play();
	}

	/**
	 * Assigns a new TiledMap to the screen.
	 * 
	 * @param map
	 *            the tiled map for this screen.
	 */
	public void setMap(TiledMap map) {
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 64f);
		MapProperties prop = map.getProperties();
		mapBounds[1] = prop.get("width", Integer.class);
		mapBounds[0] = prop.get("height", Integer.class);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Double stage.act() and draw();
		// super.render(delta);

		inputDetection();

		updateCameraPosition(gameController.getRetroMan().getPos().x,
				gameController.getRetroMan().getPos().y);
		camera.update();

		renderer.setView(camera);
		renderer.render();

		gameController.update(delta);
		gameController.getRetroMan().render(renderer, delta);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	private void updateCameraPosition(float x, float y) {
		camera.position.x = gameController.getRetroMan().getPos().x;
		camera.position.y = gameController.getRetroMan().getPos().y;

		float cameraHalfWidth = camera.viewportWidth * .5f;
		float cameraHalfHeight = camera.viewportHeight * .5f;

		camBounds[3] = camera.position.x - cameraHalfWidth;
		camBounds[1] = camera.position.x + cameraHalfWidth;
		camBounds[2] = camera.position.y - cameraHalfHeight;
		camBounds[0] = camera.position.y + cameraHalfHeight;

		if (mapBounds[1] < camera.viewportWidth) {
			camera.position.x = mapBounds[1] / 2f;
		} else if (camBounds[3] <= 0) {
			camera.position.x = 0 + cameraHalfWidth;
		} else if (camBounds[1] >= mapBounds[1]) {
			camera.position.x = mapBounds[1] - cameraHalfWidth;
		}

		// Vertical axis
		if (mapBounds[0] < camera.viewportHeight) {
			camera.position.y = mapBounds[0] / 2f;
		} else if (camBounds[2] <= 0) {
			camera.position.y = 0 + cameraHalfHeight;
		} else if (camBounds[0] >= mapBounds[0]) {
			camera.position.y = mapBounds[0] - cameraHalfHeight;
		}

		camera.position.y -= RETROMAN_BUTTON_OFFSET;
	}

	private void drawButtons() {
		skin = RetroAssetManager.getMenuSkin();

		Table table = new Table(skin);
		table.setBounds(0, 0, screenWidth, screenHeight);

		Table buttonTable = new Table(skin);

		Table innerButtonTable = new Table(skin);

		buttonLeft = new Button(skin, "left");
		buttonLeft.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonLeft.setColor(1, 1, 1, 0.66f);

		buttonRight = new Button(skin, "right");
		buttonRight.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonRight.setColor(1, 1, 1, 0.66f);

		buttonA = new Button(skin, "a");
		buttonA.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonA.setColor(1, 1, 1, 0.66f);

		Button buttonB = new Button(skin, "b");
		buttonB.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonB.addListener(new InteractButtonClickListener());
		buttonB.setColor(1, 1, 1, 0.66f);

		Button buttonHint = new Button(skin, "answer");
		buttonHint.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonHint.addListener(new GetHintClickListener());
		buttonHint.setColor(1, 1, 1, 0.66f);

		Button buttonQuest = new Button(skin, "quest");
		buttonQuest.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonQuest.addListener(new GetTaskClickListener());
		buttonQuest.setColor(1, 1, 1, 0.66f);

		Button buttonMenu = new Button(skin, "menu");
		buttonMenu.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonMenu.addListener(new MenuClickListener());
		buttonMenu.setColor(1, 1, 1, 0.66f);

		Button buttonEvaluation = new Button(skin, "evaluation");
		buttonEvaluation.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonEvaluation.addListener(new TryEvaluationButtonClickListener());
		buttonEvaluation.setColor(1, 1, 1, 0.66f);

		// Make Table
		table.add(buttonMenu).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR).left();
		table.add(buttonEvaluation).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padRight(screenWidth / DEFAULTPADDING_X_FOUR).right().row();
		table.add().expand().row();

		innerButtonTable.add(buttonHint)
				.padRight(screenWidth / DEFAULTPADDING_X_FOUR)
				.padLeft(screenWidth / DEFAULTPADDING)
				.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
		innerButtonTable.add(buttonQuest)
				.padRight(screenWidth / DEFAULTPADDING)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR)
				.padBottom(screenWidth / DEFAULTPADDING_X_TWO);

		// Add to Table check LeftMode is activated
		if (leftMode) {
			buttonTable.add(buttonA).padRight(screenWidth / DEFAULTPADDING_X_FOUR)
					.padLeft(screenWidth / DEFAULTPADDING_X_TWO)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
			buttonTable.add(buttonB).padRight(screenWidth / DEFAULTPADDING)
					.padLeft(screenWidth / DEFAULTPADDING_X_FOUR)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
			buttonTable.add(innerButtonTable);
			buttonTable.add(buttonLeft)
					.padRight(screenWidth / DEFAULTPADDING_X_FOUR)
					.padLeft(screenWidth / DEFAULTPADDING)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
			buttonTable.add(buttonRight)
					.padRight(screenWidth / DEFAULTPADDING_X_TWO)
					.padLeft(screenWidth / DEFAULTPADDING_X_FOUR)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
		} else {
			buttonTable.add(buttonLeft)
					.padRight(screenWidth / DEFAULTPADDING_X_FOUR)
					.padLeft(screenWidth / DEFAULTPADDING_X_TWO)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
			buttonTable.add(buttonRight).padRight(screenWidth / DEFAULTPADDING)
					.padLeft(screenWidth / DEFAULTPADDING_X_FOUR)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
			buttonTable.add(innerButtonTable);
			buttonTable.add(buttonB).padRight(screenWidth / DEFAULTPADDING_X_FOUR)
					.padLeft(screenWidth / DEFAULTPADDING)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
			buttonTable.add(buttonA).padRight(screenWidth / DEFAULTPADDING_X_TWO)
					.padLeft(screenWidth / DEFAULTPADDING_X_FOUR)
					.padBottom(screenWidth / DEFAULTPADDING_X_TWO);
		}

		table.add(buttonTable).colspan(COLSPAN_X_TWO);
		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
	}

	/**
	 * Performs the input detection and delegates calls to the controller so it
	 * can perform the logic.
	 */
	private void inputDetection() {
		if (!popupScreenIsShown) {
			if (Gdx.input.isKeyPressed(Keys.SPACE) || buttonA.isPressed()) {
				gameController.jumpRetroMan();
			}
			if (Gdx.input.isKeyPressed(Keys.RIGHT) || buttonRight.isPressed()) {
				gameController.goRightRetroMan();
			} else if (Gdx.input.isKeyPressed(Keys.LEFT)
					|| buttonLeft.isPressed()) {
				gameController.goLeftRetroMan();
			}
		}
	}

	/*
	 * Getter and Setter
	 */

	@Override
	public void dialogFinished() {
		popupScreenIsShown = false;
	}

	// ------------------------------------
	// --Show different views in the game--
	// ------------------------------------

	/**
	 * Shows the TaskScreen on top of the game.
	 */
	private void showTask() {
		taskDialog.show(stage);
	}

	/**
	 * Shows the HintScreen on top of the game.
	 */
	private void showHint() {
		hintDialog.show(stage);
	}

	private void showPauseDialog() {
		pauseDialog.show(stage);
	}

	/*
	 * input processing (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.B) {
			gameController.interactRetroMan();
		}
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

	public void showValidateError(String s) {
		ErrorDialog error = new ErrorDialog("", skin, "default", s);
		error.show(stage);
	}

	public void showDialogChain(RetroDialogChain dialogChain) {
		dialogChain.show(stage);
	}

	// -----------------------------------
	// --------Retro-Man-Controlls--------
	// -----------------------------------

	/**
	 * Listener when the user clicks on Button Interact
	 * 
	 * @author RetroFactory
	 */
	private class InteractButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (!popupScreenIsShown) {
				gameController.interactRetroMan();
				gameController.walkThroughDoor();
			}
		}
	}

	// ------------------------------
	// ----Other Button Listener-----
	// ------------------------------

	/**
	 * Button which starts Evalution Gamecontroller checks if possible or not
	 * 
	 * @author Retro Factory
	 */
	private class TryEvaluationButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gameController.evaluationClicked();
		}
	}

	/**
	 * Button which gives a little hint to make the level
	 * 
	 * @author Retro Factory
	 */
	private class GetHintClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			popupScreenIsShown = true;
			showHint();
		}
	}

	/**
	 * Button which shows the Task of the Level
	 * 
	 * @author Retro Factory
	 */
	private class GetTaskClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			popupScreenIsShown = true;
			showTask();
		}
	}

	/**
	 * Button which shows the Level Menu and interrupts the Game
	 * 
	 * @author Retro Factory
	 */
	private class MenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			popupScreenIsShown = true;
			showPauseDialog();
		}
	}

	/**
	 * Button which shows the Level Menu and interrupts the Game
	 * 
	 * @author Retro Factory
	 */
	private class LevelMenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gameController.abortLevel();
			game.setScreen(new LevelMenuScreen(game));
		}
	}

	/**
	 * Button which shows the Level Menu and interrupts the Game
	 * 
	 * @author Retro Factory
	 */
	private class BackToGameClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			pauseDialog.hide();
			hintDialog.hide();
			taskDialog.hide();
			popupScreenIsShown = false;
		}
	}

	private class GameDialog extends Dialog {
		public GameDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}

		private void initialize() {

			setStage(new Stage());
			Table size = new Table();
			// size.debug();
			// set Buttons
			Button buttonBack = new Button(skin, "ok");
			buttonBack.pad(screenHeight / DEFAULTBUTTONSIZE);
			buttonBack.addListener(new BackToGameClickListener());

			// size.add(buttonBack).expandX().expandY().bottom().row();
			size.add().width(screenWidth * DIALOGTEXTWIDTH)
					.height(screenHeight * DIALOGHEIGHT * FOUR_FIFTH);
			size.add().row();
			size.add(buttonBack).expandX().expandY().bottom().row();
			this.padTop(screenWidth / PADDING_THIRTY); // set padding on top of the
													// dialog
			this.padBottom(screenWidth / PADDING_THIRTY); // set padding on bottom of
														// the
			this.add(size).expand();
		}

		private void printTree(Vertex actVertex, Vector2 position) {
			while (actVertex != null) {
				int centerVertex = (Constants.GAMEELEMENT_WIDTH * (actVertex
						.getWidth() - 1)) / 2;
				Vector2 pos = new Vector2(position.x + centerVertex, position.y);

				// Add depot

				int offset = (Integer) RetroAssetManager.getDepots().getProperties()
						.get("firstgid");
				int color = Constants.DEPOT_ID;
				DepotElement d = new DepotElement();
				d.setTileId(color + offset);
				d.setPosition(pos);
				this.addActor(d);

				// Add element
				if (actVertex.getGameElement() != null) {
					offset = (Integer) actVertex.getGameElement().getTileSet()
							.getProperties().get("firstgid") - 1;
					color = actVertex.getColor();
					actVertex.getGameElement().setTileId(color + offset);

					actVertex.getGameElement().setPosition(pos);
					this.addActor(actVertex.getGameElement());
					// print Family
					if (actVertex.getFamily() != null) {
						position.y += Constants.GAMELEMENT_PADDING;
						Vector2 famPos = new Vector2(position.x, position.y
								+ Constants.GAMEELEMENT_WIDTH);
						printTree(actVertex.getFamily(), famPos);
						position.y -= Constants.GAMELEMENT_PADDING;
					}
				}
				position.x = position.x
						+ (Constants.GAMEELEMENT_WIDTH * actVertex.getWidth());
				position.x += Constants.GAMELEMENT_PADDING;
				actVertex = actVertex.getNext();
			}
		}

	}

	private class TaskDialog extends GameDialog {

		public TaskDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}

		private void initialize() {
			super.printTree(gameController.getLevelTarget(), new Vector2(50,
					200));
		}

		@Override
		protected void result(Object object) {
			this.hide();
			popupScreenIsShown = false;
		}

	}

	private class HintDialog extends GameDialog {

		public HintDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}

		private void initialize() {
			super.printTree(gameController.getLevelHint(),
					new Vector2(100, 200));
		}

		@Override
		protected void result(Object object) {
			this.hide();
			popupScreenIsShown = false;
		}

	}

	private class PauseDialog extends Dialog {
		public PauseDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}

		private void initialize() {

			padTop(screenWidth / PADDING_THIRTY); // set padding on top of the dialog
			padBottom(screenWidth / PADDING_THIRTY); // set padding on bottom of the

			setModal(true);
			setMovable(false);
			setResizable(false);

			Table dialogTable = new Table(skin);

			Label header = new Label("Menue", skin);
			header.setWrap(true);
			header.setAlignment(Align.center);

			Label levelMenu = new Label("Ins Level-Menue", skin);
			levelMenu.setWrap(true);
			levelMenu.setAlignment(Align.center);
			levelMenu.setFontScale((DIALOG_FONTSIZE * screenWidth)
					/ DIVIDEWIDTHDEFAULT);

			Label back = new Label("Zurueck zum Spiel", skin);
			back.setWrap(true);
			back.setAlignment(Align.center);
			back.setFontScale((DIALOG_FONTSIZE * screenWidth)
					/ DIVIDEWIDTHDEFAULT);

			Button buttonHome = new Button(skin, ButtonStrings.HOME);
			buttonHome.pad(screenHeight / DEFAULTBUTTONSIZE);
			buttonHome.addListener(new LevelMenuClickListener());

			Button buttonBack = new Button(skin, "play");
			buttonBack.pad(screenHeight / DEFAULTBUTTONSIZE);
			buttonBack.addListener(new BackToGameClickListener());

			// Table setting and add
			dialogTable.add(header).colspan(COLSPAN_X_TWO).expandX().top()
					.padTop(screenHeight / DEFAULTPADDING)
					.padBottom(screenHeight / DEFAULTPADDING).row();
			dialogTable.add(buttonHome).padLeft(screenWidth / DEFAULTPADDING);
			dialogTable.add(levelMenu)
					.width(screenWidth * DIALOGTEXTWIDTH * FOUR_FIFTH)
					.height(screenHeight * DIALOGHEIGHT * ONE_THIRD).row();
			dialogTable.add(buttonBack).padLeft(screenWidth / DEFAULTPADDING);
			dialogTable.add(back)
					.width(screenWidth * DIALOGTEXTWIDTH * FOUR_FIFTH)
					.height(screenHeight * DIALOGHEIGHT * ONE_THIRD).row();

			this.add(dialogTable).width(screenWidth * DIALOGTEXTWIDTH)
					.height(screenHeight * DIALOGHEIGHT * FOUR_FIFTH);

		}

		@Override
		protected void result(Object object) {
		}
	}

	private class ErrorDialog extends Dialog {

		private String errorMessage;

		public ErrorDialog(String title, Skin skin, String windowStyleName,
				String errorMessage) {
			super(title, skin, windowStyleName);
			this.errorMessage = errorMessage;
			initialize();
		}

		private void initialize() {
			padTop(screenWidth / PADDING_THIRTY); // set padding on top of the dialog
												// title
			padBottom(screenWidth / PADDING_THIRTY); // set padding on bottom of the
												// dialog title
			getButtonTable().defaults().height(screenHeight * ONE_FOURTH); // set
																			// buttons
																			// height
			getButtonTable().defaults().width(screenWidth * ONE_FOURTH); // set
																			// buttons
																			// height
			setModal(true);
			setMovable(false);
			setResizable(false);
			Label dialogText = new Label(errorMessage, skin);
			dialogText.setWrap(true);
			dialogText.setAlignment(Align.center);
			dialogText.setFontScale((DIALOG_FONTSIZE * screenWidth)
					/ DIVIDEWIDTHDEFAULT);
			getContentTable().add(dialogText).width(
					screenWidth * DIALOGTEXTWIDTH);
			button(new Button(skin, ButtonStrings.OK), true);
		}

		@Override
		protected void result(Object object) {
			this.remove();
		}

		@Override
		public float getPrefWidth() {
			// force dialog width
			return screenWidth * DIALOGWIDTH;
		}

		@Override
		public float getPrefHeight() {
			// force dialog height
			return screenHeight * DIALOGHEIGHT;
		}
	}
}
