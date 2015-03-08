package com.retroMachines;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.retroMachines.gdxemulation.RetroApplication;
import com.retroMachines.gdxemulation.RetroFiles;
import com.retroMachines.gdxemulation.RetroGL20;
import com.retroMachines.gdxemulation.RetroGraphics;
import com.retroMachines.gdxemulation.RetroInput;
import com.retroMachines.gdxemulation.RetroMachineMock;


public class GdxTestRunner extends BlockJUnit4ClassRunner implements ApplicationListener{
	   
	   private Map<FrameworkMethod, RunNotifier> invokeInRender = new HashMap<FrameworkMethod, RunNotifier>();
	   
	   public GdxTestRunner(Class<?> klass) throws InitializationError {
	      super(klass);
	      HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
	      
	      new RetroApplication(this, conf, new RetroMachineMock());
	      Gdx.gl20 = new RetroGL20();
	      Gdx.gl = Gdx.gl20;
	      Gdx.files = new RetroFiles();
	      Gdx.graphics = new RetroGraphics();
	      Gdx.input = new RetroInput();
	   }

	   
	   public void create() {
	   }

	   
	   public void resume() {
	   }

	   
	   public void render() {
	      synchronized (invokeInRender) {
	         for(Map.Entry<FrameworkMethod, RunNotifier> each : invokeInRender.entrySet()){
        		 super.runChild(each.getKey(), each.getValue());
	         }
	         invokeInRender.clear();
	      }
	   }
	   
	   

	   
	   public void resize(int width, int height) {
	   }

	   
	   public void pause() {
	   }

	   
	   public void dispose() {
	   }

	   
	   @Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
	      synchronized (invokeInRender) {
	         //add for invoking in render phase, where gl context is available
	         invokeInRender.put(method, notifier);   
	      }
	      //wait until that test was invoked
	      waitUntilInvokedInRenderMethod();
	   }

	   /**
	    *
	    */
	   private void waitUntilInvokedInRenderMethod() {
	      try {
	         while (true){
	            Thread.sleep(10);
	            synchronized (invokeInRender) {
	               if (invokeInRender.isEmpty()) break;
	            }
	         }
	      } catch (InterruptedException e) {
	         e.printStackTrace();
	      }
	   }

	}