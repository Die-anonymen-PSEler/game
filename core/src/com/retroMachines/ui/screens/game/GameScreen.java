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
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * 
 * @author RetroFactory
 *
 */
public class GameScreen extends AbstractScreen {
	
	/**
	 * the map that is currently active and may be shown to the user in case the gamescreen is also active
	 */
	private TiledMap map;
	
	/**
	 * a render for displaying the map and everything else to the screen.
	 */
	private OrthogonalTiledMapRenderer renderer;
	
	/**
	 * OrthographicCamera so we can look from the side onto our map. 
	 */
	private OrthographicCamera camera;
	
	/**
	 * reference to the gameController for information regarding the user input
	 */
	private final GameController gameController;
	
	/**
	 * 
	 * @param game
	 * @param gameController
	 * @param leftiMode
	 */
	public GameScreen(RetroMachines game, GameController gameController, boolean leftiMode) {
		super(game);
		this.gameController = gameController;
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		camera = new OrthographicCamera();
	}
	
	/**
	 * assigns a new TiledMap to the screen
	 * @param map
	 */
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
	
	/**
	 * performs the input detection and delegates calls to the controller so it can perform the logic
	 */
	private void inputDetection() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			gameController.jumpRetroMan();
		}
	}
	
	/**
	 * abolishes the screen and cleans up behind it.
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		
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
