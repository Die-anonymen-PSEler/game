package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.data.models.Profile;
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
	public void testRightLoading() {
		profileController.loadLastProfile();
		assertTrue("falscher benutzer wurde geladen.", profileController.getProfileName().equals(ProfileTest.TEST_USER_IN_DB));
		GlobalVariables gv = GlobalVariables.getSingleton();
		gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, -1);
		assertFalse("laden des benutzers sollte scheitern", profileController.loadLastProfile());
		gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, ProfileTest.TEST_USER_IN_DB_ID);
	}
	
	@Test
	public void testCreateDeleteProfile() {
		profileController.createProfile("Test");
		assertTrue("falsches profil ist aktiv", profileController.getProfileName().equals("Test"));
		Profile profile = profileController.getProfile();
		profileController.deleteProfile("Test");
		assertFalse("statistic wurde nicht vernichtet", profile.getStatistic().hasRecordInSQL());
		assertFalse("setting wurde nicht vernichtet", profile.getSetting().hasRecordInSQL());
		assertFalse("profil wurde nicht vernichtet", profile.hasRecordInSQL());
	}
	
	@Test
	public void testChangeOnProfileCreate() {
		profileController.loadLastProfile();
		int prevId = profileController.getProfile().getProfileId();
		profileController.createProfile("Luca");
		assertFalse("profil wurde nicht gewechselt", profileController.getProfile().getProfileId() == prevId);
		assertTrue("falscher profilname", profileController.getProfileName().equals("Luca"));
		profileController.deleteProfile("Luca");
	}
	
	@Test
	public void testChangeProfile() {
		profileController.loadLastProfile();
		profileController.createProfile("Maik");
		GlobalVariables gv = GlobalVariables.getSingleton();
		assertFalse("db last used profile wurde nicht geändert", 
				Integer.parseInt(gv.get(GlobalVariables.KEY_LAST_USED_PROFILE)) == ProfileTest.TEST_USER_IN_DB_ID);
		profileController.changeActiveProfile(ProfileTest.TEST_USER_IN_DB);
		assertTrue("db last used profile wurde nicht geändert", 
				Integer.parseInt(gv.get(GlobalVariables.KEY_LAST_USED_PROFILE)) == ProfileTest.TEST_USER_IN_DB_ID);
		assertTrue("falsches profil aktiv", profileController.getProfileName().equals(ProfileTest.TEST_USER_IN_DB));
		profileController.deleteProfile("Maik");
		assertTrue("standard user sollte aktiv sein", 
				profileController.getProfileName().equals(ProfileTest.TEST_USER_IN_DB));
	}
	
	@Test
	public void testListener() {
		MockListener listener = new MockListener();
		profileController.addProfileChangedListener(listener);
		profileController.createProfile("Larissa");
		assertTrue("mock listener wurde nicht informiert", listener.callHappened);
		listener.callHappened = false;
		profileController.removeProfileChangedListener(listener);
		profileController.deleteProfile("Larissa");
		assertFalse("mock listener wurde nicht informiert", listener.callHappened);
		listener.callHappened = false;
	}
	
	private class MockListener implements OnProfileChangedListener {
		
		public boolean callHappened;

		@Override
		public void profileChanged() {
			callHappened = true;
		}
		
	}
}
