package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.ui.screens.AbstractScreen;

/**
 * The MenuScreen is part of the view of RetroMachines.
 * Abstract MenuScreen class that represents the basic
 * style of a menu separated within two parts. On main part
 * (the left side) and a small part for buttons and so on.
 * @author RetroFactory
 *
 */
public abstract class MenuScreen extends AbstractScreen {
	
	/**
	 * The main table on the left side of a menu screen.
	 */
	protected Table table;
	
	/**
	 * The table on the right side of the screen containing further information.
	 */
	//protected Table tableRight;
	
	/**
	 * Creates a new MenuScreen that can be displayed to the user afterwards.
	 * @param game The game that uses this Screen.
	 */
	public MenuScreen(RetroMachines game) {
		super(game);
		table = new Table(AssetManager.menuSkin);
		//tableRight = new Table();
		stage = new Stage();
		
		initialize();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//tableRight.setFillParent(true);
	}
	
	/**
	 * Call this method to set up the menu screen. 
	 * Only fills the Table with Buttons or other UI elements that are needed. The table will be added to 
	 * the stage by the MenuScreen class.
	 */
	protected abstract void initialize();
	
	
}
