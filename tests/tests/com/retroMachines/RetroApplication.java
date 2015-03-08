package com.retroMachines;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class RetroApplication extends HeadlessApplication {
	
	private RetroMachineMock game;
	
	public RetroApplication(ApplicationListener listener, HeadlessApplicationConfiguration conf, RetroMachineMock game) {
		super(listener, conf);
		this.game = game;
	}
	
	@Override
	public ApplicationListener getApplicationListener() {
		return game;
	}
}
