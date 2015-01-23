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
		setting = new Setting(false, false, 0);
		statistic = new Statistic(0, 5, 5);
		testMe = new Profile("Testprofil", 1, setting, statistic);
	}
	
	/**
	 * get method check
	 */
	@Test
	public void test() {
		assertTrue("Settings nicht identisch", testMe.getSetting() == setting);
		assertTrue("Statistic nicht identisch", testMe.getStatistic() == statistic);
	}

}
