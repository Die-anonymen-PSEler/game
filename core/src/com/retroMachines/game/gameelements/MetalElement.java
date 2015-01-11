package com.retroMachines.game.gameelements;

import com.retroMachines.util.Constants;

/**
 * A metal element that represents the variable within the lambda calculus
 * 
 * @author RetroFactory
 * 
 */
public class MetalElement extends GameElement {

	/**
	 * The id of the color this metal element should have the hex representation
	 * can be looked up within the constants class
	 */
	private int colorId;

	/**
	 * Creates a new instance of MetalElement
	 */
	public MetalElement(int colorId) {
		this.colorId = colorId;
	}

	@Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
		String color = Constants.colorHex[colorId];
	}

}
