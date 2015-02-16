package com.retroMachines.game.gameelements;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.game.gameelements.RetroMan;

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
	
	@Test
	public void testFacing() {
		retroMan.goLeft();
		assertTrue("retroman is looking to the right", retroMan.getFaceLeft());
		retroMan.goRight();
		assertFalse("retroman is looking to the left", retroMan.getFaceLeft());
	}
	
	@Test
	public void testLeftMovement() {
		retroMan.goLeft();
		assertTrue("retromans velocity is wrong", retroMan.getVelocity().x == -RetroMan.RUNNING_IMPULSE);
	}
	
	@Test
	public void testRightMovement() {
		retroMan.goRight();
		assertTrue("retromans velocity is wrong", retroMan.getVelocity().x == RetroMan.RUNNING_IMPULSE);
	}
	
	@Test
	public void testJumpLockOnFalling() {
		retroMan.getVelocity().y = -9f;
		assertFalse("retroman is allowed to jump", retroMan.canJump());
	}

}
