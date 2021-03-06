package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.retroMachines.game.gameelements.GameElement;

/**
 * A class to instantiate dummy vertices. Each method does nothing
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class Dummy extends Vertex {
	/*
	 * @see com.retroMachines.util.lambda.Vertex#betaReduction()
	 */

	@Override
	public LinkedList<Vertex> betaReduction() {
		// dummy object, does nothing
		return new LinkedList<Vertex>();
	}

	@Override
	public boolean alphaConversion() {
		// dummy object, does nothing
		return false;
	}

	@Override
	public Vertex cloneMe() {
		// dummy object, does nothing
		return null;
	}

	@Override
	public Vertex cloneFamily() {
		// dummy object, does nothing
		return null;
	}

	@Override
	public void reorganizePositions(Vector2 start, Vector2 newPos) {
		// DO nothing because i'm a dummy

	}

	@Override
	public void deleteAfterBetaReduction() {
		// Do nothing You are a Dummy

	}

	@Override
	public Vertex updatePointerAfterBetaReduction() {
		// Do nothing You are a Dummy
		return null;
	}

	@Override
	public void updatePositionsAfterBetaReduction() {
		// Do nothing You are a Dummy
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (obj instanceof Dummy) {
			return true;
		}
		return false;
	}
	
	/*
	 * Getter and Setter
	 */

	@Override
	public Vertex getClone() {
		// its a dummy
		return null;
	}

	@Override
	public Vertex getEvaluationResult() {
		// Do nothing You are a Dummy
		return null;
	}
	
	@Override
	public GameElement getGameElement() {
		// no gameElement, just a dummy object
		return null;
	}

	@Override
	public String getType() {
		return "Dummy";

	}

	@Override
	public Vertex getReadIn() {
		return null;
	}

}
