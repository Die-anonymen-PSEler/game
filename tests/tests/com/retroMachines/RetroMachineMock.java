package com.retroMachines;

import com.retroMachines.data.models.Setting;
import com.retroMachines.game.controllers.SettingController;

public class RetroMachineMock extends RetroMachines {
	
	private SettingController settingController;
	
	@Override
	public SettingController getSettingController() {
		if (settingController == null) {
			settingController = new SettingController(null);
			settingController.setSetting(new Setting(1));
		}
		return settingController;
	}
}
