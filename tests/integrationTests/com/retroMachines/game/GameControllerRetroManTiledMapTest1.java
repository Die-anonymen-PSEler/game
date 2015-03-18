package com.retroMachines.game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.gameelements.RetroMan;

public class GameControllerRetroManTiledMapTest1 {
	
	public static final Vector2 depotPosition = new Vector2(50, 6);
	
	public static final Vector2[] notEligablePositions = {
		new Vector2(7, 15),
		new Vector2(1, 20)
	};
	
	public static final Vector2 elementInitialPosition = new Vector2(20, 11);
	
	public static final Vector2 jumpTestPosition = new Vector2(14, 5);
	
	public static final Vector2 doorPosition = new Vector2(58, 7);
	
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
		gameController.startLevel(0);
		retroMan = gameController.getRetroMan();
	}

	@After
	public void tearDown() throws Exception {
		RetroAssetManager.reloadMap(0);
		game.getProfileController().deleteProfile("ABC");
	}
	
	@Test
	public void testPickUpElementFailing() {
		gameController.interactRetroMan();
		assertFalse("retroMan sollte noch kein element tragen", retroMan.hasPickedUpElement());
	}

	@Test
	public void testPickUpElementViaController() {
		retroMan.getPos().set(elementInitialPosition.cpy().add(-1, 0));
		assertFalse("sollte nach rechts gucken", retroMan.isFacedLeft());
		assertEquals("sollte der selbe vector", elementInitialPosition, retroMan.nextPosition());
		gameController.interactRetroMan();
		assertTrue("retroMan sollte das element aufgehoben haben", retroMan.hasPickedUpElement());
	}
	
	@Test
	public void testLayDownElementViaController() {
		retroMan.getPos().set(elementInitialPosition.cpy().add(-1, 0));
		assertFalse("sollte nach rechts gucken", retroMan.isFacedLeft());
		assertEquals("sollte der selbe vector", elementInitialPosition, retroMan.nextPosition());
		gameController.interactRetroMan();
		gameController.interactRetroMan();
		assertFalse("retroMan sollte das element wieder abgelegt haben", retroMan.hasPickedUpElement());
	}
	
	@Test
	public void testInteractJumping() {
		retroMan.getPos().set(elementInitialPosition.cpy().add(-1, 0));
		assertFalse("sollte nach rechts gucken", retroMan.isFacedLeft());
		assertEquals("sollte der selbe vector", elementInitialPosition, retroMan.nextPosition());
		retroMan.jump();
		gameController.interactRetroMan();
		assertFalse("retroman springt. eine aufnahme sollte nicht möglich sein", retroMan.hasPickedUpElement());
	}
	
	@Test
	public void testCollisionDetection() {
		retroMan.getPos().set(elementInitialPosition.cpy().add(-1, 0));
		assertFalse("sollte nach rechts gucken", retroMan.isFacedLeft());
		assertEquals("sollte der selbe vector", elementInitialPosition, retroMan.nextPosition());
		gameController.goRightRetroMan();
		gameController.update(0.1f);
		gameController.update(0.1f);
		gameController.update(0.1f);
		assertEquals("sollte seine position nicht verändert haben", retroMan.getPos(), elementInitialPosition.cpy().sub(1, 0));
	}
	
	@Test
	public void testCollisionDetection2() {
		retroMan.getPos().set(jumpTestPosition.cpy());
		gameController.jumpRetroMan();
		gameController.update(0.1f);
		gameController.update(0.1f);
		gameController.update(0.1f);
		gameController.update(0.1f);
	}
	
	@Test
	public void testLevelFinishedInteract() {
		gameController.evaluationComplete();
		gameController.interactRetroMan();
		retroMan.getPos().set(doorPosition.cpy());
		gameController.interactRetroMan();
	}
	
	@Test
	public void testLevelFinishedInteract2() {
		gameController.evaluationComplete();
		retroMan.getPos().set(doorPosition.cpy());
		retroMan.getVelocity().x = 0.5f;
		gameController.interactRetroMan();
	}
	
	@Test
	public void testDoubleJump() {
		gameController.jumpRetroMan();
		gameController.jumpRetroMan();
		assertFalse("retroman sollte nicht springen dürfen", retroMan.canJump());
	}
	
	@Test
	public void testEvaluationInvalid() {
		gameController.evaluationInComplete();
	}

}
