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

	/**
	 * List of DialogChains to be displayed
	 */
	private List<DialogChain> dialogs;

	/**
	 * Position of the DialogChain
	 */
	private int position;

	/**
	 * Stage of dialogues
	 */
	private Stage stage;

	/**
	 * List of Listeners that check if a dialog is finished
	 */
	private List<DialogChainFinishedListener> listeners;

	/**
	 * constructor which initializes the two lists
	 */
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
			dialog.show(stage);
			position++;
		} else {
			notifyListeners();
		}
	}

	/**
	 * adds a dialog to the list of dialogues to be displayed
	 * @param title Title of the dialogue
	 * @param image Image that should be displayed while the dialog is displayed
	 */
	public void addDialog(String title, Texture image) {
		dialogs.add(new DialogChain(title, image));
	}

	/**
	 * shows the next dialog in the list
	 * @param s the stage it is
	 */
	public void show(Stage s) {
		this.stage = s;
		nextDialog();
	}

	/**
	 * adds a new listener to the list
	 * @param listener the listener to be added
	 */
	public void registerListener(DialogChainFinishedListener listener) {
		listeners.add(listener);
	}

	private class DialogChain extends Dialog {

		private Texture img;

		/**
		 * creates a new instance of DialogChain
		 * @param title the title it should have
		 * @param image the image that should be displayed
		 */
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

		/**
		 * when the dialog is finished
		 */
		public void dialogFinished();

	}
}
