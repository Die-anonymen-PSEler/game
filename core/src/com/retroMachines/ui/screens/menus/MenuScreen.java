package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * abstrakte MenuScreen klasse die die grundsätzliche Struktur eines Menüs wiederspiegelt.
 * @author lucabecker
 *
 */
public abstract class MenuScreen extends AbstractScreen {
	
	/**
	 * the main table on the left side of a menu screen
	 */
	protected Table tableLeft;
	
	/*
	 * the table on the right side of the screen containing further information
	 */
	protected Table tableRight = new Table();
	
	

	public MenuScreen(RetroMachines game) {
		super(game);
		tableLeft = new Table();
		tableRight = new Table();
		stage = new Stage();
		
		initialize();
		
		tableLeft.setFillParent(true);
		tableRight.setFillParent(true);
		stage.addActor(tableLeft);
		stage.addActor(tableRight);
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Call this method to set up the menu screen. 
	 * Only fill the Table with Buttons or whatever ui elements you have. The table will be added to 
	 * the stage by the MenuScreen class
	 */
	protected abstract void initialize();
	
	
}
