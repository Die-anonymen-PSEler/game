package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * The SettingMenusScreen is part of the view of RetroMachines.
 * This screen provides general settings regarding the game
 * in particular the volume of the sound of the game.
 * @author RetroFactory
 *
 */
public class SettingsMenuScreen  extends MenuScreen {
	
	/**
	 * The setting controller, so changes can be committed.
	 */
	private final SettingController settingController;
	
	/**
	 * The constructor to create a new instance of the SettingMenuScreen.
	 * @param game The game that uses this screen.
	 */
	public SettingsMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
		settingController = game.getSettingController();
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Listener for the louder button.
	 * @author RetroFactory
	 *
	 */
	private class LouderButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener for the quieter button.
	 * @author RetroFactory
	 *
	 */
	private class QuieterButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener for the volume on off button.
	 * @author RetroFactory
	 *
	 */
	private class SoundOnOffButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
}
