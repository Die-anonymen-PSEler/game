package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ProfileTest {
	
	public static final String TEST_USER_IN_DB = "name";
	
	public static final int TEST_USER_IN_DB_ID = 1;
	
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
		testMe = new Profile(1);
		testMe.setProfileName(TEST_USER_IN_DB);
	}
	
	@After
	public void tearDown() throws Exception {
		testMe.destroy();
	}
	
	/**
	 * get method check
	 */
	@Test
	public void testCreateProfile() {
		setting = new Setting(1, false, false, 0);
		statistic = new Statistic(1, 0, 5, 5);
		testMe = new Profile(1, TEST_USER_IN_DB, setting, statistic);
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
	public void testHasRecord() {
		testMe = new Profile(1);
		testMe.destroy();
		assertFalse("profil existiert noch",testMe.hasRecord());
	}
}
