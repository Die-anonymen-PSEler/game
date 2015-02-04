package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.retroMachines.data.AssetManager;

/**
 * This class is part of the model of RetroMachines.
 * A machine element that represents the abstraction within the lambda calculus.
 * @author RetroFactory
 */
public class MachineElement extends GameElement {
	
	/**
	 * The ID of the color this machine element should have.
	 * The hex representation can be looked up within the constants class.
	 */
	private int colorId;
	
	
	/**
	 * Creates a new instance of MachineElement and assigns a texture to it
	 * for rendering purposes.
	 */
	public MachineElement(int colorId) {
		this.colorId = colorId;
		// TODO load texture
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setColor(String color) {
		skin = AssetManager.getGameelementskin(); 
		texture = skin.get(color + "Machine", TextureRegion.class).getTexture();	
	}
}
