package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

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
	private class LevellockedButtonClickListener extends ClickListener {
		
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
	private class LevelunlockedButtonClickListener extends ClickListener {
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
		}
		
	}
	
	private class LevelSelectListener extends ClickListener {
		
	}
	
}
