package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.game.controllers.ProfileController;

@RunWith(GdxTestRunner.class)
public class GlobalVariablesTest {
	
	private static final String KEY_LASTUSEDPROFILE = "lastUsedProfile";
	
	private static final String VALUE_LASTUSEDPROFILE = "-1";

	@Before
	public void setUp() throws Exception {
		GlobalVariables.getSingleton();		// initialize the class
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCorrectValue() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		assertTrue("wert stimmt nicht mit default wert in dieser klasse überein", gv.get(KEY_LASTUSEDPROFILE).equals(VALUE_LASTUSEDPROFILE));
	}
	
	@Test
	public void testAssignValue() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		gv.put(KEY_LASTUSEDPROFILE, "abc123");
		assertTrue("falsche daten zurück geschrieben", gv.get(KEY_LASTUSEDPROFILE).equals("abc123"));
		gv.put(KEY_LASTUSEDPROFILE, VALUE_LASTUSEDPROFILE);
	}
	
	@Test
	public void testUnusedMethods() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		gv.hasRecord();
		gv.fetch();
		gv.destroy();
	}
	
	@Test
	public void testNextFreeId() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		gv.nextFreeId();
		for (int i = 1; i <= ProfileController.MAX_PROFILE_NUMBER; i++) {
			gv.put(String.format(GlobalVariables.KEY_SLOTS, i), "Ein String");
		}
		assertEquals("sollte -1 sein", -1, gv.nextFreeId());
		for (int i = 1; i <= ProfileController.MAX_PROFILE_NUMBER; i++) {
			gv.put(String.format(GlobalVariables.KEY_SLOTS, i), 0);
		}
	}

}
