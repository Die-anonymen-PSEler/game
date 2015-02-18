package com.retroMachines.util.lambda;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Variable;

@RunWith(GdxTestRunner.class)
public class LambdaTests {
	
	private LambdaUtil lambdaUtil;
	private static final String pathToJson = "assets/maps/Prototype.json";
	FileHandle file;
	
	@Before
	public void setUp() throws Exception {
		lambdaUtil = new LambdaUtil();
		file = Gdx.files.internal(pathToJson);
		lambdaUtil.createTreeFromJson(pathToJson);
	}

	@After
	public void tearDown() throws Exception {
		lambdaUtil = null;
		file = null;
	}

	@Test
	public void testEvaluateNull() {
		LevelTree tree = new LevelTree(null);
		try {
			tree.evaluate();
		} catch (NullPointerException e) {
			fail("Case start == null not handled. NullPointerException thrown!");
		}
	}

	@Test
	public void testEvaluateVariable() {
		int someInt = 0;
		LevelTree tree = new LevelTree(new Variable(1, someInt));
		tree.evaluate();
		assertTrue("Evaluation of just one Variable should not change anything!", tree.getStart().getColor() == someInt);
	}
	
	@Test
	public void createTreeFromJsonTest() {
		LambdaUtil util = new LambdaUtil();
		util.createTreeFromJson(pathToJson);
	}


}
