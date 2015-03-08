package com.retroMachines.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retroMachines.util.Constants;

public class AssetManagerTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitializePreLoading() {
		AssetManager.initializePreLoading();
		assertTrue("asset wurde nicht geladen", AssetManager.getTexture(Constants.BACKGROUND_PATH) != null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonExistingTexture() {
		AssetManager.getTexture("this texture won't ever exist in this project");
	}
	
	@Test
	public void testReloadMap() {
		TiledMap map1 = AssetManager.getMap(1);
		AssetManager.reloadMap(1);
		assertFalse("maps sind identisch", map1 == AssetManager.getMap(1));
	}
	
	@Test
	public void testLoaded() {
		AssetManager.initializeWhileLoading();
		assertFalse("skin ist null", AssetManager.getMenuSkin() == null);
		assertFalse("skin ist null", AssetManager.getGameelementSkin() == null);
		assertFalse("tileset ist null", AssetManager.getDepots() == null);
		assertFalse("music ist null", AssetManager.getMusic() == null);
	}

}
