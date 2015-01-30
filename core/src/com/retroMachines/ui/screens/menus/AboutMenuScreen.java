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
	
	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float HALFOFSCREEN = 2f;
	private final static float TWOTHIRDOFSCREEN = (2f / 3f);
	private final static float CENTERTITLE = 4f;
	private final static int COLSPANx2 = 2;
	
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
		title.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
		
		// Make Text
		Label aboutText = new Label(CREDIT,skin);
		aboutText.setWrap(true);
		aboutText.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		aboutText.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADINGx2).padLeft(screenWidth/ DEFAULTPADINGx4).left();
		table.add(title).width(screenWidth / HALFOFSCREEN).right().padRight((screenWidth / HALFOFSCREEN) - (screenWidth / CENTERTITLE) ).expandX().row();
		table.add(aboutText).width(screenWidth * TWOTHIRDOFSCREEN).colspan(COLSPANx2).expandX().expandY().row();
		
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
