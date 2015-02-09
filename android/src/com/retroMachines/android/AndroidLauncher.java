package com.retroMachines.android;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.retroMachines.RetroMachines;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		AudioManager mgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int volume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		RetroMachines game = new RetroMachines();
		game.setSystemVolume(volume, maxVolume);
		initialize(game, config);
	}
}
