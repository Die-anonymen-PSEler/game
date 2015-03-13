package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {
	
	private Dummy dummy;

	@Before
	public void setUp() throws Exception {
		dummy = new Dummy();
	}

	@After
	public void tearDown() throws Exception {
		dummy = null;
	}

	@Test
	public void testGetGameElement() {
		assertNull(dummy.getGameElement());
	}

	@Test
	public void testGetClone() {
		assertNull(dummy.getClone());
	}

	@Test
	public void testCloneMe() {
		assertNull(dummy.cloneMe());
	}

	@Test
	public void testCloneFamily() {
		assertNull(dummy.cloneFamily());
	}

	@Test
	public void testGetType() {
		assertEquals(dummy.getType(), "Dummy");
	}

	@Test
	public void testUpdatePointerAfterBetaReduction() {
		assertNull(dummy.updatePointerAfterBetaReduction());
	}

	@Test
	public void testGetEvaluationResult() {
		assertNull(dummy.getEvaluationResult());
	}

	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		//methode macht nichts, sollte einfach nur keine Exception werfen
	}

	@Test
	public void testDeleteAfterBetaReduction() {
		//methode macht nichts, sollte einfach nur keine Exception werfen
	}

	@Test
	public void testBetaReduction() {
		LinkedList<Vertex> list = dummy.betaReduction();
		assertEquals(list.size(), 0);
	}

	@Test
	public void testAlphaConversion() {
		assertFalse(dummy.canAlphaConversion());
	}

	@Test
	public void testGetReadIn() {
		assertNull(dummy.getReadIn());
	}

	@Test
	public void testReorganizePositions() {
		//methode macht nichts, sollte einfach nur keine Exception werfen
	}

	@Test
	public void testEquals() {
		assertFalse(dummy.equals(null));
		assertFalse(dummy.equals(new Application()));
		assertTrue(dummy.equals(new Dummy()));
	}

}
