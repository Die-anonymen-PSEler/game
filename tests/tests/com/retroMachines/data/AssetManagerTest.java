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
		RetroAssetManager.initializePreLoading();
		assertTrue("asset wurde nicht geladen", RetroAssetManager.getTexture(Constants.BACKGROUND_PATH) != null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonExistingTexture() {
		RetroAssetManager.getTexture("this texture won't ever exist in this project");
	}
	
	@Test
	public void testReloadMap() {
		TiledMap map1 = RetroAssetManager.getMap(1);
		RetroAssetManager.reloadMap(1);
		assertFalse("maps sind identisch", map1 == RetroAssetManager.getMap(1));
	}
	
	@Test
	public void testLoaded() {
		RetroAssetManager.initializeWhileLoading();
		assertFalse("skin ist null", RetroAssetManager.getMenuSkin() == null);
		assertFalse("skin ist null", RetroAssetManager.getGameelementSkin() == null);
		assertFalse("tileset ist null", RetroAssetManager.getDepots() == null);
		assertFalse("music ist null", RetroAssetManager.getMusic() == null);
		assertFalse("tileset ist null", RetroAssetManager.getLights() == null);
	}

}
