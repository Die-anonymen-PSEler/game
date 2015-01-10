package com.retroMachines.ui.screens.menus;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

public class MainMenuScreen extends MenuScreen{
	
	private Skin skin;
	
	private BitmapFont font;
	
	private TextureAtlas buttonAtlas;
	
	private TextButtonStyle textButtonStyle;
	
	private TextButton playButton;
	
	public MainMenuScreen(RetroMachines game) {
		super(game);
		
	}

	public void show() {
	}
	
	
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
