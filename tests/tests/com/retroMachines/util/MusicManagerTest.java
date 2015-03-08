package com.retroMachines.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.audio.Music;
import com.retroMachines.data.AssetManager;

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
	public void test() {
		manager.startMusic();
		Music music = AssetManager.getMusic();
		assertFalse("music should be playing", music.isPlaying()); // should be false because the audio is mocked
	}

}
