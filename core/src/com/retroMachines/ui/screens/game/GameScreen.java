package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.models.SettingsChangeListener;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * This class is part of the view of RetroMachines. 
 * It displays the actual game to the user. It's controller is 
 * the gameController which handles all the logic. If something needs 
 * to be rendered for the game it should be placed
 * within the render method of this class.
 * 
 * @author RetroFactory
 * 
 */
public class GameScreen extends AbstractScreen implements
		SettingsChangeListener, InputProcessor {

	
	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADING = 25f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	public static final int OBJECT_LAYER = 5;
	
	
	
	/**
	 * the map that is currently active and may be shown to the user in case the
	 * gameScreen is shown.
	 */
	private TiledMap map;

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
		
		if(mapBounds[1] < camera.viewportWidth)
		{
		    camera.position.x = mapBounds[1] / 2;
		}
		else if(camBounds[3] <= 0)
		{
			camera.position.x = 0 + cameraHalfWidth;
		}
		else if(camBounds[1] >= mapBounds[1])
		{
			camera.position.x = mapBounds[1] - cameraHalfWidth;
		}

		// Vertical axis
		if(mapBounds[0] < camera.viewportHeight)
		{
			camera.position.y = mapBounds[0] / 2;
		}
		else if(camBounds[2] <= 0)
		{
			camera.position.y = 0 + cameraHalfHeight;
		}
		else if(camBounds[0] >= mapBounds[0])
		{
			camera.position.y = mapBounds[0] - cameraHalfHeight;
		}
	}

	/**
	 * Is called when this screen should be displayed. Starts to play the sound.
	 */
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		//music.play();
	}

	/**
	 * Assigns a new TiledMap to the screen.
	 * 
	 * @param map the tiled map for this screen.
	 */
	public void setMap(TiledMap map) {
		this.map = map;
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
		
		updateCameraPosition(gameController.getRetroMan().getPos().x, gameController.getRetroMan().getPos().y);
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		gameController.update(delta);
		gameController.getRetroMan().render(renderer, delta);
		
		stage.act();
        stage.draw();
	}
	
	private void drawButtons() {
		skin = AssetManager.getMenuSkin();
		
		Table table = new Table(skin);
		table.setBounds(0, 0, screenWidth, screenHeight);
		
		Table buttonTable = new Table(skin);
		
		Table innerButtonTable = new Table(skin);
		
		
		// Make Buttons
		Button buttonLeft = new Button(skin, "left");
		buttonLeft.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonLeft.addListener(new LeftButtonClickListener());
		
		Button buttonRight = new Button(skin, "right");
		buttonRight.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonRight.addListener(new RightButtonClickListener());
		
		Button buttonA = new Button(skin, "a");
		buttonA.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonA.addListener(new JumpButtonClickListener());
		
		Button buttonB = new Button(skin, "b");
		buttonB.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonB.addListener(new InteractButtonClickListener());
		
		Button buttonHint = new Button(skin, "answer");
		buttonHint.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonHint.addListener(new GetHintClickListener());
		
		Button buttonQuest = new Button(skin, "quest");
		buttonQuest.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonQuest.addListener(new GetTaskClickListener());
		
		
		//Make Table
		table.add().expand().row();
		
		innerButtonTable.add(buttonHint).padRight(screenWidth / DEFAULTPADINGx4).padLeft(screenWidth / DEFAULTPADING).padBottom(screenWidth / DEFAULTPADINGx2);
		innerButtonTable.add(buttonQuest).padRight(screenWidth / DEFAULTPADING).padLeft(screenWidth / DEFAULTPADINGx4).padBottom(screenWidth / DEFAULTPADINGx2);
		
		// Add to Table check LeftMode is activated
		if (leftMode) {
			buttonTable.add(buttonA).padRight(screenWidth / DEFAULTPADINGx4).padLeft(screenWidth / DEFAULTPADINGx2).padBottom(screenWidth / DEFAULTPADINGx2);
			buttonTable.add(buttonB).padRight(screenWidth / DEFAULTPADING).padLeft(screenWidth / DEFAULTPADINGx4).padBottom(screenWidth / DEFAULTPADINGx2);
			buttonTable.add(innerButtonTable);
			buttonTable.add(buttonLeft).padRight(screenWidth / DEFAULTPADINGx4).padLeft(screenWidth / DEFAULTPADING).padBottom(screenWidth / DEFAULTPADINGx2);
			buttonTable.add(buttonRight).padRight(screenWidth / DEFAULTPADINGx2).padLeft(screenWidth / DEFAULTPADINGx4).padBottom(screenWidth / DEFAULTPADINGx2);
		} else {
			buttonTable.add(buttonLeft).padRight(screenWidth / DEFAULTPADINGx4).padLeft(screenWidth / DEFAULTPADINGx2).padBottom(screenWidth / DEFAULTPADINGx2);
			buttonTable.add(buttonRight).padRight(screenWidth / DEFAULTPADING).padLeft(screenWidth / DEFAULTPADINGx4).padBottom(screenWidth / DEFAULTPADINGx2);
			buttonTable.add(innerButtonTable);
			buttonTable.add(buttonB).padRight(screenWidth / DEFAULTPADINGx4).padLeft(screenWidth / DEFAULTPADING).padBottom(screenWidth / DEFAULTPADINGx2);
			buttonTable.add(buttonA).padRight(screenWidth / DEFAULTPADINGx2).padLeft(screenWidth / DEFAULTPADINGx4).padBottom(screenWidth / DEFAULTPADINGx2);
		}
		
		table.add(buttonTable);
		stage.addActor(table);
		
		inputMultiplexer.addProcessor(stage);
	}

	/**
	 * Performs the input detection and delegates calls to the controller so it
	 * can perform the logic.
	 */
	private void inputDetection() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			gameController.jumpRetroMan();
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			gameController.goRightRetroMan();
		}
		else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			gameController.goLeftRetroMan();
		}
	}

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

	// -----------------------------------
	// --------Retro-Man-Controlls--------
	// -----------------------------------

	/**
	 * Listener when the user clicks on Button Right
	 * 
	 * @author RetroFactory
	 */
	private class RightButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (!popupScreenIsShown) {
				gameController.goRightRetroMan();
			}
		}
	}

	/**
	 * Listener when the user clicks on Button Left
	 * 
	 * @author RetroFactory
	 */
	private class LeftButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (!popupScreenIsShown) {
				gameController.goLeftRetroMan();
			}
		}
	}

	/**
	 * Listener when the user clicks on Button Jump
	 * 
	 * @author RetroFactory
	 */
	private class JumpButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (!popupScreenIsShown) {
				gameController.jumpRetroMan();
			}
		}
	}

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

	}
	
	private void hideHint() {
		
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
			// TODO Auto-generated method stub
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
			//TODO: show level menu
			pause();
		}
	}


	
	/*
	 * input processing (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */


	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A) {
			gameController.doorTestMethod();
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
}
