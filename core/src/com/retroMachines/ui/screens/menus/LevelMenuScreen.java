package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.GameController;
import com.retroMachines.game.controllers.StatisticController;
import com.retroMachines.ui.RetroDialog;
import com.retroMachines.util.Constants;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The LevelMenuScreen is part of the view of RetroMachines. It displays the
 * list of available levels to the user and initiates a new level once the user
 * has picked one.
 * 
 * @author RetroFactory
 */
public class LevelMenuScreen extends MenuScreen {

	private final static float LEVELBUTTONSIZE = 6f;
	private final static float FONTSIZE_TWO_FIVE = 2.5f;
	private final static float ONE_EIGHT_TH = (1f / 8f);

	private RetroDialog lockedDialog;

	private StatisticController statisticController;

	private GameController gameController;

	/**
	 * The constructor to create a new instance of the LevelMenuScreen.
	 * 
	 * @param game
	 *            The game which uses this screen.
	 */
	public LevelMenuScreen(RetroMachines game) {
		super(game);
	}

	/**
	 * Initializes the LevelMenuScreen.
	 */
	@Override
	protected void initialize() {
		statisticController = game.getStatisticController();
		gameController = game.getGameController();

		skin = RetroAssetManager.getMenuSkin();

		// Make Title
		Label title = super.makeTitle("Level", FONTSIZE_TWO_FIVE);

		// Make Buttons

		// Level Buttons + Table
		Table levelTable = new Table(skin);
		int unlocked = statisticController.getLevelsCompleted() + 1;
		for (int i = 0; i < Constants.MAX_LEVEL_ID; i++) {
			Integer iToString = i + 1;
			TextButton ilevel;
			if (i >= unlocked) {
				ilevel = new TextButton(iToString.toString(), skin,
						ButtonStrings.LOCKED);
				ilevel.addListener(new LevelLockedButtonClickListener());
			} else {
				ilevel = new TextButton(iToString.toString(), skin);
				ilevel.addListener(new LevelUnlockedButtonClickListener(i));
			}
			ilevel.getStyle().font.setScale((FONTSIZE_TWO_FIVE * screenWidth)
					/ DIVIDEWIDTHDEFAULT);

			if (i % 5 == 4 && i > 0) {
				levelTable.add(ilevel).width(screenWidth / LEVELBUTTONSIZE)
						.height(screenHeight / LEVELBUTTONSIZE)
						.pad(screenHeight / DEFAULTPADDING_X_FOUR).row();
			} else {
				levelTable.add(ilevel).width(screenWidth / LEVELBUTTONSIZE)
						.height(screenHeight / LEVELBUTTONSIZE)
						.pad(screenHeight / DEFAULTPADDING_X_FOUR);
			}
		}

		// Back Button
		Button buttonReturn = new Button(skin, ButtonStrings.BACK);
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());

		// Make Table
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR).left();
		table.add(title).width(screenWidth * (2 * ONE_EIGHT_TH)).right()
				.padRight((screenWidth * HALF) - (screenWidth * ONE_EIGHT_TH))
				.expandX().row();
		table.add(levelTable).colspan(COLSPAN_X_TWO).expandY();

		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);

	}

	/**
	 * Used when level is locked.
	 * 
	 * @author RetroFactory
	 * 
	 */
	private class LevelLockedButtonClickListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (lockedDialog != null) {
				lockedDialog.show(stage);
			} else {
				lockedDialog = new RetroDialog("", "Nicht Freigeschaltet");
				lockedDialog.show(stage);
			}
		}

	}

	/**
	 * Used when Level is unlocked and can be played.
	 * 
	 * @author RetroFactory
	 * 
	 */
	private class LevelUnlockedButtonClickListener extends ClickListener {

		/**
		 * the ID of the level so it can be started later on
		 */
		private int id;

		public LevelUnlockedButtonClickListener(int id) {
			this.id = id;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			gameController.startLevel(id);
		}

	}

	protected class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

}
