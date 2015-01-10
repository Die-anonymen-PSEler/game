package com.retroMachines.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager extends com.badlogic.gdx.assets.AssetManager {
	
    public static AssetManager manager = new AssetManager();
    
    public static Skin menuSkin;
    
    /**
     * Contains all file references to the Files to load. 
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
    
    
    
    
    
    
    
    
    
    
    
    

    // In here we'll put everything that needs to be loaded in this format:
    // manager.load("file location in assets", fileType.class);
    // 
    // libGDX AssetManager currently supports: Pixmap, Texture, BitmapFont,
    //     TextureAtlas, TiledAtlas, TiledMapRenderer, Music and Sound.
    public static void queueLoading() {
        manager.load("skins/MenuButtons.pack", TextureAtlas.class);
    }

    //In here we'll create our skin, so we only have to create it once.
    public static void setMenuSkin() {
        if (menuSkin == null)
            menuSkin = new Skin(Gdx.files.internal("skins/ButtonSkin.json"),
                    manager.get("skins/MenuButtons.pack", TextureAtlas.class));
    }
    // This function gets called every render() and the AssetManager pauses the loading each frame
    // so we can still run menus and loading screens smoothly
    
    public boolean update() {
        return manager.update();
    }
}
