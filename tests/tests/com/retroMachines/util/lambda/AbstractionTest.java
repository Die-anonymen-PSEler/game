package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.game.gameelements.GameElement;

public class AbstractionTest {
	
	private Abstraction abs;
	private static int absColor = 0;

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
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		assertEquals(abs.getType(), "Abstraction");
	}

	@Test
	public void testUpdatePointerAfterBetaReduction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEvaluationResult() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAfterBetaReduction() {
		fail("Not yet implemented");
	}

	@Test
	public void testBetaReduction() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlphaConversion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReadIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testReorganizePositions() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstraction() {
		fail("Not yet implemented");
	}

}
