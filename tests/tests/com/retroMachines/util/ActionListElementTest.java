package com.retroMachines.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActionListElementTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ActionListElement element = new ActionListElement(null, null);
		assertNull("sollte keine action sein", element.getAction());
		assertNull("sollte keine gameelemenet sein sein", element.getGameElement());
	}

}
