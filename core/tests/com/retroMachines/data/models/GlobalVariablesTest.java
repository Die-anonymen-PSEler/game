package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GlobalVariablesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GlobalVariables gv = GlobalVariables.getSingleton();
		assertTrue(gv.get("lastUsedProfile").equals("1"));
	}

}
