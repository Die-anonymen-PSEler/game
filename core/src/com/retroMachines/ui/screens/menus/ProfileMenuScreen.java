package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.OnProfileChangedListener;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.ui.RetroDialog;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The ProfileMenuScreen is part of the view of RetroMachines. It shows all
 * created profiles to the user and offers to create more or delete a given
 * profile.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class ProfileMenuScreen extends MenuScreen implements
		OnProfileChangedListener {

	private final static float FONTSIZE_THREE = 3f;
	private final static float FONTSIZE_TWO_FIVE = 2.5f;
	
	private ProfileController profileController;

	private DeleteDialog deleteDialog;

	private List<String> profileList;

	/**
	 * The constructor to create a new instance of the ProfileMenuScreen.
	 * 
	 * @param game
	 *            The game which uses this screen.
	 */
	public ProfileMenuScreen(RetroMachines game) {
		super(game);
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		profileController = game.getProfileController();
		profileController.addProfileChangedListener(this);
		skin = RetroAssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Profile", skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE_THREE * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Make Profile List
		profileList = new List<String>(skin);
		profileList.setItems(profileController.getAllProfiles());
		profileList.setSelected(profileController.getProfileName());

		profileList.getStyle().font.setScale((FONTSIZE_TWO_FIVE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		Table scrollTable = new Table(skin);
		scrollTable.add(profileList);
		ScrollPane profileScroll = new ScrollPane(scrollTable, skin);
		profileScroll.setScrollingDisabled(true, false);
		profileScroll.getStyle().hScrollKnob
				.setMinWidth((DEFAULTKNOBSIZE * screenWidth)
						/ DIVIDEWIDTHDEFAULT);

		// Make Buttons
		Button buttonReturn = new Button(skin, ButtonStrings.BACK);
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ProfileReturnButtonClickListener());
		Button buttonSelectProfile = new Button(skin,
				ButtonStrings.CHANGE_PROFILE);
		buttonSelectProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonSelectProfile.addListener(new SelectProfileButtonClickListener());
		Button buttonAddProfile = new Button(skin, ButtonStrings.ADD_PROFILE);
		buttonAddProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonAddProfile.addListener(new AddProfileButtonClickListener());
		Button buttonDeleteProfile = new Button(skin,
				ButtonStrings.DELETE_PROFILE);
		buttonDeleteProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonDeleteProfile.addListener(new DeleteProfileButtonClickListener());

		// Button Table
		Table buttons = new Table(skin);
		buttons.add(buttonDeleteProfile).center()
				.padLeft(screenWidth * ONE_NINTH)
				.padRight(screenWidth * ONE_NINTH)
				.width(screenWidth * ONE_NINTH);
		buttons.add(buttonAddProfile).center().padLeft(screenWidth * ONE_NINTH)
				.padRight(screenWidth * ONE_NINTH)
				.width(screenWidth * ONE_NINTH);
		buttons.add(buttonSelectProfile).center()
				.padLeft(screenWidth * ONE_NINTH)
				.padRight(screenWidth * ONE_NINTH)
				.width(screenWidth * ONE_NINTH);

		// Make Table
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR).left();
		table.add(title).width(screenWidth * (2 * ONE_FIFTH)).right()
				.padRight((screenWidth * HALF) - (screenWidth * ONE_FIFTH))
				.expandX().row();
		table.add(profileScroll).expandY().colspan(COLSPAN_X_TWO)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		table.add(buttons).colspan(COLSPAN_X_TWO).row();

		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);

	}

	/**
	 * Method for changing the profile.
	 */
	@Override
	public void profileChanged() {
		profileList.clearItems();
		profileList.setItems(profileController.getAllProfiles());

	}

	/*
	 * ClickListener
	 */

	private class ProfileReturnButtonClickListener extends
			MenuScreen.ReturnButtonClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	/**
	 * Listener when the button for creating a profile has been clicked.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class AddProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (profileController.canUserBeCreated("thiswonteverexistinthislive")) {
				game.setScreen(new CreateProfileMenuScreen(game));
			} else {
				RetroDialog dialog = new RetroDialog("", "Du kannst maximal 5 Profile haben");
				dialog.show(stage);
			}
		}
	}

	/**
	 * Listener when the button for creating a profile has been clicked.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class DeleteProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (deleteDialog != null) {
				deleteDialog.show(stage);
			} else {
				deleteDialog = new DeleteDialog("", "Profil wirklich löschen?");
				deleteDialog.show(stage);
			}
		}
	}

	/**
	 * Listener when the button for selecting a profile has been clicked.
	 * Afterwards the main menu is shown.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class SelectProfileButtonClickListener extends ClickListener {

		public SelectProfileButtonClickListener() {
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (profileList.getSelected() != null) {
				game.getProfileController().changeActiveProfile(
						profileList.getSelected());
				game.setScreen(new MainMenuScreen(game));
			}

		}
	}

	private class DeleteDialog extends RetroDialog {
		private RetroDialog deletionError;

		public DeleteDialog(String title, String msg) {
			super(title, msg);
			button(new Button(skin, ButtonStrings.ABORT), false);
		}

		@Override
		protected void result(Object object) {
			if ((Boolean) object) {
				if (profileController.getAllProfiles().length > 1) {
					profileController.deleteProfile(profileList.getSelected());
				}
				else {
					deletionError = new RetroDialog("",
							"Zum Spielen brauchst du ein Profil!");
					deletionError.show(stage);
					this.remove();
				}
			}
		}

	}
}
