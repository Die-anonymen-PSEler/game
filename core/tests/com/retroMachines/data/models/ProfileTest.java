package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {
	
	public static final String TEST_USER_IN_DB = "TestUser";
	
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
	}
	
	/**
	 * profile id = 1 should be named "TestUser"
	 */
	@Test
	public void testDBFetch() {
		testMe = new Profile(1);
		assertTrue("profil name stimmt nicht", testMe.getProfileName().equals(TEST_USER_IN_DB));
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
}
