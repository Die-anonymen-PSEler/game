package com.retroMachines.game;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.Vertex;

public class RetroLevel {
	
	private int levelId;
	
	private LambdaUtil lambdaUtil;
	
	private TiledMap map;
	
	public RetroLevel(int id, TiledMap map, LambdaUtil util) {
		this.levelId = id;
		this.map = map;
		lambdaUtil = util;
	}

	public TiledMap getMap() {
		return map;
	}
	
	public LambdaUtil getLambdaUtil() {
		return lambdaUtil;
	}
	
	public boolean isValidGameElementPosition(Vector2 pos) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		if (layer.getCell((int)pos.x, (int) pos.y) != null) {
			return false;
		}
		layer = (TiledMapTileLayer) map.getLayers().get(Constants.SOLID_LAYER_ID);
		if (layer.getCell((int)pos.x , (int)pos.y) != null || layer.getCell( (int)pos.x, ((int)pos.y - 1)) == null) {
			return false;
		}
		return true;
	}
	
	public void placeGameElement(GameElement element, Vector2 newPos) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		Vertex vertex = lambdaUtil.getVertex(element);
		vertex.setPosition(new Vector2(newPos.x, newPos.y));
		Cell cell = new Cell();
		cell.setTile(element.getTileSet().getTile(element.getTileId()));
		layer.setCell((int) newPos.x, (int) newPos.y, cell);
	}
	
	public void removeGameElement(Vector2 elementPosition) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		layer.setCell((int) elementPosition.x, (int) elementPosition.y, null);
	}
	
	/**
	 * Returns GameElement at a given position in TiledMap and deletes it.
	 * 
	 * @param posObj
	 *            Position in TiledMap of the GameElement.
	 * @return The GameElement at this position ( null when empty).
	 */
	public GameElement getGameElement(Vector2 posObj) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);
		Cell cell = layer.getCell((int) posObj.x, (int) posObj.y);
		return (cell == null) ? null : lambdaUtil.getGameElement((int) posObj.x, (int) posObj.y);
	}
	
	public Array<Rectangle> getTiles(int startX, int startY, int endX, int endY, int layerId) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerId);
		Array<Rectangle> tiles = new Array<Rectangle>();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = new Rectangle(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
		return tiles;
	}
	
	public static class LevelBuilder {
		
		
		public static final String JSON_PATTERN = "maps/LevelJsons/Level%s.json";
		
		private TiledMap map;
		
		private LambdaUtil lambdaUtil;
		
		private RetroLevel level;
		
		/**
		 * Creates a new instance of the levelbuilder
		 */
		public LevelBuilder() {
			lambdaUtil = new LambdaUtil();
		}
		
		public RetroLevel getLevel() {
			return level;
		}
		
		/**
		 * prepares both map and the lambda structure for the game controller
		 * @param id
		 */
		public void prepare(int id) {
			map = AssetManager.loadMap(id);
			int levelId = id + 1;
			lambdaUtil.createTreeFromJson(String.format(JSON_PATTERN, levelId));
			addGameelements();
			level = new RetroLevel(levelId, this.map, this.lambdaUtil);
		}
		
		private void addGameelements() {
			LinkedList<Vertex> levelelements = lambdaUtil.getVertexList();
			TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
			for (int i = 0; i < levelelements.size(); i++) {
				Vertex v = levelelements.get(i);
				int offset = (Integer) v.getGameElement().getTileSet().getProperties().get("firstgid") - 1;
				int color = v.getColor();
				v.getGameElement().setTileId(color + offset);
				if (color <= Constants.MAX_COLOR_ID) {
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

	

}
