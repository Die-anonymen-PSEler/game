package com.retroMachines.util;

import java.util.LinkedList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.Vertex;
import com.retroMachines.util.lambda.data.Tree;

public class LevelBuilder {
	
	
	private static final String PATHTOJSON = "maps/LevelJsons/Level";
	private static final String TILESETNAME_MACHINE = "Maschinen";
	private static final String TILESETNAME_METALOBJECTS = "Metallobjekte";
	private static final String TILESETNAME_LIGHT = "Ampeln";
	private TiledMap map;
	
	private LambdaUtil lambdaUtil;
	
	/**
	 * Creates a new instance of the levelbuilder
	 */
	public LevelBuilder() {
		lambdaUtil = new LambdaUtil();
	}
	
	/**
	 * getter for the map
	 * @return map
	 */
	public TiledMap getTiledMap() {
		return map;
	}
	
	/**
	 * getter for the lambdaUtil
	 * @return lambdaUtil object, contains everything belonging to lambda
	 */
	public LambdaUtil getLambdaUtil() {
		return lambdaUtil;
	}
	
	/**
	 * prepares both map and the lambda structure for the game controller
	 * @param id
	 */
	public void prepare(int id) {
		map = AssetManager.loadMap(id);
		int pathId = id + 1;
		String pathToLevelJson = PATHTOJSON + pathId;
		lambdaUtil.createTreeFromJson(pathToLevelJson);
	}
	
	private void addGameelements() {
		TiledMapTileSets levelSets = map.getTileSets();
		TiledMapTileSet objects = levelSets.getTileSet(TILESETNAME_METALOBJECTS);
		TiledMapTileSet machines = levelSets.getTileSet(TILESETNAME_MACHINE);
		TiledMapTileSet lights = levelSets.getTileSet(TILESETNAME_LIGHT);
		LinkedList<Vertex> levelelements = lambdaUtil.getVertexList();
		for (int i = 0; i < levelelements.size(); i++) {
			//TODO Get type of element and color .... draw on map
		}
	}
}
