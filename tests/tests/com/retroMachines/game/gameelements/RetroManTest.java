package com.retroMachines.game.gameelements;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.util.Constants;

@RunWith(GdxTestRunner.class)
public class RetroManTest {
	
	private RetroMan retroMan;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializeWhileLoading();
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
		retroMan.jump();
	}
	
	@Test
	public void testFacing() {
		retroMan.goLeft();
		assertTrue("retroman is looking to the right", retroMan.isFacedLeft());
		retroMan.goRight();
		assertFalse("retroman is looking to the left", retroMan.isFacedLeft());
	}
	
	@Test
	public void testLeftMovement() {
		retroMan.goLeft();
		assertTrue("retromans velocity is wrong", retroMan.getVelocity().x == -RetroMan.RUNNING_IMPULSE);
	}
	
	@Test
	public void testLeftMovementElement() {
		retroMan.pickupElement(new MachineElement(0));
		retroMan.goLeft();
		assertTrue("retromans velocity is wrong", retroMan.getVelocity().x == -RetroMan.RUNNING_IMPULSE);
	}
	
	@Test
	public void testRightMovement() {
		retroMan.goRight();
		assertTrue("retromans velocity is wrong", retroMan.getVelocity().x == RetroMan.RUNNING_IMPULSE);
	}
	
	@Test
	public void testRightMovementElement() {
		retroMan.pickupElement(new MachineElement(0));
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
	public void testStanding() {
		retroMan.standing();
	}
	
	@Test
	public void testStandingElement() {
		retroMan.pickupElement(new MetalElement(0));
		retroMan.standing();
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
	
	@Test
	public void testPickUpRunning() {
		retroMan.goRight();
		retroMan.pickupElement(new MetalElement(0));
		assertNotNull("sollte ein objekt tragen", retroMan.layDownElement());
	}
	
	@Test
	public void testNextPosition() {
		Vector2 next = retroMan.nextPosition();
		assertNotSame("position sollte nicht die aktuell position von retroMan sein", retroMan.getPos(), next);
		retroMan.goLeft();
		next = retroMan.nextPosition();
		assertNotSame("position sollte nicht die aktuell position von retroMan sein", retroMan.getPos(), next);
	}
	
	@Test
	public void testRender() {
		BatchTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(RetroAssetManager.getMap(1));
		MetalElement element = new MetalElement(0);
		element.setTileId(MetalElementTest.VALID_TILE_ID);
		assertNotNull(element.getTextureRegion());
		retroMan.render(renderer, 0.1f);
		retroMan.pickupElement(element);
		retroMan.render(renderer, 0.1f);
		retroMan.goLeft();
		retroMan.render(renderer, 0.1f);
		retroMan.layDownElement();
		retroMan.render(renderer, 0.1f);
	}
	
	@Test
	public void testRenderJumping() {
		BatchTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(RetroAssetManager.getMap(1));
		retroMan.jump();
		retroMan.render(renderer, 0.1f);
	}
	
	@Test
	public void testRendererJumpingE() {
		BatchTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(RetroAssetManager.getMap(1));
		MetalElement element = new MetalElement(0);
		element.setTileId(MetalElementTest.VALID_TILE_ID);
		assertNotNull(element.getTextureRegion());
		retroMan.pickupElement(element);
		retroMan.jump();
		retroMan.render(renderer, 0.1f);
	}
}
