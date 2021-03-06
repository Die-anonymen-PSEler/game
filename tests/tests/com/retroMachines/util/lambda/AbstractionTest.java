package com.retroMachines.util.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		Abstraction family = new Abstraction(1);
		abs.setFamily(family);
		clone = (Abstraction) abs.cloneMe();
		assertEquals(clone.getFamily(), family);
	}

	@Test
	public void testCloneFamily() {
		Abstraction next = new Abstraction(1);
		Abstraction fam = new Abstraction(2);
		abs.setFamily(fam);
		abs.setNext(next);
		Vertex clone = abs.cloneFamily();
		assertEquals(clone.getFamily(), abs.getFamily());
		assertEquals(clone.getNext(), abs.getNext());
		abs.setFamily(null);
		abs.setNext(null);
		clone = abs.cloneFamily();
		assertNull(clone.getFamily());
		assertNull(clone.getNext());
	}

	@Test
	public void testGetType() {
		assertEquals(abs.getType(), "Abstraction");
	}

	@Test
	public void testUpdatePointerAfterBetaReduction() {
		//nextNull attribute is false
		Abstraction family = new Abstraction(1);
		abs.setFamily(family);
		Vertex result = abs.updatePointerAfterBetaReduction();
		//should return family of abs
		assertEquals(result, family);
		abs.setNext(null);
		abs.setFamily(null);
		abs.betaReduction(); //sets nextNull true
		result = abs.updatePointerAfterBetaReduction();
		//result should be null
		assertNull(result);
	}

	@Test
	public void testGetEvaluationResult() {
		abs.setNext(null);
		abs.betaReduction();
		assertEquals(abs, abs.getEvaluationResult());
		abs.setNext(new Abstraction(3));
		abs.setFamily(new Application());
		abs.betaReduction();
		assertNull(abs.getEvaluationResult());
		//Get Evaluation result  gibt nur null zur�ck when abs.next  == null alles supi
	}

	@Test
	public void testUpdatePositionsAfterBetaReduction() {
//		abs.setNext(null);
//		abs.updatePositionsAfterBetaReduction();
//		abs.setNext(new Abstraction(2));
//		abs.updatePositionsAfterBetaReduction();
		//nextNull is false
		abs.setFamily(null);
		abs.updatePositionsAfterBetaReduction();//does nothing
		abs.setFamily(new Abstraction(1));
		abs.updatePositionsAfterBetaReduction();
		abs.setNext(null);
		abs.betaReduction(); //sets nextNull true
		abs.updatePositionsAfterBetaReduction();
	}

	@Test
	public void testDeleteAfterBetaReduction() {
		//nextNull attribute in Abstraction class is false
		abs.deleteAfterBetaReduction();
		abs.setNext(null);
		abs.betaReduction(); //sets nextNull attribute to true
		abs.deleteAfterBetaReduction();
	}

	@Test
	public void testBetaReduction() {
		Variable var1 = new Variable(1);
		Application app1 = new Application();
		abs.setNext(app1);
		abs.setFamily(var1);
		abs.betaReduction();
		assertTrue(abs.getFamily().getType().equals(Constants.RetroStrings.APPLICATION_TYPE));
		abs.setNext(new Abstraction(1));
		abs.setFamily(null);
		assertNull(abs.betaReduction());
		abs.setNext(null);
		assertEquals(0, abs.betaReduction().size());
		Abstraction next = new Abstraction(1);
		next.setNext(new Abstraction(2));
		abs.setNext(next);
		Variable family = new Variable(abs.getColor());
		family.setFamily(new Abstraction(3));
		family.setNext(new Abstraction(4));
		abs.setFamily(family);
		abs.betaReduction();
		assertEquals(abs.getFamily().getNext(), family.getNext());
		assertEquals(abs.getNext(), next.getNext());
		
	}

	@Test
	public void testAlphaConversion() {
		Variable var1 = new Variable(1);
		Abstraction abs1 = new Abstraction(1);
		abs.setFamily(var1);
		assertFalse(abs.alphaConversion());
		abs.setFamily(abs1);
		assertTrue(abs.alphaConversion());
	}

	@Test
	public void testGetReadIn() {
		assertEquals(abs.getReadIn(), abs.getNext());
	}

	@Test
	public void testReorganizePositions() {
		//reset action list
		EvaluationExecutioner.initialize(null);
		abs.setPosition(new Vector2(1f,1f));
		abs.setFamily(new Application());
		Application a = new Application();
		a.setFamily(new Application());
		a.setNext(new Application());
		abs.setNext(a);
		abs.reorganizePositions(new Vector2(2, 2) , new Vector2(3f, 0f));
		// position should not yet updated
		assertEquals(0f, abs.getGameElement().getPosition().x, Constants.FLOAT_EPSILON);
		assertEquals(0f, abs.getGameElement().getPosition().y, Constants.FLOAT_EPSILON);
		assertEquals(5, EvaluationExecutioner.getActionList().size());
		assertEquals(abs.getGameElement(), EvaluationExecutioner.getActionList().getLast().getGameElement());
		
	}

	@Test
	public void testAbstraction() {
		int someColor = 1337;
		abs = new Abstraction(someColor);
		assertEquals(abs.getColor(), someColor);
	}

}
