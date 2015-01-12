package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

/**
 * The ProfileSettingsMenuScreen is part of the view of RetroMachines.
 * The profile allows the user to edit a profile in depth
 * and even delete it if he wishes.
 * @author RetroFactory
 *
 */
public class ProfileSettingsMenuScreen  extends MenuScreen {
	
	/**
	 * The ID of the profile that is edited by this screen.
	 */
	private int profileId;
	
	/**
	 * The actualCharacter represents the position in Character-String-Array in Constants.java.
	 */
	private int actualCharacter;

	public ProfileSettingsMenuScreen(RetroMachines game, int id) {
		super(game);
		this.profileId = id;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub	
	}
	
	
	/**
	 * Get Method to get the int value which represents position of the character in the 
	 * String Array in Constants.java.
	 * @return The place in string array in Constants.
	 */
	public int getActualCharacter(){
		return actualCharacter;
	}
	
	/**
	 * Listener when the button for left control has been clicked.
	 * @author RetroFactory
	 */
	private class LeftControlButtonListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for right control has been clicked.
	 * @author RetroFactory
	 */
	private class RightControlButtonListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for deleting a profile has been clicked.
	 * @author RetroFactory
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
	 * @author RetroFactory
	 */
	private class AcceptButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for change character of a profile has been clicked.
	 * @author RetroFactory
	 */
	private class NextCharacterButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}

}
