package com.retroMachines.util.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.game.gameelements.GameElement;

public class VariableTest {
	
	private Variable var;
	private static int varColor = 0;

	@Before
	public void setUp() throws Exception {
		var = new Variable(varColor);
	}

	@After
	public void tearDown() throws Exception {
		var = null;
	}

	@Test
	public void testGetGameElement() {
		GameElement g = var.getGameElement();
		assertNotNull(g);
	}

	@Test
	public void testAlphaConversion() {
		assertFalse(var.alphaConversion());
	}
	
	@Test
	public void testBetaReduction() {
		LinkedList<Vertex> list = var.betaReduction();
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testCloneMe() {
		var.cloneMe();
		fail("Not Yet Implemented");
		//TODO: implement
	}
	
	@Test
	public void testCloneFamily() {
		var.cloneFamily();
		fail("Not Yet Implemented");
		//TODO: implement
	}
	
	@Test
	public void testType() {
		assertEquals("Variable", var.getType());
	}
	
	@Test
	public void testGetReadIn() {
		assertNull(var.getReadIn());
	}
	
	@Test 
	public void testreorganizePositions() {
		var.reorganizePositions(null, null);
		//TODO: coolen assert überlegen
	}
	
	@Test
	public void testDeleteAfterBetaReduction() {
		var.deleteAfterBetaReduction();
		//TODO: coolen assert überlegen
	}
	
	@Test
	public void testUpdatePointerAfterBetaReduction() {
		var.updatePointerAfterBetaReduction();
		//TODO: coolen assert überlegen
	}
	
	@Test
	public void testUpdatePointerAfterBetaReduction1() {
		Vertex v = var.updatePointerAfterBetaReduction();
		assertEquals(v, var.getnext());
	}
	
	@Test
	public void testGetEvaluationResult() {
		assertEquals(var.getEvaluationResult(), var);
	}
	
	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		var.updatePositionsAfterBetaReduction();
		//void methode, sollte einfach nur keine Exception werfen
	}
	
	@Test
	public void testGetClone() {
		Vertex v = var.getClone();
	}
}
