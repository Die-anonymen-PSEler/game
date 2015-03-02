package com.retroMachines.ui.screens.menus;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retroMachines.RetroMachines;
import com.retroMachines.data.AssetManager;
import com.retroMachines.data.models.GlobalVariables;
import com.retroMachines.data.models.SettingsChangeListener;
import com.retroMachines.game.controllers.ProfileController;
import com.retroMachines.ui.RetroDialog;
import com.retroMachines.util.Constants.ButtonStrings;

/**
 * The MainMenuScreen is part of the view of RetroMachines.
 * The main menu of the game providing ways to all the other screens of the game.
 * It is the anchor of the game and is displayed right after the application has
 * finished loading.
 * @author RetroFactory
 *
 */
public class MainMenuScreen extends MenuScreen implements SettingsChangeListener {
	
	/**
	 * The Title of this Screen.
	 */
	public static final String TITLE = "RETROMACHINES";
	private final static float FONTSIZE3 =  3f;
	
	
	private ExitDialog exitDialog;
	
	private Music music;
		
	/**
	 * The constructor to create a new instance of the MainMenuScreen.
	 * @param game The game that uses this screen.
	 */
	public MainMenuScreen(RetroMachines game) {
		super(game);
	}
	
	/**
	 * Initializes the MainMenuScreen.
	 */
	@Override
	protected void initialize() {
		
		skin = AssetManager.getMenuSkin();
		// Make Title
		Label title = new Label(TITLE,skin);
		title.setFontScale((FONTSIZE3 * screenWidth) / DIVIDEWIDTHDEFAULT);
		title.setAlignment(Align.center);
		
		// Make Buttons
		Button buttonPlay = new Button(skin, "play");
		buttonPlay.addListener(new PlayButtonClickListener());
		buttonPlay.pad(screenHeight / (DEFAULTBUTTONSIZE * HALF));
		
		Button buttonSetting = new Button(skin, "settings");
		buttonSetting.addListener(new SettingButtonClickListener());
		buttonSetting.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		Button buttonAbout = new Button(skin, "info");
		buttonAbout.addListener(new AboutButtonClickListener());
		buttonAbout.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		Button buttonStatistics = new Button(skin, "statistic");
		buttonStatistics.addListener(new StatisticsButtonClickListener());
		buttonStatistics.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		Button buttonProfileMenu = new Button(skin, "profile");
		buttonProfileMenu.addListener(new ProfileMenuClickListener());
		buttonProfileMenu.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		Button buttonExit = new Button(skin, "exit");
		buttonExit.addListener(new ExitClickListener());
		buttonExit.pad(screenHeight / DEFAULTBUTTONSIZE);
		
		//Sidebar
		Table sidebar = new Table(skin);
		sidebar.add(buttonSetting).padTop(screenHeight / DEFAULTPADDING).row();
		sidebar.add(buttonAbout).padTop(screenHeight / DEFAULTPADDING).row();
		sidebar.add(buttonProfileMenu).padTop(screenHeight / DEFAULTPADDING).row();
		sidebar.add(buttonStatistics).padTop(screenHeight / DEFAULTPADDING).padBottom(screenHeight / DEFAULTPADDING).row();
		
		//Left Part of Menu
		Table mainPart = new Table(skin);
		mainPart.add(buttonExit).padTop(screenHeight / DEFAULTPADDINGx2).padLeft(screenWidth/ DEFAULTPADDINGx4).left().row();
		mainPart.add(title).padTop(screenHeight / DEFAULTPADDINGx2).expandX().right().row();
		mainPart.add(buttonPlay).padTop(screenHeight / DEFAULTPADDINGx4).padLeft((HALF * screenWidth) - (ONE_FIFTH * screenHeight) - (buttonPlay.getWidth() * HALF)).left().row();
		
		
		// Add Title and Buttons to View
		table.add(mainPart).expandY().width(screenWidth * FOUR_FIFTH).top();
		table.add(sidebar).width(screenWidth * ONE_FIFTH).row();
	    
	    stage.addActor(table);
	    inputMultiplexer.addProcessor(stage);

        //sound initialisieren
	    music = AssetManager.getMusic();
	    //music.play();
	    //music.setLooping(true);
        
       // game.getSettingController().add(this);
	}
    
    @Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK) {
    		if (exitDialog != null) {
        		exitDialog.show(stage);
        	} else {
        		exitDialog = new ExitDialog("" ,"Wirklich Verlassen ?");
				exitDialog.show(stage);
        	}
    		return true;
    	}
    	super.keyDown(keycode);
    	return false;
    }
   
	/**
	 * Sets the sound to the new volume that was newly adjusted in the settings.
	 */
	@Override
	public void onSettingsChanged() {
		
		// TODO Auto-generated method stub
		float newVolume = game.getSettingController().getVolume();
		music.setVolume(newVolume);
		//changes the volume in the settings so that its saved while quitting the game
		game.getSettingController().setVolume(newVolume);
	}
	

    private void disposeMusic() {
    	//music.dispose();
    }
    
    /*
     * Button Listeners
     */
	
	
	private class PlayButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			disposeMusic();
			game.setScreen(new LevelMenuScreen(game));
		}
	}
	
	private class SettingButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			disposeMusic();
			game.setScreen(new SettingsMenuScreen(game));
		}
	}
	
	private class AboutButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			disposeMusic();
			game.setScreen(new AboutMenuScreen(game));
		}
	}
	
	private class StatisticsButtonClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			disposeMusic();
			game.setScreen(new StatisticMenuScreen(game));
		}
	}
	
	private class ProfileMenuClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			disposeMusic();
			game.setScreen(new ProfileMenuScreen(game));
		}
	}
	
	private class ExitClickListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) { 
			disposeMusic();
			if(exitDialog != null) {
				exitDialog.show(stage);
			} else {
				exitDialog = new ExitDialog("", "Wirklich verlassen?");
				exitDialog.show(stage);
			}

		}
	}
	
	private class ExitDialog extends RetroDialog {

		public ExitDialog(String title, String msg) {
			super(title, msg);
			button(new Button(skin, ButtonStrings.ABORT), false);
		}
		
		@Override
		protected void result(Object object) {
			if ((Boolean) object) {
				Gdx.app.exit();
			} else {
				this.remove();
			}
		}
		
	}
}
