package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;


/**
 * The LoadMenuScreen is part of the view of RetroMachines.
 * It shows the loading screen which appears after starting the application.
 * @author RetroFactory
 */
public class LoadMenuScreen extends MenuScreen implements AssetManager.OnProgressChanged{
	
	private RetroMachines game;
	
    /**
     * The constructor to create a new instance of the LoadMenuScreen.
     * @param game The game which is loaded while the screen is displayed.
     */
    public LoadMenuScreen(RetroMachines game) {
		super(game);
		AssetManager.addProgressListener(this);
	}
    
    /**
     * Displays this screen.
     */
	@Override
    public void show() {
    }
    
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}

	@Override
	public void progressChanged(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

}