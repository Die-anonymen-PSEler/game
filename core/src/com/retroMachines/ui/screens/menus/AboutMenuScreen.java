package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	
	/**
	 * The constructor to create a new instance of the AboutMenuScreen.
	 * @param game The game which uses the screen.
	 */
	public AboutMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.menuSkin;
		
		// Make Title
		Label title = new Label("Credits",skin);
		title.setWrap(true);
		title.setFontScale((2.5f*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		// Make Text
		Label aboutText = new Label(CREDIT,skin);
		aboutText.setWrap(true);
		aboutText.setFontScale((2.5f*screenWidth)/1920f);
		aboutText.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / 10f);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(buttonReturn).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left();
		table.add(title).width(screenWidth/2f).right().padRight((screenWidth / 2f) - (screenWidth / 4f) ).expandX().row();
		table.add(aboutText).width(screenWidth/1.5f).colspan(2).expandX().expandY().row();
		
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
