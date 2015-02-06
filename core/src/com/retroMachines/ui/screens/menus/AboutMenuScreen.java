package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
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
	
	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float HALF = (1f / 2f);
	private final static float TWO_3rd = (2f / 3f);
	private final static float CENTERTITLE = (1f / 4f);
	private final static int COLSPANx2 = 2;
	
	/**
	 * The developers of RetroMachine.
	 */
	public static final String CREDIT = "by RetroMachines aka Luca Becker, Henrike Hardt, Larissa Schmid, Adrian Schulte, Maik Wiesner";
	public static final String INFO = "Der Lambda-Kalkül ist ein Element der Informatik. Es ist eine formale "
			+ "Sprache, die im Allgemeine dazu dient, Funktionen zu definieren bzw. beschreiben.";
	
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
		skin = AssetManager.getMenuSkin();
		
		Table scrollTable = new Table(skin);
		//scrollTable.add(INFO,CREDIT);
		ScrollPane profileScroll = new ScrollPane(scrollTable, skin);
		profileScroll.getStyle().hScrollKnob.setMinWidth((DEFAULTBUTTONSIZE * screenWidth) / DIVIDEWIDTHDEFAULT);
		
		// Make Title
		Label title1 = new Label("Information",skin);
		title1.setWrap(true);
		title1.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title1.setAlignment(Align.center);
		
		// Make Text
		Label infoText = new Label(INFO,skin);
		infoText.setWrap(true);
		infoText.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		infoText.setAlignment(Align.center);
		
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
		table.add(title1).width(screenWidth * HALF).right().padRight((screenWidth * HALF) - (screenWidth * CENTERTITLE) ).expandX().row();
		table.add(infoText).width(screenWidth * TWO_3rd).colspan(COLSPANx2).expandX().expandY().row();
		//table.add(title).width(screenWidth * HALF).right().padRight((screenWidth * HALF) - (screenWidth * CENTERTITLE) ).expandX().row();
		//table.add(aboutText).width(screenWidth * TWO_3rd).colspan(COLSPANx2).expandX().expandY().row();
		table.add(profileScroll).expandY().colspan(COLSPANx2).padTop(screenHeight / DEFAULTPADINGx4).padBottom(screenHeight / DEFAULTPADINGx4).row();
		
	    stage.addActor(table);
	    
	    inputMultiplexer.addProcessor(stage);
	}	
}
