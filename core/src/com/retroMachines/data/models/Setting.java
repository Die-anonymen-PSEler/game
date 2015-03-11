package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the settings of a profile. These attributes are saved
 * every time a change to this settings file is made.
 * 
 * @author RetroFactory
 */
public class Setting extends Model {

	/**
	 * the value an empty settings instance is set to.
	 */
	public static final float DEFAULT_VOLUME = 0.5f;

	/**
	 * the default value regarding sound on or off.
	 */
	public static final boolean DEFAULT_SOUNDONOFF = true;

	/**
	 * default value regarding the left/right control feature. by default right
	 * mode is enabled.
	 */
	public static final boolean DEFAULT_LEFTCONTROL = false;

	/**
	 * the id of the character that is disabled by default
	 */
	public static final int DEFAULT_SELECTEDCHARACTER = 0;

	/**
	 * the name of the table where the settings are stored
	 */
	public static final String TABLE_NAME = "settings";

	/**
	 * the key under which the volume is stored.
	 */
	private static final String KEY_VOLUME = "volume";

	/**
	 * the key under which the sound on off variable is stored.
	 */
	private static final String KEY_SOUNDONOFF = "soundOnOff";

	/**
	 * the key under which the left/right control is stored
	 */
	private static final String KEY_LEFTCONTROL = "leftControl";

	/**
	 * the key under which the id of the selectedcharacter is stored
	 */
	private static final String KEY_SELECTEDCHARACTER = "selectedCharacter";

	/**
	 * pattern where the key under which the tutorial value is stored
	 */
	private static final String KEY_PATTERN_TUTORIALS = "tutorial_seen_%s";

	/**
	 * the volume of the game range 0.0f to 1.0f
	 */
	private float volume;

	/**
	 * true if sound is enabled. false otherwise
	 */
	private boolean soundOnOff;

	/**
	 * true if leftControl is enabled
	 */
	private boolean leftControl;

	/**
	 * the id of the character that is selected
	 */
	private int selectedCharacter;

	/**
	 * Constructor which creates an Instance of Settings with all needed
	 * Attributs
	 * 
	 * @param leftControl
	 *            true if in Game the Controlbuttons of RetroMan changed Sites
	 *            (Steering on the right not Left and Jump and Interact Button
	 *            on Left)
	 * @param soundOnOff
	 *            true if sound is enabled. false otherwise
	 * @param volume
	 *            actual volume setting
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
	 * further attributes with the row id
	 * 
	 * @param rowId
	 *            the id of the row where the record regarding this setting is
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

	@Override
	public void write() {
		pref.putFloat(KEY_VOLUME, volume);
		pref.putBoolean(KEY_LEFTCONTROL, leftControl);
		pref.putBoolean(KEY_SOUNDONOFF, soundOnOff);
		pref.putInteger(KEY_SELECTEDCHARACTER, selectedCharacter);
		pref.flush();
	}

	@Override
	public boolean hasRecord() {
		return false;
	}

	@Override
	public void fetch() {
		volume = pref.getFloat(KEY_VOLUME, DEFAULT_VOLUME);
		leftControl = pref.getBoolean(KEY_LEFTCONTROL, DEFAULT_LEFTCONTROL);
		soundOnOff = pref.getBoolean(KEY_SOUNDONOFF, DEFAULT_SOUNDONOFF);
		selectedCharacter = pref.getInteger(KEY_SELECTEDCHARACTER,
				DEFAULT_SELECTEDCHARACTER);
	}

	@Override
	public void destroy() {
		volume = DEFAULT_VOLUME;
		leftControl = DEFAULT_LEFTCONTROL;
		soundOnOff = DEFAULT_SOUNDONOFF;
		write();
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Assigns a new volume to this object.
	 * 
	 * @param volume
	 *            the new volume that is assigned. it's range may go from 0 to
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
	 *            true if left control shall be enabled; false otherwise
	 */
	public void setLeftControl(boolean leftControl) {
		this.leftControl = leftControl;
		write();
	}

	/**
	 * Enables or disables the sound in this setting.
	 * 
	 * @param soundOnOff
	 *            true to enable sound; false to disable sound.
	 */
	public void setSoundOnOff(boolean soundOnOff) {
		this.soundOnOff = soundOnOff;
		write();
	}

	/**
	 * @return the current volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * Get method to check whether left or right control is enabled in this
	 * settings objects.
	 * 
	 * @return true if the left control; false equals right control.
	 */
	public boolean isLeftControl() {
		return leftControl;
	}

	/**
	 * Get method to check whether sound is enabled or not.
	 * 
	 * @return true if sound is enabled; false otherwise.
	 */
	public boolean isSoundOnOff() {
		return soundOnOff;
	}

	/**
	 * assigns a new id regarding the character to the setting instance
	 * 
	 * @param id
	 *            the id. should be within the range of available characters
	 */
	public void setSelectedCharacter(int id) {
		this.selectedCharacter = id;
		write();
	}

	/**
	 * getter method for the selected character id
	 * 
	 * @return the id
	 */
	public int getSelectedCharacter() {
		return this.selectedCharacter;
	}

	public boolean getTutorialFinished(int levelId) {
		return pref.getBoolean(String.format(KEY_PATTERN_TUTORIALS, levelId),
				false);
	}

	public void setTutorialFinished(int levelId, boolean value) {
		pref.putBoolean(String.format(KEY_PATTERN_TUTORIALS, levelId), value);
		pref.flush();
	}

}
