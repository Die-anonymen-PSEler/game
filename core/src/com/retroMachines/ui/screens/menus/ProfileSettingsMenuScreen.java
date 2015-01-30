package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;

/**
 * The ProfileSettingsMenuScreen is part of the view of RetroMachines. The
 * profile allows the user to edit a profile in depth and even delete it if he
 * wishes.
 * 
 * @author RetroFactory
 * 
 */
public class ProfileSettingsMenuScreen extends MenuScreen {
	
	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADING = 25f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float FONTSIZE2 =  2f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float TWO_8th = (2f / 8f);
	private final static float THREE_5th = (3f / 5f);
	private final static float FOUR_9th = (4f / 9f);
	private final static float FIVE_9th = (5f / 9f);
	private final static int COLSPANx2 = 2;
	
	private SettingController settingController;

	/**
	 * The actualCharacter represents the position in Character-String-Array in
	 * Constants.java.
	 */
	private int actualCharacter;

	private Button buttonRightMode;
	private Button buttonLeftMode;

	public ProfileSettingsMenuScreen(RetroMachines game) {
		super(game);		
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		settingController = game.getSettingController();
		skin = AssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Profil Einstellungen", skin);
		title.setFontScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Profile Name
		// TODO get Profile Name
		Label profileName = new Label("Profile Name", skin);
		profileName.setFontScale((FONTSIZE2 * screenWidth) / DIVIDEWIDTHDEFAULT);
		profileName.setAlignment(Align.center);

		// Subtitle LeftiMode
		Label steeringTitle = new Label("Steuerung", skin);
		steeringTitle.setFontScale((FONTSIZE2 * screenWidth) / DIVIDEWIDTHDEFAULT);
		steeringTitle.setAlignment(Align.center);

		// Make Buttons
		Button buttonOk = new Button(skin, "ok");
		buttonOk.addListener(new AcceptButtonClickListener());
		buttonOk.pad(screenHeight / DEFAULTBUTTONSIZE);

		Button buttonAbort = new Button(skin, "abort");
		buttonAbort.addListener(new AbortCreateProfileButtonClickListener());
		buttonAbort.pad(screenHeight / DEFAULTBUTTONSIZE);

		buttonLeftMode = new Button(skin, "controlLeft");
		buttonLeftMode.addListener(new LeftControlButtonClickListener());
		buttonLeftMode.pad(screenHeight / DEFAULTBUTTONSIZE);

		buttonRightMode = new Button(skin, "controlRight");
		buttonRightMode.addListener(new RightControlButtonClickListener());
		buttonRightMode.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		if (settingController.getLeftMode()) {
			buttonLeftMode.setChecked(true);
			buttonRightMode.setChecked(false);
		}
		else {
			buttonLeftMode.setChecked(false);
			buttonRightMode.setChecked(true);
		}

		Button buttonNextChar = new Button(skin, "nextChar");
		buttonNextChar.addListener(new NextCharButtonClickListener());
		buttonNextChar.pad(screenHeight / DEFAULTBUTTONSIZE);

		// Make Image
		Image charImage = new Image();
		charImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("Serious2.png")))));
		charImage.setScaling(Scaling.fit);

		// Build Tables

		// ButtonTables
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).padRight(screenWidth / DEFAULTPADING);
		buttonTable.add(buttonOk).padLeft(screenWidth / DEFAULTPADING);

		Table leftiTable = new Table(skin);
		leftiTable.add(buttonLeftMode).padRight(screenWidth / DEFAULTPADING);
		leftiTable.add(buttonRightMode).padLeft(screenWidth / DEFAULTPADING);

		// ImageTable
		Table imageTable = new Table(skin);
		imageTable.add(buttonNextChar).left().padRight(screenWidth / DEFAULTPADINGx4);
		imageTable.add(charImage).padTop(screenHeight / DEFAULTPADINGx2)
				.height((screenHeight) * THREE_5th).width((screenWidth) * TWO_8th);

		// RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight / DEFAULTPADING).row();
		rightTable.add(steeringTitle).padTop(screenHeight / DEFAULTPADING).row();
		rightTable.add(leftiTable).expandX().padTop(screenHeight / DEFAULTPADINGx2).row();

		// MainTable
		table.add(title).expandX().colspan(COLSPANx2).padTop(screenHeight / DEFAULTPADING).row();
		table.add(imageTable).width(screenWidth * FOUR_9th);
		table.add(rightTable).width(screenWidth * FIVE_9th).row();
		table.add(buttonTable).colspan(COLSPANx2).padTop(screenHeight / DEFAULTPADING).row();

		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);
	}

	/**
	 * Get Method to get the int value which represents position of the
	 * character in the String Array in Constants.java.
	 * 
	 * @return The place in string array in Constants.
	 */
	public int getActualCharacter() {
		return actualCharacter;
	}

	/**
	 * Listener when the user aborts the profile creation.
	 * 
	 * @author RetroFactory
	 */
	private class AbortCreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.setScreen(new SettingsMenuScreen(game));
		}
	}

	/**
	 * Listener when the button for ok a profile has been clicked
	 * 
	 * @author RetroFactory
	 */
	private class AcceptButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			settingController.setLeftMode(buttonLeftMode.isChecked());
			game.setScreen(new MainMenuScreen(game));
			super.clicked(event, x, y);
		}
	}

	/**
	 * Listener when the button for left control has been chosen.
	 * 
	 * @author RetroFactory
	 */
	private class LeftControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonRightMode.setChecked(false);
			buttonLeftMode.setChecked(true);
		}
	}

	/**
	 * Listener when the button for right control has been chosen.
	 * 
	 * @author RetroFactory
	 */
	private class RightControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonLeftMode.setChecked(false);
			buttonRightMode.setChecked(true);
			// TODO Save Lefti Change
		}
	}

	/**
	 * Listener when the button for right control has been chosen.
	 * 
	 * @author RetroFactory
	 */
	private class NextCharButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Change Char Pic and save it
		}
	}

}
