package com.retroMachines.util.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;

@RunWith(GdxTestRunner.class)
public class VertexTest {
	
	private Vertex app;
	private Vertex abs;
	private Vertex var;
	private Vertex dummy;
	
	private int absColor = 1;
	private int varColor = 1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

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
		assertEquals("Color wird initial nicht auf sich selbst gemappt", varColor, Vertex.getMappedColor(varColor));
	}

	@Test
	public void testRenameFamily() {

		abs.setfamily(var);
		abs.setnext(null);
		var.setnext(null);
		var.setfamily(null);
		//assertTrue(abs.recolorFamily(1, 2));
		assertEquals(2, var.getColor());
		assertEquals(2, abs.getColor());
	}

	@Test
	public void testReplaceInFamily() {
		Variable var1 = new Variable(2);
		Variable var2 = new Variable(1);
		Variable var3 = new Variable(2);
		Variable var4 = new Variable(1);
		Abstraction abs1 = new Abstraction(2);
		Abstraction abs2 = new Abstraction(1);
		Abstraction abs3 = new Abstraction(1);
		Abstraction abs4 = new Abstraction(2);
		
		var1.setnext(abs1);
		abs1.setnext(var2);
		var2.setnext(abs2);
		abs2.setfamily(var4);
		var4.setnext(var3);
		abs.setnext(var);
		LinkedList<Vertex> result = var1.replaceInFamily(abs);
		assertEquals(2, result.size());
		assertEquals(var2.getColor(), result.get(0).getColor());
		assertEquals(var2.getType(), result.get(0).getType());
		assertEquals(var4.getColor(), result.get(1).getColor());
		assertEquals(var4.getType(), result.get(1).getType());
		assertNotSame(var2, result.get(0));
		assertNotSame(var2, result.get(1));
		assertNotSame(var4, result.get(0));
		assertNotSame(var4, result.get(1));
	}

	@Test
	public void testGetVertexList() {
		abs.setfamily(var);
		LinkedList<Vertex> expectedResult = new LinkedList<Vertex>();
		expectedResult.add(var);
		expectedResult.add(abs);
		assertEquals(expectedResult, abs.getVertexList());
	}

	@Test
	public void testUpdateColorList() {
		LinkedList<Integer> clonedList = new LinkedList<Integer>();
		clonedList.add(2);
		abs.setfamily(var);
		abs.updateColorList(clonedList, 1);
		assertEquals(1, abs.getFamilyColorList().size());
		assertEquals(clonedList.getFirst(), abs.getFamilyColorList().getFirst());
		assertEquals(1, var.getFamilyColorList().size());
		assertEquals(clonedList.getFirst(), var.getFamilyColorList().getFirst());
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
