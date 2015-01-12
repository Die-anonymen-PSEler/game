package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.SettingsChangeListener;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.ui.screens.AbstractScreen;

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
		SettingsChangeListener {

	/**
	 * the map that is currently active and may be shown to the user in case the
	 * gamescreen is also active
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
	private Sound sound;

	/**
	 * file path of the soundfile
	 */
	private static final String SOUNDFILE = "";

	/**
	 * the soundID of the sound which is played while this screen is displayed
	 */
	private long soundId;
	
	/**
	 * True if LevelMenu is shown. No other Button clicks like steering of
	 * RetroMAn are now possible True if LevelMenu, Hint or Task is shown. No
	 * other Buttonclicks like steering of RetroMan are now possible
	 */
	private boolean popupScreenIsShown;
	
	/**
	 * stage shows the pop up's
	 */
	private Stage stage;


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
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		camera = new OrthographicCamera();
		initialize();
	}

	public void initialize() {
		game.getSettingController().add(this);
	}

	/**
	 * is called when this screen should be displayed. Starts to play the sound
	 */
	public void show() {
		soundId = sound.loop();
	}

	/**
	 * assigns a new TiledMap to the screen
	 * 
	 * @param map
	 */
	public void setMap(TiledMap map) {
		this.map = map;
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		inputDetection();

		camera.position.x = gameController.getRetroMan().getPos().x;
		camera.update();
		renderer.setView(camera);
		renderer.render();

		gameController.getRetroMan().render(delta);
	}

	/**
	 * performs the input detection and delegates calls to the controller so it
	 * can perform the logic
	 */
	private void inputDetection() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			gameController.jumpRetroMan();
		}
	}

	/**
	 * abolishes the screen and cleans up behind it.
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();

	}

	/**
	 * Makes the LevelMenu visible or not
	 * 
	 * @return boolean true if now visible flase if not
	 */
	public boolean showLevelMenuScreen() {
		return false;
	}

	/**
	 * sets the sound to the new volume that was newly adjusted in the settings
	 */
	@Override
	public void onSettingsChanged() {

		// TODO Auto-generated method stub
		float newVolume = game.getSettingController().getVolume();
		sound.setVolume(soundId, newVolume);
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
	 * @author Retro Factory
	 */
	private class RightButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * Listener when the user clicks on Button Left
	 * 
	 * @author Retro Factory
	 */
	private class LeftButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * Listener when the user clicks on Button Jump
	 * 
	 * @author Retro Factory
	 */
	private class JumpButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * Listener when the user clicks on Button Interact
	 * 
	 * @author Retro Factory
	 */
	private class InteractButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}

	// ------------------------------
	// --------Other Buttons---------
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
		}
	}

	/**
	 * Button which shows the Level Menu and interuots the Game
	 * 
	 * @author Retro Factory
	 */
	private class MenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}
}
