package com.retroFactory.retroMachines.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuViewClickListner {
	
	
	// �bgabe der ClickListner erfolgt entweder �ber Konstruktor oder setter Methode
	private AboutMenuView aboutMenu = new AboutMenuView();
	private AchievmentsMenuView achievmentMenu = new AchievmentsMenuView();
	private CreateFirstProfileMenuView createFirstProfileMenu = new CreateFirstProfileMenuView();
	private CreateProfileMenuView createProfileMenu = new CreateProfileMenuView();
	private LevelMenuView levelMenu = new LevelMenuView();
	private LoadMenuView loadMenu = new LoadMenuView();
	private MainMenuView mainMenu = new MainMenuView();
	private ProfileManagementMenuView profileManagementMenu = new ProfileManagementMenuView();
	private ProfileMenuView profileMenu = new ProfileMenuView();
	private ProfileSettingsMenuView profileSettingsMenu = new ProfileSettingsMenuView();
	private SettingsMenuView settingsMenu = new SettingsMenuView();
	private StageMenuView stageMenu = new StageMenuView();
	
	
	/**
	 * F�hrt in das vorherige Men�
	 * soll in fast allen Men�s benutzt werden
	 */
	private ClickListener  Back =  new ClickListener(){
        public void clicked(InputEvent event, float x, float y) {
        	
        }
    };
    
    // Hier werden alle CLicklistner erstellt
}
