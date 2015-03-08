package com.retroMachines;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RetroMachinesTest {
	
	RetroMachines game;

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void testCreate() {
		game.create();
		assertFalse("gamecontroller shouldn't be null", game.getGameController() == null);
		assertFalse("settingcontroller shouldn't be null", game.getSettingController() == null);
		assertFalse("statisticcontroller shouldn't be null", game.getStatisticController() == null);
	}

}
