package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.Profile;
import com.retroMachines.data.models.ProfileTest;
import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.Statistic;

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
	
	/**
	 * check if two profiles with the same name can be created
	 */
	@Test
	public void test3() {
		profileController.createProfile("name");
		profileController.changeActiveProfile("name");
		assertFalse("profil mit gleichem Namen möglich", profileController.canUserBeCreated("name"));
		profileController.changeActiveProfile("TestUser");
		profileController.deleteProfile("name");
	}
	
	/**
	 * check if more than 5 profiles can be created
	 */
	@Test
	public void test4() {
		profileController.createProfile("name2");
		profileController.createProfile("name3");
		profileController.createProfile("name4");
		profileController.createProfile("name5");
		assertFalse("mehr als 5 profile möglich", profileController.canUserBeCreated("name6"));
		profileController.changeActiveProfile("TestUser");
		profileController.deleteProfile("name2");
		profileController.deleteProfile("name3");
		profileController.deleteProfile("name4");
		profileController.deleteProfile("name5");
	}

}
