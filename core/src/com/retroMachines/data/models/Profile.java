package com.retroMachines.data.models;

import com.badlogic.gdx.Gdx;

/**
 * This class is part of the model of RetroMachines. It has knowledge about all
 * attributes regarding the profile of a user. It saves information on every
 * change that is made to this class.
 * 
 * @author RetroFactory
 */
public class Profile extends Model {

	/**
	 * The name of the table where the profiles are stored.
	 */
	public static final String TABLE_NAME = "profiles";

	/**
	 * The key for the profile name
	 */
	private static final String KEY_PROFILE_NAME = "name";

	/**
	 * The key for the statistics
	 */
	private static final String KEY_STATISTIC = "statisticId";

	/**
	 * The key for the settingId
	 */
	private static final String KEY_SETTING = "settingId";

	/**
	 * destroyed profile
	 */
	private static final String DESTROYED_PROFILE = "destroyed_profile_does_not_exist_no_more";

	/**
	 * the name of the profile
	 */
	private String profileName;

	/**
	 * the settings for the profile
	 */
	private Setting setting;

	/**
	 * the statistics belonging to the profile
	 */
	private Statistic statistic;

	/**
	 * Creates a new profile and attempts to fetch the further data from the
	 * persistent background storage.
	 * 
	 * @param rowId
	 *            the id of the row where the record is stored
	 */
	public Profile(int rowId) {
		super();
		this.rowId = rowId;
		pref = Gdx.app.getPreferences(TABLE_NAME + rowId);
		fetch();
	}

	/**
	 * Creates a new profile
	 * @param i where it should be stored
	 * @param name name of the profile
	 * @param setting settings of the profile
	 * @param statistic statistics of the profile
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

	@Override
	public void write() {
		pref.putString(KEY_PROFILE_NAME, profileName);
		pref.putInteger(KEY_SETTING, (setting != null) ? setting.rowId : -1);
		pref.putInteger(KEY_STATISTIC, (statistic != null) ? statistic.rowId
				: -1);
		pref.flush();
	}

	@Override
	public boolean hasRecord() {
		return !(pref.getString(KEY_PROFILE_NAME, "name")
				.equals(DESTROYED_PROFILE));
	}

	@Override
	public void fetch() {
		profileName = pref.getString(KEY_PROFILE_NAME, KEY_PROFILE_NAME);
	}

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
	 * assigns a new statistic to the profile and writes back.
	 * 
	 * @param statistic
	 *            the statistic associated to the profile
	 */
	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
		write();
	}

	/**
	 * Assigns a new profile name to the profile.
	 * 
	 * @param profileName
	 *            the new name for the profile.
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
		write();
	}

	/**
	 * Assigns a new settings object to the profile.
	 * 
	 * @param setting
	 *            the setting to assign
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
		write();
	}

	/**
	 * Get method for the statistics of the profile
	 * 
	 * @return the statistic attribute associated with this profile
	 */
	public Statistic getStatistic() {
		return this.statistic;
	}

	/**
	 * Get method to retrieve the name of the profile.
	 * 
	 * @return the name of the profile
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Get method to retrieve the profile's id.
	 * 
	 * @return the id of the profile
	 */
	public int getProfileId() {
		return rowId;
	}

	/**
	 * Get method to retrieve the profile's settings.
	 * 
	 * @return the settings of the profile
	 */
	public Setting getSetting() {
		return setting;
	}

}
