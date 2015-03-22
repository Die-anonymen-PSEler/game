package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;
import com.retroMachines.util.Constants;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the settings of a profile. These attributes are saved
 * every time a change to this settings file is made.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Setting extends Model {

	/**
	 * The key under which the volume is stored.
	 */
	private static final String KEY_VOLUME = "volume";

	/**
	 * The key under which the sound on off variable is stored.
	 */
	private static final String KEY_SOUNDONOFF = "soundOnOff";

	/**
	 * The key under which the left/right control is stored.
	 */
	private static final String KEY_LEFTCONTROL = "leftControl";

	/**
	 * The key under which the id of the selected character is stored.
	 */
	private static final String KEY_SELECTEDCHARACTER = "selectedCharacter";

	/**
	 * Pattern where the key under which the tutorial value is stored.
	 */
	private static final String KEY_PATTERN_TUTORIALS = "tutorial_seen_%s";

	/**
	 * The value an empty settings instance is set to.
	 */
	public static final float DEFAULT_VOLUME = 0.5f;

	/**
	 * The default value regarding sound on or off.
	 */
	public static final boolean DEFAULT_SOUNDONOFF = true;

	/**
	 * Default value regarding the left/right control feature. By default right
	 * mode is enabled.
	 */
	public static final boolean DEFAULT_LEFTCONTROL = false;

	/**
	 * The id of the character that is disabled by default.
	 */
	public static final int DEFAULT_SELECTEDCHARACTER = 0;

	/**
	 * The name of the table where the settings are stored.
	 */
	public static final String TABLE_NAME = "settings";

	/**
	 * The volume of the game range 0.0f to 1.0f.
	 */
	private float volume;

	/**
	 * True if sound is enabled. False otherwise.
	 */
	private boolean soundOnOff;

	/**
	 * True if leftControl is enabled.
	 */
	private boolean leftControl;

	/**
	 * The ID of the character that is selected.
	 */
	private int selectedCharacter;

	/**
	 * Constructor which creates an Instance of Settings with all needed
	 * Attributes.
	 * 
	 * @param rowId
	 *            The ID of the row where it should be stored.
	 * @param leftControl
	 *            True if in Game the ControlButtons of RetroMan changed Sites
	 *            (Steering on the right not Left and Jump and Interact Button
	 *            on the left).
	 * @param soundOnOff
	 *            True if sound is enabled. False otherwise.
	 * @param volume
	 *            Actual volume setting.
	 */
	public Setting(int rowId, boolean leftControl, boolean soundOnOff,
			float volume) {
		super();
		this.volume = volume;
		this.soundOnOff = soundOnOff;
		this.leftControl = leftControl;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		write();
	}

	/**
	 * Constructor which creates a new instance of setting and attempts to find
	 * further attributes with the row ID.
	 * 
	 * @param rowId
	 *            The ID of the row where the record regarding this setting is
	 *            stored.
	 */
	public Setting(int rowId) {
		super();
		this.rowId = rowId;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		fetch();
	}

	/*
	 * inherited methods
	 */

	/**
	 * To edit the settings.
	 */
	@Override
	public void write() {
		pref.putFloat(KEY_VOLUME, volume);
		pref.putBoolean(KEY_LEFTCONTROL, leftControl);
		pref.putBoolean(KEY_SOUNDONOFF, soundOnOff);
		pref.putInteger(KEY_SELECTEDCHARACTER, selectedCharacter);
		pref.flush();
	}

	/**
	 * True if a record excists.
	 */
	@Override
	public boolean hasRecord() {
		return false;
	}

	/**
	 * Fetch the settings.
	 */
	@Override
	public void fetch() {
		volume = pref.getFloat(KEY_VOLUME, DEFAULT_VOLUME);
		leftControl = pref.getBoolean(KEY_LEFTCONTROL, DEFAULT_LEFTCONTROL);
		soundOnOff = pref.getBoolean(KEY_SOUNDONOFF, DEFAULT_SOUNDONOFF);
		selectedCharacter = pref.getInteger(KEY_SELECTEDCHARACTER,
				DEFAULT_SELECTEDCHARACTER);
	}

	/**
	 * Reset the settings.
	 */
	@Override
	public void destroy() {
		volume = DEFAULT_VOLUME;
		leftControl = DEFAULT_LEFTCONTROL;
		soundOnOff = DEFAULT_SOUNDONOFF;
		for (int i = 0; i < Constants.MAX_LEVEL_ID; i++) {
			pref.putBoolean(String.format(KEY_PATTERN_TUTORIALS, i), false);
		}
		write();
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Get method to check whether left or right control is enabled in this
	 * settings objects.
	 * 
	 * @return True if the left control is chosen. "False" equals right control mode is chosen.
	 */
	public boolean isLeftControl() {
		return leftControl;
	}

	/**
	 * Get method to check whether sound is enabled or not.
	 * 
	 * @return True if the sound is enabled. False otherwise.
	 */
	public boolean isSoundOnOff() {
		return soundOnOff;
	}

	/**
	 * Checks whether the tutorial is finished or not. 
	 * @param levelId The level of which it should be managed.
	 * @return True if it is finished. False otherwise.
	 */
	public boolean isTutorialFinished(int levelId) {
		return pref.getBoolean(String.format(KEY_PATTERN_TUTORIALS, levelId),
				false);
	}
	
	/**
	 * Get method for the current volume.
	 * @return The current volume.
	 */
	public float getVolume() {
		return volume;
	}
	
	/**
	 * Get method for the selected character ID.
	 * 
	 * @return The ID of the character.
	 */
	public int getSelectedCharacter() {
		return this.selectedCharacter;
	}

	/**
	 * Assigns a new volume to this object.
	 * 
	 * @param volume
	 *            The new volume that is assigned. It's range may go from 0 to
	 *            1.0
	 */
	public void setVolume(float volume) {
		if (volume > 1.0f || volume < 0.0f) {
			throw new IllegalArgumentException("new volume is out of range");
		}
		this.volume = volume;
		write();
	}

	/**
	 * Set left control to this setting.
	 * 
	 * @param leftControl
	 *            True if left control shall be enabled. False otherwise.
	 */
	public void setLeftControl(boolean leftControl) {
		this.leftControl = leftControl;
		write();
	}

	/**
	 * Enables or disables the sound in this setting.
	 * 
	 * @param soundOnOff
	 *            True to enable sound. False to disable sound.
	 */
	public void setSoundOnOff(boolean soundOnOff) {
		this.soundOnOff = soundOnOff;
		write();
	}

	/**
	 * Assigns a new ID regarding the character to the setting instance.
	 * 
	 * @param id
	 *            The new ID. It should be within the range of available characters.
	 */
	public void setSelectedCharacter(int id) {
		this.selectedCharacter = id;
		write();
	}

	/**
	 * Sets whether the tutorial is finished or not.
	 * @param levelId The level of the tutorial.
	 * @param value True if it is finished. False otherwise.
	 */
	public void setTutorialFinished(int levelId, boolean value) {
		pref.putBoolean(String.format(KEY_PATTERN_TUTORIALS, levelId), value);
		pref.flush();
	}

}
