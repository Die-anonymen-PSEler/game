package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.util.MusicManager;

@RunWith(GdxTestRunner.class)
public class MainMenuScreenTest {
	
	private RetroMachines game;
	
	private MainMenuScreen screen;
	
	private static final int MAIN_TABLE_ID = 0;
	
	private static final int SIDE_TABLE_ID = 1;

	private static final Vector2 ABOUT_SCREEN_BUTTON_ID = new Vector2(SIDE_TABLE_ID, 1);
	
	private static final Vector2 PLAY_BUTTON_ID = new Vector2(MAIN_TABLE_ID, 2);

	private static final Vector2 PROFILE_BUTTON_ID = new Vector2(SIDE_TABLE_ID, 2);

	private static final Vector2 SETTING_BUTTON_ID = new Vector2(SIDE_TABLE_ID, 0);
	
	private static final Vector2 EXIT_BUTTON = new Vector2(MAIN_TABLE_ID, 0);
	
	private static final Vector2 STATISTIC_BUTTON = new Vector2(SIDE_TABLE_ID, 3);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABC");
		screen = new MainMenuScreen(game);
		game.setScreen(screen);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
		game.previousScreen();
		game = null;
		screen = null;
	}

	@Test
	public void testMusicStarted() {
		screen.show();
		MusicManager.getInstance();
	}
	
	@Test
	public void testKeyDown() {
		Screen oldScreen = game.getScreen();
		screen.keyDown(Keys.ALT_LEFT);
		assertEquals("sollte der gleiche screen sein", oldScreen, game.getScreen());
	}
	
	@Test
	public void testKeyDown2() {
		Screen oldScreen = game.getScreen();
		screen.keyDown(Keys.BACK);
		screen.keyDown(Keys.BACK);
		assertEquals("sollte der gleiche screen sein", oldScreen, game.getScreen());
	}
	
	@Test
	public void testLevelMenuScreen() {
		clickButton((int) PLAY_BUTTON_ID.x, (int) PLAY_BUTTON_ID.y);
		assertTrue("sollte level menu screen anzeigen", game.getScreen() instanceof LevelMenuScreen);
	}
	
	@Test
	public void testProfileMenuScreen() {
		clickButton((int) PROFILE_BUTTON_ID.x, (int) PROFILE_BUTTON_ID.y);
		assertTrue("sollte profil menu screen anzeigen", game.getScreen().getClass() == ProfileMenuScreen.class);
	}
	
	@Test
	public void testAboutScreen() {
		clickButton((int) ABOUT_SCREEN_BUTTON_ID.x, (int) ABOUT_SCREEN_BUTTON_ID.y);
		assertTrue("sollte profil menu screen anzeigen", game.getScreen().getClass() == AboutMenuScreen.class);
	}
	
	@Test
	public void testSettingScreen() {
		clickButton((int) SETTING_BUTTON_ID.x, (int) SETTING_BUTTON_ID.y);
		assertTrue("sollte profil menu screen anzeigen", game.getScreen().getClass() == SettingsMenuScreen.class);
	}
	
	@Test
	public void testExitButton() {
		clickButton((int) EXIT_BUTTON.x, (int) EXIT_BUTTON.y);
		clickButton((int) EXIT_BUTTON.x, (int) EXIT_BUTTON.y);
		assertTrue("sollte keinen screen aktualisieren. erst abfrage", game.getScreen() == screen);
	}
	
	@Test
	public void testStatisticButton() {
		clickButton((int) STATISTIC_BUTTON.x, (int) STATISTIC_BUTTON.y);
		assertTrue("sollte den statistic screen anzeigen", game.getScreen().getClass() == StatisticMenuScreen.class);
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
