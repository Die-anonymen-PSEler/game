package com.retroMachines.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.retroMachines.RetroMachines;

/**
 * This class is part of the view of RetroMachines.
 * It handles all the prompts in the game. 
 * @author RetroFactory
 *
 */
public abstract class AbstractScreen implements Screen {
	
	protected final static float DEFAULTBUTTONSIZE = 10f;
	protected final static float DEFAULTPADDING = 25f;
	protected final static float DEFAULTPADDINGx2 = DEFAULTPADDING * 2;
	protected final static float DEFAULTPADDINGx4 = DEFAULTPADDING * 4;
	protected final static float DEFAULTBUTTONSIZEx2 = DEFAULTBUTTONSIZE * 2;
	protected final static float DEFAULTKNOBSIZE = 15f;
	protected final static float DIVIDEWIDTHDEFAULT = 1920f;
	protected final static float DIVIDEHEIGHTDEFAULT = 1080f;
	protected final static float HALF = (1 / 2f);
	protected final static float FOUR_FIFTH = (4 / 5f);
	protected final static float ONE_FOURTH = (1 / 4f);
	protected final static float ONE_FIFTH = (1 / 5f);
	protected final static float TWO_THIRD = (2 / 3f);
	protected final static float ONE_NINTH = (1 / 9f);
	protected final static int COLSPANx2 = 2;
	
	/**
	 * The stage containing all the actors that belong within the screen.
	 */
    protected Stage stage;
    
    /**
     * The gameObject so access to Controllers is granted.
     */
    protected final RetroMachines game;
    
    
    /**
     * The skin of all Screens
     */
	protected Skin skin;
	
	/**
	 * input multiplexer for multiple input processors
	 */
	protected InputMultiplexer inputMultiplexer;
    
	protected float screenWidth;
	protected float screenHeight;
	
    /**
     * The screen class.
     * @param game the main game class
     */
    public AbstractScreen(RetroMachines game) {
    	stage = new Stage();
    	this.game = game;
    	inputMultiplexer = new InputMultiplexer();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
	}

    
    /**
     * Renders the Stage to the Screen.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        
    }

}
