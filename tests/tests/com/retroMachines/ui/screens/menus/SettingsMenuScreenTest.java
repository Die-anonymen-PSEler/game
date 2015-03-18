package com.retroMachines.ui.screens.menus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;

public class SettingsMenuScreenTest {
	
	private SettingsMenuScreen screen;
	
	private RetroMachines game;
	
	private static final int MAIN_TABLE = 2;
	
	private static final Vector2 LOUDER_BUTTON = new Vector2(MAIN_TABLE, 3);
	
	private static final Vector2 QUIETER_BUTTON = new Vector2(MAIN_TABLE, 1);
	
	private static final Vector2 SOUNDOFF_BUTTON = new Vector2(MAIN_TABLE, 0);
	
	private static final int MAX_VOLUME_LOOPS = 50;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new RetroMachines();
		game.create();
		game.getProfileController().createProfile("ABC");
		screen = new SettingsMenuScreen(game);
		game.setScreen(screen);
	}

	@After
	public void tearDown() throws Exception {
		game.getProfileController().deleteProfile("ABC");
	}

	@Test
	public void test() {
		//System.out.println(Arrays.toString(screen.table.getChildren().items));
	}
	
	@Test
	public void testReturn() {
		Button button = (Button) screen.table.getChildren().items[0];
		ClickListener listener = (ClickListener) button.getListeners().get(1);
		listener.clicked(null, 0, 0);
		assertFalse("sollten in anderem screen sein", screen == game.getScreen());
	}
	
	@Test
	public void testLouderButton() {
		float oldVolume = game.getSettingController().getVolume();
		clickButton((int) LOUDER_BUTTON.x, (int) LOUDER_BUTTON.y);
		assertFalse("lautstärke sollte verändert sein", oldVolume == game.getSettingController().getVolume());
	}
	
	@Test
	public void testLouderButton2() {
		float old = 0.0f;
		int counter = 0;
		while (old != game.getSettingController().getVolume() && counter < MAX_VOLUME_LOOPS) {
			old = game.getSettingController().getVolume();
			clickButton((int) LOUDER_BUTTON.x, (int) LOUDER_BUTTON.y);
			assertTrue("sollten nicht über eins gehen", old <= 1.0f);
			counter++;
		}
		if (counter == MAX_VOLUME_LOOPS) {
			fail("took to many rounds");
		}
	}
	
	@Test
	public void testQuieterButton() {
		float oldVolume = game.getSettingController().getVolume();
		clickButton( (int) QUIETER_BUTTON.x, (int) QUIETER_BUTTON.y);
		assertFalse("lautstärke sollte verändert sein", oldVolume == game.getSettingController().getVolume());
	}
	
	@Test
	public void testQuieterButton2() {
		float old = 0.0f;
		int counter = 0;
		while (old != game.getSettingController().getVolume() && counter < MAX_VOLUME_LOOPS) {
			old = game.getSettingController().getVolume();
			clickButton((int) QUIETER_BUTTON.x, (int) QUIETER_BUTTON.y);
			assertTrue("sollten nicht unter null fallen", old >= 0.0f);
			counter++;
		}
		if (counter == MAX_VOLUME_LOOPS) {
			fail("took to many rounds");
		}
	}
	
	@Test
	public void testSoundOff() {
		clickButton( (int) SOUNDOFF_BUTTON.x, (int) SOUNDOFF_BUTTON.y);
		assertTrue("volume sollte auf 0 sein", 0.0f == game.getSettingController().getVolume());
		clickButton( (int) SOUNDOFF_BUTTON.x, (int) SOUNDOFF_BUTTON.y);
		assertTrue("volume sollte größer als 0 sein", game.getSettingController().getVolume() > 0);
	}
	
	@Test
	public void testProfileSettingsMenu() {
		Button button = (Button) screen.table.getChildren().get(3);
		ClickListener listener = (ClickListener) button.getListeners().get(1);
		listener.clicked(null, 0, 0);
		assertTrue("sollten im profilesettingsmenu sein", game.getScreen().getClass() == ProfileSettingsMenuScreen.class);
	}
	
	@Test
	public void testCreate() {
		game.getSettingController().setLeftMode(true);
		game.getSettingController().setVolume(0.0f);
		new SettingsMenuScreen(game);
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
