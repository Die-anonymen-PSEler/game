package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.ui.screens.AbstractScreen;

public class GameScreen extends AbstractScreen {
	
	private TiledMap map;
	
	private OrthogonalTiledMapRenderer renderer;
	
	private final GameController gameController;
	
	
	public GameScreen(RetroMachines game, GameController gameController) {
		super(game);
		this.gameController = gameController;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		
	}
}
