package com.retroMachines.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.retroMachines.util.Constants;
import com.retroMachines.util.Constants.RetroStrings;

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
	 * TiledMapTileSets
	 */
	private static TiledMapTileSet objects;
	private static TiledMapTileSet machines;
	private static TiledMapTileSet lights;
	private static TiledMapTileSet depots;

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
		TextureAtlas atlas = new TextureAtlas("skins/LambdaGame.pack");
		TextureAtlas gameElementsAtlas = new TextureAtlas("Gameelements/Gameelements.pack");
		manager.finishLoading();
		menuSkin = new Skin(
				Gdx.files.internal("skins/DefaultLambdaGame.json"),
				atlas);
		notifyListeners(66);
		gameElementTexture = new Skin(gameElementsAtlas);
		notifyListeners(80);
		TmxMapLoader loader = new TmxMapLoader();
		for (int i = 1; i <= Constants.MAX_LEVEL_ID; i++) {
			maps.add(loader.load("maps/Level" + i + ".tmx"));
			notifyListeners(80 + (int) (i * 20 / (float)Constants.MAX_LEVEL_ID));
		}
		setTiledMapTileSets(maps.getFirst());
		music = Gdx.audio.newMusic(Gdx.files.internal("music/musicfile.ogg"));
		
		textureMap.put("Animation", new Texture("map/Animation.png"));
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
	public static TiledMap getMap(int levelId) {
		return maps.get(levelId);
	}
	
	public static void reloadMap(int levelId) {
		maps.set(levelId, new TmxMapLoader().load("maps/Level" + (levelId + 1) + ".tmx"));
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
	// -----GameElements skins--------
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
	
	/**
	 * Adds TileSets of Machine Metalobjects and Lights of currentMap
	 * @param currentMap
	 */
	private static void setTiledMapTileSets(TiledMap currentMap) {
		TiledMapTileSets levelSets = currentMap.getTileSets();
		objects = levelSets.getTileSet(RetroStrings.TILESETNAME_METALOBJECTS);
		machines = levelSets.getTileSet(RetroStrings.TILESETNAME_MACHINE);
		lights = levelSets.getTileSet(RetroStrings.TILESETNAME_LIGHT);
		depots = levelSets.getTileSet(RetroStrings.TILESETNAME_DEPOT);
	}
	
	/**
	 * getter for TiledMapTileSet of Objects in TiledMap
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getObjects() {
		return objects;
	}
	
	/**
	 * getter for TiledMapTileSet of Machines in TiledMap
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getMachines() {
		return machines;
	}
	
	/**
	 * getter for TiledMapTileSet of Lights in TiledMap
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getLights() {
		return lights;
	}
	
	
	/**
	 * getter for TiledMapTileSet of Depots in TiledMap
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getDepots() {
		return depots;
	}
}
