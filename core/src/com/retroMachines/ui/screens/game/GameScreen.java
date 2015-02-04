package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
		SettingsChangeListener {

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
	 * True if LevelMenu is shown. No other Button clicks like steering of
	 * RetroMan are now possible True if LevelMenu, Hint or Task is shown. No
	 * other ButtonClicks like steering of RetroMan are now possible
	 */
	private boolean popupScreenIsShown;


	/**
	 * 
	 * @param game
	 * @param gameController
	 * @param leftiMode
	 */
	public GameScreen(RetroMachines game, GameController gameController,
			boolean leftiMode) {
		super(game);
		this.gameController = gameController;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();
		initialize();
	}

	public void initialize() {
		game.getSettingController().add(this);
		music = AssetManager.getMusic();
	}
	
	private void updateCameraPosition(float x, float y) {
		camera.position.x = gameController.getRetroMan().getPos().x;
		camera.position.y = gameController.getRetroMan().getPos().y;
	}

	/**
	 * Is called when this screen should be displayed. Starts to play the sound.
	 */
	public void show() {
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
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		inputDetection();
		
		updateCameraPosition(gameController.getRetroMan().getPos().x, gameController.getRetroMan().getPos().y);
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		gameController.getRetroMan().updateRetroMan(delta);
		gameController.getRetroMan().render(renderer, delta);
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
}
