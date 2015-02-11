package com.retroMachines.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.data.Tree;

public class LevelBuilder {
	
	private TiledMap map;
	
	private LambdaUtil util;
	
	/**
	 * Creates a new instance of the levelbuilder
	 */
	public LevelBuilder() {
		util = new LambdaUtil();
		//TODO: insert paramater for json path
		util.createTreeFromJson("some path");
	}
	
	/**
	 * 
	 * @return
	 */
	public TiledMap getTiledMap() {
		return map;
	}
	
	/**
	 * 
	 * @return
	 */
	public LambdaUtil getLambda() {
		return util;
	}
	
	/**
	 * prepares both map and the lambda structure for the game controller
	 * @param id
	 */
	public void prepare(int id) {
		map = AssetManager.loadMap(id);
	}
}
