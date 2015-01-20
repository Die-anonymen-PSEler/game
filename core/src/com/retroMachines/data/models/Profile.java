package com.retroMachines.data.models;

/**
 * This class is part of the model of RetroMachines.
 * It has knowledge about all attributes regarding the profile of a user.
 * It saves information on every change that is made to this class.
 * @author RetroFactory
 */
public class Profile extends Model {

	/**
	 * The name of the table where the profiles are stored.
	 */
	public static final String TABLE_NAME = "profiles";

	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT NOT NULL,\n" +
            "\t`statisticId`\tINTEGER NOT NULL,\n" +
            "\t`settingId`\tINTEGER NOT NULL\n" +
            ");";

	/**
	 * a pattern (that should be formatted with printf or similar) that updates
	 * a row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "UPDATE `" + TABLE_NAME + "` SET `name` = '%s', `statisticId` = '%s', `settingId` = '%s' WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that deletes
	 * a row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "DELETE FROM `" + TABLE_NAME + "` WHERE id = %s;";

	/**
	 * a pattern (that should be formatted with printf or similar) that inserts
	 * a row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "INSERT INTO `" + TABLE_NAME + "` VALUES (null, '%s', '%s', '%s');";

	/**
	 * the name of the profile
	 */
	private String profileName;

	/**
	 * the id of the profile
	 */
	private int profileId;

	/**
	 * the settings for the profile
	 */
	private Setting setting;

	/**
	 * the statistics belonging to the profile
	 */
	private Statistic statistic;

	/**
	 * Creates a new profile and 
	 * 
	 * @param name
	 *            Name of the profile
	 * @param profileId
	 *            Id of the profile
	 * @param setting
	 *            settings of the profile
	 * @param statistic
	 *            statistics of the profile
	 */
	public Profile(String name, int profileId, Setting setting,
			Statistic statistic) {
		super();
		this.setProfileName(name);
		this.profileId = profileId;
		this.setSetting(setting);
		this.statistic = statistic;
	}
	
	/**
	 * Creates a new profile and attempts to fetch the further data form the 
	 * persistent background storage.
	 * @param name the name of the profile that has the record
	 */
	public Profile(String name) {
		super();
		this.profileName = name;
		fetchFromSQL();
	}
	
	/**
	 * Creates a new profile and attempts to fetch the further data form the 
	 * persistent background storage.
	 * @param rowId the id of the row where the record is stored
	 */
	public Profile(int rowId) {
		super();
		this.rowId = rowId;
		fetchFromSQL();
	}

	@Override
	public void writeToSQL() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRecordInSQL() {
		// TODO Auto-generated method stub
		return false;

	}
	
	@Override
	public void fetchFromSQL() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*
	 * Getter and Setter
	 */

	/**
	 * Get method to retrieve the name of the profile.
	 * @return the name of the profile
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Assigns a new profile name to the profile.
	 * @param profileName the new name for the profile.
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * Get method to retrieve the profile's id.
	 * @return the id of the profile
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * Get method to retrieve the profile's settings.
	 * @return the settings of the profile
	 */
	public Setting getSetting() {
		return setting;
	}

	/**
	 * Assigns a new settings object to the profile.
	 * @param setting the setting to assign
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	
	/**
	 * Get method for the statistics of the profile
	 * @return the statistic attribute associated with this profile
	 */
	public Statistic getStatistic() {
		// TODO Auto-generated method stub
		return null;
	}

}
