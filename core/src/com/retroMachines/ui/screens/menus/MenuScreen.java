package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * The MenuScreen is part of the view of RetroMachines.
 * Abstract MenuScreen class that represents the basic
 * style of a menu separated within two parts. On main part
 * (the left side) and a small part for buttons and so on.
 * @author RetroFactory
 *
 */
public abstract class MenuScreen extends AbstractScreen implements InputProcessor {
	
	/**
	 * The main table on the left side of a menu screen.
	 */
	protected Table table;
	
	
	/**
	 * Creates a new MenuScreen that can be displayed to the user afterwards.
	 * @param game The game that uses this Screen.
	 */
	public MenuScreen(RetroMachines game) {
		super(game);
		table = new Table(AssetManager.getMenuSkin());
		table.background(new TextureRegionDrawable(new TextureRegion(AssetManager.getTexture("Background.png"))));
		table.setBounds(0, 0, screenWidth, screenHeight);
		stage = new Stage();
		initialize();
		inputMultiplexer.addProcessor(this);
				
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		super.show();
	}
	
	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
		super.dispose();
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
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
    		game.previousScreen();
    	}
    	return false;
    }
    
    @Override
    public boolean keyTyped(char character) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public boolean scrolled(int amount) {
    	// TODO Auto-generated method stub
    	return false;
    }
	
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    /**
	 * Call this method to set up the menu screen. 
	 * Only fills the Table with Buttons or other UI elements that are needed. The table will be added to 
	 * the stage by the MenuScreen class.
	 */
	protected abstract void initialize();
    
	/**
	 * Button to return to the MainMenuScreen.
	 * 
	 * @author RetroFactory
	 *
	 */
	protected class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.previousScreen();
		}
	}
}
