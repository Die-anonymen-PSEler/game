package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.menus.MenuScreen.ReturnButtonClickListener;

public class StatisticMenuScreenTest {
	
	private StatisticMenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABC");
		screen = new StatisticMenuScreen(game);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
	}

	@Test
	public void testReturnButtonClick() {
		Actor[] actors = screen.table.getChildren().items;
		assertTrue("sollte ein button sein", actors[0] instanceof Button);
		Button button = (Button) actors[0];
		assertTrue("sollte returnbuttonclickedlistener sein", button.getListeners().get(1) instanceof ReturnButtonClickListener);
		ReturnButtonClickListener listener = (ReturnButtonClickListener) button.getListeners().get(1);
		Screen oldScreen = game.getScreen();
		game.setScreen(screen);
		listener.clicked(null, 0, 0);
		assertEquals("sollten die selben screens sein", oldScreen, game.getScreen());
	}

}
