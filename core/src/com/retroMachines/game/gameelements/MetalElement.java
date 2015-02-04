package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.retroMachines.data.AssetManager;
import com.retroMachines.util.Constants;

/**
 * This class is part of the model of RetroMachines.
 * A variable element that represents the application within the lambda calculus.
 * @author RetroFactory
 */
public class MetalElement extends GameElement {

	/**
	 * The ID of the color this metal element should have the hex representation
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
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setColor(String color) {
		skin = AssetManager.getGameelementskin(); 
		texture = skin.get(color + "Object", TextureRegion.class).getTexture();	
	}

}
