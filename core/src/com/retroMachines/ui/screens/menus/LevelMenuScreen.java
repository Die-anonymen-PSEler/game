package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;

/**
 * The LevelMenuScreen is part of the view of RetroMachines.
 * It displays the list of available levels to the user
 * and initiates a new level once the user has picked one.
 * @author RetroFactory
 */
public class LevelMenuScreen extends MenuScreen{
	
	private List<String> levelList;
	private LockedDialog lockedDialog; 

	/**
	 * The constructor to create a new instance of the LevelMenuScreen.
	 * @param game The game which uses this screen.
	 */
	public LevelMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the LevelMenuScreen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.menuSkin;
		
		// Make Title
		Label title = new Label("Level",skin);
		title.setWrap(true);
		title.setFontScale((2.5f*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		// Make Buttons
		
		//Level Buttons + Table
		Table levelTable = new Table(skin);
		int nLevel = 15;
		int unlocked = 4;
		//TODO get Num Of Levels?
		for(int i = 0; i < nLevel; i++) {
			Integer iToString = new Integer(i + 1);
			TextButton ilevel;			
			if (i >= unlocked) {
				ilevel = new TextButton(iToString.toString(), skin, "locked");
				ilevel.addListener(new LevelLockedButtonClickListener(i));
			} else {
				ilevel = new TextButton(iToString.toString(), skin);
				ilevel.addListener(new LevelUnlockedButtonClickListener(i));
			}
			ilevel.getStyle().font.setScale((2.5f*screenWidth)/1920f);
			
			if (i % 5 == 4 && i > 0) {
				levelTable.add(ilevel).width(screenWidth / 6f).height(screenHeight / 6f).pad(screenHeight / 100f).row();
			} else {
				levelTable.add(ilevel).width(screenWidth / 6f).height(screenHeight / 6f).pad(screenHeight / 100f);
			}
		}
		
		// Back Button
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / 10f);
		buttonReturn.addListener(new ReturnButtonClickListener());
		
		
		//Make Table
		table.add(buttonReturn).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left();
		table.add(title).width(screenWidth/4f).right().padRight((screenWidth / 2f) - (screenWidth / 8f) ).expandX().row();
		table.add(levelTable).colspan(2).expandY();
		
		
	    stage.addActor(table);
	    
	    inputMultiplexer.addProcessor(stage);
		
	}
	
	 @Override
	    public boolean keyDown(int keycode) {
	    	if (keycode == Keys.BACK) {
	    		if (lockedDialog != null) {
	    			lockedDialog.show(stage);
	        	} else {
	        		lockedDialog = new LockedDialog("", skin, "default");
	        		lockedDialog.show(stage);
	        	}
	    	}
	    	return false;
	    }
	
	/**
	 * Button to return to the MainMenuScreen.
	 * 
	 * @author RetroFactory
	 *
	 */
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
	/**
	 * Used when level is locked.
	 * @author RetroFactory
	 *
	 */
	private class LevelLockedButtonClickListener extends ClickListener {
		
		/**
		 * the ID of the level so it can be started later on.
		 */
		private int id;
		
		public LevelLockedButtonClickListener(int id) {
			this.id = id;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if(lockedDialog != null) {
				lockedDialog.show(stage);
			} else {
				lockedDialog = new LockedDialog("", skin, "default");
				lockedDialog.show(stage);
			}
		}
		
	}
	
	/**
	 * Used when Level is unlocked and can be played.
	 * @author RetroFactory
	 *
	 */
	private class LevelUnlockedButtonClickListener extends ClickListener {
		
		/**
		 * the ID of the level so it can be started later on
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
	
	private class LockedDialog extends Dialog {
		
		public LockedDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}
		
		private void initialize() {
			padTop(screenWidth / 30f); // set padding on top of the dialog title
			padBottom(screenWidth / 35f); // set padding on bottom of the dialog title
	        getButtonTable().defaults().height(screenHeight/ 4f); // set buttons height
	        getButtonTable().defaults().width(screenWidth / 4f); // set buttons height
	        setModal(true);
	        setMovable(false);
	        setResizable(false);
	        Label dialogText = new Label("Nicht Freigeschaltet",skin);
	        dialogText.setWrap(true);
	        dialogText.setAlignment(Align.center);
	        dialogText.setFontScale((2.1f * screenWidth)/1920f);
			getContentTable().add(dialogText).width(screenWidth / 1.5f);
			button(new Button(skin, "ok"), true);
		}
		
		protected void result(Object object) {
			this.remove();
		}
		
		   @Override
		   public float getPrefWidth() {
		      // force dialog width
		      return screenWidth / 1.5f;
		   }

		   @Override
		   public float getPrefHeight() {
		      // force dialog height
		      return screenHeight / 1.8f;
		   }
	}
	
}
