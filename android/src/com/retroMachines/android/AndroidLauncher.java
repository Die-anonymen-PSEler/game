package com.retroMachines.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.retroMachines.RetroMachines;

public class AndroidLauncher extends AndroidApplication {
	
	private RetroMachines game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new RetroMachines();
		initialize(game, config);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		game.androidResume();
	}
}
