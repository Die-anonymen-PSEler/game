package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
		initialize();
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
		title.setFontScale(3);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonOk = new Button(skin, "play");
		buttonOk.addListener(new CreateProfileButtonClickListener());
		buttonOk.pad(150);
		Button buttonAbort = new Button(skin, "play");
		buttonAbort.addListener(new AbortCreateProfileButtonClickListener());
		buttonAbort.pad(150);
		
		
		//Button
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).width(Gdx.graphics.getWidth()/2);
		buttonTable.add(buttonOk).width(Gdx.graphics.getWidth()/2);
		
		// Make Textfield
		nameTextField = new TextField("ProfileName", skin);
		
		table.add(title).expandX().padTop(50).row();
		table.add(nameTextField).padTop(50).row();
		table.add(buttonTable).padTop(50).row();
	    
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
			super.clicked(event, x, y);
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
