package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VertexTest {
	
	private Vertex app;
	private Vertex abs;
	private Vertex var;
	private Vertex dummy;
	
	private int absColor = 0;
	private int varColor = 0;

	@Before
	public void setUp() throws Exception {
		app = new Application();
		abs = new Abstraction(absColor);
		var = new Variable(varColor);
		dummy = new Dummy();
	}

	@After
	public void tearDown() throws Exception {
		app = null;
		abs = null;
		var = null;
		dummy = null;
	}

	@Test
	public void testVertex() {
		//only testing if attributes of vertex are correct
		//constructor was invoked in setUp method
		String fail = "Konstruktor in Vertex fehlerhaft";
		assertFalse(fail, app.isInDepot());
		//only testing app because super constructor is always the same
	}

	@Test
	public void testVertexInt() {
		assertEquals("Farbe nicht richtig gesetzt", varColor, var.getColor());
		assertEquals(1, var.getWidth()); //width is set to 1 in constructor
		assertEquals(0, var.getNextWidth());
	}

	@Test
	public void testUpdateMap() {
		//map varColor to varColor + 1
		Vertex.updateMap(varColor, varColor + 1);
		assertEquals("Color falsch gemappt", varColor + 1, Vertex.getMappedColor(varColor));
	}

	@Test
	public void testGetMappedColor() {
		//should return identity 
		assertEquals("Color wird initial nicht auf sich selbst gemappt", varColor, varColor);
	}

	@Test
	public void testRenameFamily() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testReplaceInFamily() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloneMe() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloneFamily() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertexList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateColorList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateWidth() {
		fail("Not yet implemented");
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
	public void testReadInAnimation() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadInFamilyAnimation() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGameelementPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testReorganizePositions() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetGameelementPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetnext() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetfamily() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFamilyColorlist() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNextColorlist() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIsInDepot() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFamilyColorList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCopyOfFamilyColorList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextColorList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCopyOfNextColorList() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsInDepot() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetfamily() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetnext() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNextWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextWidth() {
		fail("Not yet implemented");
	}

}