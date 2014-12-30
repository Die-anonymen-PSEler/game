package com.retroMachines.ui.screens.menus;

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

}
