package com.retroMachines.util;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConstantsTest extends Constants {

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
	public void testButtonStrings() {
		// it's a class with constants. what did you expect?
		Field[] strings = Constants.ButtonStrings.class.getFields();
		for (Field f : strings) {
			assertTrue("sollte nur strings enthalten", f.getType() == String.class);
			try {
				String value = (String) f.get(new String());
				assertNotSame("Sollte kein leerer String sein", "", value);
			} catch (IllegalArgumentException e) {
				fail("no exception expected");
			} catch (IllegalAccessException e) {
				fail("no exception expected");
			}
		}
		
		ButtonStrings buttonStrings = new ButtonStrings() {
		};
	}
	
	@Test
	public void testStrings() {
		// making sure retrostrings only contains strings
		Field[] strings = Constants.RetroStrings.class.getFields();
		for (Field f : strings) {
			assertTrue("sollte nur strings enthalten", f.getType() == String.class);
			try {
				String value = (String) f.get(new String());
				assertNotSame("Sollte kein leerer String sein", "", value);
			} catch (IllegalArgumentException e) {
				fail("no exception expected");
			} catch (IllegalAccessException e) {
				fail("no exception expected");
			}
		}
		
		RetroStrings buttonStrings = new RetroStrings() {
		};
	}

}
