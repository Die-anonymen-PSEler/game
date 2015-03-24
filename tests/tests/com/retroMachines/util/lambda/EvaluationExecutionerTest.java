package com.retroMachines.util.lambda;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.controllers.EvaluationController;
import com.retroMachines.game.gameelements.LightElement;

public class EvaluationExecutionerTest {
	
	private EvaluationController e;
	
	@Before
	public void setUp() throws Exception {
		EvaluationExecutioner.initialize(null);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void moveAndScaleAnimationWithoutDelayTest() {
		LightElement l = new LightElement();
		EvaluationExecutioner.moveAndScaleAnimationWithoutDelay(new Vector2(0f,0f), l, true);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
		
		EvaluationExecutioner.initialize(null);
		EvaluationExecutioner.moveAndScaleAnimationWithoutDelay(new Vector2(0f,0f), l, false);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
	}
	
	@Test
	public void moveAndScaleAnimationTest() {
		LightElement l = new LightElement();
		EvaluationExecutioner.moveAndScaleAnimation(new Vector2(0f,0f), l, true);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
		
		EvaluationExecutioner.initialize(null);
		EvaluationExecutioner.moveAndScaleAnimation(new Vector2(0f,0f), l, false);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
	}
	
	@Test
	public void scaleAnimationTest() {
		LightElement l = new LightElement();
		EvaluationExecutioner.scaleAnimation(l, true);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
		
		EvaluationExecutioner.initialize(null);
		EvaluationExecutioner.scaleAnimation(l, false);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
	}
	
	@Test
	public void moveAnimationTest() {
		LightElement l = new LightElement();
		EvaluationExecutioner.moveAnimation(new Vector2(0f,0f), l, true);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
		
		EvaluationExecutioner.initialize(null);
		EvaluationExecutioner.moveAnimation(new Vector2(0f,0f), l, false);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
	}
	
	@Test
	public void delayAndRunNextStepAnimTest() {
		LightElement l = new LightElement();
		EvaluationExecutioner.delayAndRunNextStepAnim(l);
		assertTrue(EvaluationExecutioner.getActionList().getLast().getGameElement().equals(l));
		assertEquals(1, EvaluationExecutioner.getActionList().size());
	}
	
	@Test
	public void autoStepClickedTest() {
		EvaluationExecutioner.autoStepClicked();
		EvaluationExecutioner.autoStepClicked();
	}
	
	@Test
	public void nextStepClickedTest() {
		EvaluationExecutioner.nextStepClicked();
		EvaluationExecutioner.nextStepClicked();
	}
	
	@Test
	public void addMeToListnerListTest() {
		EvaluationExecutioner.addMeToListnerList(null);
		EvaluationExecutioner.addMeToListnerList(new EvaluationController(null, null, null));
	}
}
