package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;

/**
 * This class is part of the model of RetroMachines.
 * It has knowledge about all attributes regarding the settings of a profile.
 * These attributes are saved every time a change to this settings file is made.
 * @author RetroFactory
 */
public class Setting extends Model {
	
	public static final float DEFAULT_VOLUME = 0.5f;
	
	public static final boolean DEFAULT_SOUNDONOFF = true;
	
	public static final boolean DEFAULT_LEFTCONTROL = false;

	/**
	 * the name of the table where the settings are stored
	 */
	public static final String TABLE_NAME = "settings";
	
	private static final String KEY_ID = "id";
	
	private static final String KEY_VOLUME = "volume";
	
	private static final String KEY_SOUNDONOFF = "soundOnOff";
	
	private static final String KEY_LEFTCONTROL = "leftControl";

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
	public Setting(int rowId, boolean leftControl, boolean soundOnOff, float volume) {
		super();
		this.volume = volume;
		this.soundOnOff = soundOnOff;
		this.leftControl = leftControl;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		write();
	}
	
	/**
	 * Constructor which creates a new instance of setting and attempts to
	 * find further attributes with the row id
	 * @param rowId the id of the row where the record regarding this setting is stored.
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
	}
	
	@Override
	public void destroy() {
		pref.putFloat(KEY_VOLUME, -1);
		pref.putBoolean(KEY_LEFTCONTROL, DEFAULT_LEFTCONTROL);
		pref.putBoolean(KEY_SOUNDONOFF, DEFAULT_SOUNDONOFF);
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Assigns a new volume to this object.
	 * @param volume the new volume that is assigned. it's range may go from 0 to 1.0
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
	 * @param leftControl true if left control shall be enabled; false otherwise
	 */
	public void setLeftControl(boolean leftControl) {
		this.leftControl = leftControl;
		write();
	}
	
	/**
	 * Enables or disables the sound in this setting.
	 * @param soundOnOff true to enable sound; false to disable sound.
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
	 * Get method to check whether left or right control is enabled in this settings objects.
	 * @return true if the left control; false equals right control.
	 */
	public boolean isLeftControl() {
		return leftControl;
	}
	
	/**
	 * Get method to check whether sound is enabled or not.
	 * @return true if sound is enabled; false otherwise.
	 */
	public boolean isSoundOnOff() {
		return soundOnOff;
	}
	
}
