package com.retroMachines.game.gameelements;

import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

public class GameElementTest {
	
	private GameElement element;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		element = new GameElement() {
			
			@Override
			public TiledMapTileSet getTileSet() {
				return null;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNullTextureRegion() {
		assertNull("sollte keine texture region enthalten", element.getTextureRegion());
	}
}
