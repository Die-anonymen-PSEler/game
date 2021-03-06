package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.ui.RetroDialog;
import com.retroMachines.util.Constants;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The ProfileSettingsMenuScreen is part of the view of RetroMachines. The
 * profile allows the user to edit a profile in depth and even delete it if he
 * wishes.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class ProfileSettingsMenuScreen extends MenuScreen {

	private final static float FONTSIZE_TWO_FIVE = 2.5f;
	private final static float FONTSIZE_TWO = 2f;
	private final static float THREE_FIFTH = (3f / 5f);
	private final static float FOUR_NINTH = (4f / 9f);
	private final static float FIVE_NINTH = (5f / 9f);

	private SettingController settingController;

	private Button buttonRightMode;
	private Button buttonLeftMode;

	private Image charImage;

	/**
	 * Creates a new instance of this class.
	 * @param game The actual game.
	 */
	public ProfileSettingsMenuScreen(RetroMachines game) {
		super(game);
	}

	private void setCharacterImage() {
		String name = Constants.TEXTURE_ANIMATION_NAMES[settingController
				.getCurrentCharacterId()];
		Texture texture = RetroAssetManager.getTexture(name);
		TextureRegion[] regions = TextureRegion.split(texture, 60, 64)[0];

		charImage.setDrawable(new TextureRegionDrawable(regions[0]));
		charImage.setScaling(Scaling.fit);
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		settingController = game.getSettingController();
		ProfileController profileController = game.getProfileController();

		skin = RetroAssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Profil Einstellungen", skin);
		title.setFontScale((FONTSIZE_TWO_FIVE * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Profile Name
		// TODO get Profile Name
		Label profileName = new Label(profileController.getProfileName(), skin);
		profileName
				.setFontScale((FONTSIZE_TWO * screenWidth) / DIVIDEWIDTHDEFAULT);
		profileName.setAlignment(Align.center);

		// Subtitle LeftiMode
		Label steeringTitle = new Label("Steuerung", skin);
		steeringTitle.setFontScale((FONTSIZE_TWO * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		steeringTitle.setAlignment(Align.center);

		Label resetTitle = new Label("Tutorial Reset", skin);
		resetTitle.setFontScale((FONTSIZE_TWO * screenWidth) / DIVIDEWIDTHDEFAULT);
		resetTitle.setWrap(true);
		resetTitle.setAlignment(Align.center);

		// Make Buttons
		Button buttonOk = new Button(skin, ButtonStrings.OK);
		buttonOk.addListener(new AcceptButtonClickListener());
		buttonOk.pad(screenHeight / DEFAULTBUTTONSIZE);

		Button buttonTutReset = new Button(skin, ButtonStrings.ABORT);
		buttonTutReset.addListener(new TutResetButtonClickListener());
		buttonTutReset.pad(screenHeight / DEFAULTBUTTONSIZE);

		buttonLeftMode = new Button(skin, ButtonStrings.CONTROL_LEFT);
		buttonLeftMode.addListener(new LeftControlButtonClickListener());
		buttonLeftMode.pad(screenHeight / DEFAULTBUTTONSIZE);

		buttonRightMode = new Button(skin, ButtonStrings.CONTROL_RIGHT);
		buttonRightMode.addListener(new RightControlButtonClickListener());
		buttonRightMode.pad(screenHeight / DEFAULTBUTTONSIZE);

		if (settingController.isLeftMode()) {
			buttonLeftMode.setChecked(true);
			buttonRightMode.setChecked(false);
		} else {
			buttonLeftMode.setChecked(false);
			buttonRightMode.setChecked(true);
		}

		Button buttonNextChar = new Button(skin, "nextChar");
		buttonNextChar.addListener(new NextCharButtonClickListener());
		buttonNextChar.pad(screenHeight / DEFAULTBUTTONSIZE);

		// Make Image
		charImage = new Image();
		setCharacterImage();

		// Build Tables

		// ButtonTables
		Table buttonTable = new Table(skin);

		buttonTable.add(buttonOk).padLeft(screenWidth / DEFAULTPADDING);

		Table leftTable = new Table(skin);
		leftTable.add(buttonLeftMode).padRight(screenWidth / DEFAULTPADDING);
		leftTable.add(buttonRightMode).padLeft(screenWidth / DEFAULTPADDING);

		// ImageTable
		Table imageTable = new Table(skin);
		imageTable.add(buttonNextChar).left()
				.padRight(screenWidth / DEFAULTPADDING_X_FOUR);
		imageTable.add(charImage).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.height((screenHeight) * THREE_FIFTH)
				.width((screenWidth) * ONE_FOURTH);

		// Tut Table
		Table tutTable = new Table(skin);
		tutTable.add(resetTitle).padTop(screenHeight / DEFAULTPADDING)
				.width(screenWidth * ONE_THIRD);
		tutTable.add(buttonTutReset).padTop(screenHeight / DEFAULTPADDING)
				.row();

		// RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight / DEFAULTPADDING).row();
		rightTable.add(steeringTitle).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.row();
		rightTable.add(leftTable).expandX()
				.padTop(screenHeight / DEFAULTPADDING_X_TWO).row();
		rightTable.add(tutTable).width(screenWidth * FIVE_NINTH)
				.padTop(screenHeight / DEFAULTPADDING_X_FOUR).row();

		// Put all in ScrollTable
		
		Table scrollTable = new Table(skin);
		scrollTable.add(title).expandX().colspan(COLSPAN_X_TWO)
		.padTop(screenHeight / DEFAULTPADDING).row();
		scrollTable.add(imageTable).width(screenWidth * FOUR_NINTH);
		scrollTable.add(rightTable).width(screenWidth * FIVE_NINTH).row();
		scrollTable.add(buttonTable).colspan(COLSPAN_X_TWO)
		.padTop(screenHeight / DEFAULTPADDING_X_TWO).row();
		ScrollPane scroll = new ScrollPane(scrollTable, skin);
		scroll.getStyle().hScrollKnob
				.setMinWidth((DEFAULTBUTTONSIZE * screenWidth)
						/ DIVIDEWIDTHDEFAULT);
		scroll.getStyle().vScrollKnob
		.setMinWidth((DEFAULTBUTTONSIZE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		
		
		// MainTable
		table.add(scroll);
		
		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);
	}

	/**
	 * Listener when the button for ok a profile has been clicked
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class AcceptButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	/**
	 * Listener when the button for left control has been chosen.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class LeftControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonRightMode.setChecked(false);
			buttonLeftMode.setChecked(true);
			settingController.setLeftMode(buttonLeftMode.isChecked());
		}
	}

	/**
	 * Listener when the button for right control has been chosen.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class RightControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonLeftMode.setChecked(false);
			buttonRightMode.setChecked(true);
			settingController.setLeftMode(buttonLeftMode.isChecked());
		}
	}

	/**
	 * Listener when the button for right control has been chosen.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class NextCharButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			settingController.toggleCharacter();
			setCharacterImage();
		}
	}

	/**
	 * Listener when the button for right control has been chosen.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	private class TutResetButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			settingController.resetTutorials();
			RetroDialog dialog = new RetroDialog("", "Erfolg");
			dialog.show(stage);
		}
	}

}
