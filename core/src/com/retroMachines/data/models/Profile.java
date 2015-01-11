package com.retroMachines.data.models;


/**
 * Profile Class
 * This class contains all information regarding the profile of a user
 * It represents the model of each profile
 * @author Retro Factory
 *
 */
public class Profile extends Model {
	
	/**
	 * the name of the table where the profiles are stored
	 */
	public static final String TABLE_NAME = "profiles";
	
	/**
	 * a raw query that should be executed in case a table doesn't exist
	 */
	public static final String CREATE_TABLE_QUERY = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that updates a
	 * row within the TABLE_NAME
	 */
	public static final String UPDATE_TABLE_QUERY_PATTERN = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that deletes a
	 * row within the TABLE_NAME
	 */
	public static final String DELETE_TABLE_QUERY_PATTERN = "";
	
	/**
	 * a pattern (that should be formatted with printf or similar) that inserts a
	 * row within the TABLE_NAME
	 */
	public static final String INSERT_TABLE_QUERY_PATTERN = "";
	
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
	 * constructor for a new profile
	 * @param name Name of the profile
	 * @param profileId Id of the profile
	 * @param setting settings of the profile
	 * @param statistic statistics of the profile
	 */
	public Profile(String name, int profileId, Setting setting, Statistic statistic) {
		super();
		this.setProfileName(name);
		this.setProfileId(profileId);
		this.setSetting(setting);
		this.statistic = statistic;
	}
	
	/**
	 * @return the name of the profile
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName new name of the profile
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return the Id of the profile
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId new Id of the profile
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return the setting
	 */
	public Setting getSetting() {
		return setting;
	}

	/**
	 * @param setting the setting to set
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
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
}
