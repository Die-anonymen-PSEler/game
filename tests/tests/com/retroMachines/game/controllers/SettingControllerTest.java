package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.SettingsChangeListener;
import com.retroMachines.util.Constants;

public class SettingControllerTest {

	private SettingController settingController;
	
	private Setting setting;
	
	@Before
	public void setUp() throws Exception {
		settingController = new SettingController(null);
		setting = new Setting(1);
		try {
			settingController.initialize();
		} catch (NullPointerException e) {
			// game is not initialized. calls to it are bad 
		}
		settingController.setSetting(setting);
	}

	@After
	public void tearDown() throws Exception {
		setting.destroy();
		settingController = null;
	}
	
	@Test
	public void testToggle() {
		int oldId = settingController.getCurrentCharacterId();
		settingController.toggleCharacter();
		assertFalse("immer noch selbe id", settingController.getCurrentCharacterId() == oldId);
	}
	
	@Test
	public void testVolumeDisabled() {
		settingController.setVolume(0.0f);
		assertFalse("ton ist immer noch an", settingController.soundEnabled());
	}
	
	@Test
	public void testSoundEnabled() {
		settingController.setVolume(0.5f);
		assertTrue("ton ist aus", settingController.soundEnabled());
	}

	@Test
	public void testListener() {
		settingController.setSetting(setting);
		MockListener mockListener = new MockListener();
		settingController.add(mockListener);
		settingController.setVolume(0.9f);
		assertTrue("methode wurde nicht aufgerufen", mockListener.callHappened);
		mockListener.callHappened = false;
		settingController.setLeftMode(true);
		assertTrue("methode wurde nicht aufgerufen", mockListener.callHappened);
		settingController.removeListener(mockListener);
		mockListener.callHappened = false;
		settingController.setVolume(0.5f);
		assertFalse("methode des mock listeners wurde aufgerufen", mockListener.callHappened);
	}
	
	@Test
	public void testResetTutorial() {
		settingController.resetTutorials();
		for (int i = 1; i <= Constants.MAX_LEVEL_ID; i++) {
			assertFalse(setting.getTutorialFinished(i));
		}
		for (int i = 1; i <= Constants.MAX_LEVEL_ID; i++) {
			assertFalse(settingController.getTutorialFinished(i));
		}
	}
	
	@Test
	public void testGetVolume() {
		setting.setVolume(0.8f);
		assertTrue("wrong volume", settingController.getVolume() == 0.8f);
		settingController.setVolume(0.3f);
		assertTrue("wrong volume", setting.getVolume() == 0.3f);
		assertTrue("wrong volume", settingController.getVolume() == 0.3f);
	}
	
	@Test
	public void testGetLeftMode() {
		setting.setLeftControl(false);
		assertFalse("should be disabled", settingController.getLeftMode());
		setting.setLeftControl(true);
		assertTrue("should be enabled", settingController.getLeftMode());
		settingController.setLeftMode(true);
		assertTrue("should be enabled", settingController.getLeftMode());
		settingController.setLeftMode(false);
		assertFalse("should be disabled", settingController.getLeftMode());
		settingController.setLeftMode(true);
		assertTrue("should be enalbed", settingController.getLeftMode());
	}
	
	@Test
	public void testSetCharacter() {
		int characterId = Constants.TEXTURE_ANIMATION_NAMES.length - 1;
		settingController.setCharacterId(characterId);
		assertTrue("falsche character id", settingController.getCurrentCharacterId() == characterId);
		try {
			settingController.setCharacterId(-1);
			fail("Exception should be thrown");
		} catch (IllegalArgumentException e) {
			// we expected this exception
		}
	}
	
	@Test
	public void testToggleCharacter() {
		int oldValue;
		for (int i = 0; i < Constants.TEXTURE_ANIMATION_NAMES.length; i++) {
			oldValue = settingController.getCurrentCharacterId();
			settingController.toggleCharacter();
			assertFalse("character id ist unverändert",settingController.getCurrentCharacterId() == oldValue);
		}
	}
	
	@Test
	public void testSetTutorialFinished() {
		boolean value = !setting.getTutorialFinished(1);
		settingController.setTutorialFinished(1, value);
		assertTrue("sollten übereinstimmen", value == settingController.getTutorialFinished(1));
	}
	
	private class MockListener implements SettingsChangeListener {
		
		public boolean callHappened;
		
		public void onSettingsChanged() {
			callHappened = true;
		}
		
	}

}
