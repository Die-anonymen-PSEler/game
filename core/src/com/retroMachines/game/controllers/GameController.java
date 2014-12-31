package com.retroMachines.game.controllers;

import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.game.GameScreen;

public class GameController {
	
	/**
	 * a reference to the main class for calls
	 */
	private final RetroMachines game;
	
	/**
	 * gameScreen
	 */
	private final GameScreen gameScreen;
	
	public GameController(RetroMachines game) {
		this.game = game;
		gameScreen = new GameScreen(game, this);
	}
	
	public void loadLevel(int levelId) {
		
		game.setScreen(gameScreen);
	}
}
