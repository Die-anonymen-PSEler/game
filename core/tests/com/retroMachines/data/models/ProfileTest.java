package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {
	
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
		testMe = new Profile("Testprofil", 1, setting, statistic);
		assertTrue("Settings nicht identisch", testMe.getSetting() == setting);
		assertTrue("Statistic nicht identisch", testMe.getStatistic() == statistic);
		assertTrue("id stimmt nicht", testMe.getProfileId() == 1);
	}
	
	/**
	 * profile id = 1 should be named "TestUser"
	 */
	@Test
	public void testDBFetch() {
		testMe = new Profile(1);
		assertTrue("profil name stimmt nicht", testMe.getProfileName().equals("TestUser"));
	}
}
