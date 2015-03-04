package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.ui.RetroDialog;
import com.retroMachines.util.Constants;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The CreateProfileMenuScreen is part of the view of RetroMachines. It is
 * responsible for showing the creating of a new profile and the interaction
 * with the user.
 * 
 * @author RetroFactory
 * 
 */
public class CreateProfileMenuScreen extends MenuScreen {

	/**
	 * The ProfileController which is needed for this screen.
	 */

	private final static float FONTSIZE3 = 3f;
	private final static float FONTSIZE2 = 2f;
	private final static float TEXTFIELDHEIGHTMULTIPLICATOR = 50f;
	private final static float TEXTFIELDTABLEWIDTH = 2f;
	private final static float TEXTFIELDTABLEHEIGHT = 120f;
	private final static float TEXTFIELDFONTSIZE = 0.3f;
	private final static float TEXTFIELDCURSORSIZE = 13f;
	private final static float IMAGEHEIGHT = (3f / 5f);
	private final static float IMAGEWIDTH = (1f / 4f);
	private final static float IMAGETABLEWIDTH = (4f / 9f);
	private final static float RIGHTTABLEWIDTH = (5f / 9f);
	private final static int PROFILENAMELENGTH = 12;
	private final static int TEXTFIELDBORDEROFFSET = 20;

	/**
	 * controllers associated with this screen
	 */
	private final ProfileController profileController;
	private final SettingController settingController;

	private TextField nameTextField;
	private Button buttonRightMode;
	private Button buttonLeftMode;

	private RetroDialog errorDialog;
	
	private Image charImage;
	
	/**
	 * counter for the currently shown character image
	 */
	private int i = 0;
	
	/**
	 * Creates a new CreateProfileMenuScreen.
	 * 
	 * @param game
	 *            The game which uses this screen.
	 */
	public CreateProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
		settingController = game.getSettingController();
	}

	/**
	 * Initializes the CreateProfileMenuScreen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Create Profile", skin);
		title.setFontScale((FONTSIZE3 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Profile Name
		Label profileName = new Label("Name", skin);
		profileName
				.setFontScale((FONTSIZE2 * screenWidth) / DIVIDEWIDTHDEFAULT);
		profileName.setAlignment(Align.center);

		// Subtitle LeftiMode
		Label steeringTitle = new Label("Steuerung", skin);
		steeringTitle.setFontScale((2f * screenWidth) / DIVIDEWIDTHDEFAULT);
		steeringTitle.setAlignment(Align.center);

		// Make Buttons
		Button buttonOk = new Button(skin, ButtonStrings.OK);
		buttonOk.addListener(new CreateProfileButtonClickListener());
		buttonOk.pad(screenHeight / DEFAULTBUTTONSIZE);

		Button buttonAbort = new Button(skin, ButtonStrings.ABORT);
		buttonAbort.addListener(new AbortCreateProfileButtonClickListener());
		buttonAbort.pad(screenHeight / DEFAULTBUTTONSIZE);

		buttonLeftMode = new Button(skin, ButtonStrings.CONTROL_LEFT);
		buttonLeftMode.addListener(new LeftControlButtonClickListener());
		buttonLeftMode.pad(screenHeight / DEFAULTBUTTONSIZE);

		buttonRightMode = new Button(skin, ButtonStrings.CONTROL_LEFT);
		buttonRightMode.addListener(new RightControlButtonClickListener());
		buttonRightMode.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonRightMode.setChecked(true);

		Button buttonNextChar = new Button(skin, ButtonStrings.NEXT_CHAR);
		buttonNextChar.addListener(new NextCharButtonClickListener());
		buttonNextChar.pad(screenHeight / DEFAULTBUTTONSIZE);

		// Make Textfield
		nameTextField = new TextField("", skin);
		nameTextField.setHeight(TEXTFIELDHEIGHTMULTIPLICATOR * screenHeight
				/ DIVIDEHEIGHTDEFAULT);
		nameTextField.setMaxLength(PROFILENAMELENGTH);
		TextFieldStyle nameTextStyle = nameTextField.getStyle();
		// ADD 20 pixel to left and right border of Textfield
		nameTextStyle.background.setLeftWidth(nameTextStyle.background
				.getLeftWidth() + TEXTFIELDBORDEROFFSET);
		nameTextStyle.background.setRightWidth(nameTextStyle.background
				.getRightWidth() + TEXTFIELDBORDEROFFSET);
		// font size fit in Textfield
		nameTextStyle.font.scale((TEXTFIELDFONTSIZE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);
		// cursor size in Textfield
		nameTextStyle.cursor.setMinWidth((TEXTFIELDCURSORSIZE * screenWidth)
				/ DIVIDEWIDTHDEFAULT);

		// Make Image
		charImage = new Image();
		setCharacterImage(i);

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
		imageTable.add(buttonNextChar).left()
				.padRight(screenWidth / DEFAULTPADDINGx4);
		imageTable.add(charImage).padTop(screenHeight / DEFAULTPADDINGx2)
				.height(screenHeight * IMAGEHEIGHT)
				.width(screenWidth * IMAGEWIDTH);

		// RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight / DEFAULTPADDING).row();
		rightTable
				.add(nameTextField)
				.height(TEXTFIELDTABLEHEIGHT
						* ((screenWidth) / DIVIDEWIDTHDEFAULT))
				.width(screenWidth / TEXTFIELDTABLEWIDTH)
				.padTop(screenHeight / DEFAULTPADDINGx2).row();
		rightTable.add(steeringTitle).padTop(screenHeight / DEFAULTPADDING)
				.row();
		rightTable.add(leftiTable).expandX()
				.padTop(screenHeight / DEFAULTPADDINGx2).row();

		// MainTable
		table.add(title).expandX().colspan(COLSPANx2)
				.padTop(screenHeight / DEFAULTPADDING).row();
		table.add(imageTable).width(screenWidth * IMAGETABLEWIDTH);
		table.add(rightTable).width(screenWidth * RIGHTTABLEWIDTH).row();
		table.add(buttonTable).colspan(COLSPANx2)
				.padTop(screenHeight / DEFAULTPADDING).row();

		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
			game.previousScreen();
		}
		return false;
	}
	
	private void setCharacterImage(int value) {
		String name = Constants.TEXTURE_ANIMATION_NAMES[value % Constants.TEXTURE_ANIMATION_NAMES.length];
		Texture texture = AssetManager.getTexture(name);
		TextureRegion[] regions = TextureRegion.split(texture, 60, 64)[0];
		
		charImage.setDrawable(new TextureRegionDrawable(regions[0]));
		charImage.setScaling(Scaling.fit);
	}

	/**
	 * Attempts to create a new profile.
	 */
	private void createProfile() {
		String name = nameTextField.getText();
		if (profileController.canUserBeCreated(name)) {
			profileController.createProfile(name);
			settingController.setLeftMode(buttonLeftMode.isChecked());
			settingController.setCharacterId(i % Constants.TEXTURE_ANIMATION_NAMES.length);
			game.setScreen(new ProfileMenuScreen(game));
		} else {
		}
	}

	/**
	 * Listener when the user made his choices and wants to create the profile.
	 * 
	 * @author RetroFactory
	 */
	private class CreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			createProfile();
		}
	}

	/**
	 * Listener when the user aborts the profile creation.
	 * @author RetroFactory
	 */
	private class AbortCreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (profileController.getAllProfiles().length > 0) {
				game.previousScreen();
			} else {
				errorDialog = new RetroDialog("", "Zum Spielen brauchst du ein Profil!");
				errorDialog.show(stage);
			}
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
			setCharacterImage(++i);
		}
	}
}
