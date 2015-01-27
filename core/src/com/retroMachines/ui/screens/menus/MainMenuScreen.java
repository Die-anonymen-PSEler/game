package com.retroMachines.ui.screens.menus;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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
	
	private BitmapFont font;
	
	private TextureAtlas buttonAtlas;
	
	private TextButtonStyle textButtonStyle;
	
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
		initialize();
	}

	/**
	 * Displays the MainMenuScreen.
	 */
	public void show() {
		skin = AssetManager.menuSkin;
		table.debug();
		// Make Title
		Label title = new Label(TITLE,skin);
		title.setFontScale(2);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonPlay = new Button(skin, "default");
		buttonPlay.addListener(new PlayButtonClickListener());
		buttonPlay.pad(100);
		
		Button buttonSetting = new Button(skin, "default");
		buttonSetting.addListener(new SettingButtonClickListener());
		buttonSetting.pad(50);
		
		Button buttonAbout = new Button(skin, "default");
		buttonAbout.addListener(new AboutButtonClickListener());
		buttonAbout.pad(50);
		
		Button buttonStatistics = new Button(skin, "default");
		buttonStatistics.addListener(new StatisticsButtonClickListener());
		buttonStatistics.pad(50);
		
		Button buttonProfileMenu = new Button(skin, "default");
		buttonProfileMenu.addListener(new ProfileMenuClickListener());
		buttonProfileMenu.pad(50);
		
		Button buttonExit = new Button(skin, "default");
		buttonExit.addListener(new ExitClickListener());
		buttonExit.pad(50);
		
		//Sidebar
		Table sidebar = new Table(skin);
		sidebar.add(buttonSetting).padTop(20).row();
		sidebar.add(buttonAbout).padTop(20).row();
		sidebar.add(buttonProfileMenu).padTop(20).row();
		sidebar.add(buttonStatistics).padTop(20).row();
		
		// Add Title and Buttons to View
		table.add(buttonExit).padTop(20).left().padLeft(50).colspan(2).row();
		table.add(title).padTop(20).colspan(2).expandX().row();
		table.add().width(table.getWidth()*3 / 4);
		table.add().width(table.getWidth() / 4).row();
		table.add(buttonPlay).padTop(20);
		table.add(sidebar).row();
	    table.top();
	    
	    stage.addActor(table);
	    Gdx.input.setInputProcessor(stage);

	        
		//soundId = sound.loop();
	}
	
	/**
	 * Initializes the MainMenuScreen.
	 */
	@Override
	protected void initialize() {

        //sound initialisieren
        
       // game.getSettingController().add(this);
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
			game.setScreen(new ProfileMenuScreen(game));
		}
	}
	
	private class ExitClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			ExitDialog exitD = new ExitDialog("Do you wanna leave?", skin, "default");
			exitD.show(stage);
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
	        getButtonTable().defaults().height(50); // set buttons height
	        getButtonTable().defaults().width(80); // set buttons height
	        setModal(true);
	        setMovable(false);
	        setResizable(false);
			text("Test");
			button("Yes", true);
			button("No", false);
		}
		
		protected void result(Object object) {
			if ((Boolean) object) {
				Gdx.app.exit();
			} else {
				
			}
		}
		
		   @Override
		   public float getPrefWidth() {
		      // force dialog width
		      return 480f;
		   }

		   @Override
		   public float getPrefHeight() {
		      // force dialog height
		      return 200f;
		   }
	}
}
