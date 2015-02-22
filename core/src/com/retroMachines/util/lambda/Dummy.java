package com.retroMachines.util.lambda;

import java.util.LinkedList;

import com.retroMachines.game.gameelements.GameElement;

/**
 * A class to instanciate dummy vertices. Each method does nothing
 * @author RetroFactory
 *
 */
public class Dummy extends Vertex {

	@Override
	public LinkedList<Vertex> betaReduction() {
		//dummy object, does nothing
		return new LinkedList<Vertex>();
	}

	@Override
	public boolean alphaConversion() {
		//dummy object, does nothing
		return false;
	}

	@Override
	public Vertex cloneMe() {
		//dummy object, does nothing
		return null;
	}

	@Override
	public Vertex cloneFamily() {
		//dummy object, does nothing
		return null;
	}

	@Override
	public GameElement getGameElement() {
		//no gameElement, just a dummy object
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
