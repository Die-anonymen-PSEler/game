package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * Abstract MenuScreen class that represents that basic
 * style of a menu separated within two parts. On main part
 * (the left side) and a small part for buttons and so on.
 * @author RetroFactory
 *
 */
public abstract class MenuScreen extends AbstractScreen {
	
	/**
	 * the main table on the left side of a menu screen
	 */
	protected Table tableLeft;
	
	/**
	 * the table on the right side of the screen containing further information
	 */
	protected Table tableRight = new Table();
	
	/**
	 * Creates a new MenuScreen that can be displayed to the user afterwards
	 * @param game
	 */
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
