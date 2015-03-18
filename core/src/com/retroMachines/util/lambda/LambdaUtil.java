package com.retroMachines.util.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.retroMachines.game.gameelements.GameElement;
import com.retroMachines.util.Constants;

/**
 * 
 * @author RetroFactory
 * 
 */
public class LambdaUtil {

	private final static String DATA = "data";
	private final static String TREE = "tree";
	private final static String HINT = "hint";
	private final static String LEVEL = "level";
	private final static String TARGET = "target";
	private final static String TUTORIALS = "tutorialScreens";
	private final static String HAS_TUTORIALS = "hasTutorialScreen";
	private final static String IMAGE = "img";
	private final static String GAMEELEMENTS = "gameelements";

	private List<OnNextLambdaStepListener> observers;
	private LevelTree levelTree;
	private boolean hasTutorial;
	private int numOfDepots;
	private LevelTree targetTree;
	private LevelTree hintTree;
	private List<Vertex> vertexList;
	private List<Texture> tutorialImgs;
	private List<GameElement> gameElementList;

	/**
	 * Constructor to create a base for the lambda calculus.
	 */
	public LambdaUtil() {
		observers = new ArrayList<OnNextLambdaStepListener>();
		vertexList = new LinkedList<Vertex>();
	}
	
	private LinkedList<Vertex> makeVertexList(JsonArray elements) {
		LinkedList<Vertex> elementList = new LinkedList<Vertex>();
		for (JsonElement e : elements) {
			JsonObject elementObject = e.getAsJsonObject();
			Vertex element = getSpecializedVertex(elementObject);
			Vector2 newPos = new Vector2(
					elementObject.get("posx").getAsFloat(), elementObject.get(
							"posy").getAsFloat());
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
	 * 
	 * @param tree
	 *            tree in json representation
	 * @return root vertex of tree
	 */
	private Vertex makeStartVertexTree(JsonArray tree) {

		if (tree.size() == 0) {
			return null;
		}

		Vertex start = new Dummy();
		int count = 1; // index of current element
		Vertex actVertex = new Dummy();
		for (JsonElement t : tree) {

			numOfDepots++;
			if (count == tree.size()) {
				actVertex.setNext(null); // lastVertex.next is null
			} else {
				actVertex.setNext(new Dummy());
			}

			actVertex.setFamily(makeStartVertexTree(t.getAsJsonObject()
					.getAsJsonArray(TREE)));
			if (count == 1) {
				// pointer on start
				start.setNext(actVertex);
			}
			actVertex = actVertex.getNext();

			count++;
		}

		return start.getNext();
	}

	/**
	 * creates lambda-tree representation of given json array for target- or
	 * hint tree.
	 * 
	 * @param array
	 *            tree in json representation
	 * @param type
	 *            type of tree: must be HINT or TARGET
	 * @return root vertex of tree
	 */
	private Vertex makeStartVertexHintOrTarget(JsonArray array, String type) {
		if (array == null) {
			return null;
		}
		if (array.size() == 0) {
			return null;
		}
		Vertex start = null;
		int count = 1; // index of current element in array

		for (JsonElement t : array) {
			// creating vertex
			Vertex actVertex = getSpecializedVertex(t.getAsJsonObject());
			// setting v.next
			if (count == array.size()) {
				actVertex.setNext(null); // lastVertex.next is null
			} else {
				JsonObject nextOb = array.get(count).getAsJsonObject();
				actVertex.setNext(getSpecializedVertex(nextOb));
			}
			// setting family
			actVertex.setFamily(makeStartVertexHintOrTarget(t.getAsJsonObject()
					.getAsJsonArray(type), type));

			if (count == 1) {
				start = actVertex;
			}
			count++;
		}
		return start;
	}

	/**
	 * Adds Alle TutorialImages of Level
	 * 
	 * @param tutList
	 * @return
	 */
	private LinkedList<Texture> makeTutorialImgList(JsonArray tutList) {
		if (tutList == null || tutList.size() == 0) {
			return new LinkedList<Texture>();
		}
		LinkedList<Texture> resultList = new LinkedList<Texture>();
		for (JsonElement j : tutList) {
			String name = j.getAsJsonObject().get(IMAGE).getAsString();
			Texture img = new Texture(Gdx.files.internal("maps/Tutorials/"
					+ name + ".png"));
			resultList.addLast(img);
		}
		return resultList;
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
		// getting and returning right type
		Vertex result;
		if (type.equals("Abs")) {
			result = new Abstraction(color);
		} else if (type.equals("App")) {
			result = new Application();
		} else if (type.equals("Var")) {
			result = new Variable(color);
		} else if (type.equals("Dum")) {
			result = new Dummy();
		} else {
			Gdx.app.error(Constants.LOG_TAG, "invalid vertex type: " + type);
			return null;
		}

		return result;
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
		JsonArray tutorials = level.getAsJsonArray(TUTORIALS);
		JsonPrimitive hasTutScreens = level.getAsJsonPrimitive(HAS_TUTORIALS);
		JsonObject data = level.getAsJsonObject(DATA);
		JsonArray elements = data.getAsJsonArray(GAMEELEMENTS);
		JsonArray hint = data.getAsJsonArray(HINT);
		JsonArray target = data.getAsJsonArray(TARGET);
		JsonArray tree = data.getAsJsonArray(TREE);

		try {
			br.close();
		} catch (IOException e) {
			Gdx.app.log(Constants.LOG_TAG, "Could not close BufferedReader!", e);
		}

		hasTutorial = hasTutScreens.getAsBoolean();
		tutorialImgs = makeTutorialImgList(tutorials);
		vertexList = makeVertexList(elements);
		gameElementList = makeGameElementList();
		numOfDepots = 0;
		levelTree = new LevelTree(makeStartVertexTree(tree));
		hintTree = new LevelTree(makeStartVertexHintOrTarget(hint, HINT));
		targetTree = new LevelTree(makeStartVertexHintOrTarget(target, TARGET));

	}

	public void registerNewListener(OnNextLambdaStepListener listener) {
		if (!observers.contains(listener)) {
			observers.add(listener);
		}
	}

	public void unregisterNewListener(OnNextLambdaStepListener listener) {
		observers.remove(listener);
	}

	public boolean hasTutorial() {
		return hasTutorial;
	}

	/*
	 * Getter and Setter
	 */
	
	/**
	 * returns gameElement according to vertex with specified position. Null if
	 * there is no such vertex
	 * 
	 * @param posX
	 *            x coordinate
	 * @param posY
	 *            y coordinate
	 * @return gameElement according to vertex
	 */
	public GameElement getGameElement(int posX, int posY) {
		// getting vertex
		Vertex vertex = null;
		for (Vertex v : vertexList) {
			if ((int) v.getPosition().x == posX
					&& (int) v.getPosition().y == posY) {
				vertex = v;
				break;
			}
		}
		return (vertex != null) ? vertex.getGameElement() : null;
	}

	/**
	 * returns vertex belonging to given gameElement.
	 * 
	 * @param g
	 *            GameElement to get vertex of
	 * @return vertex belonging to GameElement
	 */
	public Vertex getVertex(GameElement g) {
		if (g == null) {
			return null;
		}
		for (Vertex v : vertexList) {
			if (v.getGameElement().equals(g)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * getter for vertexList
	 * 
	 * @return vertexList
	 */
	public List<Vertex> getVertexList() {
		return vertexList;
	}

	/**
	 * getter for gameElementList
	 * 
	 * @return gameElementList
	 */
	public List<GameElement> getGameElementList() {
		return gameElementList;
	}

	/**
	 * getter for levelTree
	 * 
	 * @return may be null if {@link #createTreeFromJson(String)
	 *         createTreeFromJson} method was not invoked
	 */
	public LevelTree getLevelTree() {
		return levelTree;
	}

	/**
	 * getter for targetTree
	 * 
	 * @return may be null if {@link #createTreeFromJson(String)
	 *         createTreeFromJson} method was not invoked
	 */
	public LevelTree getTargetTree() {
		return targetTree;
	}

	public List<Texture> getTutorials() {
		return tutorialImgs;
	}

	/**
	 * getter for hintTree
	 * 
	 * @return may be null if {@link #createTreeFromJson(String)
	 *         createTreeFromJson} method was not invoked
	 */
	public LevelTree getHintTree() {
		return hintTree;
	}

	public int getNumOfDepots() {
		return numOfDepots;
	}

	/**
	 * getter for observers, needed for testcases.
	 * 
	 * @return observers as ArrayList
	 */
	public ArrayList<OnNextLambdaStepListener> getObservers() {
		return (ArrayList<OnNextLambdaStepListener>) observers;
	}

	public interface OnNextLambdaStepListener {

		/**
		 * this method will be called whenever one step of the lambda evaluation
		 * has been completed.
		 */
		public void nextLambdaStepPerformed();

	}
}
