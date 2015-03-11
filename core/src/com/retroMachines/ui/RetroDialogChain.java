package com.retroMachines.ui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * A DialogChain where every dialog will be displayed after the previous
 * 
 * @author lucabecker
 * 
 */
public class RetroDialogChain {

	private List<DialogChain> dialogs;

	private int position;

	private Stage s;

	private List<DialogChainFinishedListener> listeners;

	public RetroDialogChain() {
		dialogs = new LinkedList<DialogChain>();
		listeners = new LinkedList<DialogChainFinishedListener>();
	}

	private void notifyListeners() {
		for (DialogChainFinishedListener listener : listeners) {
			listener.dialogFinished();
		}
	}

	private void nextDialog() {
		if (dialogs.size() > position) {
			DialogChain dialog = dialogs.get(position);
			dialog.show(s);
			position++;
		} else {
			notifyListeners();
		}
	}

	public void addDialog(String title, Texture image) {
		dialogs.add(new DialogChain(title, image));
	}

	public void show(Stage s) {
		this.s = s;
		nextDialog();
	}

	public void registerListener(DialogChainFinishedListener listener) {
		listeners.add(listener);
	}

	private class DialogChain extends Dialog {

		private Texture img;

		public DialogChain(String title, Texture image) {
			super(title, RetroAssetManager.getMenuSkin());
			img = image;
			initialize();
		}

		private void initialize() {
			Skin skin = RetroAssetManager.getMenuSkin();
			int screenWidth = Gdx.graphics.getWidth();
			int screenHeight = Gdx.graphics.getHeight();

			Image tutImg = new Image();
			tutImg.setDrawable(new TextureRegionDrawable(new TextureRegion(img)));

			getContentTable().add(tutImg);

			padTop(screenHeight / 30f); // set padding on top of the dialog
										// title
			padBottom(screenHeight / 30f); // set padding on bottom of the
											// dialog title
			padRight(screenWidth / 30f);
			padLeft(screenWidth / 30f);
			getButtonTable().defaults().height(screenHeight * (1 / 4f)); // set
																			// buttons
																			// height
			getButtonTable().defaults().width(screenWidth * (1 / 4f)); // set
																		// buttons
																		// height
			setModal(true);
			setMovable(false);
			setResizable(false);
			button(new Button(skin, ButtonStrings.OK), true);
		}

		@Override
		protected void result(Object object) {
			this.remove();
			nextDialog();
		}

	}

	public static interface DialogChainFinishedListener {

		public void dialogFinished();

	}
}
