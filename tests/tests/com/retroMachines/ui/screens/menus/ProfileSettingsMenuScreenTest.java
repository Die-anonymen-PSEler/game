package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

public class ProfileSettingsMenuScreenTest {
	
	private static final int IMAGE_TABLE_ID = 1;
	
	private static final int RIGHT_TABLE_ID = 2;
	
	private static final int BUTTON_TABLE_ID = 3;
	
	private static final Vector2 NEXT_CHAR_BUTTON = new Vector2(IMAGE_TABLE_ID, 0);
	
	private static final Vector2 OK_BUTTON = new Vector2(BUTTON_TABLE_ID, 0);
	
	private static final Vector3 LEFT_BUTTON = new Vector3(RIGHT_TABLE_ID, 2, 0);
	
	private static final Vector3 RIGHT_BUTTON = new Vector3(RIGHT_TABLE_ID, 2, 1);
	
	private static final Vector3 TUT_RESET_BUTTON = new Vector3(RIGHT_TABLE_ID, 3, 1);
	
	private ProfileSettingsMenuScreen screen;
	
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
		game.getProfileController().createProfile("maik");
		screen = new ProfileSettingsMenuScreen(game);
		game.setScreen(screen);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("maik");
	}
	
	@Test
	public void testNextChar() {
		int oldCharId = game.getSettingController().getCurrentCharacterId();
		clickButton((int) NEXT_CHAR_BUTTON.x, (int) NEXT_CHAR_BUTTON.y);
		assertNotSame("id sollte getoggled sein", oldCharId, game.getSettingController().getCurrentCharacterId());
	}
	
	@Test
	public void testLeftButton() {
		game.getSettingController().setLeftMode(false);
		clickButton((int) LEFT_BUTTON.x, (int) LEFT_BUTTON.y, (int) LEFT_BUTTON.z);
		assertTrue("sollte im left mode sein", game.getSettingController().isLeftMode());
	}
	
	@Test
	public void testRightButton() {
		game.getSettingController().setLeftMode(true);
		clickButton((int) RIGHT_BUTTON.x, (int) RIGHT_BUTTON.y, (int) RIGHT_BUTTON.z);
		assertFalse("sollte im right mode sein", game.getSettingController().isLeftMode());
	}
	
	@Test
	public void testResetButton() {
		game.getSettingController().setTutorialFinished(3, true);
		clickButton((int) TUT_RESET_BUTTON.x, (int) TUT_RESET_BUTTON.y, (int) TUT_RESET_BUTTON.z);
		assertFalse("tutorial sollte noch nicht fertig sein", game.getSettingController().isTutorialFinished(3));
	}
	
	@Test
	public void testAcceptButton() {
		clickButton((int) OK_BUTTON.x, (int) OK_BUTTON.y);
		assertTrue("sollten im mainmenuscreen sein", game.getScreen().getClass() == MainMenuScreen.class);
	}
	
	@Test
	public void testCreationWithLeftMode() {
		game.getSettingController().setLeftMode(true);
		new ProfileSettingsMenuScreen(game);
	}
	
	private void clickButton(int tableId, int idWithinTable) {
		Actor[] actors = screen.table.getChildren().items;
		assertTrue("sollte der main table sein", actors[tableId].getClass() == Table.class);
		Table table = (Table) actors[tableId];
		assertTrue("sollte ein button sein", table.getChildren().items[idWithinTable].getClass() == Button.class);
		Button buttonProfile = (Button) table.getChildren().get(idWithinTable);
		ClickListener listener = (ClickListener) buttonProfile.getListeners().get(1);
		listener.clicked(null, 0, 0);
	}
	
	private void clickButton(int firstLevel, int secondLevel, int thirdLevel) {
		Actor[] actors = screen.table.getChildren().items;
		assertTrue("sollte der main table sein", actors[firstLevel].getClass() == Table.class);
		Table table = (Table) actors[firstLevel];
		assertTrue("sollte ein table sein", table.getChildren().get(secondLevel).getClass() == Table.class);
		table = (Table) table.getChildren().get(secondLevel);
		assertTrue("sollte ein button sein", table.getChildren().items[thirdLevel].getClass() == Button.class);
		Button buttonProfile = (Button) table.getChildren().get(thirdLevel);
		ClickListener listener = (ClickListener) buttonProfile.getListeners().get(1);
		listener.clicked(null, 0, 0);
	}
}
