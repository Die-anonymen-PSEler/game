package com.retroMachines.game.gameelements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.retroMachines.util.Constants;

/**
 * This class is part of the model of RetroMachines. It is abstract and it's
 * children describe the Elements that can be moved within the game. They have
 * to implement a render method so they can be displayed to the user.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public abstract class GameElement extends Actor {

	/**
	 * A texture where the image of the GameElement can be stored.
	 */
	protected TextureRegion textureRegion;

	/**
	 * The ID of the tileSet that represents this element.
	 */
	protected int tileId;

	/**
	 * Creates a new instance of GameElement.
	 */
	protected GameElement() {
		setX(0f);
		setY(0f);
		setBounds(getX(), getY(), Constants.GAMEELEMENT_WIDTH,
				Constants.GAMEELEMENT_WIDTH);
	}
	
	/**
	 * The method is called by the screen to draw a game element.
	 * 
	 * @param batch The game element.
	 * @param alpha The scale.
	 */
	@Override
	public void draw(Batch batch, float alpha) {

		batch.draw(textureRegion, getX(), getY(), this.getOriginX(),
				this.getOriginY(), this.getWidth(), this.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation());
	}

	/**
	 * Get method for TileSet belonging to GameElement.
	 * 
	 * @return  The TiledMapTileSet belonging to GameElement.
	 */
	public abstract TiledMapTileSet getTileSet();

	/**
	 * Get method for the textureRegion of this class.
	 * 
	 * @return the textureRegion.
	 */
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	/**
	 * Get method for the tileId.
	 * 
	 * @return The tileId.
	 */
	public int getTileId() {
		return tileId;
	}

	/**
	 * Get method for the position (evaluation Screen).
	 * 
	 * @return The position of game element.
	 */
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	/**
	 * Sets the tileId.
	 * 
	 * @param i The new tileId.
	 */
	public void setTileId(int i) {
		this.tileId = i;
		textureRegion = getTileSet().getTile(i).getTextureRegion();
	}
	
	/**
	 * Sets the position (evaluation Screen).
	 * 
	 * @param pos The new position.
	 */
	public void setPosition(Vector2 pos) {
		setX(pos.x);
		setY(pos.y);
	}
}
