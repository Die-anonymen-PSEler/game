package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.util.lambda.*;

public class LambdaTests {
	
	private Tree tree;
	
	@Before
	public void setUp() throws Exception {
		tree = new Tree(null);
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testEvaluateNull() {
		tree.setStart(null);
		try {
			tree.evaluate();
		} catch (NullPointerException e) {
			fail("Case start == null not handled. NullPointerException thrown!");
		}
	}

	@Test
	public void testEvaluateVariable() {
		Variable v = new Variable(0);
		tree.setStart(v);
		tree.evaluate();
		assertTrue("Evaluation of just one Variable should not change anything!", v.equals(tree.getStart()));
	}

}
