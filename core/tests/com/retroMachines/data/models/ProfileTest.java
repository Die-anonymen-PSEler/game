package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {
	
	public static final String TEST_USER_IN_DB = "TestUser";
	
	public static final int TEST_STATISTICID = 1;
	
	public static final int TEST_SETTINGSID = 1;
	
	/**
	 * the profile under test
	 */
	private Profile testMe;
	
	private Setting setting;
	
	private Statistic statistic;

	@Before
	public void setUp() throws Exception {
		
	}
	
	/**
	 * get method check
	 */
	@Test
	public void test() {
		setting = new Setting(false, false, 0);
		statistic = new Statistic(0, 5, 5);
		testMe = new Profile("Testprofil", setting, statistic);
		assertTrue("Settings nicht identisch", testMe.getSetting() == setting);
		assertTrue("Statistic nicht identisch", testMe.getStatistic() == statistic);
		setting.destroy();
		statistic.destroy();
		testMe.destroy();
	}
	
	/**
	 * profile id = 1 should be named "TestUser"
	 */
	@Test
	public void testDBFetch() {
		testMe = new Profile(1);
		assertTrue("profil name stimmt nicht", testMe.getProfileName().equals(TEST_USER_IN_DB));
		assertTrue("settings id stimmt nicht", testMe.getSetting().rowId == TEST_SETTINGSID);
		assertTrue("statistic id stimmt nicht", testMe.getStatistic().rowId == TEST_STATISTICID);
	}
	
	/**
	 * fetch profile and then change name
	 */
	@Test
	public void testOverrideDB() {
		testMe = new Profile(1);
		String tempName = "testcase testoverrideDb";
		testMe.setProfileName(tempName);
		Profile profileTest = new Profile(1);
		assertTrue("profile name stimmt nicht", profileTest.getProfileName().equals(tempName));
		profileTest.setProfileName(TEST_USER_IN_DB);
	}
	
	@Test
	public void testGetAllProfiles() {
		String[] profiles = Profile.getAllProfiles();
		assertTrue("zu viele profile. sollte nur eines geben", profiles.length == 1);
		assertTrue("falsches profil an erster stelle", profiles[0].equals(TEST_USER_IN_DB));
	}
}
