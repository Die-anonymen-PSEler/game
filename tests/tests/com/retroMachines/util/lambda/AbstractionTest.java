package com.retroMachines.util.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;

@RunWith(GdxTestRunner.class)
public class AbstractionTest {
	
	private Abstraction abs;
	private static int absColor = 1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		abs = new Abstraction(absColor);
	}

	@After
	public void tearDown() throws Exception {
		abs = null;
	}

	@Test
	public void testGetGameElement() {
		GameElement g = abs.getGameElement();
		assertNotNull(g);
	}

	@Test
	public void testGetClone() {
		Abstraction clone = (Abstraction) abs.getClone();
		assertEquals(clone, abs);
	}

	@Test
	public void testCloneMe() {
		Abstraction clone = (Abstraction) abs.cloneMe();
		assertEquals(clone, abs);
	}

	@Test
	public void testCloneFamily() {
		Abstraction next = new Abstraction(1);
		Abstraction fam = new Abstraction(2);
		abs.setfamily(fam);
		abs.setnext(next);
		Vertex clone = abs.cloneFamily();
		assertEquals(clone.getfamily(), abs.getfamily());
		assertEquals(clone.getnext(), abs.getnext());
		abs.setfamily(null);
		abs.setnext(null);
		clone = abs.cloneFamily();
		assertNull(clone.getfamily());
		assertNull(clone.getnext());
	}

	@Test
	public void testGetType() {
		assertEquals(abs.getType(), "Abstraction");
	}

	@Test
	public void testUpdatePointerAfterBetaReduction() {
		Abstraction next = new Abstraction(2);
		abs.setnext(next);
		abs.updatePointerAfterBetaReduction();
		abs.setnext(null);
		abs.updatePointerAfterBetaReduction();
	}

	@Test
	public void testGetEvaluationResult() {
		abs.setnext(null);
		assertEquals(abs, abs.getEvaluationResult());
		abs.setnext(new Abstraction(3));
		assertNull(abs.getEvaluationResult());
		//Get Evaluation result  gibt nur null zurück when abs.next  == null alles supi
	}

	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		abs.setnext(null);
		abs.updatePositionsAfterBetaReduction();
		abs.setnext(new Abstraction(2));
		abs.updatePositionsAfterBetaReduction();
	}

	@Test
	public void testDeleteAfterBetaReduction() {
		abs.setnext(null);
		abs.deleteAfterBetaReduction();
		abs.setnext(new Abstraction(2));
		abs.deleteAfterBetaReduction();
	}

	@Test
	public void testBetaReduction() {
		abs.setnext(new Abstraction(2));
		abs.setfamily(new Abstraction(3));
		LinkedList<Vertex> list = abs.betaReduction();
		fail();
		//TODO: validate list
	}

	@Test
	public void testAlphaConversion() {
		abs.setnext(null);
		assertFalse(abs.alphaConversion());
		abs.setnext(new Abstraction(2));
		boolean b = abs.alphaConversion();
		fail();
		//TODO: validate b
	}

	@Test
	public void testGetReadIn() {
		assertEquals(abs.getReadIn(), abs.getnext());
	}

	@Test
	public void testReorganizePositions() {
		//reset action list
		EvaluationOptimizer.initialize(null);
		abs.setPosition(new Vector2(1f,1f));
		abs.setfamily(new Application());
		Application a = new Application();
		a.setfamily(new Application());
		a.setnext(new Application());
		abs.setnext(a);
		abs.reorganizePositions(new Vector2(2, 2) , new Vector2(3f, 0f));
		// position should not yet updated
		assertEquals(0f, abs.getGameElement().getPosition().x, Constants.FLOAT_EPSILON);
		assertEquals(0f, abs.getGameElement().getPosition().y, Constants.FLOAT_EPSILON);
		assertEquals(5, EvaluationOptimizer.getActionList().size());
		assertEquals(abs.getGameElement(), EvaluationOptimizer.getActionList().getLast().getGameElement());
		
	}

	@Test
	public void testAbstraction() {
		int someColor = 1337;
		abs = new Abstraction(someColor);
		assertEquals(abs.getColor(), someColor);
	}

}
