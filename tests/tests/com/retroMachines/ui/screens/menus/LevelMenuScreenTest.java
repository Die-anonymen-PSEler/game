package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.RetroMachines;

public class LevelMenuScreenTest {
	
	private LevelMenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABD");
		screen = new LevelMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABD");
	}

	@Test
	public void test() {
		// no public methods
	}

}
