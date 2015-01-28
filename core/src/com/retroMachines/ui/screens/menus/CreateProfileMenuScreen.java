package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
		// TODO Auto-generated method stub
		//nameTextField = new TextField(profileController.getProfileName(), new Skin());
		skin = AssetManager.menuSkin;
		table.debug();
		// Make Title
		Label title = new Label("Create Profile",skin);
		title.setFontScale((3*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonOk = new Button(skin, "ok");
		buttonOk.addListener(new CreateProfileButtonClickListener());
		buttonOk.pad(screenHeight / 10f);
		Button buttonAbort = new Button(skin, "abort");
		buttonAbort.addListener(new AbortCreateProfileButtonClickListener());
		buttonAbort.pad(screenHeight / 10f);
		
		
		//Button
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).padRight(screenWidth / 25f);
		buttonTable.add(buttonOk).padLeft(screenWidth / 25f);
		
		// Make Textfield
		nameTextField = new TextField("ProfileName", skin);
		nameTextField.setHeight(10*screenHeight / 1080f);
		nameTextField.setMaxLength(12);
		TextFieldStyle nameTextStyle = nameTextField.getStyle();
		nameTextStyle.background.setLeftWidth(nameTextStyle.background.getLeftWidth() + 20);
		nameTextStyle.background.setRightWidth(nameTextStyle.background.getRightWidth() + 20);
		
		table.add(title).expandX().padTop(screenHeight/ 25f).row();
		table.add(nameTextField).width(screenWidth / 3).padTop(screenHeight/ 25f).row();
		table.add(buttonTable).padTop(screenHeight/ 25f).row();
	    
	    stage.addActor(table);
		inputMultiplexer.addProcessor(stage);

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
			// TODO Auto-generated method stub
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
	/**
	 * Listener when the button for left control has been chosen.
	 * @author RetroFactory
	 */
	private class LeftControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for right control has been chosen.
	 * @author RetroFactory
	 */
	private class RightControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
}
