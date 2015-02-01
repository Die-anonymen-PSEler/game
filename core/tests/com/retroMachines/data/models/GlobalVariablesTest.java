package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.retroMachines.data.models.GlobalVariables;

public class GlobalVariablesTest {
	
	private static final String KEY_LASTUSEDPROFILE = "lastUsedProfile";
	
	private static final String VALUE_LASTUSEDPROFILE = "1";

	@Before
	public void setUp() throws Exception {
		GlobalVariables.getSingleton();		// initialize the class
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		assertTrue("wert stimmt nicht mit default wert in dieser klasse überein", gv.get(KEY_LASTUSEDPROFILE).equals(VALUE_LASTUSEDPROFILE));
	}
	
	@Test
	public void assignValue() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		gv.put(KEY_LASTUSEDPROFILE, "abc123");
		assertTrue("falsche daten zurück geschrieben", gv.get(KEY_LASTUSEDPROFILE).equals("abc123"));
		gv.put(KEY_LASTUSEDPROFILE, VALUE_LASTUSEDPROFILE);
	}

}
