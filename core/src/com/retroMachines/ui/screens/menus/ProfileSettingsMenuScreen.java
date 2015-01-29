package com.retroMachines.ui.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;

/**
 * The ProfileSettingsMenuScreen is part of the view of RetroMachines.
 * The profile allows the user to edit a profile in depth
 * and even delete it if he wishes.
 * @author RetroFactory
 *
 */
public class ProfileSettingsMenuScreen  extends MenuScreen {
	
	/**
	 * The ID of the profile that is edited by this screen.
	 */
	private int profileId;
	
	/**
	 * The actualCharacter represents the position in Character-String-Array in Constants.java.
	 */
	private int actualCharacter;
	
	
	private Button buttonRightMode;
	private Button buttonLeftMode;
	
	public ProfileSettingsMenuScreen(RetroMachines game, int id) {
		super(game);
		this.profileId = id;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
skin = AssetManager.menuSkin;
		
		// Make Title
		Label title = new Label("Create Profile",skin);
		title.setFontScale((3*screenWidth)/1920f);
		title.setAlignment(Align.center);
		
		//Profile Name
		//TODO get Profile Name
		Label profileName = new Label("Profile Name",skin);
		profileName.setFontScale((2f*screenWidth)/1920f);
		profileName.setAlignment(Align.center);
		
		//Subtitle LeftiMode
		Label steeringTitle = new Label("Steuerung", skin);
		steeringTitle.setFontScale((2f*screenWidth)/1920f);
		steeringTitle.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonOk = new Button(skin, "ok");
		buttonOk.addListener(new AcceptButtonClickListener());
		buttonOk.pad(screenHeight / 10f);
		
		Button buttonAbort = new Button(skin, "abort");
		buttonAbort.addListener(new AbortCreateProfileButtonClickListener());
		buttonAbort.pad(screenHeight / 10f);
		
		buttonLeftMode = new Button(skin, "controlLeft");
		buttonLeftMode.addListener(new LeftControlButtonClickListener());
		buttonLeftMode.pad(screenHeight / 10f);
		
		buttonRightMode = new Button(skin, "controlRight");
		buttonRightMode.addListener(new RightControlButtonClickListener());
		buttonRightMode.pad(screenHeight / 10f);
		buttonRightMode.setChecked(true);
		
		Button buttonNextChar = new Button(skin, "nextChar");
		buttonNextChar.addListener(new NextCharButtonClickListener());
		buttonNextChar.pad(screenHeight / 10f);
		
		
		//Make Image
		Image charImage = new Image();
		charImage.setDrawable(new TextureRegionDrawable(
		        new TextureRegion(new Texture(Gdx.files.internal("Serious2.png")))));
		charImage.setScaling(Scaling.fit);
		
		// Build Tables
		
		//ButtonTables
		Table buttonTable = new Table(skin);
		buttonTable.add(buttonAbort).padRight(screenWidth / 25f);
		buttonTable.add(buttonOk).padLeft(screenWidth / 25f);
		
		Table leftiTable = new Table(skin);
		leftiTable.add(buttonLeftMode).padRight(screenWidth / 25f);
		leftiTable.add(buttonRightMode).padLeft(screenWidth / 25f);
		
		//ImageTable
		Table imageTable = new Table(skin);
		imageTable.add(buttonNextChar).left().padRight(screenWidth / 100f);
		imageTable.add(charImage).padTop(screenHeight/ 50f).height((3*screenHeight) / 5f).width(2*(screenWidth) / 8f);
		
		
		//RightTable
		Table rightTable = new Table(skin);
		rightTable.add(profileName).padTop(screenHeight/ 30f).row();
		rightTable.add(steeringTitle).padTop(screenHeight/ 10f).row();
		rightTable.add(leftiTable).expandX().padTop(screenHeight/ 50f).row();
		
		//MainTable
		table.add(title).expandX().colspan(2).padTop(screenHeight/ 25f).row();
		table.add(imageTable).width(screenWidth * (4 / 9f));
		table.add(rightTable).width(screenWidth * (5 / 9f)).row();
		table.add(buttonTable).colspan(2).padTop(screenHeight/ 25f).row();
		
	    stage.addActor(table);
		inputMultiplexer.addProcessor(stage);
	}
	
	
	/**
	 * Get Method to get the int value which represents position of the character in the 
	 * String Array in Constants.java.
	 * @return The place in string array in Constants.
	 */
	public int getActualCharacter(){
		return actualCharacter;
	}
	
	/**
	 * Listener when the user aborts the profile creation.
	 * @author RetroFactory
	 */
	private class AbortCreateProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.setScreen(new SettingsMenuScreen(game));
		}
	}
	
	/**
	 * Listener when the button for ok a profile has been clicked
	 * @author RetroFactory
	 */
	private class AcceptButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			super.clicked(event, x, y);
		}
	}
	
	/**
	 * Listener when the button for left control has been chosen.
	 * @author RetroFactory
	 */
	private class LeftControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonRightMode.setChecked(false);
			buttonLeftMode.setChecked(true);
			//TODO Save Lefti Change
		}
	}
	
	/**
	 * Listener when the button for right control has been chosen.
	 * @author RetroFactory
	 */
	private class RightControlButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			buttonLeftMode.setChecked(false);
			buttonRightMode.setChecked(true);
			//TODO Save Lefti Change
		}
	}
	
	/**
	 * Listener when the button for right control has been chosen.
	 * @author RetroFactory
	 */
	private class NextCharButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			//TODO Change Char Pic and save it
		}
	}
	

}
