package com.retroMachines.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * AssetManager manages the different assets of the game like the images and fonts. It loads the required files 
 * for the view of the game. Other classes use this class to get their views.
 * @author RetroFactury
 *
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {
	
    public static AssetManager manager = new AssetManager();
    
    public static Skin menuSkin;
    
    /**
     * Contains all file references to the Files to load 
     */
    public static final String[] assetNames = {};
    
    
    
    /**
     * loads all relevant objects into the cache of the game 
     * for flawless drawing
     */
    public void initialize() {
    	//TODO implement here
    }


    /**
     * Loads a map from the Storage based on it's id
     * @param levelId the id of the map to load
     * @return the map loaded as a TiledMap
     */
    public static TiledMap loadMap(int levelId) {
    	return new TmxMapLoader().load(("/maps/level" + levelId + ".tmx"));
    }
    
    
    
    
    
    
    
    
    
    
    
    


    /**
     * queueLoading loads the assets in the format:
     * manager.load("file location in assets", fileType.class);
     * 
     * libGDX AssetManager currently supports: Pixmap, Texture, BitmapFont,
     *  TextureAtlas, TiledAtlas, TiledMapRenderer, Music and Sound.
     */
    public static void queueLoading() {
        manager.load("skins/MenuButtons.pack", TextureAtlas.class);
    }

    /**
     * The basic menu is created to be used in the different menus
     */
    public static void setMenuSkin() {
        if (menuSkin == null)
            menuSkin = new Skin(Gdx.files.internal("skins/ButtonSkin.json"),
                    manager.get("skins/MenuButtons.pack", TextureAtlas.class));
    }
    
    /** This function gets called every render() call and the AssetManager pauses the loading of each frame
     *  so that the menus and loading screens can run smoothly
     */
    public boolean update() {
        return manager.update();
    }
}
