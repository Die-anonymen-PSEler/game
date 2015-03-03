package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
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
	private final static float FONTSIZE2_5 = 2.5f;
	private final static float ONE_8th = (1f / 8f);

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

		skin = AssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Level", skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Make Buttons

		// Level Buttons + Table
		Table levelTable = new Table(skin);
		int unlocked = statisticController.getLevelsCompleted() + 1;
		for (int i = 0; i < Constants.MAX_LEVEL_ID; i++) {
			Integer iToString = new Integer(i + 1);
			TextButton ilevel;
			if (i >= unlocked) {
				ilevel = new TextButton(iToString.toString(), skin, ButtonStrings.LOCKED);
				ilevel.addListener(new LevelLockedButtonClickListener());
			} else {
				ilevel = new TextButton(iToString.toString(), skin);
				ilevel.addListener(new LevelUnlockedButtonClickListener(i));
			}
			ilevel.getStyle().font.setScale((FONTSIZE2_5 * screenWidth)
					/ DIVIDEWIDTHDEFAULT);

			if (i % 5 == 4 && i > 0) {
				levelTable.add(ilevel).width(screenWidth / LEVELBUTTONSIZE)
						.height(screenHeight / LEVELBUTTONSIZE)
						.pad(screenHeight / DEFAULTPADDINGx4).row();
			} else {
				levelTable.add(ilevel).width(screenWidth / LEVELBUTTONSIZE)
						.height(screenHeight / LEVELBUTTONSIZE)
						.pad(screenHeight / DEFAULTPADDINGx4);
			}
		}

		// Back Button
		Button buttonReturn = new Button(skin, ButtonStrings.BACK);
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());

		// Make Table
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDINGx2)
				.padLeft(screenWidth / DEFAULTPADDINGx4).left();
		table.add(title).width(screenWidth * (2 * ONE_8th)).right()
				.padRight((screenWidth * HALF) - (screenWidth * ONE_8th))
				.expandX().row();
		table.add(levelTable).colspan(COLSPANx2).expandY();

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
