package com.retroMachines.game.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.gdxemulation.RetroMachineMock;
import com.retroMachines.util.Constants;

public class StatisticControllerTest {

	private StatisticController statisticController;
	
	private RetroMachineMock game;

	@Before
	public void setUp() throws Exception {
		game = new RetroMachineMock();
		game.getProfileController().createProfile("statistic");
		game.statisticController = new StatisticController(game);
		statisticController = game.statisticController;
		try {
			statisticController.initialize();
		} catch (NullPointerException e) {
			// game is not initialized. calls to it are bad
		}

	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("statistic");
		statisticController = null;
	}
	
	@Test
	public void testIncPlayTime() {
		float oldPlaytime = statisticController.getPlaytime();
		statisticController.incPlayTime(-1);
		assertTrue("sollte eine minute hinzugefügt werden", statisticController.getPlaytime() - Constants.SECONDS_IN_MINUTE == oldPlaytime);
	}
	
	@Test
	public void testIncLevelCompleted() {
		statisticController.incLevelCompleted(1);
		assertTrue("falsche anzahl an levels freigeschaltet", statisticController.getLevelsCompleted() >= 1);
	}
	
	@Test
	public void testStepCounter() {
		int oldValue = statisticController.getStepCounter();
		statisticController.incStepCounter(-1);
		assertTrue("falsche schritte geändert", statisticController.getStepCounter() - 1 == oldValue);
	}
	
	@Test
	public void testProfileChanged() {
		ProfileController profileController = game.getProfileController();
		statisticController.incStepCounter(133709);
		profileController.createProfile("abc");
		//statisticController.profileChanged();
		assertTrue("sollte kein schritt sein", statisticController.getStepCounter() == 0);
		profileController.deleteProfile("abc");
	}
}
