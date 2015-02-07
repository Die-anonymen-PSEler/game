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

public class LambdaUtil {

	private List<OnNextLambdaStepListener> observers;

	private final static String DATA = "data";
	private final static String TREE = "tree";
	private final static String HINT = "hint";
	private final static String LEVEL = "level";
	private final static String GAMEELEMENTS = "gameelements";

	private LevelTree levelTree;
	private LevelTree target;
	private LevelTree hintTree;
	private LinkedList<Vertex> gameelements;

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
			System.out.println("File not found");
			return;
		}
		Gson gson = new GsonBuilder().create();
		JsonObject root = gson.fromJson(br, JsonObject.class);
		// get json elements(description, id...)
		JsonObject level = root.getAsJsonObject(LEVEL);
		JsonObject data = level.getAsJsonObject(DATA);
		JsonArray elements = data.getAsJsonArray(GAMEELEMENTS);
		JsonArray hint = data.getAsJsonArray(HINT);
		System.out.println(data.toString());
		JsonArray tree = data.getAsJsonArray(TREE);
		System.out.println(tree.toString());
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Could not close BufferedReader!");
		}
		gameelements = makeGameelementList(elements);
		levelTree = new LevelTree(makeStartVertexTree(tree));
		hintTree = new LevelTree(makeStartVertexHint(hint));
		
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
		while (true) {
			// TODO weird lambda evaluation stuff
			for (OnNextLambdaStepListener listener : observers) {
				listener.nextLambdaStepPerformed();
			}
		}
	}

	public interface OnNextLambdaStepListener {

		/**
		 * this method will be called whenever one step of the lambda evaluation
		 * has been completed.
		 */
		public void nextLambdaStepPerformed();

	}
	
	private LinkedList<Vertex> makeGameelementList(JsonArray elements) {
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

	private Vertex makeStartVertexTree(JsonArray tree) {
		if (tree == null) {
			return null;
		}

		Dummy dummy = null;
		int count = 0;
		for (JsonElement t : tree) {
			dummy = new Dummy();
			if (count == tree.size()) {
				dummy.setnext(null);
			} else {
				//System.out.println(count);
				dummy.setnext(new Dummy());
			}
			count++;
			// setting family
			dummy.setfamily(makeStartVertexTree(t.getAsJsonObject()
					.getAsJsonArray(TREE)));
		}
		return dummy;
	}
	
	private Vertex makeStartVertexHint(JsonArray hint) {
		if (hint == null) {
			return null;
		}
		Vertex start = null;
		int count = 0;
		// TODO: fertig implementieren
		for (JsonElement t : hint) {
			//creating vertex
			start = getSpecializedVertex(t.getAsJsonObject());
			//setting v.next
			if (count == hint.size()) {
				start.setnext(null);
			} else {
				JsonObject nextOb = hint.get(count +1).getAsJsonObject();
				start.setnext(getSpecializedVertex(nextOb));
			}
			count++;
			//setting family
			start.setfamily(makeStartVertexHint(t.getAsJsonObject().getAsJsonArray(HINT)));
		}
		return start;
	}
	
	private Vertex makeStartVertexTarget(JsonArray target) {
		//TODO: implement
		return null;
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
		switch (type) {
		case "Var":
			return new Variable(color);
		case "Abs":
			return new Abstraction(color);
		case "App":
			return new Application(color);
		default:
			return null;
		}
	}
	
	
	public LinkedList<Vertex> getGameElements() {
		return gameelements;
	}
	
}
