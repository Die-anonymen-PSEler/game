package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.util.Constants;

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
	public void testSearchEqualAbstractions() {
		
		// includes test recolor family
		Abstraction abs1 = new Abstraction(1);
		Abstraction abs2 = new Abstraction(1);
		Abstraction abs3 = new Abstraction(3);
		Abstraction abs4 = new Abstraction(1);
		Abstraction abs5 = new Abstraction(1);
		Abstraction abs6 = new Abstraction(1);
		Variable var1 = new Variable(1);
		Variable var2 = new Variable(1);
		Variable var3 = new Variable(1);
		Application app1 = new Application();
		app1.setFamily(abs6);
		abs.setFamily(abs1);
		abs.setNext(abs2);
		abs.getNextColorList().add(1);
		abs1.setNext(var1);
		abs1.setFamily(var3);
		abs2.setNext(abs3);
		abs3.setNext(var2);
		abs3.setFamily(abs5);
		abs3.getFamilyColorList().add(1);
		var2.setNext(app1);
		abs.searchEqualAbstractions(1, 2);
		assertEquals(2, abs1.getColor());
		assertEquals(2, abs2.getColor());
		assertEquals(2, abs.getColor());
		assertEquals(3, abs3.getColor());
		assertEquals(1, abs4.getColor());
		assertEquals(2, abs5.getColor());
		assertEquals(2, abs6.getColor());
		assertEquals(2, var1.getColor());
		assertEquals(1, var2.getColor());
		assertEquals(2, var3.getColor());
		assertEquals(1, app1.getColor());
	}

	@Test
	public void testReplaceInFamily() {
		Variable var1 = new Variable(2);
		Variable var2 = new Variable(1);
		Variable var3 = new Variable(2);
		Variable var4 = new Variable(1);
		Abstraction abs1 = new Abstraction(2);
		Abstraction abs2 = new Abstraction(1);
		new Abstraction(1);
		new Abstraction(2);
		
		var1.setNext(abs1);
		abs1.setNext(var2);
		var2.setNext(abs2);
		abs2.setFamily(var4);
		var4.setNext(var3);
		abs.setNext(var);
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
		abs.setFamily(var);
		LinkedList<Vertex> expectedResult = new LinkedList<Vertex>();
		expectedResult.add(var);
		expectedResult.add(abs);
		assertEquals(expectedResult, abs.getVertexList());
	}

	@Test
	public void testUpdateColorList() {
		LinkedList<Integer> clonedList = new LinkedList<Integer>();
		clonedList.add(2);
		abs.setFamily(var);
		abs.updateColorList(clonedList, 1);
		assertEquals(1, abs.getFamilyColorList().size());
		assertEquals(clonedList.getFirst(), abs.getFamilyColorList().getFirst());
		assertEquals(1, var.getFamilyColorList().size());
		assertEquals(clonedList.getFirst(), var.getFamilyColorList().getFirst());
	}

	@Test
	public void testUpdateWidth() {
		Application app1 = new Application();
		abs.setFamily(var);
		var.setNext(app1);
		abs.updateWidth();
		assertEquals(2, abs.getWidth());
	}

	@Test
	public void testUpdatePointerAfterBetaReduction() {
		Vertex v = abs;
		Abstraction next = new Abstraction(absColor + 1);
		Abstraction family  = new Abstraction(absColor + 2);
		abs.setFamily(family);
		abs.setNext(next);
		assertEquals(v.updatePointerAfterBetaReduction(), family);
		v.setNext(null);
		v.setFamily(null);
		assertNull(v.updatePointerAfterBetaReduction());
		Abstraction familyNext = new Abstraction(absColor + 3);
		family.setNext(familyNext);
		abs.setFamily(family);
		abs.setNext(next);
		v.updatePointerAfterBetaReduction();
		abs.setFamily(null);
		v.updatePointerAfterBetaReduction();
	}

	@Test
	public void testReadInAnimation() {
		//Includes read in Family Animation
		EvaluationOptimizer.initialize(null);
		Application app1 = new Application();
		Application app2 = new Application();
		Application app3 = new Application();
		abs.setFamily(app2);
		app2.setNext(app1);
		app2.setFamily(app3);
		abs.readInAnimation(new Vector2(0f, 0f));
		assertEquals(4, EvaluationOptimizer.getActionList().size());
		assertEquals(abs.getGameElement(), EvaluationOptimizer.getActionList().getLast().getGameElement());
	}

	@Test
	public void testUpdateGameelementPosition() {
		EvaluationOptimizer.initialize(null);
		Application app1 = new Application();
		abs.setNext(var);
		abs.setFamily(app1);
		abs.updateGameelementPosition(1, 1);
		assertEquals(0, (long)abs.getGameElement().getPosition().x);
		assertEquals(0, (long)abs.getGameElement().getPosition().y);
		assertEquals(3, EvaluationOptimizer.getActionList().size());
		assertEquals(abs.getGameElement(), EvaluationOptimizer.getActionList().getLast().getGameElement());
	}

	@Test
	public void testEquals() {
		assertFalse(abs.equals(null));
		assertTrue(abs.equals(abs));
		assertFalse(abs.equals(dummy));
		//testing next
		abs.setNext(new Abstraction(absColor + 2));
		Abstraction other = new Abstraction(absColor);
		other.setNext(null);
		assertFalse(abs.equals(other));
		other.setNext(dummy);
		assertFalse(abs.equals(other));
		//testing family 
		//next attribute has to be the same, otherwise we would not reach the section were family is completed
		abs.setNext(abs);
		other.setNext(abs);
		abs.setFamily(abs);
		other.setFamily(null);
		assertFalse(abs.equals(other));
		other.setFamily(dummy);
		assertFalse(abs.equals(other));
		other.setNext(abs.getNext());
		other.setFamily(abs.getFamily());
		assertTrue(abs.equals(other));
		//branch with different colors is missing
		other = new Abstraction(absColor + 1);
		other.setFamily(abs.getFamily());
		other.setNext(abs.getNext());
		assertFalse(abs.equals(other));
		
	}

	@Test
	public void testSetnext() {
		abs.setNext(dummy);
	}

	@Test
	public void testSetfamily() {
		abs.setFamily(dummy);
	}

	@Test
	public void testSetFamilyColorlist() {
		abs.setFamilyColorlist(new LinkedList<Integer>());
	}

	@Test
	public void testSetNextColorlist() {
		abs.setNextColorlist(new LinkedList<Integer>());
	}

	@Test
	public void testSetPosition() {
		abs.setPosition(new Vector2(0, 0));
	}

	@Test
	public void testSetIsInDepot() {
		abs.setIsInDepot(false);
	}

	@Test
	public void testGetFamilyColorList() {
		abs.getFamilyColorList();
	}

	@Test
	public void testGetCopyOfFamilyColorList() {
		LinkedList<Integer> colorList = new LinkedList<Integer>();
		colorList.add(0);
		colorList.add(1);
		colorList.add(2);
		abs.setFamilyColorlist(colorList);
		LinkedList<Integer> list = abs.getCopyOfFamilyColorList();
		assertEquals(list.size(), colorList.size());
		for (int i  = 0; i < list.size(); i++) {
			assertEquals(list.get(i), colorList.get(i));
		}
	}

	@Test
	public void testGetNextColorList() {
		abs.getNextColorList();
	}

	@Test
	public void testGetCopyOfNextColorList() {
		LinkedList<Integer> colorList = new LinkedList<Integer>();
		colorList.add(0);
		colorList.add(1);
		colorList.add(2);
		abs.setNextColorlist(colorList);
		LinkedList<Integer> list = abs.getCopyOfNextColorList();
		assertEquals(list.size(), colorList.size());
		for (int i  = 0; i < list.size(); i++) {
			assertEquals(list.get(i), colorList.get(i));
		}
	}

	@Test
	public void testIsInDepot() {
		abs.isInDepot();
	}

	@Test
	public void testGetColor() {
		assertEquals(abs.getColor(), absColor);
	}

	@Test
	public void testGetfamily() {
		abs.getFamily();
	}

	@Test
	public void testGetnext() {
		abs.getNext();
	}

	@Test
	public void testGetPosition() {
		abs.getPosition();
	}

	@Test
	public void testSetWidth() {
		abs.setWidth(17);
	}

	@Test
	public void testGetWidth() {
		abs.getWidth();
	}

	@Test
	public void testSetNextWidth() {
		abs.setNextWidth(17);
	}

	@Test
	public void testGetNextWidth() {
		abs.getNextWidth();
	}
	
	@Test
	public void testHashCode() {
		Vertex v = abs;
		assertEquals(v.hashCode(), 31);
	}

}
