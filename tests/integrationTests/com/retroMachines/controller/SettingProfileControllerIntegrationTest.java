package com.retroMachines.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Setting;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;

public class SettingProfileControllerIntegrationTest {
	
	private SettingController settingController;
	
	private ProfileController profileController;
	
	private RetroMachines game;

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		settingController = game.getSettingController();
		profileController = game.getProfileController();
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void testProfileChanged() {
		profileController.createProfile("Maik");
		settingController.setVolume(0.3f);
		profileController.createProfile("Adrian");
		assertTrue("sollte standard volume sein", settingController.getVolume() == Setting.DEFAULT_VOLUME);
		profileController.deleteProfile("Adrian");
		assertTrue("sollte wieder 0.3f volume von maik sein", settingController.getVolume() == 0.3f);
		profileController.deleteProfile("Maik");
	}

}
