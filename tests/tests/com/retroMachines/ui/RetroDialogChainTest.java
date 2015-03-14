package com.retroMachines.ui;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.ui.RetroDialogChain.DialogChainFinishedListener;
import com.retroMachines.util.Constants;

@RunWith(GdxTestRunner.class)
public class RetroDialogChainTest {
	
	RetroDialogChain chain;
	
	Stage mockedStage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RetroAssetManager.initializePreLoading();
		RetroAssetManager.initializeWhileLoading();
	}
	
	@Before
	public void setUp() throws Exception {
		chain = new RetroDialogChain();
		mockedStage = mock(Stage.class);
	}

	@After
	public void tearDown() throws Exception {
		chain = null;
	}

	@Test
	public void testAddDialog() {
		chain.addDialog("title", RetroAssetManager.getTexture(Constants.BACKGROUND_PATH));
	}
	
	@Test
	public void testShow() {
		chain.addDialog("title", RetroAssetManager.getTexture(Constants.BACKGROUND_PATH));
		Stage s = new Stage();
		chain.show(s);
		assertTrue("sollte nur einen dialog enthalten", s.getActors().size == 1);
		assertTrue("sollte ein dialog sein", s.getActors().get(0) instanceof Dialog);
		Dialog dialog = (Dialog) s.getActors().get(0);
		assertTrue("sollte einen Button enthalten", dialog.getButtonTable().getChildren().get(0).getClass() == Button.class);
		Button button = (Button) dialog.getButtonTable().getChildren().get(0);
		assertTrue("sollte kind von clicklistener sein", button.getListeners().get(0) instanceof ClickListener);
		ClickListener listener = (ClickListener) button.getListeners().get(0);
		listener.clicked(null, 0, 0);
	}
	
	@Test
	public void testListeners() {
		MockListener listener = new MockListener();
		chain.registerListener(listener);
		chain.show(new Stage());
		assertTrue("listener hätte benachrichtigt werden sollen", listener.callHappened);
	}
	
	private class MockListener implements DialogChainFinishedListener {
		
		public boolean callHappened;
		
		public MockListener() {
			callHappened = false;
		}
		
		public void dialogFinished() {
			callHappened = true;
		}
	};
}
