package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.retroMachines.RetroMachines;

public class ProfileMenuScreenTest {
	
	private ProfileMenuScreen screen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		RetroMachines game = new RetroMachines();
		game.create();
		screen = new ProfileMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
	}

}
