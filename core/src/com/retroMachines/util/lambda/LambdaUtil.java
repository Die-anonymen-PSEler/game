package com.retroMachines.util.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;

/**
 * 
 * @author RetroFactory
 *
 */
public class LambdaUtil {

	private List<OnNextLambdaStepListener> observers;

	private final static String DATA = "data";
	private final static String TREE = "tree";
	private final static String HINT = "hint";
	private final static String LEVEL = "level";
	private final static String TARGET = "TARGET";
	private final static String GAMEELEMENTS = "gameelements";
	

	private LevelTree levelTree;
	private LevelTree targetTree;
	private LevelTree hintTree;
	private LinkedList<Vertex> vertexList;
	private LinkedList<GameElement> gameElementList;
	

	/**
	 * Constructor to create a base for the lambda calculus.
	 */
	public LambdaUtil() {
		observers = new ArrayList<OnNextLambdaStepListener>();
	}

	/**
	 * creates LambdaTree based on JSON description of level
	 */
	public void createTreeFromJson(String fileName) {

		BufferedReader br = null;
		FileHandle jsonFile = Gdx.files.internal(fileName);
		try {
			br = new BufferedReader(jsonFile.reader());
		} catch (GdxRuntimeException e) {
			Gdx.app.log(Constants.LOG_TAG, "File not found", e);
			return;
		}
		Gson gson = new GsonBuilder().create();
		JsonObject root = gson.fromJson(br, JsonObject.class);
		// get json elements(description, id...)
		//
		JsonObject level = root.getAsJsonObject(LEVEL);
		JsonObject data = level.getAsJsonObject(DATA);
		JsonArray elements = data.getAsJsonArray(GAMEELEMENTS);
		JsonArray hint = data.getAsJsonArray(HINT);
		JsonArray target = data.getAsJsonArray(TARGET);
		JsonArray tree = data.getAsJsonArray(TREE);
		//
		//System.out.println(data.toString());	
		//System.out.println(tree.toString());
		try {
			br.close();
		} catch (IOException e) {
			Gdx.app.log(Constants.LOG_TAG, "Could not close BufferedReader!", e);
		}
		vertexList = makeVertexList(elements);
		gameElementList = makeGameElementList();
		levelTree = new LevelTree(makeStartVertexTree(tree));
		hintTree = new LevelTree(makeStartVertexHintOrTarget(hint, HINT));
		targetTree = new LevelTree(makeStartVertexHintOrTarget(target, TARGET));
		
		//System.out.println(v.getnext().getnext());

	}

	public void registerNewListener(OnNextLambdaStepListener listener) {
		if (!observers.contains(listener)) {
			observers.add(listener);
		}
	}

	public void unregisterNewListener(OnNextLambdaStepListener listener) {
		observers.remove(listener);
	}

	public void performEvaluation() {
			if (levelTree != null) {
				levelTree.evaluate();
			}
			for (OnNextLambdaStepListener listener : observers) {
				listener.nextLambdaStepPerformed();
			
		}
	}
	
	/**
	 * returns gameElement according to vertex with specified position. Null if there is no such vertex
	 * @param posX x coordinate
	 * @param posY y coordinate
	 * @return gameElement according to vertex
	 */
	public GameElement getGameElement(int posX, int posY) {
		//getting vertex
		Vertex vertex = null;
		for (Vertex v : vertexList) {
			if (v.getPosition().x == posX && v.getPosition().y == posY) {
				vertex = v;
				break;
			}
		}
		if (vertex != null) {
			return vertex.getGameElement();
		} else {
			return null;
		}
		
	}

	private LinkedList<Vertex> makeVertexList(JsonArray elements) {
		LinkedList<Vertex> elementList = new LinkedList<Vertex>();
		for (JsonElement e : elements) {
			JsonObject elementObject = e.getAsJsonObject();
			Vertex element = getSpecializedVertex(elementObject);
			Vector2 newPos = new Vector2(elementObject.get("posx").getAsFloat(), elementObject.get("posy").getAsFloat());
			element.setPosition(newPos);
			elementList.add(element);
		}
		return elementList;
	}
	
	private LinkedList<GameElement> makeGameElementList() {
		if (vertexList == null) {
			return null;
		}
		LinkedList<GameElement> list = new LinkedList<GameElement>();
		for (Vertex v : vertexList) {
			list.add(v.getGameElement());
		}
		return list;
	}

	/**
	 * creates lambda-tree representation of given json array for level tree.
	 * Tree contains only dummy vertices. So only structure of tree is given.
	 * @param tree tree in json representation
	 * @return root vertex of tree
	 */
	private Vertex makeStartVertexTree(JsonArray tree) {
		if (tree == null) {
			return null;
		}

		Vertex start = null;
		int count = 0; //index of current element
		for (JsonElement t : tree) {
			start = new Dummy();
			if (count == tree.size()) {
				start.setnext(null); //lastVertex.next is null
			} else {
				//System.out.println(count);
				start.setnext(new Dummy());
			}
			count++;
			// setting family
			start.setfamily(makeStartVertexTree(t.getAsJsonObject()
					.getAsJsonArray(TREE)));
		}
		return start;
	}
	
	/**
	 * creates lambda-tree representation of given json array for target- or hint tree.
	 * @param array tree in json representation
	 * @param type type of tree: must be HINT or TARGET
	 * @return root vertex of tree
	 */
	private Vertex makeStartVertexHintOrTarget(JsonArray array, String type) {
		if (array == null) {
			return null;
		}
		Vertex start = null;
		int count = 0; //index of current element in array
		for (JsonElement t : array) {
			//creating vertex
			start = getSpecializedVertex(t.getAsJsonObject());
			//setting v.next
			if (count == array.size()) {
				start.setnext(null); //lastVertex.next is null
			} else {
				JsonObject nextOb = array.get(count).getAsJsonObject();
				start.setnext(getSpecializedVertex(nextOb));
			}
			count++;
			//setting family
			start.setfamily(makeStartVertexHintOrTarget(t.getAsJsonObject().getAsJsonArray(type), type));
		}
		return start;
	}
	

	/**
	 * returns new instance of vertex of type {Var,Abs,App}. If parameter does
	 * not match one of these types, null will be returned.
	 * 
	 * @param type
	 *            type of vertex
	 * @param color
	 *            color to set
	 * @return new instance of specialized vertex
	 */
	private Vertex getSpecializedVertex(JsonObject j) {
		String type = j.get("type").getAsString();
		int color = j.get("color").getAsInt();
		//getting and returning right type
		if (type.equals("Abs")) {
			return new Abstraction(color);
		} else if (type.equals("App")) {
			return new Application(color);
		} else if (type.equals("Var")){
			return new Variable(color);
		} else {
			Gdx.app.error(Constants.LOG_TAG, "invalid vertex type: " + type);
			return null;
		}
	}
	
	
	/**
	 * getter for vertexList
	 * @return vertexList
	 */
	public LinkedList<Vertex> getVertexList() {
		return vertexList;
	}
	
	/**
	 * getter for gameElementList
	 * @return gameElementList
	 */
	public LinkedList<GameElement> getGameElementList() {
		return gameElementList;
	}
	/**
	 * getter for levelTree
	 * @return may be null if {@link #createTreeFromJson(String) createTreeFromJson} method was not invoked
	 */
	public LevelTree getLevelTree() {
		return levelTree;
	}
	
	/**
	 * getter for targetTree
	 * @return may be null if {@link #createTreeFromJson(String) createTreeFromJson} method was not invoked
	 */
	public LevelTree getTargetTree() {
		return targetTree;
	}
	
	/**
	 * getter for hintTree
	 * @return may be null if {@link #createTreeFromJson(String) createTreeFromJson} method was not invoked
	 */
	public LevelTree getHintTree() {
		return hintTree;
	}

	public interface OnNextLambdaStepListener {

		/**
		 * this method will be called whenever one step of the lambda evaluation
		 * has been completed.
		 */
		public void nextLambdaStepPerformed();

	}
	
}
