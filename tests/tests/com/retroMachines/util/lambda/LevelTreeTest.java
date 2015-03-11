package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LevelTreeTest {
	
	private LevelTree tree;

	@Before
	public void setUp() throws Exception {
		tree = new LevelTree(null);
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testLevelTree() {
		assertNull(tree.getStart());
	}

	@Test
	public void testEqualsLevelTree() {
		assertFalse("Null Tree nicht beachtet", tree.equals(null));
		Vertex v = new Application();
		LevelTree comp = new LevelTree(v);
		tree.setStart(v);
		assertTrue("equals schlägt fehlt", tree.equals(comp));
	}

	@Test
	public void testSetStart() {
		Vertex v = new Dummy();
		tree.setStart(v);
		assertTrue(tree.getStart().getType() == v.getType());
	}

	@Test
	public void testGetStart() {
		Vertex v = new Abstraction(0);
		tree.setStart(v);
		assertTrue(tree.getStart().getType() == v.getType() && tree.getStart().getColor() == v.getColor());
	}

}
