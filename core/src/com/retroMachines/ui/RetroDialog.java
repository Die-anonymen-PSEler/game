package com.retroMachines.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.Constants.ButtonStrings;

public class RetroDialog extends Dialog {
	
	protected final static float DIVIDEWIDTHDEFAULT = 1920f;
	
	protected final static float DIALOGTEXTWIDTH = (10f / 17f);
	
	private Label labelMsg;
	
	private Skin skin;
	
	private Runnable runnable;
	
	private boolean autoClear;
	
	public RetroDialog(String title, String msg) {
		super(title, AssetManager.getMenuSkin());
		this.skin = AssetManager.getMenuSkin();
		autoClear = true;
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		padTop(screenWidth / 30f); // set padding on top of the dialog title
		padBottom(screenWidth / 30f); // set padding on bottom of the dialog title
        getButtonTable().defaults().height(screenHeight * (1/4f)); // set buttons height
        getButtonTable().defaults().width(screenWidth * (1/4f)); // set buttons height
        setModal(true);
        setMovable(false);
        setResizable(false);
		labelMsg = new Label(msg, skin);
		labelMsg.setWrap(true);
		labelMsg.setAlignment(Align.center);
		labelMsg.setFontScale((2.1f * screenWidth) / DIVIDEWIDTHDEFAULT);
		getContentTable().add(labelMsg).width(screenWidth * DIALOGTEXTWIDTH);
		button(new Button(skin, ButtonStrings.OK), true);
	}

	public RetroDialog(String title, Skin skin) {
		super(title, skin);
		this.skin = skin;
	}
	
	public void setRunnable(Runnable r) {
		runnable = r;
	}
	
	public void setAutoClear(boolean t) {
		autoClear = t;
	}
	
	protected void result(Object object) {
		if (autoClear) {
			this.remove();
		}
		if (runnable != null) {
			runnable.run();
		}
	}
}
