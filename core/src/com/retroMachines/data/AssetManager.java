package com.retroMachines.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The AssetManager is part of the controller of the RetroMachines. It manages
 * the different assets of the game like the images and fonts. It loads the
 * required files for the view of the game. Other classes use this class to get
 * their views.
 * 
 * @author RetroFactory
 * 
 */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {

	/**
	 * Default constructor which starts a simple instance of AssetManager.
	 */
	public static AssetManager manager = new AssetManager();

	/**
	 * The design of the menus is stored here.
	 */
	private static Skin menuSkin;

	/**
	 * The music which is played during the game is stored here.
	 */
	private static Music music;

	/**
	 * Contains all file references to the files that need to be loaded.
	 */
	public static final String[] assetNames = {};

	/**
	 * Loads all relevant objects into the cache of the game for flawless
	 * drawing.
	 */
	public static void initialize() {
		manager.finishLoading();
		manager.load("music/musicfile.ogg", Music.class);
		manager.finishLoading();
		menuSkin = new Skin(
				Gdx.files.internal("skins/DefaultLambdaGame.json"),
				manager.get("skins/LambdaGame.pack", TextureAtlas.class));
		manager.finishLoading();
	}

	/**
	 * Loads a map from the Storage based on it's ID.
	 * 
	 * @param LevelId
	 *            , the ID of the map to be loaded.
	 * @return The map, it is loaded as a TiledMap.
	 */
	public static TiledMap loadMap(int levelId) {
		return new TmxMapLoader().load(("/maps/level" + levelId + ".tmx"));
	}

	// -----------------------
	// --------Music----------
	// -----------------------
	/**
     * 
     */
	public static void loadMusic() {
		
	}
	
	public static Music getMusic() {
		return music;
	}

	// -----------------------
	// -----Menu Skins--------
	// -----------------------

	/**
	 * QueueLoading loads the assets in the format:
	 * manager.load("file location in assets", fileType.class);
	 * 
	 * libGDX AssetManager currently supports: Pixmap, Texture, BitmapFont,
	 * TextureAtlas, TiledAtlas, TiledMapRenderer, Music and Sound.
	 */
	public static void queueLoading() {
		manager.load("skins/LambdaGame.pack", TextureAtlas.class);
	}
	
	public static Skin getMenuSkin() {
		return menuSkin;
	}
}
