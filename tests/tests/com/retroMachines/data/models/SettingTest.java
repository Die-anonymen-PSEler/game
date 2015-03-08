package com.retroMachines.data.models;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SettingTest {
	
	private static final int TEST_ID = 1;
	
	private static final float TEST_VOLUME = 0.5f;
	
	private static final boolean TEST_SOUNDONOFF = true;
	
	private static final boolean TEST_LEFT = false;
	
	
	/**
	 * check if values are assigned correctly
	 */
	@Test
	public void testCreateSetting() {
		Setting setting = new Setting(TEST_ID, TEST_LEFT, TEST_SOUNDONOFF, TEST_VOLUME);
		assertTrue("volume is wrong", TEST_VOLUME == setting.getVolume());
		assertTrue("soundonoff is wrong", TEST_SOUNDONOFF == setting.isSoundOnOff());
		assertTrue("left is wrong", TEST_LEFT == setting.isLeftControl());
		setting.destroy();
	}
	
	/**
	 * check db fetch
	 */
	@Test
	public void testDBFetch() {
		Setting setting = new Setting(TEST_ID);
		setting.setVolume(TEST_VOLUME);
		setting.setSoundOnOff(TEST_SOUNDONOFF);
		setting.setLeftControl(TEST_LEFT);
		assertTrue("voluem is wrong", TEST_VOLUME == setting.getVolume());
		assertTrue("leftcontrol is wrong", TEST_LEFT == setting.isLeftControl());
		assertTrue("sound is wrong", TEST_SOUNDONOFF == setting.isSoundOnOff());
	}
	
	/**
	 * check writeBack to db
	 */
	@Test
	public void testWriteBack() {
		Setting setting = new Setting(TEST_ID);
		setting.setVolume(0.2f);
		Setting setting2 = new Setting(1);
		assertTrue("wrong volume was written back", setting2.getVolume() == 0.2f);
		setting2.setVolume(TEST_VOLUME); // write the old value so other tests aren't confused.
	}
	
	@Test
	public void testSetTutorialFinished() {
		Setting setting = new Setting(TEST_ID);
		setting.setTutorialFinished(0, true);
		assertTrue(setting.getTutorialFinished(0));
		setting.setTutorialFinished(0, false);
	}
}
