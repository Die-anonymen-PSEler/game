package com.retroMachines.game;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.RetroLevel.LevelBuilder;
import com.retroMachines.game.gameelements.GameElement;

public class RetroLevelTwoTest {
	
	private RetroLevel level;
	
	private static final Vector2[] depots = {
		new Vector2(51, 6),
		new Vector2(54, 6),
		new Vector2(51, 9)
	};
	
	private static final Vector2 machine = new Vector2(16, 14);
	
	private static final Vector2 variableMachine = new Vector2(29, 6);
	
	private static final Vector2 variable = new Vector2(38, 13);

	@Before
	public void setUp() throws Exception {
		LevelBuilder builder = new LevelBuilder();
		builder.prepare(1);
		level = builder.getLevel();
		GameElement element = level.getGameElement(machine);
		level.removeGameElement(element, machine);
		level.placeGameElement(element, depots[0]);
		element = level.getGameElement(variableMachine);
		level.removeGameElement(element, variableMachine);
		level.placeGameElement(element, depots[1]);
		element = level.getGameElement(variable);
		level.removeGameElement(element, variable);
		level.placeGameElement(element, depots[2]);
	}

	@After
	public void tearDown() throws Exception {
		RetroAssetManager.reloadMap(1);
	}

	@Test
	public void testAllDepotsFilled() {
		assertTrue("alle depots sollten befüllt sein", level.isAllDepotsFilled());
	}

}
