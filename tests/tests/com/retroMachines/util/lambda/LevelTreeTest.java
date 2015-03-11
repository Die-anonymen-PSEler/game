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
		//tree.start is null at this point
		LevelTree comp = new LevelTree(null);
		assertTrue(tree.equals(comp));
		//tree.start is still now
		Vertex v = new Application();
		comp.setStart(v);
		//tree.equals(comp) should return false
		assertFalse(tree.equals(comp));
		//testing equals method with null tree
		assertFalse("Null Tree nicht beachtet", tree.equals(null));
		tree.setStart(v);
		assertTrue("equals schlägt fehlt", tree.equals(comp));
	}

	@Test
	public void testSetStart() {
		Vertex v = new Dummy();
		tree.setStart(v);
		assertTrue(tree.getStart().equals(v));
	}

	@Test
	public void testGetStart() {
		Vertex v = new Dummy();
		tree.setStart(v);
		assertTrue(tree.getStart().equals(v));
	}

}
