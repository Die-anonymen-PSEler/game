package com.retroMachines.util.lambda;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.google.gson.Gson;
import com.retroMachines.util.lambda.data.Data;

public class LambdaUtil {
	
	private List<OnNextLambdaStepListener> observers;
	
	private Tree tree;
	private Tree target;
	private Tree hint;
	
	public LambdaUtil() {
		observers = new ArrayList<OnNextLambdaStepListener>();
	}
	
	/**
	 * creates LambdaTree based on JSON description of level
	 */
	public Tree createTreeFromJson(String fileName) {
		tree = new Tree(null);
		//create classes with gson
		Gson gson = new Gson();
		//TODO: some fancy stuff
		return tree;
	}
	
	/**
	 * dient zu Testzwecken
	 * @param args
	 */
	public static void main(String[] args) {
		new LambdaUtil().createTreeFromJson(null);
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
		 * this method will be called whenever one step of the lambda evaluation has been completed.
		 */
		public void nextLambdaStepPerformed();
		
	}
}
