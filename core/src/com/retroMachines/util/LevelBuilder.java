package com.retroMachines.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.data.Tree;

public class LevelBuilder {
	
	
	private static final String pathJSON = "maps/LevelJsons/Level";
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
		String pathToLevelJson = pathJSON + pathId;
		lambdaUtil.createTreeFromJson(pathToLevelJson);
	}
}
