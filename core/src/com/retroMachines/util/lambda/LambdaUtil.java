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
import com.google.gson.JsonObject;
import com.retroMachines.util.lambda.data.Root;

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

		BufferedReader br = null;
		FileHandle jsonFile = Gdx.files.internal(fileName);
		try {
			br = new BufferedReader(jsonFile.reader());
		} catch (GdxRuntimeException e) {
			System.out.println("File not found");
			return null;
		}
		Gson gson = new GsonBuilder().create();
		Root root = gson.fromJson(br, com.retroMachines.util.lambda.data.Root.class);
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Could not close BufferedReader!");
		}
		return null;
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
