package com.retroMachines.data;

import java.util.HashMap;
import java.util.LinkedList;

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
public class RetroAssetManager extends com.badlogic.gdx.assets.AssetManager {

	/**
	 * Default constructor which starts a simple instance of AssetManager.
	 */
	public static final RetroAssetManager MANAGER = new RetroAssetManager();

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
	 * TiledMapTileSet for objects
	 */
	private static TiledMapTileSet objects;
	/**
	 * TiledMapTileSet for machines
	 */
	
	private static TiledMapTileSet machines;
	
	/**
	 * TiledMapTileSet for lights
	 */
	private static TiledMapTileSet lights;
	
	/**
	 * TiledMapTileSet for depots
	 */
	private static TiledMapTileSet depots;

	/**
	 * Contains all file references to the files that need to be loaded.
	 */
	public static final String[] ASSET_NAMES = {};

	/**
	 * list containing all maps for fast access.
	 */
	private static final LinkedList<TiledMap> MAPS = new LinkedList<TiledMap>();

	/**
	 * a hashmap with (String,Texture) pairs.
	 */
	private static final HashMap<String, Texture> TEXTURE_MAP = new HashMap<String, Texture>();

	/**
	 * pattern where the character images are stored.
	 */
	private static final String CHARACTER_PATH_PATTERN = "Character/Animation%s.png";

	/**
	 * Added assets that need to be ready before the first game screen will be
	 * displayed Only those assets are allowed here.
	 */
	public static void initializePreLoading() {
		TEXTURE_MAP.put(Constants.BACKGROUND_PATH,
				new Texture(Gdx.files.internal("Background.png")));
		MANAGER.finishLoading();
	}

	/**
	 * Loads all relevant objects into the cache of the game for flawless
	 * drawing.
	 */
	public static void initializeWhileLoading() {
		MANAGER.load("music/musicfile.ogg", Music.class);
		TextureAtlas atlas = new TextureAtlas("skins/LambdaGame.pack");
		TextureAtlas gameElementsAtlas = new TextureAtlas(
				"Gameelements/Gameelements.pack");
		MANAGER.finishLoading();
		menuSkin = new Skin(Gdx.files.internal("skins/DefaultLambdaGame.json"),
				atlas);
		gameElementTexture = new Skin(gameElementsAtlas);
		TmxMapLoader loader = new TmxMapLoader();
		for (int i = 1; i <= Constants.MAX_LEVEL_ID; i++) {
			MAPS.add(loader.load("maps/Level" + i + ".tmx"));
		}
		setTiledMapTileSets(MAPS.getFirst());
		music = Gdx.audio.newMusic(Gdx.files.internal("music/musicfile.ogg"));
		for (int i = 0; i < Constants.TEXTURE_ANIMATION_NAMES.length; i++) {
			TEXTURE_MAP.put(
					Constants.TEXTURE_ANIMATION_NAMES[i],
					new Texture(String.format(CHARACTER_PATH_PATTERN,
							Constants.TEXTURE_ANIMATION_NAMES[i])));
		}
		MANAGER.finishLoading();
	}

	/**
	 * Loads a map from the Storage based on it's ID.
	 * 
	 * @param levelId
	 *            , the ID of the map to be loaded.
	 * @return The map, it is loaded as a TiledMap.
	 */
	public static TiledMap getMap(int levelId) {
		return MAPS.get(levelId);
	}

	/**
	 * reload the map
	 * @param levelId the level where the map should be reloaded
	 */
	public static void reloadMap(int levelId) {
		MAPS.set(levelId,
				new TmxMapLoader().load("maps/Level" + (levelId + 1) + ".tmx"));
	}

	// -----------------------
	// --------Music----------
	// -----------------------

	/**
	 * instance of the music of the game.
	 * 
	 * @return the music of the game
	 */
	public static Music getMusic() {
		return music;
	}

	// -----------------------
	// -----Menu Skins--------
	// -----------------------

	/**
	 * skin instance for all buttons.
	 * 
	 * @return the skin of the menus
	 */
	public static Skin getMenuSkin() {
		return menuSkin;
	}

	// -------------------------------
	// -----GameElements skins--------
	// -------------------------------

	/**
	 * getter for the gameElement textures
	 * @return the game element texture
	 */
	public static Skin getGameelementSkin() {
		return gameElementTexture;
	}

	/**
	 * retrieves a texture from the hashmap
	 * 
	 * @param path
	 * @return the texture asked for
	 */
	public static Texture getTexture(String path) {
		if (TEXTURE_MAP.containsKey(path)) {
			return TEXTURE_MAP.get(path);
		} else {
			throw new IllegalArgumentException(
					"That path has not been loaded? Might wanna change that "
							+ path);
		}
	}

	/**
	 * Adds TileSets of Machine Metalobjects and Lights of currentMap
	 * 
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
	 * 
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getObjects() {
		return objects;
	}

	/**
	 * getter for TiledMapTileSet of Machines in TiledMap
	 * 
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getMachines() {
		return machines;
	}

	/**
	 * getter for TiledMapTileSet of Lights in TiledMap
	 * 
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getLights() {
		return lights;
	}

	/**
	 * getter for TiledMapTileSet of Depots in TiledMap
	 * 
	 * @return TiledMapTileSet
	 */
	public static TiledMapTileSet getDepots() {
		return depots;
	}
}
