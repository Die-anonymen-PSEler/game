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
import com.retroMachines.game.controllers.OnProfileChangedListener;
import com.retroMachines.game.controllers.ProfileController;

/**
 * The ProfileMenuScreen is part of the view of RetroMachines.
 * It shows all created profiles to the user and 
 * offers to create more or delete a given profile.
 * @author RetroFactory
 *
 */
public class ProfileMenuScreen extends MenuScreen implements OnProfileChangedListener{
	
	private final static float DEFAULTBUTTONSIZE = 10f;
	private final static float DEFAULTKNOBSIZE = 15f;
	private final static float DEFAULTPADING = 25f;
	private final static float DEFAULTPADINGx2 = 50f;
	private final static float DEFAULTPADINGx4 = 100f;
	private final static float FONTSIZE3 =  3f;
	private final static float FONTSIZE2_5 =  2.5f;
	private final static float FONTSIZE2_1 =  2.1f;
	private final static float DIVIDEWIDTHDEFAULT = 1920f;
	private final static float HALF = (1f / 2f);
	private final static float ONE_4th = (1f / 4f);
	private final static float ONE_5th = (1f / 5f);
	private final static float TWO_3th = (2f / 3f);
	private final static float ONE_9th = (1f / 9f);
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
	 * @param game The game which  uses this screen.
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
		Label title = new Label("Profile",skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE3 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
			
		// Make Profile List
		profileList = new List<String>(skin);
		profileList.setItems(Profile.getAllProfiles());
		profileList.setSelected(profileController.getProfileName());

		profileList.getStyle().font.setScale((FONTSIZE2_5 * screenWidth) / DIVIDEWIDTHDEFAULT);
		Table scrollTable = new Table(skin);
		scrollTable.add(profileList);
		ScrollPane profileScroll = new ScrollPane(scrollTable, skin);
		profileScroll.getStyle().hScrollKnob.setMinWidth((DEFAULTKNOBSIZE * screenWidth) / DIVIDEWIDTHDEFAULT);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		Button buttonSelectProfile = new Button(skin, "changeProfile");
		buttonSelectProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonSelectProfile.addListener(new SelectProfileButtonClickListener());
		Button buttonAddProfile = new Button(skin, "addProfile");
		buttonAddProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonAddProfile.addListener(new AddProfileButtonClickListener());
		Button buttonDeleteProfile = new Button(skin, "deleteProfile");
		buttonDeleteProfile.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonDeleteProfile.addListener(new DeleteProfileButtonClickListener());
		
		//Button Table
		Table buttons = new Table(skin);
		buttons.add(buttonDeleteProfile).center().padLeft(screenWidth * ONE_9th).padRight(screenWidth * ONE_9th).width(screenWidth * ONE_9th);
		buttons.add(buttonAddProfile).center().padLeft(screenWidth * ONE_9th).padRight(screenWidth * ONE_9th).width(screenWidth * ONE_9th);
		buttons.add(buttonSelectProfile).center().padLeft(screenWidth * ONE_9th).padRight(screenWidth * ONE_9th).width(screenWidth * ONE_9th);
		
		// Make Table
		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADINGx2).padLeft(screenWidth/ DEFAULTPADINGx4).left();
		table.add(title).width(screenWidth * (2 * ONE_5th)).right().padRight((screenWidth * HALF) - (screenWidth * ONE_5th) ).expandX().row();
		table.add(profileScroll).expandY().colspan(COLSPANx2).padTop(screenHeight / DEFAULTPADING).padBottom(screenHeight / DEFAULTPADING).row();
		table.add(buttons).colspan(COLSPANx2).row();
		
		
		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);
		
	}
	
	@Override
	public void profileChanged() {
		profileList.clearItems();
		System.out.println(Profile.getAllProfiles().length);
		profileList.setItems(Profile.getAllProfiles());
		
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
			padTop(screenWidth / DEFAULTPADING); // set padding on top of the dialog title
			padBottom(screenWidth / DEFAULTPADING); // set padding on bottom of the dialog title
			getButtonTable().defaults().height(screenHeight * ONE_4th); // set buttons height
			getButtonTable().defaults().width(screenWidth * ONE_4th); // set buttons height
			setModal(true);
			setMovable(false);
			setResizable(false);
			Label dialogText = new Label("Profil wirklich löschen?",skin);
			dialogText.setWrap(true);
			dialogText.setAlignment(Align.center);
			dialogText.setFontScale((FONTSIZE2_1 * screenWidth) / DIVIDEWIDTHDEFAULT);
			getContentTable().add(dialogText).width(screenWidth * TWO_3th);
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
