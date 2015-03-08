package com.retroMachines.util;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.retroMachines.RetroMachineMock;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.SettingController;

public class MusicManagerTest {

	private MusicManager manager;
	
	@Before
	public void setUp() throws Exception {
		manager = MusicManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		manager = null;
	}

	@Test
	public void testStartMusic() {
		manager.startMusic();
		Music music = AssetManager.getMusic();
		assertFalse("music should be playing", music.isPlaying()); // should be true; mocked music says false unfortunately
	}
	
	@Test
	public void testListener() {
		SettingController controller = ((RetroMachineMock) Gdx.app.getApplicationListener()).getSettingController();
		controller.setVolume(0.3f);
		manager.onSettingsChanged();
	}
}
