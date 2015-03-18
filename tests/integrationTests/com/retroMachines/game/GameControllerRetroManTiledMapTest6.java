package com.retroMachines.game;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.gameelements.RetroMan;

public class GameControllerRetroManTiledMapTest6 {

	private GameController gameController;
	
	private RetroMachines game;
	
	private RetroMan retroMan;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABC");
		gameController = game.getGameController();
		gameController.startLevel(5);
		retroMan = gameController.getRetroMan();
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
	}

	@Test
	public void test() {
		
	}

}
