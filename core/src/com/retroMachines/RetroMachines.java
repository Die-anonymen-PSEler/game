package com.retroMachines;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.sql.Database;
import com.retroMachines.data.RetroDatabase;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.game.controllers.StatisticController;
import com.retroMachines.ui.screens.menus.LoadMenuScreen;
import com.retroMachines.ui.screens.menus.MainMenuScreen;


/**
 * The main class of the game "RetroMachines". It initializes all the controllers and 
 * starts the game.
 * 
 * @author RetroFactory
 * 
 */
public class RetroMachines extends Game{
	
	public static final String TITLE="Game Project"; 
    public static final int WIDTH=1920,HEIGHT=1080; // used later to set window size

	
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

	/**
	 * Initializes the game (all the controllers) after started by the Android
	 * Launcher. Afterwards it displays the loading screen to the user. When the
	 * game is loaded the main menu appears.
	 */
	@Override
	public void create() {
		setScreen(new LoadMenuScreen(this));
		//RetroDatabase.getSingleton();
		//profileController = new ProfileController(this);
		//settingController = new SettingController(this);
		//gameController = new GameController(this);
		//statisticController = new StatisticController(this);
		//globalVariables = GlobalVariables.getSingleton();
	}
	
	@Override
	public void pause() {
		super.pause();
		RetroDatabase.closeDatabase();
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

}
