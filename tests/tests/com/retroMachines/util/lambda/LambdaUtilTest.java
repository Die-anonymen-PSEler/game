package com.retroMachines.util.lambda;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.lambda.LambdaUtil.OnNextLambdaStepListener;

@RunWith(GdxTestRunner.class)
public class LambdaUtilTest {
	
	private LambdaUtil util;
	private OnNextLambdaStepListener listener;
	
	private String jsonFormat = "maps/LevelJsons/Level%d.json";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializePreLoading();
		AssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		util = new LambdaUtil();
		listener = new OnNextLambdaStepListener() {
			
			@Override
			public void nextLambdaStepPerformed() {
				//stub
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
		util.createTreeFromJson(fileName);
	}

	@Test
	public void testRegisterNewListener() {
		util.registerNewListener(listener);
		//listener should be observer now
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
	public void testGetVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertexList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameElementList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLevelTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTargetTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTutorials() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHintTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumOfDepots() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasTutorial() {
		fail("Not yet implemented");
	}

}
