package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

/**
 * this profile allows the user to edit a profile in depth
 * and even delete it if we wishes.
 * @author RetroFactory
 *
 */
public class ProfileSettingsMenuScreen  extends MenuScreen {
	
	/**
	 * the id of the profile that is edited by this screen
	 */
	private int profileId;
	
	/**
	 * int represents position in Character String Array in Constants.java 
	 */
	private int actualCharacter;

	public ProfileSettingsMenuScreen(RetroMachines game, int id) {
		super(game);
		this.profileId = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub	
	}
	
	
	/**
	 * getter Method to get the int value wich represents position of character in String Array in Constants.java
	 * @return
	 */
	public int getActualCharacter(){
		return 0;
	}
	
	/**
	 * Listener when the button for left control has been clicked
	 * @author Retro Factory
	 */
	private class LeftControlButtonListener extends ClickListener {
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
	private class RightControlButtonListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for deleting a profile has been clicked
	 * @author Retro Factory
	 */
	private class DeleteProfileButtonClickListener extends ClickListener {
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for ok a profile has been clicked
	 * @author Retro Factory
	 */
	private class AcceptButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for change character of a profile has been clicked
	 * @author Retro Factory
	 */
	private class NextCharacterButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}

}
