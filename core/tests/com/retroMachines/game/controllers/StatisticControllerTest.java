package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StatisticControllerTest {

	private StatisticController statisticController;

	@Before
	public void setUp() throws Exception {
		statisticController = new StatisticController(null);
		try {
			statisticController.initialize();
		} catch (NullPointerException e) {
			// game is not initialized. calls to it are bad
		}

	}

	@After
	public void tearDown() throws Exception {
		statisticController = null;
	}

	/*
	 * @Test public void test() { //fail("Not yet implemented"); }
	 */

}
