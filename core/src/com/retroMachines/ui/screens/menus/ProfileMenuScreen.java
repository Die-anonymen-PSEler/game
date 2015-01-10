package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.ui.screens.AbstractScreen;

public class ProfileMenuScreen extends MenuScreen{
	
	/**
	 * 
	 */
	private ProfileController profileController;
	
	public ProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Listener when the button for creating a profile has been clicked
	 * @author Retro Factory
	 */
	private class AddProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for selecting a profile has been clicked
	 * @author Retro Factory
	 */
	private class SelectProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
}
