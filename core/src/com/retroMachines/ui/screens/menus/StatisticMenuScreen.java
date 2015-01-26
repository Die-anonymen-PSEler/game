package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

public class StatisticMenuScreen  extends MenuScreen {


	/**
	 * The constructor to create a new instance of the AboutMenuScreen.
	 * @param game The game which uses the screen.
	 */
	public StatisticMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Shows the AboutMenuScreen.
	 */
	public void show() {
		
	}
	
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}

	/**
	 * Initializes the screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
