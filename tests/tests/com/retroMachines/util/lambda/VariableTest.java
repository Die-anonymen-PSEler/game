package com.retroMachines.util.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class VariableTest {
	
	private Variable var;
	private static int varColor = 1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

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
		Variable fam = new Variable(1);
		var.setFamily(fam);
		Vertex clone = var.cloneMe();
		assertEquals(clone.getFamily(), fam);
		var.setFamily(null);
		clone = var.cloneMe();
		assertNull(clone.getFamily());
	}
	
	@Test
	public void testCloneFamily() {
		Variable next = new Variable(1);
		Variable fam = new Variable(2);
		var.setNext(next);
		var.setFamily(fam);
		Vertex clone = var.cloneFamily();
		assertEquals(clone.getNext(), var.getNext());
		assertEquals(clone.getFamily(), var.getFamily());
		var.setFamily(null);
		var.setNext(null);
		clone = var.cloneFamily();
		assertNull(clone.getFamily());
		assertNull(clone.getNext());
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
	}
	
	@Test
	public void testDeleteAfterBetaReduction() {
		var.deleteAfterBetaReduction();
	}
	
	@Test
	public void testUpdatePointerAfterBetaReduction() {
		Vertex next = var.updatePointerAfterBetaReduction();
		assertEquals(var.getNext(), next);
		
	}
	
	@Test
	public void testGetEvaluationResult() {
		assertEquals(var.getEvaluationResult(), var);
	}
	
	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		var.updatePositionsAfterBetaReduction();
	}
	
	@Test
	public void testGetClone() {
		assertEquals(var.getClone().getColor(), var.getColor());
	}
}
