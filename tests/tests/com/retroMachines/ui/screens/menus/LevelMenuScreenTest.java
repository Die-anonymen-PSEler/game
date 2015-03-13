package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.ui.screens.game.GameScreen;

public class LevelMenuScreenTest {
	
	private static final int LEVEL_BUTTON_TABLE_ID = 2;
	
	private LevelMenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		HashMap<String, Integer> map = game.getProfileController().getProfileNameIdMap();
		for (String name : map.keySet()) {
			game.getProfileController().deleteProfile(name);
		}
		game.getProfileController().createProfile("ABD");
		screen = new LevelMenuScreen(game);
		game.setScreen(screen);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABD");
	}

	@Test
	public void testReturnButton() {
		Button buttonReturn = (Button) screen.table.getChildren().items[0];
		ClickListener listener = (ClickListener) buttonReturn.getListeners().get(1);
		listener.clicked(null, 0, 0);
		assertTrue("sollten uns im MainMenuScreen befinden", game.getScreen().getClass() == MainMenuScreen.class);
	}
	
	@Test
	public void testLevelNotUnlocked() {
		clickTextButton(LEVEL_BUTTON_TABLE_ID, 1);
		assertEquals("sollten im selben screen sein", screen, game.getScreen());
	}
	
	@Test
	public void testLevelUnlocked() {
		clickTextButton(LEVEL_BUTTON_TABLE_ID, 0);
		assertTrue("sollten im gamescreen sein", GameScreen.class == game.getScreen().getClass());
	}
	
	private void clickButton(int tableId, int idWithinTable) {
		Actor[] actors = screen.table.getChildren().items;
		assertTrue("sollte der main table sein", actors[tableId].getClass() == Table.class);
		Table table = (Table) actors[tableId];
		assertTrue("sollte ein button (profile) sein", table.getChildren().items[idWithinTable].getClass() == Button.class);
		Button buttonProfile = (Button) table.getChildren().get(idWithinTable);
		ClickListener listener = (ClickListener) buttonProfile.getListeners().get(1);
		listener.clicked(null, 0, 0);
	}
	
	private void clickTextButton(int tableId, int idWithinTable) {
		Actor[] actors = screen.table.getChildren().items;
		assertTrue("sollte der main table sein", actors[tableId].getClass() == Table.class);
		Table table = (Table) actors[tableId];
		assertTrue("sollte ein button (profile) sein", table.getChildren().items[idWithinTable].getClass() == TextButton.class);
		TextButton buttonProfile = (TextButton) table.getChildren().get(idWithinTable);
		ClickListener listener = (ClickListener) buttonProfile.getListeners().get(1);
		listener.clicked(null, 0, 0);
	}
}
