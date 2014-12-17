package com.retroFactory.retroMachines.menu;

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

/**
 * Abstrakte Menü Klasse auf der alle Menüs aufbauen
 * @author Adrian
 *
 */
public abstract class AbstractScreen implements Screen {

    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = Assets.menuSkin;


        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
        }

        @Override
        public void resize(int width, int height) {
            stage.getViewport().setCamera(new AndroidCamera(CreateApp.WIDTH, CreateApp.HEIGHT));
            table.invalidateHierarchy();
            table.setSize(CreateApp.WIDTH, CreateApp.HEIGHT);   
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
