package com.retroMachines.data.models;

public class Profile {
	
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
	
	public Profile(String name, int profileId, Setting setting, Statistic statistic) {
		this.setProfileName(name);
		this.setProfileId(profileId);
		this.setSetting(setting);
		this.statistic = statistic;
	}
	
	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public int getProfileId() {
		return profileId;
	}

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
}
