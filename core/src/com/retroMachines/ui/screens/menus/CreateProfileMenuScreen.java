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
		skin = AssetManager.menuSkin;
		
		// Make Title
		Label title = new Label("Create Profile",skin);
		title.setFontScale((3*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		//Profile Name
		Label profileName = new Label("Name",skin);
		profileName.setFontScale((2f*screenWidth)/1920f);
		profileName.setAlignment(Align.center);
		
		//Subtitle LeftiMode
		Label steeringTitle = new Label("Steuerung", skin);
		steeringTitle.setFontScale((2f*screenWidth)/1920f);
		steeringTitle.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonOk = new Button(skin, "ok");
		buttonOk.addListener(new CreateProfileButtonClickListener());
		buttonOk.pad(screenHeight / 10f);
		
		Button buttonAbort = new Button(skin, "abort");
		buttonAbort.addListener(new AbortCreateProfileButtonClickListener());
		buttonAbort.pad(screenHeight / 10f);
		
		buttonLeftMode = new Button(skin, "controlLeft");
		buttonLeftMode.addListener(new LeftControlButtonClickListener());
		buttonLeftMode.pad(screenHeight / 10f);
		
		buttonRightMode = new Button(skin, "controlRight");
		buttonRightMode.addListener(new RightControlButtonClickListener());
		buttonRightMode.pad(screenHeight / 10f);
		buttonRightMode.setChecked(true);
		
		Button buttonNextChar = new Button(skin, "nextChar");
		buttonNextChar.addListener(new NextCharButtonClickListener());
		buttonNextChar.pad(screenHeight / 10f);
		
		
		// Make Textfield
		nameTextField = new TextField("", skin);
		nameTextField.setHeight(52*screenHeight / 1080f);
		nameTextField.setMaxLength(12);
		TextFieldStyle nameTextStyle = nameTextField.getStyle();
		nameTextStyle.background.setLeftWidth(nameTextStyle.background.getLeftWidth() + 20);
		nameTextStyle.background.setRightWidth(nameTextStyle.background.getRightWidth() + 20);
		nameTextStyle.font.scale((0.3f * screenWidth)/1920f);
		nameTextStyle.cursor.setMinWidth((13f * screenWidth)/1920f);
		
		
		//Make Image
		Image charImage = new Image();
		charImage.setDrawable(new TextureRegionDrawable(
		        new TextureRegion(new Texture(Gdx.files.internal("Serious2.png")))));
		charImage.setScaling(Scaling.fit);
		
		// Build Tables
		

		
		//ButtonTables
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).padRight(screenWidth / 25f);
		buttonTable.add(buttonOk).padLeft(screenWidth / 25f);
		
		Table leftiTable = new Table(skin);
		leftiTable.add(buttonLeftMode).padRight(screenWidth / 25f);
		leftiTable.add(buttonRightMode).padLeft(screenWidth / 25f);
		
		//ImageTable
		Table imageTable = new Table(skin);
		imageTable.add(buttonNextChar).left().padRight(screenWidth / 100f);
		imageTable.add(charImage).padTop(screenHeight/ 50f).height((3*screenHeight) / 5f).width(2*(screenWidth) / 8f);
		
		
		//RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight/ 30f).row();
		rightTable.add(nameTextField).height(41*((3*screenWidth)/1920f)).width(screenWidth / 2).padTop(screenHeight/ 50f).row();
		rightTable.add(steeringTitle).padTop(screenHeight/ 20f).row();
		rightTable.add(leftiTable).expandX().padTop(screenHeight/ 50f).row();
		
		//MainTable
		table.add(title).expandX().colspan(2).padTop(screenHeight/ 25f).row();
		table.add(imageTable).width(screenWidth * (4 / 9f));
		table.add(rightTable).width(screenWidth * (5 / 9f)).row();
		table.add(buttonTable).colspan(2).padTop(screenHeight/ 25f).row();
		
	    stage.addActor(table);
		inputMultiplexer.addProcessor(stage);

	}
	
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
    		game.setScreen(new ProfileMenuScreen(game));
    	}
    	return false;
	}
	
	/**
	 * Attempts to create a new profile.
	 */
	private void createProfile() {
		String name = nameTextField.getMessageText();
		if (!profileController.createProfile(name)) {
			// TODO SHOW ALERT
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
