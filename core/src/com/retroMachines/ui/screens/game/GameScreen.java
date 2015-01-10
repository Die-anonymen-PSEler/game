package com.retroMachines.ui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.ui.screens.AbstractScreen;
import com.badlogic.gdx.math.Vector2;

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
	
	/**
	 * Returns Gameelement at given Pos in TiledMap and deletes it.
	 * @param posObj Position in TiledMap of Gameelement
	 * @return Gamelelemnt at this Pos  ( null when empy)
	 */
	public GameElement getGameElement(Vector2 posObj) {
		return null;
	}
	
	/**
	 * Sets Gameelement at specific Position in tiledMap
	 * @param posObj Position where The Object should Placed
	 * @param element Eleement which should placed
	 * @return false when element placed succesfull
	 */
	public boolean setGameElement(Vector2 posObj, GameElement element) {
		return false;
	}
	
	
	/**
	 * Listener when the user clicks on Button Right
	 * @author Retro Factory
	 */
	private class RightButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * Listener when the user clicks on Button Left
	 * @author Retro Factory
	 */
	private class LeftButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * Listener when the user clicks on Button Jump
	 * @author Retro Factory
	 */
	private class JumpButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * Listener when the user clicks on Button Interact
	 * @author Retro Factory
	 */
	private class InteractButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
	}
}
