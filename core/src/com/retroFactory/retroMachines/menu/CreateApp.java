package com.retroFactory.retroMachines.menu;

import com.badlogic.gdx.Game;

public class CreateApp extends Game {
    public static final String TITLE="MenuStructur"; 
    public static final int WIDTH=480,HEIGHT=800; // used later to set window size
    
    @Override
    public void create() {
            setScreen(new LoadMenu());
    }
}
