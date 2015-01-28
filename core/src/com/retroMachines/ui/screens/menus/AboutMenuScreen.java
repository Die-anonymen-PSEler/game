package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;

/**
 * The AboutMenuScreen is part of the view of RetroMachines.
 * In it the informations about RetroFactory and the authors
 * of this game are given.
 * @author RetroFactory
 *
 */
public class AboutMenuScreen extends MenuScreen {
	
	/**
	 * The developers of RetroMachine.
	 */
	public static final String CREDIT = "Luca Becker, Henrike Hardt, Larissa Schmid, Adrian Schulte, Maik Wiesner";
	
	private Table table;
	
	/**
	 * The constructor to create a new instance of the AboutMenuScreen.
	 * @param game The game which uses the screen.
	 */
	public AboutMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Shows the AboutMenuScreen.
	 */
	public void show() {
		super.show();
	}
	
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.setScreen(new MainMenuScreen(game));
		}
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.menuSkin;
		table = new Table();
		table.debug();
		
		// Make Title
		Label title = new Label(CREDIT,skin);
		title.setFontScale(2);
		title.setWrap(true);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(100);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(buttonReturn).padTop(50).padLeft(50).left().row();
		table.add(title).width(1000).expandX().expandY().row();
	    table.setFillParent(true);
	    stage.addActor(table);

	    Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
}
