package com.retroMachines.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.retroMachines.RetroMachines;

/**
 * This class is part of the view of RetroMachines. It handles all the prompts
 * in the game.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public abstract class AbstractScreen implements Screen, InputProcessor {

	protected final static float DEFAULTBUTTONSIZE = 10f;
	protected final static float DEFAULTPADDING = 25f;
	protected final static float DEFAULTPADDING_X_TWO = DEFAULTPADDING * 2;
	protected final static float DEFAULTPADDING_X_FOUR = DEFAULTPADDING * 4;
	protected final static float DEFAULTBUTTONSIZE_X_TWO = DEFAULTBUTTONSIZE * 2;
	protected final static float DEFAULTKNOBSIZE = 15f;
	protected final static float DIVIDEWIDTHDEFAULT = 1920f;
	protected final static float DIVIDEHEIGHTDEFAULT = 1080f;
	protected final static float HALF = (1 / 2f);
	protected final static float FOUR_FIFTH = (4 / 5f);
	protected final static float ONE_FOURTH = (1 / 4f);
	protected final static float ONE_FIFTH = (1 / 5f);
	protected final static float ONE_THIRD = (1 / 3f);
	protected final static float TWO_THIRD = (2 / 3f);
	protected final static float ONE_NINTH = (1 / 9f);
	protected final static int COLSPAN_X_TWO = 2;

	/**
	 * The gameObject so access to Controllers is granted.
	 */
	protected final RetroMachines game;
	
	/**
	 * The stage containing all the actors that belong within the screen.
	 */
	protected Stage stage;

	/**
	 * The skin of all Screens.
	 */
	protected Skin skin;

	/**
	 * Input multiplexer for multiple input processors.
	 */
	protected InputMultiplexer inputMultiplexer;

	/**
	 * Width of the screen.
	 */
	protected float screenWidth;
	
	/**
	 * Height of the screen.
	 */
	protected float screenHeight;

	/**
	 * The screen class.
	 * 
	 * @param game
	 *            The main game class.
	 */
	protected AbstractScreen(RetroMachines game) {
		stage = new Stage();
		this.game = game;
		inputMultiplexer = new InputMultiplexer();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
	}

	/**
	 * Renders the Stage to the Screen.
	 * @param delta time
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	/**
	 * Method to resize the screen.
	 * 
	 * @param width
	 * 			The new width.
	 * @param height
	 * 			The new height.
	 */
	@Override
	public void resize(int width, int height) {

	}

	/**
	 * The method to show the screen.
	 */
	@Override
	public void show() {

	}

	/**
	 * The method to hide the screen.
	 */
	@Override
	public void hide() {
	}

	/**
	 * The method to pause the screen.
	 */
	@Override
	public void pause() {
	}

	/**
	 * The method to resume the screen.
	 */
	@Override
	public void resume() {
	}

	/**
	 * The method to dispose the screen.
	 */
	@Override
	public void dispose() {

	}

	/**
	 * The method that controls if the key is pressed.
	 */
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

}
