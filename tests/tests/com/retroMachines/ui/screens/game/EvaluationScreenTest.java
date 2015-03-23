package com.retroMachines.ui.screens.game;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Input.Keys;

public class EvaluationScreenTest {
	
	private EvaluationScreen screen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		screen = new EvaluationScreen(null, null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUnusedMethods() {
		screen.getScreenPadding();
		screen.isScreenUpdated();
		screen.keyDown(Keys.A);
		screen.keyUp(Keys.A);
		screen.keyTyped('C');
		screen.mouseMoved(0, 0);
		screen.render(0.1f);
		screen.runAnimation();
		screen.scrolled(0);
		screen.setOnStage(null);
		screen.touchDown(0, 0, 0, 0);
		screen.touchDragged(0, 0, 0);
		screen.touchUp(0, 0, 0, 0);
	}

}
