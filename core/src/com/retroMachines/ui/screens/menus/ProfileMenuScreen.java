package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.models.Profile;
import com.retroMachines.game.controllers.OnProfileChangedListener;
import com.retroMachines.game.controllers.ProfileController;

/**
 * The ProfileMenuScreen is part of the view of RetroMachines. It shows all
 * created profiles to the user and offers to create more or delete a given
 * profile.
 * 
 * @author RetroFactory
 * 
 */
public class ProfileMenuScreen extends MenuScreen implements
		OnProfileChangedListener {

	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTKNOBSIZE = 15f;
	private final static float DEFAULTPADDING = 25f;
	private final static float DEFAULTPADDINGx2 = 50f;
	private final static float DEFAULTPADDINGx4 = 100f;
	private final static float FONTSIZE3 = 3f;
	private final static float FONTSIZE2_5 = 2.5f;
	private final static float FONTSIZE2_1 = 2.1f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float HALF = (1f / 2f);
	private final static float ONE_FOURTH = (1f / 4f);
	private final static float ONE_FIFTH = (1f / 5f);
	private final static float TWO_THIRD = (2f / 3f);
	private final static float ONE_NINTH = (1f / 9f);
	private final static int COLSPANx2 = 2;

	/**
	 * 
	 */
	private ProfileController profileController;

	/**
	 * 
	 */
	private DeleteDialog deleteDialog;

	/**
	 * 
	 */
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
		// TODO Auto-generated method stub
		skin = AssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Profile", skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE3 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Make Profile List
		profileList = new List<String>(skin);
		profileList.setItems(profileController.getAllProfiles());
		profileList.setSelected(profileController.getProfileName());

		profileList.getStyle().font.setScale((FONTSIZE2_5 * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		Table scrollTable = new Table(skin);
		scrollTable.add(profileList);
		ScrollPane profileScroll = new ScrollPane(scrollTable, skin);
		profileScroll.getStyle().hScrollKnob
				.setMinWidth((DEFAULTKNOBSIZE * screenWidth)
						/ DIVIDEWIDTHDEFAULT);

		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ProfileReturnButtonClickListener());
		Button buttonSelectProfile = new Button(skin, "changeProfile");
		buttonSelectProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonSelectProfile.addListener(new SelectProfileButtonClickListener());
		Button buttonAddProfile = new Button(skin, "addProfile");
		buttonAddProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonAddProfile.addListener(new AddProfileButtonClickListener());
		Button buttonDeleteProfile = new Button(skin, "deleteProfile");
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
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDINGx2)
				.padLeft(screenWidth / DEFAULTPADDINGx4).left();
		table.add(title).width(screenWidth * (2 * ONE_FIFTH)).right()
				.padRight((screenWidth * HALF) - (screenWidth * ONE_FIFTH))
				.expandX().row();
		table.add(profileScroll).expandY().colspan(COLSPANx2)
				.padTop(screenHeight / DEFAULTPADDING)
				.padBottom(screenHeight / DEFAULTPADDING).row();
		table.add(buttons).colspan(COLSPANx2).row();

		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);

	}

	@Override
	public void profileChanged() {
		profileList.clearItems();
		profileList.setItems(profileController.getAllProfiles());

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
			game.previousScreen();
			return true;
		} else {
			return super.keyDown(keycode);
		}
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
	 */
	private class AddProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.setScreen(new CreateProfileMenuScreen(game));
		}
	}

	/**
	 * Listener when the button for creating a profile has been clicked.
	 * 
	 * @author RetroFactory
	 */
	private class DeleteProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (deleteDialog != null) {
				deleteDialog.show(stage);
			} else {
				deleteDialog = new DeleteDialog("", skin, "default");
				deleteDialog.show(stage);
			}
		}
	}

	/**
	 * Listener when the button for selecting a profile has been clicked.
	 * Afterwards the main menu is shown.
	 * 
	 * @author RetroFactory
	 */
	private class SelectProfileButtonClickListener extends ClickListener {

		public SelectProfileButtonClickListener() {
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Is it Right ?
			game.getProfileController().changeActiveProfile(
					profileList.getSelected());
			game.setScreen(new MainMenuScreen(game));
		}
	}

	private class DeleteDialog extends Dialog {

		public DeleteDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}

		private void initialize() {
			padTop(screenWidth / DEFAULTPADDING); // set padding on top of the
													// dialog title
			padBottom(screenWidth / DEFAULTPADDING); // set padding on bottom of
														// the dialog title
			getButtonTable().defaults().height(screenHeight * ONE_FOURTH); // set
																			// buttons
																			// height
			getButtonTable().defaults().width(screenWidth * ONE_FOURTH); // set
																			// buttons
																			// height
			setModal(true);
			setMovable(false);
			setResizable(false);
			Label dialogText = new Label("Profil wirklich löschen?", skin);
			dialogText.setWrap(true);
			dialogText.setAlignment(Align.center);
			dialogText.setFontScale((FONTSIZE2_1 * screenWidth)
					/ DIVIDEWIDTHDEFAULT);
			getContentTable().add(dialogText).width(screenWidth * TWO_THIRD);
			button(new Button(skin, "ok"), true);
			button(new Button(skin, "abort"), false);
		}

		protected void result(Object object) {
			if ((Boolean) object) {
				profileController.deleteProfile(profileList.getSelected());
			} else {
				this.remove();
			}
		}

		@Override
		public float getPrefWidth() {
			// force dialog width
			return screenWidth / 1.5f;
		}

		@Override
		public float getPrefHeight() {
			// force dialog height
			return screenHeight / 1.8f;
		}
	}

}
