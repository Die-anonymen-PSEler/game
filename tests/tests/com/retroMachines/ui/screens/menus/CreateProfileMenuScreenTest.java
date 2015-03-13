package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.models.Setting;

public class CreateProfileMenuScreenTest {
	
	private CreateProfileMenuScreen screen;
	
	private RetroMachines game;
	
	private static final int BUTTON_TABLE_ID = 3;
	
	private static final int RIGHT_TABLE_ID = 2;
	
	private static final int IMAGE_TABLE_ID = 1;
	
	private static final Vector2 NEXT_CHAR_BUTTON = new Vector2(IMAGE_TABLE_ID, 0);
	
	private static final Vector2 PROFILE_NAME_INPUT_FIELD = new Vector2(RIGHT_TABLE_ID, 1);
	
	private static final Vector2 OK_BUTTON = new Vector2(BUTTON_TABLE_ID, 1);
	
	private static final Vector2 ABORT_BUTTON = new Vector2(BUTTON_TABLE_ID, 0);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABC");
		screen = new CreateProfileMenuScreen(game);
		game.setScreen(screen);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
	}

	@Test
	public void testAbortCreateProfile() {
		Screen oldScreen = game.getScreen();
		clickButton((int) ABORT_BUTTON.x, (int) ABORT_BUTTON.y);
		assertFalse("sollte den create profile screen nicht mehr anzeigen", game.getScreen() == oldScreen);
	}
	
	@Test
	public void testAbortCreateProfile2() {
		HashMap<String, Integer> map = game.getProfileController().getProfileNameIdMap();
		for (String name : map.keySet()) {
			game.getProfileController().deleteProfile(name);
		}
		Screen oldScreen = game.getScreen();
		clickButton((int) ABORT_BUTTON.x, (int) ABORT_BUTTON.y);
		assertTrue("sollte den create profile screen nicht mehr anzeigen", game.getScreen() == oldScreen);
	}
	
	@Test
	public void testAcceptProfile() {
		Screen oldScreen = game.getScreen();
		clickButton((int) OK_BUTTON.x, (int) OK_BUTTON.y);
		assertTrue("sollte weiterhin den profilescreen anzeigen", game.getScreen() == oldScreen);
	}
	
	@Test
	public void testCreateProfile() {
		clickButton((int) NEXT_CHAR_BUTTON.x, (int) NEXT_CHAR_BUTTON.y);
		clickButton((int) NEXT_CHAR_BUTTON.x, (int) NEXT_CHAR_BUTTON.y);
		Table table = (Table) screen.table.getChildren().get((int)PROFILE_NAME_INPUT_FIELD.x);
		TextField textField = (TextField) table.getChildren().get((int) PROFILE_NAME_INPUT_FIELD.y);
		String name = "asdfasdfay";
		textField.setText(name);
		clickButton((int) OK_BUTTON.x, (int) OK_BUTTON.y);
		assertTrue("sollte das neue profil enthalten", game.getProfileController().getProfileNameIdMap().containsKey(name));
		assertFalse("sollte nicht die standard character id sein", game.getSettingController().getCurrentCharacterId() == Setting.DEFAULT_SELECTEDCHARACTER);
		game.getProfileController().deleteProfile(name);
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
}
