package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.models.SettingsChangeListener;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.ui.screens.AbstractScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;

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
		SettingsChangeListener, InputProcessor {

	private static final float ZOOM_ADDITION = 0.25f;

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
	 * the sound which is played while this screen is displayed
	 */
	private Music music;

	/**
	 * the dialog which is shown when hintButton is pressed
	 */
	private HintDialog hintDialog;
	
	/**
	 * the dialog which is shown when pauseButton is pressed
	 */
	private PauseDialog pauseDialog;
	
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
			boolean leftiMode) {
		super(game);
		mapBounds = new int[4];
		camBounds = new float[4];
		this.gameController = gameController;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.zoom -= ZOOM_ADDITION;
		camera.update();
		leftMode = leftiMode;
		stage = new Stage();
		initialize();
	}

	public void initialize() {
		game.getSettingController().add(this);
		music = AssetManager.getMusic();
		inputMultiplexer.addProcessor(this);

		drawButtons();
		// instanciate HintDialog
		hintDialog = new HintDialog("", skin, "default");
		Button buttonOk = new Button(skin, "ok");
		hintDialog.button(buttonOk);
		hintDialog.text("Hint");
		hintDialog.button("OK", true);
		hintDialog.key(Keys.ENTER, true);
		//TODO: das Hinweisbild setzen + anzeigen
		
		// instanciate PauseDialog
		pauseDialog = new PauseDialog("", skin, "default");
		Button buttonToLevelMenu = new Button(skin, "ok");
		pauseDialog.button(buttonToLevelMenu);
		pauseDialog.text("Pause. Zurück ins Level-Menü?");
		pauseDialog.button("OK", true);
		pauseDialog.key(Keys.ENTER, true);

	}

	/**
	 * Is called when this screen should be displayed. Starts to play the sound.
	 */
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
		super.render(delta);

		inputDetection();

		updateCameraPosition(gameController.getRetroMan().getPos().x,
				gameController.getRetroMan().getPos().y);
		camera.update();

		renderer.setView(camera);
		renderer.render();

		gameController.update(delta);
		gameController.getRetroMan().render(renderer, delta);

		stage.act();
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
			camera.position.x = mapBounds[1] / 2;
		} else if (camBounds[3] <= 0) {
			camera.position.x = 0 + cameraHalfWidth;
		} else if (camBounds[1] >= mapBounds[1]) {
			camera.position.x = mapBounds[1] - cameraHalfWidth;
		}

		// Vertical axis
		if (mapBounds[0] < camera.viewportHeight) {
			camera.position.y = mapBounds[0] / 2;
		} else if (camBounds[2] <= 0) {
			camera.position.y = 0 + cameraHalfHeight;
		} else if (camBounds[0] >= mapBounds[0]) {
			camera.position.y = mapBounds[0] - cameraHalfHeight;
		}
	}

	private void drawButtons() {
		skin = AssetManager.getMenuSkin();

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
		table.add(buttonMenu).padTop(screenHeight / DEFAULTPADDINGx2)
				.padLeft(screenWidth / DEFAULTPADDINGx4).left();
		table.add(buttonEvaluation).padTop(screenHeight / DEFAULTPADDINGx2)
				.padRight(screenWidth / DEFAULTPADDINGx4).right().row();
		table.add().expand().row();

		innerButtonTable.add(buttonHint)
				.padRight(screenWidth / DEFAULTPADDINGx4)
				.padLeft(screenWidth / DEFAULTPADDING)
				.padBottom(screenWidth / DEFAULTPADDINGx2);
		innerButtonTable.add(buttonQuest)
				.padRight(screenWidth / DEFAULTPADDING)
				.padLeft(screenWidth / DEFAULTPADDINGx4)
				.padBottom(screenWidth / DEFAULTPADDINGx2);

		// Add to Table check LeftMode is activated
		if (leftMode) {
			buttonTable.add(buttonA).padRight(screenWidth / DEFAULTPADDINGx4)
					.padLeft(screenWidth / DEFAULTPADDINGx2)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
			buttonTable.add(buttonB).padRight(screenWidth / DEFAULTPADDING)
					.padLeft(screenWidth / DEFAULTPADDINGx4)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
			buttonTable.add(innerButtonTable);
			buttonTable.add(buttonLeft)
					.padRight(screenWidth / DEFAULTPADDINGx4)
					.padLeft(screenWidth / DEFAULTPADDING)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
			buttonTable.add(buttonRight)
					.padRight(screenWidth / DEFAULTPADDINGx2)
					.padLeft(screenWidth / DEFAULTPADDINGx4)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
		} else {
			buttonTable.add(buttonLeft)
					.padRight(screenWidth / DEFAULTPADDINGx4)
					.padLeft(screenWidth / DEFAULTPADDINGx2)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
			buttonTable.add(buttonRight).padRight(screenWidth / DEFAULTPADDING)
					.padLeft(screenWidth / DEFAULTPADDINGx4)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
			buttonTable.add(innerButtonTable);
			buttonTable.add(buttonB).padRight(screenWidth / DEFAULTPADDINGx4)
					.padLeft(screenWidth / DEFAULTPADDING)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
			buttonTable.add(buttonA).padRight(screenWidth / DEFAULTPADDINGx2)
					.padLeft(screenWidth / DEFAULTPADDINGx4)
					.padBottom(screenWidth / DEFAULTPADDINGx2);
		}

		table.add(buttonTable).colspan(COLSPANx2);
		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
	}

	/**
	 * Performs the input detection and delegates calls to the controller so it
	 * can perform the logic.
	 */
	private void inputDetection() {
		if (Gdx.input.isKeyPressed(Keys.SPACE) || buttonA.isPressed()) {
			gameController.jumpRetroMan();
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || buttonRight.isPressed()) {
			gameController.goRightRetroMan();
		} else if (Gdx.input.isKeyPressed(Keys.LEFT) || buttonLeft.isPressed()) {
			gameController.goLeftRetroMan();
		}
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * sets the sound to the new volume that was newly adjusted in the settings
	 */
	@Override
	public void onSettingsChanged() {
		// TODO Auto-generated method stub
		float newVolume = game.getSettingController().getVolume();
		music.setVolume(newVolume);
		// changes the volume in the settings so that its saved while quitting
		// the game
		game.getSettingController().setVolume(newVolume);
	}

	// ------------------------------------
	// --Show different views in the game--
	// ------------------------------------

	/**
	 * Shows the TaskScreen on top of the game.
	 */
	private void showTask() {

	}

	private void hideTask() {

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
		if (keycode == Keys.A) {
			// gameController.doorTestMethod();
		} else if (keycode == Keys.B) {
			gameController.interactRetroMan();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
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
			}
		}
	}

	// ------------------------------
	// ----Other Button Listener-----
	// ------------------------------

	/**
	 * Button which start Evalution Gamecontroller checks if possible or not
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
			// TODO: show level menu
			//pause();
			popupScreenIsShown = true;
			showPauseDialog();
		}
	}

	private class HintDialog extends Dialog {

		public HintDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			setStage(new Stage());
		}

		private void initialize() {
			// TODO: implement
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
			setStage(new Stage());
		}
		
		@Override
		protected void result(Object object) {
			this.hide();
			popupScreenIsShown = false; 
			gameController.abortLevel();
		}
	}
	
}
