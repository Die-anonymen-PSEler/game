package com.retroMachines.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.retroMachines.RetroMachines;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=RetroMachines.WIDTH; // sets window width
        config.height=RetroMachines.HEIGHT;  // sets window height
		new LwjglApplication(new RetroMachines(), config);
	}
}
