package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.ui.screens.AbstractScreen;
import com.retroMachines.util.Constants;

/**
 * The MenuScreen is part of the view of RetroMachines. Abstract MenuScreen
 * class that represents the basic style of a menu separated within two parts.
 * On main part (the left side) and a small part for buttons and so on.
 * 
 * @author RetroFactory
 * 
 */
public abstract class MenuScreen extends AbstractScreen {

	/**
	 * The main table on the left side of a menu screen.
	 */
	protected Table table;

	/**
	 * Creates a new MenuScreen that can be displayed to the user afterwards.
	 * 
	 * @param game
	 *            The game that uses this Screen.
	 */
	public MenuScreen(RetroMachines game) {
		super(game);
		table = new Table(RetroAssetManager.getMenuSkin());
		table.background(new TextureRegionDrawable(new TextureRegion(
				RetroAssetManager.getTexture(Constants.BACKGROUND_PATH))));
		table.setBounds(0, 0, screenWidth, screenHeight);
		stage = new Stage();
		initialize();
		inputMultiplexer.addProcessor(this);
	}
	
	/**
	 * makes the title of the screen
	 * @param name name of the title
	 * @param fontsize size of the font
	 * @return the title made
	 */
	public Label makeTitle(String name, float fontsize) {
		Label title = new Label(name, skin);
		title.setWrap(true);
		title.setFontScale((fontsize * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
		return title;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		super.show();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		super.hide();
	}

	/**
	 * Renders the Stage to the Screen.
	 * @param delta deltatime
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
			return true;
		}
		super.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Call this method to set up the menu screen. Only fills the Table with
	 * Buttons or other UI elements that are needed. The table will be added to
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
			game.previousScreen();
		}
	}
}
