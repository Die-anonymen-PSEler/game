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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.ProfileController;

/**
 * The CreateProfileMenuScreen is part of the view of RetroMachines.
 * It is responsible for showing the creating of a new profile and the
 * interaction with the user.
 * @author RetroFactory
 *
 */
public class CreateProfileMenuScreen  extends MenuScreen{
	
	/**
	 * The ProfileController which is needed for this screen.
	 */
	
	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTPADING = 25f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE3 =  3f;
	private final static float FONTSIZE2 =  2f;
	private final static float DIVIDEHEIGHTDEFAULT = 1080f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float TEXTFIELDHEIGHTMULTIPLIKATOR = 50f;
	private final static float TEXTFIELDTABLEWIDTH = 2f;
	private final static float TEXTFIELDTABLEHEIGHT = 120f;
	private final static float TEXTFIELDFONTSIZE = 0.3f;
	private final static float TEXTFIELDCURSORSIZE = 13f;
	private final static float IMAGEHEIGHT = (3f / 5f);
	private final static float IMAGEWIDTH = (1f /4f);
	private final static float IMAGETABLEWIDTH = (4f / 9f);
	private final static float RIGHTTABLEWIDTH = (5f / 9f);
	private final static int PROFILENAMELENGTH = 12;
	private final static int TEXTFIELDBORDEROFFSET = 20;
	private final static int COLSPANx2 = 2;
	private final ProfileController profileController;

	private TextField nameTextField;
	private Button buttonRightMode;
	private Button buttonLeftMode;
	
	/**
	 * Creates a new CreateProfileMenuScreen.
	 * @param game The game which uses this screen.
	 */
	public CreateProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
	}
	
	/**
	 * Initializes the CreateProfileMenuScreen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.getMenuSkin();
		
		// Make Title
		Label title = new Label("Create Profile",skin);
		title.setFontScale((FONTSIZE3*screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
		
		//Profile Name
		Label profileName = new Label("Name",skin);
		profileName.setFontScale((FONTSIZE2*screenWidth) / DIVIDEWIDTHDEFAULT);
		profileName.setAlignment(Align.center);
		
		//Subtitle LeftiMode
		Label steeringTitle = new Label("Steuerung", skin);
		steeringTitle.setFontScale((2f*screenWidth) / DIVIDEWIDTHDEFAULT);
		steeringTitle.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonOk = new Button(skin, "ok");
		buttonOk.addListener(new CreateProfileButtonClickListener());
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
		buttonRightMode.setChecked(true);
		
		Button buttonNextChar = new Button(skin, "nextChar");
		buttonNextChar.addListener(new NextCharButtonClickListener());
		buttonNextChar.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		
		// Make Textfield
		nameTextField = new TextField("", skin);
		nameTextField.setHeight(TEXTFIELDHEIGHTMULTIPLIKATOR * screenHeight / DIVIDEHEIGHTDEFAULT);
		nameTextField.setMaxLength(PROFILENAMELENGTH);
		TextFieldStyle nameTextStyle = nameTextField.getStyle();
		// ADD 20 pixel to left and right border of Textfield
		nameTextStyle.background.setLeftWidth(nameTextStyle.background.getLeftWidth() + TEXTFIELDBORDEROFFSET);
		nameTextStyle.background.setRightWidth(nameTextStyle.background.getRightWidth() + TEXTFIELDBORDEROFFSET);
		// font size fit in Textfield
		nameTextStyle.font.scale((TEXTFIELDFONTSIZE * screenWidth) / DIVIDEWIDTHDEFAULT);
		// cursor size in Textfield
		nameTextStyle.cursor.setMinWidth((TEXTFIELDCURSORSIZE * screenWidth) / DIVIDEWIDTHDEFAULT);
		
		
		//Make Image
		Image charImage = new Image();
		charImage.setDrawable(new TextureRegionDrawable(
		        new TextureRegion(new Texture(Gdx.files.internal("Serious2.png")))));
		charImage.setScaling(Scaling.fit);
		
		// Build Tables
		

		
		//ButtonTables
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).padRight(screenWidth / DEFAULTPADING);
		buttonTable.add(buttonOk).padLeft(screenWidth / DEFAULTPADING);
		
		Table leftiTable = new Table(skin);
		leftiTable.add(buttonLeftMode).padRight(screenWidth / DEFAULTPADING);
		leftiTable.add(buttonRightMode).padLeft(screenWidth / DEFAULTPADING);
		
		//ImageTable
		Table imageTable = new Table(skin);
		imageTable.add(buttonNextChar).left().padRight(screenWidth / DEFAULTPADINGx4);
		imageTable.add(charImage).padTop(screenHeight/ DEFAULTPADINGx2).height(screenHeight * IMAGEHEIGHT).width(screenWidth * IMAGEWIDTH);
		
		
		//RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight/ DEFAULTPADING).row();
		rightTable.add(nameTextField).height(TEXTFIELDTABLEHEIGHT * ((screenWidth) / DIVIDEWIDTHDEFAULT)).width(screenWidth / TEXTFIELDTABLEWIDTH).padTop(screenHeight/ DEFAULTPADINGx2).row();
		rightTable.add(steeringTitle).padTop(screenHeight/ DEFAULTPADING).row();
		rightTable.add(leftiTable).expandX().padTop(screenHeight/ DEFAULTPADINGx2).row();
		
		//MainTable
		table.add(title).expandX().colspan(COLSPANx2).padTop(screenHeight/ DEFAULTPADING).row();
		table.add(imageTable).width(screenWidth * IMAGETABLEWIDTH);
		table.add(rightTable).width(screenWidth * RIGHTTABLEWIDTH).row();
		table.add(buttonTable).colspan(COLSPANx2).padTop(screenHeight/ DEFAULTPADING).row();
		
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
	
	/**
	 * Attempts to create a new profile.
	 */
	private void createProfile() {
		String name = nameTextField.getMessageText();
		if (!profileController.CanUserBeCreated(name)) {
			profileController.createProfile(name);
		}
		else {
			game.setScreen(new ProfileMenuScreen(game));
		}
	}
	
	/**
	 * Listener when the user made his choices and wants to create the profile.
	 * @author RetroFactory
	 */
	private class CreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
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
			// TODO  No Abort Possible if there is no other Profile
			game.setScreen(new ProfileMenuScreen(game));
		}
	}
	
	/**
	 * Listener when the button for left control has been chosen.
	 * @author RetroFactory
	 */
	private class LeftControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonRightMode.setChecked(false);
			buttonLeftMode.setChecked(true);
			//TODO Save Lefti Change
		}
	}
	
	/**
	 * Listener when the button for right control has been chosen.
	 * @author RetroFactory
	 */
	private class RightControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonLeftMode.setChecked(false);
			buttonRightMode.setChecked(true);
			//TODO Save Lefti Change
		}
	}
	
	/**
	 * Listener when the button for right control has been chosen.
	 * @author RetroFactory
	 */
	private class NextCharButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			//TODO Change Char Pic and save it
		}
	}
}
