package com.retroMachines.game;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.retroMachines.data.RetroAssetManager;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.ui.RetroDialogChain;
import com.retroMachines.util.Constants;
import com.retroMachines.util.Constants.RetroStrings;
import com.retroMachines.util.lambda.Dummy;
import com.retroMachines.util.lambda.LambdaUtil;
import com.retroMachines.util.lambda.LevelTree;
import com.retroMachines.util.lambda.Vertex;

/**
 * a class containing all relevant information regarding a level
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class RetroLevel {

	/**
	 * The ID of the depot.
	 */
	public static final int DEPOTID = 8;

	private String errorMessage;

	private int levelId;

	private LambdaUtil lambdaUtil;

	private LevelTree evaluationTree;

	private TiledMap map;

	private LinkedList<Vertex> vertexInDepot;

	/**
	 * A private constructor so that the "levelBuilder" class has to be used.
	 * 
	 * @param id The ID of the level.
	 * @param map The map of the level.
	 * @param util The util which are used.
	 */
	private RetroLevel(int id, TiledMap map, LambdaUtil util) {
		this.levelId = id;
		this.map = map;
		lambdaUtil = util;
	}
	
	private boolean canMakeEvaluationTree() {
		errorMessage = "";
		// Search start Vertex
		int y = Integer.MAX_VALUE;
		for (Vertex v : vertexInDepot) {
			if ((int) v.getPosition().y < y) {
				y = (int) v.getPosition().y;
			}
		}
		if (y == Integer.MAX_VALUE) {
			// Error
			Gdx.app.log(Constants.LOG_TAG, "No Start Vertex found");
		}
		Vertex start = buildTree(y, lambdaUtil.getLevelTree().getStart());
		if (start == null) {
			return false;
		}
		evaluationTree = new LevelTree(start);
		return true;
	}

	private Vertex buildTree(int y, Vertex dummy) {
		Vertex result;
		// Build family and your self
		if (dummy.getFamily() != null) {
			Vertex fam = buildTree(y + Constants.DEPOTLAYER_Y_DIF,
					dummy.getFamily());
			if (fam == null) {
				return null;
			}

			result = findVertexPosY(y);

			// Check if construct is valid
			if (!equalsValidVertexFamily(result, fam)) {
				return null;
			}

			result.setFamily(fam);
			// set Width
			result.setWidth(fam.getWidth() + fam.getNextWidth());

			// Updtae ColorList
			LinkedList<Integer> newColorList = new LinkedList<Integer>();
			for (int i : fam.getFamilyColorList()) {
				newColorList.add(i);
			}
			for (int i : fam.getNextColorList()) {
				newColorList.add(i);
			}
			// Color of application is always the same
			if (!result.getType().equals("Application")) {
				// Add own color if its not in colorlist
				if (!newColorList.contains(result.getColor())) {
					newColorList.add(result.getColor());
				}
			}
			result.setFamilyColorlist(newColorList);
		} else {
			result = findVertexPosY(y);
			result.setFamily(null);
			// Make family color list only with own color
			LinkedList<Integer> newColorList = new LinkedList<Integer>();

			// Color of application is always the same
			if (!result.getType().equals("Application")) {
				newColorList.add(result.getColor());
			}
			result.setFamilyColorlist(newColorList);
		}

		// Build vertex next to you if not null
		if (dummy.getNext() != null) {
			Vertex next = buildTree(y, dummy.getNext());
			if (next == null) {
				return null;
			}
			// Check if construct is valid
			result.setNext(next);

			// set nextWidth
			result.setNextWidth(next.getWidth() + next.getNextWidth());

			LinkedList<Integer> nextColorList = new LinkedList<Integer>();
			for (int i : next.getNextColorList()) {
				nextColorList.add(i);
			}
			for (Integer c : next.getFamilyColorList()) {
				if (!nextColorList.contains(c)) {
					nextColorList.add(c);
				}
			}
			result.setNextColorlist(nextColorList);

		} else {
			// Check if construct is valid

			result.setNext(null);
			result.setNextColorlist(new LinkedList<Integer>());
		}
		return result;
	}

	private boolean equalsValidVertexFamily(Vertex v, Vertex fam) {
		if (v.getType().equals(RetroStrings.VARIABLE_TYPE) && fam != null) {
			errorMessage = RetroStrings.VARIABLE_FAMILY_INVALID;
			return false;
		} else if (v.getType().equals(RetroStrings.ABSTRACTION_TYPE)
				&& fam == null) {
			errorMessage = RetroStrings.ABSTRACTION_FAMILY_INVALID;
			return false;
		} else if (v.getType().equals(RetroStrings.APPLICATION_TYPE)
				&& fam == null) {
			errorMessage = RetroStrings.APPLICATION_FAMILY_INVALID;
			return false;
		} else {
			return true;
		}
	}

	private Vertex findVertexPosY(int yPos) {
		Vertex result = new Dummy();
		result.setPosition(new Vector2(Float.MAX_VALUE, Float.MAX_VALUE));
		int index = -1;
		int i = 0;
		for (Vertex v : vertexInDepot) {
			if ((int) v.getPosition().y == yPos) {
				if (v.getPosition().x < result.getPosition().x) {
					result = v;
					index = i;
				}
			}
			i++;
		}
		vertexInDepot.remove(index);
		Vertex returnClone = result.getClone();
		int offset = (Integer) returnClone.getGameElement().getTileSet()
				.getProperties().get("firstgid") - 1;
		int color = returnClone.getColor();
		returnClone.getGameElement().setTileId(color + offset);
		return returnClone;
	}

	/**
	 * Checks if a given position is suitable for a game element (whether it
	 * makes sense).
	 * 
	 * @param pos
	 *            The vector in question.
	 * @return True if the position is valid. False otherwise.
	 */
	public boolean isValidGameElementPosition(Vector2 pos) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				Constants.OBJECT_LAYER_ID);
		if (layer.getCell((int) pos.x, (int) pos.y) != null) {
			return false;
		}
		layer = (TiledMapTileLayer) map.getLayers().get(
				Constants.SOLID_LAYER_ID);
		if (layer.getCell((int) pos.x, (int) pos.y) != null
				|| layer.getCell((int) pos.x, ((int) pos.y - 1)) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Assigns a GameElement a new position and updates it's coordinates within
	 * the lambdaUtil. Also adds the element back to the map for rendering. You
	 * might want to call the isValidGameElementPosition first.
	 * 
	 * @param element
	 *            The element which will be read to the map.
	 * @param newPos
	 *            The position where the element will be added.
	 */
	public void placeGameElement(GameElement element, Vector2 newPos) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				Constants.OBJECT_LAYER_ID);
		Vertex vertex = lambdaUtil.getVertex(element);
		vertex.setPosition(newPos.cpy());

		Cell cell = new Cell();
		cell.setTile(element.getTileSet().getTile(element.getTileId()));
		layer.setCell((int) newPos.x, (int) newPos.y, cell);
	}

	/**
	 * Removes an Object from the map. This does only remove the visual
	 * appearance!
	 * 
	 * @param elementPosition
	 *            The vector where an element shall be removed.
	 */
	public void removeGameElement(GameElement element, Vector2 elementPosition) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				Constants.OBJECT_LAYER_ID);
		layer.setCell((int) elementPosition.x, (int) elementPosition.y, null);
		// Set vertex Pos to -1 -1 so its not longer placed on old place
		Vertex vertex = lambdaUtil.getVertex(element);
		vertex.setPosition(new Vector2(-1, -1));
	}
	
	/**
	 * A method to control if all the depots are filled.
	 * @return True if all the depots are filled. False otherwise.
	 */
	public boolean isAllDepotsFilled() {

		// Get a depot tile as a cell object
		TiledMapTileLayer depotLayer = (TiledMapTileLayer) map.getLayers().get(
				Constants.DEPOT_LAYER);
		TiledMapTileSet depotSet = map.getTileSets().getTileSet(
				RetroStrings.TILESETNAME_DEPOT);
		int offsetDepot = (Integer) depotSet.getProperties().get("firstgid");
		Cell depotCell = new Cell();
		depotCell.setTile(depotSet.getTile(DEPOTID + offsetDepot));

		int numOfVInDepot = 0;

		vertexInDepot = new LinkedList<Vertex>();
		for (Vertex v : lambdaUtil.getVertexList()) {
			Vector2 pos = v.getPosition();

			// Check if position of v is same as an Depot
			if (depotLayer.getCell((int) pos.x, (int) pos.y) != null
					&& depotLayer.getCell((int) pos.x, (int) pos.y).getTile()
							.getId() == depotCell.getTile().getId()) {

				// increase counter
				numOfVInDepot++;

				// Make List of all Vertex in Depot
				vertexInDepot.add(v);
			}
		}
		if (numOfVInDepot < lambdaUtil.getNumOfDepots()) {
			// not all elements are placed
			errorMessage = RetroStrings.NOT_ALL_PLACED;
			return false;
		}
		if (!canMakeEvaluationTree()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method to control if the level owns a tutorial.
	 * @return True if a tutorial exists. False otherwise.
	 */
	public boolean hasTutorial() {
		return lambdaUtil.hasTutorial();
	}

	/*
	 * Getter and Setter
	 */
	
	/**
	 * Returns the game element at a given position in the TiledMap.
	 * 
	 * @param posObj
	 *            Position in TiledMap of the GameElement.
	 * @return The GameElement at this position ( null when empty).
	 */
	public GameElement getGameElement(Vector2 posObj) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				Constants.OBJECT_LAYER_ID);
		Cell cell = layer.getCell((int) posObj.x, (int) posObj.y);
		return (cell == null) ? null : lambdaUtil.getGameElement(
				(int) posObj.x, (int) posObj.y);
	}

	/**
	 * Get method for the ID of the level.
	 * 
	 * @return The ID of the level.
	 */
	public int getId() {
		return levelId;
	}

	/**
	 * Get method for the message if an error occurred.
	 * 
	 * @return The error-message.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Returns an array of rectangles based on the tiles that are within a given
	 * area.
	 * 
	 * @param startX
	 *            The beginning x coordinate.
	 * @param startY
	 *            The beginning y coordinate.
	 * @param endX
	 *            The end x coordinate.
	 * @param endY
	 *            The end y coordinate.
	 * @param layerId
	 *            The ID where the rectangles shall be gathered.
	 * @return An array containing those gathered rectangles.
	 */
	public Array<Rectangle> getTiles(int startX, int startY, int endX,
			int endY, int layerId) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				layerId);
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
	 * Get method for the map.
	 * 
	 * @return The map associated with this level.
	 */
	public TiledMap getMap() {
		return map;
	}

	/**
	 * Get method for the lambda util of this level.
	 * 
	 * @return The lambda util associated with this level.
	 */
	public LambdaUtil getLambdaUtil() {
		return lambdaUtil;
	}

	/**
	 * Get method for the evaluation tree of this level.
	 * 
	 * @return The evaluation tree.
	 */
	public LevelTree getEvaluationTree() {
		return evaluationTree;
	}

	public RetroDialogChain getDialogChain() {
		RetroDialogChain chain = new RetroDialogChain();
		List<Texture> textures = lambdaUtil.getTutorials();
		for (Texture texture : textures) {
			chain.addDialog("", texture);
		}
		return chain;
	}

	/**
	 * LevelBuilder class to generate a retro-level.
	 * 
	 * @author RetroFactory
	 * @version 1.0
	 */
	public static class LevelBuilder {

		/**
		 * A pattern where the level-json-files may be found.
		 */
		public static final String JSON_PATTERN = "maps/LevelJsons/Level%s.json";

		/**
		 * The tiled-map that belongs with the level.
		 */
		private TiledMap map;

		/**
		 * The lambda util associated with the level.
		 */
		private LambdaUtil lambdaUtil;

		/**
		 * The level that was created based on the ID.
		 */
		private RetroLevel level;

		/**
		 * Creates a new instance of the levelBuilder.
		 */
		public LevelBuilder() {
			lambdaUtil = null;
			lambdaUtil = new LambdaUtil();
			map = null;
		}

		//adds the game elements to the map.
		private void addGameelements() {
			List<Vertex> levelelements = lambdaUtil.getVertexList();

			TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
					Constants.OBJECT_LAYER_ID);
			for (Vertex v : levelelements) {
				int offset = (Integer) v.getGameElement().getTileSet()
						.getProperties().get("firstgid") - 1;
				int color = v.getColor();
				v.getGameElement().setTileId(color + offset);
				if (color <= Constants.MAX_COLOR_ID) {
					Cell cell = new Cell();
					cell.setTile(v.getGameElement().getTileSet()
							.getTile(color + offset));
					Vector2 position = v.getPosition();
					layer.setCell((int) position.x, (int) position.y, cell);
				} else {
					Gdx.app.log(Constants.LOG_TAG,
							"Out of ColorRange in TiledSets");
				}
			}
		}
		
		/**
		 * Prepares both map and the lambda structure for the game controller.
		 * 
		 * @param id
		 *            The ID of the level. range [1 - Constants.MAX_LEVEL].
		 */
		public void prepare(int id) {
			map = RetroAssetManager.getMap(id);
			int levelId = id + 1;
			lambdaUtil.createTreeFromJson(String.format(JSON_PATTERN, levelId));
			addGameelements();
			level = new RetroLevel(levelId, this.map, this.lambdaUtil);
		}

		/**
		 * Get method for the level that was created once the prepare method was called.
		 * 
		 * @return The level generated.
		 */
		public RetroLevel getLevel() {
			return level;
		}
	}
}
