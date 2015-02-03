package com.retroMachines.data;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.retroMachines.util.Constants;

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
	 * maps cached within the asset manager
	 */
	private static final LinkedList<TiledMap> maps = new LinkedList<TiledMap>();
	
	private static final LinkedList<OnProgressChanged> listeners = new LinkedList<AssetManager.OnProgressChanged>();

	/**
	 * Loads all relevant objects into the cache of the game for flawless
	 * drawing.
	 */
	public static void initialize() {
		manager.finishLoading();
		manager.load("music/musicfile.ogg", Music.class);
		notifyListeners(30);
		manager.finishLoading();
		menuSkin = new Skin(
				Gdx.files.internal("skins/DefaultLambdaGame.json"),
				manager.get("skins/LambdaGame.pack", TextureAtlas.class));
		notifyListeners(66);
		TmxMapLoader loader = new TmxMapLoader();
		for (int i = 1; i < Constants.MAX_LEVEL_ID; i++) {
			maps.add(loader.load("assets/maps/Level" + i + ".tmx"));
			notifyListeners((int) (i / (float)Constants.MAX_LEVEL_ID));
		}
		manager.finishLoading();
	}
	
	/**
	 * registers the listener to be notified about changes when the assertmanager finishes
	 * @param listener
	 */
	public static void addListener(OnProgressChanged listener) {
		listeners.add(listener);
	}
	
	public static void removeListener(OnProgressChanged listener) {
		listeners.remove(listener);
	}
	
	/**
	 * @param value range 0 - 100
	 */
	private static void notifyListeners(int value) {
		for (OnProgressChanged listener : listeners) {
			listener.progressChanged(value);
		}
	}

	/**
	 * Loads a map from the Storage based on it's ID.
	 * 
	 * @param LevelId
	 *            , the ID of the map to be loaded.
	 * @return The map, it is loaded as a TiledMap.
	 */
	public static TiledMap loadMap(int levelId) {
		return maps.get(levelId);
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
	
	/**
	 * implement this listeners to be notified about the state of the game loading files
	 * @author lucabecker
	 */
	public interface OnProgressChanged {
		public void progressChanged(int value);
	}
}
