package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GameControllerTest {
	
	private GameController gameController;

	@Before
	public void setUp() throws Exception {
		gameController = new GameController(null);
	}

	@After
	public void tearDown() throws Exception {
		gameController = null;
	}

	@Test
	public void test() {
	}

}
