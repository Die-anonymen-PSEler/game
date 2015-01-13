package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * The ProfileMenuScreen is part of the view of RetroMachines.
 * It shows all created profiles to the user and 
 * offers to create more or delete a given profile.
 * @author RetroFactory
 *
 */
public class ProfileMenuScreen extends MenuScreen{
	
	private ProfileController profileController;
	
	private List<String> profileList;
	
	/**
	 * The constructor to create a new instance of the ProfileMenuScreen.
	 * @param game The game which  uses this screen.
	 */
	public ProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
		initialize();
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		profileList = new List<String>(new Skin());
		
	}
	
	private void scrollDown() {
		
	}
	
	private void scrollUp() {
		
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
	 * Listener when the button for selecting a profile has been clicked.
	 * Afterwards the main menu is shown.
	 * @author RetroFactory
	 */
	private class SelectProfileButtonClickListener extends ClickListener {
		
		/**
		 * The ID of the profile so it can be selected afterwards.
		 */
		private int id;
		
		public SelectProfileButtonClickListener(int id) {
			this.id = id;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.getProfileController().changeActiveProfile("");
		}
	}
	
	/**
	 * Listener when the button for selecting a profile has been clicked.
	 * @author RetroFactory
	 */
	private class ScrollUpButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			scrollUp();
		}
	}
	
	/**
	 * Listener when the button for selecting a profile has been clicked.
	 * @author RetroFactory
	 */
	private class ScrollDownButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			scrollDown();
		}
	}
}
