package com.retroMachines.game;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.retroMachines.data.AssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;
import com.retroMachines.util.lambda.Dummy;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Vertex;

/**
 * a class containing all relevant information regarding a level 
 * @author RetroFactory
 *
 */
public class RetroLevel {
	
	public static final int DEPOTID = 8;
	
	private int levelId;
	
	private LambdaUtil lambdaUtil;
	
	private LevelTree evaluationTree;
	
	private TiledMap map;
	
	/**
	 * Dummy tree until all depots are filled and 
	 * allDepotsFilled() = true 
	 * 	then its tree with objects placed in the depots
	 */
	private LevelTree depotTree;
	
	private LinkedList<Vertex> vertexInDepot;
	
	/**
	 * private constructor so levelbuilder class has to be used
	 * @param id
	 * @param map
	 * @param util
	 */
	private RetroLevel(int id, TiledMap map, LambdaUtil util) {
		this.levelId = id;
		this.map = map;
		lambdaUtil = util;
	}

	/**
	 * Checks if a given position is suiteable for a gameelement (whether it makes sense)
	 * @param pos the vector in question
	 * @return true if valid; false otherwise.
	 */
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
	
	/**
	 * Assigns a GameElement a new position and updates it's coordinates within the lambdautil.
	 * Also adds the element back to the map for rendering.
	 * You might wanna call the isValidGameElementPosition first
	 * @param element the element which will be readded to the map
	 * @param newPos the position where the element will be added.
	 */
	public void placeGameElement(GameElement element, Vector2 newPos) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		Vertex vertex = lambdaUtil.getVertex(element);
		vertex.setPosition(newPos.cpy());
		
		Cell cell = new Cell();
		cell.setTile(element.getTileSet().getTile(element.getTileId()));
		layer.setCell((int) newPos.x, (int) newPos.y, cell);
	}
	
	/**
	 * Removes an Object from the map. This does only remove the visual appearance!
	 * @param elementPosition the vector where element shall be removed
	 */
	public void removeGameElement(GameElement element, Vector2 elementPosition) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		layer.setCell((int) elementPosition.x, (int) elementPosition.y, null);
		//Set vertex Pos to -1 -1 so its not longer placed on old place
		Vertex vertex = lambdaUtil.getVertex(element);
		vertex.setPosition(new Vector2(-1, -1));
	}
	
	/**
	 * Returns GameElement at a given position in TiledMap
	 * 
	 * @param posObj
	 *            Position in TiledMap of the GameElement.
	 * @return The GameElement at this position ( null when empty).
	 */
	public GameElement getGameElement(Vector2 posObj) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
		Cell cell = layer.getCell((int) posObj.x, (int) posObj.y);
		return (cell == null) ? null : lambdaUtil.getGameElement((int) posObj.x, (int) posObj.y);
	}
	
	public boolean allDepotsFilled() {
		
		//Get Depot Tile as Cell objekt
		TiledMapTileLayer depotLayer = (TiledMapTileLayer) map.getLayers().get(Constants.DEPOT_LAYER);
		TiledMapTileSet depotSet = map.getTileSets().getTileSet(Constants.TILESETNAME_DEPOT);
		int offsetDepot = (Integer) depotSet.getProperties().get("firstgid");
		Cell depotCell = new Cell();
		depotCell.setTile(depotSet.getTile(DEPOTID + offsetDepot));
		
		int numOfVInDepot = 0;
		
		vertexInDepot = new LinkedList<Vertex>();
		for (Vertex v : lambdaUtil.getVertexList()) {
			Vector2 pos = v.getPosition();
			
			//Check if position of v is same as an Depot
			if (depotLayer.getCell((int)pos.x, (int)pos.y) != null 
					&& depotLayer.getCell((int)pos.x, (int)pos.y).getTile().getId() == depotCell.getTile().getId() ) {
				
				// increase counter
				numOfVInDepot++;
				
				// Make List of all Vertex in Depot
				vertexInDepot.add(v);			
			}
		}
		if ( numOfVInDepot < lambdaUtil.getNumOfDepots()) {
			//not all elements are placed
			return false;
		}
		makeEvaluationTree();
		return true;
	}
	
	private boolean makeEvaluationTree() {
		
		// Search start Vertex 
		int y = Integer.MAX_VALUE;
		for (Vertex v : vertexInDepot) {
			if((int)v.getPosition().y < y) {
				y = (int)v.getPosition().y;
			}
		}
		if(y == Integer.MAX_VALUE) {
			// Error
			Gdx.app.log(Constants.LOG_TAG, "No Start Vertex found");
		}
		Vertex start = buildTree(y, lambdaUtil.getLevelTree().getStart());
		evaluationTree = new LevelTree(start);
		return true;
	}
	
	private Vertex buildTree(int y, Vertex dummy) {
		Vertex result;
		// Build family and your self
		if (dummy.getfamily() != null) {
			Vertex fam = buildTree(y + Constants.DEPOTLAYER_Y_DIF, dummy.getfamily());
			int index = findVertexPosY(y);
			result = vertexInDepot.remove(index);
			result.setfamily(fam);
			
			// set Width
			result.setWidth(fam.getWidth() + fam.getNextWidth());
			
			//Updtae ColorList
			LinkedList<Integer> newColorList = fam.getFamilyColorList();
			
			// Color of application is always the same
			if(!result.getType().equals("Application")) {
				// Add own color if its not in colorlist
				if(!newColorList.contains(new Integer(result.getColor()))) {
					newColorList.add(result.getColor());
				}
			}

			result.setFamilyColorlist(newColorList);
		} else {
			int index = findVertexPosY(y);
			result = vertexInDepot.remove(index);
			result.setfamily(null);
			// Make family color list only with own color
			LinkedList<Integer> newColorList = new LinkedList<Integer>();
			
			// Color of application is always the same
			if(!result.getType().equals("Application")) {
				newColorList.add(result.getColor());
			}
			result.setFamilyColorlist(newColorList);
		}
		
		// Build vertex next to you if not null
		if(dummy.getnext() != null) {
			Vertex next = buildTree(y, dummy.getnext());
			
			result.setnext(next);
			
			// set nextWidth
			result.setNextWidth(next.getWidth() + result.getNextWidth());
			
			LinkedList<Integer> nextColorList = next.getNextColorList();
			for(Integer c : next.getFamilyColorList()) {
				if(!nextColorList.contains(c)) {
					nextColorList.add(c);
				}
			}
			result.setNextColorlist(nextColorList);
			
		} else {
			result.setnext(null);
			result.setNextColorlist(new LinkedList<Integer>());
		}
		return result;
	}
	
	/**
	 * Returns index of Vertex with smallest x pos and given y pos
	 * @param yPos yPosition of searched Vertex
	 * @return index of searched Vertex in "vertexinDepot" List
	 */
	private int findVertexPosY(int yPos) {
		 Vertex result = new Dummy();
		 result.setPosition(new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		 int indexResult = -1;
		 int i = 0;
		 for (Vertex v: vertexInDepot) {
			 if((int) v.getPosition().y == yPos) {
				 if(v.getPosition().x <  result.getPosition().x) {
					 result = v;
					 indexResult = i;
				 }
			 }
			 i++;
		 }
		 return indexResult;
	 }
	

	/**
	 * Returns an array of rectangles based on the tiles that are within a given area.
	 * @param startX the beginning x coordinate
	 * @param startY the beginning y coordinate
	 * @param endX the end x coordinate
	 * @param endY the end y coordinate
	 * @param layerId the id where the rectangles shall be gathered.
	 * @return an array containing those gathered rectangles
	 */
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
	

	/**
	 * Get method for the map
	 * @return the map associated with this level
	 */
	public TiledMap getMap() {
		return map;
	}
	
	/**
	 * returns the lambda util associated with this level
	 * @return
	 */
	public LambdaUtil getLambdaUtil() {
		return lambdaUtil;
	}
	
	/**
	 * Getter for the evaluation tree of this level
	 * @return evaluation Tree
	 */
	public LevelTree getEvaluationTree() {
		return evaluationTree;
	}

	
	
	/**
	 * LevelBuilder class to generate a retrolevel.
	 * @author RetroFactory
	 *
	 */
	public static class LevelBuilder {
		
		/**
		 * a pattern where the level json files may be found
		 */
		public static final String JSON_PATTERN = "maps/LevelJsons/Level%s.json";
		
		/**
		 * the tiledmap that belongs with the level
		 */
		private TiledMap map;
		
		/**
		 * the lambda util associated with the level
		 */
		private LambdaUtil lambdaUtil;
		
		/**
		 * the level that was created based on the id
		 */
		private RetroLevel level;
		
		/**
		 * Creates a new instance of the levelbuilder
		 */
		public LevelBuilder() {
			lambdaUtil = new LambdaUtil();
		}
		
		/**
		 * Returns the level that was created once the prepare method was called
		 * @return the level generated
		 */
		public RetroLevel getLevel() {
			return level;
		}
		
		/**
		 * prepares both map and the lambda structure for the game controller
		 * @param id the id of the level. range [1 - Constants.MAX_LEVEL]
		 */
		public void prepare(int id) {
			map = AssetManager.loadMap(id);
			int levelId = id + 1;
			lambdaUtil.createTreeFromJson(String.format(JSON_PATTERN, levelId));
			addGameelements();
			level = new RetroLevel(levelId, this.map, this.lambdaUtil);
		}
		
		/**
		 * adds the gameelements to the map.
		 */
		private void addGameelements() {
			LinkedList<Vertex> levelelements = lambdaUtil.getVertexList();
			
			TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Constants.OBJECT_LAYER_ID);
			for (Vertex v : levelelements) {
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
