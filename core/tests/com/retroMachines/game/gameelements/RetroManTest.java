package com.retroMachines.game.gameelements;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.retroMachines.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class RetroManTest {
	
	private RetroMan retroMan;

	@Before
	public void setUp() throws Exception {
		retroMan = new RetroMan();
	}

	@After
	public void tearDown() throws Exception {
		retroMan = null;
	}

	@Test
	public void testJumpLock() {
		retroMan.jump();
		assertFalse("retroman is allowed to jump", retroMan.canJump());
	}

}
