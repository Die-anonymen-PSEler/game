package com.retroMachines.ui.screens.game;

import static org.junit.Assert.assertFalse;
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
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.gdxemulation.RetroInput;

@RunWith(GdxTestRunner.class)
public class GameScreenTest {
	
	private GameScreen screen;
	
	private RetroMachines game;
	
	private TestGameController gameController;
	
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
		gameController = new TestGameController(game);
		gameController.startLevel(1);
		screen = (GameScreen) game.getScreen();
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
	}

	@Test
	public void testInteract() {
		screen.dialogFinished();
		screen.keyDown(Keys.B);
		assertTrue("interact request wurde gemacht", gameController.interactRetroMan);
		gameController.interactRetroMan = false;
	}
	
	@Test
	public void testJumpScreen() {
		screen.dialogFinished();
		((RetroInput) Gdx.input).addPressedKey(Keys.SPACE);
		screen.render(0.1f);
		assertTrue("retroman sollte jumpen", gameController.jumpRetroMan);
		((RetroInput) Gdx.input).removePressedKey(Keys.SPACE);
	}
	
	@Test
	public void testLeft() {
		screen.dialogFinished();
		((RetroInput) Gdx.input).addPressedKey(Keys.LEFT);
		screen.render(0.1f);
		assertTrue("retroman sollte nach links gehen", gameController.leftRetroMan);
		((RetroInput) Gdx.input).removePressedKey(Keys.LEFT);
	}
	
	@Test
	public void testScreenLeft() {
		game.getSettingController().setLeftMode(true);
		gameController = new TestGameController(game);
		gameController.startLevel(1);
	}
	
	@Test
	public void testPopUpScreen() {
		((RetroInput) Gdx.input).addPressedKey(Keys.LEFT);
		screen.render(0.1f);
		assertFalse("retroman sollte nicht links gehen", gameController.leftRetroMan);
		((RetroInput) Gdx.input).removePressedKey(Keys.LEFT);
	}
	
	@Test
	public void testUnusedMethods() {
		screen.keyTyped('c');
		screen.keyUp(Keys.A);
		screen.mouseMoved(0, 0);
		screen.scrolled(0);
		screen.touchDown(0, 0, 0, 0);
		screen.touchDragged(0, 0, 0);
		screen.touchUp(0, 0, 0, 0);
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
