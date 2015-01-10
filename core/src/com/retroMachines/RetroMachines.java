package com.retroMachines;

import com.badlogic.gdx.Game;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.ui.screens.menus.LoadMenuScreen;
import com.retroMachines.ui.screens.menus.MainMenuScreen;

public class RetroMachines extends Game {
	
	public static final int WIDTH = 0;


	public static final int HEIGHT = 0;
	
	/**
	 * controllers
	 */
	private ProfileController profileController;
	private SettingController settingController;
		
	/**
	 * initializes the game (all the controllers) after started by the Android Launcher.
	 * Afterwards it displays the loading screen to the user.
	 */
	@Override
	public void create () {
		profileController = new ProfileController(this);
		settingController = new SettingController(this);
		LoadMenuScreen lms = new LoadMenuScreen(this);
		setScreen(lms);
		
		
		
		setScreen(new MainMenuScreen(this));
		lms.dispose();
	}

	public ProfileController getProfileController() {
		return profileController;
	}

	public SettingController getSettingController() {
		return settingController;
	}
	
}
