package com.retroMachines.ui.screens;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.menus.AboutMenuScreen;

@RunWith(GdxTestRunner.class)
public class AbstractScreenTest {
	
	private AbstractScreen screen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		screen = new TestAbstractScreen(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testKeyUp() {
		screen.keyUp(0);
	}
	
	@Test
	public void testRender() {
		screen.render(0.3f);
	}
	
	@Test
	public void testShow() {
		screen.show();
	}
	
	@Test
	public void testHide() {
		screen.hide();
	}
	
	@Test
	public void testKeyDown() {
		screen.keyDown(0);
	}
	
	@Test
	public void testDispose() {
		screen.dispose();
	}
	
	@Test
	public void testPause() {
		screen.pause();
	}
	
	@Test
	public void testResume() {
		screen.resume();
	}
	
	private class TestAbstractScreen extends AbstractScreen {

		public TestAbstractScreen(RetroMachines game) {
			super(game);
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {

			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}
		
	}

}
