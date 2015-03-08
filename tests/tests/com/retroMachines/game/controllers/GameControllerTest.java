package com.retroMachines.game.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.RetroMachineMock;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.ui.screens.game.GameScreen;
import com.retroMachines.ui.screens.menus.LevelMenuScreen;

@RunWith(GdxTestRunner.class)
public class GameControllerTest {
	
	private GameController gameController;
	
	private RetroMachineMock game;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializePreLoading();
		AssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachineMock();
		gameController = new GameController(game);
	}

	@After
	public void tearDown() throws Exception {
		game = null;
		gameController = null;
	}

	@Test
	public void testGameScreenActive() {
		gameController.startLevel(1);
		assertTrue("gamescreen sollte aktiv sein", game.activeScreen instanceof GameScreen);
	}
	
	@Test
	public void testAbortLevel() {
		gameController.startLevel(1);
		ProfileController profileController = game.getProfileController();
		profileController.createProfile("rnd");
		float oldTimePlayed = game.getStatisticController().getPlaytime();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// need to sleep so we can check for a difference
		}
		gameController.abortLevel();
		assertTrue("spielzeit sollte sich vergrößert haben", oldTimePlayed < game.getStatisticController().getPlaytime());
		profileController.deleteProfile("rnd");
		assertTrue("level menu screen sollte gezeigt werden", game.activeScreen instanceof LevelMenuScreen);
	}
	
	@Test
	public void testGoRightRetroMan() {
		gameController.startLevel(1);
		RetroMan retroMan = gameController.getRetroMan();
		gameController.goRightRetroMan();
		assertTrue("RetroMan sollte eine x acceleration größer als null haben", retroMan.getVelocity().x > 0);
	}
	
	@Test
	public void testGoLeftRetroMan() {
		gameController.startLevel(1);
		RetroMan retroMan = gameController.getRetroMan();
		gameController.goLeftRetroMan();
		assertTrue("retroman sollte eine x acceleration kleiner als null haben", retroMan.getVelocity().x < 0);
	}
	
	@Test
	public void testUpdate1() {
		gameController.startLevel(1);
		RetroMan retroMan = gameController.getRetroMan();
		Vector2 oldPos = retroMan.getPos().cpy();
		gameController.goLeftRetroMan();
		gameController.update(0.5f);
		assertFalse("retroman should have moved", oldPos.equals(retroMan.getPos()));
	}
	
	@Test
	public void testUpdate2() {
		gameController.startLevel(1);
		RetroMan retroMan = gameController.getRetroMan();
		Vector2 oldPos = retroMan.getPos().cpy();
		gameController.update(0.5f);
		assertTrue("retroman should not have moved", oldPos.x == retroMan.getPos().x);
	}
	
	@Test
	public void testUpdate3() {
		gameController.startLevel(1);
		RetroMan retroMan = gameController.getRetroMan();
		gameController.jumpRetroMan();
		gameController.update(0.1f);
		assertTrue("y velocity is too big", retroMan.getVelocity().y <= RetroMan.MAX_VELOCITY_Y);
	}
}
