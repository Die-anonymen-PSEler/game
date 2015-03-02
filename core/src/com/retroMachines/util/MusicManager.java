package com.retroMachines.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.models.SettingsChangeListener;

public final class MusicManager implements SettingsChangeListener {
	
	private Music music;
	
	private float volume;
	
	private static MusicManager singleton;
	
	private MusicManager() {
		
	}
	
	public static MusicManager getInstance() {
		if (singleton == null) {
			singleton = new MusicManager();
		}
		return singleton;
	}
	
	public void startMusic() {
		if (music == null) {
			music = AssetManager.getMusic();
		}
		if (!music.isPlaying()) {
			float volume = ((RetroMachines) Gdx.app.getApplicationListener()).getSettingController().getVolume();
			music.play();
			music.setLooping(true);
			music.setVolume(volume);
		}
	}
	
	public void stopMusic() {
		if (music == null) {
			music = AssetManager.getMusic();
		}
		music.pause();
	}

	@Override
	public void onSettingsChanged() {
		volume = ((RetroMachines) Gdx.app.getApplicationListener()).getSettingController().getVolume();
		music.setVolume(volume);
	}
}
