package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.SettingController;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The SettingMenusScreen is part of the view of RetroMachines. This screen
 * provides general settings regarding the game in particular the volume of the
 * sound of the game.
 * 
 * @author RetroFactory
 * 
 */
public class SettingsMenuScreen extends MenuScreen {

	private final static float PADDING_THIRTY = 30f;
	private final static float FONTSIZE_TWO_FIVE = 2.5f;
	private final static float HALFTITLEWIDTH = (1f / 3.6f);
	private final static float TITLEWIDTH = (1f / 1.8f);
	private final static int SLIDERMIN = 0;
	private final static int SLIDERMAX = 100;
	private final static int SLIDERSTEPSIZE = 1;
	private final static float SLIDERKNOBSIZE = (1f / 5f);
	private final static float SLIDERBACKGROUNDSIZE = (1f / 75f);

	/**
	 * 1 The setting controller, so changes can be committed.
	 */
	private SettingController settingController;

	private Slider volumeSlider;
	private Button buttonSoundOff;

	/**
	 * The constructor to create a new instance of the SettingMenuScreen.
	 * 
	 * @param game
	 *            The game that uses this screen.
	 */
	public SettingsMenuScreen(RetroMachines game) {
		super(game);
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		skin = RetroAssetManager.getMenuSkin();

		// Make Title
		Label title = new Label("Einstellungen", skin);
		title.setWrap(true);
		title.setFontScale((FONTSIZE_TWO_FIVE * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);

		// Make Buttons
		Button buttonReturn = new Button(skin, ButtonStrings.BACK);
		buttonReturn.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonReturn.addListener(new ReturnButtonClickListener());
		Button buttonLouder = new Button(skin, ButtonStrings.SOUND_UP);
		buttonLouder.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonLouder.addListener(new LouderButtonClickListener());
		Button buttonQuieter = new Button(skin, ButtonStrings.SOUND_DOWN);
		buttonQuieter.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonQuieter.addListener(new QuieterButtonClickListener());
		buttonSoundOff = new Button(skin, ButtonStrings.SOUND_OFF);
		buttonSoundOff.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonSoundOff.addListener(new SoundOnOffButtonClickListener());

		Button buttonProfileSettings = new Button(skin, "profileSettings");
		buttonProfileSettings.pad(screenHeight / DEFAULTBUTTONSIZE);
		buttonProfileSettings
				.addListener(new ProfileSettingsButtonClickListener());

		// soundSlider
		volumeSlider = new Slider(SLIDERMIN, SLIDERMAX, SLIDERSTEPSIZE, false,
				skin);
		volumeSlider.getStyle().background.setMinHeight(screenHeight
				* SLIDERBACKGROUNDSIZE);
		volumeSlider.getStyle().knob
				.setMinHeight(screenHeight * SLIDERKNOBSIZE);
		volumeSlider.addListener(new SliderListener());

		// Make Table

		// Make Sound Table
		Table soundTable = new Table(skin);
		soundTable.add(buttonSoundOff).padLeft(screenWidth / PADDING_THIRTY)
				.width(screenWidth * ONE_NINTH);
		soundTable.add(buttonQuieter).padLeft(screenWidth / PADDING_THIRTY)
				.width(screenWidth * ONE_NINTH);
		soundTable.add(volumeSlider).padLeft(screenWidth / PADDING_THIRTY)
				.width(screenWidth * HALF);
		soundTable.add(buttonLouder).padLeft(screenWidth / PADDING_THIRTY)
				.padRight(screenWidth / PADDING_THIRTY)
				.width(screenWidth * ONE_NINTH);

		table.add(buttonReturn).padTop(screenHeight / DEFAULTPADDING_X_TWO)
				.padLeft(screenWidth / DEFAULTPADDING_X_FOUR).left();
		table.add(title)
				.width(screenWidth * TITLEWIDTH)
				.right()
				.padRight((screenWidth * HALF) - (screenWidth * HALFTITLEWIDTH))
				.expandX().row();
		table.add(soundTable).padTop(screenHeight / DEFAULTPADDING).expandY()
				.expandX().colspan(COLSPAN_X_TWO).row();
		table.add(buttonProfileSettings).right()
				.padRight(screenWidth * ONE_NINTH)
				.padBottom(screenWidth / DEFAULTPADDING_X_TWO).colspan(COLSPAN_X_TWO);

		stage.addActor(table);

		inputMultiplexer.addProcessor(stage);
		
		settingController = game.getSettingController();
		buttonSoundOff.setChecked(!settingController.isSoundEnabled());
		volumeSlider.setValue(settingController.getVolume() * SLIDERMAX);
	}

	private class SliderListener extends ChangeListener {

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			if (volumeSlider.getValue() == 0) {
				buttonSoundOff.setChecked(true);
			} else {
				buttonSoundOff.setChecked(false);
			}
			settingController.setVolume(volumeSlider.getValue() / SLIDERMAX);

		}

	}

	/**
	 * Listener for the louder button.
	 * 
	 * @author RetroFactory
	 * 
	 */
	private class LouderButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (volumeSlider.getValue() <= 95) {
				volumeSlider.setValue(volumeSlider.getValue() + 5);
			} else {
				volumeSlider.setValue(100);
			}
			settingController.setVolume(volumeSlider.getValue() / SLIDERMAX);
		}
	}

	/**
	 * Listener for the quieter button.
	 * 
	 * @author RetroFactory
	 * 
	 */
	private class QuieterButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (volumeSlider.getValue() >= 5) {
				volumeSlider.setValue(volumeSlider.getValue() - 5);
			} else {
				volumeSlider.setValue(0);
			}
			settingController.setVolume(volumeSlider.getValue() / SLIDERMAX);
		}
	}

	/**
	 * Listener for the volume on off button.
	 * 
	 * @author RetroFactory
	 * 
	 */
	private class SoundOnOffButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (volumeSlider.getValue() == 0) {
				buttonSoundOff.setChecked(false);
				volumeSlider.setValue(20);
				settingController.setVolume(20 / (float) SLIDERMAX);

			} else {
				buttonSoundOff.setChecked(true);
				volumeSlider.setValue(0);
				settingController.setVolume(0 / (float) SLIDERMAX);
			}

		}
	}

	/**
	 * Listener for ProfileSettingsview
	 * 
	 * @author RetroFactory
	 * 
	 */
	private class ProfileSettingsButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Get Profile ID
			game.setScreen(new ProfileSettingsMenuScreen(game));
		}
	}
}
