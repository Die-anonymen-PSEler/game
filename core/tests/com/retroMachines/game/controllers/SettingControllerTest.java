package com.retroMachines.game.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.Setting;
import com.retroMachines.data.models.SettingsChangeListener;

public class SettingControllerTest {

	private SettingController settingController;
	@Before
	public void setUp() throws Exception {
		settingController = new SettingController(null);
		try {
			settingController.initialize();
		} catch (NullPointerException e) {
			// game is not initialized. calls to it are bad 
		}
		
	}

	@After
	public void tearDown() throws Exception {
		settingController = null;
	}

	@Test
	public void testListener() {
		Setting setting = new Setting();
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
		setting.destroy();
	}
	
	private class MockListener implements SettingsChangeListener {
		
		public boolean callHappened;
		
		@Override
		public void onSettingsChanged() {
			callHappened = true;
		}
		
	}

}
