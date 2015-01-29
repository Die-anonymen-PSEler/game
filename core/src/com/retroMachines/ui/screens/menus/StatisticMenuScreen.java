package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;

public class StatisticMenuScreen  extends MenuScreen {


	/**
	 * The constructor to create a new instance of the AboutMenuScreen.
	 * @param game The game which uses the screen.
	 */
	public StatisticMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.menuSkin;
		table.debug();
		
		// Make Title
		Label title = new Label("Statitik",skin);
		title.setWrap(true);
		title.setFontScale((2.5f*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / 10f);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(buttonReturn).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left().row();
		table.add(title).width(screenWidth/1.5f).expandX().expandY().row();
		
	    stage.addActor(table);
	    
	    inputMultiplexer.addProcessor(stage);
	}
	
	/**
	 * Button to return to the MainMenuScreen.
	 * 
	 * @author RetroFactory
	 *
	 */
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
}
