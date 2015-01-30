package com.retroMachines.ui.screens.menus;

import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.AbstractScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.retroMachines.data.AssetManager;


/**
 * The LoadMenuScreen is part of the view of RetroMachines.
 * It shows the loading screen which appears after starting the application.
 * @author RetroFactory
 */
public class LoadMenuScreen extends AbstractScreen{
	
	private RetroMachines game;
	
    /**
     * The constructor to create a new instance of the LoadMenuScreen.
     * @param game The game which is loaded while the screen is displayed.
     */
    public LoadMenuScreen(RetroMachines game) {
		super(game);
		this.game = game;
		// TODO Auto-generated constructor stub
	}
    
    /**
     * Displays this screen.
     */
	@Override
    public void show() {
        //Assets.manager.clear(); 
        //not necessary, only when splash called more then once
        AssetManager.queueLoading(); 
        AssetManager.loadMusic();
    }
    
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        
        if (game.getLoading()) {
            if(AssetManager.manager.update()){ // check if all files are loaded
                AssetManager.setMenuSkin(); // uses files to create menuSkin
                AssetManager.setMusic();
                game.setScreen(new MainMenuScreen(game));
            }
        }

	}

}