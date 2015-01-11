package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

/**
 * LevelMenuscreen displays the list of available levels to the user
 * and initiates a new level once the user has picked one.
 * @author RetroFactory
 */
public class LevelMenuScreen extends MenuScreen{
	
	private List<String> levelList;

	public LevelMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Button to Return in MainmenuScreen
	 * 
	 * @author RetroMachines
	 *
	 */
	private class ReturnButtonClickListener extends ClickListener {
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
		
	}
	
	/**
	 * Used when level is locked
	 * @author RetroMachines
	 *
	 */
	private class LevelLockedButtonClickListener extends ClickListener {
		
		/**
		 * the id of the level so it can be started later on
		 */
		private int id;
		
		public LevelLockedButtonClickListener(int id) {
			this.id = id;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
		
	}
	
	/**
	 * Used when Level is unlcoked
	 * @author RetroMachines
	 *
	 */
	private class LevelUnlockedButtonClickListener extends ClickListener {
		
		/**
		 * the id of the level so it can be started later on
		 */
		private int id;
		
		public LevelUnlockedButtonClickListener(int id) {
			this.id = id;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
		
	}
	
}
