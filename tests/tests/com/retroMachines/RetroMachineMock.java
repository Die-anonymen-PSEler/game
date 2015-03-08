package com.retroMachines;

import com.badlogic.gdx.Screen;
import com.retroMachines.data.models.Setting;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.game.controllers.StatisticController;

public class RetroMachineMock extends RetroMachines {
	
	public SettingController settingController;
	
	public ProfileController profileController;
	
	public StatisticController statisticController;
	
	public Screen activeScreen;
	
	@Override
	public StatisticController getStatisticController() {
		if (profileController == null) {
			profileController = new ProfileController(this);
		}
		if (statisticController == null) {
			statisticController = new StatisticController(this);
		}
		return statisticController;
	}
	
	@Override
	public ProfileController getProfileController() {
		if (profileController == null) {
			profileController = new ProfileController(this);
		}
		return profileController;
	}
	
	@Override
	public SettingController getSettingController() {
		if (settingController == null) {
			settingController = new SettingController(null);
			settingController.setSetting(new Setting(1));
		}
		return settingController;
	}
	
	@Override
	public void setScreen(Screen screen) {
		activeScreen = screen;
	}
}
