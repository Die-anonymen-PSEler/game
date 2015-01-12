package com.retroMachines.ui.screens.menus;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
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
	
	private Skin skin;
	
	private BitmapFont font;
	
	private TextureAtlas buttonAtlas;
	
	private TextButtonStyle textButtonStyle;
	
	private TextButton playButton;
	
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
		soundId = sound.loop();
	}
	
	/**
	 * Initializes the MainMenuScreen.
	 */
	@Override
	protected void initialize() {
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal(("skins/MenuButtons.pack")));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("Back1");
        
        playButton = new TextButton("", textButtonStyle);
        
        playButton.addListener(new PlayButtonClickListener());
        
        tableLeft.add(playButton).row();
        //sound initialisieren
        
        game.getSettingController().add(this);
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
		}
	}
	
	private class SettingButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
		}
	}
	
	private class AboutButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
		}
	}
	
	private class StatisticsButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
		}
	}
	
	private class ProfileMenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			//TODO implement this
			game.setScreen(new ProfileMenuScreen(game));
		}
	}
}
