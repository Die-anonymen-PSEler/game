package com.retroMachines.game.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.data.models.Profile;

public class ProfileControllerTest {
	
	ProfileController profileController;
	
	private static final String NAME = "TESTUSER";
	
	private static final String NAME2 = "TESTUSER2";

	@Before
	public void setUp() throws Exception {
		profileController = new ProfileController(null);
		profileController.createProfile(NAME);
		profileController.createProfile(NAME2);
	}

	@After
	public void tearDown() throws Exception {
		profileController.deleteProfile(NAME2);
		profileController.deleteProfile(NAME);
		profileController = null;
	}
	
	@Test
	public void testCheckDoubleUsername() {
		profileController.canUserBeCreated(NAME);
	}
	
	@Test
	public void testListener() {
		MockListener listener = new MockListener();
		profileController.addProfileChangedListener(listener);
		profileController.createProfile("abc");
		assertTrue("mock class wurde nicht benachrichtigt", listener.callHappened);
		listener.callHappened = false;
		profileController.removeProfileChangedListener(listener);
		profileController.deleteProfile("abc");
		assertFalse("mock class wurde trotzdem benachrichtigt", listener.callHappened);
	}
	
	@Test
	public void testChangeProfile() {
		profileController.changeActiveProfile(NAME);
		assertTrue("falsches profil aktiv", profileController.getProfileName().equals(NAME));
	}
	
	@Test
	public void testGetAllProfiles() {
		String[] profileNames = profileController.getAllProfiles();
		assertTrue("falsches profil an letzter stelle", profileNames[profileNames.length - 2].equals(NAME2));
		assertTrue("falsches profil an letzter stelle", profileNames[profileNames.length - 1].equals(NAME));
	}
	
	@Test
	public void testMaximumProfiles() {
		String[] profileNames = profileController.getAllProfiles();
		for (int i = profileNames.length; i < ProfileController.MAX_PROFILE_NUMBER; i++) {
			profileController.createProfile("profil"+i);
		}
		assertFalse("nutzer kann erstellt werden", profileController.canUserBeCreated("abc"));
		for (int i = profileNames.length; i < ProfileController.MAX_PROFILE_NUMBER; i++) {
			profileController.deleteProfile("profil"+i);
		}
	}
	
	@Test
	public void testDeleteProfile() {
		profileController.createProfile("ABC");
		HashMap<String, Integer> map = profileController.getProfileNameIdMap();
		assertTrue("profil existiert nicht", map.containsKey("ABC"));
		profileController.deleteProfile("ABC");
		map = profileController.getProfileNameIdMap();
		assertFalse("profil existiert noch", map.containsKey("ABC"));
	}
	
	@Test
	public void testLoadLastProfile() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		int savedValue = Integer.parseInt(gv.get(GlobalVariables.KEY_LAST_USED_PROFILE));
		gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, -1);
		assertFalse("letzte profil sollte nicht verfügbar sein", profileController.loadLastProfile());
		gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, savedValue);
	}
	
	@Test
	public void testLoadLastProfile2() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		int savedValue = Integer.parseInt(gv.get(GlobalVariables.KEY_LAST_USED_PROFILE));
		gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, 1);
		Profile profile = new Profile(1);
		assertTrue("letztes profil sollte geladen sein", profileController.loadLastProfile());
		assertTrue("falsches profil wurde geladen", profileController.getProfile().getProfileId() == profile.getProfileId());
		gv.put(GlobalVariables.KEY_LAST_USED_PROFILE, savedValue);
	}
	
	@Test
	public void testGetProfileName() {
		profileController. deleteProfile(NAME2);
		profileController.deleteProfile(NAME);
		assertTrue("es sollte kein profil mehr existieren. der test kann sonst nicht gelingen", profileController.getAllProfiles().length == 0);
		assertTrue("profil name sollte null sein", profileController.getProfileName() == null);
		profileController.createProfile(NAME);
		assertTrue("falscher name", profileController.getProfileName().equals(NAME));
		profileController.createProfile(NAME2);
	}
	
	@Test
	public void testCreateUserNotAllowed() {
		MockListener listener = new MockListener();
		profileController.removeProfileChangedListener(listener);
		profileController.createProfile(NAME);
		assertFalse("profil scheint erstellt worden zu sein", listener.callHappened);
	}
	
	private class MockListener implements OnProfileChangedListener {
		
		public boolean callHappened;

		public void profileChanged() {
			callHappened = true;
		}
		
	}
	
}
