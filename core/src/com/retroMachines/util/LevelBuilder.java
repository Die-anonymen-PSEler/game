package com.retroMachines.util;

import java.util.LinkedList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.Vertex;

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
		String pathToLevelJson = PATHTOJSON + id;
		lambdaUtil.createTreeFromJson(pathToLevelJson);
	}
	
	private void addGameelements() {
		TiledMapTileSets levelSets = map.getTileSets();
		TiledMapTileSet objects = levelSets.getTileSet(TILESETNAME_METALOBJECTS);
		TiledMapTileSet machines = levelSets.getTileSet(TILESETNAME_MACHINE);
		TiledMapTileSet lights = levelSets.getTileSet(TILESETNAME_LIGHT);
		LinkedList<Vertex> levelelements = lambdaUtil.getVertexList();
		for (int i = 0; i < levelelements.size(); i++) {
			Vertex v = levelelements.get(i);
			int color = v.getColor();
			if (color <= Constants.MAX_ID) {
				TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.DEPOT_LAYER);
				Cell cell = new Cell();
				layer.setCell((int) v.getPosition().x, (int) v.getPosition().y, cell);
			}
			
		}
	}
}
