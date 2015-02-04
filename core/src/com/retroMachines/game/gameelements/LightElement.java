package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.retroMachines.data.AssetManager;

/**
 * This class is part of the model of RetroMachines.
 * A light element that represents the application within the lambda calculus.
 * @author RetroFactory
 */
public class LightElement extends GameElement {
	
	/**
	 * Creates a new instance of LightElement and assigns a texture to it
	 * for rendering purposes.
	 */
	public LightElement() {
		// TODO Auto-generated constructor stub
		// TODO update texture
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(String color) {
		skin = AssetManager.getGameelementskin(); 
		texture = skin.get(color + "Light", TextureRegion.class).getTexture();	
	}

}
