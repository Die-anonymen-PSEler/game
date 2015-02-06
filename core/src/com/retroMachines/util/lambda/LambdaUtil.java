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
import com.retroMachines.util.lambda.data.Root;
import com.retroMachines.util.lambda.data.Tree;

public class LambdaUtil {

	private List<OnNextLambdaStepListener> observers;

	private LevelTree tree;
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
		Root root = gson.fromJson(br,
				com.retroMachines.util.lambda.data.Root.class);
		System.out.println(gson.toJson(root));
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Could not close BufferedReader!");
		}
		List<Tree> treeList = root.getLevel().getData().getTree();
		Vertex v = makeTree(treeList);
		tree = new LevelTree(v);
		System.out.println(gson.toJson(tree));

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

	private Vertex makeTree(List<Tree> tree) {
		if (tree == null) {
			return null;
		}
		
		Dummy dummy = null;
		int count = 0;
		for (Tree t : tree) {
			dummy = new Dummy();
			if ( count == tree.size()) {
				dummy.setnext(null);
			} else {
				dummy.setnext(new Dummy());
			}
			count++;
			//setting family
			dummy.setfamily(makeTree(t.getTree()));
		}
		return dummy;
	}

}
