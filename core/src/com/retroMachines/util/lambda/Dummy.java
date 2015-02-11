package com.retroMachines.util.lambda;

import com.retroMachines.game.gameelements.GameElement;

/**
 * A class to instanciate dummy vertices. Each method does nothing
 * @author RetroFactory
 *
 */
public class Dummy extends Vertex {

	@Override
	public boolean betaReduction() {
		//dummy object, does nothing
		return false;
	}

	@Override
	public boolean alphaConversion() {
		//dummy object, does nothing
		return false;
	}

	@Override
	public Vertex cloneMe(Vertex next) {
		//dummy object, does nothing
		return null;
	}

	@Override
	public Vertex cloneFamily() {
		//dummy object, does nothing
		return null;
	}

	@Override
	public GameElement getGameElementFromVertex() {
		//no gameElement, just a dummy object
		return null;
	}

}
