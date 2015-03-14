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

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.gameelements.GameElement;

@RunWith(GdxTestRunner.class)
public class VariableTest {
	
	private Variable var;
	private static int varColor = 0;
	
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
		var.setfamily(fam);
		Vertex clone = var.cloneMe();
		assertEquals(clone.getfamily(), fam);
		var.setfamily(null);
		clone = var.cloneMe();
		assertNull(clone.getfamily());
	}
	
	@Test
	public void testCloneFamily() {
		Variable next = new Variable(1);
		Variable fam = new Variable(2);
		var.setnext(next);
		var.setfamily(fam);
		Vertex clone = var.cloneFamily();
		assertEquals(clone.getnext(), var.getnext());
		assertEquals(clone.getfamily(), var.getfamily());
		var.setfamily(null);
		var.setnext(null);
		clone = var.cloneFamily();
		assertNull(clone.getfamily());
		assertNull(clone.getnext());
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
		assertEquals(var.getnext(), next);
		
	}
	
	@Test
	public void testGetEvaluationResult() {
		assertEquals(var.getEvaluationResult(), var);
	}
	
	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		assertEquals(var, var.getEvaluationResult());
	}
	
	@Test
	public void testGetClone() {
		assertEquals(var.getClone().getColor(), var.getColor());
	}
}
