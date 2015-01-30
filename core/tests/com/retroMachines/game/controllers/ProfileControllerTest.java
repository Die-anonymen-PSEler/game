package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.ProfileTest;

public class ProfileControllerTest {
	
	ProfileController profileController;

	@Before
	public void setUp() throws Exception {
		profileController = new ProfileController(null);
	}

	@After
	public void tearDown() throws Exception {
		profileController = null;
	}
	
	/**
	 * check if loading the profile works
	 */
	@Test
	public void test() {
		 
		assertTrue("profile was not loaded successfully", profileController.loadLastProfile());
	}
	
	/**
	 * check if the correct profile was loaded
	 */
	@Test
	public void test2() {
		profileController.loadLastProfile();
		assertTrue("falscher benutzer wurde geladen.", profileController.getProfileName().equals(ProfileTest.TEST_USER_IN_DB));
	}

}
