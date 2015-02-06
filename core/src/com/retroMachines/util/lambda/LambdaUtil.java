package com.retroMachines.util.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.retroMachines.util.lambda.data.Root;
import com.retroMachines.util.lambda.data.Tree;

public class LambdaUtil {

	private List<OnNextLambdaStepListener> observers;
	
	private final static String DATA  = "data";
	private final static String TREE = "tree";

	private LevelTree levelTree;
	private LevelTree target;
	private LevelTree hint;

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
		//get json elements(description, id...)
		JsonObject level = root.getAsJsonObject("level");
		JsonObject data = level.getAsJsonObject(DATA);
		System.out.println(data.toString());
		JsonArray tree = data.getAsJsonArray(TREE);
		System.out.println(tree.toString());
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Could not close BufferedReader!");
		}
		Vertex v = makeTree(tree);
		levelTree = new LevelTree(v);
		System.out.println(v.getnext().getnext());

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

	private Vertex makeTree(JsonArray tree) {
		if (tree == null) {
			return null;
		}
		
		Dummy dummy = null;
		int count = 0;
		for (JsonElement t : tree) {
			dummy = new Dummy();
			if ( count == tree.size()) {
				dummy.setnext(null);
			} else {
				System.out.println(count);
				dummy.setnext(new Dummy());
			}
			count++;
			//setting family
			dummy.setfamily(makeTree(t.getAsJsonObject().getAsJsonArray(TREE)));
		}
		return dummy;
	}

}
