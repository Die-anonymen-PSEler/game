package com.retroMachines.ui.screens.game;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.gdxemulation.RetroInput;
import com.retroMachines.gdxemulation.RetroMachineMock;

@RunWith(GdxTestRunner.class)
public class GameScreenTest {
	
	private GameScreen screen;
	
	private RetroMachineMock game;
	
	private TestGameController gameController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializePreLoading();
		AssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachineMock();
		gameController = new TestGameController(game);
		gameController.startLevel(1);
		screen = (GameScreen) game.activeScreen;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInteract() {
		screen.initialize();
		screen.keyDown(Keys.B);
		assertTrue("interact request wurde gemacht", gameController.interactRetroMan);
		gameController.interactRetroMan = false;
	}
	
	@Test
	public void testJumpScreen() {
		screen.initialize();
		((RetroInput) Gdx.input).addPressedKey(Keys.SPACE);
		screen.render(0.1f);
		assertTrue("retroman sollte jumpen", gameController.jumpRetroMan);
		((RetroInput) Gdx.input).removePressedKey(Keys.SPACE);
	}
	
	@Test
	public void testLeft() {
		screen.initialize();
		((RetroInput) Gdx.input).addPressedKey(Keys.LEFT);
		screen.render(0.1f);
		assertTrue("retroman sollte nach links gehen", gameController.leftRetroMan);
		((RetroInput) Gdx.input).removePressedKey(Keys.LEFT);
	}
	
	private class TestGameController extends GameController {
		
		public boolean interactRetroMan;
		
		public boolean jumpRetroMan;
		
		public boolean leftRetroMan;

		public TestGameController(RetroMachines game) {
			super(game);
			interactRetroMan = false;
			jumpRetroMan = false;
			leftRetroMan = false;
		}
		
		@Override
		public void interactRetroMan() {
			interactRetroMan = true;
		}
		
		@Override
		public void jumpRetroMan() {
			jumpRetroMan = true;
		}
		
		@Override
		public void goLeftRetroMan() {
			leftRetroMan = true;
		}
	}
}
