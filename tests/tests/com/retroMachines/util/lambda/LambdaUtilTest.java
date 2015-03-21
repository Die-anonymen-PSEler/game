package com.retroMachines.util.lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.game.gameelements.MachineElement;
import com.retroMachines.util.lambda.LambdaUtil.OnNextLambdaStepListener;

@RunWith(GdxTestRunner.class)
public class LambdaUtilTest {

	private LambdaUtil util;
	private OnNextLambdaStepListener listener;

	private String jsonFormat = "maps/LevelJsons/Level%d.json";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		util = new LambdaUtil();
		listener = new OnNextLambdaStepListener() {

			@Override
			public void nextLambdaStepPerformed() {
				// stub
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		util = null;
	}

	@Test
	public void testLambdaUtil() {
		assertNotNull(util.getObservers());
	}

	@Test
	public void testCreateTreeFromJson() {
		String fileName = String.format(jsonFormat, 1);
		try {
			util.createTreeFromJson(String.format(jsonFormat, -1)); //throws Exception in method, should be catched
		} catch(Exception e) {
			
		}
		
		util.createTreeFromJson(fileName);
	}

	@Test
	public void testRegisterNewListener() {
		util.registerNewListener(listener);
		// listener should be observer now
		assertTrue(util.getObservers().contains(listener));
	}

	@Test
	public void testUnregisterNewListener() {
		util.unregisterNewListener(listener);
		assertFalse(util.getObservers().contains(listener));
	}

	@Test
	public void testGetGameElement() {
		util.getGameElement(0, 0);
	}

	@Test
	public void testGetVertexList() {
		util.getVertexList();
	}

	@Test
	public void testGetGameElementList() {
		util.getGameElementList();
	}

	@Test
	public void testGetLevelTree() {
		util.getLevelTree();
	}

	@Test
	public void testGetTargetTree() {
		util.getTargetTree();
	}

	@Test
	public void testGetTutorials() {
		util.getTutorials();
	}

	@Test
	public void testGetHintTree() {
		util.getHintTree();
	}

	@Test
	public void testGetNumOfDepots() {
		util.getNumOfDepots();
	}

	@Test
	public void testHasTutorial() {
		util.hasTutorial();
	}
	
	@Test
	public void testGetVertex() {
		assertNull(util.getVertex(null));
		util.createTreeFromJson(String.format(jsonFormat, 1)); //initializes vertex list
		Vertex v = util.getVertexList().get(0);
		GameElement g = v.getGameElement();
		assertEquals(util.getVertex(g), v);
		g = new MachineElement(1);
		assertNull(util.getVertex(g));
	}

}
