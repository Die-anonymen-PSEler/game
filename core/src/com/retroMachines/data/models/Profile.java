package com.retroMachines.data.models;

public class Profile extends Model {
	
	private static final String TABLE_NAME = "profiles";
	
	private static final String CREATE_TABLE_QUERY = "";
	
	private static final String UPDATE_TABLE_QUERY_PATTERN = "";
	
	private static final String DELETE_TABLE_QUERY_PATTERN = "";
	
	private static final String INSERT_TABLE_QUERY_PATTERN = "";
	
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
