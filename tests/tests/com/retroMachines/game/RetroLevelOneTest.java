package com.retroMachines.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.RetroLevel.LevelBuilder;
import com.retroMachines.game.gameelements.GameElement;

@RunWith(GdxTestRunner.class)
public class RetroLevelOneTest {
	
	private RetroLevel level;
	
	public static final Vector2[] notEligablePositions = {
		new Vector2(7, 15),
		new Vector2(1, 20)
	};
	
	public static final Vector2 depotPosition = new Vector2(50, 6);
	
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
		builder.prepare(0);
		level = builder.getLevel();
	}

	@After
	public void tearDown() throws Exception {
		AssetManager.reloadMap(0);
	}

	@Test
	public void testDepotcheck() {
		assertFalse("gameelement already in depot", level.allDepotsFilled());
		assertNotNull("string message sollte erstellt worden sein", level.getErrorMessage());
		assertFalse("string sollte nicht leer sein", level.getErrorMessage().equals(""));
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
	public void testNonExistingGameElement() {
		Vector2 notExisting = notEligablePositions[0];
		GameElement element = level.getGameElement(notExisting);
		assertNull("dort sollte sich kein objekt befinden",	element);
	}
	
	@Test
	public void testRemoveGameElement() {
		Vector2 variablePosition = level.getLambdaUtil().getVertexList().get(0).getPosition();
		GameElement element = level.getGameElement(variablePosition);
		level.removeGameElement(element, variablePosition);
		element = level.getGameElement(variablePosition);
		assertNull("element sollte jetzt null sein", element);
	}
	
	@Test
	public void testRemoveGamelElementAndPlace() {
		Vector2 variablePosition = level.getLambdaUtil().getVertexList().get(0).getPosition();
		GameElement element = level.getGameElement(variablePosition);
		level.removeGameElement(element, variablePosition);
		level.placeGameElement(element, depotPosition);
		element = level.getGameElement(depotPosition);
		assertNotNull("spielobjekt sollte jetzt wieder existieren", element);
	}
	
	@Test
	public void testDepotsFilled() {
		Vector2 variablePosition = level.getLambdaUtil().getVertexList().get(0).getPosition();
		GameElement element = level.getGameElement(variablePosition);
		level.removeGameElement(element, variablePosition);
		level.placeGameElement(element, depotPosition);
		assertTrue("alle spielelemente sollten plaziert sein", level.allDepotsFilled());
	}
	
	@Test
	public void testDialogChain() {
		if (level.getLambdaUtil().hasTutorial()) {
			assertNotNull("eine dialogchain sollte erstellt werden", level.getDialogChain());
		}
	}
}
