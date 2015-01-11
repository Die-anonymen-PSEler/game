package com.retroMachines;

import com.badlogic.gdx.Game;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.game.controllers.StatisticController;
import com.retroMachines.ui.screens.menus.LoadMenuScreen;
import com.retroMachines.ui.screens.menus.MainMenuScreen;

/**
 * The main class of the game "RetroMachines". It initializes profile and setting controller and sets 
 * the loading screen. 
 * @author RetroFactory
 *
 */
public class RetroMachines extends Game {
	
	/**
	 * globalVariables that are stored within persistent Background storage
	 */
	private GlobalVariables globalVariables;
	
	/**
	 * Profile controller controlls Profile Information
	 */
	private ProfileController profileController;
	
	/**
	 * Setting Controller controls general and serspecific Settings
	 */
	private SettingController settingController;
	
	/**
	 * GameController controlls Start and Playing of an Level and starts evaluation
	 */
	private GameController gameController;
	
	/**
	 * Controlls Gamestatistics like PlayTime and so
	 */
	private StatisticController statisticController;
		
	/**
	 * initializes the game (all the controllers) after started by the Android Launcher.
	 * Afterwards it displays the loading screen to the user.
	 */
	@Override
	public void create () {
		profileController = new ProfileController(this);
		settingController = new SettingController(this);
		gameController = new GameController(this);
		statisticController = new StatisticController(this);
		globalVariables = GlobalVariables.getSingleton();
		LoadMenuScreen lms = new LoadMenuScreen(this);
		setScreen(lms);
		
		
		
		setScreen(new MainMenuScreen(this));
		lms.dispose();
	}

	/**
	 * Getter for the profile controller
	 * @return the profile controller
	 */
	public ProfileController getProfileController() {
		return profileController;
	}

	/**
	 * Getter for the setting controller
	 * @return the setting controller
	 */
	public SettingController getSettingController() {
		return settingController;
	}
	
	/**
	 * Getter for the gameController controller
	 * @return the setting controller
	 */
	public GameController getGameController() {
		return gameController;
	}
	
	/**
	 * Getter for the statistic controller
	 * @return the setting controller
	 */
	public StatisticController getStatistcController() {
		return statisticController;
	}
	
}
