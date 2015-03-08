package com.retroMachines.game;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.RetroLevel;
import com.retroMachines.game.RetroLevel.LevelBuilder;
import com.retroMachines.game.gameelements.GameElement;

@RunWith(GdxTestRunner.class)
public class RetroLevelOneTest {
	
	private RetroLevel level;
	
	public static final Vector2[] notEligablePositions = {
		new Vector2(7, 15),
		new Vector2(1, 20)
	};
	
	public static final Vector2 depotPosition = new Vector2(50, 16);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializeWhileLoading();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		LevelBuilder builder = new LevelBuilder();
		builder.prepare(1);
		level = builder.getLevel();
	}

	@After
	public void tearDown() throws Exception {
		AssetManager.reloadMap(1);
	}

	@Test
	public void testDepotcheck() {
		assertFalse("gameelement already in depot", level.allDepotsFilled());
	}
	
	@Test
	public void testValidPositions() {
		for (int i = 0; i < notEligablePositions.length; i++) {
			assertFalse("das ist keine legitime position", level.isValidGameElementPosition(notEligablePositions[i]));
		}
	}
	
	@Test
	public void testGameElementExists() {
		Vector2 variablePosition = level.getLambdaUtil().getVertexList().get(0).getPosition();
		GameElement element = level.getGameElement(variablePosition);
		assertFalse("element ist null :(", element == null);
	}
	
	@Test
	public void testRemoveGameElement() {
		
	}

}
