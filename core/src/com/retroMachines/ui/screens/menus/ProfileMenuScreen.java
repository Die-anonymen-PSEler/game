package com.retroMachines.ui.screens.menus;



import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.controllers.ProfileController;

/**
 * The ProfileMenuScreen is part of the view of RetroMachines.
 * It shows all created profiles to the user and 
 * offers to create more or delete a given profile.
 * @author RetroFactory
 *
 */
public class ProfileMenuScreen extends MenuScreen{
	
	private ProfileController profileController;
	
	private List<String> profileList;
	
	/**
	 * The constructor to create a new instance of the ProfileMenuScreen.
	 * @param game The game which  uses this screen.
	 */
	public ProfileMenuScreen(RetroMachines game) {
		super(game);
		profileController = game.getProfileController();
	}

	/**
	 * Initializes this screen.
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		skin = AssetManager.menuSkin;
		
		// Make Title
		Label title = new Label("Profile",skin);
		title.setWrap(true);
		title.setFontScale((3f * screenWidth)/1920f);
		title.setAlignment(Align.center);
			
		// Make Profile List
		profileList = new List<String>(skin);
		profileList.setItems(new String[] {"Profile1","Profile2","Profile3","Profile4","Profile5","Profile6", "Profile7",
								"Profile8", "Profile9", "Profile10", "Profile11", "Profile12", "Profile13", "Profile14"});
		
		profileList.getStyle().font.setScale((2.5f * screenWidth)/1920f);
		Table scrollTable = new Table(skin);
		scrollTable.add(profileList);
		ScrollPane profileScroll = new ScrollPane(scrollTable, skin);
		profileScroll.getStyle().hScrollKnob.setMinWidth((15f * screenWidth)/1920f);
		
		// Make Buttons
		Button buttonReturn = new Button(skin, "back");
		buttonReturn.pad(screenHeight / 10f);
		buttonReturn.addListener(new ReturnButtonClickListener());
		Button buttonSelectProfile = new Button(skin, "ok");
		buttonSelectProfile.pad(screenHeight / 10f);
		buttonSelectProfile.addListener(new SelectProfileButtonClickListener());
		
		// Make Table
		table.add(buttonReturn).padTop(screenHeight / 50f).padLeft(screenWidth/ 100f).left();
		table.add(title).width(screenWidth / 2.5f).right().padRight((screenWidth / 2f) - (screenWidth / 5f) ).expandX().row();
		table.add(profileScroll).expandY().colspan(2).padTop(screenHeight / 20f).row();
		table.add(buttonSelectProfile).colspan(2).right().padRight(screenWidth / 50f);
		
		stage.addActor(table);
		inputMultiplexer.addProcessor(stage);
		
	}	
	
	/**
	 * Listener when the button for creating a profile has been clicked.
	 * @author RetroFactory
	 */
	private class AddProfileButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			game.setScreen(new CreateProfileMenuScreen(game));
		}
	}
	
	/**
	 * Listener when the button for selecting a profile has been clicked.
	 * Afterwards the main menu is shown.
	 * @author RetroFactory
	 */
	private class SelectProfileButtonClickListener extends ClickListener {
		
		public SelectProfileButtonClickListener() {
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			//TODO  IS it Right ?
			game.getProfileController().changeActiveProfile(profileList.getSelected());
		}
	}
	
	/**
	 * Button to return to the MainMenuScreen.
	 * 
	 * @author RetroFactory
	 *
	 */
	private class ReturnButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			game.setScreen(new MainMenuScreen(game));
		}
	}
}
