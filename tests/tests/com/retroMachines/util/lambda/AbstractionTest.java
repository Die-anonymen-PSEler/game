package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.gameelements.GameElement;

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
		//@Adrian: die updatePointerAfterBetaReduction schmeißt ne NullPointer, wenn next null ist.
		//an der Stelle pointer.getnext.setnext...
	}

	@Test
	public void testGetEvaluationResult() {
		abs.setnext(null);
		assertNull(abs.getEvaluationResult());
		abs.setnext(new Abstraction(3));
		assertNull(abs.getEvaluationResult());
		fail(); //test funktioniert richtig, eingefügt damit der nachfolgende Kommentar nicht verloren geht
		//getEvaluationResult liefert immer Null zurück, ist das so gewollt?
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
		abs.reorganizePositions(null, null);
	}

	@Test
	public void testAbstraction() {
		int someColor = 1337;
		abs = new Abstraction(someColor);
		assertEquals(abs.getColor(), someColor);
	}

}
