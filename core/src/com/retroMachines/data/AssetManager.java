package com.retroMachines.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	 * The Gameelement textures
	 */
	private static Skin gameElementTexture;

	/**
	 * Contains all file references to the files that need to be loaded.
	 */
	public static final String[] assetNames = {};
	
	private static final LinkedList<TiledMap> maps = new LinkedList<TiledMap>();

	private static final List<OnProgressChanged> listeners = new LinkedList<OnProgressChanged>();
	
	private static final HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
	
	/**
	 * Added assets that need to be ready before the first game screen will be displayed
	 * Only those assets are allowed here.
	 */
	public static void initializePreLoading() {
		textureMap.put("Background.png", new Texture(Gdx.files.internal("Background.png")));
		manager.finishLoading();
	}

	/**
	 * Loads all relevant objects into the cache of the game for flawless
	 * drawing.
	 */
	public static void initializeWhileLoading() {
		manager.load("music/musicfile.ogg", Music.class);
		notifyListeners(30);
		manager.finishLoading();
		TextureAtlas atlas = new TextureAtlas("skins/LambdaGame.pack");
		manager.finishLoading();
		menuSkin = new Skin(
				Gdx.files.internal("skins/DefaultLambdaGame.json"),
				atlas);
		manager.finishLoading();
		notifyListeners(66);
		TextureAtlas gameElementsAtlas = new TextureAtlas("Gameelements/Gameelements.pack");
		manager.finishLoading();
		gameElementTexture = new Skin(gameElementsAtlas);
		manager.finishLoading();
		notifyListeners(80);
		TmxMapLoader loader = new TmxMapLoader();
		for (int i = 1; i < Constants.MAX_LEVEL_ID; i++) {
			maps.add(loader.load("assets/maps/Level" + i + ".tmx"));
			notifyListeners(80 + (int) (i * 20 / (float)Constants.MAX_LEVEL_ID));
		}
		manager.finishLoading();
	}

	private static void notifyListeners(int value) {
		for (OnProgressChanged listener : listeners) {
			listener.progressChanged(value);
		}
	}
	
	public static void addProgressListener(OnProgressChanged listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public static void removeProgressListener(OnProgressChanged listener) {
		listeners.remove(listener);
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
	
	public interface OnProgressChanged {
		public void progressChanged(int value);
	}
	
	// -------------------------------
	// -----Gameelements skins--------
	// -------------------------------
	
	public static Skin getGameelementskin() {
		return gameElementTexture;
	}
	
	public static Texture getTexture(String path) {
		if (textureMap.containsKey(path)) {
			return textureMap.get(path);
		}
		else {
			throw new IllegalArgumentException("That path has not been loaded? Might wanna change that " + path);
		}
	}
}
