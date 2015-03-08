package com.retroMachines.gdxemulation;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;

public class RetroInput implements Input {
	
	private List<Integer> pressedKeys;
	
	public RetroInput() {
		pressedKeys = new ArrayList<Integer>();
	}
	
	public void addPressedKey(int id) {
		if (!pressedKeys.contains(id)) {
			pressedKeys.add((Integer)id);
		}
	}
	
	public void removePressedKey(int id) {
		if (pressedKeys.contains(id)) {
			pressedKeys.remove((Integer)id);
		}
	}

	@Override
	public float getAccelerometerX() {

		return 0;
	}

	@Override
	public float getAccelerometerY() {

		return 0;
	}

	@Override
	public float getAccelerometerZ() {

		return 0;
	}

	@Override
	public int getX() {

		return 0;
	}

	@Override
	public int getX(int pointer) {

		return 0;
	}

	@Override
	public int getDeltaX() {

		return 0;
	}

	@Override
	public int getDeltaX(int pointer) {

		return 0;
	}

	@Override
	public int getY() {

		return 0;
	}

	@Override
	public int getY(int pointer) {

		return 0;
	}

	@Override
	public int getDeltaY() {

		return 0;
	}

	@Override
	public int getDeltaY(int pointer) {

		return 0;
	}

	@Override
	public boolean isTouched() {

		return false;
	}

	@Override
	public boolean justTouched() {

		return false;
	}

	@Override
	public boolean isTouched(int pointer) {

		return false;
	}

	@Override
	public boolean isButtonPressed(int button) {

		return false;
	}

	@Override
	public boolean isKeyPressed(int key) {
		return pressedKeys.contains(key);
	}

	@Override
	public boolean isKeyJustPressed(int key) {

		return false;
	}

	@Override
	public void getTextInput(TextInputListener listener, String title,
			String text, String hint) {

		
	}

	@Override
	public void setOnscreenKeyboardVisible(boolean visible) {

		
	}

	@Override
	public void vibrate(int milliseconds) {

		
	}

	@Override
	public void vibrate(long[] pattern, int repeat) {

		
	}

	@Override
	public void cancelVibrate() {

		
	}

	@Override
	public float getAzimuth() {

		return 0;
	}

	@Override
	public float getPitch() {

		return 0;
	}

	@Override
	public float getRoll() {

		return 0;
	}

	@Override
	public void getRotationMatrix(float[] matrix) {

		
	}

	@Override
	public long getCurrentEventTime() {

		return 0;
	}

	@Override
	public void setCatchBackKey(boolean catchBack) {

		
	}

	@Override
	public boolean isCatchBackKey() {

		return false;
	}

	@Override
	public void setCatchMenuKey(boolean catchMenu) {

		
	}

	@Override
	public void setInputProcessor(InputProcessor processor) {

		
	}

	@Override
	public InputProcessor getInputProcessor() {

		return null;
	}

	@Override
	public boolean isPeripheralAvailable(Peripheral peripheral) {

		return false;
	}

	@Override
	public int getRotation() {

		return 0;
	}

	@Override
	public Orientation getNativeOrientation() {

		return null;
	}

	@Override
	public void setCursorCatched(boolean catched) {

		
	}

	@Override
	public boolean isCursorCatched() {

		return false;
	}

	@Override
	public void setCursorPosition(int x, int y) {

		
	}

	@Override
	public void setCursorImage(Pixmap pixmap, int xHotspot, int yHotspot) {

		
	}

}
