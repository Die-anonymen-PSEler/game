package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.SettingController;

/**
 * The SettingMenusScreen is part of the view of RetroMachines.
 * This screen provides general settings regarding the game
 * in particular the volume of the sound of the game.
 * @author RetroFactory
 *
 */
public class SettingsMenuScreen  extends MenuScreen {
	
	/**
	 * The setting controller, so changes can be committed.
	 */
	private final SettingController settingController;
	
	private Slider volumeSlider;
	
	/**
	 * The constructor to create a new instance of the SettingMenuScreen.
	 * @param game The game that uses this screen.
	 */
	public SettingsMenuScreen(RetroMachines game) {
		super(game);
		// TODO Auto-generated constructor stub
		settingController = game.getSettingController();
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		skin = AssetManager.menuSkin;
		
		// Make Title
		Label title = new Label("Einstellungen",skin);
		title.setWrap(true);
		title.setFontScale((2.5f*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / 10f);
		buttonReturn.addListener(new ReturnButtonClickListener());
		Button buttonLouder = new Button(skin, "soundUp");
		buttonLouder.pad(screenHeight / 10f);
		buttonLouder.addListener(new LouderButtonClickListener());
		Button buttonQuieter = new Button(skin, "soundDown");
		buttonQuieter.pad(screenHeight / 10f);
		buttonQuieter.addListener(new QuieterButtonClickListener());
		Button buttonSoundOff = new Button(skin, "soundOff");
		buttonSoundOff.pad(screenHeight / 10f);
		buttonSoundOff.addListener(new SoundOnOffButtonClickListener());
		Button buttonProfileSettings = new Button(skin, "profileSettings");
		buttonProfileSettings.pad(screenHeight / 10f);
		buttonProfileSettings.addListener(new ProfileSettingsButtonClickListener());
		
		// soundSlider 
		volumeSlider = new Slider(0, 100, 1, false, skin);
		volumeSlider.getStyle().background.setMinHeight(screenHeight / 75f);
		volumeSlider.getStyle().knob.setMinHeight(screenHeight / 5f);
		// TODO get Volume of Phone
		volumeSlider.setValue(50);
		
		
		//Make Table
		
		//Make Sound Table
		Table soundTable = new Table(skin);
		soundTable.add(buttonSoundOff).padLeft(screenWidth / 30f).width(screenWidth / 9f);
		soundTable.add(buttonQuieter).padLeft(screenWidth / 30f).width(screenWidth / 9f);
		soundTable.add(volumeSlider).padLeft(screenWidth / 30f).width(screenWidth/ 2f);
		soundTable.add(buttonLouder).padLeft(screenWidth / 30f).padRight(screenWidth / 30f).width(screenWidth / 9f);		
		
		
		table.add(buttonReturn).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left();
		table.add(title).width(screenWidth/1.8f).right().padRight((screenWidth / 2f) - (screenWidth / 3.6f) ).expandX().row();
		table.add(soundTable).padTop(screenHeight / 20f).expandY().expandX().colspan(2).row();
		table.add(buttonProfileSettings).right().padRight(screenWidth/ 9f).padBottom(screenWidth / 50f).colspan(2);
		
	    stage.addActor(table);
	    
	    inputMultiplexer.addProcessor(stage);
		
	}
	
	/**
	 * Listener for the louder button.
	 * @author RetroFactory
	 *
	 */
	private class LouderButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (volumeSlider.getValue() <= 95) {
				volumeSlider.setValue(volumeSlider.getValue() + 5);
			}
		}
	}
	
	/**
	 * Listener for the quieter button.
	 * @author RetroFactory
	 *
	 */
	private class QuieterButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (volumeSlider.getValue() >= 5) {
				volumeSlider.setValue(volumeSlider.getValue() - 5);
			}
		}
	}
	
	/**
	 * Listener for the volume on off button.
	 * @author RetroFactory
	 *
	 */
	private class SoundOnOffButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			volumeSlider.setValue(0);
		}
	}
	
	/**
	 * Listener for ProfileSettingsview
	 * @author RetroFactory
	 *
	 */
	private class ProfileSettingsButtonClickListener extends ClickListener{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Get Profile ID
			int id = 0;
			game.setScreen(new ProfileSettingsMenuScreen(game,id));
		}
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
}
