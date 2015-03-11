package com.retroMachines.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Statistic;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.StatisticController;

public class StatisticProfileControllerIntegrationTest {
	
	private StatisticController statisticController;
	
	private ProfileController profileController;

	@Before
	public void setUp() throws Exception {
		RetroMachines game = new RetroMachines();
		game.create();
		statisticController = game.getStatisticController();
		profileController = game.getProfileController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProfileCreatedDeleted() {
		profileController.createProfile("maik");
		statisticController.incPlayTime(5.0f);
		profileController.createProfile("adrian");
		assertTrue("sollte default playtime sein", Statistic.DEFAULT_PLAYTIME == statisticController.getPlaytime());
		profileController.deleteProfile("adrian");
		assertTrue("sollte maiks fünf sekunden enthalten", Statistic.DEFAULT_PLAYTIME + 5.0f == statisticController.getPlaytime());
		profileController.deleteProfile("maik");
	}
	
	@Test
	public void testProfileChanged() {
		profileController.createProfile("maik");
		profileController.createProfile("adrian");
		profileController.changeActiveProfile("maik");
		statisticController.incStepCounter(50);
		profileController.changeActiveProfile("adrian");
		assertEquals("sollte noch keinen schritt gemacht haben", Statistic.DEFAULT_STEPCOUNTER, statisticController.getStepCounter());
		profileController.changeActiveProfile("maik");
		assertEquals("sollte 50 schritte + default gemacht haben", Statistic.DEFAULT_STEPCOUNTER + 50, statisticController.getStepCounter());
		profileController.deleteProfile("maik");
		profileController.deleteProfile("adrian");
	}

}
