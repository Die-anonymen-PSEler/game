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
	
	public Profile(String name, int profileId) {
		this.setProfileName(name);
		this.setProfileId(profileId);
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
}
