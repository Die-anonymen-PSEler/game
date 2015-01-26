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
		skin = AssetManager.menuSkin;
		table = new Table();
		
		
		// Make Title
		Label title = new Label(CREDIT,skin);
		title.setWrap(true);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "default");
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(title).width(600).row();
		table.add(buttonReturn).row();
	    table.setFillParent(true);
	    stage.addActor(table);

	    Gdx.input.setInputProcessor(stage);
	}
	
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
		}
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
