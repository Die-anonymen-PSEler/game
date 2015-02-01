package com.retroMachines.util.lambda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return null;
		}
		
		Gson gson = new GsonBuilder().create();
		com.retroMachines.util.lambda.data.Root root = gson.fromJson(br, com.retroMachines.util.lambda.data.Root.class);
		System.out.println(gson.toJson(root));
		//Problem: data ist leer, siehe syso
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Could not close BufferedReader!");
		}
		return null;
	}
	
	/**
	 * dient zu Testzwecken
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = ("/home/maik/git/game/core/assets/maps/Prototype.json");
		new LambdaUtil().createTreeFromJson(fileName);
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
