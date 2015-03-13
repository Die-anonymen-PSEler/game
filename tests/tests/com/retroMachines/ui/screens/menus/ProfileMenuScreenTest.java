package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.controllers.ProfileController;

public class ProfileMenuScreenTest {
	
	private static final int BUTTON_TABLE_ID = 3;
	
	private static final Vector2 DELETE_BUTTON = new Vector2(BUTTON_TABLE_ID, 0);
	
	private static final Vector2 ADD_BUTTON = new Vector2(BUTTON_TABLE_ID, 1);
	
	private static final Vector2 SELECT_BUTTON = new Vector2(BUTTON_TABLE_ID, 2);
	
	private ProfileMenuScreen screen;
	
	private RetroMachines game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		HashMap<String, Integer> map = game.getProfileController().getProfileNameIdMap();
		for (String name : map.keySet()) {
			game.getProfileController().deleteProfile(name);
		}
		screen = new ProfileMenuScreen(game);
		game.setScreen(screen);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddProfile() {
		clickButton((int) ADD_BUTTON.x, (int) ADD_BUTTON.y);
		assertTrue("sollte im createprofilescreen sein", game.getScreen().getClass() == CreateProfileMenuScreen.class);
	}
	
	@Test
	public void testAddProfile2() {
		ProfileController controller = game.getProfileController();
		controller.createProfile("a");
		controller.createProfile("b");
		controller.createProfile("c");
		controller.createProfile("d");
		controller.createProfile("e");
		clickButton((int) ADD_BUTTON.x, (int) ADD_BUTTON.y);
		assertTrue("sollte im createprofilescreen sein", game.getScreen() == screen);
	}
	
	@Test
	public void testSelectProfile() {
		game.getProfileController().createProfile("ABC");
		game.getProfileController().createProfile("Maik");
		selectProfile("ABC");
		clickButton((int) SELECT_BUTTON.x, (int) SELECT_BUTTON.y);
		assertEquals("benutzer abc sollte ausgewählt sein", game.getProfileController().getProfileName(), "ABC");
		game.getProfileController().deleteProfile("ABC");
		game.getProfileController().deleteProfile("Maik");
	}
	
	@Test
	public void testDeleteProfile() {
		game.getProfileController().createProfile("ABC");
		game.getProfileController().createProfile("DEF");
		selectProfile("ABC");
		clickButton((int) DELETE_BUTTON.x, (int) DELETE_BUTTON.y);
		clickButton((int) DELETE_BUTTON.x, (int) DELETE_BUTTON.y);
		game.getProfileController().deleteProfile("ABC");
		game.getProfileController().deleteProfile("DEF");
	}
	
	@Test
	public void testReturnButton() {
		Button button = (Button) screen.table.getChildren().get(0);
		ClickListener listener = (ClickListener) button.getListeners().get(1);
		listener.clicked(null, 0, 0);
		assertTrue("sollte im main menu sein", game.getScreen().getClass() == MainMenuScreen.class);
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
	
	private void selectProfile(String name) {
		ScrollPane pane = (ScrollPane) screen.table.getChildren().items[2];
		Table table = (Table) pane.getChildren().get(0);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) table.getChildren().get(0);
		list.setSelected("ABC");
	}
}
