package com.retroMachines.ui.screens.menus;



import java.util.ArrayList;

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
import com.retroMachines.game.controllers.ProfileController;

/**
 * The ProfileMenuScreen is part of the view of RetroMachines.
 * It shows all created profiles to the user and 
 * offers to create more or delete a given profile.
 * @author RetroFactory
 *
 */
public class ProfileMenuScreen extends MenuScreen{
	
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
	 * @param game The game which  uses this screen.
	 */
	public ProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		skin = AssetManager.getMenuSkin();
		
		// Make Title
		Label title = new Label("Profile",skin);
		title.setWrap(true);
		title.setFontScale((3f * screenWidth)/1920f);
		title.setAlignment(Align.center);
			
		// Make Profile List
		profileList = new List<String>(skin);
		profileList.setItems(Profile.getAllProfiles());
		
		
		profileList.getStyle().font.setScale((2.5f * screenWidth)/1920f);
		Table scrollTable = new Table(skin);
		scrollTable.add(profileList);
		ScrollPane profileScroll = new ScrollPane(scrollTable, skin);
		profileScroll.getStyle().hScrollKnob.setMinWidth((15f * screenWidth)/1920f);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / 10f);
		buttonReturn.addListener(new ReturnButtonClickListener());
		Button buttonSelectProfile = new Button(skin, "changeProfile");
		buttonSelectProfile.pad(screenHeight / 10f);
		buttonSelectProfile.addListener(new SelectProfileButtonClickListener());
		Button buttonAddProfile = new Button(skin, "addProfile");
		buttonAddProfile.pad(screenHeight / 10f);
		buttonAddProfile.addListener(new AddProfileButtonClickListener());
		Button buttonDeleteProfile = new Button(skin, "deleteProfile");
		buttonDeleteProfile.pad(screenHeight / 10f);
		buttonDeleteProfile.addListener(new DeleteProfileButtonClickListener());
		
		//Button Table
		Table buttons = new Table(skin);
		buttons.add(buttonDeleteProfile).center().padLeft(screenWidth / 9f).padRight(screenWidth / 9f).width(screenWidth / 9f);
		buttons.add(buttonAddProfile).center().padLeft(screenWidth / 9f).padRight(screenWidth / 9f).width(screenWidth / 9f);
		buttons.add(buttonSelectProfile).center().padLeft(screenWidth / 9f).padRight(screenWidth / 9f).width(screenWidth / 9f);
		
		// Make Table
		table.add(buttonReturn).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left();
		table.add(title).width(screenWidth / 2.5f).right().padRight((screenWidth / 2f) - (screenWidth / 5f) ).expandX().row();
		table.add(profileScroll).expandY().colspan(2).padTop(screenHeight / 20f).padBottom(screenHeight / 20f).row();
		table.add(buttons).colspan(2).row();
		
		
		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);
		
	}	
	
	/**
	 * Listener when the button for creating a profile has been clicked.
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
	 * @author RetroFactory
	 */
	private class DeleteProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if(deleteDialog != null) {
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
	 * @author RetroFactory
	 */
	private class SelectProfileButtonClickListener extends ClickListener {
		
		public SelectProfileButtonClickListener() {
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			//TODO  Is it Right ?
			game.getProfileController().changeActiveProfile(profileList.getSelected());
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
	/**
	 * Button to return to the MainMenuScreen.
	 * 
	 * @author RetroFactory
	 *
	 */
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
private class DeleteDialog extends Dialog {
		
		public DeleteDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}
		
		private void initialize() {
			padTop(screenWidth / 30f); // set padding on top of the dialog title
			padBottom(screenWidth / 35f); // set padding on bottom of the dialog title
	        getButtonTable().defaults().height(screenHeight/ 4f); // set buttons height
	        getButtonTable().defaults().width(screenWidth / 4f); // set buttons height
	        setModal(true);
	        setMovable(false);
	        setResizable(false);
	        Label dialogText = new Label("Profil wirklich l�schen?",skin);
	        dialogText.setWrap(true);
	        dialogText.setAlignment(Align.center);
	        dialogText.setFontScale((2.1f * screenWidth)/1920f);
			getContentTable().add(dialogText).width(screenWidth / 1.5f);
			button(new Button(skin, "ok"), true);
			button(new Button(skin, "abort"), false);
		}
		
		protected void result(Object object) {
			//TODO Profile l�schen
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
