package com.retroMachines.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retroMachines.util.lambda.data.Tree;

public class LevelBuilder {
	
	private TiledMap map;
	
	private Tree tree;
	
	/**
	 * Creates a new instance of the levelbuilder
	 */
	public LevelBuilder() {
		
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
	public Tree getLambda() {
		return tree;
	}
	
	/**
	 * prepares both map and the lambda structure for the game controller
	 * @param id
	 */
	public void prepare(int id) {
		
	}
}
