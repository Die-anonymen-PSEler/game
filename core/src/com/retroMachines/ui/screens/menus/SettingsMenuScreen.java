package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * This screen provides general settings regarding the game
 * In particular this is the volume of the game
 * @author RetroFactory
 *
 */
public class SettingsMenuScreen  extends MenuScreen {
	
	/**
	 * the settings controller so changes can be commited
	 */
	private final SettingController settingController;
	
	/**
	 * 
	 * @param game
	 */
	public SettingsMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
		settingController = game.getSettingController();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Listener for the louder button
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
	 * listener for the quieter button
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
	 * listener for the volume on off button
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
