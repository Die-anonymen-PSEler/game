package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;

public class StatisticMenuScreen  extends MenuScreen {

	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float HALF = (1f / 2f);
	private final static float HALFTITEWIDTH = (1f / 4f);
	private final static float TITLEWIDTH = (1f / 2f);
	private final static int COLSPANx2 = 2;
	
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
		skin = AssetManager.getMenuSkin();
		
		// Make Title
		Label title = new Label("Statitik",skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADINGx2).padLeft(screenWidth/ DEFAULTPADINGx4).left();
		table.add(title).width(screenWidth * TITLEWIDTH).right().padRight((screenWidth * HALF) - (screenWidth * HALFTITEWIDTH) ).expandX().row();
		table.add(" Statistik Kram").expandY().colspan(COLSPANx2);
		
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
