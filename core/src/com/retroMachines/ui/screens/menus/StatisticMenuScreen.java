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

	private final static float FONTSIZE_TWO_FIVE = 2.5f;
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
		Label title = super.makeTitle("Statistik", FONTSIZE_TWO_FIVE);

		// Statistikkram
		Label stepsLeft = new Label("Schritte:", skin);
		stepsLeft
				.setFontScale((FONTSIZE_TWO_FIVE * screenWidth) / DIVIDEWIDTHDEFAULT);
		stepsLeft.setAlignment(Align.right);

		Label timeLeft = new Label("Spielzeit:", skin);
		timeLeft.setFontScale((FONTSIZE_TWO_FIVE * screenWidth) / DIVIDEWIDTHDEFAULT);
		timeLeft.setAlignment(Align.right);

		Label completedLevelLeft = new Label("Abgeschlossene Level:", skin);
		completedLevelLeft.setWrap(true);
		completedLevelLeft.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		completedLevelLeft.setAlignment(Align.center);

		steps = new Label("Text", skin);
		steps.setFontScale((FONTSIZE_TWO_FIVE * screenWidth) / DIVIDEWIDTHDEFAULT);
		steps.setAlignment(Align.left);
		steps.setText(statisticController.getStepCounter() + "");

		playTime = new Label("Text", skin);
		playTime.setFontScale((FONTSIZE_TWO_FIVE * screenWidth) / DIVIDEWIDTHDEFAULT);
		playTime.setAlignment(Align.left);
		playTime.setText(((int) statisticController.getPlaytime())
				/ Constants.SECONDS_IN_MINUTE + "");

		completedLevel = new Label("Text", skin);
		completedLevel.setFontScale((FONTSIZE_TWO_FIVE * screenWidth)
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

		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR).left();
		table.add(title)
				.width(screenWidth * TITLEWIDTH)
				.right()
				.padRight((screenWidth * HALF) - (screenWidth * HALFTITLEWIDTH))
				.expandX().row();
		table.add(statisticTable).colspan(COLSPAN_X_TWO)
				.padBottom(screenWidth / DEFAULTPADDING_X_FOUR).row();
		table.add().expandY().colspan(COLSPAN_X_TWO);

		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
	}
}
