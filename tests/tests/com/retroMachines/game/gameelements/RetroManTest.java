package com.retroMachines.game.gameelements;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.gameelements.RetroMan;
import com.retroMachines.util.Constants;

@RunWith(GdxTestRunner.class)
public class RetroManTest {
	
	private RetroMan retroMan;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		retroMan = new RetroMan(Constants.TEXTURE_ANIMATION_NAMES[0]);
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
	
	@Test
	public void testPickUpElement() {
		MetalElement metal = new MetalElement(0);
		retroMan.pickupElement(metal);
		assertTrue("retroman sollte etwas tragen", retroMan.hasPickedUpElement());
		assertTrue("das spielelement sollte das gleiche sein", retroMan.layDownElement() == metal);
	}

	@Test
	public void testLanded() {
		retroMan.jump();
		assertFalse("retroman darf springen", retroMan.canJump());
		retroMan.getVelocity().y = 0; // geschwindigkeit in y richtung auf 0 stellen
		retroMan.landed();
		assertTrue("retroman darf nicht springen", retroMan.canJump());
	}
	
	@Test
	public void testLandedElement() {
		MachineElement machine = new MachineElement(0);
		retroMan.pickupElement(machine);
		retroMan.jump();
		assertFalse("retroman darf nochmals springen", retroMan.canJump());
		retroMan.getVelocity().y = 0;
		retroMan.landed();
		assertTrue("retroMan darf nicht springen", retroMan.canJump());
	}
}
