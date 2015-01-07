package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.ui.screens.AbstractScreen;

public class GameScreen extends AbstractScreen {
	
	private TiledMap map;
	
	private OrthogonalTiledMapRenderer renderer;
	
	private OrthographicCamera camera;
	
	private final GameController gameController;
	
	
	public GameScreen(RetroMachines game, GameController gameController) {
		super(game);
		this.gameController = gameController;
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		camera = new OrthographicCamera();
	}
	
	public void setMap(TiledMap map) {
		this.map = map;
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		inputDetection();
		
		camera.position.x = gameController.getRetroMan().getPos().x;
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
		gameController.getRetroMan().render(delta);
	}
	
	private void inputDetection() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			gameController.jumpRetroMan();
		}
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		
	}
}
