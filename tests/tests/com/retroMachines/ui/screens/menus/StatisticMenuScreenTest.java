package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.RetroMachines;

public class StatisticMenuScreenTest {
	
	private StatisticMenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABC");
		screen = new StatisticMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
	}

	@Test
	public void test() {
		
	}

}
