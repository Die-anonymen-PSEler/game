package com.retroMachines.ui;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.retroMachines.GdxTestRunner;
import com.retroMachines.data.AssetManager;
import com.retroMachines.ui.RetroDialogChain.DialogChainFinishedListener;
import com.retroMachines.util.Constants;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

@RunWith(GdxTestRunner.class)
public class RetroDialogChainTest {
	
	RetroDialogChain chain;
	
	Stage mockedStage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AssetManager.initializePreLoading();
		AssetManager.initializeWhileLoading();
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
		chain.addDialog("title", AssetManager.getTexture(Constants.BACKGROUND_PATH));
	}
	
	@Test
	public void testShow() {
		chain.addDialog("title", AssetManager.getTexture(Constants.BACKGROUND_PATH));
		CustomStage s = new CustomStage();
		chain.show(s);
		assertTrue("sollte nur einen dialog enthalten", s.actors.size() == 1);
		Dialog dialog = ( (Dialog) s.actors.get(0));
	}
	
	@Test
	public void testListeners() {
		MockListener listener = new MockListener();
		chain.registerListener(listener);
		chain.show(new CustomStage());
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
	
	private class CustomStage extends Stage {
		
		public List<Actor> actors = new LinkedList<Actor>();
		
		public CustomStage() {
			super(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()),
					mock(SpriteBatch.class));
		}
		
		@Override
		public void addActor(Actor actor) {
			super.addActor(actor);
			actors.add(actor);
		}
	}

}
