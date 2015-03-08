package com.retroMachines.ui;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.AssetManager;

@RunWith(GdxTestRunner.class)
public class RetroDialogTest {
	
	private RetroDialog dialog;
	
	public static final String TITLE = "Toller Titel";
	
	public static final String MSG = "Noch bessere Nachricht";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializePreLoading();
		AssetManager.initializeWhileLoading();
	}
	
	@Before
	public void setUp() throws Exception {
		dialog = new RetroDialog(TITLE, MSG);
	}

	@After
	public void tearDown() throws Exception {
		dialog = null;
	}

	@Test
	public void testCorrecTitle() {
		assertTrue(dialog.getTitle().equals(TITLE));
	}
	
	@Test
	public void testResult() {
		TestGroup group = new TestGroup();
		group.addActor(dialog);
		dialog.setAutoClear(true);
		dialog.result(null);
		assertTrue("anderes objekt wollte entfernt werden", group.lastRemoveRequested == dialog);
	}
	
	@Test
	public void testRunnable() {
		TestRunnable r = new TestRunnable();
		dialog.setRunnable(r);
		dialog.result(null);
		assertTrue("runnable hätte ausgeführt werden sollen", r.didRun);
		r.didRun = false;
		dialog.setRunnable(null);
		dialog.result(null);
		assertFalse("runnable hätte nicht erneut ausgeführt werden dürfen", r.didRun);
	}
	
	@Test
	public void testAutoClear() {
		dialog.setAutoClear(false);
		dialog.result(null);
	}
	
	@Test
	public void testSecondConstructor() {
		RetroDialog retroDialog = new RetroDialog(TITLE, AssetManager.getMenuSkin());
		TestRunnable r = new TestRunnable();
		retroDialog.setRunnable(r);
		retroDialog.result(null);
		assertTrue("runnable hätte aufgerufen werden sollen", r.didRun);
	}
	
	private class TestRunnable implements Runnable {
		
		public boolean didRun;
		
		public TestRunnable() {
			didRun = false;
		}

		public void run() {
			didRun = true;
		}
		
	}
	
	private class TestGroup extends Group {
		
		public Actor lastRemoveRequested;
		
		@Override
		public boolean removeActor(Actor actor) {
			lastRemoveRequested = actor;
			return false;
		}
	}
}
