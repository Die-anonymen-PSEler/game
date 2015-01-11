package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * this is the AboutMenuScreen where information about RetroFactory and the authors
 * of this game is given.
 * @author RetroFactory
 *
 */
public class AboutMenuScreen extends MenuScreen {
	
	public static final String CREDIT = "Luca Becker, Henrike Hardt, Larissa Schmid, Adrian Schulte, Maik Wiesner";

	public AboutMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public void show() {
		
	}
	
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
