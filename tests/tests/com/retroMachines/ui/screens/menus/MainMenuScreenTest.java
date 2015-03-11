package com.retroMachines.ui.screens.menus;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.gdxemulation.RetroMachineMock;
import com.retroMachines.util.MusicManager;

@RunWith(GdxTestRunner.class)
public class MainMenuScreenTest {
	
	private RetroMachineMock game;
	
	private MainMenuScreen screen;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachineMock();
		screen = new MainMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
		game = null;
		screen = null;
	}

	@Test
	public void testMusicStarted() {
		screen.show();
		MusicManager.getInstance();
	}

}
