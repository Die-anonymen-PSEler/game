package com.retroMachines.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.data.models.SettingsChangeListener;

/**
 * MusicManger class to allow make sure it is played throughout the entire game.
 * 
 * @author RetroFactory
 */
public final class MusicManager implements SettingsChangeListener {

	/**
	 * the music that will be played
	 */
	private static Music music;

	/**
	 * the volume the music has. it is bound to the music set within the
	 * settingscontroller
	 */
	private float volume;

	/**
	 * instance of the singleton
	 */
	private static MusicManager Singleton;

	private MusicManager() {

	}

	/**
	 * Returns the only copy of the MusicManager
	 * 
	 * @return singleton of the musicmanager
	 */
	public synchronized static MusicManager getInstance() {
		if (Singleton == null) {
			Singleton = new MusicManager();
			music = RetroAssetManager.getMusic();
		}
		return Singleton;
	}

	/**
	 * enables the music for the game. in case it was paused before the music
	 * will continue for that point onwards.
	 */
	public void startMusic() {
		float volume = 0.5f;
		volume = ((RetroMachines) Gdx.app.getApplicationListener())
				.getSettingController().getVolume();
		if (!music.isPlaying()) {

			music.play();
			music.setLooping(true);
			music.setVolume(volume);
		}
	}

	/**
	 * pauses the music. may be continued with the startMusic method
	 */
	public void pauseMusic() {
		music.pause();
	}

	@Override
	public void onSettingsChanged() {
		volume = ((RetroMachines) Gdx.app.getApplicationListener())
				.getSettingController().getVolume();
		if (music != null) {
			music.setVolume(volume);
		}
	}
}
