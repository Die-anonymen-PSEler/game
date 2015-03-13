package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.LightElement;

@RunWith(GdxTestRunner.class)
public class ApplicationTest {
	
	Application app;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		app = new Application();
	}

	@After
	public void tearDown() throws Exception {
		app = null;
	}

	@Test
	public void testGetGameElement() {
		GameElement g = app.getGameElement();
		assertTrue(g instanceof LightElement);
	}

	@Test
	public void testGetClone() {
		assertNotNull(app.getClone());
	}

	@Test
	public void testCloneMe() {
		//branch with family = null;
		app.setfamily(null);
		Vertex clone = app.cloneMe();
		assertNull(clone.getfamily());
		assertEquals(clone.getColor(), app.getColor());
		Application dummy = new Application();
		app.setfamily(dummy);
		clone  = app.cloneMe();
		assertEquals(clone.getfamily(), app.getfamily());
		
		
	}

	@Test
	public void testCloneFamily() {
		app.setnext(null);
		app.setfamily(null);
		Vertex clone = app.cloneFamily();
		assertNull(clone.getfamily());
		assertNull(clone.getnext());
		Application next = new Application();
		Application family = new Application();
		Application nextNext = new Application();
		next.setnext(nextNext);
		next.setfamily(null);
		app.setnext(next);
		app.setfamily(family);
		clone = app.cloneFamily();
		assertEquals(clone.getnext(), next);
		assertEquals(clone.getnext().getnext(), nextNext);
		assertEquals(clone.getfamily(), family);
	}

	@Test
	public void testGetType() {
		assertEquals("Application", app.getType());
	}

	@Test
	public void testGetEvaluationResult() {
		assertNull(app.getEvaluationResult());
	}

	@Test
	public void testUpdatePositionsAfterBetaReduction() {
		app.setfamily(new Application());
		app.updatePositionsAfterBetaReduction();
	}

	@Test
	public void testDeleteAfterBetaReduction() {
		app.deleteAfterBetaReduction();
	}

	@Test
	public void testBetaReduction() {
		app.betaReduction();
	}

	@Test
	public void testAlphaConversion() {
		assertFalse(app.alphaConversion());
	}

	@Test
	public void testGetReadIn() {
		assertNull(app.getReadIn());
	}

	@Test
	public void testReorganizePositions() {
		app.reorganizePositions(null, null);
	}

	@Test
	public void testApplication() {
		app = new Application();
	}

}
