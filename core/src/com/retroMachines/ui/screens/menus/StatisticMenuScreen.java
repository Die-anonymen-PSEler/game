package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.StatisticController;

public class StatisticMenuScreen  extends MenuScreen {

	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADING = 25f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float HALF = (1f / 2f);
	private final static float HALFTITEWIDTH = (1f / 4f);
	private final static float TITLEWIDTH = (1f / 2f);
	private final static float ONE_3rd = (1f / 3f);
	private final static float TWO_3rd = (2f / 3f);
	private final static int COLSPANx2 = 2;
	
	private Label steps;
	private Label playTime;
	private Label completedLevel;
	
	private StatisticController statisticController;
	
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
		statisticController = game.getStatisticController();
		skin = AssetManager.getMenuSkin();
		
		// Make Title
		Label title = new Label("Statitik",skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
		
		//Statistikkram
		Label stepsLeft = new Label("Schritte:", skin);
		stepsLeft.setFontScale((FONTSIZE2_5 * screenWidth) /  DIVIDEWIDTHDEFAULT);
		stepsLeft.setAlignment(Align.right);
		
		Label timeLeft = new Label("Spielzeit:", skin);
		timeLeft.setFontScale((FONTSIZE2_5 * screenWidth) /  DIVIDEWIDTHDEFAULT);
		timeLeft.setAlignment(Align.right);
		
		Label completedLevelLeft = new Label("Abgeschlossene Level:", skin);
		completedLevelLeft.setWrap(true);
		completedLevelLeft.setFontScale((FONTSIZE2_5 * screenWidth) /  DIVIDEWIDTHDEFAULT);
		completedLevelLeft.setAlignment(Align.center);
		
		steps = new Label("Text", skin);
		steps.setFontScale((FONTSIZE2_5 * screenWidth) /  DIVIDEWIDTHDEFAULT);
		steps.setAlignment(Align.left);
		steps.setText(statisticController.getStepCounter() + "");
		
		playTime = new Label("Text", skin);
		playTime.setFontScale((FONTSIZE2_5 * screenWidth) /  DIVIDEWIDTHDEFAULT);
		playTime.setAlignment(Align.left);
		playTime.setText(statisticController.getPlaytime() + "");
		
		completedLevel = new Label("Text", skin);
		completedLevel.setFontScale((FONTSIZE2_5 * screenWidth) /  DIVIDEWIDTHDEFAULT);
		completedLevel.setAlignment(Align.left);
		completedLevel.setText(statisticController.getLevelsCompleted() + "");
		
		// Make Buttons
		
		// Statistik Table
		Table statisticTable = new Table(skin);
		Table rightStatistik = new Table(skin);
		rightStatistik.add(steps).padTop(screenWidth / DEFAULTPADING).row();
		rightStatistik.add(playTime).padTop(screenWidth / DEFAULTPADING).row();
		rightStatistik.add(completedLevel).padTop(screenWidth / DEFAULTPADING).padBottom(screenWidth / DEFAULTPADING).row();
		
		Table leftStatistik = new Table(skin);
		leftStatistik.add(stepsLeft).padTop(screenWidth / DEFAULTPADING).row();
		leftStatistik.add(timeLeft).padTop(screenWidth / DEFAULTPADING).row();
		leftStatistik.add(completedLevelLeft).width(screenWidth * TWO_3rd).padTop(screenWidth / DEFAULTPADING).padBottom(screenWidth / DEFAULTPADING).row();
		
		statisticTable.add(leftStatistik).left().width(screenWidth * TWO_3rd);
		statisticTable.add(rightStatistik).right().width(screenWidth * ONE_3rd);
		
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADINGx2).padLeft(screenWidth / DEFAULTPADINGx4).left();
		table.add(title).width(screenWidth * TITLEWIDTH).right().padRight((screenWidth * HALF) - (screenWidth * HALFTITEWIDTH) ).expandX().row();
		table.add(statisticTable).colspan(COLSPANx2).padBottom(screenWidth / DEFAULTPADINGx4).row();
		table.add().expandY().colspan(COLSPANx2);
		
	    stage.addActor(table);
	    
	    inputMultiplexer.addProcessor(stage);
	}
}
