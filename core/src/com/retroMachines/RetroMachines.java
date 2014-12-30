package com.retroMachines;

import com.badlogic.gdx.Game;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;

public class RetroMachines extends Game {
	
	public static final int WIDTH = 0;


	public static final int HEIGHT = 0;
	
	/**
	 * controllers
	 */
	
	private ProfileController profileController;
	private SettingController settingController;
		
	@Override
	public void create () {
		profileController = new ProfileController(this);
		settingController = new SettingController(this);
	}

	public ProfileController getProfileController() {
		return profileController;
	}

	public SettingController getSettingController() {
		return settingController;
	}
	
}
