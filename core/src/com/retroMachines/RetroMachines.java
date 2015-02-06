package com.retroMachines;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.RetroDatabase;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.game.controllers.StatisticController;
import com.retroMachines.ui.screens.menus.CreateProfileMenuScreen;
import com.retroMachines.ui.screens.menus.LoadMenuScreen;
import com.retroMachines.ui.screens.menus.MainMenuScreen;
import com.retroMachines.util.lambda.LambdaUtil;


/**
 * The main class of the game "RetroMachines". It initializes all the controllers and 
 * starts the game.
 * 
 * @author RetroFactory
 * 
 */
public class RetroMachines extends Game{
	
	public static final String TITLE="Game Project"; 
    public static final int WIDTH=1280,HEIGHT=720; // used later to set window size Desktop Mode
    
    private final Stack<Screen> screenStack;

	
	/**
	 * The Global Variables that are stored within persistent background storage.
	 */
	private GlobalVariables globalVariables;

	/**
	 * The Profile Controller controls the profile information.
	 */
	private ProfileController profileController;

	/**
	 * The Setting Controller controls the general and user specific settings.
	 */
	private SettingController settingController;

	/**
	 * The Game Controller controls the starting and playing of the levels and starts the
	 * evaluation.
	 */
	private GameController gameController;

	/**
	 * The Statistic Controller controls the game statistics like PlayTime etc.
	 */
	private StatisticController statisticController;
	
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
		//Back and Home Button
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
        AssetManager.initializePreLoading();
		setScreen(new LoadMenuScreen(this));
		AssetManager.initializeWhileLoading();
		RetroDatabase.getConnection();	// starts a connection to the database
		globalVariables = GlobalVariables.getSingleton();
		profileController = new ProfileController(this);
		boolean profileExists = profileController.loadLastProfile();
		settingController = new SettingController(this);
		settingController.initialize();
		statisticController = new StatisticController(this);
		gameController = new GameController(this);
		
		//test for LambdaUtil
		LambdaUtil util = new LambdaUtil();
		util.createTreeFromJson("maps/Prototype.json");
		
		if (profileExists) {
			// a profile is available for loading
			setScreen(new MainMenuScreen(this));
		}
		else {
			// no profile go to createprofilemenuscreen
			setScreen(new CreateProfileMenuScreen(this));
		}
	}
	
	@Override
	public void pause() {
		super.pause();
		//RetroDatabase.closeDatabase();
	}
	
	@Override
	public void resume() {
		super.resume();
		RetroDatabase.reopenDatabase();
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
	
	@Override
	public void setScreen(Screen screen) {
		screenStack.push(screen);
		super.setScreen(screen);
	}
	
	/**
	 * reloads the previous screen.
	 */
	public void previousScreen() {
		if (screenStack.size() >= 2) {
			screenStack.pop(); // remove current screen.
			setScreen(screenStack.pop()); // set the screen before.
		}
	}
}
