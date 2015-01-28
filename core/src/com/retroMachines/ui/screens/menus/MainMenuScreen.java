package com.retroMachines.ui.screens.menus;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.models.SettingsChangeListener;

/**
 * The MainMenuScreen is part of the view of RetroMachines.
 * The main menu of the game providing ways to all the other screens of the game.
 * It is the anchor of the game and is displayed right after the application has
 * finished loading.
 * @author RetroFactory
 *
 */
public class MainMenuScreen extends MenuScreen implements SettingsChangeListener {
	
	/**
	 * The Title of this Screen.
	 */
	public static final String TITLE = "RETROMACHINES";
	
	private ExitDialog exitDialog;
	
	private Sound sound;
	
	/**
	 * The path to the file for the sound.
	 */
	private static final String SOUNDFILE = "";
	
	private long soundId;
		
	/**
	 * The constructor to create a new instance of the MainMenuScreen.
	 * @param game The game that uses this screen.
	 */
	public MainMenuScreen(RetroMachines game) {
		super(game);
	}
	
	/**
	 * Initializes the MainMenuScreen.
	 */
	@Override
	protected void initialize() {
		
		skin = AssetManager.menuSkin;
		// Make Title
		Label title = new Label(TITLE,skin);
		title.setFontScale((3*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonPlay = new Button(skin, "play");
		buttonPlay.addListener(new PlayButtonClickListener());
		buttonPlay.pad(screenHeight / 5);
		
		Button buttonSetting = new Button(skin, "settings");
		buttonSetting.addListener(new SettingButtonClickListener());
		buttonSetting.pad(screenHeight / 10f);
		
		Button buttonAbout = new Button(skin, "info");
		buttonAbout.addListener(new AboutButtonClickListener());
		buttonAbout.pad(screenHeight / 10f);
		
		Button buttonStatistics = new Button(skin, "statistic");
		buttonStatistics.addListener(new StatisticsButtonClickListener());
		buttonStatistics.pad(screenHeight / 10f);
		
		Button buttonProfileMenu = new Button(skin, "profile");
		buttonProfileMenu.addListener(new ProfileMenuClickListener());
		buttonProfileMenu.pad(screenHeight / 10f);
		
		Button buttonExit = new Button(skin, "exit");
		buttonExit.addListener(new ExitClickListener());
		buttonExit.pad(screenHeight / 10f);
		
		//Sidebar
		Table sidebar = new Table(skin);
		sidebar.add(buttonSetting).padTop(screenHeight / 25f).row();
		sidebar.add(buttonAbout).padTop(screenHeight / 25f).row();
		sidebar.add(buttonProfileMenu).padTop(screenHeight / 25f).row();
		sidebar.add(buttonStatistics).padTop(screenHeight / 25f).padBottom(screenHeight / 25f).row();
		
		//Left Part of Menu
		Table mainPart = new Table(skin);
		mainPart.add(buttonExit).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left().row();
		mainPart.add(title).padTop(screenHeight / 20f).expandX().right().row();
		mainPart.add(buttonPlay).padLeft((screenWidth / 2f) - (screenHeight / 5f) - (buttonPlay.getWidth() / 2f)).left().row();
		
		
		// Add Title and Buttons to View
		table.add(mainPart).expandY().width(screenWidth * (4 / 5f)).top();
		table.add(sidebar).width(screenWidth / 5f).row();
	    
	    stage.addActor(table);
	    inputMultiplexer.addProcessor(stage);

        //sound initialisieren
        
       // game.getSettingController().add(this);
	}
	
	/**
     * Renders the Stage to the Screen.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    
    @Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK) {
    		if (exitDialog != null) {
        		exitDialog.show(stage);
        	} else {
        		exitDialog = new ExitDialog("Exit", skin, "default");
				exitDialog.show(stage);
        	}
    	}
    	return false;
    }
	
	/**
	 * Sets the sound to the new volume that was newly adjusted in the settings.
	 */
	@Override
	public void onSettingsChanged() {
		
		// TODO Auto-generated method stub
		float newVolume = game.getSettingController().getVolume();
		sound.setVolume(soundId, newVolume);
		//changes the volume in the settings so that its saved while quitting the game
		game.getSettingController().setVolume(newVolume);
	}
	
	private class PlayButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			game.setScreen(new LevelMenuScreen(game));
		}
	}
	
	private class SettingButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			game.setScreen(new SettingsMenuScreen(game));
		}
	}
	
	private class AboutButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			game.setScreen(new AboutMenuScreen(game));
		}
	}
	
	private class StatisticsButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			game.setScreen(new StatisticMenuScreen(game));
		}
	}
	
	private class ProfileMenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			game.setScreen(new CreateProfileMenuScreen(game));
		}
	}
	
	private class ExitClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			if(exitDialog != null) {
				exitDialog.show(stage);
			} else {
				exitDialog = new ExitDialog("Exit", skin, "default");
				exitDialog.show(stage);
			}

		}
	}
	
	private class ExitDialog extends Dialog {
		
		public ExitDialog(String title, Skin skin, String windowStyleName) {
			super(title, skin, windowStyleName);
			initialize();
		}
		
		private void initialize() {
			padTop(60); // set padding on top of the dialog title
			padBottom(30); // set padding on bottom of the dialog title
	        getButtonTable().defaults().height(120); // set buttons height
	        getButtonTable().defaults().width(160); // set buttons height
	        setModal(true);
	        setMovable(false);
	        setResizable(false);
	        Label dialogText = new Label("Do you realy want to leave us ?",skin);
	        dialogText.setWrap(true);
	        dialogText.setAlignment(Align.center);
	        dialogText.setFontScale(1,2);
			getContentTable().add(dialogText).width(700);
			button("Yes", true).padRight(50);
			button("No", false).padLeft(50);
		}
		
		protected void result(Object object) {
			if ((Boolean) object) {
				Gdx.app.exit();
			} else {
				this.remove();
			}
		}
		
		   @Override
		   public float getPrefWidth() {
		      // force dialog width
		      return 750;
		   }

		   @Override
		   public float getPrefHeight() {
		      // force dialog height
		      return 400;
		   }
	}
}
