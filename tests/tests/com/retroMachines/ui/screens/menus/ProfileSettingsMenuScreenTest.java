package com.retroMachines.ui.screens.menus;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.RetroMachines;

public class ProfileSettingsMenuScreenTest {
	
	private ProfileSettingsMenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		HashMap<String, Integer> map = game.getProfileController().getProfileNameIdMap();
		for (String name : map.keySet()) {
			game.getProfileController().deleteProfile(name);
		}
		game.getProfileController().createProfile("maik");
		screen = new ProfileSettingsMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("maik");
	}

	@Test
	public void test() {
		
	}

}
