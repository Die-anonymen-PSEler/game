package com.retroMachines.game.gameelements;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.data.RetroAssetManager;

public class MetalElementTest {
	
	private MetalElement element;
	
	public static final int VALID_TILE_ID = 36;
	
	private static final int INVALID_TILE_ID = 3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		element = new MetalElement(0);
		/*
		TiledMapTileSet set = element.getTileSet();
		Iterator<TiledMapTile> it = set.iterator();
		while(it.hasNext()) {
			TiledMapTile tile = it.next();
			System.out.println(tile.getId());
		}
		*/
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTileSetNotNull () {
		assertNotNull("müsste über tileset verfügen", element.getTileSet());
	}

	@Test
	public void testTextureRegion() {
		element.setTileId(VALID_TILE_ID);
		assertNotNull("sollte eine TextureRegion haben", element.getTextureRegion());
	}
	
	@Test
	public void testSetId() {
		try {
			element.setTileId(INVALID_TILE_ID);
			fail("expected nullpointer due to invalid id");
		} catch(NullPointerException e) {
			//expected
		}
	}

}
