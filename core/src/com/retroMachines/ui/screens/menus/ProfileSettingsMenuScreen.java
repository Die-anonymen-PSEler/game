package com.retroMachines.ui.screens.menus;

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
	
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float FONTSIZE2 =  2f;
	private final static float THREE_FIFTH = (3f / 5f);
	private final static float FOUR_NINTH = (4f / 9f);
	private final static float FIVE_NINTH = (5f / 9f);
	
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
		buttonAbort.addListener(new ReturnButtonClickListener());
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
		Texture texture = new Texture("map/Animation.png");
		TextureRegion[] regions = TextureRegion.split(texture, 60, 64)[0];
		
		charImage.setDrawable(new TextureRegionDrawable(regions[0]));
		charImage.setScaling(Scaling.fit);

		// Build Tables

		// ButtonTables
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).padRight(screenWidth / DEFAULTPADDING);
		buttonTable.add(buttonOk).padLeft(screenWidth / DEFAULTPADDING);

		Table leftiTable = new Table(skin);
		leftiTable.add(buttonLeftMode).padRight(screenWidth / DEFAULTPADDING);
		leftiTable.add(buttonRightMode).padLeft(screenWidth / DEFAULTPADDING);

		// ImageTable
		Table imageTable = new Table(skin);
		imageTable.add(buttonNextChar).left().padRight(screenWidth / DEFAULTPADDINGx4);
		imageTable.add(charImage).padTop(screenHeight / DEFAULTPADDINGx2)
				.height((screenHeight) * THREE_FIFTH).width((screenWidth) * ONE_FOURTH);

		// RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight / DEFAULTPADDING).row();
		rightTable.add(steeringTitle).padTop(screenHeight / DEFAULTPADDING).row();
		rightTable.add(leftiTable).expandX().padTop(screenHeight / DEFAULTPADDINGx2).row();

		// MainTable
		table.add(title).expandX().colspan(COLSPANx2).padTop(screenHeight / DEFAULTPADDING).row();
		table.add(imageTable).width(screenWidth * FOUR_NINTH);
		table.add(rightTable).width(screenWidth * FIVE_NINTH).row();
		table.add(buttonTable).colspan(COLSPANx2).padTop(screenHeight / DEFAULTPADDING).row();

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
