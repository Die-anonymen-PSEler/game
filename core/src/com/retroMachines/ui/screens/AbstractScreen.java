package com.retroMachines.ui.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
 * Abstrakte Men� Klasse auf der alle Men�s aufbauen
 * @author Adrian
 *
 */
public abstract class AbstractScreen implements Screen {

    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = AssetManager.menuSkin;


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setCamera(new AndroidCamera(RetroMachines.WIDTH, RetroMachines.HEIGHT));
        table.invalidateHierarchy();
        table.setSize(RetroMachines.WIDTH, RetroMachines.HEIGHT);   
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
    
    protected class PlayButtonClickListener extends ClickListener {
    	@Override
		public void clicked(InputEvent event, float x, float y) {
			// what shall happen?
		}
    }

}
