package com.retroMachines.ui.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;

/**
 * This class is part of the view of RetroMachines.
 * It handles all the prompts in the game. 
 * @author RetroFactory
 *
 */
public abstract class AbstractScreen implements Screen {
	
	/**
	 * The stage containing all the actors that belong within the screen.
	 */
    protected Stage stage = new Stage();
    
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
    
    /**
     * The screen class.
     * @param game the main game class
     */
    public AbstractScreen(RetroMachines game) {
    	this.game = game;
    	inputMultiplexer = new InputMultiplexer();
	}

    
    /**
     * Renders the Stage to the Screen.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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
        stage.dispose();
    }

}
