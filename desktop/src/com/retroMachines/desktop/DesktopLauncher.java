package com.retroMachines.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.retroMachines.RetroMachines;

public class DesktopLauncher {
	
    public static final int WIDTH=1280;
    public static final int HEIGHT=720;
    
    public static final String WINDOW_TITLE = "RetroMachines";
    
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH; // sets window width
		config.height = HEIGHT;  // sets window height
        config.resizable = false;
        config.title = WINDOW_TITLE;
        //config.fullscreen = true;
		new LwjglApplication(new RetroMachines(), config);
	}
}