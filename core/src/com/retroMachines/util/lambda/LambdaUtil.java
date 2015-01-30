package com.retroMachines.util.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaUtil {
	
	private List<OnNextLambdaStepListener> observers;
	
	public LambdaUtil() {
		observers = new ArrayList<OnNextLambdaStepListener>();
	}
	
	/**
	 * creates LambdaTree based on JSON description of level
	 */
	public Tree createTreeFromJason() {
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
