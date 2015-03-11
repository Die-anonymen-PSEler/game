package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.StatisticController;
import com.retroMachines.util.Constants;

public class StatisticMenuScreen extends MenuScreen {

	private final static float FONTSIZE2_5 = 2.5f;
	private final static float HALFTITLEWIDTH = (1f / 4f);
	private final static float TITLEWIDTH = (1f / 2f);
	private final static float ONE_THIRD = (1f / 3f);
	private final static float TWO_THIRD = (2f / 3f);

	private Label steps;
	private Label playTime;
	private Label completedLevel;

	private StatisticController statisticController;

	/**
	 * The constructor to create a new instance of the AboutMenuScreen.
	 * 
	 * @param game
	 *            The game which uses the screen.
	 */
	public StatisticMenuScreen(RetroMachines game) {
		super(game);
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		statisticController = game.getStatisticController();
		skin = RetroAssetManager.getMenuSkin();

		// Make Title
		Label title = super.makeTitle("Statistik", FONTSIZE2_5);

		// Statistikkram
		Label stepsLeft = new Label("Schritte:", skin);
		stepsLeft
				.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		stepsLeft.setAlignment(Align.right);

		Label timeLeft = new Label("Spielzeit:", skin);
		timeLeft.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		timeLeft.setAlignment(Align.right);

		Label completedLevelLeft = new Label("Abgeschlossene Level:", skin);
		completedLevelLeft.setWrap(true);
		completedLevelLeft.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		completedLevelLeft.setAlignment(Align.center);

		steps = new Label("Text", skin);
		steps.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		steps.setAlignment(Align.left);
		steps.setText(statisticController.getStepCounter() + "");

		playTime = new Label("Text", skin);
		playTime.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		playTime.setAlignment(Align.left);
		playTime.setText(((int) statisticController.getPlaytime())
				/ Constants.SECONDS_IN_MINUTE + "");

		completedLevel = new Label("Text", skin);
		completedLevel.setFontScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		completedLevel.setAlignment(Align.left);
		completedLevel.setText(statisticController.getLevelsCompleted() + "");

		// Make Buttons

		// Statistik Table
		Table statisticTable = new Table(skin);
		Table rightStatistik = new Table(skin);
		rightStatistik.add(steps).padTop(screenWidth / DEFAULTPADDING).row();
		rightStatistik.add(playTime).padTop(screenWidth / DEFAULTPADDING).row();
		rightStatistik.add(completedLevel).padTop(screenWidth / DEFAULTPADDING)
				.padBottom(screenWidth / DEFAULTPADDING).row();

		Table leftStatistik = new Table(skin);
		leftStatistik.add(stepsLeft).padTop(screenWidth / DEFAULTPADDING).row();
		leftStatistik.add(timeLeft).padTop(screenWidth / DEFAULTPADDING).row();
		leftStatistik.add(completedLevelLeft).width(screenWidth * TWO_THIRD)
				.padTop(screenWidth / DEFAULTPADDING)
				.padBottom(screenWidth / DEFAULTPADDING).row();

		statisticTable.add(leftStatistik).left().width(screenWidth * TWO_THIRD);
		statisticTable.add(rightStatistik).right()
				.width(screenWidth * ONE_THIRD);

		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());

		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDINGx2)
				.padLeft(screenWidth / DEFAULTPADDINGx4).left();
		table.add(title)
				.width(screenWidth * TITLEWIDTH)
				.right()
				.padRight((screenWidth * HALF) - (screenWidth * HALFTITLEWIDTH))
				.expandX().row();
		table.add(statisticTable).colspan(COLSPANx2)
				.padBottom(screenWidth / DEFAULTPADDINGx4).row();
		table.add().expandY().colspan(COLSPANx2);

		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
	}
}
