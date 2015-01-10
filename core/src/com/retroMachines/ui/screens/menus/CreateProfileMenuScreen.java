package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.ui.screens.AbstractScreen;

public class CreateProfileMenuScreen  extends MenuScreen{
	
	/**
	 * the profile contoller for this screen
	 */
	private final ProfileController profileController;

	public CreateProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
		initialize();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Listener when the user accepts the it's choice and wants to create the profile
	 * @author Retro Factory
	 */
	private class CreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the user aborts the profile creation
	 * @author Retro Factory
	 */
	private class AbortCreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for left control has been clicked
	 * @author Retro Factory
	 */
	private class LeftControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for right control has been clicked
	 * @author Retro Factory
	 */
	private class RightControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
}
