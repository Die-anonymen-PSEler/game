package com.retroMachines.gdxemulation;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;

public class RetroGraphics implements Graphics{

	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;

	@Override
	public boolean isGL30Available() {

		return false;
	}

	@Override
	public GL20 getGL20() {

		return null;
	}

	@Override
	public GL30 getGL30() {

		return null;
	}

	@Override
	public int getWidth() {

		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public long getFrameId() {

		return 0;
	}

	@Override
	public float getDeltaTime() {

		return 0.1f;
	}

	@Override
	public float getRawDeltaTime() {

		return 0;
	}

	@Override
	public int getFramesPerSecond() {

		return 0;
	}

	@Override
	public GraphicsType getType() {

		return null;
	}

	@Override
	public float getPpiX() {

		return 0;
	}

	@Override
	public float getPpiY() {

		return 0;
	}

	@Override
	public float getPpcX() {

		return 0;
	}

	@Override
	public float getPpcY() {

		return 0;
	}

	@Override
	public float getDensity() {

		return 0;
	}

	@Override
	public boolean supportsDisplayModeChange() {

		return false;
	}

	@Override
	public DisplayMode[] getDisplayModes() {

		return null;
	}

	@Override
	public DisplayMode getDesktopDisplayMode() {

		return null;
	}

	@Override
	public boolean setDisplayMode(DisplayMode displayMode) {

		return false;
	}

	@Override
	public boolean setDisplayMode(int width, int height, boolean fullscreen) {

		return false;
	}

	@Override
	public void setTitle(String title) {

		
	}

	@Override
	public void setVSync(boolean vsync) {

		
	}

	@Override
	public BufferFormat getBufferFormat() {

		return null;
	}

	@Override
	public boolean supportsExtension(String extension) {

		return false;
	}

	@Override
	public void setContinuousRendering(boolean isContinuous) {

		
	}

	@Override
	public boolean isContinuousRendering() {

		return false;
	}

	@Override
	public void requestRendering() {

		
	}

	@Override
	public boolean isFullscreen() {

		return false;
	}

}
