package com.retroMachines;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.game.controllers.StatisticController;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.CreateProfileMenuScreen;
import com.retroMachines.ui.screens.menus.MainMenuScreen;
import com.retroMachines.util.MusicManager;

/**
 * The main class of the game "RetroMachines". It initializes all the
 * controllers and starts the game.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class RetroMachines extends Game {

	/**
	 * A stack of screens in order to return to the previous.
	 */
	private final Stack<Screen> screenStack;

	/**
	 * The Profile Controller controls the profile information.
	 */
	private ProfileController profileController;

	/**
	 * The Setting Controller controls the general and user specific settings.
	 */
	private SettingController settingController;

	/**
	 * The Game Controller controls the starting and playing of the levels and
	 * starts the evaluation.
	 */
	private GameController gameController;

	/**
	 * The Statistic Controller controls the game statistics like PlayTime etc.
	 */
	private StatisticController statisticController;
	
	/**
	 * this variable will be set to true in case the android onResume method is called
	 * which will cause the game to abort a level 
	 */
	private boolean androidResume;

	/**
	 * Creates a new instance of retroMachines.
	 */
	public RetroMachines() {
		super();
		screenStack = new Stack<Screen>();
	}

	/**
	 * Initializes the game (all the controllers) after started by the Android
	 * Launcher. Afterwards it displays the loading screen to the user. When the
	 * game is loaded the main menu appears.
	 */
	@Override
	public void create() {
		// Back and Home Button
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
		GlobalVariables.getSingleton();
		profileController = new ProfileController(this);
		boolean profileExists = profileController.canLoadLastProfile();
		settingController = new SettingController(this);
		settingController.initialize();

		statisticController = new StatisticController(this);
		gameController = new GameController(this);

		settingController.add(MusicManager.getInstance());

		if (profileExists) {
			// a profile is available for loading
			setScreen(new MainMenuScreen(this));
		} else {
			// no profile go to createprofilemenuscreen
			setScreen(new CreateProfileMenuScreen(this));
		}
	}
	
	/**
	 * Reloads the previous screen.
	 */
	public void previousScreen() {
		if (screenStack.size() >= 2) {
			screenStack.pop(); // remove current screen.
			setScreen(screenStack.pop()); // set the screen before.
		}
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Getter for the Profile Controller.
	 * 
	 * @return The ProfileController.
	 */
	public ProfileController getProfileController() {
		return profileController;
	}

	/**
	 * Getter for the Setting Controller.
	 * 
	 * @return The SettingController.
	 */
	public SettingController getSettingController() {
		return settingController;
	}

	/**
	 * Getter for the Game Controller.
	 * 
	 * @return The GameController.
	 */
	public GameController getGameController() {
		return gameController;
	}

	/**
	 * Getter for the Statistic Controller.
	 * 
	 * @return The StatisticController.
	 */
	public StatisticController getStatisticController() {
		return statisticController;
	}
	
	/**
	 * is called when the android listener is resuming the app
	 * only relevant when in gamescreen
	 */
	public void androidResume() {
		if (getScreen() != null
			&& getScreen().getClass() == GameScreen.class) {
			this.androidResume = true;
		}
	}

	/**
	 * Overrides the method setScreen from Game and is used to set the screen.
	 * @param screen The screen that is to be set.
	 */
	@Override
	public void setScreen(Screen screen) {
		screenStack.push(screen);
		super.setScreen(screen);
	}
	
	@Override
	public void resume() {
		super.resume();
		if (this.androidResume && gameController != null) {
			this.androidResume = false;
			gameController.abortLevel();
		}
	}
}
