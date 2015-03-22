package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the profile of a user. It saves information on every
 * change that is made to this class.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Profile extends Model {

	/**
	 * The key for the profile name.
	 */
	private static final String KEY_PROFILE_NAME = "name";

	/**
	 * The key for the statistics.
	 */
	private static final String KEY_STATISTIC = "statisticId";

	/**
	 * The key for the settingId.
	 */
	private static final String KEY_SETTING = "settingId";

	/**
	 * Destroyed profile.
	 */
	private static final String DESTROYED_PROFILE = "destroyed_profile_does_not_exist_no_more";
	
	/**
	 * The name of the table where the profiles are stored.
	 */
	public static final String TABLE_NAME = "profiles";

	/**
	 * The name of the profile.
	 */
	private String profileName;

	/**
	 * The settings for the profile.
	 */
	private Setting setting;

	/**
	 * The statistics belonging to the profile.
	 */
	private Statistic statistic;

	/**
	 * Creates a new profile and attempts to fetch the further data from the
	 * persistent background storage.
	 * 
	 * @param rowId
	 *            The id of the row where the record is stored.
	 */
	public Profile(int rowId) {
		super();
		this.rowId = rowId;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		fetch();
	}

	/**
	 * Creates a new profile.
	 * @param i The place where it should be stored.
	 * @param name The name of the profile.
	 * @param setting The settings of the profile.
	 * @param statistic The statistics of the profile.
	 */
	public Profile(int i, String name, Setting setting, Statistic statistic) {
		super();
		rowId = i;
		this.profileName = name;
		this.setting = setting;
		this.statistic = statistic;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		write();
	}

	/**
	 * To work on the profile.
	 */
	@Override
	public void write() {
		pref.putString(KEY_PROFILE_NAME, profileName);
		pref.putInteger(KEY_SETTING, (setting != null) ? setting.rowId : -1);
		pref.putInteger(KEY_STATISTIC, (statistic != null) ? statistic.rowId
				: -1);
		pref.flush();
	}

	/**
	 * Is there a record of the profile.
	 */
	@Override
	public boolean hasRecord() {
		return !(pref.getString(KEY_PROFILE_NAME, "name")
				.equals(DESTROYED_PROFILE));
	}

	/**
	 * Fetch the value.
	 */
	@Override
	public void fetch() {
		profileName = pref.getString(KEY_PROFILE_NAME, KEY_PROFILE_NAME);
	}

	/**
	 * Delete the profile.
	 */
	@Override
	public void destroy() {
		pref.putString(KEY_PROFILE_NAME, DESTROYED_PROFILE);
		pref.putInteger(KEY_SETTING, -1);
		pref.putInteger(KEY_STATISTIC, -1);
		pref.flush();
	}

	/*
	 * Getter and Setter
	 */

	/**
	 * Assigns a new statistic to the profile and writes back.
	 * 
	 * @param statistic
	 *            The statistic associated to the profile.
	 */
	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
		write();
	}

	/**
	 * Assigns a new profile name to the profile.
	 * 
	 * @param profileName
	 *            The new name for the profile.
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
		write();
	}

	/**
	 * Assigns a new settings object to the profile.
	 * 
	 * @param setting
	 *            The setting to assign.
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
		write();
	}

	/**
	 * Get method for the statistics of the profile.
	 * 
	 * @return The statistic attribute associated with this profile.
	 */
	public Statistic getStatistic() {
		return this.statistic;
	}

	/**
	 * Get method to retrieve the name of the profile.
	 * 
	 * @return The name of the profile.
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Get method to retrieve the profile's id.
	 * 
	 * @return The id of the profile.
	 */
	public int getProfileId() {
		return rowId;
	}

	/**
	 * Get method to retrieve the profile's settings.
	 * 
	 * @return The settings of the profile.
	 */
	public Setting getSetting() {
		return setting;
	}

}
