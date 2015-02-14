package com.retroMachines.util;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.Vertex;

public class LevelBuilder {
	
	
	private static final String PATHTOJSON = "maps/LevelJsons/Level";
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
		int levelId = id + 1;
		String pathToLevelJson = PATHTOJSON + levelId + ".json";
		lambdaUtil.createTreeFromJson(pathToLevelJson);
		addGameelements();
	}
	
	private void addGameelements() {
		LinkedList<Vertex> levelelements = lambdaUtil.getVertexList();
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		for (int i = 0; i < levelelements.size(); i++) {
			Vertex v = levelelements.get(i);
			int offset = (Integer) v.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
			int color = v.getColor();
			if (color <= Constants.MAX_ID) {
				Cell cell = new Cell();
				cell.setTile(v.getGameElement().getTileSet().getTile(color + offset));
				Vector2 position = v.getPosition();
				layer.setCell((int) position.x, (int) position.y, cell);
			} else {
				Gdx.app.log(Constants.LOG_TAG, "Out of ColorRange in TiledSets");
			}	
		}
	}
}
