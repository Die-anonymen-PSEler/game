package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.RetroMachines;

@RunWith(GdxTestRunner.class)
public class MenuScreenTest {
	
	private MenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		screen = new TestMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
		screen = null;
		game = null;
	}

	@Test
	public void testKeyDown() {
		Screen previous = game.getScreen();
		if (previous == null) {
			game.setScreen(new AboutMenuScreen(game));
			previous = game.getScreen();
		}
		game.setScreen(screen);
		screen.keyDown(Keys.BACKSPACE);
		assertTrue("sollte der ursprüngliche screen sein", game.getScreen() == previous);
	}
	
	@Test
	public void testKeyDown2() {
		Screen previous = game.getScreen();
		if (previous == null) {
			game.setScreen(new AboutMenuScreen(game));
			previous = game.getScreen();
		}
		game.setScreen(screen);
		screen.keyDown(Keys.BACK);
		assertTrue("sollte der ursprüngliche screen sein", game.getScreen() == previous);
	}
	
	@Test
	public void testKeyTyped() {
		screen.keyTyped('c');
	}
	
	@Test
	public void testKeyUp() {
		screen.keyUp(Keys.A);
	}
	
	@Test
	public void testMouseMoved() {
		screen.mouseMoved(0, 0);
	}
	
	@Test
	public void testRender() {
		screen.render(0.1f);
	}
	
	@Test
	public void testRemaining() {
		screen.scrolled(1);
		screen.touchDown(0, 0, 0, 0);
		screen.touchDragged(0, 0, 0);
		screen.touchUp(0, 0, 0, 0);
	}
	
	private static class TestMenuScreen extends MenuScreen {

		public TestMenuScreen(RetroMachines game) {
			super(game);
		}

		@Override
		protected void initialize() {
			
		}
		
	}
}
